package com.guptas.android.utils;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by sakshiagarwal on 08/04/16.
 */
public class SharedPreferenceHandler {
    private static SharedPreferenceHandler instance;

    public static SharedPreferenceHandler getInstance() {
        if (instance == null) {
            instance = new SharedPreferenceHandler();
        }
        return instance;
    }

    public void saveUserName(Activity activity, String userName) {
        SharedPreferences pref = activity.getSharedPreferences(ApplicationConstants.SHARED_PREF, ApplicationConstants.PRIVATE_MODE_PREFERENCE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("username", userName);
        editor.apply();
    }

    public String getUserName(Activity activity){
        SharedPreferences pref = activity.getSharedPreferences(ApplicationConstants.SHARED_PREF, ApplicationConstants.PRIVATE_MODE_PREFERENCE);
        if (pref.contains("username")) {
            return pref.getString("username", null);
        } else {
            return null;
        }
    }

    public void clearUserName(Activity activity) {
        SharedPreferences pref = activity.getSharedPreferences(ApplicationConstants.SHARED_PREF, ApplicationConstants.PRIVATE_MODE_PREFERENCE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.apply();
    }

}