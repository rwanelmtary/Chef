package com.example.mealplanner.favourite.presenter;

import androidx.lifecycle.LiveData;

import com.example.mealplanner.model.MealsItem;

import java.util.List;

public interface FavouritePresenter {
    public void getFavMeal();
    public void remove(MealsItem mealsItem);

}
