package com.example.android.moranlee.justgo.activity.activity.exercise_usage;

/**
 * Created by yugu on 2017-10-05.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.android.moranlee.justgo.R;
import com.example.android.moranlee.justgo.activity.adapter.NormalExpandAdapter;
import com.example.android.moranlee.justgo.activity.adapter.OnGroupExpandedListener;
import com.example.android.moranlee.justgo.activity.sql_interaction.ExerciseRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Normal ExpandableListView, expand one child only
 */
public class NormalExpandExercise extends AppCompatActivity
{
    private static final String TAG = "NormalExpandFood";

    /*
    SQLite interface
    */
    ExerciseRepo exerciseRepo;

    /*
    LinkList to store data from database, array to store data for expand view usage
    */
    LinkedList<String> endurances;

    String [] endurance;

    LinkedList<String> strengths;

    String [] strength;

    LinkedList<String> balances;

    String [] balance;

    LinkedList<String> flexibilitys;

    String [] flexibility;

    LinkedList<String> datas;

    /*
    array store exercise type name
     */
    public static String[] general = {"endurance", "strength", "balance", "flexibility"};

    /*
    array store data specific exercise name from database
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
        exerciseRepo = new ExerciseRepo(this);
        // get exercise item
        ArrayList defaults = exerciseRepo.get_default_exercise_list();
        // initialize list
        endurances = new LinkedList<>();
        strengths = new LinkedList<>();
        balances = new LinkedList<>();
        flexibilitys = new LinkedList<>();
        datas = new LinkedList<>();
        // get data from database
        for (int i = 0; i < defaults.size(); i++) {
            HashMap<String, String> current = (HashMap<String, String>)defaults.get(i);
            System.out.println(current.toString());
            // put data to array base on input type
            String category = current.get("category");
            if (category.equals(null)) {
                Toast.makeText(NormalExpandExercise.this, "no thing find in map",
                               Toast.LENGTH_SHORT);
            }
            if (category.equals("0")) {
                endurances.add(current.get("name"));
            }
            if (category.equals("1")) {
                balances.add(current.get("name"));
            }
            if (category.equals("2")) {
                strengths.add(current.get("name"));
            }
            if (category.equals("3")) {
                flexibilitys.add(current.get("name"));
            }
            datas.add(current.toString());
        }
        // transfer data to array because expand view only allow array as input
        endurance = new String [endurances.size()];
        for (int i = 0; i < endurances.size(); i++) {
            endurance[i] = endurances.get(i);
        }
        balance = new String[balances.size()];
        for (int i = 0; i < balances.size(); i++) {
            balance[i] = balances.get(i);
        }
        strength = new String [strengths.size()];
        for (int i = 0; i < strengths.size(); i++) {
            strength[i] = strengths.get(i);
        }
        flexibility = new String[flexibilitys.size()];
        for (int i = 0; i < flexibilitys.size(); i++) {
            flexibility[i] = flexibilitys.get(i);
        }
        // initialize specific with arrays
        specific = new String[][] {endurance, balance, strength, flexibility};
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
                //Log.d(TAG, "onGroupClick: groupPosition:" + groupPosition + ", id:" + id);
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
                Toast.makeText(NormalExpandExercise.this,
                               specific[groupPosition][childPosition], Toast.LENGTH_SHORT).show();
                Intent goToConfirm = new Intent(getItSelf(), ConfirmExerciseData.class);
                int pos = 0;
                for (int i = 0; i < groupPosition; i++) {
                    pos += specific[i].length;
                }
                pos += childPosition;
                goToConfirm.putExtra("data", datas.get(pos));
                goToConfirm.putExtra("id", pos);
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
