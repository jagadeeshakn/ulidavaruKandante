package com.conflux.finflux.logout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.conflux.finflux.db.LoginUser;
import com.conflux.finflux.db.LoginUserRole;
import com.conflux.finflux.login.activity.LoginActivity;
import com.conflux.finflux.util.PrefManager;
import com.conflux.finflux.db.LoginUserPermission;

import io.realm.Realm;

/**
 * Created by Praveen J U on 7/5/2016.
 */
public class LogoutActivity extends AppCompatActivity {

    private Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
        logout();
    }

    private void logout() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.clear(LoginUser.class);
                realm.clear(LoginUserRole.class);
                realm.clear(LoginUserPermission.class);
            }
        });

        PrefManager.setAuthenticatedUserStatus(false);
        PrefManager.saveToken("");
        Intent in = new Intent(LogoutActivity.this, LoginActivity.class);
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(in);
        finish();
    }


}
