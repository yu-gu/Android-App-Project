package com.example.android.moranlee.justgo.activity.activity.user_usage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.moranlee.justgo.R;
import com.example.android.moranlee.justgo.activity.GlobalVariables;
import com.example.android.moranlee.justgo.activity.activity.MainMenu;
import com.example.android.moranlee.justgo.activity.data.ExerciseData;
import com.example.android.moranlee.justgo.activity.data.FoodData;
import com.example.android.moranlee.justgo.activity.sql_interaction.UserRepo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity
{

    /*
    button to check user input and allow login
     */
    Button login;

    /*
    go to register a new account
     */
    Button register;

    /*
    field store username user enter
     */
    String username;

    /*
    field store password user enter
     */
    String password;

    /*
    SQLite interface
     */
    UserRepo current_user;

    /**
     * initialize activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        // reinstall databse everytime run, do not need when finish but useful when testing
        this.deleteDatabase("JustGo");
        // connect interface with field, initialize sql interface
        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(login());
        register = (Button)findViewById(R.id.signup);
        register.setOnClickListener(signup());
        current_user = new UserRepo(this);
        // delete past user each time reinstall, not need when finish
        int past_user = current_user.get_user_num();
        for (int i = 1; i <= past_user; i++) {
            current_user.delete_by_id(i);
        }
        if (GlobalVariables.getG_CurrentMaxUserId() <= 0) {
            GlobalVariables.setG_CurrentMaxUserId(1);
        }
        if (GlobalVariables.getCurrent_max_diet_id() <= 0) {
            GlobalVariables.setCurrent_max_diet_id(0);
        }
        if (GlobalVariables.getCurrent_max_exercise_daily_id() <= 0) {
            GlobalVariables.setCurrent_max_exercise_daily_id(0);
        }
        if (GlobalVariables.getCurrent_max_exercise_id() <= 0) {
            GlobalVariables.setCurrent_max_exercise_id(12);
        }
        new FoodData(this);
        new ExerciseData(this);
    }

    /**
     * check if should allow user login
     * @return OnClickListener
     */
    private View.OnClickListener login()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // initialize input data pattern, avoid illegal input
                Pattern input_type = Pattern.compile("^[A-Za-z]*$");
                username = ((TextView)findViewById(R.id.username)).getText().toString();
                password = ((TextView)findViewById(R.id.password)).getText().toString();
                Matcher username_matcher = input_type.matcher(username);
                Matcher password_matcher = input_type.matcher(password);
                // check user do input somthing in both field
                if (username.length() <= 0 || password.length() <= 0) {
                    Toast.makeText(getApplicationContext(),
                                   "You must enter the user and the password",
                                   Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    // check if user input if legal
                    if (!username_matcher.matches() || !password_matcher.matches()) {
                        Toast.makeText(getApplicationContext(), "Invalid input of username / password",
                                       Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //check input with data from database to confirm user exist and legal
                    int id = current_user.check_user_login(username, password);
                    // go to main activity
                    if (id >= 0) {
                        GlobalVariables.setG_CurrentUserId(id);
                        Intent unit_intent = new Intent(getItSelf(), MainMenu.class);
                        unit_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(unit_intent);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                       "Invalid combination of user name and password",
                                       Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        };
    }

    /**
     *  go to register activity
     * @return
     */
    private View.OnClickListener signup()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unit_intent = new Intent(getItSelf(), Register.class);
                startActivity(unit_intent);
            }
        };
    }

    /**
     *
     * @return self for some function need
     */
    private Activity getItSelf()
    {
        return this;
    }

}
