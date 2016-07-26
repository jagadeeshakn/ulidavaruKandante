package com.conflux.finflux.offline.validator;

import android.content.Context;

import com.conflux.finflux.R;
import com.conflux.finflux.offline.exception.DateRangeException;

import java.util.ArrayList;
import java.util.Date;
import java.util.zip.DataFormatException;

/**
 * Created by Praveen J U on 7/17/2016.
 */
public class DateRangeValidator {
    private final Context context;
    private final ArrayList<Date> dates;
    private final int NUMBER_OF_Days = 5;
    private final int NumberOfAlreadyDownloadedMeetings;
    private final int NumberOfCollectionMeetingsThatCanBeDownloaded;
    private final String resource;

    public DateRangeValidator(String Tag,Context context, ArrayList<Date> dates, int numberOfAlreadyDownloadedMeetings) {
        resource = Tag;
        this.context = context;
        this.dates = dates;
        this.NumberOfAlreadyDownloadedMeetings = numberOfAlreadyDownloadedMeetings;
        this.NumberOfCollectionMeetingsThatCanBeDownloaded = this.NUMBER_OF_Days - this.NumberOfAlreadyDownloadedMeetings;
    }

    public void validate(){
        if(dates.size()>NumberOfCollectionMeetingsThatCanBeDownloaded){
            throw new DateRangeException(resource,context.getResources().getString(R.string.error_offline_date_range_exception)+" "+NumberOfCollectionMeetingsThatCanBeDownloaded+" days.");
        }
    }
}
