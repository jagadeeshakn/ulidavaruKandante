package com.conflux.finflux.finflux.injection.component;

import com.conflux.finflux.finflux.collectionSheet.activity.CollectionSheetActivity;
import com.conflux.finflux.finflux.injection.module.ActivityModule;
import com.conflux.finflux.finflux.injection.module.PerActivity;
import com.conflux.finflux.finflux.login.activity.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jagadeeshakn on 7/8/2016.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(CollectionSheetActivity collectionSheetActivity);
    void inject(LoginActivity loginActivity);
}
