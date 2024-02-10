package com.example.mealplanner.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CountriesResponse{

	@SerializedName("mealsfilter")
	private List<MealsItem> meals;

	public List<MealsItem> getMeals(){
		return meals;
	}
}