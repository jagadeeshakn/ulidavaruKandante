package com.conflux.finflux.collectionSheet.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jagadeeshakn on 7/11/2016.
 */
public class CollectionSheetData implements Parcelable {

    private List<Integer> dueDate = new ArrayList<Integer>();
    private List<LoanProduct> loanProducts = new ArrayList<LoanProduct>();
    private List<Object> savingsProducts = new ArrayList<Object>();
    private List<Group> groups = new ArrayList<Group>();
    private List<CodeValue> attendanceTypeOptions = new ArrayList<CodeValue>();
    private List<PaymentTypeOption> paymentTypeOptions = new ArrayList<PaymentTypeOption>();

    public List<Integer> getDueDate() {
        return dueDate;
    }

    public void setDueDate(List<Integer> dueDate) {
        this.dueDate = dueDate;
    }

    public List<LoanProduct> getLoanProducts() {
        return loanProducts;
    }

    public void setLoanProducts(List<LoanProduct> loanProducts) {
        this.loanProducts = loanProducts;
    }

    public List<Object> getSavingsProducts() {
        return savingsProducts;
    }

    public void setSavingsProducts(List<Object> savingsProducts) {
        this.savingsProducts = savingsProducts;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<CodeValue> getAttendanceTypeOptions() {
        return attendanceTypeOptions;
    }

    public void setAttendanceTypeOptions(List<CodeValue> attendanceTypeOptions) {
        this.attendanceTypeOptions = attendanceTypeOptions;
    }

    public List<PaymentTypeOption> getPaymentTypeOptions() {
        return paymentTypeOptions;
    }

    public void setPaymentTypeOptions(List<PaymentTypeOption> paymentTypeOptions) {
        this.paymentTypeOptions = paymentTypeOptions;
    }

    @Override
    public String toString() {
        return "CollectionSheetData{" +
                "dueDate=" + dueDate +
                ", loanProducts=" + loanProducts +
                ", savingsProducts=" + savingsProducts +
                ", groups=" + groups +
                ", attendanceTypeOptions=" + attendanceTypeOptions +
                ", paymentTypeOptions=" + paymentTypeOptions +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.dueDate);
        dest.writeTypedList(loanProducts);
        dest.writeList(this.savingsProducts);
        dest.writeTypedList(groups);
        dest.writeTypedList(attendanceTypeOptions);
        dest.writeTypedList(paymentTypeOptions);
    }

    public CollectionSheetData() {
    }

    protected CollectionSheetData(Parcel in) {
        this.dueDate = new ArrayList<Integer>();
        in.readList(this.dueDate, List.class.getClassLoader());
        this.loanProducts = in.createTypedArrayList(LoanProduct.CREATOR);
        this.savingsProducts = new ArrayList<Object>();
        in.readList(this.savingsProducts, List.class.getClassLoader());
        this.groups = in.createTypedArrayList(Group.CREATOR);
        this.attendanceTypeOptions = in.createTypedArrayList(CodeValue.CREATOR);
        this.paymentTypeOptions = in.createTypedArrayList(PaymentTypeOption.CREATOR);
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
