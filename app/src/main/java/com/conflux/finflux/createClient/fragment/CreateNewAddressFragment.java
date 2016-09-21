package com.conflux.finflux.createClient.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.conflux.finflux.R;
import com.conflux.finflux.core.FinBaseActivity;
import com.conflux.finflux.core.FinBaseFragment;
import com.conflux.finflux.createClient.adapter.AddressTypeOptionsAdapter;
import com.conflux.finflux.createClient.adapter.CountryOptionsAdapter;
import com.conflux.finflux.createClient.adapter.DistrictOptionsAdapter;
import com.conflux.finflux.createClient.adapter.StateOptionsAdapter;
import com.conflux.finflux.createClient.adapter.TalukaOptionsAdapter;
import com.conflux.finflux.createClient.data.AadharDetail;
import com.conflux.finflux.createClient.data.OptionsType;
import com.conflux.finflux.createClient.data.ClientAddressTemplate;
import com.conflux.finflux.createClient.data.ClientConstants;
import com.conflux.finflux.createClient.data.ClientPayloadData;
import com.conflux.finflux.createClient.data.ClientResponse;
import com.conflux.finflux.createClient.data.CountryData;
import com.conflux.finflux.createClient.data.DistrictData;
import com.conflux.finflux.createClient.data.EntityAddress;
import com.conflux.finflux.createClient.data.StateData;
import com.conflux.finflux.createClient.data.TalukaData;
import com.conflux.finflux.createClient.presenter.CreateAddressPresenter;
import com.conflux.finflux.createClient.viewServices.ClientAddressMvpView;
import com.conflux.finflux.dashboard.activity.DashBoardActivity;
import com.conflux.finflux.util.Logger;
import com.conflux.finflux.util.Network;
import com.conflux.finflux.util.Toaster;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by jagadeeshakn on 8/8/2016.
 */
public class CreateNewAddressFragment extends FinBaseFragment implements ClientAddressMvpView {

    public final String TAG = getClass().getSimpleName();
    private View rootview;

    private ClientPayloadData clientPayloadData;
    private AadharDetail aadharDetail;
    private ClientAddressTemplate addressTemplate;
    private File imageFile;

    private Long talukaId;
    private Long districtId;
    private Long stateId;
    private Long countryId;
    private Long addressTypeId;

    private ArrayList<DistrictData> districtList = new ArrayList<DistrictData>();
    private ArrayList<OptionsType> addressList = new ArrayList<OptionsType>();
    private ArrayList<CountryData> countryList = new ArrayList<CountryData>();
    private ArrayList<StateData> stateList = new ArrayList<StateData>();
    private ArrayList<TalukaData> talukaList = new ArrayList<TalukaData>();
    private List<Long> addressTypes;

    private boolean primaryTalukaFlag = false;
    private boolean primaryDistrictFlag = false;
    private boolean primaryStateFlag = false;
    private boolean primaryCountryFlag = false;


    @Inject
    CreateAddressPresenter mclientAddressPresenter;

    @Bind(R.id.sp_addresstype)
    Spinner sp_addressType;
    @Bind(R.id.et_house)
    EditText et_house;
    @Bind(R.id.et_street)
    EditText et_street;
    @Bind(R.id.et_village)
    EditText et_village;
    @Bind(R.id.sp_country)
    Spinner sp_country;
    @Bind(R.id.sp_state)
    Spinner sp_state;
    @Bind(R.id.sp_district)
    Spinner sp_district;
    @Bind(R.id.sp_taluka)
    Spinner sp_taluka;
    @Bind(R.id.et_pincode)
    EditText et_pincode;

    public static CreateNewAddressFragment newInstance(ClientPayloadData clientPayloadData, AadharDetail aadharDetail, File imageFile) {
        CreateNewAddressFragment createAddressFragment = new CreateNewAddressFragment();
        Bundle args = new Bundle();
        args.putParcelable(ClientConstants.CLIENT_PAYLOAD, clientPayloadData);
        args.putParcelable(ClientConstants.AADHAR_DETAIL, aadharDetail);
        args.putSerializable(ClientConstants.CLIENT_IMAGE, imageFile);
        createAddressFragment.setArguments(args);
        return createAddressFragment;
    }

