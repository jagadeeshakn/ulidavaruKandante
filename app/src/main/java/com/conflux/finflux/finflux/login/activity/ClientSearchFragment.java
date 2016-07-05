package com.conflux.finflux.finflux.login.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.conflux.finflux.finflux.R;

/**
 * Created by jagadeeshakn on 7/4/2016.
 */
public class ClientSearchFragment extends Fragment {

    private static final String LOG_TAG = ClientSearchFragment.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_client_search, null);

        return rootView;
    }
}
