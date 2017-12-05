package com.example.android.moranlee.justgo.activity.datatype;

/**
 * Created by yul04 on 2017/9/23.
 */

public class Exercise
{
    /*
    fields of a exercise type data
     */
    private int id;
    private String name;
    private int category;
    private double energy_consumption;

    /*
    getter and setter of exercise type datas
     */
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setCategory(int category)
    {
        this.category = category;
    }

    public int getCategory()
    {
        return category;
    }

    public double getEnergy_consumption()
    {
        return energy_consumption;
    }

    public void setEnergy_consumption(double energy_consumption)
    {
        this.energy_consumption = energy_consumption;
    }
}
