package com.conflux.finflux.db.collectionsheet;

import io.realm.RealmObject;

/**
 * Created by Praveen J U on 7/17/2016.
 */
public class TableMeetingTime extends RealmObject {
    private Integer iLocalMillis;
    private Integer iMinDaysInFirstWeek;
    private Long fkCenterId;

    public void setiLocalMillis(Integer iLocalMillis) {
        this.iLocalMillis = iLocalMillis;
    }

    public void setiMinDaysInFirstWeek(Integer iMinDaysInFirstWeek) {
        this.iMinDaysInFirstWeek = iMinDaysInFirstWeek;
    }

    public void setFkCenterId(Long fkCenterId) {
        this.fkCenterId = fkCenterId;
    }

    public TableMeetingTime() {

    }

    public TableMeetingTime(Integer iLocalMillis, Integer iMinDaysInFirstWeek, Long fkCenterId) {
        super();
        this.iLocalMillis = iLocalMillis;
        this.iMinDaysInFirstWeek = iMinDaysInFirstWeek;
        this.fkCenterId = fkCenterId;
    }

    public Long getFkCenterId() {
        return fkCenterId;
    }

    public Integer getiLocalMillis() {
        return iLocalMillis;
    }


    public Integer getiMinDaysInFirstWeek() {
        return iMinDaysInFirstWeek;
    }

}
