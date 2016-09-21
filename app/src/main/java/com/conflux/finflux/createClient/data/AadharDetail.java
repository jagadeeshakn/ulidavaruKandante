package com.conflux.finflux.createClient.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jagadeeshakn on 8/17/2016.
 */
public class AadharDetail implements Parcelable {

    private String uid;
    private String name="";
    private String gender="";
    private String yob;
    private String gname="";
    private String vtc="";
    private String po="";
    private String dist="";
    private String state="";
    private String pc;
    private String dob;
    private String loc="";
    private String co="";
    private String subdist="";
    private String house;
    private String street="";

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getYob() {
        return yob;
    }

    public void setYob(String yob) {
        this.yob = yob;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getVtc() {
        return vtc;
    }

    public void setVtc(String vtc) {
        this.vtc = vtc;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getSubdist() {
        return subdist;
    }

    public void setSubdist(String subdist) {
        this.subdist = subdist;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uid);
        dest.writeString(this.name);
        dest.writeString(this.gender);
        dest.writeString(this.yob);
        dest.writeString(this.gname);
        dest.writeString(this.vtc);
        dest.writeString(this.po);
        dest.writeString(this.dist);
        dest.writeString(this.state);
        dest.writeString(this.pc);
        dest.writeString(this.dob);
        dest.writeString(this.loc);
        dest.writeString(this.co);
        dest.writeString(this.subdist);
        dest.writeString(this.house);
        dest.writeString(this.street);
    }

    public AadharDetail() {
    }

    protected AadharDetail(Parcel in) {
        this.uid = in.readString();
        this.name = in.readString();
        this.gender = in.readString();
        this.yob = in.readString();
        this.gname = in.readString();
        this.vtc = in.readString();
        this.po = in.readString();
        this.dist = in.readString();
        this.state = in.readString();
        this.pc = in.readString();
        this.dob = in.readString();
        this.loc = in.readString();
        this.co = in.readString();
        this.subdist = in.readString();
        this.house = in.readString();
        this.street = in.readString();
    }

    public static final Creator<AadharDetail> CREATOR = new Creator<AadharDetail>() {
        public AadharDetail createFromParcel(Parcel source) {
            return new AadharDetail(source);
        }

        public AadharDetail[] newArray(int size) {
            return new AadharDetail[size];
        }
    };
}
