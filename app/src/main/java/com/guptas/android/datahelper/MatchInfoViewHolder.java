package com.guptas.android.datahelper;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.guptas.android.R;

/**
 * Created by sakshiagarwal on 14/04/16.
 */
public class MatchInfoViewHolder extends RecyclerView.ViewHolder {

    public TextView matchDate = null;
    public ImageView firstTeamFlag = null;
    public ImageView secondTeamFlag = null;

    public MatchInfoViewHolder(View v) {
        super(v);

        matchDate = (TextView) v.findViewById(R.id.match_date);
        firstTeamFlag = (ImageView) v.findViewById(R.id.first_team_flag);
        secondTeamFlag = (ImageView) v.findViewById(R.id.second_team_flag);
    }

}
