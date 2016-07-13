package com.conflux.finflux.infrastructure;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.conflux.finflux.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ApplicationActivationFragment extends Fragment {

    public ApplicationActivationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_application_activation, container, false);
    }
}
