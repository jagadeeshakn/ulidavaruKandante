package com.conflux.finflux.finflux.infrastructure.api.manager;

import android.widget.BaseAdapter;

import com.conflux.finflux.finflux.infrastructure.JsonDateSerializer;
import com.conflux.finflux.finflux.infrastructure.api.BaseUrl;
import com.conflux.finflux.finflux.infrastructure.api.interceptor.ApiRequestInterceptor;
import com.conflux.finflux.finflux.login.services.AuthService;
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

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Praveen J U on 6/29/2016.
 */
public class BaseApiManager {
    private BaseUrl baseUrl = new BaseUrl();
    private AuthService authApi;
    private static String BASE_URL;
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
        authApi = createApi(AuthService.class, BASE_URL );
    }

    private <T> T createApi(Class<T> clazz, String baseUrl) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new JsonDateSerializer()).create();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getOkHttpClient())
                .build()
                .create(clazz);
    }

    private OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);
        if(!shouldByPassSSLCerti) {
            return new OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .addInterceptor(new ApiRequestInterceptor())
                    .build();
        }else {
            return new OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .addInterceptor(new ApiRequestInterceptor())
                    .sslSocketFactory(getUnsafeOkHttpClient())
                    .build();
        }
    }

    private SSLSocketFactory getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            return sslSocketFactory;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
