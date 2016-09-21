package com.conflux.finflux.createClient.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jagadeeshakn on 8/11/2016.
 */
public class OfficeOption implements Parcelable {

    private Integer id;
    private String name;
    private String nameDecorated;

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

    public String getNameDecorated() {
        return nameDecorated;
    }

    public void setNameDecorated(String nameDecorated) {
        this.nameDecorated = nameDecorated;
    }

    @Override
    public String toString() {
        return "OfficeOption{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameDecorated='" + nameDecorated + '\'' +
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
        dest.writeString(this.nameDecorated);
    }

    public OfficeOption() {
    }

    protected OfficeOption(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.nameDecorated = in.readString();
    }

    public static final Creator<OfficeOption> CREATOR = new Creator<OfficeOption>() {
        public OfficeOption createFromParcel(Parcel source) {
            return new OfficeOption(source);
        }

        public OfficeOption[] newArray(int size) {
            return new OfficeOption[size];
        }
    };
}
