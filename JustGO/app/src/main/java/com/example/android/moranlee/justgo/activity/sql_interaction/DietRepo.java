package com.example.android.moranlee.justgo.activity.sql_interaction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.moranlee.justgo.activity.GlobalVariables;
import com.example.android.moranlee.justgo.activity.datatype.Diet;
import com.example.android.moranlee.justgo.activity.sql.SQLiteInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by yul04 on 2017/10/10.
 */

public class DietRepo
{

    /**
     *  sql interface to interact with database
     */
    private SQLiteInterface sql;

    /**
     *  constructor
     * @param context context hold the database
     */
    public DietRepo(Context context)
    {
        sql = new SQLiteInterface(context);
    }

    /**
     *  insert a diet type data to database
     * @param diet diet data contain all information about diet
     * @return diet_id represent if the data is insert successfully
     */
    public int insert(Diet diet)
    {
        SQLiteDatabase db = sql.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", diet.getId());
        values.put("user_id", diet.getUser_id());
        values.put("food_id", diet.getFood_id());
        values.put("date", diet.getDate());
        values.put("meal_type", diet.getMeal_type().toString());
        long diet_Id = db.insert("diet", null, values);
        db.close();
        return (int) diet_Id;
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


    public Diet create_diet(int food_id)
    {
        Diet diet = new Diet();
        diet.setId(GlobalVariables.getCurrent_max_diet_id());
        GlobalVariables.setCurrent_max_diet_id(GlobalVariables.getCurrent_max_diet_id()
                                               + 1);
        diet.setUser_id(GlobalVariables.getG_CurrentUserId());
        diet.setDate(current_date());
        diet.setFood_id(food_id);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String current_time = sdf.format(cal.getTime());
        if (Integer.parseInt(current_time.substring(0, 2)) < 10) {
            diet.setMeal_type('B');
        } else if (Integer.parseInt(current_time.substring(0, 2)) > 17) {
            diet.setMeal_type('S');
        } else {
            diet.setMeal_type('L');
        }
        return diet;
    }

    /**
     * Calculate total calories consumed by a given user
     *
     * @return total_calories
     */
    public int totalCalorieIntake()
    {
        SQLiteDatabase db = sql.getReadableDatabase();
        String selectQuery = "select * from diet where date = '" + current_date() +
                             "'";
        String selectCalorieQuery = null;
        int food_id = 0;
        int calories = 0;
        int totalCalories = 0;

        Cursor dietcursor = db.rawQuery(selectQuery, null);
        if (dietcursor.moveToFirst()) {
            do {
                food_id = Integer.parseInt(dietcursor.getString(
                                               dietcursor.getColumnIndex("food_id")));
                selectCalorieQuery = "select * from food where id = " + food_id;
                Cursor foodcursor = db.rawQuery(selectCalorieQuery, null);
                if (foodcursor.moveToFirst())
                    do {
                        calories = Integer.parseInt(foodcursor.getString(
                                                        foodcursor.getColumnIndex("calories")));
                        totalCalories += calories;
                    } while (foodcursor.moveToNext());
                foodcursor.close();
            } while (dietcursor.moveToNext());

        }
        dietcursor.close();
        db.close();
        return totalCalories;
    }

    /**
     * Calculate total calories consumed by a given user
     *
     * @return total_calories
     */
    public int totalProteinIntake()
    {
        SQLiteDatabase db = sql.getReadableDatabase();
        String selectQuery = "select * from diet where date = '" + current_date() +
                             "'";
        String selectCalorieQuery = null;
        int food_id = 0;
        int protein = 0;
        int totalProtein = 0;

        Cursor dietCursor = db.rawQuery(selectQuery, null);
        if (dietCursor.moveToFirst()) {
            do {
                food_id = Integer.parseInt(dietCursor.getString(
                                               dietCursor.getColumnIndex("food_id")));
                selectCalorieQuery = "select * from food where id = " + food_id;
                Cursor foodCursor = db.rawQuery(selectCalorieQuery, null);
                if (foodCursor.moveToFirst())
                    do {
                        protein = (int)Double.parseDouble(foodCursor.getString(
                                                              foodCursor.getColumnIndex("protein")));
                        totalProtein += protein;
                    } while (foodCursor.moveToNext());
                foodCursor.close();
            } while (dietCursor.moveToNext());

        }
        dietCursor.close();
        db.close();
        return totalProtein;
    }

    /**
     * Calculate total calories consumed by a given user
     *
     * @return total_calories
     */
    public int totalCholesterolIntake()
    {
        SQLiteDatabase db = sql.getReadableDatabase();
        String selectQuery = "select * from diet where date = '" + current_date() +
                             "'";
        String selectCalorieQuery = null;
        int food_id = 0;
        int cholesterol = 0;
        int totalCholesterol = 0;

        Cursor dietCursor = db.rawQuery(selectQuery, null);
        if (dietCursor.moveToFirst()) {
            do {
                food_id = Integer.parseInt(dietCursor.getString(
                                               dietCursor.getColumnIndex("food_id")));
                selectCalorieQuery = "select * from food where id = " + food_id;
                Cursor foodCursor = db.rawQuery(selectCalorieQuery, null);
                if (foodCursor.moveToFirst())
                    do {
                        cholesterol = (int)Double.parseDouble(foodCursor.getString(
                                foodCursor.getColumnIndex("cholesterol")));
                        totalCholesterol += cholesterol;
                    } while (foodCursor.moveToNext());
                foodCursor.close();
            } while (dietCursor.moveToNext());

        }
        dietCursor.close();
        db.close();
        return totalCholesterol;
    }

    /**
     *  get a list of all data and it`s information
     * @return foodList list contain all food type info
     */
    public ArrayList<HashMap<String, String>>  get_today_food_list()
    {
        int food_id;
        String selectCalorieQuery;
        SQLiteDatabase db = sql.getReadableDatabase();
        String selectQuery =  "select food_id from diet where date = '" +
                              current_date() + "' and user_id = " + GlobalVariables.getG_CurrentUserId();
        ArrayList<HashMap<String, String>> food_list = new
        ArrayList<HashMap<String, String>>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                food_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("food_id")));
                selectCalorieQuery = "select * from food where id = " + food_id;
                Cursor foodCursor = db.rawQuery(selectCalorieQuery, null);
                if (foodCursor.moveToFirst())
                    do {
                        HashMap<String, String> food = new HashMap<String, String>();
                        food.put("id", foodCursor.getString(foodCursor.getColumnIndex("id")));
                        food.put("category", foodCursor.getString(
                                     foodCursor.getColumnIndex("category")));
                        food.put("name", foodCursor.getString(foodCursor.getColumnIndex("name")));
                        food.put("protein", foodCursor.getString(
                                     foodCursor.getColumnIndex("protein")));
                        food.put("fat", foodCursor.getString(foodCursor.getColumnIndex("protein")));
                        food.put("calories", foodCursor.getString(
                                     foodCursor.getColumnIndex("calories")));
                        food.put("cholesterol", foodCursor.getString(
                                     foodCursor.getColumnIndex("cholesterol")));
                        food_list.add(food);
                    } while (foodCursor.moveToNext());
                foodCursor.close();
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return food_list;
    }

    /**
     * Print out a given user's recent one week diet activity along with the nutrition intake for each food
     */
    //public void print_diet_past_month()



    //public void add_new_diet()


}
