package com.conflux.finflux.collectionSheet.data;

import android.os.Parcel;

import java.util.ArrayList;

/**
 * Created by Praveen J U on 7/27/2016.
 */
public class SaveCollectionSheetPayload extends Payload {
    private ArrayList<BulkRepaymentTransactions> bulkRepaymentTransactions;
    private ArrayList<ClientsAttendance> clientsAttendance;
    private boolean forcedSubmitOfCollectionSheet = false;
    private ArrayList bulkDisbursementTransactions = new ArrayList();

    @Override
    public String toString() {
        return super.toString()+"SaveCollectionSheetPayload{" +
                "bulkRepaymentTransactions=" + bulkRepaymentTransactions +
                ", clientsAttendance=" + clientsAttendance +
                '}';
    }

    public ArrayList<ClientsAttendance> getClientsAttendance() {
        return clientsAttendance;
    }

    public void setClientsAttendance(ArrayList<ClientsAttendance> clientsAttendance) {
        this.clientsAttendance = clientsAttendance;
    }

    public ArrayList<BulkRepaymentTransactions> getBulkRepaymentTransactions() {
        return bulkRepaymentTransactions;
    }

    public void setBulkRepaymentTransactions(ArrayList<BulkRepaymentTransactions> bulkRepaymentTransactions) {
        this.bulkRepaymentTransactions = bulkRepaymentTransactions;
    }

    public SaveCollectionSheetPayload() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(this.bulkRepaymentTransactions);
        dest.writeTypedList(this.clientsAttendance);
    }

    protected SaveCollectionSheetPayload(Parcel in) {
        super(in);
        this.bulkRepaymentTransactions = in.createTypedArrayList(BulkRepaymentTransactions.CREATOR);
        this.clientsAttendance = in.createTypedArrayList(ClientsAttendance.CREATOR);
    }

    public static final Creator<SaveCollectionSheetPayload> CREATOR = new Creator<SaveCollectionSheetPayload>() {
        @Override
        public SaveCollectionSheetPayload createFromParcel(Parcel source) {
            return new SaveCollectionSheetPayload(source);
        }

        @Override
        public SaveCollectionSheetPayload[] newArray(int size) {
            return new SaveCollectionSheetPayload[size];
        }
    };
}
