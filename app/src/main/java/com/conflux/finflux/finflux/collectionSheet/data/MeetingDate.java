package com.conflux.finflux.finflux.collectionSheet.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

/**
 * Created by jagadeeshakn on 7/11/2016.
 */
public class MeetingDate implements Parcelable {

    int year;
    int month;
    int day;

    public MeetingDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.year);
        dest.writeInt(this.month);
        dest.writeInt(this.day);
    }

    public MeetingDate() {
    }

    protected MeetingDate(Parcel in) {
        this.year = in.readInt();
        this.month = in.readInt();
        this.day = in.readInt();
    }

    public static final Parcelable.Creator<MeetingDate> CREATOR = new Parcelable.Creator<MeetingDate>() {
        public MeetingDate createFromParcel(Parcel source) {
            return new MeetingDate(source);
        }

        public MeetingDate[] newArray(int size) {
            return new MeetingDate[size];
        }
    };
}
