package com.conflux.finflux.finflux.collectionSheet.activity;

import android.os.Bundle;

import com.conflux.finflux.finflux.R;
import com.conflux.finflux.finflux.dashboard.activity.NavigationWindowActivity;

/**
 * Created by jagadeeshakn on 7/5/2016.
 */
public class CollectionSheetActivity extends NavigationWindowActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_container);
        showBackButton();
    }
}
