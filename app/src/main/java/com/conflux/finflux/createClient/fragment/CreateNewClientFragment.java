package com.conflux.finflux.createClient.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import com.conflux.finflux.R;
import com.conflux.finflux.collectionSheet.data.CodeValue;
import com.conflux.finflux.core.FinBaseActivity;
import com.conflux.finflux.core.FinBaseFragment;
import com.conflux.finflux.createClient.adapter.GenderOptionsAdapter;
import com.conflux.finflux.createClient.data.AadharDetail;
import com.conflux.finflux.createClient.data.AadharQrcode;
import com.conflux.finflux.createClient.data.ActivityResultBus;
import com.conflux.finflux.createClient.data.ActivityResultEvent;
import com.conflux.finflux.createClient.data.ClientConstants;
import com.conflux.finflux.createClient.data.ClientPayloadData;
import com.conflux.finflux.createClient.data.ClientTemplate;
import com.conflux.finflux.createClient.data.OptionsType;
import com.conflux.finflux.createClient.presenter.CreateClientPresenter;
import com.conflux.finflux.createClient.services.ClientData;
import com.conflux.finflux.createClient.viewServices.CreateClientMvpView;
import com.conflux.finflux.dashboard.activity.DashBoardActivity;
import com.conflux.finflux.db.LoginUser;
import com.conflux.finflux.util.Logger;
import com.conflux.finflux.util.MFDatePicker;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by jagadeeshakn on 8/8/2016.
 */
public class CreateNewClientFragment extends FinBaseFragment implements MFDatePicker.OnDatePickListener, CreateClientMvpView {

    public final String TAG = getClass().getSimpleName();

    @Inject
    CreateClientPresenter mCreateClientPresenter;

    private ArrayList<OptionsType> genderList = new ArrayList<OptionsType>();

    private View rootView;
    private Long genderId;
    private Long officeId;
    private Long staffId;
    private int datePickerInput;
    private String dateOfBirth;
    private String birthOfDate;
    private boolean mandatoryFieldFlag = false;
    private android.support.v4.app.DialogFragment mfDatePicker;

    Realm realm;
    AadharDetail aadharDetail;
    ClientPayloadData clientPayloadData;
    ClientTemplate clientTemplateData;
    private File imageFile;

    @Bind(R.id.et_client_first_name)
    EditText et_client_first_name;
    @Bind(R.id.et_client_middle_name)
    EditText et_client_middle_name;
    @Bind(R.id.et_client_last_name)
    EditText et_client_last_name;
    @Bind(R.id.et_client_dob)
    EditText et_client_dob;
    @Bind(R.id.edit_dob)
    ImageButton edit_dob;
    @Bind(R.id.et_client_mobile_number)
    EditText et_client_mobile_number;
    @Bind(R.id.sp_gender)
    Spinner sp_gender;


    public static CreateNewClientFragment newInstance() {
        CreateNewClientFragment createNewClientFragment = new CreateNewClientFragment();
        Bundle args = new Bundle();
        createNewClientFragment.setArguments(args);
        return createNewClientFragment;
    }

    public CreateNewClientFragment() {
        //Required public empty constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((FinBaseActivity) getActivity()).getActivityComponent().inject(this);
        if (getArguments() != null) {

        }
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_create_new_client, container, false);

        ButterKnife.bind(this, rootView);
        mCreateClientPresenter.attachView(this);

        realm = Realm.getDefaultInstance();
        if(realm.isClosed()){
            Logger.d(TAG,"realm closed");
            realm = Realm.getDefaultInstance();
        }
        getToolbar();
        setToolbarTitle(getString(R.string.create_client));
        getClientTemplate();
        inflateBirthDate();
        basicOfficeData();

