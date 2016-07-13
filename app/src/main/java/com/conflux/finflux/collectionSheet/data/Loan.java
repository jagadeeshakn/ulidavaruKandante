package com.conflux.finflux.collectionSheet.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jagadeeshakn on 7/11/2016.
 */
public class Loan implements Parcelable {

    private Double disbursementAmount;
    private Double interestDue;
    private Double interestPaid;
    private Long loanId;
    private Double chargesDue;
    public Double totalDue;
    private Double collectedDue;
    private Double principalDue;
    private Double principalPaid;
    private String accountId;
    private int accountStatusId;
    public String productShortName;
    private Long productId;
    private Long currencyId;
    private Long clientId;
    private Currency currency;
    private Client client;
    private String isPaymentChanged;

    public Double getDisbursementAmount() {
        return disbursementAmount;
    }

    public void setDisbursementAmount(Double disbursementAmount) {
        this.disbursementAmount = disbursementAmount;
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

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Double getChargesDue() {
        return chargesDue;
    }

    public void setChargesDue(Double chargesDue) {
        this.chargesDue = chargesDue;
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

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getAccountStatusId() {
        return accountStatusId;
    }

    public void setAccountStatusId(int accountStatusId) {
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

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getIsPaymentChanged() {
        return isPaymentChanged;
    }

    public void setIsPaymentChanged(String isPaymentChanged) {
        this.isPaymentChanged = isPaymentChanged;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "disbursementAmount=" + disbursementAmount +
                ", interestDue=" + interestDue +
                ", interestPaid=" + interestPaid +
                ", loanId=" + loanId +
                ", chargesDue=" + chargesDue +
                ", totalDue=" + totalDue +
                ", collectedDue=" + collectedDue +
                ", principalDue=" + principalDue +
                ", principalPaid=" + principalPaid +
                ", accountId='" + accountId + '\'' +
                ", accountStatusId=" + accountStatusId +
                ", productShortName='" + productShortName + '\'' +
                ", productId=" + productId +
                ", currencyId=" + currencyId +
                ", clientId=" + clientId +
                ", currency=" + currency +
                ", client=" + client +
                ", isPaymentChanged='" + isPaymentChanged + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.disbursementAmount);
        dest.writeValue(this.interestDue);
        dest.writeValue(this.interestPaid);
        dest.writeValue(this.loanId);
        dest.writeValue(this.chargesDue);
        dest.writeValue(this.totalDue);
        dest.writeValue(this.collectedDue);
        dest.writeValue(this.principalDue);
        dest.writeValue(this.principalPaid);
        dest.writeString(this.accountId);
        dest.writeInt(this.accountStatusId);
        dest.writeString(this.productShortName);
        dest.writeValue(this.productId);
        dest.writeValue(this.currencyId);
        dest.writeValue(this.clientId);
        dest.writeParcelable(this.currency, 0);
        dest.writeParcelable(this.client, 0);
        dest.writeString(this.isPaymentChanged);
    }

    public Loan() {
    }

    protected Loan(Parcel in) {
        this.disbursementAmount = (Double) in.readValue(Double.class.getClassLoader());
        this.interestDue = (Double) in.readValue(Double.class.getClassLoader());
        this.interestPaid = (Double) in.readValue(Double.class.getClassLoader());
        this.loanId = (Long) in.readValue(Long.class.getClassLoader());
        this.chargesDue = (Double) in.readValue(Double.class.getClassLoader());
        this.totalDue = (Double) in.readValue(Double.class.getClassLoader());
        this.collectedDue = (Double) in.readValue(Double.class.getClassLoader());
        this.principalDue = (Double) in.readValue(Double.class.getClassLoader());
        this.principalPaid = (Double) in.readValue(Double.class.getClassLoader());
        this.accountId = in.readString();
        this.accountStatusId = in.readInt();
        this.productShortName = in.readString();
        this.productId = (Long) in.readValue(Long.class.getClassLoader());
        this.currencyId = (Long) in.readValue(Long.class.getClassLoader());
        this.clientId = (Long) in.readValue(Long.class.getClassLoader());
        this.currency = in.readParcelable(Currency.class.getClassLoader());
        this.client = in.readParcelable(Client.class.getClassLoader());
        this.isPaymentChanged = in.readString();
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
