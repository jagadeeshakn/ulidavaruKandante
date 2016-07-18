package com.conflux.finflux.db.collectionsheet;

import io.realm.RealmObject;

/**
 * Created by Praveen J U on 7/17/2016.
 */
public class TableMeetingDate extends RealmObject {
    private Long fkCenterId;
    private int year;
    private int month;

    public TableMeetingDate() {
    }

    private int day;

    public void setFkCenterId(Long fkCenterId) {
        this.fkCenterId = fkCenterId;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public TableMeetingDate(Long fkCenterId, int year, int month, int day) {
        super();
        this.fkCenterId = fkCenterId;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Long getFkCenterId() {
        return fkCenterId;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }


    public int getDay() {
        return day;
    }
}
