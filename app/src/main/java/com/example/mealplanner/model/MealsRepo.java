package com.example.mealplanner.model;


import com.example.mealplanner.network.RandomCallback;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface MealsRepo
{
    void getRandomMeal(RandomCallback randomCallback);
    void getCategory(RandomCallback randomCallback);
//    void getMealIngredients(String mealId, MealIngredientsCallback callback);
    void getCountrie(RandomCallback randomCallback);
    public void getSearch(RandomCallback randomCallback,String search);
    public void getFilterdMeals(String category, RandomCallback randomCallback,char c);
    public void getMealIngredients(RandomCallback randomCallback);
    public void getDetails(RandomCallback randomCallback,String id);

    public Flowable<List<Planner>> getPlannedMeal(String date);
    public void insertForPlanning(Planner planner , String date);
    public void deleFromPlanning(Planner planner);
    public Flowable<List<MealsItem>> getStoredMeals();
    public void insertMeals(MealsItem mealsItem);
    public void deletMeals(MealsItem mealsItem);

}
