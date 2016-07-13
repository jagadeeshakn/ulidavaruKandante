package com.conflux.finflux.settings.preferences;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import com.conflux.finflux.util.bluetooth.BluetoothAdmin;
import com.conflux.finflux.util.bluetooth.data.BluetoothDeviceData;

import java.util.Set;

/**
 * Created by Praveen J U on 7/6/2016.
 */
public class BluetoothDevicePreference  {
    Set<BluetoothDevice> pairedDevices;
    BluetoothDeviceData bluetoothDeviceData;
    public BluetoothDevicePreference() {
        BluetoothAdmin.setBluetooth(true);
        BluetoothAdapter bta = BluetoothAdmin.getBluetoothAdapter();
        pairedDevices = bta.getBondedDevices();
        bluetoothDeviceData = new BluetoothDeviceData();
        bluetoothDeviceData.createEntries(pairedDevices.size());
        bluetoothDeviceData.createValues(pairedDevices.size());
    }

    public BluetoothDeviceData getBluetoothEntriesAndValues(){
        int i = 0;
        for (BluetoothDevice dev : pairedDevices) {
            bluetoothDeviceData.setEntries(dev.getName(),i);
            bluetoothDeviceData.setEntryValues(dev.getAddress(),i);
            i++;
        }
        return bluetoothDeviceData;
    }
}
