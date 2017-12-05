package com.example.android.moranlee.justgo.activity.activity.food_usage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android.moranlee.justgo.R;

public class SelectFoodOption extends AppCompatActivity
{

    /*
    option to select food
     */
    Button select_from_old_food;

    Button add_new_food;

    Button search_name;

    /**
     *  initialize addnew food activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_food_opition_activity);
        //connect field to interface
        select_from_old_food = (Button)findViewById(R.id.select_from_old_food);
        select_from_old_food.setOnClickListener(select_from_old_food());
        add_new_food = (Button)findViewById(R.id.select_create_new_food);
        add_new_food.setOnClickListener(add_new_food());
        search_name = (Button)findViewById(R.id.select_search_food_name);
        search_name.setOnClickListener(search_food_name());
    }

    /**
     *  go to select food by category
     * @return
     */
    private View.OnClickListener select_from_old_food()
    {
        //    new FoodData(this);
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unit_intent = new Intent(getItSelf(), NormalExpandFood.class);
                unit_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(unit_intent);
            }
        };
    }

    /**
     * go to add new food
     * @return
     */
    private View.OnClickListener add_new_food()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unit_intent = new Intent(getItSelf(), AddNewFood.class);
                unit_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(unit_intent);
            }
        };
    }

    /**
     *   go to search food by name
     * @return
     */
    private View.OnClickListener search_food_name()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unit_intent = new Intent(getItSelf(), SearchFoodName.class);
                unit_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(unit_intent);
            }
        };
    }

    /**
     *
     * @return self becuse other function need
     */
    private Activity getItSelf()
    {
        return this;
    }

}
