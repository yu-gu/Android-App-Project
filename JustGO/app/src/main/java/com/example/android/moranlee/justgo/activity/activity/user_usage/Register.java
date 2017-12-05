package com.example.android.moranlee.justgo.activity.activity.user_usage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.android.moranlee.justgo.R;
import com.example.android.moranlee.justgo.activity.GlobalVariables;
import com.example.android.moranlee.justgo.activity.activity.MainMenu;
import com.example.android.moranlee.justgo.activity.datatype.User;
import com.example.android.moranlee.justgo.activity.sql_interaction.UserRepo;

import java.util.ArrayList;

/**
 * Created by yugu on 2017-10-10.
 */

public class Register extends AppCompatActivity
{

    /*
    input field for name
     */
    EditText name;

    /*
    input field for password
    */
    EditText password;

    /*
    input field for height
    */
    EditText height;

    /*
    field to select gender
     */
    RadioGroup gender;

    /*
    field to select birthday
     */
    DatePicker birthday;

    /*
    commit change
     */
    Button submit;

    /*
    store input info
     */
    User user;

    /*
    sqlite interface
     */
    UserRepo user_repo;

    /**
     * initialize activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        // connect field with interface
        name = (EditText)findViewById(R.id.username_textedit);
        password = (EditText)findViewById(R.id.password_register_layout);
        height = (EditText) findViewById(R.id.height_register_layout);
        gender = (RadioGroup)findViewById(R.id.choose_gender);
        birthday = (DatePicker)findViewById(R.id.birthday_Picker);
        submit = (Button)findViewById(R.id.submit_user_info);
        submit.setOnClickListener(add_user());
    }

    /**
     * add input data to database
     * @return OnClickListener
     */
    private View.OnClickListener add_user ()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create user
                user = new User();
                user.setId(GlobalVariables.getG_CurrentMaxUserId());
                GlobalVariables.setG_CurrentMaxUserId(GlobalVariables.getG_CurrentMaxUserId() +
                                                      1);
                // getvalue from field
                user.setName(name.getText().toString());
                user.setHeight(Double.parseDouble(height.getText().toString()));
                user.setPassword(password.getText().toString());
                int selectedId = gender.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                if (radioButton.getText().toString().equals("male")) {
                    user.setGender("M");
                } else {
                    user.setGender("F");
                }
                // get birthday and store as a string
                int day = birthday.getDayOfMonth();
                int month = birthday.getMonth() + 1;
                int year = birthday.getYear();
                String Day;
                if (month < 10) {
                    Day = "0" + String.valueOf(day);
                } else {
                    Day = String.valueOf(day);
                }
                String Month;
                if (month < 10) {
                    Month = "0" + String.valueOf(month);
                } else {
                    Month = String.valueOf(month);
                }
                String Year = String.valueOf(year);
                user.setBirthday(Year + Month + Day);
                // insert to database
                user_repo = new UserRepo(get_self());
                ArrayList some = user_repo.get_user_list();
                user_repo.insert(user);
                Intent unit_intent = new Intent(get_self(), MainMenu.class);
                startActivity(unit_intent);
            }
        };
    }

    /**
     *
     * @return self because other function need
     */
    private Context get_self()
    {
        return Register.this;
    }

}
