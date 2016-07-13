package com.conflux.finflux.collectionSheet.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.orm.dsl.Ignore;

/**
 * Created by jagadeeshakn on 7/11/2016.
 */
public class Status implements Parcelable {

    String code;
    String value;

    public Status(String code, String value) {
        this.code = code;
        this.value = value;
    }
    @Ignore
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeString(this.value);
    }

    public Status() {
    }

    protected Status(Parcel in) {
        this.code = in.readString();
        this.value = in.readString();
    }

    public static final Parcelable.Creator<Status> CREATOR = new Parcelable.Creator<Status>() {
        public Status createFromParcel(Parcel source) {
            return new Status(source);
        }

        public Status[] newArray(int size) {
            return new Status[size];
        }
    };
}
