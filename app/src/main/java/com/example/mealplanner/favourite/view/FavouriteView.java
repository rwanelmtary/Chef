package com.example.mealplanner.favourite.view;

import com.example.mealplanner.model.MealsItem;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface FavouriteView {
    public void showFavMeals(Flowable<List<MealsItem>> mealsItemList);
    public void deleteMeal(MealsItem mealsItem);
}
