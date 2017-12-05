package com.example.android.moranlee.justgo.DataTypeUnitTest;

import org.junit.Test;
import com.example.android.moranlee.justgo.activity.datatype.Food;
import static org.junit.Assert.*;

/**
 * Created by yugu on 2017-11-23.
 */

public class FoodUnitTest {
    //@Parameter
    Food f1;
    //@parameter
    Food f2;

    final int testID = 100;

    final int test_userID=0;

    final double test_fat = 0.0;

    final int test_category = 1;

    final String test_name = "test";

    final double test_cholesteral = 0.0;

    final double test_calories = 0.0;

    final double test_protein = 0.0;

    @Test
    public void setup() throws Exception{
        assertNull(f1);
        assertNull(f2);
        f1 = new Food();
        f2 = new Food();
        f1.setId(testID);
        f1.setUser_id(test_userID);
        f1.setFat(test_fat);
        f1.setCategory(test_category);
        f1.setName(test_name);
        f1.setCholesterol(test_cholesteral);
        f1.setCalories(test_calories);
        f1.setProtein(test_protein);

        assertTrue(f1.getId()==testID);
        assertTrue(f1.getUser_id() == test_userID);
        assertTrue(f1.getFat()==test_fat);
        assertTrue(f1.getCategory()==test_category);
        assertTrue(f1.getCholesterol()==test_cholesteral);
        assertTrue(f1.getName().equals(test_name));
        assertTrue(f1.getProtein()==test_protein);
        assertTrue(f1.getCalories()==test_calories);

    }


}

