package com.conflux.finflux.finflux.offline.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.conflux.finflux.finflux.R;
import com.conflux.finflux.finflux.infrastructure.FinfluxApplication;
import com.conflux.finflux.finflux.injection.component.ActivityComponent;
import com.conflux.finflux.finflux.injection.module.ActivityModule;
import com.conflux.finflux.finflux.util.event.EventBus;

import javax.inject.Inject;

public class OfflineManagement extends AppCompatActivity {
    private ActivityComponent mActivityComponent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_management);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}
