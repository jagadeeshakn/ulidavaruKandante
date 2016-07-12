package com.conflux.finflux.finflux.collectionSheet.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jagadeeshakn on 7/12/2016.
 */
public class Payload implements Parcelable {
    private String dateFormat;
    private String locale;
    private Long officeId;
    private Long staffId;
    private String meetingDate;

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "dateFormat='" + dateFormat + '\'' +
                ", locale='" + locale + '\'' +
                ", officeId=" + officeId +
                ", staffId=" + staffId +
                ", meetingDate='" + meetingDate + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.dateFormat);
        dest.writeString(this.locale);
        dest.writeValue(this.officeId);
        dest.writeValue(this.staffId);
        dest.writeString(this.meetingDate);
    }

    public Payload() {
    }

    protected Payload(Parcel in) {
        this.dateFormat = in.readString();
        this.locale = in.readString();
        this.officeId = (Long) in.readValue(Long.class.getClassLoader());
        this.staffId = (Long) in.readValue(Long.class.getClassLoader());
        this.meetingDate = in.readString();
    }

    public static final Creator<Payload> CREATOR = new Creator<Payload>() {
        public Payload createFromParcel(Parcel source) {
            return new Payload(source);
        }

        public Payload[] newArray(int size) {
            return new Payload[size];
        }
    };
}
