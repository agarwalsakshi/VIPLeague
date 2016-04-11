package com.guptas.android.model;

/**
 * Created by sakshiagarwal on 09/04/16.
 */
public class UserPoints {
    String user;
    Integer point;

    UserPoints()
    {}

    public UserPoints(String user, Integer point)
    {
        this.user = user;
        this.point = point;
    }

    public String getUser()
    {
        return user;
    }
    public Integer getPoint()
    {
        return point;
    }
}
