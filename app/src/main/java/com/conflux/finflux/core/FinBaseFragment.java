package com.conflux.finflux.core;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.conflux.finflux.R;

/**
 * Created by jagadeeshakn on 7/8/2016.
 */
public class FinBaseFragment extends Fragment {

    private BaseActivityCallback callback;
    private Activity activity;
    private InputMethodManager inputManager;
    private FinProgressBarHandler mProgressBarHandler;

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        try {
            callback = (BaseActivityCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement " +
                    "BaseActivityCallback");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inputManager = (InputMethodManager) getActivity().getSystemService(Context
                .INPUT_METHOD_SERVICE);
        mProgressBarHandler = new FinProgressBarHandler(getActivity());
    }

    public void showAlertDialog(String title, String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(title);
        dialog.setMessage(message).setCancelable(false).setPositiveButton("OK", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                }).create().show();
    }

    public Toolbar getToolbar() {
        return ((FinBaseActivity) getActivity()).getToolbar();
    }

    protected void showFinfluxProgressDialog() {
        showFinfluxProgressDialog(getString(R.string.please_wait));
    }

    protected void showFinfluxProgressDialog(String message) {
        if (callback != null)
            callback.showProgress(message);
    }

    protected void hideFinfluxProgressDialog() {
        if (callback != null)
            callback.hideProgress();
    }

    protected void logout() {
        callback.logout();
    }

    protected void setToolbarTitle(String title) {
        callback.setToolbarTitle(title);
    }

    public void hideKeyboard(View view) {
        inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager
                .HIDE_IMPLICIT_ONLY);
    }

    protected void showFinfluxProgressBar() {
        mProgressBarHandler.show();
    }

    protected void hideFinfluxProgressBar() {
        mProgressBarHandler.hide();
    }


}
