package com.conflux.finflux.collectionSheet.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jagadeeshakn on 7/11/2016.
 */
public class MeetingFallCenter implements Parcelable {

    private Long id;

    private String accountNo;

    private String name;

    private Integer officeId;

    private Long staffId;

    private String staffName;

    private String hierarchy;

    private Status status;

    private Boolean active;

    int isSynced;

    private List<Integer> activationDate = new ArrayList<Integer>();

    private CollectionMeetingCalendar collectionMeetingCalendar;

    private Double totalCollected;

    private Double totalOverdue;

    private Double totaldue;

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

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
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

    public CollectionMeetingCalendar getCollectionMeetingCalendar() {
        return collectionMeetingCalendar;
    }

    public void setCollectionMeetingCalendar(CollectionMeetingCalendar collectionMeetingCalendar) {
        this.collectionMeetingCalendar = collectionMeetingCalendar;
    }

    public Double getTotalCollected() {
        return totalCollected;
    }

    public void setTotalCollected(Double totalCollected) {
        this.totalCollected = totalCollected;
    }

    public Double getTotalOverdue() {
        return totalOverdue;
    }

    public void setTotalOverdue(Double totalOverdue) {
        this.totalOverdue = totalOverdue;
    }

    public Double getTotaldue() {
        return totaldue;
    }

    public void setTotaldue(Double totaldue) {
        this.totaldue = totaldue;
    }

    public int getIsSynced() {
        return isSynced;
    }

    public void setIsSynced(int isSynced) {

        this.isSynced = isSynced;
    }

    @Override
    public String toString() {
        return "MeetingFallCenter{" +
                "id=" + id +
                ", accountNo='" + accountNo + '\'' +
                ", name='" + name + '\'' +
                ", officeId=" + officeId +
                ", staffId=" + staffId +
                ", staffName='" + staffName + '\'' +
                ", hierarchy='" + hierarchy + '\'' +
                ", status=" + status +
                ", active=" + active +
                ", isSynced=" + isSynced +
                ", activationDate=" + activationDate +
                ", collectionMeetingCalendar=" + collectionMeetingCalendar +
                ", totalCollected=" + totalCollected +
                ", totalOverdue=" + totalOverdue +
                ", totaldue=" + totaldue +
                '}';
    }

    public MeetingFallCenter() {
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
        dest.writeValue(this.officeId);
        dest.writeValue(this.staffId);
        dest.writeString(this.staffName);
        dest.writeString(this.hierarchy);
        dest.writeParcelable(this.status, 0);
        dest.writeValue(this.active);
        dest.writeInt(this.isSynced);
        dest.writeList(this.activationDate);
        dest.writeParcelable(this.collectionMeetingCalendar, 0);
        dest.writeValue(this.totalCollected);
        dest.writeValue(this.totalOverdue);
        dest.writeValue(this.totaldue);
    }

    protected MeetingFallCenter(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.accountNo = in.readString();
        this.name = in.readString();
        this.officeId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.staffId = (Long) in.readValue(Long.class.getClassLoader());
        this.staffName = in.readString();
        this.hierarchy = in.readString();
        this.status = in.readParcelable(Status.class.getClassLoader());
        this.active = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.isSynced = in.readInt();
        this.activationDate = new ArrayList<Integer>();
        in.readList(this.activationDate, List.class.getClassLoader());
        this.collectionMeetingCalendar = in.readParcelable(CollectionMeetingCalendar.class.getClassLoader());
        this.totalCollected = (Double) in.readValue(Double.class.getClassLoader());
        this.totalOverdue = (Double) in.readValue(Double.class.getClassLoader());
        this.totaldue = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Creator<MeetingFallCenter> CREATOR = new Creator<MeetingFallCenter>() {
        public MeetingFallCenter createFromParcel(Parcel source) {
            return new MeetingFallCenter(source);
        }

        public MeetingFallCenter[] newArray(int size) {
            return new MeetingFallCenter[size];
        }
    };
}
