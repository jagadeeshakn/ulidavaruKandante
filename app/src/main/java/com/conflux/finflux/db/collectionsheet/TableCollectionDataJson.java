package com.conflux.finflux.db.collectionsheet;

import io.realm.RealmObject;

/**
 * Created by Praveen J U on 8/3/2016.
 */
public class TableCollectionDataJson extends RealmObject {
    private Long fkCenterId;
    private String collectionDataJson;

    public Long getFkCenterId() {
        return fkCenterId;
    }

    public void setFkCenterId(Long fkCenterId) {
        this.fkCenterId = fkCenterId;
    }

    public String getCollectionDataJson() {
        return collectionDataJson;
    }

    public void setCollectionDataJson(String collectionDataJson) {
        this.collectionDataJson = collectionDataJson;
    }
}
