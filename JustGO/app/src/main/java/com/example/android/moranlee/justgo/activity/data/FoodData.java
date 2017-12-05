package com.example.android.moranlee.justgo.activity.data;

import android.content.Context;

import com.example.android.moranlee.justgo.activity.sql_interaction.FoodRepo;

/**
 * Created by yugu on 2017-11-08.
 */

public class FoodData
{

    FoodRepo food;

    public FoodData(Context context)
    {
        food = new FoodRepo(context);
        add_food_data_meat();
        add_food_data_fruit();
        add_food_data_vegetalbe();
        add_food_data_diary();
        add_food_data_grain();
        add_food_data_fat();
    }

    /*
        Collection of food/diet data
        Adding actual data into food table in the database
     */
    public void add_food_data_diary()
    {
        //food (String name, double protein, double fat, double calories, double cholesterol)
        food.add_diary("MILK CREME MILK CHOCOLATE BARS WITH A CREAMY MILK FILLING",
                       38.89, 22.220, 583, 28);
        food.add_diary("WEIS, BETTER'N SKIM, A NON-FAT MILK WITH THE TASTE OF 2% MILK!, SKIM MILK",
                       4.58, 0, 46, 2);
        food.add_diary("MILK AND WHITE MILK CHOCOLATE TRUFFLES", 5.56, 36.110, 639,
                       14);
        food.add_diary("SPREADABLE CHEDDER", 17.86, 14.290, 321, 71);
        food.add_diary("MACARONI AND CHEESE DINNER, DELUXE, FOUR CHEESE, RICH & CREAMY CHEESE SAUCE",
                       11.22, 4.59, 337, 10);
        food.add_diary("WILLIAMS CHEESE, SHARP PINCONNING CHEESE", 25, 21.43, 393,
                       107);
        food.add_diary("CHEESE PLEASERS, MIXED CHEESE CURDS", 25, 17.86, 393, 107);
        food.add_diary("REAL MOZZARELLA CHEESE NESTLED IN A CRISPY GOLDEN COATING CHEESE STICKS",
                       10.00, 6.25, 325, 25);
        food.add_diary("DELICATE PETITE BLUE CHEESE, SOFT-RIPENED CHEESE", 17.86,
                       21.43, 393, 125);
        food.add_diary("GREAT VALUE, CHEESE PUFFS, REAL CHEESE", 3.57, 32.140, 571, 0);
        food.add_diary("IMPERIAL VALLEY CHEESE, SPICED MONTEREY JACK CHEESE, HOT PEPPER",
                       25, 17.86, 393, 107);
        food.add_diary("HERR'S, CHEESE CURLS, CHEESY CHEESE", 3.57, 12.5, 571, 0);
        food.add_diary("BEEHIVE CHEESE CO., HAND CRAFTED CHEESE", 25, 21.43, 404, 0);
        food.add_diary("OLD FASHIONED CHEESE, PASTEURIZED CHEESE SNACK, PEPPER JACK MELT",
                       6.67, 6.67, 233, 17);
        food.add_diary("MOON CHEESE, PEPPER JACK CHEESE", 33.33, 25.000, 583, 167);
        food.add_diary("Yogurt, fruit, lowfat, with low calorie sweetener, fortified with vitamin D",
                       4.85, 1.41, 105, 6);
    }

    public void add_food_data_fruit()
    {
        food.add_fruit("DEAN'S, FRUIT RUSH, FRUIT DRINK, FRUIT PUNCH", 0, 0, 25, 0);
        food.add_fruit("Apples, raw, with skin", 0.26, 0.1, 52, 0);
        food.add_fruit("Apples, raw, without skin", 0.27, 0.06, 48, 0);
        food.add_fruit("Blueberries, raw", 0.74, 0.2, 57, 0);
        food.add_fruit("Blueberries, wild, frozen", 0, 0.14, 57, 0);
        food.add_fruit("Bananas, raw", 1.09, 0.210, 0, 89);
        food.add_fruit("Blackberry juice, canned", 0.3, 0.4, 0, 38);
        food.add_fruit("Cherries, sour, red, raw", 1.00, 0.23, 50, 0);
        food.add_fruit("Cranberries, dried, sweetened", 0.17, 0.42, 308, 0);
        food.add_fruit("Grapefruit, raw, white, California", 0.88, 0.05, 37, 0);
        food.add_fruit("Lemon juice, raw", 0.35, 0.06, 22, 0);
        food.add_fruit("Peaches, yellow, raw", 0.91, 0.25, 39, 0);
        food.add_fruit("WELCH'S, FRUIT SNACKS, MIXED FRUIT", 0, 0, 314, 0);
    }

