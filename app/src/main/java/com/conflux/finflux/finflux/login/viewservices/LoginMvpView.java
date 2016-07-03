package com.conflux.finflux.finflux.login.viewservices;

import com.conflux.finflux.finflux.infrastructure.viewservice.MvpView;
import com.conflux.finflux.finflux.login.data.User;

public interface LoginMvpView extends MvpView {

    void onLoginSuccessful(User user);

    void onLoginError(Throwable throwable);

}