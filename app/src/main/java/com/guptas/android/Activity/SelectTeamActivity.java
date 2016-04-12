package com.guptas.android.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.guptas.android.R;
import com.guptas.android.datahelper.FirebaseListAdapter;
import com.guptas.android.datahelper.MatchDataHelper;
import com.guptas.android.model.MatchInfo;
import com.guptas.android.utils.AppUtils;
import com.guptas.android.utils.ApplicationConstants;
import com.guptas.android.utils.SharedPreferenceHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SelectTeamActivity extends AppCompatActivity {
    private ListView match_recycler_view;
    private SelectTeamAdapter adapter;
    private Firebase firebaseRef, firebaseUserRef;
    private LinearLayout parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_team);

        AppUtils.getInstance().showProgressDialog(this, "Loading..");
        parentLayout = (LinearLayout) findViewById(R.id.parentLayout);
        match_recycler_view = (ListView) findViewById(R.id.match_list);

        firebaseRef = new Firebase(ApplicationConstants.FIREBASSE_URL).child("Matches");

        firebaseUserRef = firebaseRef.getParent().child("UserInput").child(SharedPreferenceHandler.getInstance().getUserName(this));
        adapter = new SelectTeamAdapter(firebaseRef, SelectTeamActivity.this, R.layout.list_match);
        match_recycler_view.setAdapter(adapter);
        adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                match_recycler_view.setSelection(adapter.getCount() - 1);
            }
        });
//        updateValuesToFirebase();
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
                AppUtils.getInstance().pageTransition(SelectTeamActivity.this, LoginActivity.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void updateValuesToFirebase() {
        MatchInfo matchInfo = new MatchInfo("MumbaivsPune", "Mumbai", "Pune", (long) 1460212200, "");
        MatchInfo matchInfo2 = new MatchInfo("KolkatavsDelhi", "Kolkata", "Delhi", (long) 1460298600, "");
        MatchInfo matchInfo3 = new MatchInfo("PunjabvsGujrat", "Punjab", "Gujrat", (long) 1460385000, "");
        MatchInfo matchInfo4 = new MatchInfo("BangalorevsHyderabad", "Bangalore", "Hyderabad", (long) 1460471400, "");
        MatchInfo matchInfo5 = new MatchInfo("KolkatavsMumbai", "Kolkata", "Mumbai", (long) 1460557800, "");

        firebaseRef.push().setValue(matchInfo);
        firebaseRef.push().setValue(matchInfo2);
        firebaseRef.push().setValue(matchInfo3);
        firebaseRef.push().setValue(matchInfo4);
        firebaseRef.push().setValue(matchInfo5);
    }

    public class SelectTeamAdapter extends FirebaseListAdapter<MatchInfo> {
        private Activity mContext;

        public SelectTeamAdapter(Query ref, Activity activity, int layout) {
            super(ref, MatchInfo.class, layout, activity);
            mContext = activity;
        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        protected void populateView(View v, final MatchInfo model) {
            AppUtils.getInstance().dismissProgressDialog();
            TextView dateText = (TextView) v.findViewById(R.id.match_date);
            final ImageView firstFlag = (ImageView) v.findViewById(R.id.first_team_flag);
            final ImageView secondFlag = (ImageView) v.findViewById(R.id.second_team_flag);
            final Long Timestamp = model.getTimestamp();
            Date date = new Date((long) Timestamp * 1000);
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd");
            SimpleDateFormat stf = new SimpleDateFormat("hh a");
            String formattedDate = sdf.format(date);
            String formatTime = stf.format(date);
            dateText.setText(formattedDate + " , " + formatTime);

            firstFlag.setBackground(MatchDataHelper.getInstance().convertNameToLogo(model.getTeam1(), mContext));
            secondFlag.setBackground(MatchDataHelper.getInstance().convertNameToLogo(model.getTeam2(), mContext));
            firebaseUserRef.child(model.getMatchname()).addValueEventListener(new ValueEventListener() {
                  @Override
                  public void onDataChange(DataSnapshot snapshot) {
                      String team = (String) snapshot.getValue();
                      Log.e("SelectTeamActivity", "Team name..:" +team);
                  }
                  @Override
                  public void onCancelled(FirebaseError firebaseError) {
                  }
              }

            );

            firstFlag.setOnClickListener(new View.OnClickListener()

                                         {
                                             @Override
                                             public void onClick(View v) {
                 if ((Timestamp - 43200) < (System.currentTimeMillis() / 1000L) && (System.currentTimeMillis() / 1000L) < Timestamp) {
                     firebaseUserRef.child(model.getTeam1() + "vs" + model.getTeam2()).setValue(model.getTeam1());
                     Snackbar.make(parentLayout, "Your selection has been updated. You can change it anytime before the match starts.", Snackbar.LENGTH_LONG).show();
                 } else if ((System.currentTimeMillis() / 1000L) > Timestamp) {
                     Snackbar.make(parentLayout, "Oops! Selection for the team has been closed.", Snackbar.LENGTH_LONG).show();
                 } else {
                     Snackbar.make(parentLayout, "You can select your team 12 hours before the match starts.", Snackbar.LENGTH_LONG).show();
                 }

                                             }
                                         });
            secondFlag.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                  if ((Timestamp - 43200) < (System.currentTimeMillis() / 1000L) && (System.currentTimeMillis() / 1000L) < Timestamp) {
                      firebaseUserRef.child(model.getTeam1() + "vs" + model.getTeam2()).setValue(model.getTeam2());
                      Snackbar.make(parentLayout, "Your selection has been updated. You can change it anytime before the match starts.", Snackbar.LENGTH_LONG).show();
                  } else if ((System.currentTimeMillis() / 1000L) > Timestamp) {
                      Snackbar.make(parentLayout, "Oops! Selection for the team has been closed.", Snackbar.LENGTH_LONG).show();
                  } else {
                      Snackbar.make(parentLayout, "You can select your team 12 hours before the match starts.", Snackbar.LENGTH_LONG).show();
                  }
              }
                                          });

        }
    }
}
