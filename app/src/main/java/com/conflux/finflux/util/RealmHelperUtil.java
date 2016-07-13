package com.conflux.finflux.util;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by Praveen J U on 7/2/2016.
 */
public class RealmHelperUtil {
    public static void beginTransaction(Realm realm){
        realm.beginTransaction();
    }
    public static void commitTransaction(Realm realm){
        realm.commitTransaction();
    }

    public static boolean isTheEntryNew(Realm realm,Class<? extends RealmObject> clazz){
        Logger.d(clazz.getSimpleName(),"Checking for the old entries");
        long count = realm.where(clazz).count();
        Logger.e(clazz.getSimpleName(),"The count is "+count);
        if(count == 0){
            return true;
        }else {
            return false;
        }
    }
}
