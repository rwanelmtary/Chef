package com.example.mealplanner.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Details {
    @SerializedName("meals")
    private List<MealsItem> meals;

    public List<MealsItem> getMeals(){
        return meals;
    }

}
