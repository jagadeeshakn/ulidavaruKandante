package com.conflux.finflux.finflux.db;

import com.orm.SugarRecord;
import com.orm.query.Select;

import java.security.PublicKey;


/**
 * Created by praveen on 6/23/2016.
 */
public class Activation extends SugarRecord {

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



    public static boolean isNew(){
        //one app can have only one activation key so no string is passed to this just check for count
        long count = Select.from(Activation.class).count();
        if(count == 0){
            return  true;
        }else {
            return false;
        }
    }


    public static boolean hasActivated(String TAG){
        Activation activation = Select.from(Activation.class).first();
        if(activation != null){
            return true;
        }
        else return false;
    }
}