        if (savedInstanceState != null){
            clientTemplateData = savedInstanceState.getParcelable(ClientConstants.GENDER_LIST);
            if (clientTemplateData != null){
                for (OptionsType genderOptions : clientTemplateData.getGenderOptions()) {
                    genderList.add(genderOptions);
                }
                inflateGenderSpinner(genderList, getString(R.string.select_gender), sp_gender);
                setSpinnerListenerForGender();
                setGenderSpinnerSelection();
            }
        }
        return rootView;
    }

    private void getClientTemplate() {
        mCreateClientPresenter.loadClientTemplate();
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
    public void showFetchingError(HttpException response) {
        if (response != null) {
            Logger.d(TAG, "on failure to get client template");
        }
    }

    @Override
    public void showClientTemplate(ClientTemplate clientTemplate) {
        genderList.clear();
        try {
            if (clientTemplate != null) {
                clientTemplateData = clientTemplate;
                for (OptionsType genderOptions : clientTemplate.getGenderOptions()) {
                    genderList.add(genderOptions);
                }
                inflateGenderSpinner(genderList, getString(R.string.select_gender), sp_gender);
                setSpinnerListenerForGender();
                setGenderSpinnerSelection();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inflateGenderSpinner(ArrayList<OptionsType> spinnerList, String optionName, Spinner spinner) {
        Logger.d(TAG,"gender list is........."+spinnerList);
        OptionsType defaultSelectOption = new OptionsType();
        OptionsType removeDefault = new OptionsType();
        defaultSelectOption.setId((long) -1);
        defaultSelectOption.setName(optionName);
        for (OptionsType option : spinnerList) {
            if (option.getId() == -1) {
                removeDefault = option;
                break;
            } else {
                removeDefault = null;
            }
        }
        if (removeDefault != null) {
            spinnerList.remove(removeDefault);
        }

        spinnerList.add(0, defaultSelectOption);
        GenderOptionsAdapter spinnerAdapter = new GenderOptionsAdapter(getActivity(), spinnerList);
        spinner.setAdapter(spinnerAdapter);

    }

    private void setSpinnerListenerForGender() {
        sp_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Logger.i(TAG, "gender spinner selection item has been selected");
                if (genderList.get(i).getId() != -1 && genderList.get(i).getId() != genderId) {
                    genderId = genderList.get(i).getId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void setGenderSpinnerSelection() {
        if (genderId != -1) {
            for (OptionsType addressOption : genderList) {
                if (addressOption.getId() == genderId) {
                    int position = genderList.indexOf(addressOption);
                    sp_gender.setSelection(position, true);
                    break;
                }
            }
        }
    }

    @Override
    public void onDatePicked(String date) {
        switch (datePickerInput) {
            case R.id.edit_dob:
                dateOfBirth = date.replace("-", " ");
                et_client_dob.setText(date);
                break;
            case R.id.et_client_dob:
                Logger.d(TAG,"an edit text dob is called");
                dateOfBirth = date.replace("-", " ");
                et_client_dob.setText(date);
                break;
            default:
                break;
        }
    }

    public void inflateBirthDate() {
        mfDatePicker = MFDatePicker.newInsance(this);
        //method to display calander view and listener on select new birth date
        edit_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerInput = view.getId();
                mfDatePicker.show(getActivity().getSupportFragmentManager(), ClientConstants.DFRAG_DATE_PICKER);
            }
        });
        et_client_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.d(TAG, "an edit text dob is called");
                datePickerInput = v.getId();
                mfDatePicker.show(getActivity().getSupportFragmentManager(), ClientConstants.DFRAG_DATE_PICKER);
            }
        });
    }

    @OnClick(R.id.bt_autofill)
    public void loadAadhar(View view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.Aadhar_service));
        builder.setItems(R.array.scan_choice, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    // The 'which' argument contains the index position
                    // of the selected item
                    case 0:
                        Intent intent = new Intent(getActivity(), AadharQrcode.class);
                        startActivityForResult(intent, 111);
                        break;
                    case 1:
                        aadharOtpDialog();
                        break;
                    case 2:
                        aadharFingerPrintDialog();
                        break;
                    default:
                        break;

                }

            }
        })
                .setNegativeButton(R.string.bt_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = builder.create();
        ListView listView = alertDialog.getListView();
        listView.setDivider(new ColorDrawable(Color.BLUE));
        listView.setDividerHeight(2);
        alertDialog.show();

    }

    private void aadharOtpDialog() {
    }

    private void aadharFingerPrintDialog() {

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ActivityResultBus.getInstance().postQueue(
                new ActivityResultEvent(requestCode, resultCode, data));
        switch (requestCode) {
            case 111:
                //handle activity result from aadhar qr code scan;
                if (resultCode == Activity.RESULT_OK) {
                    Bundle resultData = data.getExtras();
                    Bundle bundleOfobject = resultData.getBundle("details");
                    aadharDetail = (AadharDetail) bundleOfobject.getParcelable("data");
                    setViews(aadharDetail);
                }
                break;
        }

    }

    public void setViews(AadharDetail data) {
        String gender, fullName;
        fullName = data.getName();
        String[] clientName = fullName.split(" ", 2);
        et_client_first_name.setText(clientName[0]);
        if (clientName.length > 1) {
            et_client_last_name.setText(clientName[1]);
        }
        String genders = data.getGender();
        if (genders.equals("M")) {
            gender = "Male";
        } else if (genders.equals("F")) {
            gender = "Female";
        } else {
            gender = "Others";
        }

        for (OptionsType genderOption : genderList) {
            if (gender.contains(genderOption.getName())) {

                int positon = genderList.indexOf(genderOption);
                genderId = genderList.get(positon).getId();
                Logger.d(TAG, "gender position" + positon);
                sp_gender.setSelection(positon, true);
            }
        }
        et_client_dob.setText(data.getDob());
    }

    public void checkForMandatoryFields(final EditText editText, final String errormsg) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && TextUtils.isEmpty(editText.getText().toString())) {
                    editText.setError(errormsg);
                } else {
                    editText.setError(null);
                    mandatoryFieldFlag = true;
                }
            }
        });
    }

    public void basicOfficeData() {
        LoginUser user = realm.where(LoginUser.class).findFirst();
        officeId = user.getOfficeId();
        staffId = user.getStaffid();
    }
    public Boolean setClientPayload() {
        try {
            checkForMandatoryFields(et_client_first_name,getString(R.string.first_name_required));
            checkForMandatoryFields(et_client_last_name,getString(R.string.last_name_required));
            clientPayloadData = new ClientPayloadData();
            clientPayloadData.setLocale("en");
            clientPayloadData.setDateFormat("dd MMMMM yyyy");
            clientPayloadData.setOfficeId(officeId);
            clientPayloadData.setFirstname(et_client_first_name.getText().toString());
            if (!et_client_middle_name.getText().toString().isEmpty()) {
                clientPayloadData.setMiddlename(et_client_middle_name.getText().toString());
            }
            clientPayloadData.setLastname(et_client_last_name.getText().toString());
            if (!et_client_dob.getText().toString().isEmpty()) {
                String birthDate = et_client_dob.getText().toString();
                if (birthDate.contains("/")) {
                    //date fromat should be in form of dd MM yyyy
                    birthOfDate = birthDate.replace("/", " ");
                } else {
                    birthOfDate = birthDate.replace("-", " ");
                }

                clientPayloadData.setDateOfBirth(birthOfDate);
            }
            if(sp_gender.getSelectedItemId() != -1 && !genderList.isEmpty()){
                clientPayloadData.setGenderId(sp_gender.getSelectedItemId());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public void bitmapToFileConversion(Bitmap clientImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        clientImage.compress(Bitmap.CompressFormat.PNG, 40, bytes);
        imageFile = new File(getActivity().getExternalCacheDir(), "client_image.png");
        // you can create a new file name "test.jpg" in sdcard folder.
        try {
            imageFile.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // write the bytes in file
        FileOutputStream fo = null;
        try {
            fo = new FileOutputStream(imageFile);
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            fo.write(bytes.toByteArray());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // remember close de FileOutput
        try {
            fo.close();
            fo.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(ClientConstants.GENDER_LIST,clientTemplateData);
        super.onSaveInstanceState(outState);
    }

    @OnClick(R.id.btn_next)
    public void loadAddressFragment(View view) {
       loadForAddressFragment();
    }

    public void loadForAddressFragment(){
        setClientPayload();
        if (!mandatoryFieldFlag) {
            replaceFragment(CreateNewAddressFragment.newInstance(clientPayloadData, aadharDetail, imageFile), false, R.id.container);
        }
    }


    @OnClick(R.id.btn_cancel)
    public void loadDashBoard(View view) {
        startActivity(new Intent(getActivity(), DashBoardActivity.class));
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mCreateClientPresenter.detachView();
    }
}

