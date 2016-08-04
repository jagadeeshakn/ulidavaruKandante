package com.conflux.finflux.collectionSheet.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.conflux.finflux.R;
import com.conflux.finflux.collectionSheet.data.CollectionSheetConstants;
import com.conflux.finflux.collectionSheet.data.CollectionSheetData;
import com.conflux.finflux.collectionSheet.data.CollectionSheetDataForAdapter;
import com.conflux.finflux.util.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jagadeeshakn on 7/30/2016.
 */
public class ClientLoanProductsDialogFragment extends Fragment {

    private final String TAG = getClass().getSimpleName();
    private View rootView;

    @Bind(R.id.tv_client_name)
    TextView tv_client_name;
    @Bind(R.id.tv_client_id)
    TextView tv_client_id;
    @Bind(R.id.sp_attendance)
    Spinner sp_attendance;
    @Bind(R.id.btn_cancel)
    Button btn_cancel;
    @Bind(R.id.btn_save)
    Button btn_save;
    @Bind(R.id.lv_loan_repayments)
    RecyclerView lv_loan_repayments;

    CollectionSheetDataForAdapter clientCollectionSheetData;
    CollectionSheetData collectionSheetData;
    private HashMap<Long,Double> loans = new HashMap<Long,Double>();

    public static ClientLoanProductsDialogFragment newInstance(CollectionSheetDataForAdapter collectionSheetDataAdapter,CollectionSheetData collectionSheetData){
        ClientLoanProductsDialogFragment productsDialogFragment = new ClientLoanProductsDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable(CollectionSheetConstants.COLLECTION_SHEET_DATA_USED_FOR_ADAPTER, collectionSheetDataAdapter);
        args.putParcelable(CollectionSheetConstants.COLLECTION_SHEET_DATA_FROM_SERVER,collectionSheetData);

        return productsDialogFragment;
    }
    public ClientLoanProductsDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            clientCollectionSheetData = getArguments().getParcelable(CollectionSheetConstants.COLLECTION_SHEET_DATA_USED_FOR_ADAPTER);
            collectionSheetData = getArguments().getParcelable(CollectionSheetConstants.COLLECTION_SHEET_DATA_FROM_SERVER);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_client_loan_products_dialog, container, false);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lv_loan_repayments.setLayoutManager(layoutManager);
        lv_loan_repayments.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }

    @OnClick(R.id.btn_cancel)
    public void cancel(View view) {

    }

    @OnClick(R.id.btn_save)
    public void save(View view) {
    }

    private void updateTheChangeInCollectionSheedData(){
        Iterator iterator = loans.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            Iterator iteratorforclientLoan = clientCollectionSheetData.getEditLoans().entrySet().iterator();
            while (iteratorforclientLoan.hasNext()){
                Map.Entry entryForClientLoan = (Map.Entry) iteratorforclientLoan.next();
                if(entryForClientLoan.getKey().equals(entry.getKey())){
                    Logger.e(TAG, "map elements are equal");
                    clientCollectionSheetData.getEditLoans().put((Long)entryForClientLoan.getKey(),(Double)entry.getValue());
                }
            }
        }
    }
}
