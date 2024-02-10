package com.example.mealplanner.favourite.presenter;

import androidx.lifecycle.LiveData;

import com.example.mealplanner.favourite.view.FavouriteView;
import com.example.mealplanner.model.MealsItem;
import com.example.mealplanner.model.MealsRepo;

import java.util.List;

public class FavouritePresenterImp implements FavouritePresenter {
    private FavouriteView favouriteView;
    private MealsRepo mealsRepo;

    public FavouritePresenterImp(FavouriteView favouriteView, MealsRepo mealsRepo) {
        this.favouriteView = favouriteView;
        this.mealsRepo = mealsRepo;
    }

    @Override
    public void getFavMeal() {
        favouriteView.showFavMeals(mealsRepo.getStoredMeals());

    }

    @Override
    public void remove(MealsItem mealsItem) {
        mealsRepo.deletMeals(mealsItem);

    }
}
