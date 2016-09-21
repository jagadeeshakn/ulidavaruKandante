package com.conflux.finflux.createClient.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.conflux.finflux.collectionSheet.data.CodeValue;
import com.conflux.finflux.collectionSheet.data.Currency;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jagadeeshakn on 8/11/2016.
 */
public class ChargeData implements Parcelable {

    private Long id;
    private String name;
    private boolean active;
    private boolean penalty;
    private Currency currency;
    private BigDecimal amount;
    private CodeValue chargeTimeType;
    private CodeValue chargeAppliesTo;
    private CodeValue chargeCalculationType;
    private CodeValue chargePaymentMode;
    private ArrayList<Integer> feeOnMonthDay;
    private Integer feeInterval;
    private BigDecimal minCap;
    private BigDecimal maxCap;
    private CodeValue feeFrequency;
    private GLAccountData incomeOrLiabilityAccount;

    private ArrayList<Currency> currencyOptions;
    private ArrayList<CodeValue> chargeCalculationTypeOptions;//
    private ArrayList<CodeValue> chargeAppliesToOptions;//
    private ArrayList<CodeValue> chargeTimeTypeOptions;//
    private ArrayList<CodeValue> chargePaymetModeOptions;//

    private ArrayList<CodeValue> loanChargeCalculationTypeOptions;
    private ArrayList<CodeValue> loanChargeTimeTypeOptions;
    private ArrayList<CodeValue> savingsChargeCalculationTypeOptions;
    private ArrayList<CodeValue> savingsChargeTimeTypeOptions;
    private ArrayList<CodeValue> clientChargeCalculationTypeOptions;
    private ArrayList<CodeValue> clientChargeTimeTypeOptions;
    private ArrayList<CodeValue> feeFrequencyOptions;

    private Map<String, ArrayList<GLAccountData>> incomeOrLiabilityAccountOptions;

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isPenalty() {
        return penalty;
    }

