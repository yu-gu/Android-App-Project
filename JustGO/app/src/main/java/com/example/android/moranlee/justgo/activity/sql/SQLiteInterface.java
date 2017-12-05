package com.example.android.moranlee.justgo.activity.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yul04 on 2017/9/23.
 */

public class SQLiteInterface extends SQLiteOpenHelper
{

    /**
     *  database version require to initialize database
     */
    private static final int DATABASE_VERSION = 2;

    /**
     *  database name require to initialize database, and drop it
     */
    private static final String DATABASE_NAME = "JustGo";

    /**
     *  constructor
     * @param context context hold the databse
     */
    public SQLiteInterface(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     *  initialize database with basic tables
     * @param db database store the tables
     */
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String [] end = drop_table();
        for (int i = 0; i < end.length; i++) {
            db.execSQL(end[i]);
        }
        String [] start = do_create_table_string();
        for (int i = 0; i < start.length; i++) {
            db.execSQL(start[i]);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String [] end = drop_table();
        for (int i = 0; i < end.length; i++) {
            db.execSQL(end[i]);
        }
    }

    /**
     *  create string array contain sql query to create require tables
     * @return creating contain all  create table sql query
     */
    private String [] do_create_table_string()
    {
        String [] creating = new String [6];
        creating[0] =
            "create table food (id int primary key not null, user_id int not null, category int not null, name char(50) not null, protein numeric not null, fat numeric not null, cholesterol numeric not null, calories numeric not null);";
        creating[1] =
            "create table user (id int primary key not null, name char(50) not null, password char(15) not null, height numeric not null, gender char(1) not null, birthday varchar(8) not null);";
        creating[2] =
            "create table weight (id int primary key not null, user_id int not null, date char(20) not null, weight numeric not null);";
        creating[3] =
            "create table diet (id id int primary key not null, food_id int not null, user_id int not null, date char(50) not null, meal_type char(1) not null);";
        creating[4] =
            "create table exercise_daily (id int primary key not null, exercise_id int not null, user_id int not null, date char(50) not null, duration numeric not null);";
        creating[5] =
            "create table exercise (id int primary key not null, category int not null, name char(20) not null, energy_consumption numeric not null);";
        return creating;
    }

    /**
     *  create string array contain sql quert to frop required tables
     * @return dropping containing all drop table sql query
     */
    private String [] drop_table()
    {
        String [] dropping = new String [6];
        dropping[0] = "drop table if exists food;";
        dropping[1] = "drop table if exists user;";
        dropping[2] = "drop table if exists weight;";
        dropping[3] = "drop table if exists diet;";
        dropping[4] = "drop table if exists ExerciseDaily;";
        dropping[5] = "drop table if exists exercise;";
        return dropping;
    }

}
