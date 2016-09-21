package com.conflux.finflux.createClient.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jagadeeshakn on 8/11/2016.
 */
public class StaffOption implements Parcelable {
    private Integer id;
    private String displayName;
    private Map<String, Object> staffOption = new HashMap<String, Object>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Map<String, Object> getStaffOption() {
        return staffOption;
    }

    public void setStaffOption(Map<String, Object> staffOption) {
        this.staffOption = staffOption;
    }

    @Override
    public String toString() {
        return "StaffOption{" +
                "id=" + id +
                ", displayName='" + displayName + '\'' +
                ", staffOption=" + staffOption +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.displayName);

    }

    public StaffOption() {
    }

    protected StaffOption(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.displayName = in.readString();

    }

    public static final Creator<StaffOption> CREATOR = new Creator<StaffOption>() {
        public StaffOption createFromParcel(Parcel source) {
            return new StaffOption(source);
        }

        public StaffOption[] newArray(int size) {
            return new StaffOption[size];
        }
    };
}
