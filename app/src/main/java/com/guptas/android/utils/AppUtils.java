package com.guptas.android.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by sakshiagarwal on 09/04/16.
 */
public class AppUtils {
    private static AppUtils instance;
    private ProgressDialog dialog;

    public static AppUtils getInstance(){
        if (instance == null){
            instance = new AppUtils();
        }
        return instance;
    }


    public boolean isNetworkAvailable(Activity activity){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void pageTransition(Activity activity, Class<?> transitionclass){
        Intent transitionintent = new Intent(activity, transitionclass);
        activity.startActivity(transitionintent);
    }

    public void showProgressDialog(Activity activity, String message){
        if (dialog == null) {
            dialog = new ProgressDialog(activity);
        }
        dialog.setMessage(message);
        dialog.setInverseBackgroundForced(false);
        dialog.setCancelable(false);
        dialog.show();
    }

    public void dismissProgressDialog(){
        if (dialog != null){
            Log.e("App util", "Dismissing");
            dialog.dismiss();
            dialog = null;
        }
    }
}
