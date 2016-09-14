package com.conflux.finflux.collectionSheet.event;

import com.conflux.finflux.collectionSheet.data.CollectionSheetDataForAdapter;
import com.conflux.finflux.collectionSheet.data.Loan;
import com.conflux.finflux.db.collectionsheet.TableCollectionDataJson;
import com.conflux.finflux.util.Logger;

/**
 * Created by Praveen J U on 9/4/2016.
 */
public class ClientCollectionSheetDataChangeListner {

    CollectionSheetDataForAdapter clientCollectionSheetData;
    public CollectionSheetDataForAdapter getClientCollectionSheetData() {
        return clientCollectionSheetData;
    }

    public ClientCollectionSheetDataChangeListner(CollectionSheetDataForAdapter clientCollectionSheetData){
        this.clientCollectionSheetData = clientCollectionSheetData;
    }
}
