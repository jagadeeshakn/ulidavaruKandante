package com.conflux.finflux.createClient.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jagadeeshakn on 8/20/2016.
 */
public class EntityAddress implements Parcelable {

    private Integer entityTypeEnum;

    private Long entityId;

    private List<Long> addressTypes = new ArrayList<Long>();

    private String houseNo;

    private String streeNo;

    private String villageTown;

    private Long talukId;

    private String addressLineOne;

    private Long districtId;

    private Long stateId;

    private Long countryId;

    private String postalCode;

    private String locale;

    private String dateFormat;

    private String addressLineTwo;

    public Integer getEntityTypeEnum() {
        return entityTypeEnum;
    }

    public void setEntityTypeEnum(Integer entityTypeEnum) {
        this.entityTypeEnum = entityTypeEnum;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public List<Long> getAddressTypes() {
        return addressTypes;
    }

    public void setAddressTypes(List<Long> addressTypes) {
        this.addressTypes = addressTypes;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getStreeNo() {
        return streeNo;
    }

    public void setStreeNo(String streeNo) {
        this.streeNo = streeNo;
    }

    public String getVillageTown() {
        return villageTown;
    }

    public void setVillageTown(String villageTown) {
        this.villageTown = villageTown;
    }

    public Long getTalukId() {
        return talukId;
    }

    public void setTalukId(Long talukId) {
        this.talukId = talukId;
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

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

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }

    @Override
    public String toString() {
        return "EntityAddress{" +
                "entityTypeEnum=" + entityTypeEnum +
                ", entityId=" + entityId +
                ", addressTypes=" + addressTypes +
                ", houseNo='" + houseNo + '\'' +
                ", streeNo='" + streeNo + '\'' +
                ", villageTown='" + villageTown + '\'' +
                ", talukId=" + talukId +
                ", addressLineOne='" + addressLineOne + '\'' +
                ", districtId=" + districtId +
                ", stateId=" + stateId +
                ", countryId=" + countryId +
                ", postalCode='" + postalCode + '\'' +
                ", locale='" + locale + '\'' +
                ", dateFormat='" + dateFormat + '\'' +
                ", addressLineTwo='" + addressLineTwo + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.entityTypeEnum);
        dest.writeValue(this.entityId);
        dest.writeList(this.addressTypes);
        dest.writeString(this.houseNo);
        dest.writeString(this.streeNo);
        dest.writeString(this.villageTown);
        dest.writeValue(this.talukId);
        dest.writeString(this.addressLineOne);
        dest.writeValue(this.districtId);
        dest.writeValue(this.stateId);
        dest.writeValue(this.countryId);
        dest.writeString(this.postalCode);
        dest.writeString(this.locale);
        dest.writeString(this.dateFormat);
        dest.writeString(this.addressLineTwo);
    }

    public EntityAddress() {
    }

    protected EntityAddress(Parcel in) {
        this.entityTypeEnum = (Integer) in.readValue(Integer.class.getClassLoader());
        this.entityId = (Long) in.readValue(Long.class.getClassLoader());
        this.addressTypes = new ArrayList<Long>();
        in.readList(this.addressTypes, List.class.getClassLoader());
        this.houseNo = in.readString();
        this.streeNo = in.readString();
        this.villageTown = in.readString();
        this.talukId = (Long) in.readValue(Long.class.getClassLoader());
        this.addressLineOne = in.readString();
        this.districtId = (Long) in.readValue(Long.class.getClassLoader());
        this.stateId = (Long) in.readValue(Long.class.getClassLoader());
        this.countryId = (Long) in.readValue(Long.class.getClassLoader());
        this.postalCode = in.readString();
        this.locale = in.readString();
        this.dateFormat = in.readString();
        this.addressLineTwo = in.readString();
    }

    public static final Creator<EntityAddress> CREATOR = new Creator<EntityAddress>() {
        public EntityAddress createFromParcel(Parcel source) {
            return new EntityAddress(source);
        }

        public EntityAddress[] newArray(int size) {
            return new EntityAddress[size];
        }
    };
}
