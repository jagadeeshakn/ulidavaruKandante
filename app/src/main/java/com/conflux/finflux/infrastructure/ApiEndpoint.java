/*
 * This project is licensed under the open source MPL V2.
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */

package com.conflux.finflux.infrastructure;


/**
 * @author praveen
 */
public class ApiEndpoint {

    public static final String API_ENDPOINT = "demo.openmf.org";
    public static final String API_PATH = "/fineract-provider/api/v1";
    public static final String PROTOCOL_HTTPS = "https://";

    private String url;

    public void updateInstanceUrl(String instanceUrl) {
        this.url = instanceUrl;
    }

    public String getUrl() {
        if (url == null)
            return PROTOCOL_HTTPS + API_ENDPOINT + API_PATH;
        return url;
    }

    public String getName() {
        return "mifos";
    }
}
