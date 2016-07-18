package com.conflux.finflux.collectionSheet.services;

import com.conflux.finflux.collectionSheet.data.CollectionSheetData;
import com.conflux.finflux.collectionSheet.data.Payload;
import com.conflux.finflux.collectionSheet.data.ProductiveCollectionData;
import com.conflux.finflux.infrastructure.api.constants.APIEndPoint;
import com.conflux.finflux.util.PrefManager;

import java.util.ArrayList;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jagadeeshakn on 7/12/2016.
 */
public interface CollectionSheetServices {
    public static final String ACCEPT_JSON = "Accept: application/json";

    public static final String CONTENT_TYPE_JSON = "Content-Type: application/json";

    @Headers({ACCEPT_JSON,CONTENT_TYPE_JSON})
    @GET(APIEndPoint.CENTERS)
    Observable<ArrayList<ProductiveCollectionData>> getProductiveSheet(@Query("dateFormat") String dateFormat,@Query("locale") String locale,@Query("meetingDate")String meetingdate,@Query("officeId") Long officeId,@Query("staffId") Long staffId);

    @Headers({ACCEPT_JSON,CONTENT_TYPE_JSON})
    @POST(APIEndPoint.CENTERS+ "/{centerId}?command=generateCollectionSheet")
    Observable<CollectionSheetData> getCollectionSheet(@Path("centerId") long centerId, @Body Payload payload);

}
