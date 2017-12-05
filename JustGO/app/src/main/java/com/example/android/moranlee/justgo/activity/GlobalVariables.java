package com.example.android.moranlee.justgo.activity;

import android.app.Application;

/**
 * Created by yul04 on 2017/10/10.
 */

public class GlobalVariables extends Application
{
    static int g_CurrentUserId;
    static int g_CurrentMaxFoodId;
    static int g_CurrentMaxUserId;
    static int current_max_diet_id;
    static int current_max_exercise_daily_id;
    static int current_max_exercise_id;
    static int g_CurrentMaxWeightId;

    public static int getG_CurrentMaxWeightId()
    {
        return g_CurrentMaxWeightId;
    }

    public static void setG_CurrentMaxWeightId(int g_CurrentMaxWeightId)
    {
        GlobalVariables.g_CurrentMaxWeightId = g_CurrentMaxWeightId;
    }

    public static int getAndSetCurrentMaxWeightId()
    {
        int id = getG_CurrentMaxWeightId();
        setG_CurrentMaxWeightId(getG_CurrentMaxWeightId() + 1);
        return id;
    }

    /*
            @Return the id of user currently using the system
         */
    public static int getG_CurrentUserId()
    {
        return g_CurrentUserId;
    }

    /*
        Set current user id
     */
    public static void setG_CurrentUserId(int g_CurrentUserId)
    {
        GlobalVariables.g_CurrentUserId = g_CurrentUserId;
    }

    /*
        @Return the maximum id avialable in food database,
        count the number of items available for select in food table
     */
    public static int getG_CurrentMaxFoodId()
    {
        return g_CurrentMaxFoodId;
    }

    /*
        Set the maximum food id
     */
    public static void setG_CurrentMaxFoodId(int g_CurrentMaxFoodId)
    {
        GlobalVariables.g_CurrentMaxFoodId = g_CurrentMaxFoodId;
    }

    /*
       @Return the maximum id avialable in food database,
       count the number of items available for select in food table
    */
    public static int get_and_set_Current_max_food_id()
    {
        g_CurrentMaxFoodId += 1;
        return g_CurrentMaxFoodId;
    }


    /*
            @Return the maximum id avialable in user database,
            count the number of users available in user table
         */
    public static int getG_CurrentMaxUserId()
    {
        return g_CurrentMaxUserId;
    }

    /*
        Set the maximum the maximum user id
     */
    public static void setG_CurrentMaxUserId(int g_CurrentMaxUserId)
    {
        GlobalVariables.g_CurrentMaxUserId = g_CurrentMaxUserId;
    }

    /*
        @Return the maximum id avialable in diet table,
        count the numebr of diets available in diet table
     */
    public static int getCurrent_max_diet_id()
    {
        return current_max_diet_id;
    }

    /*
        Set maximum diet id available in diet table
     */
    public static void setCurrent_max_diet_id(int current_max_diet_id)
    {
        GlobalVariables.current_max_diet_id = current_max_diet_id;
    }

    /*
        @Return the maximum id avialable in exercise_daily table,
        count the number of diets available in diet table
     */
    public static int getCurrent_max_exercise_daily_id()
    {
        return current_max_exercise_daily_id;
    }

    /*
        Set maximum exercise_daily id available in exercise_daily table
     */
    public static void setCurrent_max_exercise_daily_id(int
            current_max_exercise_daily_id)
    {
        GlobalVariables.current_max_exercise_daily_id = current_max_exercise_daily_id;
    }

    /*
        @Return the maximum id avialable in exercise table,
        count the number of diets available in diet table
     */
    public static int getCurrent_max_exercise_id()
    {
        return current_max_exercise_id;
    }

    /*
       Set maximum exercise id available in exercise_daily table
    */
    public static void setCurrent_max_exercise_id(int current_max_exercise_id)
    {
        GlobalVariables.current_max_exercise_id = current_max_exercise_id;
    }

    public static int  getAndSetCurrent_max_exercise_id()
    {
        int result = getCurrent_max_exercise_id();
        setCurrent_max_exercise_id(getCurrent_max_exercise_id() + 1);
        return result;
    }


}
