package com.conflux.finflux.db.collectionsheet;

import io.realm.RealmObject;

/**
 * Created by Praveen J U on 7/17/2016.
 */
public class TableStatus  extends RealmObject{
    private Integer id;
    private String code;
    private String value;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private int type;
    private Long fkCenterId;

    public void setId(Integer id) {
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


    public Integer getId() {
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
