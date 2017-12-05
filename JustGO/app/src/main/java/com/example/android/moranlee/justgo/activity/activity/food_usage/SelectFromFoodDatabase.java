package com.example.android.moranlee.justgo.activity.activity.food_usage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.moranlee.justgo.R;
import com.example.android.moranlee.justgo.activity.sql_interaction.FoodRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class SelectFromFoodDatabase extends AppCompatActivity
{

    FoodRepo getFoods;

    TextView show_from_database;

    LinkedList<String> meats = new LinkedList<String>();;

    String [] meat = new String[20];

    LinkedList<String> vegetables = new LinkedList<String>();

    String [] vegetable = new String[20];

    LinkedList<String> fruits = new LinkedList<String>();;

    String [] fruit = new String[20];

    LinkedList<String> grains = new LinkedList<String>();;

    String [] grain = new String[20];

    LinkedList<String> dairys = new LinkedList<String>();;

    String [] dairy = new String[20];

    LinkedList<String> fats = new LinkedList<String>();;

    String [] fat = new String[20];

    //For testing
    String[] m = {"1", "2"};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_from_food_database_activity);
        show_from_database = (TextView)findViewById(R.id.show_from_database);
        show_from_database.setMovementMethod(new ScrollingMovementMethod());
        //getFoods = new FoodRepo(this);
        //new FoodData(this);
        //String text = "";
        //show_from_database.setText(text);
    }

    public void some()
    {
        ArrayList defaults = getFoods.get_default_food_list();
        meats = new LinkedList<>();
        vegetables = new LinkedList<>();
        fruits = new LinkedList<>();
        grains = new LinkedList<>();
        fats = new LinkedList<>();
        dairys = new LinkedList<>();
        for (int i = 0; i < defaults.size(); i++) {
            HashMap<String, String> current = (HashMap<String, String>)defaults.get(i);
            System.out.println(current.toString());
            //Log.d(TAG, "onCreate() returned: " + current.toString());
            String category = current.get("category");
            if (category.equals(null)) {
                Toast.makeText(SelectFromFoodDatabase.this, "no thing find in map",
                               Toast.LENGTH_SHORT);
            }
            //Toast.makeText(this,category, Toast.LENGTH_SHORT).show();
            if (category.equals("1")) {
                meats.add(current.get("name"));
            }
            if (category.equals("2")) {
                fruits.add(current.get("name"));
            }
            if (category.equals("3")) {
                vegetables.add(current.get("name"));
            }
            if (category.equals("4")) {
                dairys.add(current.get("name"));
            }
            if (category.equals("5")) {
                grains.add(current.get("name"));
            }
            if (category.equals("6")) {
                fats.add(current.get("name"));
            }
        }

        meat = new String [meats.size()];
        for (int i = 0; i < meats.size(); i++) {
            meat[i] = meats.get(i);
        }
        fruit = new String[fruits.size()];
        for (int i = 0; i < fruits.size(); i++) {
            fruit[i] = fruits.get(i);
        }
        vegetable = new String [vegetables.size()];
        for (int i = 0; i < vegetables.size(); i++) {
            vegetable[i] = vegetables.get(i);
        }
        grain = new String[grains.size()];
        for (int i = 0; i < grains.size(); i++) {
            grain[i] = grains.get(i);
        }
        dairy = new String [dairys.size()];
        for (int i = 0; i < dairys.size(); i++) {
            dairy[i] = dairys.get(i);
        }
        fat = new String[fats.size()];
        for (int i = 0; i < fats.size(); i++) {
            fat[i] = fats.get(i);
        }


//        meat = (String [])meats.toArray();
//        fruit = (String [])fruits.toArray();
//        vegetable = (String [])vegetables.toArray();
//        grain = (String [])grains.toArray();
//        dairy = (String [])dairys.toArray();
//        fat = (String [])fats.toArray();

        //show_from_database.setText(text);
    }

}
