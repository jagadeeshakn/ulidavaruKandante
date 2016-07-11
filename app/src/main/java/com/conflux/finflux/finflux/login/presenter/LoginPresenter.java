package com.conflux.finflux.finflux.login.presenter;

import com.conflux.finflux.finflux.base.Presenter;
import com.conflux.finflux.finflux.infrastructure.api.manager.BaseApiManager;
import com.conflux.finflux.finflux.infrastructure.api.manager.Data;
import com.conflux.finflux.finflux.login.data.User;
import com.conflux.finflux.finflux.login.viewservices.LoginMvpView;
import com.conflux.finflux.finflux.util.Logger;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Praveen J U on 7/2/2016.
 */
public class LoginPresenter implements Presenter<LoginMvpView> {
    private final Data mDataManager;
    private Subscription mSubscription;
    private LoginMvpView mLoginMvpView;

    @Inject
    public LoginPresenter(Data dataManager) {
        mDataManager = dataManager;
    }


    public void login(String instanceURL, String username, String password) {
        mDataManager.mBaseApiManager.updateEndPoint(instanceURL);
        mLoginMvpView.showProgressbar(true);
        Logger.d(getClass().getSimpleName(),"Start the Authenticating User");
        mSubscription = mDataManager.login(username, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {
                        mLoginMvpView.showProgressbar(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginMvpView.showProgressbar(false);
                        mLoginMvpView.onLoginError(e);
                    }

                    @Override
                    public void onNext(User user) {
                        mLoginMvpView.showProgressbar(false);
                        mLoginMvpView.onLoginSuccessful(user);
                    }
                });
    }



    @Override
    public void attachView(LoginMvpView mvpView) {
        mLoginMvpView =  mvpView;
    }

    @Override
    public void detachView() {
        mLoginMvpView = null;
        if (mSubscription != null) mSubscription.unsubscribe();
    }
}
