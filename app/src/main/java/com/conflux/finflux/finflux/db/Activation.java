package com.conflux.finflux.finflux.db;

import com.conflux.finflux.finflux.util.Logger;
import com.conflux.finflux.finflux.util.RealmHelperUtil;
import com.orm.SugarRecord;
import com.orm.query.Select;

import java.security.PublicKey;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Index;


/**
 * Created by praveen on 6/23/2016.
 */
public class Activation extends RealmObject {
    @PrimaryKey
    @Index
    private long id;

    private boolean isActivated;

    private String fromDate;

    private String toDate;

    private String activationKey;



    public void setIsActivated(boolean isActivated) {
        this.isActivated = isActivated;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public String getFromDate() {
        return fromDate;
    }


    public String getToDate() {
        return toDate;
    }

    public static boolean isNew(Realm realm){
        return RealmHelperUtil.isTheEntryNew(realm,Activation.class);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getActivationKey() {
        return activationKey;
    }



}
