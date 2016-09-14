package com.conflux.finflux.db.collectionsheet;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Praveen J U on 7/17/2016.
 */
public class TableCollectionMeetingCalendar extends RealmObject {
    private Long fkMeetingFallCenterId;
    private Long id;
    private Long calendarInstanceId;
    private Long entityId;
    private TableStatus entityType;
    private String title;
    private String description;
    private String location;
    private String startDate;
    private Integer iLocalMillis;
    private Integer iMinDaysInFirstWeek;

    public Integer getiMinDaysInFirstWeek() {
        return iMinDaysInFirstWeek;
    }

    public void setiMinDaysInFirstWeek(Integer iMinDaysInFirstWeek) {
        this.iMinDaysInFirstWeek = iMinDaysInFirstWeek;
    }

    public Integer getiLocalMillis() {
        return iLocalMillis;
    }

    public void setiLocalMillis(Integer iLocalMillis) {
        this.iLocalMillis = iLocalMillis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private boolean repeating;
    private String recurrence;

    public TableCollectionMeetingCalendar() {
    }


    public void setFkMeetingFallCenterId(Long fkMeetingFallCenterId) {
        this.fkMeetingFallCenterId = fkMeetingFallCenterId;
    }

    public void setCalendarInstanceId(Long calendarInstanceId) {
        this.calendarInstanceId = calendarInstanceId;
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

    public void setRepeating(boolean repeating) {
        this.repeating = repeating;
    }

    public void setRecurrence(String recurrence) {
        this.recurrence = recurrence;
    }

    public void setStartDate(String startDate) {
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


    public boolean isRepeating() {
        return repeating;
    }



    public String getRecurrence() {
        return recurrence;
    }



    public String getStartDate() {
        return startDate;
    }


}
