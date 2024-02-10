package com.example.mealplanner.model;

import java.util.ArrayList;

public class CatigoryRespone {
    private ArrayList<CategoriesItem> meals;

    public CatigoryRespone(ArrayList<CategoriesItem> meals) {
        this.meals = meals;
    }

    public ArrayList<CategoriesItem> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<CategoriesItem> meals) {
        this.meals = meals;
    }
}
