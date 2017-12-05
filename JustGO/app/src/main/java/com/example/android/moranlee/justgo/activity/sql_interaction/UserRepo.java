package com.example.android.moranlee.justgo.activity.sql_interaction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.android.moranlee.justgo.activity.GlobalVariables;
import com.example.android.moranlee.justgo.activity.datatype.User;
import com.example.android.moranlee.justgo.activity.sql.SQLiteInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class UserRepo
{

    /**
     *  sql interface to interact with database
     */
    private SQLiteInterface sql;

    /**
     *  constructor, add the admin(first user)
     * @param context context hold the database
     */
    public UserRepo(Context context)
    {
        sql = new SQLiteInterface(context);
        add_admin_user();
    }

    /**
     *  insert a user type data to databse
     * @param user user data contain all information about user
     * @return int user_id represent if the data is insert successfully
     */
    public int insert(User user)
    {
        SQLiteDatabase db = sql.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", user.getId());
        values.put("name", user.getName());
        values.put("password", user.getPassword());
        values.put("height", user.getHeight());
        values.put("gender", user.getGender());
        values.put("birthday", user.getBirthday());
        long user_Id = db.insert("user", null, values);
        db.close();
        return (int) user_Id;
    }

    /**
     *
     * @param id admin user id -> 0
     * @return admin all info about admin
     */
    public User create_admin_user(int id)
    {
        User admin = new User();
        admin.setId(id);
        admin.setName("admin");
        admin.setPassword("admin");
        admin.setHeight((Math.random()) * 100);
        admin.setGender("M");
        admin.setBirthday("19991231");
        return admin;
    }


    /**
     *  remove a user by user id
     * @param user_Id the id of user aim to delete
     */
    public void delete_by_id(int user_Id)
    {
        SQLiteDatabase db = sql.getWritableDatabase();

        db.delete("user", "id" + "= ?", new String[] { String.valueOf(user_Id) });
        db.close();
    }


    /**
     *  add admin user to database
     */
    public void add_admin_user()
    {
        insert(create_admin_user(0));
    }

    /**
     *  check the combine of user_name and password
     * @param input_name user name in input field
     * @param input_password user password in input field
     * @return id if combination is valid, else return -1
     */
    public int check_user_login(String input_name, String input_password)
    {
        SQLiteDatabase db = sql.getReadableDatabase();
        String selectQuery =  "select * from user where name == '" + input_name + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                System.out.println(cursor.getString(cursor.getColumnIndex("name")));
                String Password = cursor.getString(cursor.getColumnIndex("password"));
                if (Password.equals(input_password)) {
                    int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                    cursor.close();
                    db.close();
                    return id;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return -1;
    }

    /**
     *  get all user data stored in an array
     * @return userList list of all user in user table
     */
    public ArrayList<HashMap<String, String>>  get_user_list()
    {
        SQLiteDatabase db = sql.getReadableDatabase();
        String selectQuery =  "select * from user";
        ArrayList<HashMap<String, String>> userList = new
        ArrayList<HashMap<String, String>>();
        Log.d(TAG, "get_default_user_list: " + db.toString());
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> user = new HashMap<String, String>();
                user.put("id", cursor.getString(cursor.getColumnIndex("id")));
                user.put("name", cursor.getString(cursor.getColumnIndex("name")));
                user.put("password", cursor.getString(cursor.getColumnIndex("password")));
                user.put("height", cursor.getString(cursor.getColumnIndex("height")));
                user.put("gender", cursor.getString(cursor.getColumnIndex("gender")));
                user.put("birthday", cursor.getString(cursor.getColumnIndex("birthday")));
                userList.add(user);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }

    /**
     *  count number of num in database
     * @return num number of user
     */
    public int  get_user_num()
    {
        SQLiteDatabase db = sql.getReadableDatabase();
        String selectQuery =  "select * from user";
        int num = 0;
        //ArrayList<HashMap<String, String>> foodList = new ArrayList<HashMap<String, String>>();
        //Log.d(TAG, "get_default_user_list: "+db.toString());
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                num++;
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return num;
    }

    public void update_password (String password)
    {
        SQLiteDatabase db = sql.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", GlobalVariables.getG_CurrentUserId());
        values.put("password", password);
        db.update("user", values, "id" + "= ?", new String[] { String.valueOf(GlobalVariables.getG_CurrentUserId()) });
        db.close();
    }

    public void update_height (Double height)
    {
        SQLiteDatabase db = sql.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", GlobalVariables.getG_CurrentUserId());
        values.put("height", height);
        db.update("user", values, "id" + "= ?", new String[] { String.valueOf(GlobalVariables.getG_CurrentUserId()) });
        db.close();
    }

    public void update_weight (Double weight)
    {
        SQLiteDatabase db = sql.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", GlobalVariables.getG_CurrentUserId());
        values.put("weight", weight);
        db.update("user", values, "id" + "= ?", new String[] { String.valueOf(GlobalVariables.getG_CurrentUserId()) });
        db.close();
    }

    public void update_gender (String gender)
    {
        SQLiteDatabase db = sql.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", GlobalVariables.getG_CurrentUserId());
        values.put("gender", gender);
        db.update("user", values, "id" + "= ?", new String[] { String.valueOf(GlobalVariables.getG_CurrentUserId()) });
        db.close();
    }

    // Get current user gender
    public String getUserGender()
    {
        SQLiteDatabase db = sql.getReadableDatabase();
        String selectQuery =  "select gender from user where id = " +
                              GlobalVariables.getG_CurrentUserId();
        String gender = "";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                gender = cursor.getString(cursor.getColumnIndex("gender"));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return gender;
    }

    // Get current user height
    public double getUserHeight()
    {
        SQLiteDatabase db = sql.getReadableDatabase();
        String selectQuery =  "select height from user where id = " +
                              GlobalVariables.getG_CurrentUserId();
        double height = 0.0;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                height = Double .parseDouble(cursor.getString(
                                                 cursor.getColumnIndex("height")));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return height;
    }

    // Get current user age
    public int getUserAge()
    {
        SQLiteDatabase db = sql.getReadableDatabase();
        String selectQuery =  "select birthday from user where id = " +
                              GlobalVariables.getG_CurrentUserId();
        String birthday = "";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                birthday = cursor.getString(cursor.getColumnIndex("birthday"));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        String timeStamp = new SimpleDateFormat("yyyyMMdd").format(
            Calendar.getInstance().getTime());
        int y1 = Integer.parseInt(birthday.substring(0, 4));
        int y2 = Integer.parseInt(timeStamp.substring(0, 4));
        int m1 = Integer.parseInt(timeStamp.substring(4, 6));
        int m2 = Integer.parseInt(birthday.substring(4, 6));
        int d1 = Integer.parseInt(birthday.substring(6));
        int d2 = Integer.parseInt(timeStamp.substring(6));
        int age = (y2 - y1);
        if (m2 < m1) {
            age -= 1;
        } else {
            if (d2 < d1) {
                age -= 1;
            }
        }
        return age;
    }
// <<<<<<< HEAD

//     /**
//      *  change password of current user
//      * @param password string of password
//      */
//     public void update_password (String password) {
//         SQLiteDatabase db = sql.getWritableDatabase();
//         ContentValues values = new ContentValues();
//         values.put("id", GlobalVariables.getG_CurrentUserId());
//         values.put("password",password);
//         db.update("user", values, "id" + "= ?", new String[] { String.valueOf(GlobalVariables.getG_CurrentUserId()) });
//         db.close();
//     }

//     /**
//      * change height of current user
//      * @param height double of height
//      */
//     public void update_height (Double height) {
//         SQLiteDatabase db = sql.getWritableDatabase();
//         ContentValues values = new ContentValues();
//         values.put("id", GlobalVariables.getG_CurrentUserId());
//         values.put("height",height);
//         db.update("user", values, "id" + "= ?", new String[] { String.valueOf(GlobalVariables.getG_CurrentUserId()) });
//         db.close();
//     }

//     /**
//      *  change weight of current user
//      * @param weight double of weight
//      */
//     public void update_weight (Double weight) {
//         SQLiteDatabase db = sql.getWritableDatabase();
//         ContentValues values = new ContentValues();
//         values.put("id", GlobalVariables.getG_CurrentUserId());
//         values.put("weight",weight);
//         db.update("user", values, "id" + "= ?", new String[] { String.valueOf(GlobalVariables.getG_CurrentUserId()) });
//         db.close();
//     }

//     /**
//      * change gender of current user
//      * @param gender string of gender
//      */
//     public void update_gender (String gender) {
//         SQLiteDatabase db = sql.getWritableDatabase();
//         ContentValues values = new ContentValues();
//         values.put("id", GlobalVariables.getG_CurrentUserId());
//         values.put("gender",gender);
//         db.update("user", values, "id" + "= ?", new String[] { String.valueOf(GlobalVariables.getG_CurrentUserId()) });
//         db.close();
// =======
    // Get most recent weight, waiting for wait system to test
    public Double getRecentWeight()
    {
//        SQLiteDatabase db = sql.getReadableDatabase();
//        String selectQuery = "SELECT weight FROM user WHERE id = "+GlobalVariables.getG_CurrentUserId()+
//                " ORDER BY date DESC LIMIT 1";
//        double weight = 0.0;
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                weight = Double .parseDouble(cursor.getString(cursor.getColumnIndex("weight")));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return weight;
        return 50.0;
    }

}
