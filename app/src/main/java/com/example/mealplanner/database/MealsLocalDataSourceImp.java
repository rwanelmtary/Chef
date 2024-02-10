package com.example.mealplanner.database;

import android.content.Context;

import com.example.mealplanner.model.MealsItem;
import com.example.mealplanner.model.Planner;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealsLocalDataSourceImp implements MealsLocalDataSource{
    private MealsDao mealsDao;
    private static MealsLocalDataSourceImp mealsLocalDataSourceImp = null;
    private Flowable<List<MealsItem>>storedMeals;
    private Flowable<List<Planner>>storedToPlanner;
    private String date;

    private MealsLocalDataSourceImp(Context context){
        MealsDataBase mealsDataBase = MealsDataBase.getInstance(context.getApplicationContext());
        mealsDao = mealsDataBase.getMealsDao();

        storedMeals = mealsDao.getAllMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());


        storedToPlanner = mealsDao.getMealOfTheDate(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public static MealsLocalDataSourceImp getInstance(Context context){
        if (mealsLocalDataSourceImp==null){
            mealsLocalDataSourceImp = new MealsLocalDataSourceImp( context);
        }return mealsLocalDataSourceImp;


    }
    @Override
    public void insertMeal(MealsItem mealsItem) {
        new Thread(()-> mealsDao.insertMeal(mealsItem)).start();

    }

    @Override
    public void deleteMeal(MealsItem mealsItem) {
        new Thread(()-> mealsDao.deletMeal(mealsItem)).start();

    }

    @Override
    public Flowable<List<MealsItem>> getAllStoredMeals() {
        return storedMeals;
    }

    @Override
    public void insertToTable(Planner planner, String date) {
      new Thread(()-> mealsDao .insertToPlanner(planner)).start();
    }

    @Override
    public void deletFromTable(Planner planner) {
        new Thread(() -> mealsDao.deletFromPlanner(planner)).start();

    }

    @Override
    public Flowable<List<Planner>> getAllPlannedMeals(String date) {
        return mealsDao.getMealOfTheDate(date);
    }
}
