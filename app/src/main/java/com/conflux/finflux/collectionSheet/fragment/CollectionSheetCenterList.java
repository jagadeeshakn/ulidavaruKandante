package com.conflux.finflux.collectionSheet.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.conflux.finflux.R;
import com.conflux.finflux.collectionSheet.adapter.CenterCollectionListAdapter;
import com.conflux.finflux.collectionSheet.data.Client;
import com.conflux.finflux.collectionSheet.data.CollectionSheetConstants;
import com.conflux.finflux.collectionSheet.data.CollectionSheetData;
import com.conflux.finflux.collectionSheet.data.CollectionSheetDataForAdapter;
import com.conflux.finflux.collectionSheet.data.FinfluxGroup;
import com.conflux.finflux.collectionSheet.data.Loan;
import com.conflux.finflux.collectionSheet.data.MeetingFallCenter;
import com.conflux.finflux.collectionSheet.data.Payload;
import com.conflux.finflux.collectionSheet.data.ProductiveCollectionData;
import com.conflux.finflux.collectionSheet.presenter.CollectionSheetPresenter;
import com.conflux.finflux.collectionSheet.viewServices.CollectionSheetMvpView;
import com.conflux.finflux.core.FinBaseActivity;
import com.conflux.finflux.core.FinBaseFragment;
import com.conflux.finflux.db.LoginUser;
import com.conflux.finflux.login.data.User;
import com.conflux.finflux.util.DateHelper;
import com.conflux.finflux.util.ErrorDialogFragment;
import com.conflux.finflux.util.FragmentConstants;
import com.conflux.finflux.util.Logger;
import com.conflux.finflux.util.MFDatePicker;
import com.conflux.finflux.util.PrefManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.conflux.finflux.R;
import com.conflux.finflux.core.FinBaseActivity;
import com.conflux.finflux.core.FinBaseFragment;

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
    private String en;
    private String code;

    private List<MeetingFallCenter> meetingFallCenterList;

    public static CollectionSheetCenterList newInstance() {
        CollectionSheetCenterList centerList = new CollectionSheetCenterList();

        return centerList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((FinBaseActivity) getActivity()).getActivityComponent().inject(this);
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_center_collection_sheet, container, false);
        ButterKnife.bind(this, rootView);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lv_recycler_view.setLayoutManager(layoutManager);
        lv_recycler_view.setItemAnimator(new DefaultItemAnimator());

        mCollectionSheetPresenter.attachView(this);
        getToolbar();
        setToolbarTitle(getString(R.string.collection_sheet));
        setCenterMeetingDate();
        inflateMeetingDate();
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
        Realm realm = Realm.getDefaultInstance();
        LoginUser user = realm.where(LoginUser.class).findFirst();
        officeId = user.getOfficeId();
        staffId = user.getStaffid();
        setProductiveCollectionPayload(staffId);
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
            CenterCollectionListAdapter collectionListAdapter = new CenterCollectionListAdapter(getActivity(), meetingFallCenterList);
            lv_recycler_view.setAdapter(collectionListAdapter);
            ((CenterCollectionListAdapter) collectionListAdapter).setOnItemClickListener(new CenterCollectionListAdapter.MyClickListener() {
                @Override
                public void onItemClick(int position, View v) {
                    int mPosition = position;
                    try {
                        if (mPosition != -1) {
                            Payload payload = new Payload();
                            payload.setDateFormat(CollectionSheetConstants.DATE_FORMAT);
                            payload.setLocale(CollectionSheetConstants.EN);
                            payload.setTransactionDate(dateString);
                            payload.setCalendarId(meetingFallCenterList.get(position).getCollectionMeetingCalendar().getCalendarInstanceId());
                            long centerId = meetingFallCenterList.get(position).getId();
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
        //String centerTotalDue = CurrencyFormatter.getCurrencyFormat(en, code, totalDue);
        // String centerTotalCollected = CurrencyFormatter.getCurrencyFormat(en, code, totalCollected);
        tv_total_collection.setText(String.valueOf(totalDue));
        tv_actual_collection.setText(String.valueOf(totalCollected));

    }

    @Override
    public void showCenterCollectionSheet(CollectionSheetData collectionSheetData) {
        Logger.d(TAG, "centerwise collection sheet generated successfully     " + collectionSheetData);

        ArrayList<CollectionSheetDataForAdapter> collectionSheetDataForAdapters = getGroupDeatils(collectionSheetData);
        for (CollectionSheetDataForAdapter collectionSheetDataForAdapter : collectionSheetDataForAdapters) {
            if (collectionSheetDataForAdapter.getLoans() != null) {
                for (Loan loan : collectionSheetDataForAdapter.getLoans()) {
                    Logger.d(TAG, "loan product short name  " + loan.getProductShortName() + " total due " + loan.getTotalDue());
                }
            }
        }
    }

    private ArrayList<CollectionSheetDataForAdapter> getGroupDeatils(CollectionSheetData collectionSheetData) {
        ArrayList<CollectionSheetDataForAdapter> collectionSheetDataForAdapter = new ArrayList<CollectionSheetDataForAdapter>();
        for (FinfluxGroup group : collectionSheetData.getGroups()) {
            double totalDue = 0;
            double totalCollected = 0;
            for (Client client : group.getClients()) {
                CollectionSheetDataForAdapter collectionSheetDataForAdapterClient = new CollectionSheetDataForAdapter();
                collectionSheetDataForAdapterClient.setEntityId(client.getClientId());
                collectionSheetDataForAdapterClient.setEntityType(CollectionSheetConstants.CLIENT);
                collectionSheetDataForAdapterClient.setEntityName(client.getClientName());
                collectionSheetDataForAdapterClient.setParentId(group.getGroupId());
                Logger.d(TAG, "the group id " + group.getGroupId());
                double clientToalDue = 0;
                collectionSheetDataForAdapterClient.setLoans(client.getLoans());
                for (Loan loan : client.getLoans()) {
                    clientToalDue = clientToalDue + loan.getTotalDue();
                }
                totalDue = totalDue + clientToalDue;
                collectionSheetDataForAdapterClient.setDueAmount(clientToalDue);
                collectionSheetDataForAdapterClient.setCollectedAmount(clientToalDue);
                collectionSheetDataForAdapterClient.setAttendanceType(client.getAttendanceType());
                collectionSheetDataForAdapter.add(collectionSheetDataForAdapterClient);
            }
            CollectionSheetDataForAdapter collectionSheetDataForAdapterGroup = new CollectionSheetDataForAdapter();
            collectionSheetDataForAdapterGroup.setEntityId(group.getGroupId());
            collectionSheetDataForAdapterGroup.setEntityName(group.getGroupName());
            collectionSheetDataForAdapterGroup.setEntityType(CollectionSheetConstants.GROUP);
            collectionSheetDataForAdapterGroup.setDueAmount(totalDue);
            totalCollected = totalDue;
            collectionSheetDataForAdapterGroup.setCollectedAmount(totalCollected);
            collectionSheetDataForAdapter.add(collectionSheetDataForAdapterGroup);
        }
        return collectionSheetDataForAdapter;
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
    public void onDestroyView() {
        super.onDestroyView();
        mCollectionSheetPresenter.detachView();
    }
}
