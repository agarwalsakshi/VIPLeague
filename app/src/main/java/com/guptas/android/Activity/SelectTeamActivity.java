package com.guptas.android.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
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
import java.util.List;

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
        adapter = new SelectTeamAdapter(firebaseRef, SelectTeamActivity.this, R.layout.list_match);
        match_recycler_view.setAdapter(adapter);
        adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                match_recycler_view.setSelection(adapter.getCount() - 1);
            }
        });
        updateValuesToFirebase();
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

    public void updateValuesToFirebase() {
        MatchInfo matchInfo1 = new MatchInfo("MI", "RPS", (long) 1460212200, "RPS");
        firebaseRef.child("MIvsRPS").setValue(matchInfo1);
        MatchInfo matchInfo2 = new MatchInfo("KKR", "DD", (long) 1460298600, "KKR");
        firebaseRef.child("KKRvsDD").setValue(matchInfo2);
        MatchInfo matchInfo3 = new MatchInfo("KXIP", "GL", (long) 1460385000, "GL");
        firebaseRef.child("KXIPvsGL").setValue(matchInfo3);
        MatchInfo matchInfo4 = new MatchInfo("RCB", "SRH", (long) 1460471400, "RCB");
        firebaseRef.child("RCBvsSRH").setValue(matchInfo4);
        MatchInfo matchInfo5 = new MatchInfo("KKR", "MI", (long) 1460557800, "");
        firebaseRef.child("KKRvsMI").setValue(matchInfo5);
        MatchInfo matchInfo7 = new MatchInfo("GL", "RPS", (long) 1460644200, "");
        firebaseRef.child("GLvsRPS").setValue(matchInfo7);
        MatchInfo matchInfo6 = new MatchInfo("DD", "KXIP", (long) 1460730600, "");
        firebaseRef.child("DDvsKXIP").setValue(matchInfo6);
        MatchInfo matchInfo8 = new MatchInfo("SRH", "KKR", (long) 1460802600, "");
        firebaseRef.child("SRHvsKKR").setValue(matchInfo8);
        MatchInfo matchInfo9 = new MatchInfo("MI", "GL", (long) 1460817000, "");
        firebaseRef.child("MIvsGL").setValue(matchInfo9);
        MatchInfo matchInfo10 = new MatchInfo("KXIP", "RPS", (long) 1460889000, "");
        firebaseRef.child("KXIPvsRPS").setValue(matchInfo10);
        MatchInfo matchInfo11 = new MatchInfo("RCB", "DD", (long) 1460903400, "");
        firebaseRef.child("RCBvsDD").setValue(matchInfo11);
        MatchInfo matchInfo12 = new MatchInfo("SRH", "MI", (long) 1460989800, "");
        firebaseRef.child("SRHvsMI").setValue(matchInfo12);
        MatchInfo matchInfo13 = new MatchInfo("KXIP", "KKR", (long) 1461076200, "");
        firebaseRef.child("KXIPvsKKR").setValue(matchInfo13);
        MatchInfo matchInfo14 = new MatchInfo("MI", "RCB", (long) 1461162600, "");
        firebaseRef.child("MIvsRCB").setValue(matchInfo14);
        MatchInfo matchInfo15 = new MatchInfo("GL", "SRH", (long) 1461249000, "");
        firebaseRef.child("GLvsSRH").setValue(matchInfo15);
        MatchInfo matchInfo16 = new MatchInfo("RPS", "RCB", (long) 1461335400, "");
        firebaseRef.child("RPSvsRCB").setValue(matchInfo16);
        MatchInfo matchInfo17 = new MatchInfo("DD", "MI", (long) 1461407400, "");
        firebaseRef.child("DDvsMI").setValue(matchInfo17);
        MatchInfo matchInfo18 = new MatchInfo("SRH", "KXIP", (long) 1461421800, "");
        firebaseRef.child("SRHvsKXIP").setValue(matchInfo18);
        MatchInfo matchInfo19 = new MatchInfo("GL", "RCB", (long) 1461493800, "");
        firebaseRef.child("GLvsRCB").setValue(matchInfo19);
        MatchInfo matchInfo20 = new MatchInfo("RPS", "KKR", (long) 1461508200, "");
        firebaseRef.child("RPSvsKKR").setValue(matchInfo20);
        MatchInfo matchInfo21 = new MatchInfo("KXIP", "MI", (long) 1461594600, "");
        firebaseRef.child("KXIPvsMI").setValue(matchInfo21);
        MatchInfo matchInfo22 = new MatchInfo("SRH", "RPS", (long) 1461681000, "");
        firebaseRef.child("SRHvsRPS").setValue(matchInfo22);
        MatchInfo matchInfo23 = new MatchInfo("DD", "GL", (long) 1461767400, "");
        firebaseRef.child("DDvsGL").setValue(matchInfo23);
        MatchInfo matchInfo24 = new MatchInfo("MI", "KKR", (long) 1461853800, "");
        firebaseRef.child("MIvsKKR").setValue(matchInfo24);
        MatchInfo matchInfo25 = new MatchInfo("RPS", "GL", (long) 1461940200, "");
        firebaseRef.child("RPSvsGL").setValue(matchInfo25);
        MatchInfo matchInfo26 = new MatchInfo("DD", "KKR", (long) 1462012200, "");
        firebaseRef.child("DDvsKKR").setValue(matchInfo26);
        MatchInfo matchInfo27 = new MatchInfo("SRH", "RCB", (long) 1462026600, "");
        firebaseRef.child("SRHvsRCB").setValue(matchInfo27);
        MatchInfo matchInfo28 = new MatchInfo("GL", "KXIP", (long) 1462098600, "");
        firebaseRef.child("GLvsKXIP").setValue(matchInfo28);
        MatchInfo matchInfo29 = new MatchInfo("RPS", "MI", (long) 1462113000, "");
        firebaseRef.child("RPSvsMI").setValue(matchInfo29);
        MatchInfo matchInfo30 = new MatchInfo("RCB", "KKR", (long) 1462199400, "");
        firebaseRef.child("RCBvsKKR").setValue(matchInfo30);
        MatchInfo matchInfo31 = new MatchInfo("GL", "DD", (long) 1462285800, "");
        firebaseRef.child("GLvsDD").setValue(matchInfo31);
        MatchInfo matchInfo32 = new MatchInfo("KKR", "KXIP", (long) 1462372200, "");
        firebaseRef.child("KKRvsKXIP").setValue(matchInfo32);
        MatchInfo matchInfo33 = new MatchInfo("DD", "RPS", (long) 1462458600, "");
        firebaseRef.child("DDvsRPS").setValue(matchInfo33);
        MatchInfo matchInfo34 = new MatchInfo("SRH", "GL", (long) 1462545000, "");
        firebaseRef.child("SRHvsGL").setValue(matchInfo34);
        MatchInfo matchInfo35 = new MatchInfo("RCB", "RPS", (long) 1462617000, "");
        firebaseRef.child("RCBvsRPS").setValue(matchInfo35);
        MatchInfo matchInfo36 = new MatchInfo("KXIP", "DD", (long) 1462631400, "");
        firebaseRef.child("KXIPvsDD").setValue(matchInfo36);
        MatchInfo matchInfo37 = new MatchInfo("MI", "SRH", (long) 1462703400, "");
        firebaseRef.child("MIvsSRH").setValue(matchInfo37);
        MatchInfo matchInfo38 = new MatchInfo("KKR", "GL", (long) 1462717800, "");
        firebaseRef.child("KKRvsGL").setValue(matchInfo38);
        MatchInfo matchInfo39 = new MatchInfo("KXIP", "RCB", (long) 1462804200, "");
        firebaseRef.child("KXIPvsRCB").setValue(matchInfo39);
        MatchInfo matchInfo40 = new MatchInfo("RPS", "SRH", (long) 1462890600, "");
        firebaseRef.child("RPSvsSRH").setValue(matchInfo40);
        MatchInfo matchInfo41 = new MatchInfo("RCB", "MI", (long) 1462977000, "");
        firebaseRef.child("RCBvsMI").setValue(matchInfo41);
        MatchInfo matchInfo42 = new MatchInfo("SRH", "DD", (long) 1463063400, "");
        firebaseRef.child("SRHvsDD").setValue(matchInfo42);
        MatchInfo matchInfo43 = new MatchInfo("MI", "KXIP", (long) 1463149800, "");
        firebaseRef.child("MIvsKXIP").setValue(matchInfo43);
        MatchInfo matchInfo44 = new MatchInfo("RCB", "GL", (long) 1463221800, "");
        firebaseRef.child("RCBvsGL").setValue(matchInfo44);
        MatchInfo matchInfo45 = new MatchInfo("KKR", "RPS", (long) 1463236200, "");
        firebaseRef.child("KKRvsRPS").setValue(matchInfo45);
        MatchInfo matchInfo46 = new MatchInfo("MI", "DD", (long) 1463308200, "");
        firebaseRef.child("MIvsDD").setValue(matchInfo46);
        MatchInfo matchInfo47 = new MatchInfo("KXIP", "SRH", (long) 1463322600, "");
        firebaseRef.child("KXIPvsSRH").setValue(matchInfo47);
        MatchInfo matchInfo48 = new MatchInfo("KKR", "RCB", (long) 1463409000, "");
        firebaseRef.child("KKRvsRCB").setValue(matchInfo48);
        MatchInfo matchInfo49 = new MatchInfo("RPS", "DD", (long) 1463495400, "");
        firebaseRef.child("RPSvsDD").setValue(matchInfo49);
        MatchInfo matchInfo50 = new MatchInfo("RCB", "KXIP", (long) 1463581800, "");
        firebaseRef.child("RCBvsKXIP").setValue(matchInfo50);
        MatchInfo matchInfo51 = new MatchInfo("GL", "KKR", (long) 1463668200, "");
        firebaseRef.child("GLvsKKR").setValue(matchInfo51);
        MatchInfo matchInfo52 = new MatchInfo("DD", "SRH", (long) 1463754600, "");
        firebaseRef.child("DDvsSRH").setValue(matchInfo52);
        MatchInfo matchInfo53 = new MatchInfo("RPS", "KXIP", (long) 1463826600, "");
        firebaseRef.child("RPSvsKXIP").setValue(matchInfo53);
        MatchInfo matchInfo54 = new MatchInfo("GL", "MI", (long) 1463841000, "");
        firebaseRef.child("GLvsMI").setValue(matchInfo54);
        MatchInfo matchInfo55 = new MatchInfo("KKR", "SRH", (long) 1463913000, "");
        firebaseRef.child("KKRvsSRH").setValue(matchInfo55);
        MatchInfo matchInfo56 = new MatchInfo("DD", "RCB", (long) 1463927400, "");
        firebaseRef.child("DDvsRCB").setValue(matchInfo56);
        MatchInfo matchInfo57 = new MatchInfo("FPT", "SPT", (long) 1464100200, "");
        firebaseRef.child("FPTvsSPT").setValue(matchInfo57);
        MatchInfo matchInfo58 = new MatchInfo("TPT", "FPT", (long) 1464186600, "");
        firebaseRef.child("TPTvsFPT").setValue(matchInfo58);
        MatchInfo matchInfo59 = new MatchInfo("LQ1", "WE", (long) 1464359400, "");
        firebaseRef.child("LQ1vsWE").setValue(matchInfo59);
        MatchInfo matchInfo60 = new MatchInfo("Q1", "Q2", (long) 1464532200, "");
        firebaseRef.child("Q1vsQ2").setValue(matchInfo60);
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
