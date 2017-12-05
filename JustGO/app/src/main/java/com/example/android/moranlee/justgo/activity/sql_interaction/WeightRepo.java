package com.example.android.moranlee.justgo.activity.sql_interaction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.moranlee.justgo.activity.GlobalVariables;
import com.example.android.moranlee.justgo.activity.datatype.Weight;
import com.example.android.moranlee.justgo.activity.sql.SQLiteInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by yul04 on 2017/10/13.
 */

public class WeightRepo
{
    private SQLiteInterface sql;

    public WeightRepo(Context context)
    {
        sql = new SQLiteInterface(context);
    }

    public int insert(Weight weight)
    {
        SQLiteDatabase db = sql.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", weight.getId());
        values.put("user_id", weight.getUser_id());
        values.put("date", weight.getDate());
        values.put("weight", weight.getWeight());
        long weight_id = db.insert("weight", null, values);
        db.close();
        return (int) weight_id;
    }

    public ArrayList<HashMap<String, String>> get_all_weight(int user_id)
    {
        SQLiteDatabase db = sql.getReadableDatabase();
        String selectQuery = "select * from weight where user_id = " + user_id;
        ArrayList<HashMap<String, String>> exList = new ArrayList<>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            HashMap<String, String> weight = new HashMap<>();
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String value = cursor.getString(cursor.getColumnIndex("weight"));
            weight.put("date", date);
            weight.put("weight", value);
            exList.add(weight);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return exList;
    }

    public Double get_last_weight(int user_id)
    {
        SQLiteDatabase db = sql.getReadableDatabase();
        String selectQuery = "SELECT * FROM weight where user_id = " + user_id +
                             " ORDER BY id DESC LIMIT 1;";
        ArrayList<HashMap<String, String>> exList = new ArrayList<>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Double current_weight = 68.0;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String value = cursor.getString(cursor.getColumnIndex("weight"));
            current_weight = Double.parseDouble(value);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return current_weight;
    }

    /**
     *  return current date as a string for usage when insert diet
     * @return date string contain current date
     */
    public String current_date()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        return date;
    }

    public Weight createNewWeight(double value)
    {
        Weight weight = new Weight();
        weight.setId(GlobalVariables.getAndSetCurrentMaxWeightId());
        weight.setUser_id(GlobalVariables.getG_CurrentUserId());
        weight.setDate(current_date());
        weight.setWeight(value);
        return weight;
    }

}
