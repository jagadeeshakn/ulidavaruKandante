package com.conflux.finflux.finflux.collectionSheet.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.conflux.finflux.finflux.R;
import com.conflux.finflux.finflux.collectionSheet.activity.CollectionSheetActivity;
import com.conflux.finflux.finflux.collectionSheet.presenter.CollectionSheetPresenter;
import com.conflux.finflux.finflux.collectionSheet.viewServices.CollectionSheetMvpView;
import com.conflux.finflux.finflux.core.FinBaseActivity;
import com.conflux.finflux.finflux.core.FinBaseFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by jagadeeshakn on 7/8/2016.
 */
public class CollectionSheetCenterList extends FinBaseFragment implements CollectionSheetMvpView {

    //Constants...
    private  final String TAG=getClass().getSimpleName();

    @Inject
    CollectionSheetPresenter mCollectionSheetPresenter;

    private View rootView;

    public static CollectionSheetCenterList newInstance(){
        CollectionSheetCenterList centerList=new CollectionSheetCenterList();

        return centerList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((FinBaseActivity)getActivity()).getActivityComponent().inject((CollectionSheetActivity) getActivity());
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_collection_sheet_center,container,false);
        ButterKnife.bind(this,rootView);
        return rootView;
    }

    @Override
    public void showProgressbar(boolean b) {
        if (b){
            showFinfluxProgressDialog();
        }else {
            hideFinfluxProgressDialog();
        }

    }
}
