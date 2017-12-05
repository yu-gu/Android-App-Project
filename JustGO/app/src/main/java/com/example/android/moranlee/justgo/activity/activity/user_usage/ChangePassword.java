package com.example.android.moranlee.justgo.activity.activity.user_usage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.moranlee.justgo.R;
import com.example.android.moranlee.justgo.activity.sql_interaction.UserRepo;


/*
* Created by Jesse on 10/12/2017.
*/


public class ChangePassword extends AppCompatActivity
{
    EditText passwordField;
    EditText confirmPasswordField;
    Button confirmChange;
    UserRepo userInfo;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        passwordField = (EditText) findViewById(R.id.password_field);
        confirmPasswordField = (EditText)findViewById(R.id.password_confirm_field);
        confirmChange = (Button)findViewById(R.id.password_submit);
        userInfo = new UserRepo(this);
        confirmChange.setOnClickListener(SubmitPasswordChange());

    }

    private View.OnClickListener SubmitPasswordChange()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passwordString = passwordField.getText().toString();
                String confirmPasswordString = confirmPasswordField.getText().toString();

                Log.d("test", passwordString);
                Log.d("test2", confirmPasswordString);


                if (passwordString.equals(confirmPasswordString)
                        && (!passwordString.equals("                ") )
                        && (!passwordString.isEmpty()) ) {
                    userInfo.update_password(passwordString);
                    Toast successToast = Toast.makeText(ChangePassword.this, "Password Changed!",
                                                        Toast.LENGTH_SHORT);
                    successToast.show();
                    Intent unit_intent = new Intent(getItSelf(), EditProfile.class);
                    startActivity(unit_intent);

                } else {
                    Toast errorToast = Toast.makeText(ChangePassword.this,
                                                      "Passwords do not match or are invalid!", Toast.LENGTH_SHORT);
                    errorToast.show();
                }
                //passwordString = "                ";
                //confirmPasswordString = "                ";

            }
        };
    }

    private Activity getItSelf()
    {
        return this;
    }
}
