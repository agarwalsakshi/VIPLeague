package com.guptas.android.datahelper;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.guptas.android.R;
import com.guptas.android.model.Users;

/**
 * Created by pavitra on 14/4/16.
 */
public class UserPointsViewHolder extends RecyclerView.ViewHolder {

    public TextView name = null;
    public TextView points = null;


    public UserPointsViewHolder(View v) {
        super(v);

        name = (TextView) v.findViewById(R.id.userName);
        points = (TextView) v.findViewById(R.id.userPoints);
    }

}
