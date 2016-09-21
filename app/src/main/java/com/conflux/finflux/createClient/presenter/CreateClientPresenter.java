package com.conflux.finflux.createClient.presenter;

import com.conflux.finflux.base.BasePresenter;
import com.conflux.finflux.collectionSheet.viewServices.CollectionSheetMvpView;
import com.conflux.finflux.createClient.data.ClientTemplate;
import com.conflux.finflux.createClient.services.ClientData;
import com.conflux.finflux.createClient.viewServices.CreateClientMvpView;
import com.conflux.finflux.infrastructure.api.manager.Data;
import com.conflux.finflux.util.Logger;
import com.conflux.finflux.util.PrefManager;

import javax.inject.Inject;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jagadeeshakn on 8/8/2016.
 */
public class CreateClientPresenter extends BasePresenter<CreateClientMvpView> {

    private final ClientData mDataManager;
    private Subscription mSubscription;
    private CreateClientMvpView mCreateClientMvpView;

    @Inject
    public CreateClientPresenter(ClientData dataManager) {
        mDataManager = dataManager;
    }

    public void loadClientTemplate() {
        checkViewAttached();
        String instanceUrl = PrefManager.getInstanceUrl();
        Logger.d(getClass().getSimpleName(), "The client template url is " + instanceUrl);
        mDataManager.mBaseApiManager.updateEndPoint(instanceUrl);
        if (mSubscription != null) mSubscription.unsubscribe();
        mCreateClientMvpView.showProgressbar(true);
        mSubscription = mDataManager.getClientTemplate()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ClientTemplate>() {

                    @Override
                    public void onCompleted() {
                        mCreateClientMvpView.showProgressbar(false);

                    }

                    @Override
                    public void onError(Throwable e) {
                        mCreateClientMvpView.showProgressbar(false);
                        e.printStackTrace();
                        if (e instanceof HttpException) {
                            HttpException response = (HttpException) e;
                            mCreateClientMvpView.showFetchingError(response);
                        }
                    }

                    @Override
                    public void onNext(ClientTemplate clientTemplate) {
                        Logger.d(getClass().getSimpleName(), "On Successful client template");
                        mCreateClientMvpView.showClientTemplate(clientTemplate);

                    }
                });

    }

    @Override
    public void attachView(CreateClientMvpView mvpView) {
        super.attachView(mvpView);
        this.mCreateClientMvpView = mvpView;
    }

    @Override
    public void detachView() {
        super.detachView();
        this.mCreateClientMvpView = null;
        if (mSubscription != null) mSubscription.unsubscribe();
    }

}
