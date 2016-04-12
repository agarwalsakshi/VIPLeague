package com.guptas.android.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.guptas.android.R;
import com.guptas.android.utils.AppUtils;
import com.guptas.android.utils.SharedPreferenceHandler;

public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(SharedPreferenceHandler.getInstance().getUserId(this) != null)
        {
            AppUtils.getInstance().pageTransition(this, SelectTeamActivity.class);
        }
        else
        {
            setContentView(R.layout.activity_login);
        }

    }

}
