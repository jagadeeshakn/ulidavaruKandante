package com.conflux.finflux.db.collectionsheet;

/**
 * Created by Praveen J U on 7/27/2016.
 */
public class TableCollectionSheedData {
    private long id;
    private String Data;
    private Integer totalNumberOfCenters;
    private Integer totalNumberOfcentersDownloaded;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public Integer getTotalNumberOfCenters() {
        return totalNumberOfCenters;
    }

    public void setTotalNumberOfCenters(Integer totalNumberOfCenters) {
        this.totalNumberOfCenters = totalNumberOfCenters;
    }

    public Integer getTotalNumberOfcentersDownloaded() {
        return totalNumberOfcentersDownloaded;
    }

    public void setTotalNumberOfcentersDownloaded(Integer totalNumberOfcentersDownloaded) {
        this.totalNumberOfcentersDownloaded = totalNumberOfcentersDownloaded;
    }
}
