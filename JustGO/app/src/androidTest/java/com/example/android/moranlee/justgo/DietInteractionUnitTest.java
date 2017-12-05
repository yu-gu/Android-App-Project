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
import com.example.android.moranlee.justgo.activity.sql.SQLiteInterface;
import com.example.android.moranlee.justgo.activity.sql_interaction.DietRepo;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Integer.parseInt;
import static org.junit.Assert.*;

/**
 * Created by yugu on 2017-11-23.
 */

//@RunWith(AndroidJUnit4.class)
public class DietInteractionUnitTest {


    Context mMockContext;

    @Before
    public void setUp() {
        mMockContext = new RenamingDelegatingContext(InstrumentationRegistry.getTargetContext(), "test_");
    }

    //@Parameter
    DietRepo f1;
    //@parameter
    DietRepo f2;

    private SQLiteInterface sql;

    Diet f3;

    SQLiteDatabase db;

    final private int testID = 100;

    final private int test_userID=0;

    final private String test_date = "2015-01-01";

    final private char test_meal_type = 'B';

    final private int test_food_id = 1;

    Context context;

    @Test
    public void setup() throws Exception{
        assertNull(f1);
        assertNull(f2);
        f3 = new Diet();
        this.setData();
        assertNotNull(f3);
        assertEquals(f3.getDate(),test_date);

        sql = new SQLiteInterface(mMockContext);
        db = sql.getWritableDatabase();
        f1 = new DietRepo(mMockContext);
        f2 = new DietRepo(context);

        this.insertdata();

        ArrayList<HashMap<String, String>> test_diet_list = this.get_diet_by_user_id(test_userID);

        assertNotNull(test_diet_list);

        String testUserID = test_diet_list.get(0).get("user_id");
        String testFoodID = test_diet_list.get(0).get("food_id");
        String testDate = test_diet_list.get(0).get("date");
        String testMealType = test_diet_list.get(0).get("meal_type");

        assertEquals(testMealType,Character.toString(test_meal_type));
        assertEquals(testDate,test_date);
        assertEquals(parseInt(testFoodID),test_food_id);
        assertEquals(parseInt(testUserID),test_userID);
        Log.d("myTag", "End of testing");
        db.close();
    }

    public void setData(){
        f3.setDate(test_date);
        f3.setMeal_type(test_meal_type);
        f3.setUser_id(test_userID);
        f3.setId(testID);
        f3.setFood_id(test_food_id);
    }

    public void insertdata() { f1.insert(f3); }


    public ArrayList<HashMap<String, String>> get_diet_by_user_id(int user_id)
    {
        SQLiteDatabase db = sql.getReadableDatabase();
        ArrayList<HashMap<String, String>> resultSet = new ArrayList<>();
        //Cursor cursor = db.query("food", null, "name like '%" + input_name + "%'",
        //        null, null, null, null);

        String selectQuery =  "select * from diet where user_id = " + user_id;
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> food = new HashMap<String, String>();
                food.put("id", cursor.getString(cursor.getColumnIndex("id")));
                food.put("food_id", cursor.getString(cursor.getColumnIndex("food_id")));
                food.put("user_id", cursor.getString(cursor.getColumnIndex("user_id")));
                food.put("date", cursor.getString(cursor.getColumnIndex("date")));
                food.put("meal_type", cursor.getString(cursor.getColumnIndex("meal_type")));

                resultSet.add(food);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return resultSet;
    }

}



