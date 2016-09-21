package com.conflux.finflux.createClient.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jagadeeshakn on 8/20/2016.
 */
public class ClientPayloadData implements Parcelable {

    private Long officeId;
    private Long staffId;
    private String firstname;
    private String lastname;
    private Object middlename;
    private String mobileNo;
    private Long genderId;
    private Object clientTypeId;
    private boolean active;
    private String locale = "en";
    private String dateFormat = "dd MMMM yyyy";
    private Object activationDate;
    private String submittedOnDate;
    private String dateOfBirth;
    private Long savingsProductId;
    private List<EntityAddress> addresses = new ArrayList<EntityAddress>();

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Object getMiddlename() {
        return middlename;
    }

    public void setMiddlename(Object middlename) {
        this.middlename = middlename;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Long getGenderId() {
        return genderId;
    }

    public void setGenderId(Long genderId) {
        this.genderId = genderId;
    }

    public Object getClientTypeId() {
        return clientTypeId;
    }

    public void setClientTypeId(Object clientTypeId) {
        this.clientTypeId = clientTypeId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public Object getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Object activationDate) {
        this.activationDate = activationDate;
    }

    public String getSubmittedOnDate() {
        return submittedOnDate;
    }

    public void setSubmittedOnDate(String submittedOnDate) {
        this.submittedOnDate = submittedOnDate;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getSavingsProductId() {
        return savingsProductId;
    }

    public void setSavingsProductId(Long savingsProductId) {
        this.savingsProductId = savingsProductId;
    }

    public List<EntityAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<EntityAddress> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "ClientPayloadData{" +
                "officeId=" + officeId +
                ", staffId=" + staffId +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", middlename=" + middlename +
                ", mobileNo='" + mobileNo + '\'' +
                ", genderId=" + genderId +
                ", clientTypeId=" + clientTypeId +
                ", active=" + active +
                ", locale='" + locale + '\'' +
                ", dateFormat='" + dateFormat + '\'' +
                ", activationDate=" + activationDate +
                ", submittedOnDate='" + submittedOnDate + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", savingsProductId=" + savingsProductId +
                ", addresses=" + addresses +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.officeId);
        dest.writeValue(this.staffId);
        dest.writeString(this.firstname);
        dest.writeString(this.lastname);
        dest.writeString((String) this.middlename);
        dest.writeString(this.mobileNo);
        dest.writeValue(this.genderId);
        dest.writeValue(this.clientTypeId);
        dest.writeByte(active ? (byte) 1 : (byte) 0);
        dest.writeString(this.locale);
        dest.writeString(this.dateFormat);
        dest.writeString((String)this.activationDate);
        dest.writeString(this.submittedOnDate);
        dest.writeString(this.dateOfBirth);
        dest.writeValue(this.savingsProductId);
        dest.writeTypedList(addresses);
    }

    public ClientPayloadData() {
    }

    protected ClientPayloadData(Parcel in) {
        this.officeId = (Long) in.readValue(Long.class.getClassLoader());
        this.staffId = (Long) in.readValue(Long.class.getClassLoader());
        this.firstname = in.readString();
        this.lastname = in.readString();
        this.middlename = in.readParcelable(Object.class.getClassLoader());
        this.mobileNo = in.readString();
        this.genderId = (Long) in.readValue(Long.class.getClassLoader());
        this.clientTypeId = in.readParcelable(Object.class.getClassLoader());
        this.active = in.readByte() != 0;
        this.locale = in.readString();
        this.dateFormat = in.readString();
        this.activationDate = in.readParcelable(Object.class.getClassLoader());
        this.submittedOnDate = in.readString();
        this.dateOfBirth = in.readString();
        this.savingsProductId = (Long) in.readValue(Long.class.getClassLoader());
        this.addresses = in.createTypedArrayList(EntityAddress.CREATOR);
    }

    public static final Creator<ClientPayloadData> CREATOR = new Creator<ClientPayloadData>() {
        public ClientPayloadData createFromParcel(Parcel source) {
            return new ClientPayloadData(source);
        }

        public ClientPayloadData[] newArray(int size) {
            return new ClientPayloadData[size];
        }
    };
}
