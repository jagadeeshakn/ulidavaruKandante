package com.conflux.finflux.finflux.dashboard.activity;

import android.os.Bundle;

import com.conflux.finflux.finflux.R;

/**
 * Created by jagadeeshakn on 7/2/2016.
 */
public class DashBoardActivity extends NavigationWindowActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

     // setup navigation drawer
        setupNavigationBar();
    }
}
