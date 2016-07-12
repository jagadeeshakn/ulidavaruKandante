package com.conflux.finflux.finflux.collectionSheet.activity;

import android.os.Bundle;

import com.conflux.finflux.finflux.R;
import com.conflux.finflux.finflux.collectionSheet.fragment.CollectionSheetCenterList;
import com.conflux.finflux.finflux.core.FinBaseActivity;

/**
 * Created by jagadeeshakn on 7/5/2016.
 */
public class CollectionSheetActivity extends FinBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_container);
        getActivityComponent().inject(this);
        replaceFragment(CollectionSheetCenterList.newInstance(),false,R.id.container);
    }
}
