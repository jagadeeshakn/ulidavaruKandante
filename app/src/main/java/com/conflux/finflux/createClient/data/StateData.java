package com.conflux.finflux.createClient.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jagadeeshakn on 8/21/2016.
 */
public class StateData implements Parcelable {

    private Long stateId;

    private Long countryId;

    private String stateName;

    private String isoStateCode;

    private List<DistrictData> districtDatas = new ArrayList<DistrictData>();

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public List<DistrictData> getDistrictDatas() {
        return districtDatas;
    }

    public void setDistrictDatas(List<DistrictData> districtDatas) {
        this.districtDatas = districtDatas;
    }

    public String getIsoStateCode() {
        return isoStateCode;
    }

    public void setIsoStateCode(String isoStateCode) {
        this.isoStateCode = isoStateCode;
    }

    @Override
    public String toString() {
        return "StateData{" +
                "stateId=" + stateId +
                ", countryId=" + countryId +
                ", stateName='" + stateName + '\'' +
                ", isoStateCode='" + isoStateCode + '\'' +
                ", districtDatas=" + districtDatas +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.stateId);
        dest.writeValue(this.countryId);
        dest.writeString(this.stateName);
        dest.writeString(this.isoStateCode);
        dest.writeTypedList(districtDatas);
    }

    public StateData() {
    }

    protected StateData(Parcel in) {
        this.stateId = (Long) in.readValue(Long.class.getClassLoader());
        this.countryId = (Long) in.readValue(Long.class.getClassLoader());
        this.stateName = in.readString();
        this.isoStateCode = in.readString();
        this.districtDatas = new ArrayList<DistrictData>();
        in.readList(this.districtDatas, List.class.getClassLoader());
    }

    public static final Creator<StateData> CREATOR = new Creator<StateData>() {
        public StateData createFromParcel(Parcel source) {
            return new StateData(source);
        }

        public StateData[] newArray(int size) {
            return new StateData[size];
        }
    };
}