package com.conflux.finflux.login.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.conflux.finflux.R;
import com.conflux.finflux.core.FinBaseActivity;
import com.conflux.finflux.dashboard.activity.DashBoardActivity;
import com.conflux.finflux.db.LoginUser;
import com.conflux.finflux.db.LoginUserRole;
import com.conflux.finflux.infrastructure.FinfluxApplication;
import com.conflux.finflux.infrastructure.analytics.services.ApplicationAnalytics;
import com.conflux.finflux.infrastructure.api.manager.BaseApiManager;
import com.conflux.finflux.infrastructure.api.manager.Data;
import com.conflux.finflux.injection.component.ActivityComponent;
import com.conflux.finflux.login.data.LoginConstants;
import com.conflux.finflux.login.data.Role;
import com.conflux.finflux.login.data.User;
import com.conflux.finflux.login.presenter.LoginPresenter;
import com.conflux.finflux.login.viewservices.LoginMvpView;
import com.conflux.finflux.settings.activity.SettingsActivity;
import com.conflux.finflux.util.Logger;
import com.conflux.finflux.db.LoginUserPermission;
import com.conflux.finflux.util.Network;
import com.conflux.finflux.util.PrefManager;
import com.conflux.finflux.util.RealmAutoIncrement;
import com.conflux.finflux.util.Toaster;

import java.security.cert.CertificateException;

import javax.inject.Inject;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmList;

public class LoginActivity extends FinBaseActivity implements LoginMvpView {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @Bind(R.id.input_username)
    EditText _emailText;
    @Bind(R.id.input_password)
    EditText _passwordText;
    @Bind(R.id.btn_login)
    Button _loginButton;
    @Bind(R.id.btn_settings)
    ImageView btn_settings;

    String username;
    String password;
    private ActivityComponent mActivityComponent;
    @Inject
    public Data mDataManager;
    @Inject
    public LoginPresenter mLoginPresenter;
    private ProgressDialog progress;
    private Realm realm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        realm = Realm.getDefaultInstance();
        Logger.d(TAG, "The loginviewer is " + mLoginPresenter);
        mLoginPresenter.attachView(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login(false);
            }
        });
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @OnClick(R.id.btn_settings)
    public void initiateStartSettingsActivity(View view){
        startActivity(new Intent(this, SettingsActivity.class));
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }


    private void login(boolean shouldByPassSSLSecurity) {
        if (!validate())
            return;
        FinfluxApplication.baseApiManager = new BaseApiManager();
        PrefManager.canUseDefaultCertificate(shouldByPassSSLSecurity);
        String instanceUrl = PrefManager.getInstanceUrl();
        Log.i(getClass().getSimpleName(), "the instance url in login method is " + instanceUrl);
        mLoginPresenter.login(instanceUrl, username, password);
    }

    public boolean validate() {
        boolean valid = true;

        username = _emailText.getText().toString().trim();
        password = _passwordText.getText().toString().trim();

        if (username.isEmpty()) {
            _emailText.setError(getString(R.string.no_username));
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty()) {
            _passwordText.setError(getString(R.string.no_password));
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    @Override
    public void onLoginSuccessful(final User user) {
        Logger.i(TAG, "Login Successful  " + user.getUsername());
        writeUserDetailsToTable(user);
        setPreferenceAuthenticationStatus(user.isAuthenticated());
        PrefManager.saveToken("Basic "+user.getBase64EncodedAuthenticationKey());
        ApplicationAnalytics.sendLoginStatus(true,username, LoginConstants.SUCCESSFUL);
        loadDashBoard();
    }

    private void setPreferenceAuthenticationStatus(boolean isAuthenticated) {
        PrefManager.setAuthenticatedUserStatus(isAuthenticated);
    }

    public void loadDashBoard() {
        Intent intent = new Intent(LoginActivity.this, DashBoardActivity.class);
        Logger.d(TAG, "Authenticated user -> initializing start Dashboard activity");
        startActivity(intent);
        finish();
    }

    private void writeUserDetailsToTable(final User user) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    LoginUser loginUser = new LoginUser();
                    Logger.d(TAG, "The id is " + RealmAutoIncrement.getInstance(realm).getNextId(LoginUser.class));
                    loginUser.setId(RealmAutoIncrement.getInstance(realm).getNextId(LoginUser.class));
                    loginUser.setUsername(user.getUsername());
                    loginUser.setUserId(user.getUserId());
                    loginUser.setAuthenticated(user.isAuthenticated());
                    loginUser.setBase64EncodedAuthenticationKey(user.getBase64EncodedAuthenticationKey());
                    loginUser.setOfficeId(user.getOfficeId());
                    if (user.getStaffId() != null) {
                        loginUser.setStaffid(user.getStaffId());
                    }
                    loginUser.setOfficeName(user.getOfficeName());
                    loginUser.setAuthenticated(user.isAuthenticated());
                    if (loginUser.getPermissions() == null) {
                        RealmList<LoginUserPermission> realmStringsPermissions = new RealmList<LoginUserPermission>();

                        loginUser.setPermissions(realmStringsPermissions);
                    }
                        //create the object of realm string and add each permissions to this list
                        for (String permission : user.getPermissions()) {
                            LoginUserPermission permissionString = realm.createObject(LoginUserPermission.class);
                            permissionString.setValue(permission);
                            permissionString.setFkLoginUserUserId(user.getUserId());
                            loginUser.getPermissions().add(permissionString);
                        }
                    if (loginUser.getRoles() == null) {
                        RealmList<LoginUserRole> realmStringsRoles = new RealmList<LoginUserRole>();
                        loginUser.setRoles(realmStringsRoles);
                    }

                        for (Role role : user.getRoles()) {
                            try {
                                LoginUserRole loginUserRole = realm.createObject(LoginUserRole.class);
                                loginUserRole.setName(role.getName());
                                loginUserRole.setFkLoginUserUserId(user.getUserId());
                                loginUserRole.setDescription(role.getDescription());
                                loginUser.getRoles().add(loginUserRole);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    realm.copyToRealm(loginUser);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onLoginError(Throwable throwable) {
        Logger.e(TAG, "Login failure " + throwable.getMessage() + " clasue class " + throwable.getCause());
        try {
            ApplicationAnalytics.sendLoginStatus(false,username,throwable.getMessage().substring(0,99));
            if (throwable.getCause() instanceof SSLHandshakeException || throwable.getCause() instanceof CertificateException || throwable instanceof SSLPeerUnverifiedException || throwable.getCause() instanceof HostnameVerifier) {
                login(true);
            } else
                Toaster.show(findViewById(android.R.id.content), throwable.getMessage());

        } catch (NullPointerException e) {
            if (Network.getConnectivityStatusString(this).equals("Not connected to " +
                    "Internet")) {
                Toaster.show(findViewById(android.R.id.content), "Not connected to Network");
            } else {
                Toaster.show(findViewById(android.R.id.content), getString(R.string.error_unknown));
            }
        }
    }

    @Override
    public void showProgressbar(boolean b) {
        Logger.d(TAG, "show progress bar" + b);
        if (b) {
            showProgress("Logging In");
        } else {
            hideProgress();
        }
    }

    public void showProgress(String message) {
        if (progress == null) {
            progress = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
            progress.setCancelable(false);
        }
        progress.setMessage(message);
        progress.show();
    }

    public void hideProgress() {
        if (progress != null && progress.isShowing())
            progress.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
        mLoginPresenter.detachView();
    }
}
