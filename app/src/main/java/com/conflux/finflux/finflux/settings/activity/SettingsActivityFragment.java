package com.conflux.finflux.finflux.settings.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.conflux.finflux.finflux.R;
import com.conflux.finflux.finflux.login.activity.LoginActivity;
import com.conflux.finflux.finflux.util.Logger;
import com.conflux.finflux.finflux.util.PrefManager;
import com.conflux.finflux.finflux.util.ValidationUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class SettingsActivityFragment extends Fragment {

    @Bind(R.id.card_view_server_detail)
    CardView cardViewServerDetail;
    @Bind(R.id.editText_url)EditText editTextURL;
    @Bind(R.id.editText_port_number) EditText editTextPort;
    @Bind(R.id.editText_tenant)EditText editTextTenant;
    @Bind(R.id.tv_constructed_instance_url)TextView textViewConstructedInstanceUrl;
    @Bind(R.id.editText_organization)EditText editTextOrganizationName;

    private String instanceURL;
    private String tenantIdentifier;
    private boolean isValidUrl;
    private final String TAG = getClass().getSimpleName();
    private View rootView;
    private TextWatcher urlWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            Integer port = editTextPort.getEditableText().toString().trim().isEmpty() ? null : Integer.valueOf(editTextPort.getEditableText().toString());
            instanceURL = ValidationUtil.getInstanceUrl(editTextURL.getText().toString().trim(), port);
            tenantIdentifier=editTextTenant.getEditableText().toString().trim();
            isValidUrl = ValidationUtil.isValidUrl(instanceURL);
            textViewConstructedInstanceUrl.setText(instanceURL);
            textViewConstructedInstanceUrl.setTextColor(isValidUrl ? getResources().getColor(android.R.color.holo_green_dark) : getResources().getColor(android.R.color.holo_red_dark));
        }
    };

    public SettingsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this,rootView);
        editTextURL.addTextChangedListener(urlWatcher);
        editTextPort.addTextChangedListener(urlWatcher);
        populateViews();
        return rootView;
    }

    //populate views if preferences are already exists
    private void populateViews(){
        editTextOrganizationName.setText(PrefManager.getOrganization());
        editTextPort.setText(PrefManager.getPort());
        editTextTenant.setText(PrefManager.getTenant());
        editTextURL.setText(PrefManager.getInstanceDomain());
    }

    @OnClick(R.id.button_submit)
    public void onClickSubmit(View view){
        if(validate()){
            PrefManager.setInstanceDomain(editTextURL.getText().toString().trim());
            PrefManager.setInstanceUrl(instanceURL);
            PrefManager.setPort(editTextPort.getText().toString().trim());
            PrefManager.setOrganizationName(editTextOrganizationName.getText().toString().trim());
            PrefManager.setTenant(editTextTenant.getText().toString().trim());
            getActivity().finish();
        }
    }

    private boolean validate(){
        boolean flag = true;
        if(editTextOrganizationName.getText().toString().trim().isEmpty()){
            editTextOrganizationName.setError(getString(R.string.error_organization_name));
            flag = false;
        }
        if(editTextURL.getText() == null || editTextURL.getText().toString().trim().isEmpty()){
            editTextURL.setError(getString(R.string.url_error_empty));
            flag = false;
        }
        if(editTextTenant.getText().toString().trim().isEmpty()){
            editTextTenant.setError(getString(R.string.tenant_error_empty));
            flag = false;
        }
        return flag;
    }
}
