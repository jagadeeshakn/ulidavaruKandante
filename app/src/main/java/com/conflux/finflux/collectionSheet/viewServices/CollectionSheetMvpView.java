package com.conflux.finflux.collectionSheet.viewServices;

import com.conflux.finflux.base.MvpView;
import com.conflux.finflux.collectionSheet.data.ProductiveCollectionData;

import java.util.ArrayList;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by jagadeeshakn on 7/8/2016.
 */
public interface CollectionSheetMvpView extends MvpView {
    @Override
    void showProgressbar(boolean b);
    void showProductiveCollectionSheet(ArrayList<ProductiveCollectionData> productiveCollectionData);
    void showFetchingError(HttpException response);

}
