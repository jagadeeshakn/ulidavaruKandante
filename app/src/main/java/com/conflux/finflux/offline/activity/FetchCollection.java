package com.conflux.finflux.offline.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.conflux.finflux.R;
import com.conflux.finflux.core.FinBaseActivity;
import com.conflux.finflux.offline.data.DateString;
import com.conflux.finflux.offline.event.DatePickedEvent;
import com.conflux.finflux.offline.fragment.MeetingsFallCenterListFragment;
import com.conflux.finflux.offline.fragment.dummy.DummyContent;
import com.conflux.finflux.util.Logger;
import com.conflux.finflux.util.event.EventBus;
import com.conflux.finflux.util.management.FragmentManagement;
import com.squareup.otto.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FetchCollection extends FinBaseActivity implements MeetingsFallCenterListFragment.OnListFragmentInteractionListener {

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fetch_collection);
        getActivityComponent().inject(this);
        EventBus.getInstance().register(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Subscribe
    public void datePickedEvent(DatePickedEvent event){
        Logger.d(TAG,"The Selected Dates are "+event.getSelectedDates());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
        ArrayList<DateString> meetingDates = new ArrayList<DateString>();
        for(Date date : event.getSelectedDates()){
            meetingDates.add(new DateString(simpleDateFormat.format(date)));
        }
        MeetingsFallCenterListFragment meetingsFallCenterListFragment = MeetingsFallCenterListFragment.newInstance(1,meetingDates);
        FragmentManagement.replaceFragment(getSupportFragmentManager(),meetingsFallCenterListFragment,true,R.id.fragment);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getInstance().unregister(this);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
