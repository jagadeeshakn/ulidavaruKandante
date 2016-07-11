package com.conflux.finflux.finflux.offline.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.conflux.finflux.finflux.R;
import com.conflux.finflux.finflux.offline.event.DatePickedEvent;
import com.conflux.finflux.finflux.util.Logger;
import com.conflux.finflux.finflux.util.event.EventBus;
import com.squareup.timessquare.CalendarPickerView;

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

    private OnFragmentInteractionListener mListener;

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
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        Date today = new Date();
        calendarPickerView.init(today, nextYear.getTime()).inMode(CalendarPickerView.SelectionMode.RANGE);
        return rootview;
    }


    @OnClick(R.id.submit)
    public void submitDate(View view){
        Logger.d(TAG,"Submit the dates button clicked");
        ArrayList<Date> dates = (ArrayList<Date>) calendarPickerView.getSelectedDates();
        EventBus.getInstance().post(new DatePickedEvent(dates));
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
