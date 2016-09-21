package com.conflux.finflux.createClient.viewServices;

import com.conflux.finflux.base.MvpView;
import com.conflux.finflux.createClient.data.ClientTemplate;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by jagadeeshakn on 8/8/2016.
 */
public interface CreateClientMvpView extends MvpView {

    void showProgressbar(boolean b);
    void showFetchingError(HttpException response);
    void showClientTemplate(ClientTemplate clientTemplate);

}
