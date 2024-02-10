package com.example.mealplanner.home.presenter;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.example.mealplanner.home.view.HomeView;
import com.example.mealplanner.model.AreaResponse;
import com.example.mealplanner.model.Details;
import com.example.mealplanner.model.IngredientResponse;
import com.example.mealplanner.model.MealIngredients;
import com.example.mealplanner.model.MealsItem;
import com.example.mealplanner.model.MealsRepo;
import com.example.mealplanner.model.MealsResponse;
import com.example.mealplanner.model.AllCategoryResponse;
import com.example.mealplanner.model.Planner;
import com.example.mealplanner.network.RandomCallback;

import java.util.List;

public class HomePresenterImp implements HomePresenter, RandomCallback {
    private MealsRepo mealsRepo;
    private HomeView homeView;



    public HomePresenterImp(MealsRepo mealsRepo, HomeView homeView ) {
        this.mealsRepo = mealsRepo;
        this.homeView = homeView;

    }
    public HomePresenterImp(MealsRepo mealsRepo){
        this.mealsRepo = mealsRepo;
    }

    @Override
    public void getRandomMeal() {
        mealsRepo.getRandomMeal((RandomCallback) this);

    }

    @Override
    public void getCategories() {
        mealsRepo.getCategory(this);
    }

    @Override
    public void getCountries() {
        mealsRepo.getCountrie(this);
    }

    @Override
    public void getFilterMeal(String country, char c) {
        mealsRepo.getFilterdMeals(country,this,c);

    }

    @Override
    public void getDetaildMeal(Details details,String id) {
        mealsRepo.getDetails(this,id);
    }

    @Override
    public void addToFav(MealsItem mealsItem) {
        mealsRepo.insertMeals(mealsItem);

    }

    @Override
    public void getIngredients( ) {
        mealsRepo.getMealIngredients(this);
    }

    @Override
    public void addToPlan(Planner planner, String date) {
        Log.i(TAG, "addToPlan: from home presenter");
        mealsRepo.insertForPlanning(planner,date);
    }

    @Override
    public void getDetaildMeal(String id) {
        mealsRepo.getDetails(this,id);
    }


    @Override
    public void success(MealsResponse meals) {
        homeView.showMeal(meals);

    }

    @Override
    public void onSuccessIngredients(MealIngredients mealIngredients) {
        //homeView.showIngredients(ingredientResponse);


    }

    // HomePresenterImp.java
    @Override
    public void onSuccess(AllCategoryResponse allmeals) {
        if (homeView != null) {
            homeView.showAllMeals(allmeals);
        } else {
            Log.e(TAG, "onSuccess: HomeView is null");
        }
    }

    @Override
    public void countriesSuccess(AreaResponse areaResponse) {
        if(homeView != null){
            homeView.showCountries(areaResponse);
        }else {
            Log.e(TAG, "countriesSuccess: countries is null" );
        }
    }

    @Override
    public void onSearchSuccess(List<MealsItem> mealsItem) {

    }

    @Override
    public void filterdMeals(MealsResponse mealsResponse) {
        homeView.showFilterMeal(mealsResponse);

    }

    @Override
    public void MealDetails(Details details,String id) {
        homeView.showDetails(details,id);
    }


    @Override
    public void failur(String errormsg) {
        homeView.showError(errormsg);

    }


}
