package com.example.android.moranlee.justgo.activity.data;

import com.example.android.moranlee.justgo.activity.sql_interaction.ExerciseRepo;
import android.content.Context;

/**
 * Created by yugu on 2017-11-10.
 */

public class ExerciseData
{

    private ExerciseRepo exercises;

    public ExerciseData(Context context)
    {
        exercises = new ExerciseRepo(context);
        addExercise();
    }

    /*
        Unit of Calories: per hour
        Weight: based on a weight of 150 lbs
     */
    public void addExercise()
    {
        exercises.addExercise(0, "Hiking", 367);
        exercises.addExercise(0, "Dancing", 331);
        exercises.addExercise(0, "Golf (walking - carrying clubs)", 331);
        exercises.addExercise(0, "Bicycling", 294);
        exercises.addExercise(0, "Walking", 279);
        exercises.addExercise(0, "Weightlifting", 220);
        exercises.addExercise(0, "Stretching", 184);
        exercises.addExercise(0, "Running/jogging (5 mph)", 588);
        exercises.addExercise(0, "Bicycling (>10mph)", 588);
        exercises.addExercise(0, "BRISK WALK", 255);
        exercises.addExercise(0, "INCLINE WALK", 408);
        exercises.addExercise(0, "Skiing", 408);
        exercises.addExercise(0, "Snoe shoeing", 544);
        exercises.addExercise(0, "Softball, officiating", 272);

        exercises.addExercise(1, "BARBELL BENCH PRESS", 459);
        exercises.addExercise(1, "CRUNCHES", 204);
        exercises.addExercise(1, "SIDE CRUNCHES", 221);
        exercises.addExercise(1, "PUSHUPS", 340);
        exercises.addExercise(1, "INCLINE DUMBBELL PRESS", 408);
        exercises.addExercise(1, "BICYCLE CRUNCHES", 255);
        exercises.addExercise(1, "WIDE-GRIP FRONT PULLDOWNS", 408);
        exercises.addExercise(1, "WIDE-GRIP FRONT PULLDOWNS", 408);
        exercises.addExercise(1, "ONE-ARM DUMBBELL BENT-OVER ROWS", 408);
        exercises.addExercise(1, "REVERSE CRUNCHES", 391);
        exercises.addExercise(1, "BARBELL SQUATS", 476);
        exercises.addExercise(1, "ANGLED LEG PRESS", 408);
        exercises.addExercise(1, "LEG EXTENSIONS", 207);
        exercises.addExercise(1, "LYING LEG CURLS", 272);
        exercises.addExercise(1, "STANDING CALF RAISES", 204);
        exercises.addExercise(1, "BARBELL BENCH PRESS", 459);

        //Competitive Exercises
        exercises.addExercise(2, "Basketball (vigorous)", 441);
        exercises.addExercise(2, "Basketball, game", 544);
        exercises.addExercise(2, "Swimming (slow freestyle laps)", 514);
        exercises.addExercise(2, "Hocky", 204);
        exercises.addExercise(2, "Hockey, ice", 544);
        exercises.addExercise(2, "Football", 204);
        exercises.addExercise(2, "Bowling", 204);
        exercises.addExercise(2, "Baseball", 347);
        exercises.addExercise(2, "Table tennis", 272);
        exercises.addExercise(2, "volleyball, beach", 544);
        exercises.addExercise(2, "Boxing, in ring, general", 816);
        exercises.addExercise(2, "Broomball", 476);
        exercises.addExercise(2, "Race Walking", 442);


        exercises.addExercise(3, "YOGA FOR BEGINNERS", 170);
        exercises.addExercise(3, "STANDING BARBELL CURLS", 357);
        exercises.addExercise(3, "CONCENTRATION CURLS", 357);
        exercises.addExercise(3, "BARBELL WRIST CURLS", 204);


    }



}
