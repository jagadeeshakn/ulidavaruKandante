package com.conflux.finflux.collectionSheet.data;

import android.os.Parcel;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jagadeeshakn on 7/30/2016.
 */
public class CollectionSheetPayload extends Payload {

    private ArrayList<BulkRepaymentTransactions> bulkRepaymentTransactions;
    private ArrayList<AttendanceType> clientsAttendance;

    public ArrayList<BulkRepaymentTransactions> getBulkRepaymentTransactions() {
        return bulkRepaymentTransactions;
    }

    public void setBulkRepaymentTransactions(ArrayList<BulkRepaymentTransactions> bulkRepaymentTransactions) {
        this.bulkRepaymentTransactions = bulkRepaymentTransactions;
    }

    public ArrayList<AttendanceType> getClientsAttendance() {
        return clientsAttendance;
    }

    public void setClientsAttendance(ArrayList<AttendanceType> clientsAttendance) {
        this.clientsAttendance = clientsAttendance;
    }

    @Override
    public String toString()
    {
        return new Gson().toJson(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(bulkRepaymentTransactions);
        dest.writeTypedList(clientsAttendance);
    }

    public CollectionSheetPayload() {
    }

    protected CollectionSheetPayload(Parcel in) {
        super(in);
         this.bulkRepaymentTransactions = in.createTypedArrayList(BulkRepaymentTransactions.CREATOR);
        this.clientsAttendance = in.createTypedArrayList(AttendanceType.CREATOR);
    }

    public static final Creator<CollectionSheetPayload> CREATOR = new Creator<CollectionSheetPayload>() {
        public CollectionSheetPayload createFromParcel(Parcel source) {
            return new CollectionSheetPayload(source);
        }

        public CollectionSheetPayload[] newArray(int size) {
            return new CollectionSheetPayload[size];
        }
    };
}
