package com.conflux.finflux.collectionSheet.fragment;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.conflux.finflux.R;
import com.conflux.finflux.collectionSheet.adapter.GroupCollectionListAdapter;
import com.conflux.finflux.collectionSheet.data.AttendanceType;
import com.conflux.finflux.collectionSheet.data.BulkRepaymentTransactions;
import com.conflux.finflux.collectionSheet.data.CollectionSheetConstants;
import com.conflux.finflux.collectionSheet.data.CollectionSheetData;
import com.conflux.finflux.collectionSheet.data.CollectionSheetDataForAdapter;
import com.conflux.finflux.collectionSheet.data.CollectionSheetPayload;
import com.conflux.finflux.collectionSheet.data.Loan;
import com.conflux.finflux.collectionSheet.data.MeetingFallCenter;
import com.conflux.finflux.collectionSheet.presenter.CollectionSheetPresenter;
import com.conflux.finflux.collectionSheet.viewServices.CollectionSheetGroupMvpView;
import com.conflux.finflux.collectionSheet.viewServices.CollectionSheetMvpView;
import com.conflux.finflux.core.FinBaseActivity;
import com.conflux.finflux.core.FinBaseFragment;
import com.conflux.finflux.infrastructure.api.manager.SaveResponse;
import com.conflux.finflux.settings.activity.ApplicationSettings;
import com.conflux.finflux.util.DateHelper;
import com.conflux.finflux.util.ErrorDialogFragment;
import com.conflux.finflux.util.Logger;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnItemClick;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by jagadeeshakn on 7/16/2016.
 */
public class CollectionSheetGroupList extends FinBaseFragment implements CollectionSheetGroupMvpView {

    private final String TAG = getClass().getSimpleName();

    @Inject
    CollectionSheetPresenter mCollectionSheetPresenter;

    private View rootView;
    private String meetingDate;
    private String centerName;
    private Long calenderId;

    @Bind(R.id.center_name_header)
    LinearLayout form_header;
    @Bind(R.id.tv_meeting_date)
    TextView tv_meeting_date;
    @Bind(R.id.tv_center_name)
    TextView tv_center_name;
    @Bind(R.id.tv_center_name_bottom)
    TextView tv_center_name_bottom;
    @Bind(R.id.group_recycler_view)
    RecyclerView group_recycler_view;
    @Bind(R.id.tv_amount)
    TextView tv_amount;
    @Bind(R.id.tv_due_amount)
    TextView tv_due_amount;

    ArrayList<CollectionSheetDataForAdapter> collectionSheetDataAdapter;
    List<MeetingFallCenter> meetingFallCenters;
    CollectionSheetData collectionSheetData;

    public static CollectionSheetGroupList newInstance(ArrayList<CollectionSheetDataForAdapter> collectionSheetDataForAdapter, CollectionSheetData collectionSheetData, String dateString, String centerName,Long calenderId) {
        CollectionSheetGroupList collectionSheetGroupList = new CollectionSheetGroupList();
        Bundle args = new Bundle();
        args.putParcelableArrayList(CollectionSheetConstants.COLLECTION_SHEET_DATA_FOR_ADAPTER, collectionSheetDataForAdapter);
        args.putParcelable(CollectionSheetConstants.COLLECTION_SHEET, collectionSheetData);
        args.putString(CollectionSheetConstants.MEETING_DATE, dateString);
        args.putString(CollectionSheetConstants.CENTER_NAME, centerName);
        args.putLong(CollectionSheetConstants.CALENDER_ID,calenderId);
        return collectionSheetGroupList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((FinBaseActivity) getActivity()).getActivityComponent().inject(this);
        if (getArguments() != null) {
            collectionSheetDataAdapter = getArguments().getParcelableArrayList(CollectionSheetConstants.COLLECTION_SHEET_DATA_FOR_ADAPTER);
            collectionSheetData = getArguments().getParcelable(CollectionSheetConstants.COLLECTION_SHEET);
            meetingFallCenters = getArguments().getParcelableArrayList(CollectionSheetConstants.MEETING_FALL_CENTER);
            meetingDate = getArguments().getString(CollectionSheetConstants.MEETING_DATE);
            Logger.d(TAG,"center meeting data is......."+meetingDate);
            centerName = getArguments().getString(CollectionSheetConstants.CENTER_NAME);
            calenderId = getArguments().getLong(CollectionSheetConstants.CALENDER_ID);
        }
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflater.inflate(R.layout.fragment_group_collection_list, container, false);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        group_recycler_view.setLayoutManager(mLayoutManager);
        getToolbar();
        setToolbarTitle(getString(R.string.collection_sheet));
        setCenterDetails();
        showCenterDetailsList();
        return rootView;
    }

