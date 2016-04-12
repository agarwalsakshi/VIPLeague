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
    private RecyclerView allUsersList;
    private ArrayList<MatchInfo> matchInfos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);
        allUsersList = (RecyclerView) findViewById(R.id.all_users_list);

        Firebase firebaseRef = new Firebase(ApplicationConstants.FIREBASSE_URL);

        firebaseRef.child("Matches").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (AppUtils.getInstance().isNetworkAvailable(AllUsersActivity.this)) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        MatchInfo matchInfo = postSnapshot.getValue(MatchInfo.class);
                        matchInfos.add(matchInfo);
                    }
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        firebaseRef.child("UserInput").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (AppUtils.getInstance().isNetworkAvailable(AllUsersActivity.this)) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Log.e("PostSnapshot", postSnapshot.toString());


                        HashMap<String, ArrayList<HashMap<String, String>>> userMap = new HashMap<>();
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
}
