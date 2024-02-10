package com.example.mealplanner.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MealsResponse implements Serializable {

    @SerializedName("meals")
    private List<MealsItem> meals;

    public List<MealsItem> getMeals(){
        return meals;
    }

}
