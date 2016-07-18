package com.conflux.finflux.offline.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.conflux.finflux.R;
import com.conflux.finflux.offline.event.DatePickedEvent;
import com.conflux.finflux.offline.fragment.DatePicker;
import com.conflux.finflux.util.Logger;
import com.conflux.finflux.util.event.EventBus;
import com.conflux.finflux.util.management.FragmentManagement;
import com.squareup.otto.Subscribe;

/**
 * A placeholder fragment containing a simple view.
 */
public class FetchCollectionFragment extends Fragment {

    private final String TAG = getClass().getSimpleName();
    private View rootView;

    public FetchCollectionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_fetch_collection, container, false);
        EventBus.getInstance().register(this);
        FragmentManager fragmentManagement = getActivity().getSupportFragmentManager();
        fragmentManagement.beginTransaction();
        DatePicker datePicker = new DatePicker();
        FragmentManagement.replaceFragment(getActivity().getSupportFragmentManager(),datePicker,true,R.id.fragment);
        return rootView;
    }

    @Subscribe
    public void datePickedEvent(DatePickedEvent event){
        Logger.d(TAG,"The Selected Dates are in list are "+event.getSelectedDates());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getInstance().unregister(this);
    }
}
