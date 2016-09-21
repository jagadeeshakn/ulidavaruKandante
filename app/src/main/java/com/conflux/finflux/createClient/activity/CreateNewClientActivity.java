package com.conflux.finflux.createClient.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.conflux.finflux.R;
import com.conflux.finflux.collectionSheet.fragment.CollectionSheetCenterList;
import com.conflux.finflux.core.FinBaseActivity;
import com.conflux.finflux.createClient.fragment.CreateNewClientFragment;

/**
 * Created by jagadeeshakn on 8/8/2016.
 */
public class CreateNewClientActivity extends FinBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createclient_container);
        getActivityComponent().inject(this);
        showBackButton();
        replaceFragment(CreateNewClientFragment.newInstance(), false, R.id.container);
    }
}
