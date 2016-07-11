package com.conflux.finflux.finflux.settings.activity;


import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.support.v7.app.ActionBar;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

import com.conflux.finflux.finflux.R;
import com.conflux.finflux.finflux.infrastructure.FinfluxApplication;
import com.conflux.finflux.finflux.settings.asynctask.BlueToothConnectivity;
import com.conflux.finflux.finflux.settings.preferences.BluetoothDevicePreference;
import com.conflux.finflux.finflux.util.Logger;
import com.conflux.finflux.finflux.util.PrefManager;
import com.conflux.finflux.finflux.util.alert.AlertMessage;
import com.conflux.finflux.finflux.util.bluetooth.BluetoothAdmin;
import com.conflux.finflux.finflux.util.bluetooth.BluetoothComm;
import com.conflux.finflux.finflux.util.bluetooth.BluetoothStateChanged;
import com.conflux.finflux.finflux.util.bluetooth.data.BluetoothDeviceData;
import com.conflux.finflux.finflux.util.bluetooth.services.BroadCastReceiverService;

import java.util.List;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p/>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class ApplicationSettings extends AppCompatPreferenceActivity implements BroadCastReceiverService {

    private final String TAG = getClass().getSimpleName();
    private BroadcastReceiver broadcastReceiver;
    private static BluetoothDevicePreference bluetoothDevicePreference;
    private static BluetoothDeviceData bluetoothDeviceData;
    public static BluetoothComm bluetoothComm;
    private static boolean result;
    /**
     * A preference value change listener that updates the preference's summary
     * to reflect its new value.
     */
    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();

            if (preference instanceof ListPreference) {
                // For list preferences, look up the correct display value in
                // the preference's 'entries' list.
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);

                // Set the summary to reflect the new value.
                preference.setSummary(
                        index >= 0
                                ? listPreference.getEntries()[index]
                                : null);

            } else if (preference instanceof RingtonePreference) {
                // For ringtone preferences, look up the correct display value
                // using RingtoneManager.
                if (TextUtils.isEmpty(stringValue)) {
                    // Empty values correspond to 'silent' (no ringtone).
                    preference.setSummary(R.string.pref_ringtone_silent);

                } else {
                    Ringtone ringtone = RingtoneManager.getRingtone(
                            preference.getContext(), Uri.parse(stringValue));

                    if (ringtone == null) {
                        // Clear the summary if there was a lookup error.
                        preference.setSummary(null);
                    } else {
                        // Set the summary to reflect the new ringtone display
                        // name.
                        String name = ringtone.getTitle(preference.getContext());
                        preference.setSummary(name);
                    }
                }

            } else if(preference.getKey().equals("enable_peripherals")){
                // For all other preferences, set the summary to the value's
                // simple string representation.
                if(stringValue.equals("true")) {
                    preference.setSummary(preference.getContext().getString(R.string.peripheral_enable));
                }else {
                    preference.setSummary(preference.getContext().getString(R.string.peripheral_diabled));
                }
            }
            return true;
        }
    };

    private final static BluetoothStateChanged mBluetoothChangeReceiver = new BluetoothStateChanged();

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

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
    };

    public void populateBluetoothListPreference(){
        bluetoothDevicePreference = new BluetoothDevicePreference();
        bluetoothDeviceData = bluetoothDevicePreference.getBluetoothEntriesAndValues();
    }


    /**
     * Helper method to determine if the device has an extra-large screen. For
     * example, 10" tablets are extra-large.
     */
    private static boolean isXLargeTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    /**
     * Binds a preference's summary to its value. More specifically, when the
     * preference's value is changed, its summary (line of text below the
     * preference title) is updated to reflect the value. The summary is also
     * immediately updated upon calling this method. The exact display format is
     * dependent on the type of preference.
     *
     * @see #sBindPreferenceSummaryToValueListener
     */
    private static void bindPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        // Trigger the listener immediately with the preference's
        // current value.
        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    private static void bindPreferenceSummaryToBoolean(Preference preference) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        // Trigger the listener immediately with the preference's
        // current value.
        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getBoolean(preference.getKey(), false));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BluetoothAdmin.setBluetooth(true);
        Logger.d(TAG,"The activity started");
        result = FinfluxApplication.getEvoluteSetUp().blActivateLibrary(this,R.raw.licence_full);
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerBroadCastReceiver(mBluetoothChangeReceiver,filter);

        setupActionBar();
    }



    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            if (!super.onMenuItemSelected(featureId, item)) {
                NavUtils.navigateUpFromSameTask(this);
            }
            return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onIsMultiPane() {
        return isXLargeTablet(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.pref_headers, target);
        Header header = onGetNewHeader();
    }

    @Override
    public void onHeaderClick(Header header, int position) {
        super.onHeaderClick(header, position);
        /*if(header.fragment.equals(BluetoothAdmin.DEVICE_SETTINGS_PAGE)){
            BluetoothAdmin.setBluetooth(true);
        }*/
    }

    /**
     * This method stops fragment injection in malicious applications.
     * Make sure to deny any unknown fragments here.
     */
    protected boolean isValidFragment(String fragmentName) {
        if(fragmentName.equals(BluetoothAdmin.DEVICE_SETTINGS_PAGE)){
            BluetoothAdmin.setBluetooth(true);
        }
        return PreferenceFragment.class.getName().equals(fragmentName)
                || CollectionSheetPreferenceFragment.class.getName().equals(fragmentName)
                || DevicePreferenceFragment.class.getName().equals(fragmentName);
    }

    @Override
    public void reigisterReceiver(BroadcastReceiver broadcastReceiver, IntentFilter filter) {
        registerBroadCastReceiver(broadcastReceiver,filter);
    }

    @Override
    public void unregisterReceiverService(BroadcastReceiver broadcastReceiver) {
        unregisterReceiver(broadcastReceiver);
    }


    /**
     * This fragment shows notification preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class DevicePreferenceFragment extends PreferenceFragment  {
        private final String TAG = getClass().getSimpleName();
        ListPreference listPreference;
        ProgressDialog progressDialog;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if(result){
                Logger.d(getClass().getSimpleName(),"library status Active");
            }else {
                Logger.d(getClass().getSimpleName(),"library status Inactive");
            }
            addPreferencesFromResource(R.xml.pref_device);
            setHasOptionsMenu(true);
            Logger.d(TAG,"evolute device status "+FinfluxApplication.getEvoluteSetUp().isLibActivated());
            bluetoothDeviceData = mBluetoothChangeReceiver.populateBluetoothListPreference();
            listPreference = (ListPreference)findPreference("paired_device");
            listPreference.setEntries(bluetoothDeviceData.getEntries());
            listPreference.setEntryValues(bluetoothDeviceData.getEntryValues());
            bindPreferenceSummaryToBoolean(findPreference("enable_peripherals"));
            bindPreferenceSummaryToValue(findPreference("paired_device"));
        }




        @Override
        public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
            Logger.d(getClass().getSimpleName(),"on click tree");
            ListPreference listPreference = (ListPreference)findPreference("paired_device");

            if(preference.getKey().equals(getString(R.string.enable_peripherals))){
                SwitchPreference switchPreference = (SwitchPreference)findPreference("enable_peripherals");
                if(switchPreference.isChecked()){
                    Logger.d(TAG,"Enable Peripherals has been initiated");
                    if(listPreference.getEntry() != null && listPreference.getValue() != null){
                        Logger.d(TAG,"Paired device is present");
                        Logger.d(getClass().getSimpleName(),"the entry is "+listPreference.getEntry());
                        Logger.d(getClass().getSimpleName(),"the value is "+listPreference.getValue());
                        Logger.d(TAG,"library activated status " +FinfluxApplication.getEvoluteSetUp().isLibActivated());
                    }else {
                        Logger.d(TAG,"Paired device is not present");
                        //display popup message to select paired devices
                        displayAlertMessagewithConfirmationButton(getString(R.string.error_select_paired_device),getActivity());
                        switchPreference.setChecked(false);
                    }
                }
            }
            if(preference.getKey().equals("enable_printer")){
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Please wait");
                progressDialog.show();
                Logger.d(TAG,"Enable printer has been selected");
                final SwitchPreference switchPreference = (SwitchPreference)findPreference("enable_printer");
                if(switchPreference.isChecked()) {
                    Logger.d(TAG,"enable printer is set yes ");
                    //alert the user to turn on the evolute device and then click ok to carry on the
                    //connection to the device and enable the print
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle(getActivity().getString(R.string.alert_message));
                    builder.setMessage(getString(R.string.dialog_turn_on_printing_device))
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    enablePrinter(switchPreference);
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }else {
                    if(bluetoothComm != null && bluetoothComm.isConnect()){
                        bluetoothComm.closeConn();
                        bluetoothComm =  null;
                    }
                    progressDialog.dismiss();
                }
            }

            return super.onPreferenceTreeClick(preferenceScreen, preference);
        }

        private void enablePrinter(final SwitchPreference switchPreference){
            //call async task to communicate to the evolute device
            BlueToothConnectivity blueToothConnectivity = new BlueToothConnectivity() {
                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    super.onPostExecute(aBoolean);
                    if (aBoolean) {
                        if (isAdded()) {
                            switchPreference.setChecked(true);
                            if (progressDialog != null && progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                        }

                    }else {
                        if(isAdded()){
                            switchPreference.setChecked(false);
                            if(progressDialog != null && progressDialog.isShowing()){
                                progressDialog.dismiss();
                                bluetoothComm = null;
                                AlertMessage.displayAlert(getActivity(),getString(R.string.error_connecting_printer));
                            }

                        }
                    }
                }
            };
            blueToothConnectivity.execute();
        }


        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), ApplicationSettings.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }

    private void registerBroadCastReceiver(BroadcastReceiver mReceiver, IntentFilter filter){
        registerReceiver(mReceiver, filter);
    }

    private static void displayAlertMessagewithConfirmationButton(String message,Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.alert_message));
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


    /**
     * This fragment shows data and sync preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class CollectionSheetPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_collection_sheet_print);
            setHasOptionsMenu(true);
        }

        @Override
        public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
            if(preference.getKey().equals("enable_collectionsheet_client_receipt")||
                    preference.getKey().equals("enable_collectionsheet_office_copy")){
                SwitchPreference switchPreference = (SwitchPreference)preference;
                if(switchPreference.isChecked()){
                    if(PrefManager.getBoolean("enable_printer",false)){
                        switchPreference.setChecked(true);
                    }else {
                        displayAlertMessagewithConfirmationButton(getString(R.string.error_enable_printer),getActivity());
                        switchPreference.setChecked(false);
                    }
                }
            }
            return super.onPreferenceTreeClick(preferenceScreen, preference);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), ApplicationSettings.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(bluetoothComm != null && bluetoothComm.isConnect()){
            bluetoothComm.closeConn();
        }
        unregisterReceiverService(mBluetoothChangeReceiver);
    }

}
