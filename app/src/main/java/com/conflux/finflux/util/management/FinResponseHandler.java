package com.conflux.finflux.util.management;

import com.conflux.finflux.util.PrefManager;

/**
 * Created by jagadeeshakn on 9/2/2016.
 */
public class FinResponseHandler {
    public FinResponseHandler() {

    }

    public static String getResponse() {
        switch (PrefManager.getUserStatus()) {

            case 0:
                return "created successfully";
            case 1:
                return "Saved into DB Successfully";

            default:
                return "";
        }

    }
}
