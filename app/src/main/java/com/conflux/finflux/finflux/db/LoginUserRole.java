package com.conflux.finflux.finflux.db;

import com.conflux.finflux.finflux.util.RealmAutoIncrement;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Praveen J U on 7/2/2016.
 */
public class LoginUserRole extends RealmObject {
    /*@PrimaryKey
    private long id;*/
    private String name;
    private String description;
    private long fkLoginUserUserId;


   /* public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }*/

    public long getFkLoginUserUserId() {
        return fkLoginUserUserId;
    }

    public void setFkLoginUserUserId(long fkLoginUserUserId) {
        this.fkLoginUserUserId = fkLoginUserUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
