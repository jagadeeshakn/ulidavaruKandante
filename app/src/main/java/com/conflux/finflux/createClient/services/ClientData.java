package com.conflux.finflux.createClient.services;

import com.conflux.finflux.collectionSheet.data.CollectionSheetData;
import com.conflux.finflux.collectionSheet.data.Payload;
import com.conflux.finflux.createClient.data.ClientTemplate;
import com.conflux.finflux.infrastructure.api.manager.BaseApiManager;
import com.conflux.finflux.util.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by jagadeeshakn on 8/16/2016.
 */

public class ClientData {

    public final BaseApiManager mBaseApiManager;

    @Inject
    public ClientData(BaseApiManager baseApiManager) {
        mBaseApiManager = baseApiManager;
    }

    public Observable<ClientTemplate> getClientTemplate() {
        Logger.d(getClass().getSimpleName(), "client template has been fetched");
        return mBaseApiManager.getClientsApi().getClientTemplate();
    }


}
