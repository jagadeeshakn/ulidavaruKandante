package com.conflux.finflux.infrastructure;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.conflux.finflux.R;
import com.conflux.finflux.infrastructure.analytics.services.ApplicationAnalytics;
import com.conflux.finflux.infrastructure.analytics.data.FabricIoConstants;
import com.conflux.finflux.infrastructure.api.manager.BaseApiManager;
import com.conflux.finflux.injection.component.ApplicationComponent;

import com.conflux.finflux.injection.component.DaggerApplicationComponent;
import com.conflux.finflux.injection.module.ApplicationModule;
import com.conflux.finflux.util.Logger;
import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;
import com.leopard.api.Setup;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

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
    private ApplicationComponent mApplicationComponent;

    public static FinfluxApplication get(Context context) {
        return (FinfluxApplication) context.getApplicationContext();
    }

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
            boolean result = false;
            result = setup.blActivateLibrary(this,R.raw.licence_full);
            if(result){
                Logger.d(getClass().getSimpleName(),"library status Active");
            }else {
                Logger.d(getClass().getSimpleName(),"library status Inactive");
            }
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

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent =
                    DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }
}
