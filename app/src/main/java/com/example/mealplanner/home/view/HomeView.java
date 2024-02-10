package com.example.mealplanner.home.view;

import com.example.mealplanner.model.AreaResponse;
import com.example.mealplanner.model.Details;
import com.example.mealplanner.model.IngredientResponse;
import com.example.mealplanner.model.MealIngredients;
import com.example.mealplanner.model.MealsResponse;
import com.example.mealplanner.model.AllCategoryResponse;

public interface HomeView {
    void showMeal(MealsResponse meals);
    void showAllMeals(AllCategoryResponse allmeals);
    void  showCountries(AreaResponse areaResponse);
    void showFilterMeal(MealsResponse mealsResponse);
    public void showDetails(Details details , String id);
    public void showIngredients(MealIngredients mealIngredients);

    public void showError(String errormsg);
}
