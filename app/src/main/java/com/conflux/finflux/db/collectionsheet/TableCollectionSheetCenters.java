package com.conflux.finflux.db.collectionsheet;

/**
 * Created by Praveen J U on 7/27/2016.
 */
public class TableCollectionSheetCenters {
    private long fkCenterData;
    private String centers;

    public long getFkCenterData() {
        return fkCenterData;
    }

    public void setFkCenterData(long fkCenterData) {
        this.fkCenterData = fkCenterData;
    }

    public String getCenters() {
        return centers;
    }

    public void setCenters(String centers) {
        this.centers = centers;
    }
}
