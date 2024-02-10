package com.example.mealplanner.planner.presenter;

import com.example.mealplanner.model.MealsRepo;
import com.example.mealplanner.model.Planner;
import com.example.mealplanner.planner.view.PlannerView;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class PlannerPresenterImp  implements PlannerPresenter{
    private PlannerView plannerView;
    private MealsRepo mealsRepo;

    public PlannerPresenterImp(PlannerView plannerView, MealsRepo mealsRepo) {
        this.plannerView = plannerView;
        this.mealsRepo = mealsRepo;
    }

    @Override
    public void getTheMeal(String date) {
        plannerView.showPlanner(mealsRepo.getPlannedMeal(date));
    }

    @Override
    public void deleta(Planner planner) {
        mealsRepo.deleFromPlanning(planner);

    }
}
