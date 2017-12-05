package com.example.android.moranlee.justgo.activity.activity.food_usage;

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

import java.util.ArrayList;

public class NormalExpandSearch extends AppCompatActivity
{

    private static final String TAG = "NormalExpandSearch";

    /*
    array lists to store search result from other activity
     */
    ArrayList<String> nameList;

    ArrayList<String> dataList;


    /*
    array store food type name
     */
    public static String[] general = {"Search Result"};

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
        nameList = getIntent().getStringArrayListExtra("name");
        dataList = getIntent().getStringArrayListExtra("data");

        specific = new String[1][nameList.size()];

        for (int i = 0; i < nameList.size(); i++) {
            specific[0][i] = nameList.get(i);
        }

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
                //Log.d(TAG, "groupPosition:" + groupPosition + ", id:" + id);
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
                Toast.makeText(NormalExpandSearch.this, specific[groupPosition][childPosition],
                               Toast.LENGTH_SHORT).show();
                Intent go_to_confirm = new Intent(getItSelf(), ConfirmFoodNutrient.class);
                go_to_confirm.putExtra("data", dataList.get(childPosition));

                go_to_confirm.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(go_to_confirm);
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
     * @return self becuse other function need
     */
    private Activity getItSelf()
    {
        return this;
    }
}
