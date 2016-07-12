package com.conflux.finflux.finflux.collectionSheet.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.dsl.Ignore;

import java.util.ArrayList;

/**
 * Created by jagadeeshakn on 7/11/2016.
 */
public class Client implements Parcelable {

    Long clientId;
    String clientName;
    AttendanceType attendanceType;
    Long groupId;
    FinfluxGroup mifosGroup;

    @Ignore
    private ArrayList<Loan> loans;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public AttendanceType getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(AttendanceType attendanceType) {
        this.attendanceType = attendanceType;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public FinfluxGroup getMifosGroup() {
        return mifosGroup;
    }

    public void setMifosGroup(FinfluxGroup mifosGroup) {
        this.mifosGroup = mifosGroup;
    }

    public ArrayList<Loan> getLoans() {
        return loans;
    }

    public void setLoans(ArrayList<Loan> loans) {
        this.loans = loans;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", clientName='" + clientName + '\'' +
                ", attendanceType=" + attendanceType +
                ", groupId=" + groupId +
                ", mifosGroup=" + mifosGroup +
                ", loans=" + loans +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.clientId);
        dest.writeString(this.clientName);
        dest.writeParcelable(this.attendanceType, 0);
        dest.writeValue(this.groupId);
        dest.writeParcelable(this.mifosGroup, 0);
        dest.writeTypedList(loans);
    }

    public Client() {
    }

    protected Client(Parcel in) {
        this.clientId = (Long) in.readValue(Long.class.getClassLoader());
        this.clientName = in.readString();
        this.attendanceType = in.readParcelable(AttendanceType.class.getClassLoader());
        this.groupId = (Long) in.readValue(Long.class.getClassLoader());
        this.mifosGroup = in.readParcelable(FinfluxGroup.class.getClassLoader());
        this.loans = in.createTypedArrayList(Loan.CREATOR);
    }

    public static final Creator<Client> CREATOR = new Creator<Client>() {
        public Client createFromParcel(Parcel source) {
            return new Client(source);
        }

        public Client[] newArray(int size) {
            return new Client[size];
        }
    };
}
