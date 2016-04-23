package com.guptas.android.model;

import java.io.Serializable;

/**
 * Created by pavitra on 15/4/16.
 */
public class UserTeamPair implements Serializable{
    private String predictorName;
    private String predictedTeam;

    public UserTeamPair(String predictorName, String predictedTeam) {
        this.predictorName = predictorName;
        this.predictedTeam = predictedTeam;
    }

    public String getPredictorName() {
        return predictorName;
    }

    public void setPredictorName(String predictorName) {
        this.predictorName = predictorName;
    }

    public String getPredictedTeam() {
        return predictedTeam;
    }

    public void setPredictedTeam(String predictedTeam) {
        this.predictedTeam = predictedTeam;
    }
}
