package com.conflux.finflux.finflux.infrastructure.api.manager;

import android.widget.BaseAdapter;

import com.conflux.finflux.finflux.collectionSheet.services.CollectionSheetServices;
import com.conflux.finflux.finflux.infrastructure.JsonDateSerializer;
import com.conflux.finflux.finflux.infrastructure.api.BaseUrl;
import com.conflux.finflux.finflux.infrastructure.api.interceptor.ApiRequestInterceptor;
import com.conflux.finflux.finflux.login.services.AuthService;
import com.conflux.finflux.finflux.util.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.cert.CertificateException;
import java.util.Date;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Praveen J U on 6/29/2016.
 */
public class BaseApiManager {
    private final String TAG = getClass().getSimpleName();
    private BaseUrl baseUrl = new BaseUrl();
    private AuthService authApi;
    private CollectionSheetServices productiveApi;
    private String BASE_URL;
    private boolean shouldByPassSSLCerti = false;

    public BaseApiManager(){
        setApi();
    }

    public void setShouldByPassSSL(boolean shouldByPassSSL){
        shouldByPassSSLCerti = shouldByPassSSL;
        setApi();
    }


    public  void updateEndPoint(String endpoint){
        baseUrl.updateInstanceUrl(endpoint);
        BASE_URL = baseUrl.getUrl();
        setApi();
    }

    public void setApi(){
        createAuthApi();
        Logger.d(TAG,"The Url is after set up"+baseUrl.getUrl());
        productiveApi=createApi(CollectionSheetServices.class,baseUrl.getUrl());
    }


    public AuthService getAuthApi() {
        return authApi;
    }

    public CollectionSheetServices getProductiveApi() {
        return productiveApi;
    }

    //retrofit buider
    private <T> T createApi(Class<T> clazz, final String url) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new JsonDateSerializer()).create();
        Logger.d(getClass().getSimpleName(), "the Url is " + baseUrl.getUrl());
        return new Retrofit.Builder()
                .baseUrl(baseUrl.getUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new FinfluxOkHttpClient().getMifosOkHttpClient())
                .build()
                .create(clazz);
    }

    private void createAuthApi() {

        authApi = new Retrofit.Builder()
                .baseUrl(baseUrl.getUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new FinfluxOkHttpClient().getMifosOkHttpClient())
                .build()
                .create(AuthService.class);
    }




}
