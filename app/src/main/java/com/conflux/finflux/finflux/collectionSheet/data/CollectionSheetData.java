package com.conflux.finflux.finflux.collectionSheet.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.dsl.Ignore;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by jagadeeshakn on 7/11/2016.
 */
public class CollectionSheetData implements Parcelable {

    public int[] dueDate;


    public ArrayList<FinfluxGroup> groups;

    public ArrayList<Loan> loanProducts;
    public ArrayList<AttendanceType> attendanceTypeOptions;

    public int[] getDueDate() {
        return dueDate;
    }

    public void setDueDate(int[] dueDate) {
        this.dueDate = dueDate;
    }

    public ArrayList<FinfluxGroup> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<FinfluxGroup> groups) {
        this.groups = groups;
    }

    public ArrayList<Loan> getLoanProducts() {
        return loanProducts;
    }

    public void setLoanProducts(ArrayList<Loan> loanProducts) {
        this.loanProducts = loanProducts;
    }

    public ArrayList<AttendanceType> getAttendanceTypeOptions() {
        return attendanceTypeOptions;
    }

    public void setAttendanceTypeOptions(ArrayList<AttendanceType> attendanceTypeOptions) {
        this.attendanceTypeOptions = attendanceTypeOptions;
    }

    @Override
    public String toString() {
        return "CollectionSheetData{" +
                "dueDate=" + Arrays.toString(dueDate) +
                ", groups=" + groups +
                ", loanProducts=" + loanProducts +
                ", attendanceTypeOptions=" + attendanceTypeOptions +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeIntArray(this.dueDate);
        dest.writeTypedList(groups);
        dest.writeTypedList(loanProducts);
        dest.writeTypedList(attendanceTypeOptions);
    }

    public CollectionSheetData() {
    }

    protected CollectionSheetData(Parcel in) {
        this.dueDate = in.createIntArray();
        this.groups = in.createTypedArrayList(FinfluxGroup.CREATOR);
        this.loanProducts = in.createTypedArrayList(Loan.CREATOR);
        this.attendanceTypeOptions = in.createTypedArrayList(AttendanceType.CREATOR);
    }

    public static final Creator<CollectionSheetData> CREATOR = new Creator<CollectionSheetData>() {
        public CollectionSheetData createFromParcel(Parcel source) {
            return new CollectionSheetData(source);
        }

        public CollectionSheetData[] newArray(int size) {
            return new CollectionSheetData[size];
        }
    };
}
