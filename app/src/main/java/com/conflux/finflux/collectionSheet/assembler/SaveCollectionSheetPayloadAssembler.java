package com.conflux.finflux.collectionSheet.assembler;

import com.conflux.finflux.collectionSheet.data.BulkRepaymentTransactions;
import com.conflux.finflux.collectionSheet.data.ClientsAttendance;
import com.conflux.finflux.collectionSheet.data.CollectionSheetConstants;
import com.conflux.finflux.collectionSheet.data.CollectionSheetDataForAdapter;
import com.conflux.finflux.collectionSheet.data.Loan;
import com.conflux.finflux.collectionSheet.data.SaveCollectionSheetPayload;
import com.conflux.finflux.util.DateHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Praveen J U on 9/5/2016.
 */
public class SaveCollectionSheetPayloadAssembler {
    private SaveCollectionSheetPayload saveCollectionSheetPayload;
    private ArrayList<CollectionSheetDataForAdapter> collectionSheetDataForAdapters;


    public SaveCollectionSheetPayload assemblePayload(final ArrayList<CollectionSheetDataForAdapter> collectionSheetDataForAdapters, final Long calenderId, String transactionDate){
        this.saveCollectionSheetPayload = new SaveCollectionSheetPayload();
        this.collectionSheetDataForAdapters = collectionSheetDataForAdapters;
        saveCollectionSheetPayload.setClientsAttendance(assembleClientsAttendances());
        saveCollectionSheetPayload.setBulkRepaymentTransactions(assembleRepayments());
        saveCollectionSheetPayload.setCalendarId(calenderId);
        saveCollectionSheetPayload.setTransactionDate(DateHelper.getDateFormatFullString(transactionDate));
        saveCollectionSheetPayload.setDateFormat(CollectionSheetConstants.DATE_FORMAT);
        saveCollectionSheetPayload.setLocale(CollectionSheetConstants.EN);
        return saveCollectionSheetPayload;
    }

    private  ArrayList<ClientsAttendance> assembleClientsAttendances(){
        ArrayList<ClientsAttendance> clientsAttendances = new ArrayList<>();
        for(CollectionSheetDataForAdapter collectionSheetData : this.collectionSheetDataForAdapters){
            if(collectionSheetData.getEntityType().equals(CollectionSheetConstants.CLIENT)){
                ClientsAttendance clientsAttendance = ClientsAttendance.instance(collectionSheetData.getEntityId(),collectionSheetData.getAttendanceType().getId());
                clientsAttendances.add(clientsAttendance);
            }
        }
        return clientsAttendances;
    }

    private ArrayList<BulkRepaymentTransactions> assembleRepayments(){
        ArrayList<BulkRepaymentTransactions> bulkRepaymentTransactionses = new ArrayList<>();
        for(CollectionSheetDataForAdapter collectionSheetData : collectionSheetDataForAdapters){
            if(collectionSheetData.getEntityType().equals(CollectionSheetConstants.CLIENT)){
                for(Loan loan : collectionSheetData.getLoans()){
                    BulkRepaymentTransactions bulkRepayment = BulkRepaymentTransactions.instance(loan.getLoanId(), loan.getTotalCollected());
                    bulkRepaymentTransactionses.add(bulkRepayment);
                }
            }
        }
        return bulkRepaymentTransactionses;
    }

}
