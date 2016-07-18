package com.conflux.finflux.offline.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.conflux.finflux.collectionSheet.data.MeetingFallCenter;

/**
 * Created by Praveen J U on 7/17/2016.
 */
public class CenterWIthMeetingAndCheckedStatus implements Parcelable {
    private String Date;
    private MeetingFallCenter meetingFallCenter;
    public boolean checkedStatus = false;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public MeetingFallCenter getMeetingFallCenter() {
        return meetingFallCenter;
    }

    public void setMeetingFallCenter(MeetingFallCenter meetingFallCenter) {
        this.meetingFallCenter = meetingFallCenter;
    }

    public static CenterWIthMeetingAndCheckedStatus getInstance(){
        return new CenterWIthMeetingAndCheckedStatus();
    }
    public CenterWIthMeetingAndCheckedStatus() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Date);
        dest.writeParcelable(this.meetingFallCenter, flags);
        dest.writeByte(this.checkedStatus ? (byte) 1 : (byte) 0);
    }

    protected CenterWIthMeetingAndCheckedStatus(Parcel in) {
        this.Date = in.readString();
        this.meetingFallCenter = in.readParcelable(MeetingFallCenter.class.getClassLoader());
        this.checkedStatus = in.readByte() != 0;
    }

    public static final Creator<CenterWIthMeetingAndCheckedStatus> CREATOR = new Creator<CenterWIthMeetingAndCheckedStatus>() {
        @Override
        public CenterWIthMeetingAndCheckedStatus createFromParcel(Parcel source) {
            return new CenterWIthMeetingAndCheckedStatus(source);
        }

        @Override
        public CenterWIthMeetingAndCheckedStatus[] newArray(int size) {
            return new CenterWIthMeetingAndCheckedStatus[size];
        }
    };
}
