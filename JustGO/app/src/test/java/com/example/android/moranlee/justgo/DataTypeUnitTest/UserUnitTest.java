package com.example.android.moranlee.justgo.DataTypeUnitTest;

import org.junit.Test;
import com.example.android.moranlee.justgo.activity.datatype.User;
import static org.junit.Assert.*;

/**
 * Created by yugu on 2017-11-23.
 */

public class UserUnitTest {
    //@Parameter
    User f1;
    //@parameter
    User f2;

    final private int testID = 100;

    final private String test_name= "test";

    final private String test_password = "test";

    final private double test_height = 0.0;

    final private String test_gender = "M";

    final private String test_birthday = "2015-01-01";


    @Test
    public void setup() throws Exception{
        assertNull(f1);
        assertNull(f2);
        f1 = new User();
        f2 = new User();
        f1.setId(testID);
        f1.setName(test_name);
        f1.setBirthday(test_birthday);
        f1.setGender(test_gender);
        f1.setHeight(test_height);
        f1.setPassword(test_password);

        assertTrue(f1.getId()==testID);
        assertTrue(f1.getName() == test_name);
        assertTrue(f1.getBirthday() == test_birthday);
        assertTrue(f1.getGender() == test_gender);
        assertTrue(f1.getPassword() == test_password);
        assertTrue(f1.getHeight() == test_height);


    }


}

