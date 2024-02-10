package com.example.mealplanner.network;

import android.util.Log;

import com.example.mealplanner.model.AllCategoryResponse;
import com.example.mealplanner.model.AreaResponse;
import com.example.mealplanner.model.Details;
import com.example.mealplanner.model.MealIngredients;
import com.example.mealplanner.model.MealsResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRemoteDataSourceImp implements MealsRemoteDataSource {
    public static final String TAG = "Random";
    public static final String RANDOM_URL = "https://www.themealdb.com/api/json/v1/1/";
    public static MealsRemoteDataSourceImp mealsRemoteDataSourceImp = null;
    private APIservice apiService;

    private MealsRemoteDataSourceImp() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(RANDOM_URL)
                .addConverterFactory(GsonConverterFactory.create(gson)).addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(APIservice.class);

    }

    public static MealsRemoteDataSourceImp getInstance() {
        if (mealsRemoteDataSourceImp == null) {
            mealsRemoteDataSourceImp = new MealsRemoteDataSourceImp();

        }
        return mealsRemoteDataSourceImp;
    }


    @Override
    public void makeNetworkCallback(RandomCallback randomCallback) {
        Call<MealsResponse> call = apiService.getMealsResponse();


        call.enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "randomcallback" + response.body().getMeals());
                    randomCallback.success(response.body());

                    Log.i(TAG, "onResponse: " + response);
                } else {
                    Log.i(TAG, "failed" + response.raw().code());
                }


            }


            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                randomCallback.failur(t.getMessage());
            }
        });
    }

//    @Override
//    public void makeCategoryCallback(RandomCallback randomCallback) {
//
//    }

    // MealsRemoteDataSourceImp.java
    @Override
    public void makeCategoryCallback(RandomCallback randomCallback) {
        Call<AllCategoryResponse> responseCall = apiService.getCategoryMeals();

        responseCall.enqueue(new Callback<AllCategoryResponse>() {
            @Override
            public void onResponse(Call<AllCategoryResponse> call, Response<AllCategoryResponse> response) {
                if (response.isSuccessful()) {
                    AllCategoryResponse allCategoryResponse = response.body();
                    if (allCategoryResponse != null) {
                        Log.i(TAG, "onResponse: " + allCategoryResponse.getCategories());
                        randomCallback.onSuccess(allCategoryResponse);
                    } else {
                        randomCallback.failur("Category response body is null");
                    }
                } else {
                    randomCallback.failur("Failed to fetch categories. Error code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<AllCategoryResponse> call, Throwable t) {
                randomCallback.failur(t.getMessage());
            }
        });
    }

    @Override
    public void makeCountriesCallback(RandomCallback randomCallback) {
        Call<AreaResponse> countriesResponseCall = apiService.getCountries();
        countriesResponseCall.enqueue(new Callback<AreaResponse>() {
            @Override
            public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response) {
                if (response.isSuccessful()) {
                    AreaResponse areaResponse = response.body();
                    if (areaResponse != null) {
                        Log.i(TAG, "onResponse: " + areaResponse.getCountries());
                        randomCallback.countriesSuccess(areaResponse);
                    } else {
                        randomCallback.failur("caountry response is null");
                    }
                }
            }

            @Override
            public void onFailure(Call<AreaResponse> call, Throwable t) {
                randomCallback.failur(t.getMessage());

            }
        });

    }

    @Override
    public void makeFilteredCallback(String category, RandomCallback randomCallback, char c) {

        if (c == 'a') {
            Single<MealsResponse> allMealsByCategory = apiService.getAllMealsByArea(category);
            Disposable d4 = allMealsByCategory.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(MealResponse -> {
                                if (MealResponse != null && MealResponse.getMeals() != null && !MealResponse.getMeals().isEmpty()) {
                                    randomCallback.filterdMeals(MealResponse);
                                    Log.i(TAG, "makeCountryCall: " + MealResponse.getMeals().get(0));
                                } else {
                                    randomCallback.failur("Empty or null meals list received");
                                }
                            },
                            throwable -> randomCallback.failur(throwable.getMessage()));

        } else if (c == 'i') {

            Single<MealsResponse> allMealsByIngredient = apiService.getAllMealsByIngredient(category);
            Disposable d5 = allMealsByIngredient.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(MealResponse -> {
                                if (MealResponse != null && MealResponse.getMeals() != null && !MealResponse.getMeals().isEmpty()) {
                                    randomCallback.filterdMeals(MealResponse);
                                } else {
                                    randomCallback.failur("Empty or null meals list received");
                                }
                            },
                            throwable -> randomCallback.failur(throwable.getMessage()));

        } else if (c == 'c') {
            Single<MealsResponse> allMealsByCountry = apiService.requestAllMealsCategory(category);
            Disposable d6 = allMealsByCountry.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(Meal -> {
                                if (Meal!= null && Meal.getMeals() != null && !Meal.getMeals().isEmpty()) {
                                    randomCallback.filterdMeals(Meal);
                                } else {
                                    randomCallback.failur("Empty or null meals list received");
                                }
                            },
                            throwable -> randomCallback.failur(throwable.getMessage()));
        }
    }


    @Override
    public void makeSearchCallBack(RandomCallback randomCallback, String search) {
        Single<MealsResponse> allMealsBySearch = apiService.getAllMealsBySearch(search);
        Disposable d = allMealsBySearch.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(myResponse -> {
                            Log.d(TAG, "enqueueCall: search ");
                            randomCallback.onSearchSuccess(myResponse.getMeals());
                        },
                        throwable -> randomCallback.failur(throwable.getMessage()));
    }

    @Override
    public void makeIngridentsCallback(RandomCallback randomCallback) {
      Disposable disposable = apiService.getIngredients().subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(ingridentResponse ->{
               randomCallback.onSuccessIngredients((MealIngredients) ingridentResponse.getMeals());
                  Log.i(TAG, "makeIngridentsCallback: "+ingridentResponse.getMeals());
        },
                      throwable -> randomCallback.failur(throwable.getMessage()));
    }

    @Override
    public void makeDetailsCallback(RandomCallback randomCallback, String id) {
        Single<Details>allDetails = apiService.getMealDetails(id);
        Disposable dis = allDetails.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(detailsResponse ->{
                            if (detailsResponse!= null && detailsResponse.getMeals() != null && !detailsResponse.getMeals().isEmpty()) {
                                randomCallback.MealDetails(detailsResponse,id);
                            } else {
                                randomCallback.failur("Empty or null details list received");
                            }
                        },
                        throwable -> randomCallback.failur(throwable.getMessage()));
    }

                }













