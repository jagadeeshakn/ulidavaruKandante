package com.conflux.finflux.util;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.conflux.finflux.infrastructure.FinfluxApplication;

/**
 * Created by Praveen J U on 7/4/2016.
 */
public class Toaster {
    public static void show(View view, String text) {
        final Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id
                .snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }

    public static void show(View view, int res) {
        show(view, FinfluxApplication.getInstance().getResources().getString(res));
    }
}
