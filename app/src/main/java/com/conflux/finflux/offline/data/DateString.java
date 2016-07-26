package com.conflux.finflux.offline.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Praveen J U on 7/17/2016.
 */
public class DateString implements Parcelable {
    private String date;

    public DateString(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
    }

    protected DateString(Parcel in) {
        this.date = in.readString();
    }

    public static final Parcelable.Creator<DateString> CREATOR = new Parcelable.Creator<DateString>() {
        @Override
        public DateString createFromParcel(Parcel source) {
            return new DateString(source);
        }

        @Override
        public DateString[] newArray(int size) {
            return new DateString[size];
        }
    };
}
