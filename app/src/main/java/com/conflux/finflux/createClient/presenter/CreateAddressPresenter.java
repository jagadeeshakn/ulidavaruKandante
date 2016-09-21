package com.conflux.finflux.createClient.presenter;

import com.conflux.finflux.base.BasePresenter;
import com.conflux.finflux.createClient.data.ClientAddressTemplate;
import com.conflux.finflux.createClient.data.ClientPayloadData;
import com.conflux.finflux.createClient.data.ClientResponse;
import com.conflux.finflux.createClient.data.ClientTemplate;
import com.conflux.finflux.createClient.services.ClientAddressData;
import com.conflux.finflux.createClient.services.ClientData;
import com.conflux.finflux.createClient.viewServices.ClientAddressMvpView;
import com.conflux.finflux.util.Logger;
import com.conflux.finflux.util.PrefManager;
import com.conflux.finflux.util.management.FinResponseHandler;

import javax.inject.Inject;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jagadeeshakn on 8/22/2016.
 */
public class CreateAddressPresenter extends BasePresenter<ClientAddressMvpView> {

    private final ClientAddressData mDataManager;
    private Subscription mSubscription;
    private ClientAddressMvpView mCreateAddressMvpView;

    @Inject
    public CreateAddressPresenter(ClientAddressData dataManager) {
        mDataManager = dataManager;
    }

    public void loadAddressTemplate() {
        checkViewAttached();
        String instanceUrl = PrefManager.getInstanceUrl();
        mDataManager.mBaseApiManager.updateEndPoint(instanceUrl);
        if (mSubscription != null) mSubscription.unsubscribe();
        mCreateAddressMvpView.showProgressbar(true);
        mSubscription = mDataManager.getAddressTemplate()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ClientAddressTemplate>() {

                    @Override
                    public void onCompleted() {
                        mCreateAddressMvpView.showProgressbar(false);

                    }

                    @Override
                    public void onError(Throwable e) {
                        mCreateAddressMvpView.showProgressbar(false);
                        e.printStackTrace();
                        if (e instanceof HttpException) {
                            HttpException response = (HttpException) e;
                            mCreateAddressMvpView.showFetchingError(response);
                        }
                    }

                    @Override
                    public void onNext(ClientAddressTemplate addressTemplate) {
                        Logger.d(getClass().getSimpleName(), "On Successful client address template");
                        mCreateAddressMvpView.showClientAddressTemplate(addressTemplate);

                    }
                });
    }

    @Override
    public void attachView(ClientAddressMvpView mvpView) {
        super.attachView(mvpView);
        this.mCreateAddressMvpView = mvpView;
    }

    @Override
    public void detachView() {
        super.detachView();
        this.mCreateAddressMvpView = null;
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void createClient(ClientPayloadData clientPayloadData) {
        checkViewAttached();
        String instanceUrl = PrefManager.getInstanceUrl();
        mDataManager.mBaseApiManager.updateEndPoint(instanceUrl);
        if (mSubscription != null) mSubscription.unsubscribe();
        mCreateAddressMvpView.showProgressbar(true);
        mSubscription = mDataManager.loadCreateClient(clientPayloadData)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ClientResponse>() {

                    @Override
                    public void onCompleted() {
                        mCreateAddressMvpView.showProgressbar(false);

                    }

                    @Override
                    public void onError(Throwable e) {
                        mCreateAddressMvpView.showProgressbar(false);
                        e.printStackTrace();
                        if (e instanceof HttpException) {
                            HttpException response = (HttpException) e;
                            mCreateAddressMvpView.showFetchingError(response);
                        }
                    }

                    @Override
                    public void onNext(ClientResponse response) {
                        Logger.d(getClass().getSimpleName(), "On Successful client creation");
                        mCreateAddressMvpView.showClientCreatedSuccessfully(response, "client" + FinResponseHandler.getResponse());
                    }
                });


    }
}
