package com.conflux.finflux.createClient.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.conflux.finflux.collectionSheet.data.CodeValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jagadeeshakn on 8/21/2016.
 */
public class ClientAddressTemplate implements Parcelable {

    private List<OptionsType> addressTypeOptions = new ArrayList<OptionsType>();

    private List<CodeValue> addressEntityTypeOptions = new ArrayList<CodeValue>();

    private List<CountryData> countryDatas = new ArrayList<CountryData>();

    public List<OptionsType> getAddressTypeOptions() {
        return addressTypeOptions;
    }

    public void setAddressTypeOptions(List<OptionsType> addressTypeOptions) {
        this.addressTypeOptions = addressTypeOptions;
    }

    public List<CodeValue> getAddressEntityTypeOptions() {
        return addressEntityTypeOptions;
    }

    public void setAddressEntityTypeOptions(List<CodeValue> addressEntityTypeOptions) {
        this.addressEntityTypeOptions = addressEntityTypeOptions;
    }

    public List<CountryData> getCountryDatas() {
        return countryDatas;
    }

    public void setCountryDatas(List<CountryData> countryDatas) {
        this.countryDatas = countryDatas;
    }

    @Override
    public String toString() {
        return "ClientAddressTemplate{" +
                "addressTypeOptions=" + addressTypeOptions +
                ", addressEntityTypeOptions=" + addressEntityTypeOptions +
                ", countryDatas=" + countryDatas +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.addressTypeOptions);
        dest.writeTypedList(addressEntityTypeOptions);
        dest.writeList(this.countryDatas);
    }

    public ClientAddressTemplate() {
    }

    protected ClientAddressTemplate(Parcel in) {
        this.addressTypeOptions = new ArrayList<OptionsType>();
        in.readList(this.addressTypeOptions, List.class.getClassLoader());
        this.addressEntityTypeOptions = in.createTypedArrayList(CodeValue.CREATOR);
        this.countryDatas = new ArrayList<CountryData>();
        in.readList(this.countryDatas, List.class.getClassLoader());
    }

    public static final Creator<ClientAddressTemplate> CREATOR = new Creator<ClientAddressTemplate>() {
        public ClientAddressTemplate createFromParcel(Parcel source) {
            return new ClientAddressTemplate(source);
        }

        public ClientAddressTemplate[] newArray(int size) {
            return new ClientAddressTemplate[size];
        }
    };
}
