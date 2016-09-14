package com.conflux.finflux.collectionSheet.event;

import com.conflux.finflux.collectionSheet.data.SaveCollectionSheetDataForListner;

/**
 * Created by Praveen J U on 9/11/2016.
 */

public class SaveCollectionSheetEventListner {

    private SaveCollectionSheetDataForListner saveCollectionSheetDataForListner;

    public SaveCollectionSheetEventListner(final SaveCollectionSheetDataForListner saveCollectionSheetDataForListner){
        this.saveCollectionSheetDataForListner = saveCollectionSheetDataForListner;
    }

    public SaveCollectionSheetDataForListner getSaveCollectionSheetDataForListner() {
        return saveCollectionSheetDataForListner;
    }
}
