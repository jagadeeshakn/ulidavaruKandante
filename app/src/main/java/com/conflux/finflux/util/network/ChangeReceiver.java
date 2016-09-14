package com.conflux.finflux.util.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.conflux.finflux.util.AppConstants;

public class ChangeReceiver extends BroadcastReceiver {
    private boolean isOnline = false;

    public ChangeReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        this.isOnline = NetworkUtil.getConnectivityStatusBoolean(context);
        Bundle extras = intent.getExtras();
        Intent i = new Intent(AppConstants.NetworkChangeListner);
        // Data you need to pass to activity
        extras.putBoolean(AppConstants.NetworkStatus,NetworkUtil.getConnectivityStatusBoolean(context));
        i.putExtra(AppConstants.Network,extras);

        context.sendBroadcast(i);
    }

}
