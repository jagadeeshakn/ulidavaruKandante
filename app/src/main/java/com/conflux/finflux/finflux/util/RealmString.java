package com.conflux.finflux.finflux.util;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Praveen J U on 7/2/2016.
 */
public class RealmString extends RealmObject {
    /*@PrimaryKey
    private long id;*/
    private String value;
    private long fkLoginUserUserId;

    public long getFkLoginUserUserId() {
        return fkLoginUserUserId;
    }

    public void setFkLoginUserUserId(long fkLoginUserUserId) {
        this.fkLoginUserUserId = fkLoginUserUserId;
    }

    /*public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }*/

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
