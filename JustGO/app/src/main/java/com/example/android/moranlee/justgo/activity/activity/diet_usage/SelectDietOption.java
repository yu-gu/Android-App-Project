package com.example.android.moranlee.justgo.activity.activity.diet_usage;

/**
 * Created by yugu on 2017-10-10.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android.moranlee.justgo.R;

public class SelectDietOption extends AppCompatActivity
{

    /*
    button go to select food activity
     */
    Button selectFromOldDiet;

    Button selectByName;

    /**
     * initialize activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_diet_option_activity);
        selectFromOldDiet = (Button)findViewById(R.id.select_from_old_food);
        selectByName = (Button)findViewById(R.id.select_search_food_by_name);
        selectFromOldDiet.setOnClickListener(select_from_old_food());
        selectByName.setOnClickListener(search_food_name());
    }

    /**
     *  go to select from form old food activity
     * @return
     */
    private View.OnClickListener select_from_old_food()
    {

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unitIntent = new Intent(getItSelf(), NormalExpandDiet.class);
                unitIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(unitIntent);
            }
        };
    }

    /**
     *  go to search food by it`s name
     * @return
     */
    private View.OnClickListener search_food_name()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unitIntent = new Intent(getItSelf(), SearchDietName.class);
                unitIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(unitIntent);
            }
        };
    }

    /**
     *
     * @return self because other function need
     */
    private Activity getItSelf()
    {
        return this;
    }

}
