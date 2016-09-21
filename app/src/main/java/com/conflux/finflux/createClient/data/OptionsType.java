package com.conflux.finflux.createClient.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jagadeeshakn on 8/31/2016.
 */
public class OptionsType implements Parcelable {

    private Long id;

    private String name;

    private Integer position;

    private String description;

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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "OptionsType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position=" + position +
                ", description='" + description + '\'' +
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
        dest.writeValue(this.position);
        dest.writeString(this.description);
    }

    public OptionsType() {
    }

    protected OptionsType(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.position = (Integer) in.readValue(Integer.class.getClassLoader());
        this.description = in.readString();
    }

    public static final Creator<OptionsType> CREATOR = new Creator<OptionsType>() {
        public OptionsType createFromParcel(Parcel source) {
            return new OptionsType(source);
        }

        public OptionsType[] newArray(int size) {
            return new OptionsType[size];
        }
    };
}
