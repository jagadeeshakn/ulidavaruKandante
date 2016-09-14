package com.conflux.finflux.collectionSheet.assembler;

import com.conflux.finflux.collectionSheet.data.CodeValue;
import com.conflux.finflux.collectionSheet.data.CollectionSheetDataForAdapter;
import com.conflux.finflux.collectionSheet.data.Currency;
import com.conflux.finflux.collectionSheet.data.Loan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Praveen J U on 8/26/2016.
 */
public class ClientLoanDetailsDataAssembler {


    public static CollectionSheetDataForAdapter createNewObjectForClient(CollectionSheetDataForAdapter collectionSheetDataForAdapter){
        CollectionSheetDataForAdapter dataForAdapter = new CollectionSheetDataForAdapter();
        //set attendance of the client
        CodeValue attendanceType = new CodeValue();
        attendanceType.setCode(collectionSheetDataForAdapter.getAttendanceType().getCode());
        attendanceType.setId(collectionSheetDataForAdapter.getAttendanceType().getId());
        attendanceType.setValue(collectionSheetDataForAdapter.getAttendanceType().getValue());
        dataForAdapter.setAttendanceType(attendanceType);

        dataForAdapter.setEntityId(collectionSheetDataForAdapter.getEntityId());
        dataForAdapter.setEntityName(collectionSheetDataForAdapter.getEntityName());
        dataForAdapter.setEntityType(collectionSheetDataForAdapter.getEntityType());
        dataForAdapter.setParentId(collectionSheetDataForAdapter.getParentId());

        dataForAdapter.setCollectedAmount(collectionSheetDataForAdapter.getCollectedAmount());
        dataForAdapter.setDueAmount(collectionSheetDataForAdapter.getDueAmount());
        dataForAdapter.setLoans(creatNewLoanObjects(collectionSheetDataForAdapter.getLoans()));
        return dataForAdapter;

    }

    private static List<Loan> creatNewLoanObjects(List<Loan> loans){
        List<Loan> clientLoans = new ArrayList<>();
        for(Loan loan : loans){
            Loan newLoan = new Loan();
            newLoan.setLoanId(loan.getLoanId());
            newLoan.setAccountId(loan.getAccountId());
            newLoan.setAccountStatusId(loan.getAccountStatusId());
            newLoan.setProductShortName(loan.getProductShortName());
            newLoan.setProductId(loan.getProductId());
            newLoan.setPrincipalDue(loan.getPrincipalDue());
            newLoan.setPrincipalPaid(loan.getPrincipalPaid());
            newLoan.setInterestDue(loan.getInterestDue());
            newLoan.setInterestPaid(loan.getInterestPaid());
            newLoan.setTotalDue(loan.getTotalDue());
            newLoan.setTotalCollected(loan.getTotalCollected());

            //set currency
            Currency currency = new Currency();
            try {
                currency.setCode(loan.getCurrency().getCode());
                currency.setName(loan.getCurrency().getName());
                currency.setDecimalPlaces(loan.getCurrency().getDecimalPlaces());
                currency.setDisplayLabel(loan.getCurrency().getDisplayLabel());
                currency.setInMultiplesOf(loan.getCurrency().getInMultiplesOf());
                currency.setNameCode(loan.getCurrency().getNameCode());
                currency.setDisplaySymbol(loan.getCurrency().getDisplaySymbol());
            }catch (NullPointerException e){

            }
            newLoan.setCurrency(currency);


            clientLoans.add(newLoan);
        }
        return clientLoans;
    }
}
