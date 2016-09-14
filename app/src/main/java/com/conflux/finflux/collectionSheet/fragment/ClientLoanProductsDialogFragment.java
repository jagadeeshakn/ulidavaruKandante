package com.conflux.finflux.collectionSheet.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.conflux.finflux.R;
import com.conflux.finflux.collectionSheet.adapter.AttendanceTypesAdapter;
import com.conflux.finflux.collectionSheet.adapter.LoanRecyclerViewAdapter;
import com.conflux.finflux.collectionSheet.assembler.ClientLoanDetailsDataAssembler;
import com.conflux.finflux.collectionSheet.data.AttendanceType;
import com.conflux.finflux.collectionSheet.data.CodeValue;
import com.conflux.finflux.collectionSheet.data.CollectionSheetConstants;
import com.conflux.finflux.collectionSheet.data.CollectionSheetData;
import com.conflux.finflux.collectionSheet.data.CollectionSheetDataForAdapter;
import com.conflux.finflux.collectionSheet.event.ClientCollectionSheetDataChangeListner;
import com.conflux.finflux.util.Logger;
import com.conflux.finflux.util.event.EventBus;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

/**
 * Created by jagadeeshakn on 7/30/2016.
 */
public class ClientLoanProductsDialogFragment extends DialogFragment {

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
        CollectionSheetDataForAdapter data = new CollectionSheetDataForAdapter();
        args.putParcelable(CollectionSheetConstants.COLLECTION_SHEET_DATA_USED_FOR_ADAPTER, collectionSheetDataAdapter);
        args.putParcelable(CollectionSheetConstants.COLLECTION_SHEET_DATA_FROM_SERVER,collectionSheetData);
        productsDialogFragment.setArguments(args);
        return productsDialogFragment;
    }
    public ClientLoanProductsDialogFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            CollectionSheetDataForAdapter dataForAdapter = getArguments().getParcelable(CollectionSheetConstants.COLLECTION_SHEET_DATA_USED_FOR_ADAPTER);
            clientCollectionSheetData = ClientLoanDetailsDataAssembler.createNewObjectForClient(dataForAdapter);
            collectionSheetData = getArguments().getParcelable(CollectionSheetConstants.COLLECTION_SHEET_DATA_FROM_SERVER);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_client_loan_products_dialog, container, false);
        ButterKnife.bind(this,rootView);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lv_loan_repayments.setLayoutManager(layoutManager);
        lv_loan_repayments.setItemAnimator(new DefaultItemAnimator());
        EventBus.getInstance().register(this);
        setClientDetails();
        displayAttendanceOptions();
        setClientAttendance();
        displayLoans();
        return rootView;
    }

    private void displayLoans(){
        LoanRecyclerViewAdapter adapter = new LoanRecyclerViewAdapter(getActivity(),clientCollectionSheetData.getLoans(),"","");
        lv_loan_repayments.setAdapter(adapter);
        adapter.onFoucListener(new LoanRecyclerViewAdapter.OnItemFocusedListnerClass() {
            @Override
            public void onItemFocused(final int position, View view) {
                EditText editText = (EditText) view;
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.UK);
                        Double totalDue = Double.valueOf(0);
                        try {
                            totalDue = numberFormat.parse(editable.toString()).doubleValue();
                        } catch (NumberFormatException e) {
                            Logger.e(TAG, "number was unable to convert");
                        } catch (ParseException e) {
                            Logger.e(TAG, "number was unable to parse");
                        }
                        clientCollectionSheetData.getLoans().get(position).setTotalCollected(totalDue);
                    }
                });
            }
        });
    }

    private void setClientAttendance(){
        for(CodeValue attendance : collectionSheetData.getAttendanceTypeOptions()){
            if(attendance.getId() == clientCollectionSheetData.getAttendanceType().getId()){
                int position = collectionSheetData.getAttendanceTypeOptions().indexOf(attendance);
                sp_attendance.setSelection(position,true);
            }
        }
    }

    private void displayAttendanceOptions(){
        AttendanceTypesAdapter attendanceTypesAdapter = new AttendanceTypesAdapter(getActivity(),collectionSheetData.getAttendanceTypeOptions());
        sp_attendance.setAdapter(attendanceTypesAdapter);
        sp_attendance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CodeValue attendanceType = collectionSheetData.getAttendanceTypeOptions().get(i);
                clientCollectionSheetData.setAttendanceType(attendanceType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setClientDetails(){
        Logger.d(TAG,"the text view is "+tv_client_name);
        Logger.d(TAG,"the text view is "+tv_client_id);
        Logger.d(TAG,"the text view is "+clientCollectionSheetData.getEntityName());
        Logger.d(TAG,"the text view is "+clientCollectionSheetData.getEntityId());
        tv_client_name.setText(clientCollectionSheetData.getEntityName());
        tv_client_id.setText(String.valueOf(clientCollectionSheetData.getEntityId()));
    }

    @OnClick(R.id.btn_cancel)
    public void cancel(View view) {
        dismiss();
    }

    @OnClick(R.id.btn_save)
    public void save(View view) {
        EventBus.getInstance().post(new ClientCollectionSheetDataChangeListner(clientCollectionSheetData));
        dismiss();
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

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getInstance().unregister(this);
    }
}
