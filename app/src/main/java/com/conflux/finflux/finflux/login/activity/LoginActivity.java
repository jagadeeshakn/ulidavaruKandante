package com.conflux.finflux.finflux.login.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.conflux.finflux.finflux.R;
import com.conflux.finflux.finflux.dashboard.activity.DashBoardActivity;
import com.conflux.finflux.finflux.db.LoginUser;
import com.conflux.finflux.finflux.db.LoginUserRole;
import com.conflux.finflux.finflux.infrastructure.FinfluxApplication;
import com.conflux.finflux.finflux.infrastructure.api.manager.BaseApiManager;
import com.conflux.finflux.finflux.infrastructure.api.manager.Data;
import com.conflux.finflux.finflux.login.data.Role;
import com.conflux.finflux.finflux.login.data.User;
import com.conflux.finflux.finflux.login.presenter.LoginPresenter;
import com.conflux.finflux.finflux.login.viewservices.LoginMvpView;
import com.conflux.finflux.finflux.util.Logger;
import com.conflux.finflux.finflux.util.PrefManager;
import com.conflux.finflux.finflux.util.RealmAutoIncrement;
import com.conflux.finflux.finflux.util.RealmString;
import com.conflux.finflux.finflux.util.Toaster;

import javax.inject.Inject;
import javax.net.ssl.SSLHandshakeException;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmList;

public class LoginActivity extends AppCompatActivity implements LoginMvpView {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @Bind(R.id.input_username)
    EditText _emailText;
    @Bind(R.id.input_password)
    EditText _passwordText;
    @Bind(R.id.btn_login)
    Button _loginButton;

    String username;
    String password;
    @Inject
    Data mDataManager;
    @Inject
    LoginPresenter mLoginPresenter;
    private ProgressDialog progress;
    private Realm realm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        Logger.d(TAG, "The loginviewer is " + mLoginPresenter);
        if (mLoginPresenter == null) {
            mLoginPresenter = new LoginPresenter(mDataManager);
        }
        mLoginPresenter.attachView(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login(true);
            }
        });
    }

    public void login() {
        Logger.d(TAG, "Login");

        if (!validate()) {

            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String userName = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
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
        loadDashBoard();

    }

    public void loadDashBoard() {
        Intent intent = new Intent(LoginActivity.this, DashBoardActivity.class);
        Logger.d(TAG, "on dashboard activity is calling");
        startActivity(intent);
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
                    loginUser.setBase64EncodedAuthenticationKey(user.getBase64EncodedAuthenticationKey());
                    loginUser.setOfficeId(user.getOfficeId());
                    loginUser.setOfficeName(user.getOfficeName());
                    loginUser.setAuthenticated(user.isAuthenticated());
                    if (loginUser.getPermissions() == null) {
                        RealmList<RealmString> realmStringsPermissions = new RealmList<RealmString>();
                        loginUser.setPermissions(realmStringsPermissions);
                        //create the object of realm string and add each permissions to this list
                        for (String permission : user.getPermissions()) {
                            RealmString permissionString = new RealmString();
                            permissionString.setValue(permission);
                            permissionString.setFkLoginUserUserId(user.getUserId());
                            loginUser.getPermissions().add(permissionString);
                        }
                    }
                    if (loginUser.getRoles() == null) {
                        RealmList<LoginUserRole> realmStringsRoles = new RealmList<LoginUserRole>();
                        loginUser.setRoles(realmStringsRoles);
                        for (Role role : user.getRoles()) {
                            try {
                                LoginUserRole loginUserRole = new LoginUserRole();
                                loginUserRole.setName(role.getName());
                                loginUserRole.setFkLoginUserUserId(user.getUserId());
                                loginUserRole.setDescription(role.getDescription());
                                loginUser.getRoles().add(loginUserRole);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
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
        Logger.e(TAG, "Login failure " + throwable.getMessage());
        try {
            if (throwable.getCause() instanceof SSLHandshakeException) {
                login(true);
            } else
                Toaster.show(findViewById(android.R.id.content), throwable.getMessage());

        } catch (NullPointerException e) {

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
