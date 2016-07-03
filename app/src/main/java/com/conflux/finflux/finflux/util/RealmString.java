package com.conflux.finflux.finflux.util;

import io.realm.RealmObject;

/**
 * Created by Praveen J U on 7/2/2016.
 */
public class RealmString extends RealmObject {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
