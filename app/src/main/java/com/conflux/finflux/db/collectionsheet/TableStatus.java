package com.conflux.finflux.db.collectionsheet;

import io.realm.RealmObject;

/**
 * Created by Praveen J U on 7/17/2016.
 */
public class TableStatus  extends RealmObject{
    private Long id;
    private String code;
    private String value;
    private Long fkCenterId;

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setFkCenterId(Long fkCenterId) {
        this.fkCenterId = fkCenterId;
    }

    public TableStatus() {
    }

    public TableStatus(Long id, String code, String value, Long fkCenterId) {
        super();
        this.id = id;
        this.code = code;
        this.value = value;
        this.fkCenterId = fkCenterId;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public Long getFkCenterId() {
        return fkCenterId;
    }
}
