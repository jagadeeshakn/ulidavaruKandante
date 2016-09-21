package com.conflux.finflux.infrastructure.api.manager;

import com.conflux.finflux.collectionSheet.services.CollectionSheetServices;
import com.conflux.finflux.createClient.services.CreateClientServices;
import com.conflux.finflux.infrastructure.JsonDateSerializer;
import com.conflux.finflux.infrastructure.api.BaseUrl;
import com.conflux.finflux.login.services.AuthService;
import com.conflux.finflux.util.Logger;
import com.conflux.finflux.util.PrefManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Praveen J U on 6/29/2016.
 */
public class BaseApiManager {
    private final String TAG = getClass().getSimpleName();
    private static Retrofit mRetrofit;
    private static BaseUrl baseUrl = new BaseUrl();
    private static AuthService authApi;
    private static CollectionSheetServices productiveApi;
    private static CollectionSheetServices collectionApi;
    private static CreateClientServices clientsApi;
    private String BASE_URL;
    private boolean shouldByPassSSLCerti = false;

    public BaseApiManager() {
        createService();
    }

    public void setShouldByPassSSL(boolean shouldByPassSSL) {
        shouldByPassSSLCerti = shouldByPassSSL;
        createService();
    }


    public void updateEndPoint(String endpoint) {
        baseUrl.updateInstanceUrl(endpoint);
        BASE_URL = baseUrl.getUrl();
        createService();
    }

    public static void setApi() {
        createAuthApi();
        productiveApi = createApi(CollectionSheetServices.class);
        collectionApi = createApi(CollectionSheetServices.class);
        clientsApi = createApi(CreateClientServices.class);
    }

    //retrofit builder
    private static <T> T createApi(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

    public static void createService() {

        Gson gson = new GsonBuilder().disableHtmlEscaping()
                .registerTypeAdapter(Date.class, new JsonDateSerializer()).create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(PrefManager.getInstanceUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new FinfluxOkHttpClient().getMifosOkHttpClient())
                .build();
        setApi();
    }

    private static void createAuthApi() {

        authApi = new Retrofit.Builder()
                .baseUrl(baseUrl.getUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new FinfluxOkHttpClient().getMifosOkHttpClient())
                .build()
                .create(AuthService.class);
    }


    public AuthService getAuthApi() {
        return authApi;
    }

    public CollectionSheetServices getProductiveApi() {
        return productiveApi;
    }

    public CollectionSheetServices getCollectionApi() {
        return collectionApi;
    }

    public  CreateClientServices getClientsApi(){
        return clientsApi;
    }

}
