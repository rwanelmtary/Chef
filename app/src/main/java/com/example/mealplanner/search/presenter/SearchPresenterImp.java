package com.example.mealplanner.search.presenter;

import com.example.mealplanner.model.AllCategoryResponse;
import com.example.mealplanner.model.AreaResponse;
import com.example.mealplanner.model.Details;
import com.example.mealplanner.model.IngredientResponse;
import com.example.mealplanner.model.MealIngredients;
import com.example.mealplanner.model.MealsItem;
import com.example.mealplanner.model.MealsRepo;
import com.example.mealplanner.model.MealsResponse;
import com.example.mealplanner.network.RandomCallback;
import com.example.mealplanner.search.view.Searchview;

import java.util.List;

public class SearchPresenterImp implements  SearchPresenter , RandomCallback {
    private MealsRepo mealsRepo;
    private Searchview searchview;

    public SearchPresenterImp(MealsRepo mealsRepo, Searchview searchview) {
        this.mealsRepo = mealsRepo;
        this.searchview = searchview;
    }

    @Override
    public void getSearchedMeal(String search) {
        mealsRepo.getSearch(this,search);


    }

    @Override
    public void success(MealsResponse meals) {

    }

    @Override
    public void onSuccessIngredients(MealIngredients mealIngredients) {

    }

    @Override
    public void onSuccess(AllCategoryResponse allmeals) {

    }

    @Override
    public void countriesSuccess(AreaResponse areaResponse) {

    }

    @Override
    public void onSearchSuccess(List<MealsItem> mealsItem) {
        searchview.resultMeal(mealsItem);

    }

    @Override
    public void filterdMeals(MealsResponse mealsResponse) {

    }

    @Override
    public void MealDetails(Details details, String id) {

    }

    @Override
    public void failur(String errormsg) {

    }
}
