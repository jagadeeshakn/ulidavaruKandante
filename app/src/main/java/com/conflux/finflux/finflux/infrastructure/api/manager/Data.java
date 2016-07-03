package com.conflux.finflux.finflux.infrastructure.api.manager;

import com.conflux.finflux.finflux.login.data.User;
import com.conflux.finflux.finflux.util.Logger;

import javax.inject.Inject;
import javax.inject.Provider;

import rx.Observable;

/**
 * Created by Praveen J U on 7/2/2016.
 */

public class Data {
    public final BaseApiManager mBaseApiManager;

    @Inject
    public Data(BaseApiManager baseApiManager) {

        mBaseApiManager = baseApiManager;
    }

    public void updateDomainName(String domain){
        mBaseApiManager.updateEndPoint(domain);
    }

    public void shouldByPassSSl(boolean shouldByPassSSl){
        mBaseApiManager.setShouldByPassSSL(shouldByPassSSl);
    }

    /**
     * @param username Username
     * @param password Password
     * @return Basic OAuth
     */
    public Observable<User> login(String username, String password) {
        Logger.e(getClass().getSimpleName(),"Login api");
        return mBaseApiManager.getAuthApi().authenticate(username, password);
    }
}
