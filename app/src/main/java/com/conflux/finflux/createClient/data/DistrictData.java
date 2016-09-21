package com.conflux.finflux.createClient.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jagadeeshakn on 8/21/2016.
 */
public class DistrictData implements Parcelable {

    private Long districtId;

    private Long stateId;

    private String districtName;

    private String isoDistrictCode;

    private List<TalukaData> talukaDatas = new ArrayList<TalukaData>();

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getIsoDistrictCode() {
        return isoDistrictCode;
    }

    public void setIsoDistrictCode(String isoDistrictCode) {
        this.isoDistrictCode = isoDistrictCode;
    }

    public List<TalukaData> getTalukaDatas() {
        return talukaDatas;
    }

    public void setTalukaDatas(List<TalukaData> talukaDatas) {
        this.talukaDatas = talukaDatas;
    }

    @Override
    public String toString() {
        return "DistrictData{" +
                "districtId=" + districtId +
                ", stateId=" + stateId +
                ", districtName='" + districtName + '\'' +
                ", isoDistrictCode='" + isoDistrictCode + '\'' +
                ", talukaDatas=" + talukaDatas +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.districtId);
        dest.writeValue(this.stateId);
        dest.writeString(this.districtName);
        dest.writeString(this.isoDistrictCode);
        dest.writeList(this.talukaDatas);
    }

    public DistrictData() {
    }

    protected DistrictData(Parcel in) {
        this.districtId = (Long) in.readValue(Long.class.getClassLoader());
        this.stateId = (Long) in.readValue(Long.class.getClassLoader());
        this.districtName = in.readString();
        this.isoDistrictCode = in.readString();
        this.talukaDatas = new ArrayList<TalukaData>();
        in.readList(this.talukaDatas, List.class.getClassLoader());
    }

    public static final Creator<DistrictData> CREATOR = new Creator<DistrictData>() {
        public DistrictData createFromParcel(Parcel source) {
            return new DistrictData(source);
        }

        public DistrictData[] newArray(int size) {
            return new DistrictData[size];
        }
    };
}