    public void setPenalty(boolean penalty) {
        this.penalty = penalty;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public CodeValue getChargeTimeType() {
        return chargeTimeType;
    }

    public void setChargeTimeType(CodeValue chargeTimeType) {
        this.chargeTimeType = chargeTimeType;
    }

    public CodeValue getChargeAppliesTo() {
        return chargeAppliesTo;
    }

    public void setChargeAppliesTo(CodeValue chargeAppliesTo) {
        this.chargeAppliesTo = chargeAppliesTo;
    }

    public CodeValue getChargeCalculationType() {
        return chargeCalculationType;
    }

    public void setChargeCalculationType(CodeValue chargeCalculationType) {
        this.chargeCalculationType = chargeCalculationType;
    }

    public CodeValue getChargePaymentMode() {
        return chargePaymentMode;
    }

    public void setChargePaymentMode(CodeValue chargePaymentMode) {
        this.chargePaymentMode = chargePaymentMode;
    }

    public ArrayList<Integer> getFeeOnMonthDay() {
        return feeOnMonthDay;
    }

    public void setFeeOnMonthDay(ArrayList<Integer> feeOnMonthDay) {
        this.feeOnMonthDay = feeOnMonthDay;
    }

    public Integer getFeeInterval() {
        return feeInterval;
    }

    public void setFeeInterval(Integer feeInterval) {
        this.feeInterval = feeInterval;
    }

    public BigDecimal getMinCap() {
        return minCap;
    }

    public void setMinCap(BigDecimal minCap) {
        this.minCap = minCap;
    }

    public BigDecimal getMaxCap() {
        return maxCap;
    }

    public void setMaxCap(BigDecimal maxCap) {
        this.maxCap = maxCap;
    }

    public CodeValue getFeeFrequency() {
        return feeFrequency;
    }

    public void setFeeFrequency(CodeValue feeFrequency) {
        this.feeFrequency = feeFrequency;
    }

    public GLAccountData getIncomeOrLiabilityAccount() {
        return incomeOrLiabilityAccount;
    }

    public void setIncomeOrLiabilityAccount(GLAccountData incomeOrLiabilityAccount) {
        this.incomeOrLiabilityAccount = incomeOrLiabilityAccount;
    }

    public ArrayList<Currency> getCurrencyOptions() {
        return currencyOptions;
    }

    public void setCurrencyOptions(ArrayList<Currency> currencyOptions) {
        this.currencyOptions = currencyOptions;
    }

    public ArrayList<CodeValue> getChargeCalculationTypeOptions() {
        return chargeCalculationTypeOptions;
    }

    public void setChargeCalculationTypeOptions(ArrayList<CodeValue> chargeCalculationTypeOptions) {
        this.chargeCalculationTypeOptions = chargeCalculationTypeOptions;
    }

    public ArrayList<CodeValue> getChargeAppliesToOptions() {
        return chargeAppliesToOptions;
    }

    public void setChargeAppliesToOptions(ArrayList<CodeValue> chargeAppliesToOptions) {
        this.chargeAppliesToOptions = chargeAppliesToOptions;
    }

    public ArrayList<CodeValue> getChargeTimeTypeOptions() {
        return chargeTimeTypeOptions;
    }

    public void setChargeTimeTypeOptions(ArrayList<CodeValue> chargeTimeTypeOptions) {
        this.chargeTimeTypeOptions = chargeTimeTypeOptions;
    }

    public ArrayList<CodeValue> getChargePaymetModeOptions() {
        return chargePaymetModeOptions;
    }

    public void setChargePaymetModeOptions(ArrayList<CodeValue> chargePaymetModeOptions) {
        this.chargePaymetModeOptions = chargePaymetModeOptions;
    }

    public ArrayList<CodeValue> getLoanChargeCalculationTypeOptions() {
        return loanChargeCalculationTypeOptions;
    }

    public void setLoanChargeCalculationTypeOptions(ArrayList<CodeValue> loanChargeCalculationTypeOptions) {
        this.loanChargeCalculationTypeOptions = loanChargeCalculationTypeOptions;
    }

    public ArrayList<CodeValue> getLoanChargeTimeTypeOptions() {
        return loanChargeTimeTypeOptions;
    }

    public void setLoanChargeTimeTypeOptions(ArrayList<CodeValue> loanChargeTimeTypeOptions) {
        this.loanChargeTimeTypeOptions = loanChargeTimeTypeOptions;
    }

    public ArrayList<CodeValue> getSavingsChargeCalculationTypeOptions() {
        return savingsChargeCalculationTypeOptions;
    }

    public void setSavingsChargeCalculationTypeOptions(ArrayList<CodeValue> savingsChargeCalculationTypeOptions) {
        this.savingsChargeCalculationTypeOptions = savingsChargeCalculationTypeOptions;
    }

    public ArrayList<CodeValue> getSavingsChargeTimeTypeOptions() {
        return savingsChargeTimeTypeOptions;
    }

    public void setSavingsChargeTimeTypeOptions(ArrayList<CodeValue> savingsChargeTimeTypeOptions) {
        this.savingsChargeTimeTypeOptions = savingsChargeTimeTypeOptions;
    }

    public ArrayList<CodeValue> getClientChargeCalculationTypeOptions() {
        return clientChargeCalculationTypeOptions;
    }

    public void setClientChargeCalculationTypeOptions(ArrayList<CodeValue> clientChargeCalculationTypeOptions) {
        this.clientChargeCalculationTypeOptions = clientChargeCalculationTypeOptions;
    }

    public ArrayList<CodeValue> getClientChargeTimeTypeOptions() {
        return clientChargeTimeTypeOptions;
    }

    public void setClientChargeTimeTypeOptions(ArrayList<CodeValue> clientChargeTimeTypeOptions) {
        this.clientChargeTimeTypeOptions = clientChargeTimeTypeOptions;
    }

    public ArrayList<CodeValue> getFeeFrequencyOptions() {
        return feeFrequencyOptions;
    }

    public void setFeeFrequencyOptions(ArrayList<CodeValue> feeFrequencyOptions) {
        this.feeFrequencyOptions = feeFrequencyOptions;
    }

    public Map<String, ArrayList<GLAccountData>> getIncomeOrLiabilityAccountOptions() {
        return incomeOrLiabilityAccountOptions;
    }

    public void setIncomeOrLiabilityAccountOptions(Map<String, ArrayList<GLAccountData>> incomeOrLiabilityAccountOptions) {
        this.incomeOrLiabilityAccountOptions = incomeOrLiabilityAccountOptions;
    }

    @Override
    public String toString() {
        return "ChargeData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", penalty=" + penalty +
                ", currency=" + currency +
                ", amount=" + amount +
                ", chargeTimeType=" + chargeTimeType +
                ", chargeAppliesTo=" + chargeAppliesTo +
                ", chargeCalculationType=" + chargeCalculationType +
                ", chargePaymentMode=" + chargePaymentMode +
                ", feeOnMonthDay=" + feeOnMonthDay +
                ", feeInterval=" + feeInterval +
                ", minCap=" + minCap +
                ", maxCap=" + maxCap +
                ", feeFrequency=" + feeFrequency +
                ", incomeOrLiabilityAccount=" + incomeOrLiabilityAccount +
                ", currencyOptions=" + currencyOptions +
                ", chargeCalculationTypeOptions=" + chargeCalculationTypeOptions +
                ", chargeAppliesToOptions=" + chargeAppliesToOptions +
                ", chargeTimeTypeOptions=" + chargeTimeTypeOptions +
                ", chargePaymetModeOptions=" + chargePaymetModeOptions +
                ", loanChargeCalculationTypeOptions=" + loanChargeCalculationTypeOptions +
                ", loanChargeTimeTypeOptions=" + loanChargeTimeTypeOptions +
                ", savingsChargeCalculationTypeOptions=" + savingsChargeCalculationTypeOptions +
                ", savingsChargeTimeTypeOptions=" + savingsChargeTimeTypeOptions +
                ", clientChargeCalculationTypeOptions=" + clientChargeCalculationTypeOptions +
                ", clientChargeTimeTypeOptions=" + clientChargeTimeTypeOptions +
                ", feeFrequencyOptions=" + feeFrequencyOptions +
                ", incomeOrLiabilityAccountOptions=" + incomeOrLiabilityAccountOptions +
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
        dest.writeByte(active ? (byte) 1 : (byte) 0);
        dest.writeByte(penalty ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.currency, 0);
        dest.writeSerializable(this.amount);
        dest.writeParcelable(this.chargeTimeType, 0);
        dest.writeParcelable(this.chargeAppliesTo, 0);
        dest.writeParcelable(this.chargeCalculationType, 0);
        dest.writeParcelable(this.chargePaymentMode, 0);
        dest.writeList(this.feeOnMonthDay);
        dest.writeValue(this.feeInterval);
        dest.writeSerializable(this.minCap);
        dest.writeSerializable(this.maxCap);
        dest.writeParcelable(this.feeFrequency, 0);
        dest.writeParcelable(this.incomeOrLiabilityAccount, 0);
        dest.writeTypedList(currencyOptions);
        dest.writeTypedList(chargeCalculationTypeOptions);
        dest.writeTypedList(chargeAppliesToOptions);
        dest.writeTypedList(chargeTimeTypeOptions);
        dest.writeTypedList(chargePaymetModeOptions);
        dest.writeTypedList(loanChargeCalculationTypeOptions);
        dest.writeTypedList(loanChargeTimeTypeOptions);
        dest.writeTypedList(savingsChargeCalculationTypeOptions);
        dest.writeTypedList(savingsChargeTimeTypeOptions);
        dest.writeTypedList(clientChargeCalculationTypeOptions);
        dest.writeTypedList(clientChargeTimeTypeOptions);
        dest.writeTypedList(feeFrequencyOptions);
        //dest.writeParcelable(this.incomeOrLiabilityAccountOptions, flags);
    }

    public ChargeData() {
    }

    protected ChargeData(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.active = in.readByte() != 0;
        this.penalty = in.readByte() != 0;
        this.currency = in.readParcelable(Currency.class.getClassLoader());
        this.amount = (BigDecimal) in.readSerializable();
        this.chargeTimeType = in.readParcelable(CodeValue.class.getClassLoader());
        this.chargeAppliesTo = in.readParcelable(CodeValue.class.getClassLoader());
        this.chargeCalculationType = in.readParcelable(CodeValue.class.getClassLoader());
        this.chargePaymentMode = in.readParcelable(CodeValue.class.getClassLoader());
        this.feeOnMonthDay = new ArrayList<Integer>();
        in.readList(this.feeOnMonthDay, List.class.getClassLoader());
        this.feeInterval = (Integer) in.readValue(Integer.class.getClassLoader());
        this.minCap = (BigDecimal) in.readSerializable();
        this.maxCap = (BigDecimal) in.readSerializable();
        this.feeFrequency = in.readParcelable(CodeValue.class.getClassLoader());
        this.incomeOrLiabilityAccount = in.readParcelable(GLAccountData.class.getClassLoader());
        this.currencyOptions = in.createTypedArrayList(Currency.CREATOR);
        this.chargeCalculationTypeOptions = in.createTypedArrayList(CodeValue.CREATOR);
        this.chargeAppliesToOptions = in.createTypedArrayList(CodeValue.CREATOR);
        this.chargeTimeTypeOptions = in.createTypedArrayList(CodeValue.CREATOR);
        this.chargePaymetModeOptions = in.createTypedArrayList(CodeValue.CREATOR);
        this.loanChargeCalculationTypeOptions = in.createTypedArrayList(CodeValue.CREATOR);
        this.loanChargeTimeTypeOptions = in.createTypedArrayList(CodeValue.CREATOR);
        this.savingsChargeCalculationTypeOptions = in.createTypedArrayList(CodeValue.CREATOR);
        this.savingsChargeTimeTypeOptions = in.createTypedArrayList(CodeValue.CREATOR);
        this.clientChargeCalculationTypeOptions = in.createTypedArrayList(CodeValue.CREATOR);
        this.clientChargeTimeTypeOptions = in.createTypedArrayList(CodeValue.CREATOR);
        this.feeFrequencyOptions = in.createTypedArrayList(CodeValue.CREATOR);
        //this.incomeOrLiabilityAccountOptions = in.readParcelable(Map<String, ArrayList<GLAccountData>>.class.getClassLoader());
    }

    public static final Creator<ChargeData> CREATOR = new Creator<ChargeData>() {
        public ChargeData createFromParcel(Parcel source) {
            return new ChargeData(source);
        }

        public ChargeData[] newArray(int size) {
            return new ChargeData[size];
        }
    };
}
