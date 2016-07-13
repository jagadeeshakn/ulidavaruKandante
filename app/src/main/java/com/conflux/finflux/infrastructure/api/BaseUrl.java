package com.conflux.finflux.infrastructure.api;

import com.conflux.finflux.util.PrefManager;

/**
 * Created by Praveen J U on 6/29/2016.
 */
public class BaseUrl {
    public static final String PROTOCOL_HTTPS = "https://";
    public static final String API_ENDPOINT = "demo.openmf.org";
    public static final String API_PATH = "/fineract-provider/api/v1/";
    // "/" in the last of the base url always


    private String url;

    public void updateInstanceUrl(String instanceUrl) {
        this.url = instanceUrl;
    }


    public String getUrl() {
        return PrefManager.getInstanceUrl();
    }

}
