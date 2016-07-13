package com.conflux.finflux.collectionSheet.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jagadeeshakn on 7/11/2016.
 */
public class IBase implements Parcelable{
    private Integer iMinDaysInFirstWeek;

    public Integer getiMinDaysInFirstWeek() {
        return iMinDaysInFirstWeek;
    }

    public void setiMinDaysInFirstWeek(Integer iMinDaysInFirstWeek) {
        this.iMinDaysInFirstWeek = iMinDaysInFirstWeek;
    }

    @Override
    public String toString() {
        return "IBase{" +
                "iMinDaysInFirstWeek=" + iMinDaysInFirstWeek +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.iMinDaysInFirstWeek);
    }

    public IBase() {
    }

    protected IBase(Parcel in) {
        this.iMinDaysInFirstWeek = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<IBase> CREATOR = new Parcelable.Creator<IBase>() {
        public IBase createFromParcel(Parcel source) {
            return new IBase(source);
        }

        public IBase[] newArray(int size) {
            return new IBase[size];
        }
    };
}
