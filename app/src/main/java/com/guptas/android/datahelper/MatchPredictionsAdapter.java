package com.guptas.android.datahelper;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guptas.android.R;
import com.guptas.android.model.UserTeamPair;

import java.util.List;

/**
 * Created by pavitra on 15/4/16.
 */
public class MatchPredictionsAdapter extends RecyclerView.Adapter<MatchPredictionsAdapter.MyViewHolder> {

    public List<UserTeamPair> userTeamPairs;

    public MatchPredictionsAdapter(List<UserTeamPair> userTeamPairs) {
        Log.e("UserTeamPairs ----->", userTeamPairs.toString());
        this.userTeamPairs = userTeamPairs;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_match_predictions, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.predictorName.setText(userTeamPairs.get(position).getPredictorName());
        holder.predictedTeam.setText(userTeamPairs.get(position).getPredictedTeam());
    }

    @Override
    public int getItemCount() {
        return userTeamPairs.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView predictorName;
        public TextView predictedTeam;

        public MyViewHolder(View view) {
            super(view);
            predictedTeam = (TextView) view.findViewById(R.id.predicted_team);
            predictorName = (TextView) view.findViewById(R.id.predictor_name);
        }
    }

}
