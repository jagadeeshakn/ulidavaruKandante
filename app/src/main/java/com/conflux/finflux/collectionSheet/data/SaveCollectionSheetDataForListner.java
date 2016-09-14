package com.conflux.finflux.collectionSheet.data;

import java.math.BigDecimal;

/**
 * Created by Praveen J U on 9/11/2016.
 *
 * When the collection sheet is successfully saved. this class is used to update the center collected amount
 * if the network is down this class is used to decide to store the center detail in the database.
 */
public class SaveCollectionSheetDataForListner {
    private Long centerId;
    private Double collectedAmount;
    private boolean isOffline;

     public SaveCollectionSheetDataForListner(final Long centerId, final Double collectedAmount, final boolean isOffline){
        this.centerId = centerId;
        this.collectedAmount = collectedAmount;
        this.isOffline = isOffline;
    }

    public Long getCenterId() {
        return centerId;
    }

    public Double getCollectedAmount() {
        return collectedAmount;
    }

    public boolean isOffline() {
        return isOffline;
    }
}
