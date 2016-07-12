package com.conflux.finflux.finflux.collectionSheet.services;

import com.conflux.finflux.finflux.collectionSheet.data.ProductiveCollectionData;
import com.conflux.finflux.finflux.infrastructure.api.constants.APIEndPoint;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jagadeeshakn on 7/12/2016.
 */
public interface CollectionSheetServices {
    @GET(APIEndPoint.CENTERS)
    Observable<ArrayList<ProductiveCollectionData>> getProductiveSheet(@Query("dateFormat") String dateFormat,@Query("locale") String locale,@Query("meetingDate")String meetingdate,@Query("officeId") Long officeId,@Query("staffId") Long staffId);
}
