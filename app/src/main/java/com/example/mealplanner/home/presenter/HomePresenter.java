package com.example.mealplanner.home.presenter;

import com.example.mealplanner.model.Details;
import com.example.mealplanner.model.IngredientResponse;
import com.example.mealplanner.model.MealsItem;
import com.example.mealplanner.model.Planner;

public interface HomePresenter {
    public void getRandomMeal();
    public void getCategories();
    public void getCountries();
    public void getFilterMeal(String country,char c);
    public void getDetaildMeal(Details details, String id);

    public void addToFav(MealsItem mealsItem);
    public void getIngredients( );
    public void addToPlan(Planner planner , String date);

    void getDetaildMeal(String id);
}
