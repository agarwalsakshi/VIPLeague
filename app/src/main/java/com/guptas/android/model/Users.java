package com.guptas.android.model;

/**
 * Created by sakshiagarwal on 09/04/16.
 */
public class Users {
    private String name;
    private Integer points;

    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    public Users() {
    }

    public Users(String name, Integer points)
    {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public Integer getPoints() {
        return points;
    }
}
