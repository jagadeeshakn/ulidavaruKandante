package com.conflux.finflux.collectionSheet.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jagadeeshakn on 7/11/2016.
 */
public class ProductiveCollectionData implements Parcelable{

    private Long staffId;

    private String staffName;

    private List<MeetingFallCenter> meetingFallCenters = new ArrayList<MeetingFallCenter>();

    public ProductiveCollectionData(Long staffId, String staffName, List<MeetingFallCenter> meetingFallCenters) {
        this.staffId = staffId;
        this.staffName = staffName;
        this.meetingFallCenters = meetingFallCenters;
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

    public List<MeetingFallCenter> getMeetingFallCenters() {
        return meetingFallCenters;
    }

    public void setMeetingFallCenters(List<MeetingFallCenter> meetingFallCenters) {
        this.meetingFallCenters = meetingFallCenters;
    }

    @Override
    public String toString() {
        return "ProductiveCollectionData{" +
                "staffId=" + staffId +
                ", staffName='" + staffName + '\'' +
                ", meetingFallCenters=" + meetingFallCenters +
                '}';
    }


    public ProductiveCollectionData() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.staffId);
        dest.writeString(this.staffName);
        dest.writeTypedList(this.meetingFallCenters);
    }

    protected ProductiveCollectionData(Parcel in) {
        this.staffId = (Long) in.readValue(Long.class.getClassLoader());
        this.staffName = in.readString();
        this.meetingFallCenters = in.createTypedArrayList(MeetingFallCenter.CREATOR);
    }

    public static final Creator<ProductiveCollectionData> CREATOR = new Creator<ProductiveCollectionData>() {
        @Override
        public ProductiveCollectionData createFromParcel(Parcel source) {
            return new ProductiveCollectionData(source);
        }

        @Override
        public ProductiveCollectionData[] newArray(int size) {
            return new ProductiveCollectionData[size];
        }
    };
}
