package com.example.android.moranlee.justgo.activity.datatype;

/**
 * Created by yul04 on 2017/9/23.
 */

public class ExerciseDaily
{
    /*
    fields of a exercise daily type data
     */
    private int id;
    private int exercise_id;
    private int user_id;
    private String date;
    private double duration;

    /*
    getter and setter of exercise_daily type datas
     */
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getExercise_id()
    {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id)
    {
        this.exercise_id = exercise_id;
    }

    public int getUser_id()
    {
        return user_id;
    }

    public void setUser_id(int user_id)
    {
        this.user_id = user_id;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public double getDuration()
    {
        return duration;
    }

    public void setDuration(double duration)
    {
        this.duration = duration;
    }
}
