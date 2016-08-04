package com.conflux.finflux.collectionSheet.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jagadeeshakn on 7/27/2016.
 */
public class LoanProduct implements Parcelable {

    private Long id;

    private String name;

    private Boolean includeInBorrowerCycle;

    private Boolean useBorrowerCycle;

    private Currency currency;

    private Boolean isLinkedToFloatingInterestRates;

    private Boolean isFloatingInterestRateCalculationAllowed;

    private Boolean allowVariableInstallments;

    private Boolean isInterestRecalculationEnabled;

    private Boolean canDefineInstallmentAmount;

    private List<Object> principalVariationsForBorrowerCycle = new ArrayList<Object>();

    private List<Object> interestRateVariationsForBorrowerCycle = new ArrayList<Object>();

    private List<Object> numberOfRepaymentVariationsForBorrowerCycle = new ArrayList<Object>();

    private Boolean holdGuaranteeFunds;

    private Boolean accountMovesOutOfNPAOnlyOnArrearsCompletion;

    public Boolean getAccountMovesOutOfNPAOnlyOnArrearsCompletion() {
        return accountMovesOutOfNPAOnlyOnArrearsCompletion;
    }

    public void setAccountMovesOutOfNPAOnlyOnArrearsCompletion(Boolean accountMovesOutOfNPAOnlyOnArrearsCompletion) {
        this.accountMovesOutOfNPAOnlyOnArrearsCompletion = accountMovesOutOfNPAOnlyOnArrearsCompletion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIncludeInBorrowerCycle() {
        return includeInBorrowerCycle;
    }

    public void setIncludeInBorrowerCycle(Boolean includeInBorrowerCycle) {
        this.includeInBorrowerCycle = includeInBorrowerCycle;
    }

    public Boolean getUseBorrowerCycle() {
        return useBorrowerCycle;
    }

    public void setUseBorrowerCycle(Boolean useBorrowerCycle) {
        this.useBorrowerCycle = useBorrowerCycle;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Boolean getIsLinkedToFloatingInterestRates() {
        return isLinkedToFloatingInterestRates;
    }

    public void setIsLinkedToFloatingInterestRates(Boolean isLinkedToFloatingInterestRates) {
        this.isLinkedToFloatingInterestRates = isLinkedToFloatingInterestRates;
    }

    public Boolean getIsFloatingInterestRateCalculationAllowed() {
        return isFloatingInterestRateCalculationAllowed;
    }

    public void setIsFloatingInterestRateCalculationAllowed(Boolean isFloatingInterestRateCalculationAllowed) {
        this.isFloatingInterestRateCalculationAllowed = isFloatingInterestRateCalculationAllowed;
    }

    public Boolean getAllowVariableInstallments() {
        return allowVariableInstallments;
    }

    public void setAllowVariableInstallments(Boolean allowVariableInstallments) {
        this.allowVariableInstallments = allowVariableInstallments;
    }

    public Boolean getIsInterestRecalculationEnabled() {
        return isInterestRecalculationEnabled;
    }

    public void setIsInterestRecalculationEnabled(Boolean isInterestRecalculationEnabled) {
        this.isInterestRecalculationEnabled = isInterestRecalculationEnabled;
    }

    public Boolean getCanDefineInstallmentAmount() {
        return canDefineInstallmentAmount;
    }

    public void setCanDefineInstallmentAmount(Boolean canDefineInstallmentAmount) {
        this.canDefineInstallmentAmount = canDefineInstallmentAmount;
    }

    public List<Object> getPrincipalVariationsForBorrowerCycle() {
        return principalVariationsForBorrowerCycle;
    }

    public void setPrincipalVariationsForBorrowerCycle(List<Object> principalVariationsForBorrowerCycle) {
        this.principalVariationsForBorrowerCycle = principalVariationsForBorrowerCycle;
    }

    public List<Object> getInterestRateVariationsForBorrowerCycle() {
        return interestRateVariationsForBorrowerCycle;
    }

    public void setInterestRateVariationsForBorrowerCycle(List<Object> interestRateVariationsForBorrowerCycle) {
        this.interestRateVariationsForBorrowerCycle = interestRateVariationsForBorrowerCycle;
    }

    public List<Object> getNumberOfRepaymentVariationsForBorrowerCycle() {
        return numberOfRepaymentVariationsForBorrowerCycle;
    }

    public void setNumberOfRepaymentVariationsForBorrowerCycle(List<Object> numberOfRepaymentVariationsForBorrowerCycle) {
        this.numberOfRepaymentVariationsForBorrowerCycle = numberOfRepaymentVariationsForBorrowerCycle;
    }

    public Boolean getHoldGuaranteeFunds() {
        return holdGuaranteeFunds;
    }

    public void setHoldGuaranteeFunds(Boolean holdGuaranteeFunds) {
        this.holdGuaranteeFunds = holdGuaranteeFunds;
    }

    @Override
    public String toString() {
        return "LoanProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", includeInBorrowerCycle=" + includeInBorrowerCycle +
                ", useBorrowerCycle=" + useBorrowerCycle +
                ", currency=" + currency +
                ", isLinkedToFloatingInterestRates=" + isLinkedToFloatingInterestRates +
                ", isFloatingInterestRateCalculationAllowed=" + isFloatingInterestRateCalculationAllowed +
                ", allowVariableInstallments=" + allowVariableInstallments +
                ", isInterestRecalculationEnabled=" + isInterestRecalculationEnabled +
                ", canDefineInstallmentAmount=" + canDefineInstallmentAmount +
                ", principalVariationsForBorrowerCycle=" + principalVariationsForBorrowerCycle +
                ", interestRateVariationsForBorrowerCycle=" + interestRateVariationsForBorrowerCycle +
                ", numberOfRepaymentVariationsForBorrowerCycle=" + numberOfRepaymentVariationsForBorrowerCycle +
                ", holdGuaranteeFunds=" + holdGuaranteeFunds +
                ", accountMovesOutOfNPAOnlyOnArrearsCompletion=" + accountMovesOutOfNPAOnlyOnArrearsCompletion +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeValue(this.includeInBorrowerCycle);
        dest.writeValue(this.useBorrowerCycle);
        dest.writeParcelable(this.currency, 0);
        dest.writeValue(this.isLinkedToFloatingInterestRates);
        dest.writeValue(this.isFloatingInterestRateCalculationAllowed);
        dest.writeValue(this.allowVariableInstallments);
        dest.writeValue(this.isInterestRecalculationEnabled);
        dest.writeValue(this.canDefineInstallmentAmount);
        dest.writeList(this.principalVariationsForBorrowerCycle);
        dest.writeList(this.interestRateVariationsForBorrowerCycle);
        dest.writeList(this.numberOfRepaymentVariationsForBorrowerCycle);
        dest.writeValue(this.holdGuaranteeFunds);
        dest.writeValue(this.accountMovesOutOfNPAOnlyOnArrearsCompletion);
    }

    public LoanProduct() {
    }

    protected LoanProduct(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.includeInBorrowerCycle = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.useBorrowerCycle = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.currency = in.readParcelable(Currency.class.getClassLoader());
        this.isLinkedToFloatingInterestRates = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.isFloatingInterestRateCalculationAllowed = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.allowVariableInstallments = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.isInterestRecalculationEnabled = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.canDefineInstallmentAmount = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.principalVariationsForBorrowerCycle = new ArrayList<Object>();
        in.readList(this.principalVariationsForBorrowerCycle, List.class.getClassLoader());
        this.interestRateVariationsForBorrowerCycle = new ArrayList<Object>();
        in.readList(this.interestRateVariationsForBorrowerCycle, List.class.getClassLoader());
        this.numberOfRepaymentVariationsForBorrowerCycle = new ArrayList<Object>();
        in.readList(this.numberOfRepaymentVariationsForBorrowerCycle, List.class.getClassLoader());
        this.holdGuaranteeFunds = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.accountMovesOutOfNPAOnlyOnArrearsCompletion = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<LoanProduct> CREATOR = new Creator<LoanProduct>() {
        public LoanProduct createFromParcel(Parcel source) {
            return new LoanProduct(source);
        }

        public LoanProduct[] newArray(int size) {
            return new LoanProduct[size];
        }
    };
}
