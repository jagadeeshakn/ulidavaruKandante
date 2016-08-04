package com.conflux.finflux.collectionSheet.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jagadeeshakn on 7/11/2016.
 */
public class Loan implements Parcelable {

    private Long loanId;

    private String accountId;

    private Long accountStatusId;

    private String productShortName;

    private Long productId;

    private Currency currency;

    private Double principalDue;

    private Double principalPaid;

    private Double interestDue;

    private Double interestPaid;

    private Double totalDue;

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Long getAccountStatusId() {
        return accountStatusId;
    }

    public void setAccountStatusId(Long accountStatusId) {
        this.accountStatusId = accountStatusId;
    }

    public String getProductShortName() {
        return productShortName;
    }

    public void setProductShortName(String productShortName) {
        this.productShortName = productShortName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Double getPrincipalDue() {
        return principalDue;
    }

    public void setPrincipalDue(Double principalDue) {
        this.principalDue = principalDue;
    }

    public Double getPrincipalPaid() {
        return principalPaid;
    }

    public void setPrincipalPaid(Double principalPaid) {
        this.principalPaid = principalPaid;
    }

    public Double getInterestDue() {
        return interestDue;
    }

    public void setInterestDue(Double interestDue) {
        this.interestDue = interestDue;
    }

    public Double getInterestPaid() {
        return interestPaid;
    }

    public void setInterestPaid(Double interestPaid) {
        this.interestPaid = interestPaid;
    }

    public Double getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(Double totalDue) {
        this.totalDue = totalDue;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", accountId='" + accountId + '\'' +
                ", accountStatusId=" + accountStatusId +
                ", productShortName='" + productShortName + '\'' +
                ", productId=" + productId +
                ", currency=" + currency +
                ", principalDue=" + principalDue +
                ", principalPaid=" + principalPaid +
                ", interestDue=" + interestDue +
                ", interestPaid=" + interestPaid +
                ", totalDue=" + totalDue +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.loanId);
        dest.writeString(this.accountId);
        dest.writeValue(this.accountStatusId);
        dest.writeString(this.productShortName);
        dest.writeValue(this.productId);
        dest.writeParcelable(this.currency, 0);
        dest.writeValue(this.principalDue);
        dest.writeValue(this.principalPaid);
        dest.writeValue(this.interestDue);
        dest.writeValue(this.interestPaid);
        dest.writeValue(this.totalDue);
    }

    public Loan() {
    }

    protected Loan(Parcel in) {
        this.loanId = (Long) in.readValue(Long.class.getClassLoader());
        this.accountId = in.readString();
        this.accountStatusId = (Long) in.readValue(Long.class.getClassLoader());
        this.productShortName = in.readString();
        this.productId = (Long) in.readValue(Long.class.getClassLoader());
        this.currency = in.readParcelable(Currency.class.getClassLoader());
        this.principalDue = (Double) in.readValue(Double.class.getClassLoader());
        this.principalPaid = (Double) in.readValue(Double.class.getClassLoader());
        this.interestDue = (Double) in.readValue(Double.class.getClassLoader());
        this.interestPaid = (Double) in.readValue(Double.class.getClassLoader());
        this.totalDue = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Creator<Loan> CREATOR = new Creator<Loan>() {
        public Loan createFromParcel(Parcel source) {
            return new Loan(source);
        }

        public Loan[] newArray(int size) {
            return new Loan[size];
        }
    };
}
