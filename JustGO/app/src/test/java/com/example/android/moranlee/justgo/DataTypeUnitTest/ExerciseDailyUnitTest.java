package com.example.android.moranlee.justgo.DataTypeUnitTest;

import org.junit.Test;
import com.example.android.moranlee.justgo.activity.datatype.ExerciseDaily;
import static org.junit.Assert.*;

/**
 * Created by yugu on 2017-11-23.
 */

public class ExerciseDailyUnitTest {
    //@Parameter
    ExerciseDaily f1;
    //@parameter
    ExerciseDaily f2;

    final private int testID = 100;

    final private int test_exercise_id= 0;

    final private int test_user_id = 0;

    final private String test_date = "2015-01-01";

    final private double test_duration = 0.0;

    @Test
    public void setup() throws Exception{
        assertNull(f1);
        assertNull(f2);
        f1 = new ExerciseDaily();
        f2 = new ExerciseDaily();
        f1.setId(testID);
        f1.setExercise_id(test_exercise_id);
        f1.setUser_id(test_user_id);
        f1.setDate(test_date);
        f1.setDuration(test_duration);

        assertTrue(f1.getId()==testID);
        assertTrue(f1.getUser_id() == test_user_id);
        assertTrue(f1.getDate().equals(test_date));
        assertTrue(f1.getDuration() == test_duration);
        assertTrue(f1.getExercise_id() == test_exercise_id);

    }


}

