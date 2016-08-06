package com.conflux.finflux.collectionSheet.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jagadeeshakn on 7/27/2016.
 */
public class PaymentTypeOption implements Parcelable {

    private Integer id;

    private String name;

    private String description;

    private Boolean isCashPayment;

    private Integer position;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsCashPayment() {
        return isCashPayment;
    }

    public void setIsCashPayment(Boolean isCashPayment) {
        this.isCashPayment = isCashPayment;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "PaymentTypeOption{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isCashPayment=" + isCashPayment +
                ", position=" + position +
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
        dest.writeString(this.description);
        dest.writeValue(this.isCashPayment);
        dest.writeValue(this.position);
    }

    public PaymentTypeOption() {
    }

    protected PaymentTypeOption(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.description = in.readString();
        this.isCashPayment = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.position = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<PaymentTypeOption> CREATOR = new Creator<PaymentTypeOption>() {
        public PaymentTypeOption createFromParcel(Parcel source) {
            return new PaymentTypeOption(source);
        }

        public PaymentTypeOption[] newArray(int size) {
            return new PaymentTypeOption[size];
        }
    };
}
