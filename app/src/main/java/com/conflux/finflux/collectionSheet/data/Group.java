package com.conflux.finflux.collectionSheet.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jagadeeshakn on 7/11/2016.
 */
public class Group implements Parcelable {

    private Long groupId;

    private String groupName;

    private Long staffId;

    private String staffName;

    private Long levelId;

    private String levelName;

    private List<Client> clients = new ArrayList<Client>();

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", staffId=" + staffId +
                ", staffName='" + staffName + '\'' +
                ", levelId=" + levelId +
                ", levelName='" + levelName + '\'' +
                ", clients=" + clients +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.groupId);
        dest.writeString(this.groupName);
        dest.writeValue(this.staffId);
        dest.writeString(this.staffName);
        dest.writeValue(this.levelId);
        dest.writeString(this.levelName);
        dest.writeTypedList(clients);
    }

    public Group() {
    }

    protected Group(Parcel in) {
        this.groupId = (Long) in.readValue(Long.class.getClassLoader());
        this.groupName = in.readString();
        this.staffId = (Long) in.readValue(Long.class.getClassLoader());
        this.staffName = in.readString();
        this.levelId = (Long) in.readValue(Long.class.getClassLoader());
        this.levelName = in.readString();
        this.clients = in.createTypedArrayList(Client.CREATOR);
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
