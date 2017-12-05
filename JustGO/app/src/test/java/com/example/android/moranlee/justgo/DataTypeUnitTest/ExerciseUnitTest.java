package com.example.android.moranlee.justgo.DataTypeUnitTest;

import org.junit.Test;
import com.example.android.moranlee.justgo.activity.datatype.Exercise;
import static org.junit.Assert.*;

/**
 * Created by yugu on 2017-11-23.
 */

public class ExerciseUnitTest {
    //@Parameter
    Exercise f1;
    //@parameter
    Exercise f2;

    final private int testID = 100;

    final private String test_name= "test";

    final private int test_category = 0;

    final private double test_energy_consumption = 0.0;

    @Test
    public void setup() throws Exception{
        assertNull(f1);
        assertNull(f2);
        f1 = new Exercise();
        f2 = new Exercise();
        f1.setId(testID);
        f1.setName(test_name);
        f1.setCategory(test_category);
        f1.setEnergy_consumption(test_energy_consumption);

        assertTrue(f1.getId()==testID);
        assertTrue(f1.getName() == test_name);
        assertTrue(f1.getCategory() == test_category);
        assertTrue(f1.getEnergy_consumption() == test_energy_consumption);
    }


}

