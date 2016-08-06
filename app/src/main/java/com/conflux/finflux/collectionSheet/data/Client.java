package com.conflux.finflux.collectionSheet.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.dsl.Ignore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jagadeeshakn on 7/11/2016.
 */
public class Client implements Parcelable {

    private Long clientId;

    private String clientName;

    private List<Loan> loans = new ArrayList<Loan>();

    private CodeValue attendanceType;

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

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public CodeValue getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(CodeValue attendanceType) {
        this.attendanceType = attendanceType;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", clientName='" + clientName + '\'' +
                ", loans=" + loans +
                ", attendanceType=" + attendanceType +
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
        dest.writeTypedList(loans);
        dest.writeParcelable(this.attendanceType, 0);
    }

    public Client() {
    }

    protected Client(Parcel in) {
        this.clientId = (Long) in.readValue(Long.class.getClassLoader());
        this.clientName = in.readString();
        this.loans = in.createTypedArrayList(Loan.CREATOR);
        this.attendanceType = in.readParcelable(CodeValue.class.getClassLoader());
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
