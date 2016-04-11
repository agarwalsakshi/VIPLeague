package com.guptas.android.model;

/**
 * Created by sakshiagarwal on 09/04/16.
 */
public class Users {
    private String username, password;

    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    public Users() {
    }

    public Users(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
