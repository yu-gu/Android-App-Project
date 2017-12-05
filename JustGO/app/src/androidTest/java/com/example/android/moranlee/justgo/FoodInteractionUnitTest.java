package com.example.android.moranlee.justgo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.RenamingDelegatingContext;

import org.junit.Test;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.android.moranlee.justgo.activity.datatype.Diet;
import com.example.android.moranlee.justgo.activity.datatype.Food;
import com.example.android.moranlee.justgo.activity.sql.SQLiteInterface;
import com.example.android.moranlee.justgo.activity.sql_interaction.FoodRepo;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Integer.parseInt;
import static org.junit.Assert.*;

/**
 * Created by yugu on 2017-11-23.
 */

//@RunWith(AndroidJUnit4.class)
public class FoodInteractionUnitTest {


    Context mMockContext;

    @Before
    public void setUp() {
        mMockContext = new RenamingDelegatingContext(InstrumentationRegistry.getTargetContext(), "test_");
    }

    FoodRepo f1;

    FoodRepo f2;

    private SQLiteInterface sql;

    Food f3;

    SQLiteDatabase db;

    final private int testID = 100;

    final private int test_userID=0;

    final private double test_fat = 0.0;

    final private int test_category = 1;

    final private String test_name = "test";

    final private double test_cholesteral = 0.0;

    final private double test_calories = 0.0;

    final private double test_protein = 0.0;

    Context context;

    @Test
    public void setup() throws Exception{
        assertNull(f1);
        assertNull(f2);
        f3 = new Food();
        this.setData();
        assertNotNull(f3);

        sql = new SQLiteInterface(mMockContext);
        db = sql.getWritableDatabase();
        f1 = new FoodRepo(mMockContext);

        this.insertdata();

        ArrayList<HashMap<String, String>> test_food_list = this.get_food_by_user_id(test_userID);

        assertNotNull(test_food_list);

        String test_ID = test_food_list.get(0).get("id");
        String testUserID = test_food_list.get(0).get("user_id");
        String testFat = test_food_list.get(0).get("fat");
        String testCategory = test_food_list.get(0).get("category");
        String testName = test_food_list.get(0).get("name");
        String testCholesterol = test_food_list.get(0).get("cholesterol");
        String testCalories = test_food_list.get(0).get("calories");
        String testProtein = test_food_list.get(0).get("protein");

        assertEquals(testID,parseInt(test_ID));
        //assertEquals(parseInt(testUserID),test_userID);
        assertEquals(Double.parseDouble(testFat),test_fat,0);
        assertEquals(parseInt(testCategory),test_category);
        assertEquals(testName,test_name);
        assertEquals(Double.parseDouble(testCholesterol),test_cholesteral,0);
        assertEquals(Double.parseDouble(testCalories),(test_calories),0);
        assertEquals(Double.parseDouble(testProtein),(test_protein),0);


        Log.d("myTag", "End of testing");
        db.close();
    }

    public void setData(){
        f3.setId(testID);
        f3.setUser_id(test_userID);
        f3.setFat(test_fat);
        f3.setCategory(test_category);
        f3.setName(test_name);
        f3.setCholesterol(test_cholesteral);
        f3.setCalories(test_calories);
        f3.setProtein(test_protein);
    }

    public void insertdata() { f1.insert(f3); }


    public ArrayList<HashMap<String, String>> get_food_by_user_id(int user_id)
    {
        SQLiteDatabase db = sql.getReadableDatabase();
        ArrayList<HashMap<String, String>> resultSet = new ArrayList<>();
//        Cursor cursor = db.query("food", null, "name like '%" + input_name + "%'",
//                null, null, null, null);
        String selectQuery =  "select * from food where user_id = " + user_id;
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> food = new HashMap<String, String>();
                food.put("id", cursor.getString(cursor.getColumnIndex("id")));
                food.put("category", cursor.getString(cursor.getColumnIndex("category")));
                food.put("name", cursor.getString(cursor.getColumnIndex("name")));
                food.put("protein", cursor.getString(cursor.getColumnIndex("protein")));
                food.put("fat", cursor.getString(cursor.getColumnIndex("fat")));
                food.put("calories", cursor.getString(cursor.getColumnIndex("calories")));
                food.put("cholesterol", cursor.getString(
                        cursor.getColumnIndex("cholesterol")));
                resultSet.add(food);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return resultSet;
    }

}



