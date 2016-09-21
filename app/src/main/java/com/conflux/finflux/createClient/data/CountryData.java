package com.conflux.finflux.createClient.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jagadeeshakn on 8/21/2016.
 */
public class CountryData implements Parcelable {

    private Long countryId;

    private String isoCountryCode;

    private String countryName;

    private List<StateData> statesDatas = new ArrayList<StateData>();

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getIsoCountryCode() {
        return isoCountryCode;
    }

    public void setIsoCountryCode(String isoCountryCode) {
        this.isoCountryCode = isoCountryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<StateData> getStatesDatas() {
        return statesDatas;
    }

    public void setStatesDatas(List<StateData> statesDatas) {
        this.statesDatas = statesDatas;
    }

    @Override
    public String toString() {
        return "CountryData{" +
                "countryId=" + countryId +
                ", isoCountryCode='" + isoCountryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", statesDatas=" + statesDatas +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.countryId);
        dest.writeString(this.isoCountryCode);
        dest.writeString(this.countryName);
        dest.writeList(this.statesDatas);
    }

    public CountryData() {
    }

    protected CountryData(Parcel in) {
        this.countryId = (Long) in.readValue(Long.class.getClassLoader());
        this.isoCountryCode = in.readString();
        this.countryName = in.readString();
        this.statesDatas = new ArrayList<StateData>();
        in.readList(this.statesDatas, List.class.getClassLoader());
    }

    public static final Creator<CountryData> CREATOR = new Creator<CountryData>() {
        public CountryData createFromParcel(Parcel source) {
            return new CountryData(source);
        }

        public CountryData[] newArray(int size) {
            return new CountryData[size];
        }
    };
}
