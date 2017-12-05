package com.example.android.moranlee.justgo.activity.datatype;

/**
 * Created by yul04 on 2017/9/23.
 */

public class Weight
{
    /*
    fields of a weight type data
     */
    private int id;
    private int user_id;
    private String date;
    private double weight;

    /*
    getter and setter of weight type datas
     */
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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

    public double getWeight()
    {
        return weight;
    }

    public void setWeight(double weight)
    {
        this.weight = weight;
    }
}
