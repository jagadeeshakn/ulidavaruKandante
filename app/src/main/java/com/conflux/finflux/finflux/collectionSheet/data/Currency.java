package com.conflux.finflux.finflux.collectionSheet.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jagadeeshakn on 7/11/2016.
 */
public class Currency implements Parcelable {
    private String code;
    private String name;
    private int decimalPlaces;
    private int inMultiplesOf;
    private String displaySymbol;
    private String nameCode;
    private String displayLabel;
    private Long loanId;
    private Loan loan;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDecimalPlaces() {
        return decimalPlaces;
    }

    public void setDecimalPlaces(int decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    public int getInMultiplesOf() {
        return inMultiplesOf;
    }

    public void setInMultiplesOf(int inMultiplesOf) {
        this.inMultiplesOf = inMultiplesOf;
    }

    public String getDisplaySymbol() {
        return displaySymbol;
    }

    public void setDisplaySymbol(String displaySymbol) {
        this.displaySymbol = displaySymbol;
    }

    public String getNameCode() {
        return nameCode;
    }

    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    public String getDisplayLabel() {
        return displayLabel;
    }

    public void setDisplayLabel(String displayLabel) {
        this.displayLabel = displayLabel;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", decimalPlaces=" + decimalPlaces +
                ", inMultiplesOf=" + inMultiplesOf +
                ", displaySymbol='" + displaySymbol + '\'' +
                ", nameCode='" + nameCode + '\'' +
                ", displayLabel='" + displayLabel + '\'' +
                ", loanId=" + loanId +
                ", loan=" + loan +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeString(this.name);
        dest.writeInt(this.decimalPlaces);
        dest.writeInt(this.inMultiplesOf);
        dest.writeString(this.displaySymbol);
        dest.writeString(this.nameCode);
        dest.writeString(this.displayLabel);
        dest.writeValue(this.loanId);
        dest.writeParcelable(this.loan, 0);
    }

    public Currency() {
    }

    protected Currency(Parcel in) {
        this.code = in.readString();
        this.name = in.readString();
        this.decimalPlaces = in.readInt();
        this.inMultiplesOf = in.readInt();
        this.displaySymbol = in.readString();
        this.nameCode = in.readString();
        this.displayLabel = in.readString();
        this.loanId = (Long) in.readValue(Long.class.getClassLoader());
        this.loan = in.readParcelable(Loan.class.getClassLoader());
    }

    public static final Creator<Currency> CREATOR = new Creator<Currency>() {
        public Currency createFromParcel(Parcel source) {
            return new Currency(source);
        }

        public Currency[] newArray(int size) {
            return new Currency[size];
        }
    };
}
