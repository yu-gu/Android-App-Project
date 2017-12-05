package com.example.android.moranlee.justgo.activity.activity.food_usage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.moranlee.justgo.R;

import com.example.android.moranlee.justgo.activity.activity.MainMenu;
import com.example.android.moranlee.justgo.activity.datatype.Food;
import com.example.android.moranlee.justgo.activity.sql_interaction.FoodRepo;
import com.example.android.moranlee.justgo.activity.*;


public class AddNewFood extends AppCompatActivity
{

    /*
    input field for name
     */
    EditText name;

    /*
    input field for protein
    */
    EditText protein;

    /*
    input field for calorie
    */
    EditText calorie;

    /*
    input field for cholesterol
    */
    EditText cholesterol;

    /*
    input field for fat
    */
    EditText fat;

    /*
    submit new food
    */
    Button submit;

    /*
    store data as a food type
     */
    Food newFood;

    /*
    connection to the SQLite
     */
    FoodRepo foodRepo;

    /**
     *  initialize addnew food activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_diet_activity);
        // connect var to fields by id
        name = (EditText)findViewById(R.id.new_food_name);
        protein = (EditText) findViewById(R.id.new_food_protein);
        calorie = (EditText) findViewById(R.id.new_food_calories);
        cholesterol = (EditText)findViewById(R.id.new_food_cholesterol);
        fat = (EditText) findViewById(R.id.new_food_fat);
        submit = (Button) findViewById(R.id.submit_change);
        foodRepo = new FoodRepo(this);
        // onclick listener for button to submit change
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newFood = new Food();
                newFood.setId(GlobalVariables.getG_CurrentMaxFoodId() + 1);
                GlobalVariables.setG_CurrentMaxFoodId(GlobalVariables.getG_CurrentMaxFoodId() +
                                                      1);
                newFood.setUser_id(GlobalVariables.getG_CurrentUserId());
                newFood.setCalories(Double.parseDouble(calorie.getText().toString()));
                newFood.setCategory(7);
                newFood.setCholesterol(Double.parseDouble(cholesterol.getText().toString()));
                newFood.setProtein(Double.parseDouble(protein.getText().toString()));
                newFood.setFat(Double.parseDouble(fat.getText().toString()));
                newFood.setName(name.getText().toString());
                foodRepo.insert(newFood);
                Intent goBack = new Intent(getItSelf(), SelectFoodOption.class);
                goBack.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(goBack);
                finish();
            }
        });
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
