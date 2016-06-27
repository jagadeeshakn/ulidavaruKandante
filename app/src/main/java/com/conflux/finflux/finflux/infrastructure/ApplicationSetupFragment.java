package com.conflux.finflux.finflux.infrastructure;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.conflux.finflux.finflux.R;
import com.conflux.finflux.finflux.db.Activation;
import com.conflux.finflux.finflux.login.LoginConstants;
import com.conflux.finflux.finflux.util.Logger;
import com.conflux.finflux.finflux.util.PrefManager;
import com.conflux.finflux.finflux.util.ValidationUtil;
import com.facebook.stetho.common.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class ApplicationSetupFragment extends Fragment {
    @Bind(R.id.card_view_activate)
    CardView cardViewActivate;
    @Bind(R.id.card_view_server_detail)
    CardView cardViewServerDetail;
    @Bind(R.id.editText_activation_key)EditText editTextActivationKey;
    @Bind(R.id.editText_url)EditText editTextURL;
    @Bind(R.id.editText_port_number) EditText editTextPort;
    @Bind(R.id.editText_tenant)EditText editTextTenant;
    @Bind(R.id.tv_constructed_instance_url)TextView textViewConstructedInstanceUrl;

    private String instanceURL;
    private String tenantIdentifier;
    private boolean isValidUrl;
    private List<String> errorMessage;

    private final String TAG = getClass().getSimpleName();
    private View rootView = null;

    public ApplicationSetupFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_application_setup, container, false);
        ButterKnife.bind(this,rootView);
        if(!Activation.hasActivated(TAG)){
            cardViewActivate.setVisibility(View.VISIBLE);
        }else {
            long count = PrefManager.getLong(LoginConstants.LOGIN_COUNT,0);
            if(count == 0){
                cardViewServerDetail.setVisibility(View.VISIBLE);
                editTextURL.addTextChangedListener(urlWatcher);
                editTextPort.addTextChangedListener(urlWatcher);
            }
        }
        return rootView;
    }

    private TextWatcher urlWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            Integer port = editTextPort.getEditableText().toString().isEmpty() ? null : Integer.valueOf(editTextPort.getEditableText().toString());
            instanceURL = ValidationUtil.getInstanceUrl(editTextURL.getText().toString(), port);
            tenantIdentifier=editTextTenant.getEditableText().toString();
            isValidUrl = ValidationUtil.isValidUrl(instanceURL);
            textViewConstructedInstanceUrl.setText(instanceURL);
            textViewConstructedInstanceUrl.setTextColor(isValidUrl ? getResources().getColor(android.R.color.holo_green_dark) : getResources().getColor(android.R.color.holo_red_dark));
        }
    };

    @OnClick(R.id.button_submit)
    public void onClickSubmit(View view){
        if(validate()){
            Logger.i(TAG,"Successful validation");
        }
    }

    private boolean validate(){
        if(editTextURL.getText() == null || editTextURL.getText().toString().isEmpty()){
            editTextURL.setError(getString(R.string.url_error_empty));
        }
        if(editTextTenant.getText().toString().isEmpty()){
            editTextTenant.setError(getString(R.string.tenant_error_empty));
        }
        return true;
    }


    @OnClick(R.id.button_activate)
    public void onButtonActivateClickListner(View view){
        String activationKey = editTextActivationKey.getText().toString();
        if(!activationKey.isEmpty()){
            /* Fixme praveen send the activation key to server validate and then store it to sqlite */
            saveActivationKey(activationKey);
        }
    }

    private void saveActivationKey(String activationKey){
        if(Activation.isNew()){
            Activation activation = new Activation();
            activation.setIsActivated(true);
            activation.setActivationKey(activationKey);
            activation.save();
        }
    }

    private boolean hasActivated(){
        return Activation.hasActivated(TAG);
    }
}
