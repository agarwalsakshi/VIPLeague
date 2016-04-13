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
        editor.putString(ApplicationConstants.FACEBOOK_USER_NAME, userName);
        editor.apply();
    }

    public String getUserName(Activity activity){
        SharedPreferences pref = activity.getSharedPreferences(ApplicationConstants.SHARED_PREF, ApplicationConstants.PRIVATE_MODE_PREFERENCE);
        if (pref.contains(ApplicationConstants.FACEBOOK_USER_NAME)) {
            return pref.getString(ApplicationConstants.FACEBOOK_USER_NAME, null);
        } else {
            return null;
        }
    }

    public void saveUserId(Activity activity, String userId) {
        SharedPreferences pref = activity.getSharedPreferences(ApplicationConstants.SHARED_PREF, ApplicationConstants.PRIVATE_MODE_PREFERENCE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(ApplicationConstants.FACEBOOK_USER_ID, userId);
        editor.apply();
    }

    public String getUserId(Activity activity){
        SharedPreferences pref = activity.getSharedPreferences(ApplicationConstants.SHARED_PREF, ApplicationConstants.PRIVATE_MODE_PREFERENCE);
        if (pref.contains(ApplicationConstants.FACEBOOK_USER_ID)) {
            return pref.getString(ApplicationConstants.FACEBOOK_USER_ID, null);
        } else {
            return null;
        }
    }

    public void deleteAllData(Activity activity){
        SharedPreferences pref = activity.getSharedPreferences(ApplicationConstants.SHARED_PREF, ApplicationConstants.PRIVATE_MODE_PREFERENCE);
        pref.edit().clear().commit();
    }


}