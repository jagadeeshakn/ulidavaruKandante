package com.conflux.finflux.finflux.infrastructure.api.interceptor;

import com.conflux.finflux.finflux.util.PrefManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Praveen J U on 6/29/2016.
 */
public class ApiRequestInterceptor implements Interceptor {
    public static final String HEADER_TENANT = "Fineract-Platform-TenantId";
    public static final String HEADER_AUTH = "Authorization";

    public ApiRequestInterceptor(){

    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request chianrequest = chain.request();
        Request.Builder builder = chianrequest.newBuilder()
                .header(HEADER_TENANT, PrefManager.getTenant());

        if (PrefManager.isAuthenticated())
            builder.header(HEADER_AUTH, PrefManager.getToken());

        Request request = builder.build();
        return chain.proceed(request);
    }
}
