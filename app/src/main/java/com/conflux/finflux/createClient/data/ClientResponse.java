package com.conflux.finflux.createClient.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jagadeeshakn on 9/2/2016.
 */
public class ClientResponse {

    private Integer officeId;

    private Long clientId;

    private Long resourceId;

    private Map<String, Object> clientResponse = new HashMap<String, Object>();


    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }


    public Long getClientId() {
        return clientId;
    }


    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }


    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }


    public Map<String, Object> getClientResponse() {
        return this.clientResponse;
    }

    public void setClientResponse(String name, Object value) {
        this.clientResponse.put(name, value);
    }


}
