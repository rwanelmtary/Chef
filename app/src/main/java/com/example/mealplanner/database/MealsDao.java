package com.example.mealplanner.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mealplanner.model.MealsItem;
import com.example.mealplanner.model.Planner;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
@Dao
public interface MealsDao {
    @Query("SELECT * From Meals_table")
    Flowable<List<MealsItem>>getAllMeals();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeal (MealsItem mealsItem);

    @Delete
    void deletMeal(MealsItem mealsItem);


    // Planner
    @Query("SELECT * From planner_table where date = :date")
    Flowable <List<Planner>> getMealOfTheDate(String date);

    @Insert(onConflict = OnConflictStrategy .IGNORE)
    void insertToPlanner (Planner planner );

    @Delete
    void  deletFromPlanner(Planner planner);




}
