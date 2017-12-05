package com.example.android.moranlee.justgo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.test.RenamingDelegatingContext;
import android.util.Log;

import com.example.android.moranlee.justgo.activity.datatype.Weight;
import com.example.android.moranlee.justgo.activity.sql.SQLiteInterface;
import com.example.android.moranlee.justgo.activity.sql_interaction.WeightRepo;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Integer.parseInt;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Jesse on 2017-12-02.
 */

public class WeightInteractionTest {



    Context mMockContext;

    @Before
    public void setUp() {
        mMockContext = new RenamingDelegatingContext(InstrumentationRegistry.getTargetContext(), "test_");
    }

    WeightRepo f1;

    WeightRepo f2;

    private SQLiteInterface sql;

    Weight f3;

    SQLiteDatabase db;

    final private int testID = 100;

    final private int testUserID= 1;

    final private String testDate = "2015-01-01";

    final private double testWeight = 20.0;

    Context context;

    @Test
    public void setup() throws Exception{
        assertNull(f1);
        assertNull(f2);
        f3 = new Weight();
        this.setData();
        assertNotNull(f3);

        sql = new SQLiteInterface(mMockContext);
        db = sql.getWritableDatabase();
        f1 = new WeightRepo(mMockContext);

        this.insertdata();

        ArrayList<HashMap<String, String>> weightList = this.getWeightByUserId(1);

        assertNotNull(weightList);

        String ListID = weightList.get(0).get("id");
        String ListUserID = weightList.get(0).get("user_id");
        String ListDate = weightList.get(0).get("date");
        String ListWeight = weightList.get(0).get("weight");
        assertEquals(parseInt(ListID),testID);
        //assertEquals(parseInt(testUserID),test_userID);
        assertEquals(parseInt(ListUserID),testUserID);
        assertEquals(ListDate,testDate);
        assertEquals(Double.parseDouble(ListWeight),testWeight,0);



        Log.d("myTag", "End of testing");
        db.close();
    }

    public void setData(){
        f3.setId(testID);
        f3.setUser_id(testUserID);
        f3.setDate(testDate);
        f3.setWeight(testWeight);

    }

    public void insertdata() { f1.insert(f3); }


    public ArrayList<HashMap<String, String>> getWeightByUserId(int userID)
    {
        SQLiteDatabase db = sql.getReadableDatabase();
        ArrayList<HashMap<String, String>> resultSet = new ArrayList<>();
//        Cursor cursor = db.query("food", null, "name like '%" + input_name + "%'",
//                null, null, null, null);
        String selectQuery =  "select * from weight where user_id = " + userID;
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> weight = new HashMap<String, String>();
                weight.put("id", cursor.getString(cursor.getColumnIndex("id")));
                weight.put("user_id", cursor.getString(cursor.getColumnIndex("user_id")));
                weight.put("date", cursor.getString(cursor.getColumnIndex("date")));
                weight.put("weight", cursor.getString(cursor.getColumnIndex("weight")));

                resultSet.add(weight);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return resultSet;
    }

}
