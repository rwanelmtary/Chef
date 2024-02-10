package com.example.mealplanner.filter.view.presenter;

import com.example.mealplanner.model.MealsItem;
import com.example.mealplanner.model.MealsResponse;

public interface FilterPresenter {
    public void getFilterdMeals(String category,char c);
   public void onFilterdMealsReceived(MealsResponse mealsResponse); // Add this method

}
