package com.conflux.finflux.collectionSheet.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jagadeeshakn on 7/11/2016.
 */
public class AttendanceType implements Parcelable {

    Long attendanceTypeId;
    String code;
    String value;
    Long clientId;
    Client client;

    public Long getAttendanceTypeId() {
        return attendanceTypeId;
    }

    public void setAttendanceTypeId(Long attendanceTypeId) {
        this.attendanceTypeId = attendanceTypeId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.attendanceTypeId);
        dest.writeString(this.code);
        dest.writeString(this.value);
        dest.writeValue(this.clientId);
        dest.writeParcelable(this.client, 0);
    }

    public AttendanceType() {
    }

    protected AttendanceType(Parcel in) {
        this.attendanceTypeId = (Long) in.readValue(Long.class.getClassLoader());
        this.code = in.readString();
        this.value = in.readString();
        this.clientId = (Long) in.readValue(Long.class.getClassLoader());
        this.client = in.readParcelable(Client.class.getClassLoader());
    }

    public static final Creator<AttendanceType> CREATOR = new Creator<AttendanceType>() {
        public AttendanceType createFromParcel(Parcel source) {
            return new AttendanceType(source);
        }

        public AttendanceType[] newArray(int size) {
            return new AttendanceType[size];
        }
    };
}
