package com.example.android.moranlee.justgo.activity.datatype;

/**
 * Created by yul04 on 2017/9/23.
 */

public class Food
{
    /*
    fields of a food type data
     */
    private int id;
    private int user_id;
    private int category;
    private String name;
    private double protein;
    private double fat;
    private double cholesterol;
    private double calories;

    /*
    getter and setter of food type datas
     */
    public void setId(int id)
    {
        this.id = id;
    }

    public void setUser_id(int user_id)
    {
        this.user_id = user_id;
    }

    public void setCategory(int category)
    {
        this.category = category;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setProtein(double protein)
    {
        this.protein = protein;
    }

    public void setFat(double fat)
    {
        this.fat = fat;
    }

    public void setCholesterol(double cholesterol)
    {
        this.cholesterol = cholesterol;
    }

    public void setCalories(double calories)
    {
        this.calories = calories;
    }

    public int getId()
    {
        return id;
    }

    public int getUser_id()
    {
        return user_id;
    }

    public int getCategory()
    {
        return category;
    }

    public String getName()
    {
        return name;
    }

    public double getProtein()
    {
        return protein;
    }

    public double getFat()
    {
        return fat;
    }

    public double getCholesterol()
    {
        return cholesterol;
    }

    public double getCalories()
    {
        return calories;
    }
}
