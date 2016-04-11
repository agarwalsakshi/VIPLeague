package com.guptas.android.model;

/**
 * Created by sakshiagarwal on 08/04/16.
 */
public class MatchInfo {
    private String team1, team2, result, matchname;
    private Long timestamp;

    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    public MatchInfo() {
    }

    public MatchInfo(String matchname, String team1, String team2, Long timestamp, String result)
    {
        this.result = result;
        this.team1 = team1;
        this.team2 = team2;
        this.timestamp = timestamp;
        this.matchname = matchname;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getResult() {
        return result;
    }

    public String getMatchname() {
        return matchname;
    }

}
