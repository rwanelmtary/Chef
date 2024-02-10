package com.example.mealplanner.planner.presenter;

import com.example.mealplanner.model.Planner;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface PlannerPresenter {
    public void getTheMeal(String date);
    public void  deleta(Planner planner);
}
