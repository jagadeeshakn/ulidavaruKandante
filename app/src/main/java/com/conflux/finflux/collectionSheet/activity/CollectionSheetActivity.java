package com.conflux.finflux.collectionSheet.activity;

import android.os.Bundle;

import com.conflux.finflux.R;
import com.conflux.finflux.collectionSheet.fragment.CollectionSheetCenterList;
import com.conflux.finflux.core.FinBaseActivity;
import com.conflux.finflux.util.event.EventBus;

/**
 * Created by jagadeeshakn on 7/5/2016.
 */
public class CollectionSheetActivity extends FinBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_container);
        getActivityComponent().inject(this);
        showBackButton();
        replaceFragment(CollectionSheetCenterList.newInstance(),false,R.id.container);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getInstance().unregister(this);
    }
}
