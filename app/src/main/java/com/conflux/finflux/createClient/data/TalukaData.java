package com.conflux.finflux.createClient.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jagadeeshakn on 8/21/2016.
 */
public class TalukaData implements Parcelable {

    private Long talukaId;

    private Long districtId;

    private String talukaName;

    private String isoTalukaCode;

    public Long getTalukaId() {
        return talukaId;
    }

    public void setTalukaId(Long talukaId) {
        this.talukaId = talukaId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getTalukaName() {
        return talukaName;
    }

    public void setTalukaName(String talukaName) {
        this.talukaName = talukaName;
    }

    public String getIsoTalukaCode() {
        return isoTalukaCode;
    }

    public void setIsoTalukaCode(String isoTalukaCode) {
        this.isoTalukaCode = isoTalukaCode;
    }

    @Override
    public String toString() {
        return "TalukaData{" +
                "talukaId=" + talukaId +
                ", districtId=" + districtId +
                ", talukaName='" + talukaName + '\'' +
                ", isoTalukaCode='" + isoTalukaCode + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.talukaId);
        dest.writeValue(this.districtId);
        dest.writeString(this.talukaName);
        dest.writeString(this.isoTalukaCode);
    }

    public TalukaData() {
    }

    protected TalukaData(Parcel in) {
        this.talukaId = (Long) in.readValue(Long.class.getClassLoader());
        this.districtId = (Long) in.readValue(Long.class.getClassLoader());
        this.talukaName = in.readString();
        this.isoTalukaCode = in.readString();
    }

    public static final Parcelable.Creator<TalukaData> CREATOR = new Parcelable.Creator<TalukaData>() {
        public TalukaData createFromParcel(Parcel source) {
            return new TalukaData(source);
        }

        public TalukaData[] newArray(int size) {
            return new TalukaData[size];
        }
    };
}
