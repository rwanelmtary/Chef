package com.example.mealplanner.planner.view;

import com.example.mealplanner.model.Planner;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface PlannerView {
    public void showPlanner(Flowable<List<Planner>>planner);
    public void delete(Planner planner);
}
