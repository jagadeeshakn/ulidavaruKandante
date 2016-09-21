package com.conflux.finflux.createClient.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.conflux.finflux.collectionSheet.data.CodeValue;

/**
 * Created by jagadeeshakn on 8/11/2016.
 */
public class GLAccountData implements Parcelable {

    private Long id;
    private String name;
    private Long parentId;
    private String glCode;
    private Boolean disabled;
    private Boolean manualEntriesAllowed;
    private CodeValue type;
    private CodeValue usage;
    private String description;
    private String nameDecorated;
    private CodeValue tagId;
    private Long organizationRunningBalance;

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getGlCode() {
        return glCode;
    }

    public void setGlCode(String glCode) {
        this.glCode = glCode;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Boolean getManualEntriesAllowed() {
        return manualEntriesAllowed;
    }

    public void setManualEntriesAllowed(Boolean manualEntriesAllowed) {
        this.manualEntriesAllowed = manualEntriesAllowed;
    }

    public CodeValue getType() {
        return type;
    }

    public void setType(CodeValue type) {
        this.type = type;
    }

    public CodeValue getUsage() {
        return usage;
    }

    public void setUsage(CodeValue usage) {
        this.usage = usage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameDecorated() {
        return nameDecorated;
    }

    public void setNameDecorated(String nameDecorated) {
        this.nameDecorated = nameDecorated;
    }

    public CodeValue getTagId() {
        return tagId;
    }

    public void setTagId(CodeValue tagId) {
        this.tagId = tagId;
    }

    public Long getOrganizationRunningBalance() {
        return organizationRunningBalance;
    }

    public void setOrganizationRunningBalance(Long organizationRunningBalance) {
        this.organizationRunningBalance = organizationRunningBalance;
    }

    @Override
    public String toString() {
        return "GLAccountData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", glCode='" + glCode + '\'' +
                ", disabled=" + disabled +
                ", manualEntriesAllowed=" + manualEntriesAllowed +
                ", type=" + type +
                ", usage=" + usage +
                ", description='" + description + '\'' +
                ", nameDecorated='" + nameDecorated + '\'' +
                ", tagId=" + tagId +
                ", organizationRunningBalance=" + organizationRunningBalance +
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
        dest.writeValue(this.parentId);
        dest.writeString(this.glCode);
        dest.writeValue(this.disabled);
        dest.writeValue(this.manualEntriesAllowed);
        dest.writeParcelable(this.type, 0);
        dest.writeParcelable(this.usage, 0);
        dest.writeString(this.description);
        dest.writeString(this.nameDecorated);
        dest.writeParcelable(this.tagId, 0);
        dest.writeValue(this.organizationRunningBalance);
    }

    public GLAccountData() {
    }

    protected GLAccountData(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.parentId = (Long) in.readValue(Long.class.getClassLoader());
        this.glCode = in.readString();
        this.disabled = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.manualEntriesAllowed = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.type = in.readParcelable(CodeValue.class.getClassLoader());
        this.usage = in.readParcelable(CodeValue.class.getClassLoader());
        this.description = in.readString();
        this.nameDecorated = in.readString();
        this.tagId = in.readParcelable(CodeValue.class.getClassLoader());
        this.organizationRunningBalance = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Creator<GLAccountData> CREATOR = new Creator<GLAccountData>() {
        public GLAccountData createFromParcel(Parcel source) {
            return new GLAccountData(source);
        }

        public GLAccountData[] newArray(int size) {
            return new GLAccountData[size];
        }
    };
}
