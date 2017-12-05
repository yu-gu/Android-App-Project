package com.example.android.moranlee.justgo.activity.activity;

/**
 * Created by homeyxue on 2017-11-04.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.example.android.moranlee.justgo.R;
import com.example.android.moranlee.justgo.activity.activity.user_usage.Login;


public class WelcomeScreen extends Activity
{
    LinearLayout up, bottom;
    Animation uptodown, downtoup;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        up = (LinearLayout) findViewById(R.id.up_part);
        bottom = (LinearLayout) findViewById(R.id.bottom_part);
        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        up.setAnimation(uptodown);
        bottom.setAnimation(downtoup);


        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        myThread.start();
    }
}
