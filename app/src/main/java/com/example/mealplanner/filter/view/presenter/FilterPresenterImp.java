package com.example.mealplanner.filter.view.presenter;

import com.example.mealplanner.filter.view.FilterView;
import com.example.mealplanner.model.AllCategoryResponse;
import com.example.mealplanner.model.AreaResponse;
import com.example.mealplanner.model.Details;
import com.example.mealplanner.model.IngredientResponse;
import com.example.mealplanner.model.MealIngredients;
import com.example.mealplanner.model.MealsItem;
import com.example.mealplanner.model.MealsRepo;
import com.example.mealplanner.model.MealsResponse;
import com.example.mealplanner.network.RandomCallback;

import java.util.List;

public class FilterPresenterImp implements FilterPresenter, RandomCallback{
    private MealsRepo mealsRepo;
    private FilterView filterView;


    public FilterPresenterImp(MealsRepo mealsRepo, FilterView filterView) {
        this.mealsRepo = mealsRepo;
        this.filterView = filterView;
    }


    @Override
    public void getFilterdMeals(String category, char c) {
//        mealsRepo.getFilterdMeals(category, this,c);
    }

    @Override
    public void onFilterdMealsReceived(MealsResponse mealsResponse) {
        filterView.showFilterdMeals(mealsResponse);
    }


    @Override
    public void success(MealsResponse meals) {
        filterView.showFilterdMeals(meals);

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

    }

    @Override
    public void filterdMeals(MealsResponse mealsResponse) {
        filterView.showFilterdMeals(mealsResponse);

    }

    @Override
    public void MealDetails(Details details, String id) {

    }

    @Override
    public void failur(String errormsg) {

    }


}
