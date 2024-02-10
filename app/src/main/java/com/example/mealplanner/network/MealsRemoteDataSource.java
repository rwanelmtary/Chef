package com.example.mealplanner.network;

public interface MealsRemoteDataSource {
    public void makeNetworkCallback (RandomCallback randomCallback);
    public void makeCategoryCallback(RandomCallback randomCallback);
    public void makeCountriesCallback(RandomCallback randomCallback);
    public void makeFilteredCallback(String category ,RandomCallback randomCallback,char c);
    public void makeSearchCallBack(RandomCallback randomCallback,String search);
    public void  makeIngridentsCallback(RandomCallback randomCallback);
    public void makeDetailsCallback(RandomCallback randomCallback , String id);

}
