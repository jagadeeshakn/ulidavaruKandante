package com.conflux.finflux.db.collectionsheet;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.processor.RealmAnalytics;

/**
 * Created by Praveen J U on 7/17/2016.
 */
public class TableProductiveCollectionData extends RealmObject {

    private long id;
    private String meetingDate;
    private Long staffId;
    private String staffName;
    private RealmList<TableMeetingFallCenter> meetingFallCenters;


    public TableProductiveCollectionData() {
    }

    public TableProductiveCollectionData(long id, String meetingDate, Long staffId, String staffName, RealmList<TableMeetingFallCenter> meetingFallCenters) {
        super();
        this.id = id;
        this.meetingDate = meetingDate;
        this.staffId = staffId;
        this.staffName = staffName;
        this.meetingFallCenters = meetingFallCenters;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public RealmList<TableMeetingFallCenter> getMeetingFallCenters() {
        return meetingFallCenters;
    }

    public void setMeetingFallCenters(RealmList<TableMeetingFallCenter> meetingFallCenters) {
        this.meetingFallCenters = meetingFallCenters;
    }
}
