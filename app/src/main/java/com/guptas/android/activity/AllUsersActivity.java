package com.guptas.android.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.guptas.android.R;
import com.guptas.android.datahelper.UserPointsViewHolder;
import com.guptas.android.model.Users;
import com.guptas.android.utils.ApplicationConstants;
import com.guptas.android.utils.SharedPreferenceHandler;

import static com.guptas.android.R.layout.*;

public class AllUsersActivity extends AppCompatActivity {
    Firebase firebaseRef;
    RecyclerView recyclerView;
    FirebaseRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_all_users);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        firebaseRef = new Firebase(ApplicationConstants.FIREBASSE_URL).child("Users");
        firebaseRef.child(SharedPreferenceHandler.getInstance().getUserId(this)).child("points").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                    Log.e("testing.." , "snapshop..:" +snapshot);
                    TextView yourScore = (TextView) findViewById(R.id.user_score);
                    if(snapshot.getValue() != null)
                    yourScore.setText(snapshot.getValue().toString());
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.allUsersRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new FirebaseRecyclerAdapter<Users, UserPointsViewHolder>(Users.class, R.layout.list_all_users, UserPointsViewHolder.class, firebaseRef) {
            @Override
            protected void populateViewHolder(UserPointsViewHolder userPointsViewHolder, Users users, int i) {
                if (users.getName() != null){
                    userPointsViewHolder.name.setText(users.getName());
                    if(users.getPoints() != null)
                        userPointsViewHolder.points.setText(users.getPoints().toString());
                    else
                    userPointsViewHolder.points.setText("0");
                }
            }
        };
        recyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
