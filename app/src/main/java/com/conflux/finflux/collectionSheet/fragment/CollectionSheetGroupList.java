package com.conflux.finflux.collectionSheet.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.conflux.finflux.R;
import com.conflux.finflux.core.FinBaseActivity;
import com.conflux.finflux.core.FinBaseFragment;

/**
 * Created by jagadeeshakn on 7/16/2016.
 */
public class CollectionSheetGroupList extends FinBaseFragment {

    private  final String TAG=getClass().getSimpleName();

    private View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((FinBaseActivity) getActivity()).getActivityComponent().inject(this);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflater.inflate(R.layout.fragment_group_collection_list,container,false);
        getToolbar();
        setToolbarTitle(getString(R.string.collection_sheet));
        return rootView;
    }

}
