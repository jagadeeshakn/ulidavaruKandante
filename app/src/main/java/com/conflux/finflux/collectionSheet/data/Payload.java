package com.conflux.finflux.collectionSheet.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jagadeeshakn on 7/12/2016.
 */
public class Payload implements Parcelable {
    private String dateFormat;
    private String locale;
    private Object officeId;
    private Object staffId;
    private Object meetingDate;
    private String transactionDate;
    private long calendarId;

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
        return (Long) officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public Long getStaffId() {
        return (Long) staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getMeetingDate() {
        return (String) meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public long getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(long calendarId) {
        this.calendarId = calendarId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "dateFormat='" + dateFormat + '\'' +
                ", locale='" + locale + '\'' +
                ", officeId=" + officeId +
                ", staffId=" + staffId +
                ", meetingDate='" + meetingDate + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                ", calendarId=" + calendarId +
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
        dest.writeString((String) this.meetingDate);
        dest.writeString(this.transactionDate);
        dest.writeLong(this.calendarId);
    }

    public Payload() {
    }

    protected Payload(Parcel in) {
        this.dateFormat = in.readString();
        this.locale = in.readString();
        this.officeId = (Long) in.readValue(Long.class.getClassLoader());
        this.staffId = (Long) in.readValue(Long.class.getClassLoader());
        this.meetingDate = in.readString();
        this.transactionDate = in.readString();
        this.calendarId = in.readLong();
    }

}
