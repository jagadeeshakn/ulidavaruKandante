package com.conflux.finflux.finflux.injection.module;

import android.app.Activity;
import android.content.Context;

import com.conflux.finflux.finflux.util.event.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jagadeeshakn on 7/8/2016.
 */
@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @Singleton
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context provideContext(){ return mActivity;}

}
