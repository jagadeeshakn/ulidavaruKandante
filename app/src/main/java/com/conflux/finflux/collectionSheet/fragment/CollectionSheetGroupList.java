package com.conflux.finflux.collectionSheet.fragment;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.conflux.finflux.R;
import com.conflux.finflux.collectionSheet.adapter.GroupCollectionListAdapter;
import com.conflux.finflux.collectionSheet.assembler.GroupCollectionDataAssembler;
import com.conflux.finflux.collectionSheet.assembler.SaveCollectionSheetPayloadAssembler;
import com.conflux.finflux.collectionSheet.data.AttendanceType;
import com.conflux.finflux.collectionSheet.data.BulkRepaymentTransactions;
import com.conflux.finflux.collectionSheet.data.Client;
import com.conflux.finflux.collectionSheet.data.CollectionSheetConstants;
import com.conflux.finflux.collectionSheet.data.CollectionSheetData;
import com.conflux.finflux.collectionSheet.data.CollectionSheetDataForAdapter;
import com.conflux.finflux.collectionSheet.data.CollectionSheetPayload;
import com.conflux.finflux.collectionSheet.data.Group;
import com.conflux.finflux.collectionSheet.data.Loan;
import com.conflux.finflux.collectionSheet.data.MeetingFallCenter;
import com.conflux.finflux.collectionSheet.data.SaveCollectionSheetDataForListner;
import com.conflux.finflux.collectionSheet.data.SaveCollectionSheetPayload;
import com.conflux.finflux.collectionSheet.event.ClientCollectionSheetDataChangeListner;
import com.conflux.finflux.collectionSheet.presenter.CollectionSheetPresenter;
import com.conflux.finflux.collectionSheet.presenter.SaveCollectionSheetPresenter;
import com.conflux.finflux.collectionSheet.viewServices.CollectionSheetGroupMvpView;
import com.conflux.finflux.collectionSheet.viewServices.CollectionSheetMvpView;
import com.conflux.finflux.core.FinBaseActivity;
import com.conflux.finflux.core.FinBaseFragment;
import com.conflux.finflux.infrastructure.api.manager.SaveResponse;
import com.conflux.finflux.settings.activity.ApplicationSettings;
import com.conflux.finflux.util.DateHelper;
import com.conflux.finflux.util.ErrorDialogFragment;
import com.conflux.finflux.util.Logger;
import com.conflux.finflux.util.Toaster;
import com.conflux.finflux.util.event.EventBus;
import com.google.gson.Gson;
import com.squareup.otto.Subscribe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by jagadeeshakn on 7/16/2016.
 */
public class CollectionSheetGroupList extends FinBaseFragment implements CollectionSheetGroupMvpView {

    private final String TAG = getClass().getSimpleName();

    @Inject
    SaveCollectionSheetPresenter mCollectionSheetPresenter;

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
    @Bind(R.id.recycler_view_groups)
    RecyclerView recyclerViewGroups;
    @Bind(R.id.tv_amount)
    TextView tv_amount;
    @Bind(R.id.tv_due_amount)
    TextView tv_due_amount;

    ArrayList<CollectionSheetDataForAdapter> collectionSheetDataAdapter;
    List<MeetingFallCenter> meetingFallCenters;
    CollectionSheetData collectionSheetData;
    GroupCollectionListAdapter groupCollectionListAdapter;
    private Long centerId;
    private boolean isAllreadySubmitted;

