package com.conflux.finflux.injection.component;

import com.conflux.finflux.collectionSheet.activity.CollectionSheetActivity;
import com.conflux.finflux.collectionSheet.fragment.CollectionSheetCenterList;
import com.conflux.finflux.collectionSheet.fragment.CollectionSheetGroupList;
import com.conflux.finflux.injection.module.ActivityModule;
import com.conflux.finflux.injection.module.PerActivity;
import com.conflux.finflux.login.activity.LoginActivity;
import com.conflux.finflux.offline.activity.OfflineManagement;

import dagger.Component;

/**
 * Created by jagadeeshakn on 7/8/2016.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(LoginActivity loginActivity);
    void inject(CollectionSheetActivity collectionSheetActivity);
    void inject(CollectionSheetCenterList collectionSheetCenterList);
    void inject(CollectionSheetGroupList collectionSheetGroupList);
    void inject(OfflineManagement offlineManagement);
}
