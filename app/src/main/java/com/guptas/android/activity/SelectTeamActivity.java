package com.guptas.android.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.facebook.login.LoginManager;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.guptas.android.R;
import com.guptas.android.datahelper.MatchDataHelper;
import com.guptas.android.datahelper.MatchInfoViewHolder;
import com.guptas.android.model.MatchInfo;
import com.guptas.android.utils.AppUtils;
import com.guptas.android.utils.ApplicationConstants;
import com.guptas.android.utils.SharedPreferenceHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.guptas.android.R.layout.*;

public class SelectTeamActivity extends AppCompatActivity {
    private RecyclerView match_recycler_view;
    private Firebase firebaseRef;
    private LinearLayout parentLayout;
    private Map<String, String> user_match_prediction_pair;
    private Integer userPoints = 0;
    private Map<String, String> match_winner_pair;
    FirebaseRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_select_team);
        getSupportActionBar().setHomeButtonEnabled(true);
        AppUtils.getInstance().showProgressDialog(this, "Loading..");
        parentLayout = (LinearLayout) findViewById(R.id.parentLayout);
        firebaseRef = new Firebase(ApplicationConstants.FIREBASSE_URL);
        match_recycler_view = (RecyclerView) findViewById(R.id.match_list);
        match_recycler_view.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new FirebaseRecyclerAdapter<MatchInfo, MatchInfoViewHolder>(MatchInfo.class, R.layout.list_match, MatchInfoViewHolder.class, firebaseRef.child("Matches")) {
            @Override
            protected void populateViewHolder(MatchInfoViewHolder matchInfoViewHolder, final MatchInfo matchInfo, int i) {
                AppUtils.getInstance().dismissProgressDialog();
                final Long Timestamp = matchInfo.getTimestamp();
                Date date = new Date((long) Timestamp * 1000);
                SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd");
                SimpleDateFormat stf = new SimpleDateFormat("hh a");
                String formattedDate = sdf.format(date);
                String formatTime = stf.format(date);
                matchInfoViewHolder.firstTeamFlag.setBackground(MatchDataHelper.getInstance().convertNameToLogo(matchInfo.getTeam1(), SelectTeamActivity.this));
                matchInfoViewHolder.secondTeamFlag.setBackground(MatchDataHelper.getInstance().convertNameToLogo(matchInfo.getTeam2(), SelectTeamActivity.this));
                matchInfoViewHolder.matchDate.setText(formattedDate + " , " + formatTime);
                matchInfoViewHolder.firstTeamFlag.setOnClickListener(new View.OnClickListener()

                {
                    @Override
                    public void onClick(View v) {
                        if ((Timestamp - 43200) < (System.currentTimeMillis() / 1000L) && (System.currentTimeMillis() / 1000L) < Timestamp) {
                            firebaseRef.child("UserInput").child(SharedPreferenceHandler.getInstance().getUserId(SelectTeamActivity.this)).child(matchInfo.getTeam1() + "vs" + matchInfo.getTeam2()).setValue(matchInfo.getTeam1());
                            Snackbar.make(parentLayout, "Your selection has been updated. You can change it anytime before the match starts.", Snackbar.LENGTH_LONG).show();
                        } else if ((System.currentTimeMillis() / 1000L) > Timestamp) {
                            Snackbar.make(parentLayout, "Oops! Selection for the team has been closed.", Snackbar.LENGTH_LONG).show();
                        } else {
                            Snackbar.make(parentLayout, "You can select your team 12 hours before the match starts.", Snackbar.LENGTH_LONG).show();
                        }

                    }
                });
                matchInfoViewHolder.secondTeamFlag.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if ((Timestamp - 43200) < (System.currentTimeMillis() / 1000L) && (System.currentTimeMillis() / 1000L) < Timestamp) {
                            firebaseRef.child("UserInput").child(SharedPreferenceHandler.getInstance().getUserId(SelectTeamActivity.this)).child(matchInfo.getTeam1() + "vs" + matchInfo.getTeam2()).setValue(matchInfo.getTeam2());
                            Snackbar.make(parentLayout, "Your selection has been updated. You can change it anytime before the match starts.", Snackbar.LENGTH_LONG).show();
                        } else if ((System.currentTimeMillis() / 1000L) > Timestamp) {
                            Snackbar.make(parentLayout, "Oops! Selection for the team has been closed.", Snackbar.LENGTH_LONG).show();
                        } else {
                            Snackbar.make(parentLayout, "You can select your team 12 hours before the match starts.", Snackbar.LENGTH_LONG).show();
                        }
                    }
                });
            }
        };
        match_recycler_view.setAdapter(mAdapter);
        getPoints();
        Log.e("testing.." , "id..:" +SharedPreferenceHandler.getInstance().getUserId(this));
        Log.e("testing.." , "name..:" +SharedPreferenceHandler.getInstance().getUserName(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                SharedPreferenceHandler.getInstance().deleteAllData(this);
                LoginManager.getInstance().logOut();
                AppUtils.getInstance().pageTransition(SelectTeamActivity.this, LoginActivity.class);
                return true;
            case R.id.score:
                AppUtils.getInstance().pageTransition(SelectTeamActivity.this, AllUsersActivity.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getPoints() {
        user_match_prediction_pair = new HashMap<>();
        match_winner_pair = new HashMap<>();
        userPoints = 0;
        firebaseRef.child("UserInput").child("908521382590752").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    user_match_prediction_pair.put(postSnapshot.getKey(), postSnapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());

            }
        });

        firebaseRef.child("Matches").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                userPoints = 0;
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    MatchInfo matchInfo = postSnapshot.getValue(MatchInfo.class);
                    match_winner_pair.put(postSnapshot.getKey().substring(2),matchInfo.getResult());
                }

                for (String match: user_match_prediction_pair.keySet()) {
                    if (user_match_prediction_pair.get(match).equals(match_winner_pair.get(match))) {
                        userPoints = userPoints + 5;
                    }

                }
                Log.e("UserPoints ", userPoints.toString());
                firebaseRef.child("Users").child("908521382590752").child("points").setValue(userPoints);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }
}
