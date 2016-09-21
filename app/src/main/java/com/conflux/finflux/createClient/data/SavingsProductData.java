package com.conflux.finflux.createClient.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.conflux.finflux.collectionSheet.data.CodeValue;
import com.conflux.finflux.collectionSheet.data.Currency;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by jagadeeshakn on 8/11/2016.
 */
public class SavingsProductData implements Parcelable {

    private Long id;
    private String name;
    private String shortName;
    private String description;
    private Currency currency;
    private BigDecimal nominalAnnualInterestRate;
    private CodeValue interestCompoundingPeriodType;
    private CodeValue interestPostingPeriodType;
    private CodeValue interestCalculationType;
    private CodeValue interestCalculationDaysInYearType;
    private BigDecimal minRequiredOpeningBalance;
    private Integer lockinPeriodFrequency;
    private CodeValue lockinPeriodFrequencyType;
    private boolean withdrawalFeeForTransfers;
    private boolean allowOverdraft;
    private BigDecimal overdraftLimit;
    private BigDecimal minRequiredBalance;
    private boolean enforceMinRequiredBalance;
    private BigDecimal minBalanceForInterestCalculation;

    // accounting
    private CodeValue accountingRule;
    private Map<String, Object> accountingMappings;
    /* private ArrayList<PaymentTypeToGLAccountMapper> paymentChannelToFundSourceMappings;
     private ArrayList<ChargeToGLAccountMapper> feeToIncomeAccountMappings;
     private ArrayList<ChargeToGLAccountMapper> penaltyToIncomeAccountMappings;
 */
    // charges
    private ArrayList<ChargeData> charges;

    // template
    private ArrayList<Currency> currencyOptions;
    private ArrayList<CodeValue> interestCompoundingPeriodTypeOptions;
    private ArrayList<CodeValue> interestPostingPeriodTypeOptions;
    private ArrayList<CodeValue> interestCalculationTypeOptions;
    private ArrayList<CodeValue> interestCalculationDaysInYearTypeOptions;
    private ArrayList<CodeValue> lockinPeriodFrequencyTypeOptions;
    private ArrayList<CodeValue> withdrawalFeeTypeOptions;
    // private ArrayList<PaymentTypeData> paymentTypeOptions;
    private ArrayList<CodeValue> accountingRuleOptions;
    private Map<String, ArrayList<GLAccountData>> accountingMappingOptions;
    private ArrayList<ChargeData> chargeOptions;
    private ArrayList<ChargeData> penaltyOptions;

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getNominalAnnualInterestRate() {
        return nominalAnnualInterestRate;
    }

    public void setNominalAnnualInterestRate(BigDecimal nominalAnnualInterestRate) {
        this.nominalAnnualInterestRate = nominalAnnualInterestRate;
    }

    public CodeValue getInterestCompoundingPeriodType() {
        return interestCompoundingPeriodType;
    }

    public void setInterestCompoundingPeriodType(CodeValue interestCompoundingPeriodType) {
        this.interestCompoundingPeriodType = interestCompoundingPeriodType;
    }

    public CodeValue getInterestPostingPeriodType() {
        return interestPostingPeriodType;
    }

    public void setInterestPostingPeriodType(CodeValue interestPostingPeriodType) {
        this.interestPostingPeriodType = interestPostingPeriodType;
    }

    public CodeValue getInterestCalculationType() {
        return interestCalculationType;
    }

    public void setInterestCalculationType(CodeValue interestCalculationType) {
        this.interestCalculationType = interestCalculationType;
    }

    public CodeValue getInterestCalculationDaysInYearType() {
        return interestCalculationDaysInYearType;
    }

    public void setInterestCalculationDaysInYearType(CodeValue interestCalculationDaysInYearType) {
        this.interestCalculationDaysInYearType = interestCalculationDaysInYearType;
    }

    public BigDecimal getMinRequiredOpeningBalance() {
        return minRequiredOpeningBalance;
    }

    public void setMinRequiredOpeningBalance(BigDecimal minRequiredOpeningBalance) {
        this.minRequiredOpeningBalance = minRequiredOpeningBalance;
    }

    public Integer getLockinPeriodFrequency() {
        return lockinPeriodFrequency;
    }

    public void setLockinPeriodFrequency(Integer lockinPeriodFrequency) {
        this.lockinPeriodFrequency = lockinPeriodFrequency;
    }

