package com.example.mealplanner.model;

import java.util.ArrayList;

public class MealIngredients {



        private String mealName;
        private ArrayList<String> ingredients;

        public MealIngredients(String mealName, ArrayList<String> ingredients) {
            this.mealName = mealName;
            this.ingredients = ingredients;
        }

        public String getMealName() {
            return mealName;
        }

        public void setMealName(String mealName) {
            this.mealName = mealName;
        }

        public ArrayList<String> getIngredients() {
            return ingredients;
        }

        public void setIngredients(ArrayList<String> ingredients) {
            this.ingredients = ingredients;
        }
    }


