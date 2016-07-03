package com.conflux.finflux.finflux.db;

import com.conflux.finflux.finflux.login.data.Role;
import com.conflux.finflux.finflux.util.RealmString;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Praveen J U on 7/2/2016.
 */
public class LoginUser extends RealmObject {
    public static final String AUTHENTICATION_KEY = "authenticationKey";

    private String username;
    private int userId;
    private String base64EncodedAuthenticationKey;
    private boolean authenticated;
    private int officeId;
    private String officeName;
    private RealmList<LoginUserRole> roles;
    private RealmList<RealmString> permissions;

    public static String getAuthenticationKey() {
        return AUTHENTICATION_KEY;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBase64EncodedAuthenticationKey() {
        return base64EncodedAuthenticationKey;
    }

    public void setBase64EncodedAuthenticationKey(String base64EncodedAuthenticationKey) {
        this.base64EncodedAuthenticationKey = base64EncodedAuthenticationKey;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public RealmList<LoginUserRole> getRoles() {
        return roles;
    }

    public void setRoles(RealmList<LoginUserRole> roles) {
        this.roles = roles;
    }

    public RealmList<RealmString> getPermissions() {
        return permissions;
    }

    public void setPermissions(RealmList<RealmString> permissions) {
        this.permissions = permissions;
    }
}
