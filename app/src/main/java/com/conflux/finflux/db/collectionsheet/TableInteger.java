package com.conflux.finflux.db.collectionsheet;

import android.content.Intent;

import io.realm.RealmObject;

/**
 * Created by Praveen J U on 7/17/2016.
 */
public class TableInteger extends RealmObject{
    private Integer value;
    private Long fkEntityIdForInteger;

    public TableInteger() {
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setFkEntityIdForInteger(Long fkEntityIdForInteger) {
        this.fkEntityIdForInteger = fkEntityIdForInteger;
    }

    public TableInteger(Integer value, Long fkEntityIdForInteger) {
        super();
        this.value = value;
        this.fkEntityIdForInteger = fkEntityIdForInteger;
    }

    public Long getFkEntityIdForInteger() {
        return fkEntityIdForInteger;
    }

    public Integer getValue() {
        return value;
    }
}
