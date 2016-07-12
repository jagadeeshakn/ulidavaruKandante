package com.conflux.finflux.finflux.collectionSheet.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.dsl.Ignore;

import java.util.ArrayList;

/**
 * Created by jagadeeshakn on 7/11/2016.
 */
public class FinfluxGroup implements Parcelable {

    public int staffId;
    public String staffName;
    public int levelId;
    public String levelName;
    private Double totalDue;
    private Double collectedDue;
    long groupId;
    String groupName;
    long centerId;
    ArrayList<Client> clients;

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Double getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(Double totalDue) {
        this.totalDue = totalDue;
    }

    public Double getCollectedDue() {
        return collectedDue;
    }

    public void setCollectedDue(Double collectedDue) {
        this.collectedDue = collectedDue;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public long getCenterId() {
        return centerId;
    }

    public void setCenterId(long centerId) {
        this.centerId = centerId;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "FinfluxGroup{" +
                "staffId=" + staffId +
                ", staffName='" + staffName + '\'' +
                ", levelId=" + levelId +
                ", levelName='" + levelName + '\'' +
                ", totalDue=" + totalDue +
                ", collectedDue=" + collectedDue +
                ", groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", centerId=" + centerId +
                ", clients=" + clients +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.staffId);
        dest.writeString(this.staffName);
        dest.writeInt(this.levelId);
        dest.writeString(this.levelName);
        dest.writeValue(this.totalDue);
        dest.writeValue(this.collectedDue);
        dest.writeLong(this.groupId);
        dest.writeString(this.groupName);
        dest.writeLong(this.centerId);
        dest.writeTypedList(clients);
    }

    public FinfluxGroup() {
    }

    protected FinfluxGroup(Parcel in) {
        this.staffId = in.readInt();
        this.staffName = in.readString();
        this.levelId = in.readInt();
        this.levelName = in.readString();
        this.totalDue = (Double) in.readValue(Double.class.getClassLoader());
        this.collectedDue = (Double) in.readValue(Double.class.getClassLoader());
        this.groupId = in.readLong();
        this.groupName = in.readString();
        this.centerId = in.readLong();
        this.clients = in.createTypedArrayList(Client.CREATOR);
    }

    public static final Creator<FinfluxGroup> CREATOR = new Creator<FinfluxGroup>() {
        public FinfluxGroup createFromParcel(Parcel source) {
            return new FinfluxGroup(source);
        }

        public FinfluxGroup[] newArray(int size) {
            return new FinfluxGroup[size];
        }
    };
}
