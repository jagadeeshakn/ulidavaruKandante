package com.conflux.finflux.infrastructure.api.manager;

import com.google.gson.Gson;

/**
 * Created by jagadeeshakn on 8/3/2016.
 */
public class SaveResponse {

    public int groupId;
    public int resourceId;
    public Changes changes;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
