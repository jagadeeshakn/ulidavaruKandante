package com.conflux.finflux.finflux.collectionSheet.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jagadeeshakn on 7/11/2016.
 */
public class Group implements Parcelable {

    private Long id;
    private String accountNo;
    private String name;
    private String externalId;

    private Status status;
    @SuppressWarnings("unused")
    private Boolean active;
    private ArrayList<Integer> activationDate;

    private Integer officeId;
    private String officeName;
    private Long centerId;
    private String centerName;
    private Long staffId;
    private String staffName;
    private String hierarchy;
    private String groupLevel;

    // associations
    private ArrayList<Client> clientMembers;
    private ArrayList<Client> activeClientMembers;

    // template
    private ArrayList<Center> centerOptions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
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

    public ArrayList<Integer> getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(ArrayList<Integer> activationDate) {
        this.activationDate = activationDate;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public Long getCenterId() {
        return centerId;
    }

    public void setCenterId(Long centerId) {
        this.centerId = centerId;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
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

    public String getGroupLevel() {
        return groupLevel;
    }

    public void setGroupLevel(String groupLevel) {
        this.groupLevel = groupLevel;
    }

    public ArrayList<Client> getClientMembers() {
        return clientMembers;
    }

    public void setClientMembers(ArrayList<Client> clientMembers) {
        this.clientMembers = clientMembers;
    }

    public ArrayList<Client> getActiveClientMembers() {
        return activeClientMembers;
    }

    public void setActiveClientMembers(ArrayList<Client> activeClientMembers) {
        this.activeClientMembers = activeClientMembers;
    }

    public ArrayList<Center> getCenterOptions() {
        return centerOptions;
    }

    public void setCenterOptions(ArrayList<Center> centerOptions) {
        this.centerOptions = centerOptions;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", accountNo='" + accountNo + '\'' +
                ", name='" + name + '\'' +
                ", externalId='" + externalId + '\'' +
                ", status=" + status +
                ", active=" + active +
                ", activationDate=" + activationDate +
                ", officeId=" + officeId +
                ", officeName='" + officeName + '\'' +
                ", centerId=" + centerId +
                ", centerName='" + centerName + '\'' +
                ", staffId=" + staffId +
                ", staffName='" + staffName + '\'' +
                ", hierarchy='" + hierarchy + '\'' +
                ", groupLevel='" + groupLevel + '\'' +
                ", clientMembers=" + clientMembers +
                ", activeClientMembers=" + activeClientMembers +
                ", centerOptions=" + centerOptions +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.accountNo);
        dest.writeString(this.name);
        dest.writeString(this.externalId);
        dest.writeParcelable(this.status, 0);
        dest.writeValue(this.active);
        dest.writeList(this.activationDate);
        dest.writeValue(this.officeId);
        dest.writeString(this.officeName);
        dest.writeValue(this.centerId);
        dest.writeString(this.centerName);
        dest.writeValue(this.staffId);
        dest.writeString(this.staffName);
        dest.writeString(this.hierarchy);
        dest.writeString(this.groupLevel);
        dest.writeTypedList(clientMembers);
        dest.writeTypedList(activeClientMembers);
        dest.writeTypedList(centerOptions);
    }

    public Group() {
    }

    protected Group(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.accountNo = in.readString();
        this.name = in.readString();
        this.externalId = in.readString();
        this.status = in.readParcelable(Status.class.getClassLoader());
        this.active = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.activationDate = new ArrayList<Integer>();
        in.readList(this.activationDate, List.class.getClassLoader());
        this.officeId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.officeName = in.readString();
        this.centerId = (Long) in.readValue(Long.class.getClassLoader());
        this.centerName = in.readString();
        this.staffId = (Long) in.readValue(Long.class.getClassLoader());
        this.staffName = in.readString();
        this.hierarchy = in.readString();
        this.groupLevel = in.readString();
        this.clientMembers = in.createTypedArrayList(Client.CREATOR);
        this.activeClientMembers = in.createTypedArrayList(Client.CREATOR);
        this.centerOptions = in.createTypedArrayList(Center.CREATOR);
    }

    public static final Creator<Group> CREATOR = new Creator<Group>() {
        public Group createFromParcel(Parcel source) {
            return new Group(source);
        }

        public Group[] newArray(int size) {
            return new Group[size];
        }
    };
}
