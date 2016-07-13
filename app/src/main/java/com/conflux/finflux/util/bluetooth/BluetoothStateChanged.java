package com.conflux.finflux.util.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.conflux.finflux.settings.preferences.BluetoothDevicePreference;
import com.conflux.finflux.util.Logger;
import com.conflux.finflux.util.bluetooth.data.BluetoothDeviceData;

public class BluetoothStateChanged extends BroadcastReceiver {

    private final String TAG = getClass().getSimpleName();
    BluetoothDevicePreference bluetoothDevicePreference;
    BluetoothDeviceData bluetoothDeviceData;
    public BluetoothStateChanged() {
        Logger.d(TAG,"Receiver initiated");
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        final String action = intent.getAction();
        Logger.d(TAG,"Receiver received");
        if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
            final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,
                    BluetoothAdapter.ERROR);
            switch (state) {
                case BluetoothAdapter.STATE_OFF:
                    Logger.d(TAG,"Bluetooth status : off");
                    break;
                case BluetoothAdapter.STATE_TURNING_OFF:
                    Logger.d(TAG,"Bluetooth status : turning off");
                    break;
                case BluetoothAdapter.STATE_ON:
                    Logger.d(TAG,"Bluetooth status : state on");
                    populateBluetoothListPreference();
                    break;
                case BluetoothAdapter.STATE_TURNING_ON:
                    Logger.d(TAG,"Bluetooth status : turning on");
                    break;
            }
        }
    }

    public BluetoothDeviceData populateBluetoothListPreference(){
        bluetoothDevicePreference = new BluetoothDevicePreference();
        bluetoothDeviceData = bluetoothDevicePreference.getBluetoothEntriesAndValues();
        return bluetoothDeviceData;
    }


}
