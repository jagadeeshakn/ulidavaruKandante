package com.conflux.finflux.collectionSheet.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Praveen J U on 7/27/2016.
 */
public class ClientsAttendance implements Parcelable {
    private Integer attendanceType;
    private Long clientId;

    @Override
    public String toString() {
        return "ClientsAttendance{" +
                "attendanceType=" + attendanceType +
                ", clientId=" + clientId +
                '}';
    }

    public Integer getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(Integer attendanceType) {
        this.attendanceType = attendanceType;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.attendanceType);
        dest.writeValue(this.clientId);
    }

    public ClientsAttendance() {
    }

    private ClientsAttendance(final Long clientId, final Integer attendanceTypeId){
        this.clientId = clientId;
        this.attendanceType = attendanceTypeId;
    }

    public static ClientsAttendance instance(final Long clientId, final Integer attendanceTypeId){
        return new ClientsAttendance(clientId, attendanceTypeId);
    }
    protected ClientsAttendance(Parcel in) {
        this.attendanceType = (Integer) in.readValue(Long.class.getClassLoader());
        this.clientId = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<ClientsAttendance> CREATOR = new Parcelable.Creator<ClientsAttendance>() {
        @Override
        public ClientsAttendance createFromParcel(Parcel source) {
            return new ClientsAttendance(source);
        }

        @Override
        public ClientsAttendance[] newArray(int size) {
            return new ClientsAttendance[size];
        }
    };
}
