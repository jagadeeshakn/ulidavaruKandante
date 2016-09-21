package com.conflux.finflux.createClient.services;

import com.conflux.finflux.collectionSheet.data.ProductiveCollectionData;
import com.conflux.finflux.createClient.data.ClientAddressTemplate;
import com.conflux.finflux.createClient.data.ClientPayloadData;
import com.conflux.finflux.createClient.data.ClientResponse;
import com.conflux.finflux.createClient.data.ClientTemplate;
import com.conflux.finflux.infrastructure.api.constants.APIEndPoint;

import java.util.ArrayList;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jagadeeshakn on 8/8/2016.
 */
public interface CreateClientServices {

    public static final String ACCEPT_JSON = "Accept: application/json";

    public static final String CONTENT_TYPE_JSON = "Content-Type: application/json";

    @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
    @GET(APIEndPoint.CLIENTS + "/template")
    Observable<ClientTemplate> getClientTemplate();

    @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
    @GET(APIEndPoint.CLIENTS+"/addresses/template")
    Observable<ClientAddressTemplate> getAddressTemplate();

    @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
    @POST(APIEndPoint.CLIENTS)
    Observable<ClientResponse> createClient(@Body ClientPayloadData payloadData);

}
