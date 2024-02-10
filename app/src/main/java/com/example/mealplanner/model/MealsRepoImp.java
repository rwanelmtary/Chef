package com.example.mealplanner.model;

import android.content.Context;

import com.example.mealplanner.database.MealsLocalDataSource;
import com.example.mealplanner.database.MealsLocalDataSourceImp;
import com.example.mealplanner.network.MealsRemoteDataSource;
import com.example.mealplanner.network.MealsRemoteDataSourceImp;
import com.example.mealplanner.network.RandomCallback;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class MealsRepoImp implements MealsRepo {
    private MealsRemoteDataSource mealsRemoteDataSource;
    private MealsLocalDataSource mealsLocalDataSource;

    private static MealsRepoImp mealsRepoImp = null;

    private MealsRepoImp(MealsRemoteDataSource mealsRemoteDataSource, MealsLocalDataSource mealsLocalDataSource) {
        this.mealsRemoteDataSource = mealsRemoteDataSource;
        this.mealsLocalDataSource = mealsLocalDataSource;
    }

    public static MealsRepoImp getInstance(Context context) {
        if (mealsRepoImp == null) {
            MealsRemoteDataSource mealsRemoteDataSource = MealsRemoteDataSourceImp.getInstance();
            MealsLocalDataSource mealsLocalDataSource = MealsLocalDataSourceImp.getInstance(context);
            mealsRepoImp = new MealsRepoImp(mealsRemoteDataSource, mealsLocalDataSource);
        }
        return mealsRepoImp;
    }

    @Override
    public void getRandomMeal(RandomCallback randomCallback) {
        mealsRemoteDataSource.makeNetworkCallback(randomCallback);
    }

    @Override
    public void getCategory(RandomCallback randomCallback) {
        mealsRemoteDataSource.makeCategoryCallback(randomCallback);
    }

    @Override
    public void getCountrie(RandomCallback randomCallback) {
        mealsRemoteDataSource.makeCountriesCallback(randomCallback);
    }

    @Override
    public void getSearch(RandomCallback randomCallback, String search) {
        mealsRemoteDataSource.makeSearchCallBack(randomCallback,search);
    }

    @Override
    public void  getFilterdMeals(String category, RandomCallback randomCallback,char c) {
        mealsRemoteDataSource.makeFilteredCallback(category,randomCallback,c);

    }

    @Override
    public void getMealIngredients(RandomCallback randomCallback) {
        mealsRemoteDataSource.makeIngridentsCallback(randomCallback);
    }

    @Override
    public void getDetails(RandomCallback randomCallback, String id) {
        mealsRemoteDataSource.makeDetailsCallback(randomCallback,id);
    }

    @Override
    public Flowable<List<Planner>> getPlannedMeal(String date) {
        return mealsLocalDataSource.getAllPlannedMeals(date);
    }

    @Override
    public void insertForPlanning(Planner planner, String date) {
        mealsLocalDataSource.insertToTable(planner,date);
    }

    @Override
    public void deleFromPlanning(Planner planner) {
        mealsLocalDataSource.deletFromTable(planner);

    }

    @Override
    public Flowable<List<MealsItem>> getStoredMeals() {
        return mealsLocalDataSource.getAllStoredMeals();
    }

    @Override
    public void insertMeals(MealsItem mealsItem) {
        mealsLocalDataSource.insertMeal(mealsItem);
    }

    @Override
    public void deletMeals(MealsItem mealsItem) {
        mealsLocalDataSource.deleteMeal(mealsItem);
    }
}