    public static CollectionSheetGroupList newInstance(ArrayList<CollectionSheetDataForAdapter> collectionSheetDataForAdapter, CollectionSheetData collectionSheetData, String dateString, String centerName,Long calenderId, Long centerId, boolean isAllreadyCollected) {
        CollectionSheetGroupList collectionSheetGroupList = new CollectionSheetGroupList();
        Bundle args = new Bundle();
        args.putParcelableArrayList(CollectionSheetConstants.COLLECTION_SHEET_DATA_FOR_ADAPTER, collectionSheetDataForAdapter);
        args.putParcelable(CollectionSheetConstants.COLLECTION_SHEET, collectionSheetData);
        args.putLong(CollectionSheetConstants.CENTER_ID,centerId);
        args.putBoolean(CollectionSheetConstants.IS_ALLREADY_COLLECTED,isAllreadyCollected);
        SimpleDateFormat dateAsFullString = new SimpleDateFormat("dd MMMM yyyy");
        SimpleDateFormat formatToddMMyyyy = new SimpleDateFormat("dd MM yyyy");
        Date date =null;
        String meetingDate = null;
        try {
            date = dateAsFullString.parse(dateString);
            meetingDate = formatToddMMyyyy.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        args.putString(CollectionSheetConstants.MEETING_DATE, meetingDate);
        args.putString(CollectionSheetConstants.CENTER_NAME, centerName);
        args.putLong(CollectionSheetConstants.CALENDER_ID,calenderId);
        collectionSheetGroupList.setArguments(args);
        return collectionSheetGroupList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((FinBaseActivity) getActivity()).getActivityComponent().inject(this);
        Logger.d(TAG,"on Create ");
        if (getArguments() != null) {
            collectionSheetDataAdapter = getArguments().getParcelableArrayList(CollectionSheetConstants.COLLECTION_SHEET_DATA_FOR_ADAPTER);
            collectionSheetData = getArguments().getParcelable(CollectionSheetConstants.COLLECTION_SHEET);
            meetingFallCenters = getArguments().getParcelableArrayList(CollectionSheetConstants.MEETING_FALL_CENTER);
            meetingDate = getArguments().getString(CollectionSheetConstants.MEETING_DATE);
            Logger.d(TAG,"center meeting data is......."+meetingDate);
            centerName = getArguments().getString(CollectionSheetConstants.CENTER_NAME);
            calenderId = getArguments().getLong(CollectionSheetConstants.CALENDER_ID);
            centerId = getArguments().getLong(CollectionSheetConstants.CENTER_ID);
            isAllreadySubmitted =getArguments().getBoolean(CollectionSheetConstants.IS_ALLREADY_COLLECTED);
        }
    }

    @Subscribe
    public void updateEditedCollectionSheetDetail(ClientCollectionSheetDataChangeListner data){
        Logger.d(TAG,"the collection sheet data is on event "+data.getClientCollectionSheetData());

        for(Group group : collectionSheetData.getGroups()) {
            if(data.getClientCollectionSheetData().getParentId().equals(group.getGroupId())){
                for(Client client : group.getClients()){
                    if(client.getClientId().equals(data.getClientCollectionSheetData().getEntityId()) && data.getClientCollectionSheetData().getEntityType().equals(CollectionSheetConstants.CLIENT)){
                        client.setLoans(data.getClientCollectionSheetData().getLoans());
                        client.setAttendanceType(data.getClientCollectionSheetData().getAttendanceType());
                    }
                }
            }
        }
        ArrayList<CollectionSheetDataForAdapter> dataAdapter = new GroupCollectionDataAssembler().assembleDataForGroupList(collectionSheetData,null);
        for(int i =0 ; i < dataAdapter.size() ; i++){
            collectionSheetDataAdapter.remove(i);
            collectionSheetDataAdapter.add(i,dataAdapter.get(i));
        }
        groupCollectionListAdapter.notifyDataSetChanged();
        Logger.d(TAG, "the collection sheet data adapeter is " + new Gson().toJson(collectionSheetDataAdapter));

        displayTotalDueAndColected();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_group_collection_list, container, false);
        ButterKnife.bind(this,rootView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        Logger.d(TAG, "The Layout is " + mLayoutManager);
        EventBus.getInstance().register(this);
        recyclerViewGroups.setLayoutManager(mLayoutManager);
        mCollectionSheetPresenter.attachView(this);
        getToolbar();
        setToolbarTitle(getString(R.string.collection_sheet));
        setCenterDetails();
        showCenterDetailsList();

        return rootView;
    }

    public void setCenterDetails() {
        try {
            Logger.d(TAG,"the meeting date is "+meetingDate);
            Spanned spanned = DateHelper.getDateFormatTodisplay(meetingDate);
            tv_meeting_date.setText(spanned);
            tv_center_name.setText(centerName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void showCenterDetailsList() {
        groupCollectionListAdapter = new GroupCollectionListAdapter(getActivity(), collectionSheetDataAdapter);
        recyclerViewGroups.setAdapter(groupCollectionListAdapter);
        ((GroupCollectionListAdapter)groupCollectionListAdapter).setOnItemClickListener(new GroupCollectionListAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if(collectionSheetDataAdapter.get(position).getEntityType().equals(CollectionSheetConstants.CLIENT)) {
                    ClientLoanProductsDialogFragment clientLoanProductsDialogFragment = ClientLoanProductsDialogFragment.newInstance(collectionSheetDataAdapter.get(position), collectionSheetData);
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                            .beginTransaction();
                    fragmentTransaction.addToBackStack(CollectionSheetConstants.LOAN_PRODUCT_DIALOG_FRAGMENT);
                    clientLoanProductsDialogFragment.show(getChildFragmentManager(), CollectionSheetConstants.LOAN_PRODUCT_DIALOG_FRAGMENT);
                }
            }
        });
        displayTotalDueAndColected();
    }

    private void displayTotalDueAndColected(){
        Double totalDue = new GroupCollectionDataAssembler().claculateGroupTotal(collectionSheetDataAdapter, CollectionSheetConstants.TOTAL_DUE);
        Double totalCollected = new GroupCollectionDataAssembler().claculateGroupTotal(collectionSheetDataAdapter, CollectionSheetConstants.TOTAL_COLLECTED);
        tv_due_amount.setText(String.valueOf(totalDue));
        tv_amount.setText(String.valueOf(totalCollected));
    }



    @OnClick(R.id.submit)
    public void submit(View view) {
        if(!isAllreadySubmitted){
            SaveCollectionSheetPayload saveCollectionSheetPayload = new SaveCollectionSheetPayloadAssembler().assemblePayload(collectionSheetDataAdapter,calenderId, meetingDate);
            Logger.d(TAG,"the Payload is "+saveCollectionSheetPayload);
            mCollectionSheetPresenter.saveCollectionSheet(centerId,saveCollectionSheetPayload);
        }
    }

    @Override
    public void showProgressbar(boolean b) {
        if(b){
            showFinfluxProgressDialog("Please Wait");
        }else {
            hideFinfluxProgressDialog();
        }
    }

    @Override
    public void showCollectionSheetSaved(SaveResponse saveResponse) {
        Logger.d(TAG,"Success");
        Toaster.show(rootView,R.string.collection_sheet_save_successfull);
        //call print activity
        EventBus.getInstance().post(new SaveCollectionSheetDataForListner(centerId,new Double(tv_amount.getText().toString()),false));
        getActivity().onBackPressed();
    }

    @Override
    public void showFetchingError(HttpException response) {
        Response response1 = response.response();
        Logger.d(TAG, "Failure"+ getStringFromInputStream(response1.errorBody().byteStream()));
    }

    public static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCollectionSheetPresenter.detachView();
        EventBus.getInstance().unregister(this);
    }
}