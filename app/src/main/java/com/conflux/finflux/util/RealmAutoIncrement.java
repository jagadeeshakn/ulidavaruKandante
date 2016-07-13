package com.conflux.finflux.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by Praveen J U on 7/3/2016.
 */
public class RealmAutoIncrement {
    private Realm realm;
    private Map<Class<? extends RealmObject>, AtomicInteger> modelMap = new HashMap<>();
    private static RealmAutoIncrement autoIncrementMap;

    private RealmAutoIncrement(Realm realm) {
        this.realm = realm;
    }


    /**
     * Utility method which query for all models saved and get the bigger model id saved
     * Used to guarantee which the last model id saved is really the last
     *
     * @param clazz Model which should get the last id
     * @return The last id saved from model passed
     */
    private long getLastIdFromModel(Class<? extends RealmObject> clazz) {

        String primaryKeyColumnName = "id";
        Number lastId = realm.where(clazz).max(primaryKeyColumnName);

        return lastId == null ? 0 : lastId.longValue();
    }

    public long getNextId(Class<? extends RealmObject> clazz){
        long nextId = getLastIdFromModel(clazz) + 1;
        Logger.i(clazz.getSimpleName(),"The new Id is "+nextId);
        return nextId;
    }

    /**
     * Search in modelMap for the last saved id from model passed and return the next one
     *
     * @param clazz Model to search the last id
     * @return The next id which can be saved in database for that model,
     * {@code null} will be returned when this method is called by reflection
     */
    public Integer getNextIdFromModel(Class<? extends RealmObject> clazz) {


        if (isValidMethodCall()) {

            AtomicInteger modelId = modelMap.get(clazz);

            if (modelId == null) {
                return 0;
            }
            return modelId.incrementAndGet();
        }
        return null;
    }

    /**
     * Utility method to validate if the method is called from reflection,
     * in this case is considered a not valid call otherwise is a valid call
     *
     * @return The boolean which define if the method call is valid or not
     */
    private boolean isValidMethodCall() {

        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        for (StackTraceElement stackTraceElement : stackTraceElements) {

            if (stackTraceElement.getMethodName().equals("newInstance")) {
                return false;
            }
        }
        return true;
    }

    public static RealmAutoIncrement getInstance(Realm realm) {

        if (autoIncrementMap == null) {
            autoIncrementMap = new RealmAutoIncrement(realm);
        }

        return autoIncrementMap;
    }
}
