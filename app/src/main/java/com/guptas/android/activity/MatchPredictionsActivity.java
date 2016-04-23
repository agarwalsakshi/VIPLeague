package com.guptas.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.guptas.android.R;
import com.guptas.android.datahelper.MatchPredictionsAdapter;
import com.guptas.android.model.UserTeamPair;
import com.guptas.android.utils.ApplicationConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by pavitra on 15/4/16.
 */
public class MatchPredictionsActivity extends Activity {
    private RecyclerView recyclerView;
    private MatchPredictionsAdapter mAdapter;
    private ArrayList<UserTeamPair> userTeamList;
    private Bundle extras;
    private String matchname;
    private Map<String, String> userid_username_map;
    private List<UserTeamPair> userTeamPairList;

    TextView matchnameTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_predictions);

        extras = getIntent().getExtras();
        userTeamList = (ArrayList<UserTeamPair>) extras.getSerializable("UserTeamPair");
//      userTeamList is a key value pair of userid and selected team, iterating a number of times equal to the number of users.

        matchname = extras.getString("Matchname");

        matchnameTextview = (TextView) findViewById(R.id.Matchname_textview);
        matchnameTextview.setText(matchname);

        userid_username_map = new HashMap<>();
        Firebase firebase = new Firebase(ApplicationConstants.FIREBASSE_URL).child("Users");

        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userTeamPairList = new ArrayList<UserTeamPair>();

                for (DataSnapshot user:dataSnapshot.getChildren()) {
                    userid_username_map.put(user.getKey(), ((Map<String, String>) user.getValue()).get("name"));
                    //userid_username_map is a key value pair of userids and usernames of all users.
                }

                Log.e("User Username Map", userid_username_map.toString());

                for (UserTeamPair userTeamPair: userTeamList) {
                    Log.e("PredictorId", userTeamPair.getPredictorName());
                    userTeamPairList.add(new UserTeamPair(userid_username_map.get(userTeamPair.getPredictorName()), userTeamPair.getPredictedTeam()));
                }

                recyclerView = (RecyclerView) findViewById(R.id.match_users_predictions_recycler_view);

                mAdapter = new MatchPredictionsAdapter(userTeamPairList);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public List<UserTeamPair> convertUseridsToUsernames(final List<UserTeamPair> userTeamPairs) {


        return userTeamPairList;
    }
}