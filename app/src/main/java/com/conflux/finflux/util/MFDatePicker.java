package com.conflux.finflux.util;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.widget.DatePicker;

import java.security.acl.LastOwnerException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jagadeeshakn on 7/9/2016.
 */
public class MFDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public static final String TAG = "MFDatePicker";
    static String dateSet;
    static Calendar calendar;

    static {

        calendar = Calendar.getInstance();
        dateSet = new StringBuilder()
                .append(calendar.get(Calendar.DAY_OF_MONTH) < 10 ?
                        ("0" + calendar.get(Calendar.DAY_OF_MONTH))
                        : calendar.get(Calendar.DAY_OF_MONTH))
                .append("-")
                .append(getMonthFullName(calendar.get(Calendar.MONTH)+1))
                .append("-")
                .append(calendar.get(Calendar.YEAR))
                .toString();
    }

    OnDatePickListener onDatePickListener;

    public MFDatePicker() {

    }

    public static MFDatePicker newInsance(Fragment fragment) {
        MFDatePicker mfDatePicker = new MFDatePicker();
        mfDatePicker.onDatePickListener = (OnDatePickListener) fragment;
        return mfDatePicker;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return new DatePickerDialog(getActivity(),
                this, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
    }

    //get date for generate collection sheet (date format is dd mmmm yyyy)
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        //TODO Fix Single digit problem that fails with the locale
        Logger.d(TAG, "month value after select the date from date picker   " + month);
        month += 1;
        onDatePickListener.onDatePicked(
                new StringBuilder()
                        .append(day < 10 ? "0" + day : day)
                        .append("-")
                        .append(getMonthFullName(month))
                        .append("-")
                        .append(year)
                        .toString()
        );

    }

    public static String getMonthFullName(int month) {
        String monthName = "";
        switch (month) {
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;
            case 8:
                monthName = "August";
                break;
            case 9:
                monthName = "September";
                break;
            case 10:
                monthName = "October";
                break;
            case 11:
                monthName = "November";
                break;
            case 12:
                monthName = "December";
                break;
        }
        return monthName;
    }


    public static String getDatePickedAsString() {
        return dateSet;
    }

    public interface OnDatePickListener {
        public void onDatePicked(String date);
    }

    public void setOnDatePickListener(OnDatePickListener onDatePickListener) {
        this.onDatePickListener = onDatePickListener;
    }


}
