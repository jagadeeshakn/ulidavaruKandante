package com.conflux.finflux.collectionSheet.data;

/**
 * Created by Praveen J U on 7/19/2016.
 */
public class GenerateCollectionSheetPayloadAssembler {
    private final MeetingFallCenter meetingFallCenter;
    private final String meetingDate;

    public GenerateCollectionSheetPayloadAssembler(MeetingFallCenter meetingFallCenter, String meetingDate) {
        this.meetingFallCenter = meetingFallCenter;
        this.meetingDate = meetingDate;
    }

    public MeetingFallCenter getMeetingFallCenter() {
        return meetingFallCenter;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public Payload assemblePayload(){
        Payload payload = new Payload();
        payload.setDateFormat(CollectionSheetConstants.DATE_FORMAT);
        payload.setLocale(CollectionSheetConstants.EN);
        payload.setTransactionDate(meetingDate);
        payload.setCalendarId(meetingFallCenter.getCollectionMeetingCalendar().getCalendarInstanceId());
        return payload;
    }
}
