package com.conflux.finflux.finflux.infrastructure;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.conflux.finflux.finflux.R;
import com.conflux.finflux.finflux.infrastructure.analytics.services.ApplicationAnalytics;
import com.conflux.finflux.finflux.infrastructure.analytics.data.FabricIoConstants;
import com.conflux.finflux.finflux.infrastructure.api.manager.BaseApiManager;
import com.conflux.finflux.finflux.util.Logger;
import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;
import com.leopard.api.Setup;
import com.orm.SugarApp;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.util.Set;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by praveen on 6/23/2016.
 */
public class FinfluxApplication extends Application {

    private static FinfluxApplication instance;
    public static BaseApiManager baseApiManager;
    private static Setup setup;
    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        instance = this;
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).name(getString(R.string.db_name)+".realm").build();
        Realm.setDefaultConfiguration(realmConfiguration);
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());


        try {
            setup = new Setup();
        }catch (Exception e){
            Logger.d(getClass().getSimpleName(), "error " + e.getMessage());
        }
        ApplicationAnalytics.sendApplicationLaunchedInformation(FabricIoConstants.APPLICATION_ACTIVE);
    }

    public static Setup getEvoluteSetUp(){
        return setup;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        ApplicationAnalytics.sendApplicationLaunchedInformation(FabricIoConstants.APPLICATION_TERMINATED);
    }

    public static FinfluxApplication getInstance(){
        return instance;
    }
}
