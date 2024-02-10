package com.example.mealplanner.network;


import com.example.mealplanner.model.AreaResponse;
import com.example.mealplanner.model.Details;
import com.example.mealplanner.model.IngredientResponse;
import com.example.mealplanner.model.MealIngredients;
import com.example.mealplanner.model.MealsItem;
import com.example.mealplanner.model.MealsResponse;
import com.example.mealplanner.model.AllCategoryResponse;

import java.util.List;

public interface RandomCallback {
    public void success(MealsResponse meals);
    public void onSuccessIngredients(MealIngredients mealIngredients) ;
    public void onSuccess(AllCategoryResponse allmeals);
    public void  countriesSuccess(AreaResponse areaResponse);
    public void onSearchSuccess(List<MealsItem> mealsItem);
     public void  filterdMeals(MealsResponse mealsResponse);
     public void MealDetails(Details details,String id);
    public void failur(String errormsg);

}