    public void add_food_data_meat()
    {
        food.add_meat("Chicken, roasting, light meat, meat only, raw", 22.2, 1.1, 109,
                      57);
        food.add_meat("Chicken, dark meat, drumstick, meat and skin, with added solution, cooked, roasted",
                      24.72, 9.00, 180, 128);
        food.add_meat("Turkey, dark meat, meat only, with added solution, cooked, roasted",
                      26.10, 5.4, 158, 105);
        food.add_meat("Duck, domesticated, meat and skin, cooked, roasted", 18.99, 25,
                      337, 84);
        food.add_meat("Lamb, variety meats and by-products, tongue, raw", 15.7, 16,
                      222, 156);
        food.add_meat("Fried Chicken, Thigh, meat only, skin and breading removed",
                      23.21, 6, 178, 125);
        food.add_meat("Fried Chicken, Drumstick, meat only, skin and breading removed",
                      26.24, 6.6, 172, 133);
        food.add_meat("Fried Chicken, Wing, meat only, skin and breading removed",
                      28.77, 9, 215, 148);
        food.add_meat("Pork, cured, ham with natural juices, spiral slice, meat only, boneless, separable lean only, heated, roasted",
                      22.56, 1.33, 126, 63);
        food.add_meat("Beef, ground, 97% lean meat / 3% fat, crumbles, cooked, pan-browned",
                      29.46, 4.9, 175, 89);
        food.add_meat("KFC, Fried Chicken, EXTRA CRISPY, Drumstick, meat only, skin and breading removed",
                      25.9, 6.3, 170, 137);
        food.add_meat("Babyfood, meat, meat sticks, junior", 13.4, 14.6, 184, 70);
        food.add_meat("Chicken, broilers or fryers, dark meat, meat and skin, cooked, stewed",
                      23.5, 14.66, 233, 82);
        food.add_meat("Beef, ground, 97% lean meat / 3% fat, raw", 21.98, 3, 121, 60);
    }

    public void add_food_data_vegetalbe()
    {
        food.add_vegetable("Cabbage, raw", 1.28, 0.68, 25, 0);
        food.add_vegetable("KIMCHI KOREA MARINATED CABBAGE", 1.79, 0, 27, 0);
        food.add_vegetable("Broccoli raab, raw", 3.17, 0.20, 22, 0);
        food.add_vegetable("Broccoli raab, cooked", 3.83, 0.23, 33, 0);
        food.add_vegetable("CAMPBELL'S CHUNKY Soups, Chicken Broccoli Cheese & Potato Soup",
                           2.86, 1.633, 86, 0);
        food.add_vegetable("Avocados, raw, California", 1.96, 13.3, 167, 0);
        food.add_vegetable("Vegetables, mixed (corn, lima beans, peas, green beans, carrots) canned, no salt added",
                           1.40, 0.15, 37, 0);
        food.add_vegetable("Carrots, raw", 0.93, 0.168, 41, 0);
        food.add_vegetable("Carrots, cooked, boiled, drained, without salt", 0.76,
                           0.115, 35, 0);
        food.add_vegetable("Tomatoes, orange, raw", 1.16, 0.14, 16, 0);
        food.add_vegetable("Tomatoes, yellow, raw", 0.98, 0.18, 15, 0);
        food.add_vegetable("CAMPBELL'S Red and White, Tomato Bisque, condensed", 1.59,
                           2.19, 103, 4);
        food.add_vegetable("Potatoes, white, flesh and skin, raw", 1.68, 0.07, 69, 0);
        food.add_vegetable("Lettuce, green leaf, raw", 1.36, 0.15, 15, 0);
        food.add_vegetable("Onions, raw", 1.1, 0.1, 40, 0);
        food.add_vegetable("Pickles, chowchow, with cauliflower onion mustard, sweet",
                           1.5, 0.9, 121, 0);
        food.add_vegetable("Peas and onions, frozen, cooked, boiled, drained, without salt",
                           2.54, 0.2, 45, 0);
    }

