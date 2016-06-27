package com.conflux.finflux.finflux.infrastructure;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.orm.SugarApp;


/**
 * Created by praveen on 6/23/2016.
 */
public class FinfluxApplication extends SugarApp {

    private static FinfluxApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Stetho.initializeWithDefaults(this);
    }

    public static FinfluxApplication getInstance(){
        return instance;
    }
}
