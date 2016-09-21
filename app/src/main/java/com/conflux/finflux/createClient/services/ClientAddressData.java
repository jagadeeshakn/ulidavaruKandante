package com.conflux.finflux.createClient.services;

import com.conflux.finflux.createClient.data.ClientAddressTemplate;
import com.conflux.finflux.createClient.data.ClientPayloadData;
import com.conflux.finflux.createClient.data.ClientResponse;
import com.conflux.finflux.createClient.data.ClientTemplate;
import com.conflux.finflux.infrastructure.api.manager.BaseApiManager;
import com.conflux.finflux.util.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by jagadeeshakn on 8/22/2016.
 */


public class ClientAddressData {

    public final BaseApiManager mBaseApiManager;

    @Inject
    public ClientAddressData(BaseApiManager baseApiManager) {
        mBaseApiManager = baseApiManager;
    }

    public Observable<ClientAddressTemplate> getAddressTemplate() {
        Logger.d(getClass().getSimpleName(), "client template has been fetched");
        return mBaseApiManager.getClientsApi().getAddressTemplate();
    }
    public Observable<ClientResponse> loadCreateClient(final ClientPayloadData payloadData) {
        Logger.d(getClass().getSimpleName(), "client creation has been intiated");
        return mBaseApiManager.getClientsApi().createClient(payloadData);
    }

}
