package com.example.android.moranlee.justgo.DataTypeUnitTest;

import org.junit.Test;
import com.example.android.moranlee.justgo.activity.datatype.Weight;
import static org.junit.Assert.*;

/**
 * Created by yugu on 2017-11-23.
 */

public class WeightUnitTest {
    //@Parameter
    Weight f1;
    //@parameter
    Weight f2;

    final private int testID = 100;

    final private int test_user_id= 0;

    final private String  test_date = "2015-01-01";

    final private double test_weight = 0.0;

    @Test
    public void setup() throws Exception{
        assertNull(f1);
        assertNull(f2);
        f1 = new Weight();
        f2 = new Weight();
        f1.setId(testID);
        f1.setDate(test_date);
        f1.setUser_id(test_user_id);
        f1.setWeight(test_weight);

        assertTrue(f1.getId()==testID);
        assertTrue(f1.getDate() == test_date);
        assertTrue(f1.getWeight() == test_weight);
        assertTrue(f1.getUser_id() == test_user_id);
    }


}

