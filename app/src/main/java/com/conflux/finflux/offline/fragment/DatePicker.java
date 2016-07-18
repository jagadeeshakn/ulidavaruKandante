package com.conflux.finflux.offline.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.conflux.finflux.R;
import com.conflux.finflux.offline.event.DatePickedEvent;
import com.conflux.finflux.offline.exception.DateRangeException;
import com.conflux.finflux.offline.validator.DateRangeValidator;
import com.conflux.finflux.util.Logger;
import com.conflux.finflux.util.event.EventBus;
import com.squareup.timessquare.CalendarPickerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DatePicker extends Fragment {

    private final String TAG = getClass().getSimpleName();

    @Bind(R.id.calendar_view)
    CalendarPickerView calendarPickerView;
    @Bind(R.id.submit)
    Button submitButton;

    private View rootview;
    private int mDay;
    private int mMonth;
    private int mYear;
    Calendar nextYear;
    private OnFragmentInteractionListener mListener;

    DatePickerDialog datePickerDialog;
    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int day) {
            Logger.d(TAG,"Selected date is "+year+"- "+month+"- "+day);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd mm yyyy");
            String date = day+" "+month+" "+year;
            Date formatedDate= null;
            try {
                formatedDate = simpleDateFormat.parse(date);
                calendarPickerView.init(formatedDate,nextYear.getTime()).inMode(CalendarPickerView.SelectionMode.RANGE);
            } catch (ParseException e) {
                e.printStackTrace();
            }


        }
    };

    public DatePicker() {
        // Required empty public constructor
    }


    public static DatePicker newInstance(String param1, String param2) {
        DatePicker fragment = new DatePicker();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_date_picker, container, false);
        ButterKnife.bind(this,rootview);
        EventBus.getInstance().register(this);
        nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        Date today = new Date();
        calendarPickerView.init(today, nextYear.getTime()).inMode(CalendarPickerView.SelectionMode.RANGE);
        setHasOptionsMenu(true);
        return rootview;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_date_pick,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Logger.d(TAG,"The option selected is "+item.getItemId());
        switch (item.getItemId()){
            case R.id.action_change_current_date:
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                    datePickerDialog = new DatePickerDialog(getActivity(),dateSetListener,mYear,mMonth,mDay);
                datePickerDialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @OnClick(R.id.submit)
    public void submitDate(View view){
        Logger.d(TAG,"Submit the dates button clicked");
        ArrayList<Date> dates = (ArrayList<Date>) calendarPickerView.getSelectedDates();
        try {
            new DateRangeValidator(TAG,getActivity(),dates,0).validate();
            EventBus.getInstance().post(new DatePickedEvent(dates));
        }catch (DateRangeException e){
            displayAlertMessagewithConfirmationButton(e.getMessage(),getActivity());
        }


    }
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
      /*  if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getInstance().unregister(this);
        mListener = null;
    }

    private static void displayAlertMessagewithConfirmationButton(String message,Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.alert_message));
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
