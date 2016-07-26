package com.conflux.finflux.offline.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.conflux.finflux.collectionSheet.data.MeetingFallCenter;

/**
 * Created by Praveen J U on 7/17/2016.
 */
public class CenterListHelper implements Parcelable {
    private String Date;
    private MeetingFallCenter meetingFallCenter;
    private boolean canDownload;
    private String reason;
    public boolean checkedStatus = false;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }



    public boolean isCanDownload() {
        return canDownload;
    }

    public void setCanDownload(boolean canDownload) {
        this.canDownload = canDownload;
    }



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

    public static CenterListHelper getInstance(){
        return new CenterListHelper();
    }
    public CenterListHelper() {
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

    protected CenterListHelper(Parcel in) {
        this.Date = in.readString();
        this.meetingFallCenter = in.readParcelable(MeetingFallCenter.class.getClassLoader());
        this.checkedStatus = in.readByte() != 0;
    }

    public static final Creator<CenterListHelper> CREATOR = new Creator<CenterListHelper>() {
        @Override
        public CenterListHelper createFromParcel(Parcel source) {
            return new CenterListHelper(source);
        }

        @Override
        public CenterListHelper[] newArray(int size) {
            return new CenterListHelper[size];
        }
    };
}
