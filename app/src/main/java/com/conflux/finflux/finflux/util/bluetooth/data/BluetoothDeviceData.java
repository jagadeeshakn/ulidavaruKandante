package com.conflux.finflux.finflux.util.bluetooth.data;

/**
 * Created by Praveen J U on 7/7/2016.
 */
public class BluetoothDeviceData {
    private CharSequence[] entries;
    private CharSequence[] entryValues;

    public CharSequence[] getEntries() {
        return entries;
    }

    public void createEntries(int size){
        entries = new CharSequence[size];
    }

    public void createValues(int size){
        entryValues = new CharSequence[size];
    }

    public void setEntries(CharSequence entries,int i) {
        this.entries[i] = entries;
    }

    public CharSequence[] getEntryValues() {
        return entryValues;
    }

    public void setEntryValues(CharSequence entryValue, int i) {
        this.entryValues[i] = entryValue;
    }
}