    public void setCenterDetails() {
        try {
            Spanned spanned = DateHelper.getDateFormatTodisplay(meetingDate);
            tv_meeting_date.setText(spanned);
            tv_center_name.setText(centerName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mCollectionSheetPresenter.detachView();
    }

    public void showCenterDetailsList() {
        GroupCollectionListAdapter groupCollectionListAdapter = new GroupCollectionListAdapter(getActivity(), collectionSheetDataAdapter);
        group_recycler_view.setAdapter(groupCollectionListAdapter);
    }

    @OnItemClick(R.id.group_recycler_view)
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Logger.d(TAG, "list item " + i + " clicked");
        if (collectionSheetDataAdapter.get(i).getEntityType().equals(CollectionSheetConstants.CLIENT)) {
            Logger.d(TAG, "item Selected is a client");

            ClientLoanProductsDialogFragment clientLoanProductsDialogFragment = ClientLoanProductsDialogFragment.newInstance(collectionSheetDataAdapter.get(i),collectionSheetData);
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                    .beginTransaction();
            fragmentTransaction.addToBackStack(CollectionSheetConstants.LOAN_PRODUCT_DIALOG_FRAGMENT);
            // clientLoanProductsDialogFragment.show(fragmentTransaction,"client loan product fragment");
        }
    }

    private void calculateTotalCollectedAmount(ArrayList<CollectionSheetDataForAdapter> collectionSheetDataForAdapters) {
        double collectedAmount = 0;
        for (CollectionSheetDataForAdapter collectionSheetDataForAdapter : collectionSheetDataForAdapters) {
            if (collectionSheetDataForAdapter.getEntityType().equals(CollectionSheetConstants.GROUP)) {
                if (collectionSheetDataForAdapter.getCollectedAmount() != null) {

                    collectedAmount = collectedAmount + collectionSheetDataForAdapter.getCollectedAmount();
                } else {
                    collectedAmount = collectedAmount + collectionSheetDataForAdapter.getDueAmount();
                }
            }
        }
        Logger.d(TAG, "the collected amount from groups is " + collectedAmount);
    }

    private static CollectionSheetPayload generateCollectionSheetPayload(ArrayList<CollectionSheetDataForAdapter> collectionSheetDataForAdapters, String meetingDate, CollectionSheetData collectionSheet, Long calendarId){
        CollectionSheetPayload collectionSheetPayload = new CollectionSheetPayload();
        String transactionDate = DateHelper.getDateFormatFullName(meetingDate);
        ArrayList<AttendanceType> clientAttendance = new ArrayList<AttendanceType>();
        ArrayList<BulkRepaymentTransactions> bulkRepaymentTransactions = new ArrayList<BulkRepaymentTransactions>();
        for (CollectionSheetDataForAdapter collectionSheetDataForAdapter : collectionSheetDataForAdapters) {
            if (collectionSheetDataForAdapter.getEntityType().equals(CollectionSheetConstants.CLIENT)) {
                /*for (AttendanceType attendance : collectionSheet.getAttendanceTypeOptions()) {
                    if (attendance.getValue().toLowerCase().equals(CollectionSheetConstants.PRESENT)) {
                        AttendanceType attendanceType = new AttendanceType(collectionSheetDataForAdapter.getEntityId(),attendance.getAttendanceTypeId());
                        clientAttendance.add(attendanceType);
                    }
                }*/

                for (Loan loan : collectionSheetDataForAdapter.getLoans()) {
                    BulkRepaymentTransactions bulkRepaymentTransaction = new BulkRepaymentTransactions();
                    bulkRepaymentTransaction.setLoanId(loan.getLoanId());
                    bulkRepaymentTransaction.setTransactionAmount(loan.getTotalDue());
                    bulkRepaymentTransactions.add(bulkRepaymentTransaction);
                }
            }
        }
            collectionSheetPayload.setCalendarId(calendarId);
            collectionSheetPayload.setDateFormat(CollectionSheetConstants.DATE_FORMAT);
            collectionSheetPayload.setClientsAttendance(clientAttendance);
            collectionSheetPayload.setTransactionDate(transactionDate);
            collectionSheetPayload.setBulkRepaymentTransactions(bulkRepaymentTransactions);
            return collectionSheetPayload;
    }

    @OnClick(R.id.submit)
    public void submit(View view) {

    }

    @Override
    public void showProgressbar(boolean b) {

    }

    @Override
    public void showCollectionSheetSaved(SaveResponse saveResponse) {

    }

    @Override
    public void showFetchingError(HttpException response) {

    }
}