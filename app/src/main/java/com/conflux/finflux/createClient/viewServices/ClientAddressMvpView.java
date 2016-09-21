package com.conflux.finflux.createClient.viewServices;

import com.conflux.finflux.base.MvpView;
import com.conflux.finflux.createClient.data.ClientAddressTemplate;
import com.conflux.finflux.createClient.data.ClientResponse;
import com.conflux.finflux.createClient.data.ClientTemplate;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by jagadeeshakn on 8/21/2016.
 */
public interface ClientAddressMvpView extends MvpView {

    void showProgressbar(boolean b);
    void showFetchingError(HttpException response);
    void showClientAddressTemplate(ClientAddressTemplate clientAddressTemplate);
    void showClientCreatedSuccessfully(ClientResponse client, String s);

}
