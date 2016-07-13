package com.conflux.finflux.util.bluetooth.services;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;

/**
 * Created by Praveen J U on 7/7/2016.
 */
public interface BroadCastReceiverService {
    void reigisterReceiver(BroadcastReceiver broadcastReceiver, IntentFilter filter);
    void unregisterReceiverService(BroadcastReceiver broadcastReceiver);
}
