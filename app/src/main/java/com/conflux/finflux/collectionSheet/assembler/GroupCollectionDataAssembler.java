package com.conflux.finflux.collectionSheet.assembler;

import com.conflux.finflux.collectionSheet.data.BulkRepaymentTransactions;
import com.conflux.finflux.collectionSheet.data.Client;
import com.conflux.finflux.collectionSheet.data.CodeValue;
import com.conflux.finflux.collectionSheet.data.CollectionSheetConstants;
import com.conflux.finflux.collectionSheet.data.CollectionSheetData;
import com.conflux.finflux.collectionSheet.data.CollectionSheetDataForAdapter;
import com.conflux.finflux.collectionSheet.data.Group;
import com.conflux.finflux.collectionSheet.data.Loan;
import com.conflux.finflux.collectionSheet.data.SaveCollectionSheetPayload;
import com.conflux.finflux.util.Logger;

import java.util.ArrayList;

/**
 * Created by Praveen J U on 7/27/2016.
 */
public class GroupCollectionDataAssembler {
    private final String TAG = getClass().getSimpleName();
    public ArrayList<CollectionSheetDataForAdapter> assembleDataForGroupList(CollectionSheetData collectionSheetData, final SaveCollectionSheetPayload payload) {
        ArrayList<CollectionSheetDataForAdapter> collectionSheetDataForAdapter = new ArrayList<CollectionSheetDataForAdapter>();
        for (Group group : collectionSheetData.getGroups()) {
            double totalDue = 0;
            double totalCollected = 0;
            for (Client client : group.getClients()) {
                CollectionSheetDataForAdapter collectionSheetDataForAdapterClient = new CollectionSheetDataForAdapter();
                collectionSheetDataForAdapterClient.setEntityId(client.getClientId());
                collectionSheetDataForAdapterClient.setEntityType(CollectionSheetConstants.CLIENT);
                collectionSheetDataForAdapterClient.setEntityName(client.getClientName());
                collectionSheetDataForAdapterClient.setParentId(group.getGroupId());
                Logger.d(TAG, "the group id " + group.getGroupId());
                double clientToalDue = 0;
                double clientTotalCollected = 0;
                collectionSheetDataForAdapterClient.setLoans(client.getLoans());
                for (Loan loan : client.getLoans()) {
                    if(loan.getTotalCollected() == null) {
                        clientToalDue = clientToalDue + loan.getTotalDue();
                        clientTotalCollected = clientToalDue;
                        loan.setTotalCollected(loan.getTotalDue());
                    }else {
                        clientToalDue = clientToalDue + loan.getTotalDue();
                        clientTotalCollected = clientTotalCollected + loan.getTotalCollected();
                    }
                }
                totalDue = totalDue + clientToalDue;
                totalCollected = totalCollected + clientTotalCollected;
                collectionSheetDataForAdapterClient.setDueAmount(clientToalDue);
                if(payload != null){
                    double payloadClientTotal = 0;
                    for(Loan loan : client.getLoans()){
                        ArrayList<BulkRepaymentTransactions> loans = payload.getBulkRepaymentTransactions();
                        for(BulkRepaymentTransactions transaction : loans){
                            if(transaction.getLoanId().equals(loan.getLoanId())){
                                payloadClientTotal = payloadClientTotal + transaction.getTransactionAmount();
                                loan.setTotalCollected(transaction.getTransactionAmount());
                            }
                        }
                    }
                    collectionSheetDataForAdapterClient.setCollectedAmount(payloadClientTotal);
                    //get client attendance Options Here to update the client attendance
                    collectionSheetDataForAdapterClient.setAttendanceType(client.getAttendanceType());
                }else {
                    collectionSheetDataForAdapterClient.setCollectedAmount(clientTotalCollected);
                    CodeValue attendanceType = null;
                    for(CodeValue attendance : collectionSheetData.getAttendanceTypeOptions()){
                        if(attendance.getId() == client.getAttendanceType().getId()){
                            attendanceType = attendance;
                        }
                    }
                    if(attendanceType == null){
                        for(CodeValue attendance : collectionSheetData.getAttendanceTypeOptions() ){
                            if(attendance.getValue().equalsIgnoreCase("present")){
                                attendanceType = attendance;
                            }
                        }
                    }
                    collectionSheetDataForAdapterClient.setAttendanceType(attendanceType);
                }
                collectionSheetDataForAdapter.add(collectionSheetDataForAdapterClient);
            }
            CollectionSheetDataForAdapter collectionSheetDataForAdapterGroup = new CollectionSheetDataForAdapter();
            collectionSheetDataForAdapterGroup.setEntityId(group.getGroupId());
            collectionSheetDataForAdapterGroup.setEntityName(group.getGroupName());
            collectionSheetDataForAdapterGroup.setEntityType(CollectionSheetConstants.GROUP);
            collectionSheetDataForAdapterGroup.setDueAmount(totalDue);
            collectionSheetDataForAdapterGroup.setCollectedAmount(totalCollected);
            collectionSheetDataForAdapter.add(collectionSheetDataForAdapterGroup);
        }
        return collectionSheetDataForAdapter;
    }


    //method used to calculat the total collected/ total due.
    public Double claculateGroupTotal(final ArrayList<CollectionSheetDataForAdapter> collectionSheetDataForAdapter, final String totalFor){
        //use only group entity type from the group
        Double total = 0.0;
        for(CollectionSheetDataForAdapter entityType: collectionSheetDataForAdapter){
            if(entityType.getEntityType().equals(CollectionSheetConstants.GROUP)){
                CollectionSheetDataForAdapter selectedGroup = entityType;
                if(totalFor.equals(CollectionSheetConstants.TOTAL_DUE)) {
                    total = total + selectedGroup.getDueAmount();
                }
                if(totalFor.equals(CollectionSheetConstants.TOTAL_COLLECTED)){
                    total = total + selectedGroup.getCollectedAmount();
                }
            }
        }
        return total;
    }
}
