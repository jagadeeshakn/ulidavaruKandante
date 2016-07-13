package com.conflux.finflux.login.viewservices;

import com.conflux.finflux.base.MvpView;
import com.conflux.finflux.login.data.User;

public interface LoginMvpView extends MvpView {

    void onLoginSuccessful(User user);

    void onLoginError(Throwable throwable);

}