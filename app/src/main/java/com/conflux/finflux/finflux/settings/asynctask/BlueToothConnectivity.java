package com.conflux.finflux.finflux.settings.asynctask;

import android.os.AsyncTask;
import android.preference.SwitchPreference;
import android.speech.tts.Voice;

import com.conflux.finflux.finflux.settings.activity.ApplicationSettings;
import com.conflux.finflux.finflux.util.PrefManager;
import com.conflux.finflux.finflux.util.bluetooth.BluetoothComm;
import com.facebook.stetho.common.StringUtil;

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
