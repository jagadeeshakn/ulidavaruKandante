package com.conflux.finflux.infrastructure.api.interceptor;

import com.conflux.finflux.util.PrefManager;

import java.io.IOException;


import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;

/**
 * Created by Praveen J U on 6/29/2016.
 */
public class ApiRequestInterceptor implements Interceptor {
    public static final String HEADER_TENANT = "Fineract-Platform-TenantId";
    public static final String HEADER_AUTH = "Authorization";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    public ApiRequestInterceptor(){

    }
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();

        Request.Builder request = original.newBuilder()
                .addHeader(HEADER_TENANT, PrefManager.getTenant());

        if(PrefManager.isAuthenticated()){
            request.addHeader(HEADER_AUTH,PrefManager.getToken());
        }
        return chain.proceed(request.build());
    }


    public OkHttpClient getChain() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder request = original.newBuilder()
                        .header(HEADER_TENANT, PrefManager.getTenant())
                        .header(CONTENT_TYPE, APPLICATION_JSON)
                        .method(original.method(), original.body());
                if(PrefManager.isAuthenticated())
                    request.header(HEADER_AUTH, PrefManager.getToken());
                return chain.proceed(request.build());
            }
        });
        return httpClient.build();
    }

}
