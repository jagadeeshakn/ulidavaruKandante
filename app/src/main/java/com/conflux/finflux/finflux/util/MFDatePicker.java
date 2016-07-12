package com.conflux.finflux.finflux.util;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.widget.DatePicker;

import java.sql.Date;
import java.util.Calendar;

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
                        ("0"+calendar.get(Calendar.DAY_OF_MONTH))
                        : calendar.get(Calendar.DAY_OF_MONTH))
                .append("-")
                .append(calendar.get(Calendar.MONTH) + 1 < 10 ?
                        ("0"+(calendar.get(Calendar.MONTH) + 1))
                        : calendar.get(Calendar.MONTH) + 1)
                .append("-")
                .append(calendar.get(Calendar.YEAR))
                .toString();
    }

    OnDatePickListener onDatePickListener;

    public MFDatePicker(){

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

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        //TODO Fix Single digit problem that fails with the locale
        onDatePickListener.onDatePicked(
                new StringBuilder()
                        .append(day < 10 ? "0" + day : day)
                        .append("-")
                        .append((month + 1) < 10 ? "0" + (month + 1) : month + 1)
                        .append("-")
                        .append(year)
                        .toString()
        );

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
