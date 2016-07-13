package com.conflux.finflux.collectionSheet.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jagadeeshakn on 7/11/2016.
 */
public class IChronology implements Parcelable {
    private IBase iBase;

    public IBase getiBase() {
        return iBase;
    }

    public void setiBase(IBase iBase) {
        this.iBase = iBase;
    }

    @Override
    public String toString() {
        return "IChronology{" +
                "iBase=" + iBase +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.iBase, flags);
    }

    public IChronology() {
    }

    protected IChronology(Parcel in) {
        this.iBase = in.readParcelable(IBase.class.getClassLoader());
    }

    public static final Parcelable.Creator<IChronology> CREATOR = new Parcelable.Creator<IChronology>() {
        public IChronology createFromParcel(Parcel source) {
            return new IChronology(source);
        }

        public IChronology[] newArray(int size) {
            return new IChronology[size];
        }
    };
}
