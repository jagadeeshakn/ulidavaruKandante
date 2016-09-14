package com.conflux.finflux.collectionSheet.data;

import android.os.Parcel;
import android.os.Parcelable;
/*
 * Created by Praveen J U on 7/27/2016.
 */
public class BulkRepaymentTransactions implements Parcelable {
    private Long loanId;
    private double transactionAmount;

    private BulkRepaymentTransactions(final Long loanId, final double transactionAmount){
        this.loanId = loanId;
        this.transactionAmount = transactionAmount;
    }

    public static BulkRepaymentTransactions instance(final Long loanId, final double transactionAmount){
        return new BulkRepaymentTransactions(loanId, transactionAmount);
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    @Override
    public String toString() {
        return "BulkRepaymentTransactions{" +
                "loanId=" + loanId +
                ", transactionAmount=" + transactionAmount +
                '}';
    }

    public BulkRepaymentTransactions() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.loanId);
        dest.writeDouble(this.transactionAmount);
    }

    protected BulkRepaymentTransactions(Parcel in) {
        this.loanId = (Long) in.readValue(Long.class.getClassLoader());
        this.transactionAmount = in.readDouble();
    }

    public static final Creator<BulkRepaymentTransactions> CREATOR = new Creator<BulkRepaymentTransactions>() {
        @Override
        public BulkRepaymentTransactions createFromParcel(Parcel source) {
            return new BulkRepaymentTransactions(source);
        }

        @Override
        public BulkRepaymentTransactions[] newArray(int size) {
            return new BulkRepaymentTransactions[size];
        }
    };
}
