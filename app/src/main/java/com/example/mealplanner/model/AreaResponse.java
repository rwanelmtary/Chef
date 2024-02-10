package com.example.mealplanner.model;

import java.util.List;

public class AreaResponse {
    private List<CountryResponse> meals;

    public void CountryResponse(List<CountryResponse> meals) {
        this.meals = meals;
    }

    public void CountryResponse() {
    }

    public List<CountryResponse> getCountries() {
        return meals;
    }

    public void setCountries(List<CountryResponse> meals) {
        this.meals =meals;
}

}
