package com.conflux.finflux.infrastructure.api.manager;


import com.conflux.finflux.collectionSheet.data.Payload;
import com.conflux.finflux.collectionSheet.data.ProductiveCollectionData;
import com.conflux.finflux.login.data.User;
import com.conflux.finflux.util.Logger;

import java.util.ArrayList;

import javax.inject.Inject;

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
        Logger.e(getClass().getSimpleName(), "Login api");
        return mBaseApiManager.getAuthApi().authenticate(username, password);
    }

    public Observable<ArrayList<ProductiveCollectionData>>getProductiveCollectionSheet(Payload payload){
        Logger.d(getClass().getSimpleName(),"productive collection sheet api");
        return mBaseApiManager.getProductiveApi().getProductiveSheet(payload.getDateFormat(),payload.getLocale(),payload.getMeetingDate(),payload.getOfficeId(),payload.getStaffId());
    }

}
