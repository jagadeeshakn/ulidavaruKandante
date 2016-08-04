package com.conflux.finflux.infrastructure.api.manager;


import com.conflux.finflux.collectionSheet.data.CollectionSheetData;
import com.conflux.finflux.collectionSheet.data.CollectionSheetPayload;
import com.conflux.finflux.collectionSheet.data.Payload;
import com.conflux.finflux.collectionSheet.data.ProductiveCollectionData;
import com.conflux.finflux.login.data.User;
import com.conflux.finflux.util.Logger;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.http.Body;
import retrofit2.http.Path;
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
        Logger.d(getClass().getSimpleName(), "productive collection sheet api");
        return mBaseApiManager.getProductiveApi().getProductiveSheet(payload.getDateFormat(), payload.getLocale(), payload.getMeetingDate(), payload.getOfficeId(), payload.getStaffId());
    }

    public Observable<CollectionSheetData>getCenterCollectionSheet(Long centerId,Payload payload){
        Logger.d(getClass().getSimpleName(), "center collection sheet api");
        return mBaseApiManager.getCollectionApi().getCollectionSheet(centerId, payload);
    }

    public Observable<SaveResponse> saveCollectionSheetAsync(Long centerId, CollectionSheetPayload payload) {
        Logger.d(getClass().getSimpleName(), "save center collection sheet api");
        return mBaseApiManager.getCollectionApi().saveCollectionSheet(centerId, payload);
    }
}
