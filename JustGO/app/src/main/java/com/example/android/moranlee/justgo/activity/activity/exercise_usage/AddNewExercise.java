package com.example.android.moranlee.justgo.activity.activity.exercise_usage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.android.moranlee.justgo.R;
import com.example.android.moranlee.justgo.activity.GlobalVariables;
import com.example.android.moranlee.justgo.activity.activity.MainMenu;
import com.example.android.moranlee.justgo.activity.datatype.Exercise;
import com.example.android.moranlee.justgo.activity.sql_interaction.ExerciseRepo;

public class AddNewExercise extends AppCompatActivity
{

    /*
    radio button to select new exercise category
     */
    RadioGroup categoryGroup;
    RadioButton endurance;
    RadioButton strength;
    RadioButton balance;
    RadioButton flexibility;

    /*
    store input new exercise name
     */
    EditText name;

    /*
    store new input exercise calorie consumption
     */
    EditText consumption;

    /*
    submit new exercise
     */
    Button submit;

    /*
    new exercise category id
     */
    int category;

    /*
    sqlite interface
     */
    ExerciseRepo exercise_repo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_exercise_activity);
        categoryGroup = (RadioGroup)findViewById(R.id.add_exercise_cate_choice);
        endurance = (RadioButton) findViewById(R.id.new_exercise_endurance);
        strength = (RadioButton) findViewById(R.id.new_exercise_strength);
        balance = (RadioButton) findViewById(R.id.new_exercise_balance);
        flexibility = (RadioButton) findViewById(R.id.new_exercise_flexibility);
        name = (EditText) findViewById(R.id.edit_exercise_name);
        consumption = (EditText) findViewById(R.id.edit_exercise_consumption);
        submit = (Button)findViewById(R.id.add_exercise_submit);
        category = 0;
        exercise_repo = new ExerciseRepo(this);
        categoryGroup.setOnCheckedChangeListener(new
        RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == endurance.getId()) {
                    category = 1;
                } else if (checkedId == strength.getId()) {
                    category = 2;
                } else if (checkedId == balance.getId()) {
                    category = 3;
                } else if (checkedId == flexibility.getId()) {
                    category = 4;
                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().length() <= 0 || consumption.getText().length() <= 0
                        || category == 0) {
                    Toast.makeText(AddNewExercise.this,
                                   "new exercise can not exist without category/name/calorie consumption",
                                   Toast.LENGTH_SHORT).show();
                } else {
                    Exercise exercise = new Exercise();
                    exercise.setId(GlobalVariables.getAndSetCurrent_max_exercise_id());
                    exercise.setCategory(category - 1);
                    exercise.setName(name.getText().toString());
                    exercise.setEnergy_consumption(Double.parseDouble(
                                                       consumption.getText().toString()));
                    exercise_repo.insert(exercise);
                    Intent goBack = new Intent(get_self(), SelectExerciseOption.class);
                    goBack.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(goBack);
                    finish();

                }
            }
        });
    }

    private Context get_self()
    {
        return AddNewExercise.this;
    }


}
