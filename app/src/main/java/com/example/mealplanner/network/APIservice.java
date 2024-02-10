package com.example.mealplanner.network;

import com.example.mealplanner.model.AllCategoryResponse;
import com.example.mealplanner.model.AreaResponse;
import com.example.mealplanner.model.Details;
import com.example.mealplanner.model.IngredientResponse;
import com.example.mealplanner.model.MealsResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIservice {
    @GET("random.php")
    public Call<MealsResponse> getMealsResponse();

    @GET("categories.php")
   public Call<AllCategoryResponse> getCategoryMeals();

    @GET("list.php?a=list")
    public Call<AreaResponse> getCountries();

    @GET("search.php?")
    Single<MealsResponse> getAllMealsBySearch(@Query("s") String name);

    @GET("list.php?i=list")
    Single<IngredientResponse> getIngredients();
    @GET("filter.php?")
    Single <MealsResponse> requestAllMealsCategory(@Query("c") String category);

    @GET("filter.php?")
    Single<MealsResponse> getAllMealsByArea(@Query("a") String area);

    @GET("filter.php?")
    Single<MealsResponse> getAllMealsByIngredient(@Query("i") String ingredient);
    @GET("lookup.php?")
    Single<Details> getMealDetails(@Query("i") String id);



}
