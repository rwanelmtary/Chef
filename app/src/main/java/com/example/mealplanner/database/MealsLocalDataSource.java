package com.example.mealplanner.database;

import com.example.mealplanner.model.MealsItem;
import com.example.mealplanner.model.Planner;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface MealsLocalDataSource {
    void insertMeal(MealsItem mealsItem);
    void deleteMeal(MealsItem mealsItem);
    Flowable<List<MealsItem>>getAllStoredMeals();

    void insertToTable(Planner planner, String date);

    void deletFromTable(Planner planner);
    Flowable<List<Planner>>getAllPlannedMeals(String date);

}
