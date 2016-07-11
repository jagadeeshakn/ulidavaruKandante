package com.conflux.finflux.finflux.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Praveen J U on 7/2/2016.
 */
public class LoginUserPermission extends RealmObject {

    private String value;
    private long fkLoginUserUserId;

    public long getFkLoginUserUserId() {
        return fkLoginUserUserId;
    }

    public void setFkLoginUserUserId(long fkLoginUserUserId) {
        this.fkLoginUserUserId = fkLoginUserUserId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
