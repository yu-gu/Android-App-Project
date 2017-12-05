package com.example.android.moranlee.justgo.DataTypeUnitTest;

import org.junit.Test;
import com.example.android.moranlee.justgo.activity.datatype.Diet;
import static org.junit.Assert.*;

/**
 * Created by yugu on 2017-11-23.
 */

public class DietUnitTest {
    //@Parameter
    Diet f1;
    //@parameter
    Diet f2;

    final private int testID = 100;

    final private int test_userID=0;

    final private String test_date = "2015-01-01";

    final private char test_meal_type = 'B';

    @Test
    public void setup() throws Exception{
        assertNull(f1);
        assertNull(f2);
        f1 = new Diet();
        f2 = new Diet();
        f1.setId(testID);
        f1.setUser_id(test_userID);
        f1.setDate(test_date);
        f1.setMeal_type(test_meal_type);

        assertTrue(f1.getId()==testID);
        assertTrue(f1.getUser_id() == test_userID);
        assertTrue(f1.getDate() == test_date);
        assertTrue(f1.getMeal_type() == test_meal_type);
    }


}

