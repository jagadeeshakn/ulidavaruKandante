package com.conflux.finflux.collectionSheet.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jagadeeshakn on 7/11/2016.
 */
public class MeetingTime implements Parcelable {

    private Integer iLocalMillis;
    private IChronology iChronology;

    public Integer getiLocalMillis() {
        return iLocalMillis;
    }

    public void setiLocalMillis(Integer iLocalMillis) {
        this.iLocalMillis = iLocalMillis;
    }

    public IChronology getiChronology() {
        return iChronology;
    }

    public void setiChronology(IChronology iChronology) {
        this.iChronology = iChronology;
    }

    @Override
    public String toString() {
        return "MeetingTime{" +
                "iLocalMillis=" + iLocalMillis +
                ", iChronology=" + iChronology +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.iLocalMillis);
        dest.writeParcelable(this.iChronology, flags);
    }

    public MeetingTime() {
    }

    protected MeetingTime(Parcel in) {
        this.iLocalMillis = (Integer) in.readValue(Integer.class.getClassLoader());
        this.iChronology = in.readParcelable(IChronology.class.getClassLoader());
    }

    public static final Parcelable.Creator<MeetingTime> CREATOR = new Parcelable.Creator<MeetingTime>() {
        public MeetingTime createFromParcel(Parcel source) {
            return new MeetingTime(source);
        }

        public MeetingTime[] newArray(int size) {
            return new MeetingTime[size];
        }
    };
}
