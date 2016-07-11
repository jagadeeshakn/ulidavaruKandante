package com.conflux.finflux.finflux.offline.fragment;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.conflux.finflux.finflux.R;
import com.conflux.finflux.finflux.collectionSheet.activity.CollectionSheetActivity;
import com.conflux.finflux.finflux.infrastructure.FinfluxApplication;
import com.conflux.finflux.finflux.injection.component.ActivityComponent;
import com.conflux.finflux.finflux.injection.module.ActivityModule;
import com.conflux.finflux.finflux.offline.activity.FetchCollection;
import com.conflux.finflux.finflux.offline.activity.OfflineManagement;
import com.conflux.finflux.finflux.util.Logger;
import com.conflux.finflux.finflux.util.event.EventBus;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class OfflineManagementFragment extends Fragment {
    private final String TAG = getClass().getSimpleName();
    private ActivityComponent mActivityComponent;
    private View rootView;
    Bus eventBus;

    @Bind(R.id.fetch_collection)
    CardView fetchCollection;
    @Bind(R.id.push_collection)
    CardView pushCollection;

    public OfflineManagementFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        EventBus.getInstance().register(this);
        rootView = inflater.inflate(R.layout.fragment_offline_management, container, false);
        ButterKnife.bind(this,rootView);
        Logger.d(TAG,"onCreate View");
        return  rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logger.d(TAG,"onViewCreated");

    }

    @OnClick(R.id.fetch_collection)
    public void initiateFetchCollectionActivity(){
        Logger.d(TAG,"Initialized fetch collection Activity");
        startActivity(new Intent(getActivity(),FetchCollection.class));
    }
}
