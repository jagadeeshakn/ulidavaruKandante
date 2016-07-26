package com.conflux.finflux.db.collectionsheet;


import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Praveen J U on 7/17/2016.
 */
public class TableMeetingFallCenter extends RealmObject {
    private long fkProductiveCollectionSheetDataId;
    private Long id;
    private String accountNo;
    private String name;
    private Integer officeId;
    private Long staffId;
    private String staffName;
    private String hierarchy;
    private TableStatus status;
    private Boolean active;
    private int isSynced;
    private RealmList<TableInteger> activationDate;
    private TableCollectionMeetingCalendar collectionMeetingCalendar;
    private Double totalCollected;
    private Double totalOverdue;
    private Double totaldue;

    public long getFkProductiveCollectionSheetDataId() {
        return fkProductiveCollectionSheetDataId;
    }

    public Long getId() {
        return id;
    }

    public TableMeetingFallCenter() {
    }

    public String getAccountNo() {
        return accountNo;
    }

    public String getName() {
        return name;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public String getHierarchy() {
        return hierarchy;
    }

    public TableStatus getStatus() {
        return status;
    }

    public Boolean getActive() {
        return active;
    }

    public int getIsSynced() {
        return isSynced;
    }

    public RealmList<TableInteger> getActivationDate() {
        return activationDate;
    }

    public TableCollectionMeetingCalendar getCollectionMeetingCalendar() {
        return collectionMeetingCalendar;
    }

    public Double getTotalCollected() {
        return totalCollected;
    }

    public Double getTotalOverdue() {
        return totalOverdue;
    }

    public void setFkProductiveCollectionSheetDataId(long fkProductiveCollectionSheetDataId) {
        this.fkProductiveCollectionSheetDataId = fkProductiveCollectionSheetDataId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public void setHierarchy(String hierarchy) {
        this.hierarchy = hierarchy;
    }

    public void setStatus(TableStatus status) {
        this.status = status;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setIsSynced(int isSynced) {
        this.isSynced = isSynced;
    }

    public void setActivationDate(RealmList<TableInteger> activationDate) {
        this.activationDate = activationDate;
    }

    public void setCollectionMeetingCalendar(TableCollectionMeetingCalendar collectionMeetingCalendar) {
        this.collectionMeetingCalendar = collectionMeetingCalendar;
    }

    public void setTotalCollected(Double totalCollected) {
        this.totalCollected = totalCollected;
    }

    public void setTotalOverdue(Double totalOverdue) {
        this.totalOverdue = totalOverdue;
    }

    public void setTotaldue(Double totaldue) {
        this.totaldue = totaldue;
    }

    public Double getTotaldue() {
        return totaldue;
    }

    public TableMeetingFallCenter(long fkProductiveCollectionSheetDataId, Long id, String accountNo, String name, Integer officeId, Long staffId, String staffName, String hierarchy, TableStatus status, Boolean active, int isSynced, RealmList<TableInteger> activationDate, TableCollectionMeetingCalendar collectionMeetingCalendar, Double totalCollected, Double totalOverdue, Double totaldue) {
        super();
        this.fkProductiveCollectionSheetDataId = fkProductiveCollectionSheetDataId;
        this.id = id;
        this.accountNo = accountNo;
        this.name = name;
        this.officeId = officeId;

        this.staffId = staffId;
        this.staffName = staffName;
        this.hierarchy = hierarchy;
        this.status = status;
        this.active = active;
        this.isSynced = isSynced;
        this.activationDate = activationDate;
        this.collectionMeetingCalendar = collectionMeetingCalendar;
        this.totalCollected = totalCollected;
        this.totalOverdue = totalOverdue;
        this.totaldue = totaldue;
    }
}
