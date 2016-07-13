package com.conflux.finflux.collectionSheet.fragment;

import android.os.Bundle;
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
import com.conflux.finflux.collectionSheet.data.CollectionSheetConstants;
import com.conflux.finflux.collectionSheet.data.Payload;
import com.conflux.finflux.collectionSheet.data.ProductiveCollectionData;
import com.conflux.finflux.collectionSheet.presenter.CollectionSheetPresenter;
import com.conflux.finflux.collectionSheet.viewServices.CollectionSheetMvpView;
import com.conflux.finflux.core.FinBaseActivity;
import com.conflux.finflux.core.FinBaseFragment;
import com.conflux.finflux.db.LoginUser;
import com.conflux.finflux.login.data.User;
import com.conflux.finflux.util.DateHelper;
import com.conflux.finflux.util.Logger;
import com.conflux.finflux.util.MFDatePicker;
import com.conflux.finflux.util.PrefManager;

import java.util.ArrayList;
import java.util.Calendar;
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
        mCollectionSheetPresenter.attachView(this);
        Logger.d(TAG,"collection sheet listener  in not null  "+mCollectionSheetPresenter);
       // getToolbar();
        setCenterMeetingDate();
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

    public void basicCenterData(){
        Realm realm = Realm.getDefaultInstance();
       LoginUser user = realm.where(LoginUser.class).findFirst();
        Logger.d(TAG,"user present in data base   "+user.toString());
        Logger.d(TAG,"User id "+user.getUsername());
        Logger.d(TAG,"Staff is "+user.getStaffid());
        officeId=user.getOfficeId();
        staffId = user.getStaffid();
        setProductiveCollectionPayload(staffId);
    }

    public void setProductiveCollectionPayload(Long staffId){
        Payload payload=new Payload();
        if (staffId!=0) {
            try {
                payload.setDateFormat(CollectionSheetConstants.dateFormat);
                payload.setLocale(CollectionSheetConstants.EN);
                payload.setMeetingDate("09 July 2015");
                payload.setOfficeId(officeId);
                payload.setStaffId(staffId);
                mCollectionSheetPresenter.loadProductiveCollectionSheet(payload);
            }catch (Exception e){
                e.printStackTrace();
                Logger.d(TAG,"staff is not assigned to user");
            }
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
    public void showProductiveCollectionSheet(ArrayList<ProductiveCollectionData> productiveCollectionData) {
        Logger.d(TAG,"productive collectio sheet generated successfully     "+productiveCollectionData);
    }

    @Override
    public void showFetchingError(HttpException response) {
        if (response!=null){
            Logger.d(TAG,"failure to get productive collection sheet    "+response.getMessage());
        }
    }

    @Override
    public void onDatePicked(String date) {
        dateString = date.replace("-", " ");
        String dayString[] = date.split("-");
        String selectedDay = dayString[0].toString();
        selectedDay = DateHelper.removeLeadingZeroes(selectedDay);
        String dayNumberSuffix = DateHelper.getDayNumberSuffix(Integer.parseInt(selectedDay));
        Spanned spanned = Html.fromHtml(selectedDay + dayNumberSuffix + getString(R.string.center_meeting));
        tv_today_date.setText(spanned);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mCollectionSheetPresenter.detachView();
    }
}
