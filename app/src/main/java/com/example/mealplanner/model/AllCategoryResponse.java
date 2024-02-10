package com.example.mealplanner.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllCategoryResponse {

	@SerializedName("categories")
	private List<CategoriesItem> categories;

	public List<CategoriesItem> getCategories(){
		return categories;
	}
}