package com.conflux.finflux.finflux.infrastructure.api.manager;

import com.conflux.finflux.finflux.infrastructure.api.interceptor.ApiRequestInterceptor;
import com.conflux.finflux.finflux.util.Logger;
import com.conflux.finflux.finflux.util.PrefManager;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Praveen J U on 7/4/2016.
 */
public class FinfluxOkHttpClient {

    public OkHttpClient getMifosOkHttpClient() {
        boolean shouldByPassSSl = PrefManager.isSetUseDefaultCertificate();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Logger.d(getClass().getSimpleName(),"can bypass ssl? "+shouldByPassSSl);
        try {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(
                                X509Certificate[] chain,
                                String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(
                                X509Certificate[] chain,
                                String authType) throws CertificateException {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
            };

            // Install the all-trusting trust manager
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            //Enable Full Body Logging
            HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
            logger.setLevel(HttpLoggingInterceptor.Level.BODY);

            //Set SSL certificate to OkHttpClient Builder
            if(shouldByPassSSl) {
                builder.sslSocketFactory(sslSocketFactory);

                builder.hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Enable Full Body Logging
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);

        //Interceptor :> Full Body Logger and ApiRequest Header
        builder.addInterceptor(logger);
        builder.addInterceptor(new ApiRequestInterceptor());

        return builder.build();

    }
}