    public CreateNewAddressFragment() {
        //Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((FinBaseActivity) getActivity()).getActivityComponent().inject(this);
        if (getArguments() != null) {
            clientPayloadData = getArguments().getParcelable(ClientConstants.CLIENT_PAYLOAD);
            aadharDetail = getArguments().getParcelable(ClientConstants.AADHAR_DETAIL);
            imageFile = getArguments().getParcelable(ClientConstants.CLIENT_IMAGE);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_new_client_address, container, false);
        ButterKnife.bind(this, rootview);
        mclientAddressPresenter.attachView(this);

        getToolbar();
        setToolbarTitle(getString(R.string.create_address));
        getAddressTemplate();

        return rootview;
    }

    private void getAddressTemplate() {
        mclientAddressPresenter.loadAddressTemplate();
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
        if (response != null){
            Logger.d(TAG, "client creation is failure");
        }
    }

    @Override
    public void showClientAddressTemplate(ClientAddressTemplate clientAddressTemplate) {
        if (clientAddressTemplate != null) {
            addressTemplate = new ClientAddressTemplate();
            addressTemplate = clientAddressTemplate;
        }
        if (clientAddressTemplate.getAddressTypeOptions().size() > 0 && clientAddressTemplate.getAddressTypeOptions() != null) {
            if (addressList != null) {
                addressList.clear();
            }
            for (OptionsType addressType : clientAddressTemplate.getAddressTypeOptions()) {
                addressList.add(addressType);
            }
            inflateAddressSpinner(addressList, getString(R.string.select_addressType), sp_addressType);
            setAddressSpinnerSelection();
            setSpinnerListnerForAddress();
        }
        if (clientAddressTemplate.getCountryDatas().size() > 0 && clientAddressTemplate.getCountryDatas() != null) {
            if (countryList != null) {
                countryList.clear();
            }
            for (CountryData countryData : clientAddressTemplate.getCountryDatas()) {
                countryList.add(countryData);
            }
            inflateCountrySpinner(countryList, getString(R.string.select_country), sp_country);
            if (aadharDetail != null) {
                populateViews(aadharDetail);
            } else {
                setCountrySpinnerSelection();
                setSpinnerListnerForCountry();
            }
        }
    }

    private void clientStateTemplate(Long countryId) {
        if (countryId != null) {
            if (stateList != null) {
                stateList.clear();
            }
            if (addressTemplate.getCountryDatas().size() > 0 && addressTemplate.getCountryDatas() != null) {
                for (CountryData countryData : addressTemplate.getCountryDatas()) {
                    if (countryData.getStatesDatas().size() > 0 && countryData.getStatesDatas() != null) {
                        for (StateData stateData : countryData.getStatesDatas()) {
                            if (stateData.getCountryId() == countryId) {
                                stateList.add(stateData);
                            }
                        }
                    }
                }
            }
        }
        inflateStateSpinner(stateList, getString(R.string.select_state), sp_state);
        if (aadharDetail != null) {
            populateStateViews(aadharDetail);
        } else {
            setStateSpinnerSelection();
            setSpinnerListnerForState();
        }
    }

