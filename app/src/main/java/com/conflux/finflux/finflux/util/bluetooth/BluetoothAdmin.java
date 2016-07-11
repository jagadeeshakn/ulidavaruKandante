package com.conflux.finflux.finflux.util.bluetooth;

import android.bluetooth.BluetoothAdapter;

/**
 * Created by Praveen J U on 7/7/2016.
 */
public class BluetoothAdmin {
    public static final String DEVICE_SETTINGS_PAGE = "com.conflux.finflux.finflux.settings.activity.ApplicationSettings$NotificationPreferenceFragment";
    public static  BluetoothAdapter bluetoothAdapter;
    public static boolean setBluetooth(boolean enable) {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        boolean isEnabled = bluetoothAdapter.isEnabled();
        if (enable && !isEnabled) {
            return bluetoothAdapter.enable();
        }
        else if(!enable && isEnabled) {
            return bluetoothAdapter.disable();
        }
        // No need to change bluetooth state
        return true;
    }

    public static BluetoothAdapter getBluetoothAdapter(){
        return bluetoothAdapter;
    }
}
