package com.conflux.finflux.finflux.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.conflux.finflux.finflux.infrastructure.FinfluxApplication;

import java.util.Set;
import java.util.logging.StreamHandler;

/**
 * @author praveen
 */
public class PrefManager {

    private static final String USER_ID = "preferences_user_id";
    private static final String TOKEN = "preferences_token";
    private static final String TENANT = "preferences_tenant";
    private static final String INSTANCE_URL = "preferences_instance";
    private static final String INSTANCE_DOMAIN = "preferences_domain";
    private static final String PORT = "preferences_port";
    private static final String ORGANIZATION = "organization_name";
    private static final String CAN_USE_DEFAULT_CERTIFICATE = "can use default certificate";

    public static void canUseDefaultCertificate(boolean shouldByPassSSl){
        putBoolean(CAN_USE_DEFAULT_CERTIFICATE, shouldByPassSSl);
    }

    public static boolean isSetUseDefaultCertificate(){
        return getBoolean(CAN_USE_DEFAULT_CERTIFICATE,false);
    }

    public static SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(FinfluxApplication.getInstance().getApplicationContext());
    }

    public static void clearPrefs() {
        getPreferences().edit().clear().commit();
    }

    public static int getInt(String preferenceKey, int preferenceDefaultValue) {
        return getPreferences().getInt(preferenceKey, preferenceDefaultValue);
    }

    public static void putInt(String preferenceKey, int preferenceValue) {
        getPreferences().edit().putInt(preferenceKey, preferenceValue).commit();
    }

    public static long getLong(String preferenceKey, long preferenceDefaultValue) {
        return getPreferences().getLong(preferenceKey, preferenceDefaultValue);
    }

    public static void putLong(String preferenceKey, long preferenceValue) {
        getPreferences().edit().putLong(preferenceKey, preferenceValue).commit();
    }

    public static float getFloat(String preferenceKey, float preferenceDefaultValue) {
        return getPreferences().getFloat(preferenceKey, preferenceDefaultValue);
    }

    public static void putFloat(String preferenceKey, float preferenceValue) {
        getPreferences().edit().putFloat(preferenceKey, preferenceValue).commit();
    }

    public static boolean getBoolean(String preferenceKey, boolean preferenceDefaultValue) {
        return getPreferences().getBoolean(preferenceKey, preferenceDefaultValue);
    }

    public static void putBoolean(String preferenceKey, boolean preferenceValue) {
        getPreferences().edit().putBoolean(preferenceKey, preferenceValue).commit();
    }

    public static String getString(String preferenceKey, String preferenceDefaultValue) {
        return getPreferences().getString(preferenceKey, preferenceDefaultValue);
    }

    public static void putString(String preferenceKey, String preferenceValue) {
        getPreferences().edit().putString(preferenceKey, preferenceValue).commit();
    }

    public static void putStringSet(String preferencesKey, Set<String> values) {
        getPreferences().edit().putStringSet(preferencesKey, values).commit();
    }

    public static Set<String> getStringSet(String preferencesKey) {
        return getPreferences().getStringSet(preferencesKey, null);
    }

    // Concrete methods

    /**
     * Authentication
     */

    public static void saveToken(String token) {
        putString(TOKEN, token);
    }

    public static void clearToken() {
        putString(TOKEN, "");
    }

    public static String getToken() {
        return getString(TOKEN, "");
    }

    public static boolean isAuthenticated() {
        return !TextUtils.isEmpty(getToken());
    }

    public static int getUserId() {
        return getInt(USER_ID, -1);
    }

    public static void setUserId(int id) {
        putInt(USER_ID, id);
    }

    public static void setOrganizationName(String organizationName){
        putString(ORGANIZATION,organizationName);
    }

    public static void setTenant(String tenant) {
            putString(TENANT, tenant);
    }

    public static String getTenant() {
        return getString(TENANT, "");
    }

    /**
     * Connection
     */
    public static void setInstanceUrl(String instanceUrl) {
        putString(INSTANCE_URL, instanceUrl+'/');
    }

    public static String getInstanceUrl() {
        return getString(INSTANCE_URL, "");
    }

    public static void setInstanceDomain(String instanceDomain) {
        putString(INSTANCE_DOMAIN, instanceDomain);
    }

    public static String getInstanceDomain() {
        return getString(INSTANCE_DOMAIN, "");
    }

    public static String getOrganization(){
        return getString(ORGANIZATION,"");
    }

    public static void setPort(String port) {
        if (!TextUtils.isEmpty(port))
            putString(PORT, port);
    }

    public static String getPort() {
        return getString(PORT, "");
    }
}


