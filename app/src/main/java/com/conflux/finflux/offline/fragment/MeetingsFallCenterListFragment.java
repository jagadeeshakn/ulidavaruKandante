package com.conflux.finflux.offline.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.conflux.finflux.R;
import com.conflux.finflux.collectionSheet.data.CollectionSheetConstants;
import com.conflux.finflux.collectionSheet.data.ExtendedProductionCollectiondata;
import com.conflux.finflux.collectionSheet.data.MeetingFallCenter;
import com.conflux.finflux.collectionSheet.data.Payload;
import com.conflux.finflux.collectionSheet.data.ProductiveCollectionData;
import com.conflux.finflux.collectionSheet.presenter.CollectionSheetPresenter;
import com.conflux.finflux.collectionSheet.viewServices.CollectionSheetMvpView;
import com.conflux.finflux.core.FinBaseActivity;
import com.conflux.finflux.core.FinBaseFragment;
import com.conflux.finflux.db.LoginUser;
import com.conflux.finflux.offline.data.CenterWIthMeetingAndCheckedStatus;
import com.conflux.finflux.offline.data.DateString;
import com.conflux.finflux.offline.fragment.dummy.DummyContent;
import com.conflux.finflux.offline.fragment.dummy.DummyContent.DummyItem;
import com.conflux.finflux.util.Logger;

import java.util.ArrayList;

import javax.inject.Inject;

import io.realm.Realm;
import retrofit2.adapter.rxjava.HttpException;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class MeetingsFallCenterListFragment extends FinBaseFragment implements CollectionSheetMvpView {

    private final String TAG = getClass().getSimpleName();
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String MEETING_DATES = "meeting-dates";
    private static final String MEETING_FALL_CENTER = "meeting-fall-center";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private ArrayList<CenterWIthMeetingAndCheckedStatus> centerWIthMeetingAndCheckedStatuses;

    @Inject
    CollectionSheetPresenter mCollectionSheetPresenter;

    private Long officeId;
    private Long staffId;
    private int i=0;
    Realm realm;
    private ArrayList<DateString> meetingDates;
    private ArrayList<ExtendedProductionCollectiondata> extendedProductionCollectiondatas;
    private MeetingsFallCenterListRecyclerViewAdapter meetingsFallCenterListRecyclerViewAdapter;

    public MeetingsFallCenterListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MeetingsFallCenterListFragment newInstance(int columnCount, ArrayList<DateString> meetingdates) {
        MeetingsFallCenterListFragment fragment = new MeetingsFallCenterListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(MEETING_DATES,meetingdates);
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
        ((FinBaseActivity) getActivity()).getActivityComponent().inject(this);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            meetingDates = getArguments().getParcelableArrayList(MEETING_DATES);
            centerWIthMeetingAndCheckedStatuses = getArguments().getParcelableArrayList(MEETING_FALL_CENTER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meetingsfallcenterlist_list, container, false);
        mCollectionSheetPresenter.attachView(this);
        getProductionCollectionSheetData();
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            if(centerWIthMeetingAndCheckedStatuses == null){
                centerWIthMeetingAndCheckedStatuses = new ArrayList<CenterWIthMeetingAndCheckedStatus>();
            }
            meetingsFallCenterListRecyclerViewAdapter = new MeetingsFallCenterListRecyclerViewAdapter(getActivity(),centerWIthMeetingAndCheckedStatuses, mListener);
            recyclerView.setAdapter(meetingsFallCenterListRecyclerViewAdapter);
            meetingsFallCenterListRecyclerViewAdapter.setOnItemClickListener(myClickListener);
        }
        return view;
    }


   MeetingsFallCenterListRecyclerViewAdapter.MyClickListener myClickListener = new MeetingsFallCenterListRecyclerViewAdapter.MyClickListener() {
       @Override
       public void onItemClick(int position, View v) {
           Logger.d(TAG,"The clicked item is "+centerWIthMeetingAndCheckedStatuses.get(position).checkedStatus);
           if(centerWIthMeetingAndCheckedStatuses.get(position).checkedStatus){
               centerWIthMeetingAndCheckedStatuses.get(position).checkedStatus = false;
           }else {
               centerWIthMeetingAndCheckedStatuses.get(position).checkedStatus = true;
           }
           meetingsFallCenterListRecyclerViewAdapter.notifyDataSetChanged();
       }
   };

    private void getProductionCollectionSheetData(){
        basicCenterData();
        preparePayload();
    }

    public void preparePayload(){
        Payload payload=new Payload();
        if (staffId!=0) {
            try {
                payload.setDateFormat(CollectionSheetConstants.dateFormat);
                payload.setLocale(CollectionSheetConstants.EN);
                payload.setMeetingDate(meetingDates.get(i).getDate());
                payload.setOfficeId(officeId);
                payload.setStaffId(staffId);
                mCollectionSheetPresenter.loadProductiveCollectionSheet(payload);
            }catch (Exception e){
                e.printStackTrace();
                Logger.d(TAG,"staff is not assigned to user");
            }
        }
    }

    public void basicCenterData(){
        LoginUser user = realm.where(LoginUser.class).findFirst();
        officeId=user.getOfficeId();
        staffId = user.getStaffid();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public void showProductiveCollectionSheet(ArrayList<ProductiveCollectionData> productiveCollectionData) {
        Logger.d(TAG,"fetched the center "+productiveCollectionData);
        intialiseTheExtendeProductionCollectiondata(i,productiveCollectionData);
        checkForMeetings();
    }

    private void intialiseTheExtendeProductionCollectiondata(final int i,final ArrayList<ProductiveCollectionData> productiveCollectionDatas){
        if(extendedProductionCollectiondatas == null){
            extendedProductionCollectiondatas = new ArrayList<ExtendedProductionCollectiondata>();
        }
        if(centerWIthMeetingAndCheckedStatuses == null){
            centerWIthMeetingAndCheckedStatuses = new ArrayList<CenterWIthMeetingAndCheckedStatus>();
        }
        for(ProductiveCollectionData productiveCollectionData : productiveCollectionDatas) {
            ExtendedProductionCollectiondata extendedProductionCollectiondata = new ExtendedProductionCollectiondata(productiveCollectionData, meetingDates.get(i).getDate(), productiveCollectionData.getMeetingFallCenters().size());
            extendedProductionCollectiondatas.add(extendedProductionCollectiondata);
            for (MeetingFallCenter meetingFallCenter : productiveCollectionData.getMeetingFallCenters()) {
                CenterWIthMeetingAndCheckedStatus centerWIthMeetingAndCheckedStatus = new CenterWIthMeetingAndCheckedStatus();
                centerWIthMeetingAndCheckedStatus.setDate(meetingDates.get(i).getDate());
                centerWIthMeetingAndCheckedStatus.setMeetingFallCenter(meetingFallCenter);
                centerWIthMeetingAndCheckedStatuses.add(centerWIthMeetingAndCheckedStatus);
                meetingsFallCenterListRecyclerViewAdapter.notifyDataSetChanged();
            }
        }
    }

    //if there are items in the meetingDates increment the value of i and call api
    private void checkForMeetings(){
        ++i;
        Logger.d(TAG,"the value of i "+i+"size of array "+meetingDates.size());

        if(i<meetingDates.size()){
            getProductionCollectionSheetData();
        }
    }

    @Override
    public void showFetchingError(HttpException response) {
        checkForMeetings();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
