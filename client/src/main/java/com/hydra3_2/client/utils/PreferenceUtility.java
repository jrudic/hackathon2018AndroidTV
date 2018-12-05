package com.hydra3_2.client.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


/**
 * Utility class for Preference data related to networking
 */
public class PreferenceUtility {

    private static final String TAG = "PreferenceUtility";


    private static final String USERNAME_KEY = "username_uid_key";

    // Cache the token so the decryption doesn't have to take place on every network request
    private static String sAuthToken = null;

    private PreferenceUtility() {
    }

    /**
     * Sets username
     *
     * @param context  - context
     * @param username - username
     */
    public static void setUsername(Context context, String username) {
        setString(context, USERNAME_KEY, username);
    }

    /**
     * Gets username from preferences
     *
     * @param context - context
     * @return username
     */
    public static String getUsername(Context context) {
        return getString(context, USERNAME_KEY, "");
    }


    private static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    private static String getString(Context context, String key, String defaultValue) {
        return getSharedPreferences(context).getString(key, defaultValue);
    }

    private static void setString(Context context, String key, String value) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(key, value);
        editor.apply();
    }

    private static long getLong(Context context, String key, long defaultValue) {
        return getSharedPreferences(context).getLong(key, defaultValue);
    }

    private static void setLong(Context context, String key, long value) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putLong(key, value);
        editor.apply();
    }

    private static int getInt(Context context, String key, int defaultValue) {
        return getSharedPreferences(context).getInt(key, defaultValue);
    }

    private static void setInt(Context context, String key, int value) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putInt(key, value);
        editor.apply();
    }

}