    public CodeValue getLockinPeriodFrequencyType() {
        return lockinPeriodFrequencyType;
    }

    public void setLockinPeriodFrequencyType(CodeValue lockinPeriodFrequencyType) {
        this.lockinPeriodFrequencyType = lockinPeriodFrequencyType;
    }

    public boolean isWithdrawalFeeForTransfers() {
        return withdrawalFeeForTransfers;
    }

    public void setWithdrawalFeeForTransfers(boolean withdrawalFeeForTransfers) {
        this.withdrawalFeeForTransfers = withdrawalFeeForTransfers;
    }

    public boolean isAllowOverdraft() {
        return allowOverdraft;
    }

    public void setAllowOverdraft(boolean allowOverdraft) {
        this.allowOverdraft = allowOverdraft;
    }

    public BigDecimal getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(BigDecimal overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public BigDecimal getMinRequiredBalance() {
        return minRequiredBalance;
    }

    public void setMinRequiredBalance(BigDecimal minRequiredBalance) {
        this.minRequiredBalance = minRequiredBalance;
    }

    public boolean isEnforceMinRequiredBalance() {
        return enforceMinRequiredBalance;
    }

    public void setEnforceMinRequiredBalance(boolean enforceMinRequiredBalance) {
        this.enforceMinRequiredBalance = enforceMinRequiredBalance;
    }

    public BigDecimal getMinBalanceForInterestCalculation() {
        return minBalanceForInterestCalculation;
    }

    public void setMinBalanceForInterestCalculation(BigDecimal minBalanceForInterestCalculation) {
        this.minBalanceForInterestCalculation = minBalanceForInterestCalculation;
    }

    public CodeValue getAccountingRule() {
        return accountingRule;
    }

    public void setAccountingRule(CodeValue accountingRule) {
        this.accountingRule = accountingRule;
    }

    public Map<String, Object> getAccountingMappings() {
        return accountingMappings;
    }

    public void setAccountingMappings(Map<String, Object> accountingMappings) {
        this.accountingMappings = accountingMappings;
    }

    public ArrayList<ChargeData> getCharges() {
        return charges;
    }

    public void setCharges(ArrayList<ChargeData> charges) {
        this.charges = charges;
    }

    public ArrayList<Currency> getCurrencyOptions() {
        return currencyOptions;
    }

    public void setCurrencyOptions(ArrayList<Currency> currencyOptions) {
        this.currencyOptions = currencyOptions;
    }

    public ArrayList<CodeValue> getInterestCompoundingPeriodTypeOptions() {
        return interestCompoundingPeriodTypeOptions;
    }

    public void setInterestCompoundingPeriodTypeOptions(ArrayList<CodeValue> interestCompoundingPeriodTypeOptions) {
        this.interestCompoundingPeriodTypeOptions = interestCompoundingPeriodTypeOptions;
    }

    public ArrayList<CodeValue> getInterestPostingPeriodTypeOptions() {
        return interestPostingPeriodTypeOptions;
    }

    public void setInterestPostingPeriodTypeOptions(ArrayList<CodeValue> interestPostingPeriodTypeOptions) {
        this.interestPostingPeriodTypeOptions = interestPostingPeriodTypeOptions;
    }

    public ArrayList<CodeValue> getInterestCalculationTypeOptions() {
        return interestCalculationTypeOptions;
    }

    public void setInterestCalculationTypeOptions(ArrayList<CodeValue> interestCalculationTypeOptions) {
        this.interestCalculationTypeOptions = interestCalculationTypeOptions;
    }

    public ArrayList<CodeValue> getInterestCalculationDaysInYearTypeOptions() {
        return interestCalculationDaysInYearTypeOptions;
    }

    public void setInterestCalculationDaysInYearTypeOptions(ArrayList<CodeValue> interestCalculationDaysInYearTypeOptions) {
        this.interestCalculationDaysInYearTypeOptions = interestCalculationDaysInYearTypeOptions;
    }

    public ArrayList<CodeValue> getLockinPeriodFrequencyTypeOptions() {
        return lockinPeriodFrequencyTypeOptions;
    }

    public void setLockinPeriodFrequencyTypeOptions(ArrayList<CodeValue> lockinPeriodFrequencyTypeOptions) {
        this.lockinPeriodFrequencyTypeOptions = lockinPeriodFrequencyTypeOptions;
    }

    public ArrayList<CodeValue> getWithdrawalFeeTypeOptions() {
        return withdrawalFeeTypeOptions;
    }

    public void setWithdrawalFeeTypeOptions(ArrayList<CodeValue> withdrawalFeeTypeOptions) {
        this.withdrawalFeeTypeOptions = withdrawalFeeTypeOptions;
    }

    public ArrayList<CodeValue> getAccountingRuleOptions() {
        return accountingRuleOptions;
    }

    public void setAccountingRuleOptions(ArrayList<CodeValue> accountingRuleOptions) {
        this.accountingRuleOptions = accountingRuleOptions;
    }

    public Map<String, ArrayList<GLAccountData>> getAccountingMappingOptions() {
        return accountingMappingOptions;
    }

    public void setAccountingMappingOptions(Map<String, ArrayList<GLAccountData>> accountingMappingOptions) {
        this.accountingMappingOptions = accountingMappingOptions;
    }

    public ArrayList<ChargeData> getChargeOptions() {
        return chargeOptions;
    }

    public void setChargeOptions(ArrayList<ChargeData> chargeOptions) {
        this.chargeOptions = chargeOptions;
    }

    public ArrayList<ChargeData> getPenaltyOptions() {
        return penaltyOptions;
    }

    public void setPenaltyOptions(ArrayList<ChargeData> penaltyOptions) {
        this.penaltyOptions = penaltyOptions;
    }

    @Override
    public String toString() {
        return "SavingsProductData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", description='" + description + '\'' +
                ", currency=" + currency +
                ", nominalAnnualInterestRate=" + nominalAnnualInterestRate +
                ", interestCompoundingPeriodType=" + interestCompoundingPeriodType +
                ", interestPostingPeriodType=" + interestPostingPeriodType +
                ", interestCalculationType=" + interestCalculationType +
                ", interestCalculationDaysInYearType=" + interestCalculationDaysInYearType +
                ", minRequiredOpeningBalance=" + minRequiredOpeningBalance +
                ", lockinPeriodFrequency=" + lockinPeriodFrequency +
                ", lockinPeriodFrequencyType=" + lockinPeriodFrequencyType +
                ", withdrawalFeeForTransfers=" + withdrawalFeeForTransfers +
                ", allowOverdraft=" + allowOverdraft +
                ", overdraftLimit=" + overdraftLimit +
                ", minRequiredBalance=" + minRequiredBalance +
                ", enforceMinRequiredBalance=" + enforceMinRequiredBalance +
                ", minBalanceForInterestCalculation=" + minBalanceForInterestCalculation +
                ", accountingRule=" + accountingRule +
                ", accountingMappings=" + accountingMappings +
                ", charges=" + charges +
                ", currencyOptions=" + currencyOptions +
                ", interestCompoundingPeriodTypeOptions=" + interestCompoundingPeriodTypeOptions +
                ", interestPostingPeriodTypeOptions=" + interestPostingPeriodTypeOptions +
                ", interestCalculationTypeOptions=" + interestCalculationTypeOptions +
                ", interestCalculationDaysInYearTypeOptions=" + interestCalculationDaysInYearTypeOptions +
                ", lockinPeriodFrequencyTypeOptions=" + lockinPeriodFrequencyTypeOptions +
                ", withdrawalFeeTypeOptions=" + withdrawalFeeTypeOptions +
                ", accountingRuleOptions=" + accountingRuleOptions +
                ", accountingMappingOptions=" + accountingMappingOptions +
                ", chargeOptions=" + chargeOptions +
                ", penaltyOptions=" + penaltyOptions +
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
        dest.writeString(this.shortName);
        dest.writeString(this.description);
        dest.writeParcelable(this.currency, 0);
        dest.writeSerializable(this.nominalAnnualInterestRate);
        dest.writeParcelable(this.interestCompoundingPeriodType, 0);
        dest.writeParcelable(this.interestPostingPeriodType, 0);
        dest.writeParcelable(this.interestCalculationType, 0);
        dest.writeParcelable(this.interestCalculationDaysInYearType, 0);
        dest.writeSerializable(this.minRequiredOpeningBalance);
        dest.writeValue(this.lockinPeriodFrequency);
        dest.writeParcelable(this.lockinPeriodFrequencyType, 0);
        dest.writeByte(withdrawalFeeForTransfers ? (byte) 1 : (byte) 0);
        dest.writeByte(allowOverdraft ? (byte) 1 : (byte) 0);
        dest.writeSerializable(this.overdraftLimit);
        dest.writeSerializable(this.minRequiredBalance);
        dest.writeByte(enforceMinRequiredBalance ? (byte) 1 : (byte) 0);
        dest.writeSerializable(this.minBalanceForInterestCalculation);
        dest.writeParcelable(this.accountingRule, 0);
        //dest.writeParcelable(this.accountingMappings, flags);
        dest.writeTypedList(charges);
        dest.writeTypedList(currencyOptions);
        dest.writeTypedList(interestCompoundingPeriodTypeOptions);
        dest.writeTypedList(interestPostingPeriodTypeOptions);
        dest.writeTypedList(interestCalculationTypeOptions);
        dest.writeTypedList(interestCalculationDaysInYearTypeOptions);
        dest.writeTypedList(lockinPeriodFrequencyTypeOptions);
        dest.writeTypedList(withdrawalFeeTypeOptions);
        dest.writeTypedList(accountingRuleOptions);
        //dest.writeParcelable(this.accountingMappingOptions, flags);
        dest.writeTypedList(chargeOptions);
        dest.writeTypedList(penaltyOptions);
    }

    public SavingsProductData() {
    }

    protected SavingsProductData(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.shortName = in.readString();
        this.description = in.readString();
        this.currency = in.readParcelable(Currency.class.getClassLoader());
        this.nominalAnnualInterestRate = (BigDecimal) in.readSerializable();
        this.interestCompoundingPeriodType = in.readParcelable(CodeValue.class.getClassLoader());
        this.interestPostingPeriodType = in.readParcelable(CodeValue.class.getClassLoader());
        this.interestCalculationType = in.readParcelable(CodeValue.class.getClassLoader());
        this.interestCalculationDaysInYearType = in.readParcelable(CodeValue.class.getClassLoader());
        this.minRequiredOpeningBalance = (BigDecimal) in.readSerializable();
        this.lockinPeriodFrequency = (Integer) in.readValue(Integer.class.getClassLoader());
        this.lockinPeriodFrequencyType = in.readParcelable(CodeValue.class.getClassLoader());
        this.withdrawalFeeForTransfers = in.readByte() != 0;
        this.allowOverdraft = in.readByte() != 0;
        this.overdraftLimit = (BigDecimal) in.readSerializable();
        this.minRequiredBalance = (BigDecimal) in.readSerializable();
        this.enforceMinRequiredBalance = in.readByte() != 0;
        this.minBalanceForInterestCalculation = (BigDecimal) in.readSerializable();
        this.accountingRule = in.readParcelable(CodeValue.class.getClassLoader());
       // this.accountingMappings = in.readParcelable(Map<String, Object>.class.getClassLoader());
        this.charges = in.createTypedArrayList(ChargeData.CREATOR);
        this.currencyOptions = in.createTypedArrayList(Currency.CREATOR);
        this.interestCompoundingPeriodTypeOptions = in.createTypedArrayList(CodeValue.CREATOR);
        this.interestPostingPeriodTypeOptions = in.createTypedArrayList(CodeValue.CREATOR);
        this.interestCalculationTypeOptions = in.createTypedArrayList(CodeValue.CREATOR);
        this.interestCalculationDaysInYearTypeOptions = in.createTypedArrayList(CodeValue.CREATOR);
        this.lockinPeriodFrequencyTypeOptions = in.createTypedArrayList(CodeValue.CREATOR);
        this.withdrawalFeeTypeOptions = in.createTypedArrayList(CodeValue.CREATOR);
        this.accountingRuleOptions = in.createTypedArrayList(CodeValue.CREATOR);
        //this.accountingMappingOptions = in.readParcelable(Map<String, ArrayList<GLAccountData>>.class.getClassLoader());
        this.chargeOptions = in.createTypedArrayList(ChargeData.CREATOR);
        this.penaltyOptions = in.createTypedArrayList(ChargeData.CREATOR);
    }

    public static final Creator<SavingsProductData> CREATOR = new Creator<SavingsProductData>() {
        public SavingsProductData createFromParcel(Parcel source) {
            return new SavingsProductData(source);
        }

        public SavingsProductData[] newArray(int size) {
            return new SavingsProductData[size];
        }
    };
}
