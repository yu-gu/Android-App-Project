package com.example.android.moranlee.justgo.activity.activity.recommandition_usage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.moranlee.justgo.R;
import com.example.android.moranlee.justgo.activity.GlobalVariables;
import com.example.android.moranlee.justgo.activity.datatype.Food;
import com.example.android.moranlee.justgo.activity.sql_interaction.DietRepo;
import com.example.android.moranlee.justgo.activity.sql_interaction.ExerciseDailyRepo;
import com.example.android.moranlee.justgo.activity.sql_interaction.FoodRepo;
import com.example.android.moranlee.justgo.activity.sql_interaction.UserRepo;
import com.example.android.moranlee.justgo.activity.sql_interaction.WeightRepo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class GetRecommendation extends AppCompatActivity
{

    EditText target_weight;
    Button submit;
    TextView print_food;
    Button prev;
    Button next;


    UserRepo current_user;
    DietRepo dietRepo;
    FoodRepo foodRepo;
    ExerciseDailyRepo exerciseDailyRepo;
    WeightRepo weightRepo;
    //!! currently we are assuming each food added is 1 portion.
    //how many servings of a food group user is supposed to have in a day
    private int recoFruitsAndVeg;
    private int recoMeat;
    private int recoGrains;
    private int recoDairy;
    //amount of servings user has had today so far
    private int fruitsAndVegToday;
    private int meatToday;
    private int grainsToday;
    private int dairyToday;
    private double goalWeight;
    //calories eaten today
    private int currentCaloriesEaten;
    private ArrayList<com.example.android.moranlee.justgo.activity.datatype.Food>
    recommendedFoodList;
    private int recommendedListIterator;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_exercise_recommendation_activity);
        current_user = new UserRepo(this);
        dietRepo = new DietRepo(this);
        exerciseDailyRepo = new ExerciseDailyRepo(this);
        weightRepo = new WeightRepo(this);
        foodRepo = new FoodRepo(this);
        onStartUp();
        target_weight = (EditText)findViewById(R.id.target_weight);
        submit = (Button) findViewById(R.id.submit_reco);
        print_food = (TextView) findViewById(R.id.print_reco_food);
        prev = (Button) findViewById(R.id.reco_prev);
        next = (Button) findViewById(R.id.reco_next);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (target_weight.getText().length() <= 0) {
                    Toast.makeText(GetRecommendation.this, "please enter your target weight",
                                   Toast.LENGTH_SHORT).show();
                } else {
                    DisplayRecommendedFood();
                }
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayPrevFood();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayNextFood();
            }
        });
    }



    //sets all Today variables to 0
    public void initializeDay()
    {
        fruitsAndVegToday = 0;
        meatToday = 0;
        grainsToday = 0;
        dairyToday = 0;
    }

    //sets goal weight
    public void setGoalWeight(int weight)
    {
        this.goalWeight = weight;
    }
    //sets calories eaten today
    public double getCurrentCaloriesEaten()
    {
        double sumOfCalories = 0;
        ArrayList<HashMap<String, String>> todayFoodList =
                                            dietRepo.get_today_food_list();
        for (int i = 0; i < todayFoodList.size(); i++) {
            HashMap<String, String> some = todayFoodList.get(i);
            sumOfCalories += Double.parseDouble(some.get("calories"));
        }
        return sumOfCalories;

    }
    //returns daily exercise calory burning
    public double getSumOfCaloriesBurnt()
    {
        return exerciseDailyRepo.total_calorie_consumption();
    }

    //sets amount of food groups eaten today
    public void setFoodPortions()
    {

        //food categories are integers, here is what each integer refers to
        //1 is meat
        //2 is fruit
        //3 is vegetable
        //4 is dairy
        //5 is grain
        //6 is fat
        //other is userDefined

        ArrayList<HashMap<String, String>> todayFoodList =
                                            dietRepo.get_today_food_list();
        //set current food intake to zero because we are about to iterate and account for daily food intake
        initializeDay();
        for (int i = 0; i < todayFoodList.size(); i++) {
            HashMap<String, String> some = todayFoodList.get(i);
            int foodGroup = Integer.parseInt(some.get("category"));
            switch (foodGroup) {
            case 1:
                meatToday++;
            case 2:
                fruitsAndVegToday++;
            case 3:
                fruitsAndVegToday++;
            case 4:
                dairyToday++;
            case 5:
                grainsToday++;

            default:
            }
        }
    }

    //sourced from https://www.canada.ca/en/health-canada/services/food-nutrition/canada-food-guide/food-guide-basics/much-food-you-need-every-day.html
    public void getRecoFruitsAndVeg()
    {
        if (current_user.getUserAge() < 14) {
            recoFruitsAndVeg = 6;
        } else if ((current_user.getUserAge() >= 14)
                   && current_user.getUserAge() < 19) {
            if (current_user.getUserGender().equals("M")) {
                recoFruitsAndVeg = 8;
            } else {
                recoFruitsAndVeg = 7;
            }
        } else if ((current_user.getUserAge() >= 19)
                   && (current_user.getUserAge() < 51)) {
            if (current_user.getUserGender().equals("M")) {
                recoFruitsAndVeg = 9;
            } else {
                recoFruitsAndVeg = 8;
            }
        } else {
            if (current_user.getUserGender().equals("M")) {
                recoFruitsAndVeg = 7;
            } else {
                recoFruitsAndVeg = 7;
            }
        }
    }



    public void getRecoDairy()
    {
        if (current_user.getUserAge() < 14) {
            recoDairy = 3;
        } else if ((current_user.getUserAge() >= 14)
                   && current_user.getUserAge() < 19) {
            if (current_user.getUserGender().equals("M")) {
                recoDairy = 4;
            } else {
                recoDairy = 3;
            }
        } else if ((current_user.getUserAge() >= 19)
                   && (current_user.getUserAge() < 51)) {
            if (current_user.getUserGender().equals("M")) {
                recoDairy = 2;
            } else {
                recoDairy = 2;
            }
        } else {
            if (current_user.getUserGender().equals("M")) {
                recoDairy = 3;
            } else {
                recoDairy = 3;
            }
        }
    }


    public void getRecoGrains()
    {
        if (current_user.getUserAge() < 14) {
            recoGrains = 6;
        } else if ((current_user.getUserAge() >= 14)
                   && current_user.getUserAge() < 19) {
            if (current_user.getUserGender().equals("M")) {
                recoGrains = 7;
            } else {
                recoGrains = 6;
            }
        } else if ((current_user.getUserAge() >= 19)
                   && (current_user.getUserAge() < 51)) {
            if (current_user.getUserGender().equals("M")) {
                recoGrains = 8;
            } else {
                recoGrains = 6;
            }
        } else {
            if (current_user.getUserGender().equals("M")) {
                recoGrains = 7;
            } else {
                recoGrains = 6;
            }
        }
    }

    public void getRecoMeats()
    {
        if (current_user.getUserAge() < 14) {
            recoMeat = 1;
        } else if ((current_user.getUserAge() >= 14)
                   && current_user.getUserAge() < 19) {
            if (current_user.getUserGender().equals("M")) {
                recoMeat = 3;
            } else {
                recoMeat = 2;
            }
        } else if ((current_user.getUserAge() >= 19)
                   && (current_user.getUserAge() < 51)) {
            if (current_user.getUserGender().equals("M")) {
                recoMeat = 3;
            } else {
                recoMeat = 2;
            }
        } else {
            if (current_user.getUserGender().equals("M")) {
                recoMeat = 3;
            } else {
                recoMeat = 2;
            }
        }
    }

    // sets the global variables
    //stores how much of each food group user is supposed to eat
    public void setRecommendedFoodPortions()
    {
        getRecoDairy();
        getRecoFruitsAndVeg();
        getRecoGrains();
        getRecoMeats();
    }




    //calculated in metric
    //assumes these units
    //kilograms divided by meters squared

    public double calcBMI()
    {
        Double weight = weightRepo.get_last_weight(
                            GlobalVariables.getG_CurrentUserId());
        Double height = current_user.getUserHeight();
        return weight / (height * height);
    }
    //amount of calories you burn per day by doing nothing
    //assumes metric
    //user needs to keep track of current_user.get_user_age()
    // user has birthday now, so need to calculate current_user.get_user_age() from birthday
    public double neededBmr()
    {
        Double weight = weightRepo.get_last_weight(
                            GlobalVariables.getG_CurrentUserId());
        Double height = current_user.getUserHeight();
        if ((current_user.getUserGender().equals("M"))) {
            return (10 * weight + (6.25 * height) - (5 * current_user.getUserAge()) - 5);
        } else {
            return (10 * weight + (6.25 * height) - (5 * current_user.getUserAge()) - 161);
        }
    }
    //example if food_repo (and other repo classes) in how to get databases
    //assumes you want to maintain current weight
    public double caloriesAvailable()
    {

        // needed_Bmr - sumOfCaloriesAtenSoFar + Sum_of_Calories_of_exercise_burnt

        //float CaloriesToMaintainWeight = (neededBmr() - Integer.parseInt(getCurrentCaloriesEaten()) + Integer.parseInt(getSumOfCaloriesBurnt()));
        double goalBMR = 0;
        Double height = current_user.getUserHeight();
        if (goalWeight == 0) {
            //user has not set their goal weight yet
            //sets goal weight to current weight
            goalWeight = 68.0;
        }
        if ((current_user.getUserGender().equals("M"))) {
            goalBMR = (10 * goalWeight + (6.25 * height) - (5 * current_user.getUserAge())
                       - 5);
        } else {
            goalBMR =  (10 * goalWeight + (6.25 * height) - (5 * current_user.getUserAge())
                        - 161);
        }
        return (goalBMR - getCurrentCaloriesEaten() + getSumOfCaloriesBurnt());
    }

    //sets the food portions use of each group user has eaten in global variables
    //returns array of food groups.  food group that has the smallest ratio of recommended daily intake aten
    //is given highest priority and placed at front of array
    //return of 1 recommends meats
    //return of 2 recommends fruit/vegetable
    //return of 3 recommends dairy
    //return of 4 recommends brain
    public int[] getPriorityFood()
    {

        setFoodPortions();
        //gets ratio of todays current intake / recoommended intake -- for each food group
        double meatRatio = (meatToday / recoMeat);
        double fruitRatio = fruitsAndVegToday / recoFruitsAndVeg;
        double dairyRatio = dairyToday / recoDairy;
        double grainRatio = grainsToday / recoGrains;
        //assumes meat is the smallest ratio
        double ratioArray[] = {meatRatio, fruitRatio, dairyRatio, grainRatio};
        int  returnArray[] = new int [4];
        double minValue = meatRatio;
        double Value = meatRatio;
        Arrays.sort(ratioArray);

        for (int i = 0; i < 4; i++) {
            if (ratioArray[i] == meatRatio) {
                returnArray[i] = 1;
            } else if (ratioArray[i] == fruitRatio) {
                returnArray[i] = 2;
            } else if (ratioArray[i] == dairyRatio) {
                returnArray[i] = 3;
            } else if (ratioArray[i] == grainRatio) {
                returnArray[i] = 4;
            }
        }

        return returnArray;



    }

    public void onStartUp()
    {
        setFoodPortions();
        setRecommendedFoodPortions();
        foodSuggestion();
        recommendedListIterator = 0;

    }
    //fills global variable reccomendedFoodList with food recommended to user
    public void foodSuggestion()
    {

        //gets food group with the highest priority
        //priority array key:
        //1 is meats
        //2 is fruit/vegetable
        //4 is dairy
        //5 grain


        //Food Database key:
        //1 is meat
        //2 is fruit
        //3 is vegetable
        //4 is dairy
        //5 is grain
        //6 is fat
        int foodGroupsToRecommend[] = getPriorityFood();
        int highestPriorityFoodGroup = foodGroupsToRecommend[0];
        double caloriesAvail = caloriesAvailable();
        ArrayList<Food> FoodList = foodRepo.getRecommendedFoods(caloriesAvail,
                                   highestPriorityFoodGroup);
        Collections.shuffle(FoodList);
        recommendedFoodList = FoodList;

    }

    public void DisplayRecommendedFood()
    {
        int foodGroupsToRecommend[] = getPriorityFood();
        float highestPriorityFoodGroup = foodGroupsToRecommend[0];
        String foodGroup;
        if (highestPriorityFoodGroup == 1) {
            foodGroup = "meat";
        } else if (highestPriorityFoodGroup == 2) {
            foodGroup = "fruits and vegetables";
        } else if (highestPriorityFoodGroup == 3) {
            foodGroup = "dairy";
        } else {
            foodGroup = "grains";
        }
        print_food.setText("It appears you are lacking in " + foodGroup +
                           ".  We recommend you eat some " + recommendedFoodList.get(
                               recommendedListIterator).getName());
        //Toast.makeText(this, "It appears you are lacking in "+ foodGroup+ ".  We recommend you eat some "+ recommendedFoodList.get(0).getName(), Toast.LENGTH_SHORT).show();
    }

    public void DisplayNextFood()
    {
        //makes sure they are not at the last element in recommended food list
        if (recommendedListIterator >= recommendedFoodList.size() - 1) {
            //throw toast error
            DisplayRecommendedFood();
            Toast.makeText(this, "Reach maxNumber of suggestion",
                           Toast.LENGTH_SHORT).show();
        }

        else {
            recommendedListIterator++;
            DisplayRecommendedFood();
        }
    }

    public void DisplayPrevFood()
    {
        if (recommendedListIterator <= 0) {
            //throw toast error
            Toast.makeText(this, "Reach minNumber of suggestion",
                           Toast.LENGTH_SHORT).show();
            DisplayRecommendedFood();
        }

        else {
            recommendedListIterator--;
            DisplayRecommendedFood();
        }

    }
}
