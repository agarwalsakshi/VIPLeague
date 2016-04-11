package com.guptas.android.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.guptas.android.R;
import com.guptas.android.model.Users;
import com.guptas.android.utils.AppUtils;
import com.guptas.android.utils.ApplicationConstants;
import com.guptas.android.utils.SharedPreferenceHandler;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Firebase firebaseRef;
    private EditText userName, passWord;
    private RelativeLayout loginLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (SharedPreferenceHandler.getInstance().getUserName(this) == null)  {
            setContentView(R.layout.activity_login);
            loginLayout = (RelativeLayout) findViewById(R.id.lofinLayout);
            Button loginButton = (Button) findViewById(R.id.login_button);
            Button signupButton = (Button) findViewById(R.id.signup);
            loginButton.setOnClickListener(this);
            signupButton.setOnClickListener(this);
            userName = (EditText) findViewById(R.id.username);
            passWord = (EditText) findViewById(R.id.password);
            firebaseRef = new Firebase(ApplicationConstants.FIREBASSE_URL);
        }
        else {
            AppUtils.getInstance().pageTransition(this, SelectTeamActivity.class);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                checkUserLogin();
                break;
            case R.id.signup:
                signUpUser();
                break;
        }
    }

    private void checkUserLogin() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        firebaseRef.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (AppUtils.getInstance().isNetworkAvailable(LoginActivity.this)) {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        Users users = postSnapshot.getValue(Users.class);
                        String username = users.getUsername();
                        String password = users.getPassword();
                        Log.e("Username", "" + username);
                        Log.e("Password", "" + password);
                        if (userName.getText().toString().equals(username) && passWord.getText().toString().equals(password)) {
                            SharedPreferenceHandler.getInstance().clearUserName(LoginActivity.this);
                            SharedPreferenceHandler.getInstance().saveUserName(LoginActivity.this, username);
                            Log.e("Sharedpreferences", "Username.." + username);
                            AppUtils.getInstance().pageTransition(LoginActivity.this, SelectTeamActivity.class);
                        } else {
                            Snackbar.make(loginLayout, "Oops! Username-Password match not found.", Snackbar.LENGTH_LONG)
                                    .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                                    .show();
                        }
                    }
                } else {
                    Snackbar.make(loginLayout, "Check your internet connection", Snackbar.LENGTH_LONG)
                            .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                            .show();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    private void signUpUser() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        if (userName.getText().toString().equals("") || passWord.getText().toString().equals("")) {
            Snackbar.make(loginLayout, "Empty Field", Snackbar.LENGTH_LONG)
                    .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                    .show();
        } else {
            if (AppUtils.getInstance().isNetworkAvailable(this)) {

                Users users = new Users(userName.getText().toString(), passWord.getText().toString());
                firebaseRef.child("Users").push().setValue(users);
                SharedPreferenceHandler.getInstance().clearUserName(LoginActivity.this);
                SharedPreferenceHandler.getInstance().saveUserName(this, userName.getText().toString());
                Snackbar snackbar = Snackbar
                        .make(loginLayout, "Make a note of your username & password.", Snackbar.LENGTH_INDEFINITE)
                        .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                        .setAction("DONE", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AppUtils.getInstance().pageTransition(LoginActivity.this, SelectTeamActivity.class);
                            }
                        });
                snackbar.show();
            } else {
                Snackbar.make(loginLayout, "Check your internet connection", Snackbar.LENGTH_LONG)
                        .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                        .show();
            }
        }
    }
}
