package com.conflux.finflux.dashboard.activity;

import android.os.Bundle;

import com.conflux.finflux.R;
import com.conflux.finflux.core.FinBaseActivity;

/**
 * Created by jagadeeshakn on 7/2/2016.
 */
public class DashBoardActivity extends FinBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

     // setup navigation drawer
        setupNavigationBar();
    }
}
