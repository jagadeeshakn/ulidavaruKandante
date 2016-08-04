package com.conflux.finflux.collectionSheet.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jagadeeshakn on 7/18/2016.
 */
public class CollectionSheetDataForAdapter implements Parcelable {

    private Long entityId;
    private String entityType;
    private String entityName;
    private Long parentId;
    private HashMap<Long,Double> editLoans;
    private CodeValue editAttendanceType;
    private Double dueAmount;
    private Double collectedAmount;
    private List<Loan> loans = new ArrayList<>();
    private CodeValue attendanceType;


    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public HashMap<Long, Double> getEditLoans() {
        return editLoans;
    }

    public void setEditLoans(HashMap<Long, Double> editLoans) {
        this.editLoans = editLoans;
    }

    public CodeValue getEditAttendanceType() {
        return editAttendanceType;
    }

    public void setEditAttendanceType(CodeValue editAttendanceType) {
        this.editAttendanceType = editAttendanceType;
    }

    public Double getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(Double dueAmount) {
        this.dueAmount = dueAmount;
    }

    public Double getCollectedAmount() {
        return collectedAmount;
    }

    public void setCollectedAmount(Double collectedAmount) {
        this.collectedAmount = collectedAmount;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public CodeValue getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(CodeValue attendanceType) {
        this.attendanceType = attendanceType;
    }

    @Override
    public String toString() {
        return "CollectionSheetDataForAdapter{" +
                "entityId=" + entityId +
                ", entityType='" + entityType + '\'' +
                ", entityName='" + entityName + '\'' +
                ", parentId=" + parentId +
                ", editLoans=" + editLoans +
                ", editAttendanceType=" + editAttendanceType +
                ", dueAmount=" + dueAmount +
                ", collectedAmount=" + collectedAmount +
                ", loans=" + loans +
                ", attendanceType=" + attendanceType +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.entityId);
        dest.writeString(this.entityType);
        dest.writeString(this.entityName);
        dest.writeValue(this.parentId);
        dest.writeParcelable(this.editAttendanceType, 0);
        dest.writeValue(this.dueAmount);
        dest.writeValue(this.collectedAmount);
        dest.writeTypedList(loans);
        dest.writeParcelable(this.attendanceType, 0);
        dest.writeByte((byte) (editLoans == null ? 0 : 1));
        if(editLoans!=null) {
            dest.writeInt(editLoans.size());
            for (Map.Entry<Long, Double> entry : editLoans.entrySet()) {
                dest.writeLong(entry.getKey());
                dest.writeDouble(entry.getValue());
            }
        }
    }

    public CollectionSheetDataForAdapter() {
    }

    protected CollectionSheetDataForAdapter(Parcel in) {
        this.entityId = (Long) in.readValue(Long.class.getClassLoader());
        this.entityType = in.readString();
        this.entityName = in.readString();
        this.parentId = (Long) in.readValue(Long.class.getClassLoader());
        this.editAttendanceType = in.readParcelable(CodeValue.class.getClassLoader());
        this.dueAmount = (Double) in.readValue(Double.class.getClassLoader());
        this.collectedAmount = (Double) in.readValue(Double.class.getClassLoader());
        this.loans = in.createTypedArrayList(Loan.CREATOR);
        this.attendanceType = in.readParcelable(CodeValue.class.getClassLoader());
        if(in.readByte()==1) {
            editLoans = new HashMap<Long,Double>();
            int size = in.readInt();
            for (int i = 0; i < size; i++) {
                Long key = in.readLong();
                Double value = in.readDouble();
                editLoans.put(key, value);
            }
        }
    }

    public static final Creator<CollectionSheetDataForAdapter> CREATOR = new Creator<CollectionSheetDataForAdapter>() {
        public CollectionSheetDataForAdapter createFromParcel(Parcel source) {
            return new CollectionSheetDataForAdapter(source);
        }

        public CollectionSheetDataForAdapter[] newArray(int size) {
            return new CollectionSheetDataForAdapter[size];
        }
    };
}
