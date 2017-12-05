package com.example.android.moranlee.justgo.activity.activity.analysis_usage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.moranlee.justgo.R;
import com.example.android.moranlee.justgo.activity.GlobalVariables;
import com.example.android.moranlee.justgo.activity.sql_interaction.DietRepo;
import com.example.android.moranlee.justgo.activity.sql_interaction.UserRepo;
import com.example.android.moranlee.justgo.activity.sql_interaction.WeightRepo;


public class Analysis extends AppCompatActivity
{
    UserRepo user;
    DietRepo diet;
    WeightRepo we;
    /**
     *  initialize analysis activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        user = new UserRepo(this);
        diet = new DietRepo(this);
        we = new WeightRepo(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analysis);

        // Get user information
        Double weight = we.get_last_weight(GlobalVariables.getG_CurrentUserId());
        String gender = user.getUserGender();
        Double height = user.getUserHeight();
        int age = user.getUserAge();
        int calorie = diet.totalCalorieIntake();
        int protein = diet.totalProteinIntake();
        int cholesterol = diet.totalCholesterolIntake();

        // Display user information
        displayHeight(height);
        displayWeight(weight);
        displayGender(gender);
        displayAge(age);
        int bmi = displayBMI(weight, height);
        displayHealth(bmi);
        displayNutrition(calorie, protein, cholesterol);
    }

    private void displayHeight(Double height)
    {
        TextView heightText = (TextView) findViewById(R.id.analysisHeight);
        heightText.setText("Height: " + height);
    }

    private void displayWeight(Double weight)
    {
        TextView weightText = (TextView) findViewById(R.id.analysisWeight);
        weightText.setText("Weight: " + weight);
    }

    private void displayAge(int age)
    {
        TextView ageText = (TextView) findViewById(R.id.analysisAge);
        ageText.setText("Age: " + age);
    }

    private void displayGender(String gender)
    {
        TextView genderText = (TextView) findViewById(R.id.analysisGender);
        if (gender.equals("M"))
            genderText.setText("Gender: Male");
        else
            genderText.setText("Gender: Female");
    }

    private int displayBMI(Double weight, Double height)
    {
        int bmi = (int)(weight / (height * height / 10000));
        TextView BMIText = (TextView) findViewById(R.id.analysisBMI);
        BMIText.setText("BMI: " + bmi);
        return bmi;
    }

    private void displayHealth(int bmi)
    {
        TextView healthText = (TextView) findViewById(R.id.analysisHealth);
        if (bmi < 18.5)
            healthText.setText("Health Status: Underweight");
        else if (bmi < 25)
            healthText.setText("Health Status: Recommended");
        else if (bmi < 30)
            healthText.setText("Health Status: Overweight");
        else
            healthText.setText("Health Status: Obese");
    }

    private void displayNutrition(int calorie, int protein, int cholesterol)
    {
        TextView calorieText = (TextView) findViewById(R.id.analysisCalorie);
        TextView proteinText = (TextView) findViewById(R.id.analysisProtein);
        TextView cholesterolText = (TextView) findViewById(R.id.analysisCholesterol);
        calorieText.setText("Calorie Intake: " + calorie);
        proteinText.setText("Protein Intake: " + protein);
        cholesterolText.setText("Cholesterol Intake: " + cholesterol);
    }

}
