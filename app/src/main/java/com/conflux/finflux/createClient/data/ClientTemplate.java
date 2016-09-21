package com.conflux.finflux.createClient.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.conflux.finflux.collectionSheet.data.CodeValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jagadeeshakn on 8/11/2016.
 */
public class ClientTemplate implements Parcelable {

    private List<Integer> activationDate = new ArrayList<Integer>();

    private Integer officeId;

    private List<OfficeOption> officeOptions = new ArrayList<OfficeOption>();

    private List<StaffOption> staffOptions = new ArrayList<StaffOption>();

    private List<SavingsProductData> savingProductOptions = new ArrayList<SavingsProductData>();

    private List<OptionsType> genderOptions = new ArrayList<OptionsType>();

    private List<CodeValue> clientTypeOptions = new ArrayList<CodeValue>();

    private List<Object> clientClassificationOptions = new ArrayList<Object>();

    private List<Object> clientNonPersonConstitutionOptions = new ArrayList<Object>();

    private List<Object> clientNonPersonMainBusinessLineOptions = new ArrayList<Object>();

    private List<CodeValue> clientLegalFormOptions = new ArrayList<CodeValue>();

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Integer> getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(List<Integer> activationDate) {
        this.activationDate = activationDate;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public List<OfficeOption> getOfficeOptions() {
        return officeOptions;
    }

    public void setOfficeOptions(List<OfficeOption> officeOptions) {
        this.officeOptions = officeOptions;
    }

    public List<StaffOption> getStaffOptions() {
        return staffOptions;
    }

    public void setStaffOptions(List<StaffOption> staffOptions) {
        this.staffOptions = staffOptions;
    }

    public List<SavingsProductData> getSavingProductOptions() {
        return savingProductOptions;
    }

    public void setSavingProductOptions(List<SavingsProductData> savingProductOptions) {
        this.savingProductOptions = savingProductOptions;
    }

    public List<OptionsType> getGenderOptions() {
        return genderOptions;
    }

    public void setGenderOptions(List<OptionsType> genderOptions) {
        this.genderOptions = genderOptions;
    }

    public List<CodeValue> getClientTypeOptions() {
        return clientTypeOptions;
    }

    public void setClientTypeOptions(List<CodeValue> clientTypeOptions) {
        this.clientTypeOptions = clientTypeOptions;
    }

    public List<Object> getClientClassificationOptions() {
        return clientClassificationOptions;
    }

    public void setClientClassificationOptions(List<Object> clientClassificationOptions) {
        this.clientClassificationOptions = clientClassificationOptions;
    }

    public List<Object> getClientNonPersonConstitutionOptions() {
        return clientNonPersonConstitutionOptions;
    }

    public void setClientNonPersonConstitutionOptions(List<Object> clientNonPersonConstitutionOptions) {
        this.clientNonPersonConstitutionOptions = clientNonPersonConstitutionOptions;
    }

    public List<Object> getClientNonPersonMainBusinessLineOptions() {
        return clientNonPersonMainBusinessLineOptions;
    }

    public void setClientNonPersonMainBusinessLineOptions(List<Object> clientNonPersonMainBusinessLineOptions) {
        this.clientNonPersonMainBusinessLineOptions = clientNonPersonMainBusinessLineOptions;
    }

    public List<CodeValue> getClientLegalFormOptions() {
        return clientLegalFormOptions;
    }

    public void setClientLegalFormOptions(List<CodeValue> clientLegalFormOptions) {
        this.clientLegalFormOptions = clientLegalFormOptions;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    @Override
    public String toString() {
        return "ClientTemplate{" +
                "activationDate=" + activationDate +
                ", officeId=" + officeId +
                ", officeOptions=" + officeOptions +
                ", staffOptions=" + staffOptions +
                ", savingProductOptions=" + savingProductOptions +
                ", genderOptions=" + genderOptions +
                ", clientTypeOptions=" + clientTypeOptions +
                ", clientClassificationOptions=" + clientClassificationOptions +
                ", clientNonPersonConstitutionOptions=" + clientNonPersonConstitutionOptions +
                ", clientNonPersonMainBusinessLineOptions=" + clientNonPersonMainBusinessLineOptions +
                ", clientLegalFormOptions=" + clientLegalFormOptions +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.activationDate);
        dest.writeValue(this.officeId);
        dest.writeTypedList(officeOptions);
        dest.writeTypedList(staffOptions);
        dest.writeTypedList(savingProductOptions);
        dest.writeTypedList(genderOptions);
        dest.writeTypedList(clientTypeOptions);
        dest.writeList(this.clientClassificationOptions);
        dest.writeList(this.clientNonPersonConstitutionOptions);
        dest.writeList(this.clientNonPersonMainBusinessLineOptions);
        dest.writeTypedList(clientLegalFormOptions);
        //dest.writeParcelable(this.additionalProperties, flags);
    }

    public ClientTemplate() {
    }

    protected ClientTemplate(Parcel in) {
        this.activationDate = new ArrayList<Integer>();
        in.readList(this.activationDate, List.class.getClassLoader());
        this.officeId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.officeOptions = in.createTypedArrayList(OfficeOption.CREATOR);
        this.staffOptions = in.createTypedArrayList(StaffOption.CREATOR);
        this.savingProductOptions = in.createTypedArrayList(SavingsProductData.CREATOR);
        this.genderOptions = in.createTypedArrayList(OptionsType.CREATOR);
        this.clientTypeOptions = in.createTypedArrayList(CodeValue.CREATOR);
        this.clientClassificationOptions = new ArrayList<Object>();
        in.readList(this.clientClassificationOptions, List.class.getClassLoader());
        this.clientNonPersonConstitutionOptions = new ArrayList<Object>();
        in.readList(this.clientNonPersonConstitutionOptions, List.class.getClassLoader());
        this.clientNonPersonMainBusinessLineOptions = new ArrayList<Object>();
        in.readList(this.clientNonPersonMainBusinessLineOptions, List.class.getClassLoader());
        this.clientLegalFormOptions = in.createTypedArrayList(CodeValue.CREATOR);
        //this.additionalProperties = in.readParcelable(Map<String, Object>.class.getClassLoader());
    }

    public static final Creator<ClientTemplate> CREATOR = new Creator<ClientTemplate>() {
        public ClientTemplate createFromParcel(Parcel source) {
            return new ClientTemplate(source);
        }

        public ClientTemplate[] newArray(int size) {
            return new ClientTemplate[size];
        }
    };
}
