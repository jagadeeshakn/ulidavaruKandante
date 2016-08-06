package com.conflux.finflux.collectionSheet.presenter;

import com.conflux.finflux.base.BasePresenter;
import com.conflux.finflux.collectionSheet.data.CollectionSheetData;
import com.conflux.finflux.collectionSheet.data.CollectionSheetPayload;
import com.conflux.finflux.collectionSheet.data.Payload;
import com.conflux.finflux.collectionSheet.data.ProductiveCollectionData;
import com.conflux.finflux.collectionSheet.viewServices.CollectionSheetMvpView;
import com.conflux.finflux.infrastructure.api.manager.Data;
import com.conflux.finflux.infrastructure.api.manager.SaveResponse;
import com.conflux.finflux.util.Logger;
import com.conflux.finflux.util.PrefManager;
import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jagadeeshakn on 7/8/2016.
 */
public class CollectionSheetPresenter extends BasePresenter<CollectionSheetMvpView> {

    private final Data mDataManager;
    private Subscription mSubscription;
    private CollectionSheetMvpView mCollectionMvpView;

    @Inject
    public CollectionSheetPresenter(Data dataManager) {
        mDataManager = dataManager;
    }

    public void loadProductiveCollectionSheet(Payload payload) {
        checkViewAttached();
        String instanceUrl = PrefManager.getInstanceUrl();
        Logger.d(getClass().getSimpleName(), "The url is " + instanceUrl);
        mDataManager.mBaseApiManager.updateEndPoint(instanceUrl);
        if (mSubscription != null) mSubscription.unsubscribe();
        mCollectionMvpView.showProgressbar(true);
        mSubscription = mDataManager.getProductiveCollectionSheet(payload)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ArrayList<ProductiveCollectionData>>() {
                    @Override
                    public void onCompleted() {
                        mCollectionMvpView.showProgressbar(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mCollectionMvpView.showProgressbar(false);
                        e.printStackTrace();
                        if (e instanceof HttpException) {
                            HttpException response = (HttpException) e;
                            mCollectionMvpView.showFetchingError(response);
                        }
                    }

                    @Override
                    public void onNext(ArrayList<ProductiveCollectionData> productiveCollectionDatas) {
                        Logger.d(getClass().getSimpleName(), "Successful");
                        mCollectionMvpView.showProductiveCollectionSheet(productiveCollectionDatas);

                    }
                });
    }

    public void loadCollectionsForGroup(Long centerId, Payload payload) {
        checkViewAttached();
        String instanceUrl = PrefManager.getInstanceUrl();
        mDataManager.mBaseApiManager.updateEndPoint(instanceUrl);
        if (mSubscription != null) mSubscription.unsubscribe();
        mCollectionMvpView.showProgressbar(true);
        mSubscription = mDataManager.getCenterCollectionSheet(centerId, payload)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<CollectionSheetData>() {
                    @Override
                    public void onCompleted() {
                        mCollectionMvpView.showProgressbar(false);
                    }

                    @Override
                    public void onError(Throwable e) {

                        mCollectionMvpView.showProgressbar(false);
                        e.printStackTrace();
                        if (e instanceof HttpException) {
                            HttpException response = (HttpException) e;
                            mCollectionMvpView.showFetchingError(response);
                        }
                    }

                    @Override
                    public void onNext(CollectionSheetData collectionSheetData) {
                        Logger.d(getClass().getSimpleName(), "Successful");
                        mCollectionMvpView.showCenterCollectionSheet(collectionSheetData);
                    }
                });

    }

    public void saveCollectionSheet(Long  centerId, CollectionSheetPayload payload) {
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
                    }
                });
    }

    @Override
    public void attachView(CollectionSheetMvpView mvpView) {
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
