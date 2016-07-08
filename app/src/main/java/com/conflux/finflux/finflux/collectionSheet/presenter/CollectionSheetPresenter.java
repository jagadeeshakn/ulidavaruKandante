package com.conflux.finflux.finflux.collectionSheet.presenter;

import com.conflux.finflux.finflux.base.BasePresenter;
import com.conflux.finflux.finflux.collectionSheet.viewServices.CollectionSheetMvpView;
import com.conflux.finflux.finflux.infrastructure.api.manager.Data;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by jagadeeshakn on 7/8/2016.
 */
public class CollectionSheetPresenter extends BasePresenter<CollectionSheetMvpView> {

    private final Data mDataManager;
    private Subscription mSubscription;

    @Inject
    public CollectionSheetPresenter(Data dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(CollectionSheetMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }
}
