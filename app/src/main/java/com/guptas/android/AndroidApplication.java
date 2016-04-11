package com.guptas.android;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by sakshiagarwal on 11/04/16.
 */
public class AndroidApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }
}