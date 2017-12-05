package com.example.android.moranlee.justgo.activity.sql_interaction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.android.moranlee.justgo.activity.GlobalVariables;
import com.example.android.moranlee.justgo.activity.datatype.Exercise;
import com.example.android.moranlee.justgo.activity.sql.SQLiteInterface;
import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;


public class ExerciseRepo
{
    /**
    default exercise type name string []
     */
    private String [] endurance = {"run", "walk", "dance"};
    private String [] strength = {"arm raise", "chair dip", "leg raise"};
    private String [] balance = {"balance walk", "stand on one foot", "tai chi"};
    private String [] flexibility = {"yoga", "buddy stretch", "calf"};

    /**
     *  sql interface to interact with database
     */
    private SQLiteInterface sql;

    /**
     *  constructor, add default exercise type
     * @param context context hold the database
     */
    public ExerciseRepo(Context context)
    {
        sql = new SQLiteInterface(context);
        //add_default_exercise();
    }

    /**
     *  create default exercise with type endurance
     * @param i type id
     * @return ex exercise type data
     */
    private Exercise cretae_default_endurance(int i)
    {
        Exercise ex = new Exercise();
        ex.setId(i);
        ex.setCategory(0);
        ex.setName(endurance[i]);
        ex.setEnergy_consumption(Math.random());
        return ex;
    }

    /**
     *  create default exercise with type strength
     * @param i type id
     * @return ex exercise type data
     */
    private Exercise cretae_default_strength(int i)
    {
        Exercise ex = new Exercise();
        ex.setId(i);
        ex.setCategory(1);
        ex.setName(strength[i - 3]);
        ex.setEnergy_consumption(Math.random());
        return ex;
    }

    /**
     *  create default exercise with type balance
     * @param i type id
     * @return ex exercise type data
     */
    private Exercise cretae_default_balance(int i)
    {
        Exercise ex = new Exercise();
        ex.setId(i);
        ex.setCategory(2);
        ex.setName(balance[i - 6]);
        ex.setEnergy_consumption(Math.random());
        return ex;
    }

    /**
     *  create default exercise with type flexibility
     * @param i type id
     * @return ex exercise type data
     */
    private Exercise cretae_default_flexibility(int i)
    {
        Exercise ex = new Exercise();
        ex.setId(i);
        ex.setCategory(3);
        ex.setName(flexibility[i - 9]);
        ex.setEnergy_consumption(Math.random());
        return ex;
    }


    public void addExercise(int category, String name, double energy)
    {
        Exercise new_exercise = new Exercise();
        new_exercise.setName(name);
        new_exercise.setCategory(category);
        new_exercise.setEnergy_consumption(energy);
        new_exercise.setId(GlobalVariables.getAndSetCurrent_max_exercise_id());
        this.insert(new_exercise);
    }


    /**
     *  insert a exercise type data to database
     * @param ex exercise data contain all information about user
     * @return int ex_id represent if the data is insert successfully
     */
    public int insert(Exercise ex)
    {
        SQLiteDatabase db = sql.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", ex.getId());
        values.put("name", ex.getName());
        values.put("category", ex.getCategory());
        values.put("energy_consumption", ex.getEnergy_consumption());
        long ex_id = db.insert("exercise", null, values);
        db.close();
        return (int) ex_id;
    }

    /**
     *  add default exercise datas to database
     */
    private void add_default_exercise()
    {
        for (int i = 0; i < 3; i++) {
            insert(cretae_default_endurance(i));
        }
        for (int i = 3; i < 6; i++) {
            insert(cretae_default_strength(i));
        }
        for (int i = 6; i < 9; i++) {
            insert(cretae_default_balance(i));
        }
        for (int i = 9; i < 12; i++) {
            insert(cretae_default_flexibility(i));
        }
    }

    /**
     *  get a list of all data and it`s information
     * @return exlist list contain all exercise type info
     */
    public ArrayList<HashMap<String, String>>  get_default_exercise_list()
    {
        SQLiteDatabase db = sql.getReadableDatabase();
        String selectQuery =  "select * from exercise";
        ArrayList<HashMap<String, String>> exList = new
        ArrayList<HashMap<String, String>>();
        Log.d(TAG, "get_default_exercise_list: " + db.toString());
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> exMap = new HashMap<String, String>();
                exMap.put("id", cursor.getString(cursor.getColumnIndex("id")));
                exMap.put("category", cursor.getString(cursor.getColumnIndex("category")));
                exMap.put("name", cursor.getString(cursor.getColumnIndex("name")));
                exMap.put("energy_consumption",
                          cursor.getString(cursor.getColumnIndex("energy_consumption")));
                exList.add(exMap);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return exList;
    }


}
