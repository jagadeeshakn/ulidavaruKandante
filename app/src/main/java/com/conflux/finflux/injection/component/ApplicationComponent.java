package com.conflux.finflux.injection.component;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.conflux.finflux.createClient.services.ClientAddressData;
import com.conflux.finflux.createClient.services.ClientData;
import com.conflux.finflux.infrastructure.api.manager.Data;
import com.conflux.finflux.injection.module.ApplicationContext;
import com.conflux.finflux.injection.module.ApplicationModule;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jagadeeshakn on 7/8/2016.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();
    Data mDataManager();
    ClientData mclientDataManager();
    ClientAddressData mclientAddressData();

    Bus eventBus();
}
