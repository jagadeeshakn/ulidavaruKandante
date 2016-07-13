package com.conflux.finflux.collectionSheet.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.orm.dsl.Ignore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jagadeeshakn on 7/11/2016.
 */
public class CollectionMeetingCalendar implements Parcelable {

    Long calendarInstanceId;
    long calendarId;
    int entityId;
    Status entityType;
    String title;
    String description;
    String location;
    MeetingDate meetingCalendarDate;
    private MeetingTime meetingTime;
    boolean repeating;
    String recurrence;
    @Ignore
    private List<Integer> startDate = new ArrayList<Integer>();

    public long getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(long calendarId) {
        this.calendarId = calendarId;
    }

    public MeetingDate getMeetingCalendarDate() {
        return meetingCalendarDate;
    }

    public void setMeetingCalendarDate(MeetingDate meetingCalendarDate) {
        this.meetingCalendarDate = meetingCalendarDate;
    }

    public boolean isRepeating() {
        return repeating;
    }

    public void setRepeating(boolean repeating) {
        this.repeating = repeating;
    }


    /*public void setCalendarInstanceId(Long calendarInstanceId) {
        this.calendarInstanceId = calendarInstanceId;
    }*/

    @Ignore
    public List<Integer> getStartDate() {
        return startDate;
    }

    @Ignore
    public void setStartDate(List<Integer> startDate) {
        this.startDate = startDate;
    }

    public Long getCalendarInstanceId() {
        return calendarInstanceId;
    }

    public void setCalendarInstanceId(Long calendarInstanceId) {
        this.calendarInstanceId = calendarInstanceId;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public Status getEntityType() {
        return entityType;
    }

    public void setEntityType(Status entityType) {
        this.entityType = entityType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(String recurrence) {
        this.recurrence = recurrence;
    }

    public MeetingTime getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(MeetingTime meetingTime) {
        this.meetingTime = meetingTime;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public CollectionMeetingCalendar() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.calendarInstanceId);
        dest.writeLong(this.calendarId);
        dest.writeInt(this.entityId);
        dest.writeParcelable(this.entityType, 0);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.location);
        dest.writeParcelable(this.meetingCalendarDate, 0);
        dest.writeParcelable(this.meetingTime, flags);
        dest.writeByte(repeating ? (byte) 1 : (byte) 0);
        dest.writeString(this.recurrence);
        dest.writeList(this.startDate);
    }

    protected CollectionMeetingCalendar(Parcel in) {
        this.calendarInstanceId = in.readLong();
        this.calendarId = in.readLong();
        this.entityId = in.readInt();
        this.entityType = in.readParcelable(Status.class.getClassLoader());
        this.title = in.readString();
        this.description = in.readString();
        this.location = in.readString();
        this.meetingCalendarDate = in.readParcelable(MeetingDate.class.getClassLoader());
        this.meetingTime = in.readParcelable(MeetingTime.class.getClassLoader());
        this.repeating = in.readByte() != 0;
        this.recurrence = in.readString();
        this.startDate = new ArrayList<Integer>();
        in.readList(this.startDate, List.class.getClassLoader());
    }

    public static final Creator<CollectionMeetingCalendar> CREATOR = new Creator<CollectionMeetingCalendar>() {
        public CollectionMeetingCalendar createFromParcel(Parcel source) {
            return new CollectionMeetingCalendar(source);
        }

        public CollectionMeetingCalendar[] newArray(int size) {
            return new CollectionMeetingCalendar[size];
        }
    };
}