    public void add_food_data_grain()
    {
        food.add_grain("Bread, multi-grain, toasted", 14.52, 4.6, 288, 0);
        food.add_grain("Bread, white, commercially prepared (includes soft bread crumbs)",
                       8.85, 3.33, 266, 0);
        food.add_grain("DAVE'S KILLER BREAD, WHITE BREAD", 7.50, 5.00, 275, 0);
        food.add_grain("PANERA BREAD, SESAME SEMOLINA BREAD", 10.61, 0.76, 227, 0);
        food.add_grain("RAISIN BREAD, THE REAL BREAD COMPANY", 8.89, 6.67, 311, 0);
        food.add_grain("Bread, egg", 9.50, 6, 287, 51);
        food.add_grain("Bread, wheat", 10.67, 4.53, 274, 0);
        food.add_grain("Bread, french or vienna, toasted (includes sourdough)", 13,
                       2.14, 319, 0);
        food.add_grain("Bread, multi-grain, toasted (includes whole-grain)", 14.52,
                       2.6, 288, 0);
        food.add_grain("WHOLE GRAIN GLUTEN FREE PASTRY FLOUR ANCINET GRAIN", 10, 1.67,
                       367, 0);
        food.add_grain("Triticale flour, whole-grain", 13.18, 1.81, 338, 0);
    }



    public void add_food_data_fat()
    {
        food.add_fat("BELLA IMPORTED, OIL CURED OLIVES, WITH OLIVE OIL", 0, 40.00, 533,
                     0);
        food.add_fat("OLIVEXTRA PREMIUM, CANOLA OIL, EXTRA VIRGIN OLIVE OIL", 0, 93.33,
                     800, 0);
        food.add_fat("LEONESSA, MIXED OLIVE BRUSCHETTA EXTRA VIRGIN OLIVE OIL", 3.33,
                     33.33, 367, 0);
        food.add_fat("BLACK OLIVE PASTE WITH EXTRA VIRGIN OLIVE OIL", 6.67, 33.33, 387,
                     0);
        food.add_fat("SUNFLOWER OIL & EXTRA VIRGIN OLIVE OIL", 0, 93.33, 800, 0);
        food.add_fat("SUNFLOWER OIL & EXTRA VIRGIN OLIVE OIL BLEND", 0, 93.33, 800, 0);
        food.add_fat("CANOLA OIL & FIRST COLD PRESS EXTRA VIRGIN OLIVE OIL", 0, 93.33,
                     800, 0);
        food.add_fat("EXTRA VIRGIN OLIVE OIL", 0, 93.33, 800, 0);
        food.add_fat("SUNFLOWER OIL & EXTRA VIRGIN OLIVE OIL", 0, 93.33, 800, 0);
        food.add_fat("TENDERPOP CANOLA OIL, BUTTERY POPCORN OIL", 0, 100, 886, 0);
        food.add_fat("51% VEGETABLE OIL SPREAD WITH NON-HYDROGENATED OILS", 0, 50, 429,
                     0);
        food.add_fat("CANOLA OIL & FIRST COLD PRESS EXTRA VIRGIN OLIVE OIL", 0, 93.33,
                     800, 0);
        food.add_fat("Fish oil, salmon", 0.00, 100, 902, 485);
        food.add_fat("MAC NUT OIL, MACADAMIA NUT OIL", 0, 93.33, 93, 0);
        food.add_fat("Oil, corn and canola", 0, 100, 884, 0);
        food.add_fat("Oil, soybean, salad or cooking, (partially hydrogenated)", 0,
                     100, 884, 0);
    }



}

