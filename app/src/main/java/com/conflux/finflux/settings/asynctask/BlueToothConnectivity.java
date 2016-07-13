package com.conflux.finflux.settings.asynctask;

import android.os.AsyncTask;

import com.conflux.finflux.settings.activity.ApplicationSettings;
import com.conflux.finflux.util.PrefManager;
import com.conflux.finflux.util.bluetooth.BluetoothComm;

/**
 * Created by Praveen J U on 7/8/2016.
 */
public class BlueToothConnectivity extends AsyncTask<Void,Void,Boolean> {
    @Override
    protected Boolean doInBackground(Void... voids) {
        final String pairedDeiviceAddress = PrefManager.getPairedDevice();
        if(!pairedDeiviceAddress.isEmpty()) {
            ApplicationSettings.bluetoothComm = new BluetoothComm(pairedDeiviceAddress);
            ApplicationSettings.bluetoothComm.createConn();
            if(ApplicationSettings.bluetoothComm.isConnect()) {
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }
}