    private void clientDistrictTemplate(Long stateId) {
        if (stateId != null) {
            if (districtList != null) {
                districtList.clear();
            }
            if (addressTemplate.getCountryDatas().size() > 0 && addressTemplate.getCountryDatas() != null) {
                for (CountryData countryData : addressTemplate.getCountryDatas()) {
                    if (countryData.getStatesDatas().size() > 0 && countryData.getStatesDatas() != null) {
                        for (StateData stateData : countryData.getStatesDatas()) {
                            if (stateData.getDistrictDatas().size() > 0 && stateData.getDistrictDatas() != null) {
                                for (DistrictData districtData : stateData.getDistrictDatas()) {
                                    if (districtData.getStateId() == stateId) {
                                        districtList.add(districtData);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            inflateDistrictSpinner(districtList, getString(R.string.select_district), sp_district);
            if (aadharDetail != null) {
                populateDistViews(aadharDetail);
            } else {
                setDistrictSpinnerSelection();
                setSpinnerListnerForDistrict();
            }
        }
    }

    private void clientTalukaTemplate(Long districtId) {
        if (districtId != null) {
            if (talukaList != null) {
                talukaList.clear();
            }
            if (addressTemplate.getCountryDatas().size() > 0 && addressTemplate.getCountryDatas() != null) {
                for (CountryData countryData : addressTemplate.getCountryDatas()) {
                    if (countryData.getStatesDatas().size() > 0 && countryData.getStatesDatas() != null) {
                        for (StateData stateData : countryData.getStatesDatas()) {
                            if (stateData.getDistrictDatas().size() > 0 && stateData.getDistrictDatas() != null) {
                                for (DistrictData districtData : stateData.getDistrictDatas()) {
                                    if (districtData.getTalukaDatas().size() > 0 && districtData.getTalukaDatas() != null) {
                                        for (TalukaData talukaData : districtData.getTalukaDatas()) {
                                            if (talukaData.getDistrictId() == districtId) {
                                                talukaList.add(talukaData);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            inflateTalukaSpinner(talukaList, getString(R.string.select_taluka), sp_taluka);
            if (aadharDetail != null) {
                populateTalukaViews(aadharDetail);
            } else {
                setTalukaSpinnerSelection();
                setSpinnerListnerForTaluka();
            }
        }
    }


    private void inflateAddressSpinner(ArrayList<OptionsType> addressList, String optionName, Spinner sp_addressType) {

        OptionsType defaultSelectOption = new OptionsType();
        OptionsType removeDefault = new OptionsType();
        defaultSelectOption.setId((long) -1);
        defaultSelectOption.setName(optionName);
        for (OptionsType option : addressList) {
            if (option.getId() == -1) {
                removeDefault = option;
                break;
            } else {
                removeDefault = null;
            }
        }
        if (removeDefault != null) {
            addressList.remove(removeDefault);
        }

        addressList.add(0, defaultSelectOption);
        AddressTypeOptionsAdapter addressAdapter = new AddressTypeOptionsAdapter(getActivity(), addressList);
        sp_addressType.setAdapter(addressAdapter);

    }

    private void setSpinnerListnerForAddress() {

        sp_addressType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Logger.i(TAG, "address spinner selection item has been selected");
                if (addressList.get(i).getId() != -1 && addressList.get(i).getId() != addressTypeId) {
                    addressTypeId = addressList.get(i).getId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setAddressSpinnerSelection() {
        if (addressTypeId != new Long(-1)) {
            for (OptionsType addressOption : addressList) {
                if (addressOption.getId() == addressTypeId) {
                    int position = addressList.indexOf(addressOption);
                    sp_addressType.setSelection(position, true);
                    break;
                }
            }
        }
    }

    private void inflateCountrySpinner(ArrayList<CountryData> countryList, String optionName, Spinner sp_country) {
        CountryData defaultSelectOption = new CountryData();
        CountryData removeDefault = new CountryData();
        defaultSelectOption.setCountryId((long) -1);
        defaultSelectOption.setCountryName(optionName);
        for (CountryData option : countryList) {
            if (option.getCountryId() == -1) {
                removeDefault = option;
                break;
            } else {
                removeDefault = null;
            }
        }
        if (removeDefault != null) {
            countryList.remove(removeDefault);
        }

        countryList.add(0, defaultSelectOption);
        CountryOptionsAdapter spinnerAdapter = new CountryOptionsAdapter(getActivity(), countryList);
        sp_country.setAdapter(spinnerAdapter);

    }


    private void setSpinnerListnerForCountry() {

        sp_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Logger.i(TAG, "country spinner selection item has been selected");
                if (countryList.get(i).getCountryId() != -1 && countryList.get(i).getCountryId() != countryId) {
                    countryId = countryList.get(i).getCountryId();

                    if (Network.isOnline(getActivity())) {
                        clientStateTemplate(countryId);
                    } else {
                        // setOfflineStateTemplate(countryId);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setCountrySpinnerSelection() {

        if (countryId != new Long(-1)) {
            for (CountryData countryOption : countryList) {
                if (countryOption.getCountryName().compareToIgnoreCase("india") == 0) {
                    int position = countryList.indexOf(countryOption);
                    countryId = countryOption.getCountryId();
                    sp_country.setSelection(position, true);
                    if (Network.isOnline(getActivity())) {
                        clientStateTemplate(countryId);
                    } else {
                        //setOfflineStateTemplate(countryId);
                    }
                    break;
                }
            }
        }
    }

    public void inflateStateSpinner(ArrayList<StateData> stateList, String optionName, Spinner sp_state) {

        StateData defaultSelectOption = new StateData();
        StateData removeDefault = new StateData();
        defaultSelectOption.setStateId((long) -1);
        defaultSelectOption.setStateName(optionName);
        for (StateData option : stateList) {
            if (option.getStateId() == -1) {
                removeDefault = option;
                break;
            } else {
                removeDefault = null;
            }
        }
        if (removeDefault != null) {
            stateList.remove(removeDefault);
        }

        stateList.add(0, defaultSelectOption);
        StateOptionsAdapter stateAdapter = new StateOptionsAdapter(getActivity(), stateList);
        sp_state.setAdapter(stateAdapter);
    }

    public void setStateSpinnerSelection() {
        if (stateId != new Long(-1)) {
            for (StateData stateOption : stateList) {
                if (stateOption.getStateId() == stateId) {
                    int position = stateList.indexOf(stateOption);
                    sp_state.setSelection(position, true);
                    break;
                }
            }
        }
    }

    public void setSpinnerListnerForState() {
        sp_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Logger.i(TAG, "state spinner selection item has been selected");
                if (stateList.get(i).getStateId() != -1 && stateList.get(i).getStateId() != stateId) {
                    stateId = stateList.get(i).getStateId();

                    if (Network.isOnline(getActivity())) {
                        clientDistrictTemplate(stateId);
                    } else {
                        //setOfflineDistrictTemplate(stateId);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void inflateDistrictSpinner(ArrayList<DistrictData> districtList, String optionName, Spinner sp_district) {

        DistrictData defaultSelectOption = new DistrictData();
        DistrictData removeDefault = new DistrictData();
        defaultSelectOption.setDistrictId((long) -1);
        defaultSelectOption.setDistrictName(optionName);
        for (DistrictData option : districtList) {
            if (option.getDistrictId() == -1) {
                removeDefault = option;
                break;
            } else {
                removeDefault = null;
            }
        }
        if (removeDefault != null) {
            districtList.remove(removeDefault);
        }

        districtList.add(0, defaultSelectOption);
        DistrictOptionsAdapter districtAdapter = new DistrictOptionsAdapter(getActivity(), districtList);
        sp_district.setAdapter(districtAdapter);
    }

    public void setDistrictSpinnerSelection() {
        if (districtId != new Long(-1)) {
            for (DistrictData districtOption : districtList) {
                if (districtOption.getDistrictId() == districtId) {
                    int position = districtList.indexOf(districtOption);
                    sp_district.setSelection(position, true);
                    break;
                }
            }
        }
    }

    public void setSpinnerListnerForDistrict() {
        sp_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Logger.i(TAG, "district spinner selection item has been selected");
                if (districtList.get(i).getDistrictId() != -1 && districtList.get(i).getDistrictId() != districtId) {
                    districtId = districtList.get(i).getDistrictId();

                    if (Network.isOnline(getActivity())) {
                        clientTalukaTemplate(districtId);
                    } else {
                        // setOfflineTalukaTemplate(districtId);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void inflateTalukaSpinner(ArrayList<TalukaData> talukaList, String optionName, Spinner sp_taluka) {

        TalukaData defaultSelectOption = new TalukaData();
        TalukaData removeDefault = new TalukaData();
        defaultSelectOption.setTalukaId((long) -1);
        defaultSelectOption.setTalukaName(optionName);
        for (TalukaData option : talukaList) {
            if (option.getTalukaId() == -1) {
                removeDefault = option;
                break;
            } else {
                removeDefault = null;
            }
        }
        if (removeDefault != null) {
            talukaList.remove(removeDefault);
        }

        talukaList.add(0, defaultSelectOption);
        TalukaOptionsAdapter talukaAdapter = new TalukaOptionsAdapter(getActivity(), talukaList);
        sp_taluka.setAdapter(talukaAdapter);
    }

    public void setTalukaSpinnerSelection() {
        if (talukaId != new Long(-1)) {
            for (TalukaData talukaOption : talukaList) {
                if (talukaOption.getTalukaId() == talukaId) {
                    int position = talukaList.indexOf(talukaOption);
                    sp_taluka.setSelection(position, true);
                    break;
                }
            }
        }
    }

    public void setSpinnerListnerForTaluka() {
        sp_taluka.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Logger.i(TAG, "taluka spinner selection item has been selected");
                if (talukaList.get(i).getTalukaId() != -1 && talukaList.get(i).getTalukaId() != talukaId) {
                    talukaId = talukaList.get(i).getTalukaId();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void populateViews(AadharDetail aadharDetail) {

        et_house.setText(aadharDetail.getHouse());
        et_street.setText(aadharDetail.getStreet());
        et_village.setText(aadharDetail.getVtc());
        et_pincode.setText(aadharDetail.getPc());

        if (countryList != null) {
            String countryName = "india";
            for (CountryData countryData : countryList) {
                if ((countryData.getCountryName()).compareToIgnoreCase(countryName) == 0) {
                    Logger.d(TAG, "after scan country is   india");
                    int countryPosition = countryList.indexOf(countryData);
                    Logger.d(TAG, "spinner position   " + countryList.indexOf(countryData));
                    countryId = countryData.getCountryId();
                    sp_country.setSelection(countryPosition, true);
                    if (Network.isOnline(getActivity())) {
                        clientStateTemplate(countryId);
                    } else {
                        //setOfflineStateTemplate(countryId);
                    }

                    primaryCountryFlag = true;

                }
            }
            if (!primaryCountryFlag) {
                inflateCountrySpinner(countryList, getString(R.string.select_country), sp_country);
                setCountrySpinnerSelection();
                setSpinnerListnerForCountry();
            }
        }
    }

    private void populateStateViews(AadharDetail aadharDetail) {
        String stateName = aadharDetail.getState();
        int statePosition;
        if (stateList != null) {
            for (StateData stateOption : stateList) {
                String templateState = stateOption.getStateName();
                if (templateState.compareToIgnoreCase(stateName) == 0) {
                    statePosition = stateList.indexOf(stateOption);
                    stateId = stateOption.getStateId();
                    sp_state.setSelection(statePosition, true);
                    if (Network.isOnline(getActivity())) {
                        clientDistrictTemplate(stateId);
                    } else {
                        //setOfflineDistrictTemplate(stateId);
                    }

                    primaryStateFlag = true;
                }
            }
            if (!primaryStateFlag) {
                inflateStateSpinner(stateList, getString(R.string.select_state), sp_state);
                setStateSpinnerSelection();
                setSpinnerListnerForState();
            }
        }

    }

    private void populateDistViews(AadharDetail aadharDetail) {
        String districtName = aadharDetail.getDist();
        if (districtList != null) {
            for (DistrictData districtOption : districtList) {
                String templateDistrict = districtOption.getDistrictName();
                if (templateDistrict.compareToIgnoreCase(districtName) == 0) {
                    int distposition = districtList.indexOf(districtOption);
                    sp_district.setSelection(distposition, true);
                    if (Network.isOnline(getActivity())) {
                        clientTalukaTemplate(districtId);
                    } else {
                        //setOfflineTalukaTemplate(districtId);
                    }

                    primaryDistrictFlag = true;
                }
            }
            if (!primaryDistrictFlag) {
                inflateDistrictSpinner(districtList, getString(R.string.select_district), sp_district);
                setDistrictSpinnerSelection();
                setSpinnerListnerForDistrict();
            }
        }
    }

    private void populateTalukaViews(AadharDetail aadharDetail) {
        String talukaName = aadharDetail.getSubdist();
        if (talukaList != null) {
            for (TalukaData talukaOption : talukaList) {
                String templateTaluka = talukaOption.getTalukaName();
                if (templateTaluka.compareToIgnoreCase(talukaName) == 0) {
                    int talukaPosition = talukaList.indexOf(talukaOption);
                    sp_taluka.setSelection(talukaPosition, true);
                    primaryTalukaFlag = true;
                }
            }
            if (!primaryTalukaFlag) {
                inflateTalukaSpinner(talukaList, getString(R.string.select_taluka), sp_taluka);
                setTalukaSpinnerSelection();
                setSpinnerListnerForTaluka();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mclientAddressPresenter.detachView();
    }

    public EntityAddress loadAddressPayload() {
        EntityAddress entityAddress = new EntityAddress();
        addressTypes = new ArrayList<>();
        try {
            entityAddress.setLocale("en");
            entityAddress.setDateFormat("dd MMMM yyyy");
            if (sp_addressType.getSelectedItemId() != -1) {
                addressTypes.add(sp_addressType.getSelectedItemId());
                entityAddress.setAddressTypes(addressTypes);
            }
            if (!et_house.getText().toString().isEmpty()) {
                entityAddress.setHouseNo(et_house.getText().toString());
            }
            if (!et_street.getText().toString().isEmpty()) {
                entityAddress.setAddressLineOne(et_street.getText().toString());
            }
            if (!et_village.getText().toString().isEmpty()) {
                entityAddress.setVillageTown(et_village.getText().toString());
            }
            if (sp_country.getSelectedItemId() != -1 && !countryList.isEmpty()) {
                entityAddress.setCountryId(sp_country.getSelectedItemId());
            }
            if (sp_state.getSelectedItemId() != -1 && !stateList.isEmpty()) {
                entityAddress.setStateId(sp_state.getSelectedItemId());
            }
            if (sp_district.getSelectedItemId() != -1 && !districtList.isEmpty()) {
                entityAddress.setDistrictId(sp_district.getSelectedItemId());
            }
            if (sp_taluka.getSelectedItemId() != -1 && !talukaList.isEmpty()) {
                entityAddress.setTalukId(sp_taluka.getSelectedItemId());
            }
            if (!et_pincode.getText().toString().isEmpty()) {
                entityAddress.setPostalCode(et_pincode.getText().toString());
            }
        } catch (Exception e) {
        }

        return entityAddress;
    }

    @OnClick(R.id.btn_save)
    public void saveEntityAddress(View view) {
        List<EntityAddress> entityAddresses = new ArrayList<>();
        entityAddresses.add(loadAddressPayload());
        clientPayloadData.setAddresses(entityAddresses);
        if (Network.isOnline(getActivity())) {
            mclientAddressPresenter.createClient(clientPayloadData);
        }
    }

    @Override
    public void showClientCreatedSuccessfully(ClientResponse client, String s) {
        Toaster.show(rootview,s);

    }


    @OnClick(R.id.btn_cancel)
    public void cancelClientCreation(View view) {
        //click on cancel button, client creation is canceled and navigate to the homepage
        startActivity(new Intent(getActivity(), DashBoardActivity.class));

    }

}
