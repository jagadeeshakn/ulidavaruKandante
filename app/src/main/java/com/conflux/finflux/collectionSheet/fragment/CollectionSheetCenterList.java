package com.conflux.finflux.collectionSheet.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.conflux.finflux.R;
import com.conflux.finflux.collectionSheet.adapter.CenterCollectionListAdapter;
import com.conflux.finflux.collectionSheet.assembler.GroupCollectionDataAssembler;
import com.conflux.finflux.collectionSheet.data.Client;
import com.conflux.finflux.collectionSheet.data.CollectionSheetConstants;
import com.conflux.finflux.collectionSheet.data.CollectionSheetData;
import com.conflux.finflux.collectionSheet.data.CollectionSheetDataForAdapter;
import com.conflux.finflux.collectionSheet.data.Group;
import com.conflux.finflux.collectionSheet.data.Loan;
import com.conflux.finflux.collectionSheet.data.MeetingFallCenter;
import com.conflux.finflux.collectionSheet.data.Payload;
import com.conflux.finflux.collectionSheet.data.ProductiveCollectionData;
import com.conflux.finflux.collectionSheet.data.SaveCollectionSheetDataForListner;
import com.conflux.finflux.collectionSheet.event.SaveCollectionSheetEventListner;
import com.conflux.finflux.collectionSheet.presenter.CollectionSheetPresenter;
import com.conflux.finflux.collectionSheet.viewServices.CollectionSheetMvpView;
import com.conflux.finflux.core.FinBaseActivity;
import com.conflux.finflux.core.FinBaseFragment;
import com.conflux.finflux.db.LoginUser;
import com.conflux.finflux.infrastructure.analytics.services.ApplicationAnalytics;
import com.conflux.finflux.util.AppConstants;
import com.conflux.finflux.util.DateHelper;
import com.conflux.finflux.util.ErrorDialogFragment;
import com.conflux.finflux.util.FragmentConstants;
import com.conflux.finflux.util.Logger;
import com.conflux.finflux.util.MFDatePicker;
import com.conflux.finflux.util.Network;
import com.conflux.finflux.util.bluetooth.services.BroadCastReceiverService;
import com.conflux.finflux.util.event.EventBus;
import com.conflux.finflux.util.network.ChangeReceiver;
import com.google.gson.Gson;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by jagadeeshakn on 7/8/2016.
 */
public class CollectionSheetCenterList extends FinBaseFragment implements CollectionSheetMvpView, MFDatePicker.OnDatePickListener {

    //Constants...
    private final String TAG = getClass().getSimpleName();

    @Inject
    CollectionSheetPresenter mCollectionSheetPresenter;


    private View rootView;
    private android.support.v4.app.DialogFragment mfDatePicker;

    @Bind(R.id.tv_meeting_date)
    TextView tv_today_date;
    @Bind(R.id.iv_date_picker)
    ImageButton iv_date_picker;
    @Bind(R.id.my_recycler_view)
    RecyclerView lv_recycler_view;
    @Bind(R.id.tv_total_due_collection)
    TextView tv_total_collection;
    @Bind(R.id.tv_actual_collection)
    TextView tv_actual_collection;
    @Bind(R.id.ll_bottom_layout)
    LinearLayout ll_bottom_layout;

    private int day;
    private String dateString;
    private Long staffId;
    private Long officeId;
    private String centerName;
    private Long calendarId;
    private String en;
    private String code;

    private List<MeetingFallCenter> meetingFallCenterList;
    private CenterCollectionListAdapter collectionListAdapter;

    public static CollectionSheetCenterList newInstance() {
        CollectionSheetCenterList centerList = new CollectionSheetCenterList();

        return centerList;
    }

