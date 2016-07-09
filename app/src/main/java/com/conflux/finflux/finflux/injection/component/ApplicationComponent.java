package com.conflux.finflux.finflux.injection.component;

import android.app.Application;
import android.content.Context;

import com.conflux.finflux.finflux.injection.module.ApplicationContext;
import com.conflux.finflux.finflux.injection.module.ApplicationModule;
import com.conflux.finflux.finflux.injection.module.PerActivity;
import com.squareup.otto.Bus;

import javax.inject.Scope;
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

    Bus eventBus();
}
