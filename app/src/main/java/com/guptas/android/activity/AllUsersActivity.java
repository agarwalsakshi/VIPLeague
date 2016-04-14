package com.guptas.android.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.guptas.android.R;
import com.guptas.android.datahelper.UserPointsViewHolder;
import com.guptas.android.model.Users;
import com.guptas.android.utils.ApplicationConstants;

import static com.guptas.android.R.layout.*;

public class AllUsersActivity extends AppCompatActivity {
    Firebase firebaseRef;
    RecyclerView recyclerView;
    FirebaseRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_all_users);

        Firebase.setAndroidContext(this);

        firebaseRef = new Firebase(ApplicationConstants.FIREBASSE_URL).child("Users");

        recyclerView = (RecyclerView) findViewById(R.id.allUsersRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new FirebaseRecyclerAdapter<Users, UserPointsViewHolder>(Users.class, R.layout.list_all_users, UserPointsViewHolder.class, firebaseRef) {
            @Override
            protected void populateViewHolder(UserPointsViewHolder userPointsViewHolder, Users users, int i) {
                if (users.getName() != null && users.getPoints() != null) {
                    userPointsViewHolder.name.setText(users.getName());
                    userPointsViewHolder.points.setText(users.getPoints().toString());
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
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