    public CollectionSheetCenterList() {
        //Required public empty constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((FinBaseActivity) getActivity()).getActivityComponent().inject(this);
        ApplicationAnalytics.sendEventLogs(TAG, CollectionSheetConstants.PRODUCTIVE_COLLECTION_SHEET);
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_center_collection_sheet, container, false);
            ButterKnife.bind(this, rootView);
            final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            lv_recycler_view.setLayoutManager(layoutManager);
            lv_recycler_view.setItemAnimator(new DefaultItemAnimator());
            mCollectionSheetPresenter.attachView(this);
            EventBus.getInstance().register(this);
            getToolbar();
            setToolbarTitle(getString(R.string.collection_sheet));
            setCenterMeetingDate();
            inflateMeetingDate();
        }
        if (!mCollectionSheetPresenter.isViewAttached()) {
            mCollectionSheetPresenter.attachView(this);
        }

        return rootView;
    }

    public void setCenterMeetingDate() {
        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        String leadingZero = DateHelper.removeLeadingZeroes(String.valueOf(day));
        String todayDate = DateHelper.getDayNumberSuffix(Integer.parseInt(leadingZero));
        Spanned spanned = Html.fromHtml(leadingZero + todayDate + getString(R.string.center_meeting));
        tv_today_date.setText(spanned);
        dateString = MFDatePicker.getDatePickedAsString().replace("-", " ");
        basicCenterData();
    }

    public void basicCenterData() {
        try {
            Realm realm = Realm.getDefaultInstance();
            LoginUser user = realm.where(LoginUser.class).findFirst();
            officeId = user.getOfficeId();
            staffId = user.getStaffid();
            setProductiveCollectionPayload(staffId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inflateMeetingDate() {
        mfDatePicker = MFDatePicker.newInsance(this);
        iv_date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mfDatePicker.show(getActivity().getSupportFragmentManager(), CollectionSheetConstants.DFRAG_DATE_PICKER);
            }
        });
    }

    @Override
    public void onDatePicked(String date) {
        Logger.d(TAG, "selected meeting date iss    " + date);
        dateString = date.replace("-", " ");
        String dayString[] = date.split("-");
        String selectedDay = dayString[0].toString();
        selectedDay = DateHelper.removeLeadingZeroes(selectedDay);
        String dayNumberSuffix = DateHelper.getDayNumberSuffix(Integer.parseInt(selectedDay));
        Spanned spanned = Html.fromHtml(selectedDay + dayNumberSuffix + getString(R.string.center_meeting));
        tv_today_date.setText(spanned);
        if(collectionListAdapter != null && meetingFallCenterList != null){
            meetingFallCenterList.clear();
            collectionListAdapter.notifyDataSetChanged();
        }
        setProductiveCollectionPayload(staffId);

    }

    public void setProductiveCollectionPayload(Long staffId) {
        Payload payload = new Payload();
        try {
            if (staffId != 0) {
                payload.setDateFormat(CollectionSheetConstants.DATE_FORMAT);
                payload.setLocale(CollectionSheetConstants.EN);
                payload.setMeetingDate(dateString);
                payload.setOfficeId(officeId);
                payload.setStaffId(staffId);
                mCollectionSheetPresenter.loadProductiveCollectionSheet(payload);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.d(TAG, "staff is not assigned to user");
        }
    }


    @Override
    public void showProductiveCollectionSheet(ArrayList<ProductiveCollectionData> productiveCollectionData) {
        Logger.d(TAG, "productive collection sheet generated successfully     " + productiveCollectionData);
        try {
            if (productiveCollectionData.size() > 0) {
                ll_bottom_layout.setVisibility(View.VISIBLE);
                showCenterCollectionList(productiveCollectionData);

            } else {

                ll_bottom_layout.setVisibility(View.INVISIBLE);
                ErrorDialogFragment errorDialogFragment = ErrorDialogFragment.newInstance(TAG, getString(R.string.no_collection_sheet) + " " + dateString, getString(R.string.alert));
                errorDialogFragment.show(getChildFragmentManager(), FragmentConstants.FRAG_ERROR);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void showCenterCollectionList(ArrayList<ProductiveCollectionData> productiveCollectionDatas) {

        ProductiveCollectionData centerCollectionTaskData = null;
        for (ProductiveCollectionData collectionData : productiveCollectionDatas) {
            centerCollectionTaskData = collectionData;
        }
        try {
            meetingFallCenterList = centerCollectionTaskData.getMeetingFallCenters();
            calculateCenterTotal(meetingFallCenterList);
            collectionListAdapter = new CenterCollectionListAdapter(getActivity(), meetingFallCenterList);
            lv_recycler_view.setAdapter(collectionListAdapter);
            ((CenterCollectionListAdapter) collectionListAdapter).setOnItemClickListener(new CenterCollectionListAdapter.MyClickListener() {
                @Override
                public void onItemClick(int position, View v) {
                    try {
                        if (position != -1) {
                            Payload payload = new Payload();
                            long centerId = meetingFallCenterList.get(position).getId();
                            centerName = meetingFallCenterList.get(position).getName();
                            calendarId = meetingFallCenterList.get(position).getCollectionMeetingCalendar().getId();
                            payload.setDateFormat(CollectionSheetConstants.DATE_FORMAT);
                            payload.setLocale(CollectionSheetConstants.EN);
                            payload.setTransactionDate(dateString);
                            payload.setCalendarId(calendarId);

                            mCollectionSheetPresenter.loadCollectionsForGroup(centerId, payload);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void calculateCenterTotal(List<MeetingFallCenter> centerList) {
        double totalDue = 0;
        double totalCollected = 0;
        for (MeetingFallCenter centerData : centerList) {
            Logger.d(TAG, "the center data is " + centerData);
            if (centerData != null) {
                try {
                    totalDue += centerData.getTotaldue();
                    Logger.d(TAG, "update the centers total amount    " + centerData.getTotalCollected());
                    totalCollected += centerData.getTotalCollected();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        tv_total_collection.setText(String.valueOf(totalDue));
        tv_actual_collection.setText(String.valueOf(totalCollected));

    }

    @Override
    public void showCenterCollectionSheet(CollectionSheetData collectionSheetData, Long centerId) {
        Logger.d(TAG, "centerwise collection sheet generated successfully     " + collectionSheetData);
        GroupCollectionDataAssembler assembler = new GroupCollectionDataAssembler();
        ArrayList<CollectionSheetDataForAdapter> collectionSheetDataForAdapters = assembler.assembleDataForGroupList(collectionSheetData,null);
        boolean isAlreadyCollected = false;
        for(MeetingFallCenter center: meetingFallCenterList){
            if(center.getId().equals(centerId)){
                if(center.getTotalCollected() != 0){
                    isAlreadyCollected = true;
                }
            }
        }
        Logger.d(TAG,"The date string is "+dateString);
        replaceFragment(CollectionSheetGroupList.newInstance(collectionSheetDataForAdapters, collectionSheetData, dateString, centerName,calendarId, centerId, isAlreadyCollected), true, R.id.container);
    }




    @Override
    public void showFetchingError(HttpException response) {
        if (response != null) {
            Logger.d(TAG, "failure to get collection sheet    " + response.getMessage());
        }
    }


    @Override
    public void showProgressbar(boolean b) {
        if (b) {
            showFinfluxProgressDialog();
        } else {
            hideFinfluxProgressDialog();
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCollectionSheetPresenter.detachView();
        EventBus.getInstance().unregister(this);
    }

    @Subscribe
    public void updateCenterDetails(SaveCollectionSheetEventListner eventListner){
        for(MeetingFallCenter center : meetingFallCenterList){
            if(center.getId().equals(eventListner.getSaveCollectionSheetDataForListner().getCenterId())){
                center.setTotalCollected(eventListner.getSaveCollectionSheetDataForListner().getCollectedAmount());
            }
        }
        collectionListAdapter.notifyDataSetChanged();
        calculateCenterTotal(meetingFallCenterList);
    }

    @Override
    public void setConnectivitytatus(boolean isOnline) {
        super.setConnectivitytatus(isOnline);
        Logger.d(TAG,"In center List "+isOnline);
    }
}
