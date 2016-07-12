package com.conflux.finflux.finflux.collectionSheet.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jagadeeshakn on 7/11/2016.
 */
public class Center implements Parcelable {

    private Long id;
    private String name;
    private String externalId;
    private Long officeId;
    private String officeName;
    private Long staffId;
    private String staffName;
    private String hierarchy;
    private Status status;
    private Boolean active;
    private List<Integer> activationDate = new ArrayList<Integer>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(String hierarchy) {
        this.hierarchy = hierarchy;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Integer> getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(List<Integer> activationDate) {
        this.activationDate = activationDate;
    }

    @Override
    public String toString() {
        return "Center{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", externalId='" + externalId + '\'' +
                ", officeId=" + officeId +
                ", officeName='" + officeName + '\'' +
                ", staffId=" + staffId +
                ", staffName='" + staffName + '\'' +
                ", hierarchy='" + hierarchy + '\'' +
                ", status=" + status +
                ", active=" + active +
                ", activationDate=" + activationDate +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.externalId);
        dest.writeValue(this.officeId);
        dest.writeString(this.officeName);
        dest.writeValue(this.staffId);
        dest.writeString(this.staffName);
        dest.writeString(this.hierarchy);
        dest.writeParcelable(this.status, 0);
        dest.writeValue(this.active);
        dest.writeList(this.activationDate);
    }

    public Center() {
    }

    protected Center(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.externalId = in.readString();
        this.officeId = (Long) in.readValue(Long.class.getClassLoader());
        this.officeName = in.readString();
        this.staffId = (Long) in.readValue(Long.class.getClassLoader());
        this.staffName = in.readString();
        this.hierarchy = in.readString();
        this.status = in.readParcelable(Status.class.getClassLoader());
        this.active = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.activationDate = new ArrayList<Integer>();
        in.readList(this.activationDate, List.class.getClassLoader());
    }

    public static final Creator<Center> CREATOR = new Creator<Center>() {
        public Center createFromParcel(Parcel source) {
            return new Center(source);
        }

        public Center[] newArray(int size) {
            return new Center[size];
        }
    };
}
