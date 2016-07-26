package com.conflux.finflux.collectionSheet.data;

import android.os.Parcel;

/**
 * Created by Praveen J U on 7/17/2016.
 */
public class ExtendedProductionCollectiondata extends ProductiveCollectionData {

    private String meetingDate;
    private int TotalNumberOfCenters;

    public ExtendedProductionCollectiondata(final ProductiveCollectionData productiveCollectionData,final String meetingDate, final int totalNumberOfCenters) {
        super(productiveCollectionData.getStaffId(),productiveCollectionData.getStaffName(),productiveCollectionData.getMeetingFallCenters());
        this.meetingDate = meetingDate;
        TotalNumberOfCenters = totalNumberOfCenters;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public int getTotalNumberOfCenters() {
        return TotalNumberOfCenters;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.meetingDate);
        dest.writeInt(this.TotalNumberOfCenters);
    }

    public ExtendedProductionCollectiondata() {
    }

    protected ExtendedProductionCollectiondata(Parcel in) {
        super(in);
        this.meetingDate = in.readString();
        this.TotalNumberOfCenters = in.readInt();
    }

    public static final Creator<ExtendedProductionCollectiondata> CREATOR = new Creator<ExtendedProductionCollectiondata>() {
        @Override
        public ExtendedProductionCollectiondata createFromParcel(Parcel source) {
            return new ExtendedProductionCollectiondata(source);
        }

        @Override
        public ExtendedProductionCollectiondata[] newArray(int size) {
            return new ExtendedProductionCollectiondata[size];
        }
    };
}
