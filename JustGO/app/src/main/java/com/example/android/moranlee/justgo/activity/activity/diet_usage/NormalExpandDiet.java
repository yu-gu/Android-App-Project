package com.example.android.moranlee.justgo.activity.activity.diet_usage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.android.moranlee.justgo.R;
import com.example.android.moranlee.justgo.activity.GlobalVariables;
import com.example.android.moranlee.justgo.activity.adapter.NormalExpandAdapter;
import com.example.android.moranlee.justgo.activity.adapter.OnGroupExpandedListener;
import com.example.android.moranlee.justgo.activity.sql_interaction.FoodRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by yul04 on 2017/10/17.
 */

public class NormalExpandDiet extends AppCompatActivity
{
    private static final String TAG = "ExpandDietActivity";

    /*
    SQLite interface
     */
    FoodRepo getFoods;

    /*
    LinkList to store data from database, array to store data for expand view usage
    */
    LinkedList<String> meats;

    String [] meat;

    LinkedList<String> vegetables;

    String [] vegetable;

    LinkedList<String> fruits;

    String [] fruit;

    LinkedList<String> grains;

    String [] grain;

    LinkedList<String> dairys;

    String [] dairy;

    LinkedList<String> fats;

    String [] fat;

    LinkedList<String> users;

    String [] user;

    LinkedList<String> datas;

    /*
    array store food type name
     */
    public static String[] general = {"meats", "fruits", "vegetables", "dairys", "grains", "fats", "users"};

    /*
    array store data specific food name from database
     */
    public static String[][] specific;

    /**
     * initialize activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand);
        // initialize sql interface
        getFoods = new FoodRepo(this);
        // get food item
        ArrayList defaults = getFoods.get_default_food_list();
        // initialize list
        meats = new LinkedList<>();
        vegetables = new LinkedList<>();
        fruits = new LinkedList<>();
        grains = new LinkedList<>();
        fats = new LinkedList<>();
        dairys = new LinkedList<>();
        users = new LinkedList<>();
        datas = new LinkedList<>();
        // get data from database
        for (int i = 0; i < defaults.size(); i++) {
            HashMap<String, String> current = (HashMap<String, String>)defaults.get(i);
            System.out.println(current.toString());
            // put data to array base on input type
            String category = current.get("category");
            if (category.equals(null)) {
                Toast.makeText(NormalExpandDiet.this, "no thing find in map",
                               Toast.LENGTH_SHORT);
            }
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
            if (category.equals(Integer.toString(GlobalVariables.getG_CurrentUserId()))) {
                users.add(current.get("name"));
            }
            datas.add(current.toString())
            ;
        }
        // transfer data to array because expand view only allow array as input
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
        user = new String[users.size()];
        for (int i = 0; i < users.size(); i++) {
            user[i] = users.get(i);
        }
        // initialize specific with arrays
        specific = new String[][] {meat, fruit, vegetable, dairy, fat, grain, user};
        // create expand view and initialize
        final ExpandableListView listView = (ExpandableListView) findViewById(
                                                R.id.expandable_list);
        final NormalExpandAdapter adapter = new NormalExpandAdapter(general, specific);
        adapter.setOnGroupExpandedListener(new OnGroupExpandedListener() {


            @Override

            public void onGroupExpanded(int groupPosition) {
                expandOnlyOne(listView, groupPosition, general.length);
            }
        });

        listView.setAdapter(adapter);
        //  set on group listener
        listView.setOnGroupClickListener(new
        ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                Log.d(TAG, "onGroupClick: groupPosition:" + groupPosition + ", id:" + id);
                // must return false
                return false;
            }
        });

        //  set child on child listener
        listView.setOnChildClickListener(new
        ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(NormalExpandDiet.this, specific[groupPosition][childPosition],
                               Toast.LENGTH_SHORT).show();
                Intent goToConfirm = new Intent(getItSelf(), ConfirmDietNutrient.class);
                int pos = 0;
                for (int i = 0; i < groupPosition; i++) {
                    pos += specific[i].length;
                }
                pos += childPosition;
                goToConfirm.putExtra("data", datas.get(pos));
                goToConfirm.putExtra("id", pos + 1);
                goToConfirm.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(goToConfirm);
                finish();
                return true;
            }
        });
    }

    //close other if one is expand
    private boolean expandOnlyOne(ExpandableListView view, int expandedPosition,
                                  int groupLength)
    {
        boolean result = true;
        for (int i = 0; i < groupLength; i++) {
            if (i != expandedPosition && view.isGroupExpanded(i)) {
                result &= view.collapseGroup(i);
            }
        }
        return result;
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
