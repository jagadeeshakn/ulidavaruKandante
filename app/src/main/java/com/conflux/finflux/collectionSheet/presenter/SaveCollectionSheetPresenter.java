package com.conflux.finflux.collectionSheet.presenter;

import com.conflux.finflux.base.BasePresenter;
import com.conflux.finflux.collectionSheet.data.SaveCollectionSheetPayload;
import com.conflux.finflux.collectionSheet.viewServices.CollectionSheetGroupMvpView;
import com.conflux.finflux.collectionSheet.viewServices.CollectionSheetMvpView;
import com.conflux.finflux.infrastructure.api.manager.Data;
import com.conflux.finflux.infrastructure.api.manager.SaveResponse;
import com.conflux.finflux.util.Logger;
import com.conflux.finflux.util.PrefManager;

import javax.inject.Inject;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Praveen J U on 9/9/2016.
 */
public class SaveCollectionSheetPresenter extends BasePresenter<CollectionSheetGroupMvpView> {

    private final Data mDataManager;
    private Subscription mSubscription;
    private CollectionSheetGroupMvpView mCollectionMvpView;

    @Inject
    public SaveCollectionSheetPresenter(Data dataManager) {
        mDataManager = dataManager;
    }

    public void saveCollectionSheet(Long  centerId, SaveCollectionSheetPayload payload) {
        checkViewAttached();
        String instanceUrl = PrefManager.getInstanceUrl();
        mDataManager.mBaseApiManager.updateEndPoint(instanceUrl);
        if (mSubscription != null) mSubscription.unsubscribe();
        mCollectionMvpView.showProgressbar(true);
        mSubscription = mDataManager.saveCollectionSheetAsync(centerId, payload)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<SaveResponse>() {
                    @Override
                    public void onCompleted() {
                        mCollectionMvpView.showProgressbar(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mCollectionMvpView.showProgressbar(false);
                        if (e instanceof HttpException) {
                            HttpException response = (HttpException) e;
                            getMvpView().showFetchingError(response);
                        }
                    }

                    @Override
                    public void onNext(SaveResponse saveResponse) {
                        mCollectionMvpView.showProgressbar(false);
                        Logger.d(getClass().getSimpleName(),"the response is "+saveResponse);
                    }
                });
    }

    @Override
    public void attachView(CollectionSheetGroupMvpView mvpView) {
        super.attachView(mvpView);
        this.mCollectionMvpView = mvpView;
    }

    @Override
    public void detachView() {
        super.detachView();
        this.mCollectionMvpView = null;
        if (mSubscription != null) mSubscription.unsubscribe();
    }
}
