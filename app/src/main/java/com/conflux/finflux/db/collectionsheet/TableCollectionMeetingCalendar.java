package com.conflux.finflux.db.collectionsheet;

import com.conflux.finflux.collectionSheet.data.Status;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Praveen J U on 7/17/2016.
 */
public class TableCollectionMeetingCalendar extends RealmObject {
    private Long fkMeetingFallCenterId;
    private Long calendarInstanceId;
    private long calendarId;
    private Long entityId;
    private TableStatus entityType;
    private String title;
    private String description;
    private String location;
    private TableMeetingDate meetingDate;
    private TableMeetingTime meetingTime;
    private boolean repeating;
    private String recurrence;
    private RealmList<TableInteger> startDate;

    public TableCollectionMeetingCalendar() {
    }

    public TableCollectionMeetingCalendar(Long fkMeetingFallCenterId, Long calendarInstanceId, long calendarId, Long entityId, TableStatus entityType, String title, String description, String location, TableMeetingDate meetingDate, TableMeetingTime meetingTime, boolean repeating, String recurrence, RealmList<TableInteger> startDate) {
        super();
        this.fkMeetingFallCenterId = fkMeetingFallCenterId;
        this.calendarInstanceId = calendarInstanceId;
        this.calendarId = calendarId;
        this.entityId = entityId;
        this.entityType = entityType;
        this.title = title;
        this.description = description;
        this.location = location;
        this.meetingDate = meetingDate;
        this.meetingTime = meetingTime;
        this.repeating = repeating;
        this.recurrence = recurrence;
        this.startDate = startDate;
    }

    public void setFkMeetingFallCenterId(Long fkMeetingFallCenterId) {
        this.fkMeetingFallCenterId = fkMeetingFallCenterId;
    }

    public void setCalendarInstanceId(Long calendarInstanceId) {
        this.calendarInstanceId = calendarInstanceId;
    }

    public void setCalendarId(long calendarId) {
        this.calendarId = calendarId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public void setEntityType(TableStatus entityType) {
        this.entityType = entityType;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setMeetingDate(TableMeetingDate meetingDate) {
        this.meetingDate = meetingDate;
    }

    public void setMeetingTime(TableMeetingTime meetingTime) {
        this.meetingTime = meetingTime;
    }

    public void setRepeating(boolean repeating) {
        this.repeating = repeating;
    }

    public void setRecurrence(String recurrence) {
        this.recurrence = recurrence;
    }

    public void setStartDate(RealmList<TableInteger> startDate) {
        this.startDate = startDate;
    }

    public TableStatus getEntityType() {
        return entityType;
    }


    public Long getFkMeetingFallCenterId() {
        return fkMeetingFallCenterId;
    }

    public Long getCalendarInstanceId() {

        return calendarInstanceId;
    }

    public long getCalendarId() {
        return calendarId;
    }
    public Long getEntityId() {
        return entityId;
    }



    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }



    public TableMeetingDate getMeetingDate() {
        return meetingDate;
    }



    public TableMeetingTime getMeetingTime() {
        return meetingTime;
    }



    public boolean isRepeating() {
        return repeating;
    }



    public String getRecurrence() {
        return recurrence;
    }



    public RealmList<TableInteger> getStartDate() {
        return startDate;
    }


}
