package com.example.android.moranlee.justgo.activity.activity.user_usage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.android.moranlee.justgo.R;
import com.example.android.moranlee.justgo.activity.activity.MainMenu;
import com.example.android.moranlee.justgo.activity.sql_interaction.UserRepo;

/**
 * Created by Jesse on 10/12/2017.
 */


public class EditProfile extends AppCompatActivity
{

    /*
    input field to get new user height
     */
    EditText height;

    /*
    input field to get new user weight(default)
     */
    EditText weight;

    /*
    group of button fro user to select gender
     */
    RadioGroup gender;

    /*
    check if user change their height
     */
    boolean height_change;

    /*
    check if user change their weight
     */
    boolean weight_change;

    /*
    collect input to determine next step
     */
    Button submit;

    /*
    go to change password field
     */
    Button passwordBtn;

    /*
    SQLite interface
     */
    UserRepo user_repo;

    /**
     * initialize activity
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        // connect field to interface
        height = (EditText)findViewById(R.id.input2);
        weight = (EditText)findViewById(R.id.input1);
        gender = (RadioGroup)findViewById(R.id.gender_change_group);
        submit = (Button)findViewById(R.id.button4);
        passwordBtn = (Button) findViewById(R.id.profile_change_password);
        // initialize interface
        user_repo = new UserRepo(this);
        passwordBtn.setOnClickListener(changePassword());
        submit.setOnClickListener(submit_change());
    }


    /**
     *  function to switch activity to change password
     * @return OnClickListener
     */
    private View.OnClickListener changePassword()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unit_intent = new Intent(getItSelf(), ChangePassword.class);

                startActivity(unit_intent);
            }
        };
    }

    /**
     *  function to update user data
     * @return OnClickListener
     */
    private View.OnClickListener submit_change()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_weight = weight.getText().toString();
                String new_height = height.getText().toString();
                // check if user enter new height/weigth
                if (new_height.length() == 0) {
                    height_change = false;
                } else {
                    height_change = true;
                }
                if (new_weight.length() == 0) {
                    weight_change = false;
                } else {
                    height_change = true;
                }
                // update field base on user input
                if (height_change) {
                    user_repo.update_height(Double.parseDouble(new_height));
                }
                if (weight_change) {
                    user_repo.update_weight(Double.parseDouble(new_weight));
                }
                RadioButton genders = (RadioButton)findViewById(
                                          gender.getCheckedRadioButtonId());
                user_repo.update_gender(genders.getText().toString());
                // go back to main menu
                Intent unit_intent = new Intent(getItSelf(), MainMenu.class);
                startActivity(unit_intent);
            }
        };
    }

    private Activity getItSelf()
    {
        return this;
    }
}


