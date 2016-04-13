package com.guptas.android.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.guptas.android.R;
import com.guptas.android.model.MatchInfo;
import com.guptas.android.utils.AppUtils;
import com.guptas.android.utils.ApplicationConstants;


import java.util.ArrayList;
import java.util.HashMap;

public class AllUsersActivity extends AppCompatActivity {
    Firebase firebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);
        firebaseRef = new Firebase(ApplicationConstants.FIREBASSE_URL);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
