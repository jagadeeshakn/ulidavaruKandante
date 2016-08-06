package com.conflux.finflux.collectionSheet.viewServices;

import com.conflux.finflux.base.MvpView;
import com.conflux.finflux.infrastructure.api.manager.SaveResponse;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by jagadeeshakn on 8/1/2016.
 */
public interface CollectionSheetGroupMvpView extends MvpView {

    @Override
    void showProgressbar(boolean b);
    void showCollectionSheetSaved(SaveResponse saveResponse);
    void showFetchingError(HttpException response);
}
