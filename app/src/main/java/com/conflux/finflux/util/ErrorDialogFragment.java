package com.conflux.finflux.util;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.conflux.finflux.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jagadeeshakn on 7/15/2016.
 */
public class ErrorDialogFragment extends DialogFragment {

    private final String TAG = getClass().getSimpleName();
    private static final String CLASS_NAME = "class name";
    private static final String MESSAGE = "message";
    private static final String MESSAGE_TYPE = "message type";


    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.tv_message)
    TextView tv_message;
    @Bind(R.id.btn_ok)
    Button btn_ok;

    private View rootView;

    private String className;
    private String message;
    private String messageType;


    public static ErrorDialogFragment newInstance(String className, String message, String messageType) {
        ErrorDialogFragment fragment = new ErrorDialogFragment();
        Bundle args = new Bundle();
        args.putString(CLASS_NAME, className);
        args.putString(MESSAGE, message);
        args.putString(MESSAGE_TYPE, messageType);
        fragment.setArguments(args);
        return fragment;
    }

    public ErrorDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            className = getArguments().getString(CLASS_NAME);
            message = getArguments().getString(MESSAGE);
            messageType = getArguments().getString(MESSAGE_TYPE);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.error_dialog_fragment, container, false);
        ButterKnife.bind(this, rootView);
        setTitle();
        displayMessage();
        return rootView;
    }

    private void setTitle() {
        Logger.d(TAG + " -> " + className, message);
        tv_title.setText(messageType);
    }

    private void displayMessage() {
        Logger.d(TAG + " -> " + className, message);
        tv_message.setText(message);
    }

    @OnClick(R.id.btn_ok)
    public void onOkayButtonPressed(View view) {
        getDialog().dismiss();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
