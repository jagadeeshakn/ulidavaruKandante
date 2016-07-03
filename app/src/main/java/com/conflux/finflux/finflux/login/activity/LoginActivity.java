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
import com.conflux.finflux.finflux.infrastructure.FinfluxApplication;
import com.conflux.finflux.finflux.infrastructure.api.manager.BaseApiManager;
import com.conflux.finflux.finflux.infrastructure.api.manager.Data;
import com.conflux.finflux.finflux.login.data.User;
import com.conflux.finflux.finflux.login.presenter.LoginPresenter;
import com.conflux.finflux.finflux.login.viewservices.LoginMvpView;
import com.conflux.finflux.finflux.util.Logger;
import com.conflux.finflux.finflux.util.PrefManager;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;

public class LoginActivity extends AppCompatActivity implements LoginMvpView {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @Bind(R.id.input_username)
    EditText _emailText;
    @Bind(R.id.input_password)
    EditText _passwordText;
    @Bind(R.id.btn_login)
    Button _loginButton;
    @Bind(R.id.link_signup)
    TextView _signupLink;

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
        if(mLoginPresenter == null)
        {
            mLoginPresenter =new LoginPresenter(mDataManager);
        }
        mLoginPresenter.attachView(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login(false);
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
               /* Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);*/
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

        String instanceUrl = PrefManager.getInstanceUrl();
        Log.i(getClass().getSimpleName(), "the instance url in login method is " + instanceUrl);
        mLoginPresenter.login(instanceUrl, username, password);
    }

    public boolean validate() {
        boolean valid = true;

      username = _emailText.getText().toString();
      password = _passwordText.getText().toString();

        if (username.isEmpty()) {
            _emailText.setError(getString(R.string.no_username));
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() ) {
            _passwordText.setError(getString(R.string.no_password));
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    @Override
    public void onLoginSuccessful(User user) {

        Logger.i(TAG,"Login Successful");

    }

    @Override
    public void onLoginError(Throwable throwable) {
        Logger.e(TAG,"Login failure"+throwable.getMessage());
    }

    @Override
    public void showProgressbar(boolean b) {
        Logger.d(TAG,"show progress bar"+b);
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
