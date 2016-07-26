package com.conflux.finflux.offline.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.conflux.finflux.R;
import com.conflux.finflux.collectionSheet.data.CollectionSheetConstants;
import com.conflux.finflux.collectionSheet.data.CollectionSheetData;
import com.conflux.finflux.collectionSheet.data.ExtendedProductionCollectiondata;
import com.conflux.finflux.collectionSheet.data.GenerateCollectionSheetPayloadAssembler;
import com.conflux.finflux.collectionSheet.data.MeetingFallCenter;
import com.conflux.finflux.collectionSheet.data.Payload;
import com.conflux.finflux.collectionSheet.data.ProductiveCollectionData;
import com.conflux.finflux.collectionSheet.presenter.CollectionSheetPresenter;
import com.conflux.finflux.collectionSheet.viewServices.CollectionSheetMvpView;
import com.conflux.finflux.core.FinBaseActivity;
import com.conflux.finflux.core.FinBaseFragment;
import com.conflux.finflux.db.LoginUser;
import com.conflux.finflux.offline.data.CenterListHelper;
import com.conflux.finflux.offline.data.DateString;
import com.conflux.finflux.offline.data.ProductionCollectionSheedDataAssembler;
import com.conflux.finflux.offline.fragment.dummy.DummyContent.DummyItem;
import com.conflux.finflux.util.Logger;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    private ArrayList<CenterListHelper> centerListHelpers;
    private View view;

    @Inject
    CollectionSheetPresenter mCollectionSheetPresenter;
    @Bind(R.id.center_list)
    RecyclerView recyclerView;
    @Bind(R.id.btn_download)
    Button btnDownload;

    private Long officeId;
    private Long staffId;
    private int i = 0;
    private int mCountSelectedCenters = 0;
    Realm realm;
    private ArrayList<DateString> meetingDates;
    private ArrayList<ExtendedProductionCollectiondata> extendedProductionCollectiondatas;
    private MeetingsFallCenterListRecyclerViewAdapter meetingsFallCenterListRecyclerViewAdapter;

    public MeetingsFallCenterListFragment() {
    }

    public static MeetingsFallCenterListFragment newInstance(int columnCount, ArrayList<DateString> meetingdates) {
        MeetingsFallCenterListFragment fragment = new MeetingsFallCenterListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(MEETING_DATES, meetingdates);
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
            centerListHelpers = getArguments().getParcelableArrayList(MEETING_FALL_CENTER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_meetingsfallcenterlist_list, container, false);
        mCollectionSheetPresenter.attachView(this);
        ButterKnife.bind(this, view);
        getProductionCollectionSheetData();
        Context context = view.getContext();
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        if (centerListHelpers == null) {
            centerListHelpers = new ArrayList<CenterListHelper>();
        }
        meetingsFallCenterListRecyclerViewAdapter = new MeetingsFallCenterListRecyclerViewAdapter(getActivity(), centerListHelpers, mListener);
        recyclerView.setAdapter(meetingsFallCenterListRecyclerViewAdapter);
        meetingsFallCenterListRecyclerViewAdapter.setOnItemClickListener(myClickListener);
        return view;
    }


    MeetingsFallCenterListRecyclerViewAdapter.MyClickListener myClickListener = new MeetingsFallCenterListRecyclerViewAdapter.MyClickListener() {
        @Override
        public void onItemClick(int position, View v) {
            Logger.d(TAG, "The clicked item is " + centerListHelpers.get(position).checkedStatus);
            if (centerListHelpers.get(position).checkedStatus) {
                centerListHelpers.get(position).checkedStatus = false;
            } else {
                centerListHelpers.get(position).checkedStatus = true;
            }
            meetingsFallCenterListRecyclerViewAdapter.notifyDataSetChanged();
        }
    };

    private void getProductionCollectionSheetData() {
        basicCenterData();
        preparePayload();
    }

    public void preparePayload() {
        Payload payload = new Payload();
        if (staffId != 0) {
            try {
                payload.setDateFormat(CollectionSheetConstants.DATE_FORMAT);
                payload.setLocale(CollectionSheetConstants.EN);
                payload.setMeetingDate(meetingDates.get(i).getDate());
                payload.setOfficeId(officeId);
                payload.setStaffId(staffId);
                mCollectionSheetPresenter.loadProductiveCollectionSheet(payload);
            } catch (Exception e) {
                e.printStackTrace();
                Logger.d(TAG, "staff is not assigned to user");
            }
        }
    }

    public void basicCenterData() {
        LoginUser user = realm.where(LoginUser.class).findFirst();
        officeId = user.getOfficeId();
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
        Logger.d(TAG, "fetched the center " + productiveCollectionData);
        intialiseTheExtendeProductionCollectiondata(i, productiveCollectionData);
        checkForMeetings();
    }

    @OnClick(R.id.btn_download)
    public void onClickDownload(View view) {
        Logger.d(TAG, "Download ");
        btnDownload.setClickable(false);
        mCountSelectedCenters = 0;
        downloadSelectedCenters();
    }

    private void downloadSelectedCenters() {
        if (centerListHelpers.size() > 0) {
            downloadNextCenter();
        }
    }

    private void downloadNextCenter() {
        if (mCountSelectedCenters < centerListHelpers.size()) {
            if(centerListHelpers.get(mCountSelectedCenters).checkedStatus) {
                downloadCenterwithId(centerListHelpers.get(mCountSelectedCenters),
                        centerListHelpers.get(mCountSelectedCenters).getMeetingFallCenter().getId());
            }else {
                ++mCountSelectedCenters;
                downloadNextCenter();
            }
        }else {
            btnDownload.setClickable(true);
        }
    }

    private void downloadCenterwithId(final CenterListHelper centerListHelper, final Long centerId) {
        Payload payload = new GenerateCollectionSheetPayloadAssembler(centerListHelper.getMeetingFallCenter(),
                centerListHelper.getDate()).assemblePayload();
        mCollectionSheetPresenter.loadCollectionsForGroup(centerId,payload);

    }

    @Override
    public void showCenterCollectionSheet(CollectionSheetData collectionSheetData) {
        Logger.d(TAG,"successful group list "+collectionSheetData);
        ++mCountSelectedCenters;
        downloadNextCenter();
    }

    private void intialiseTheExtendeProductionCollectiondata(final int i, final ArrayList<ProductiveCollectionData> productiveCollectionDatas) {
        if (extendedProductionCollectiondatas == null) {
            extendedProductionCollectiondatas = new ArrayList<ExtendedProductionCollectiondata>();
        }
        if (centerListHelpers == null) {
            centerListHelpers = new ArrayList<CenterListHelper>();
        }
        if (productiveCollectionDatas.size() > 0) {
            ProductionCollectionSheedDataAssembler productionCollectionSheedDataAssembler = new ProductionCollectionSheedDataAssembler(getActivity(), productiveCollectionDatas, meetingDates.get(i).getDate());
            extendedProductionCollectiondatas.addAll(productionCollectionSheedDataAssembler.assembleProductionCollectionSheetData());
            centerListHelpers.addAll(productionCollectionSheedDataAssembler.assembleCenterListHelperData(realm));
            meetingsFallCenterListRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    private void checkForMeetings() {
        ++i;
        Logger.d(TAG, "the value of i " + i + "size of array " + meetingDates.size());

        if (i < meetingDates.size()) {
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
