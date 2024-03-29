package com.example.mealplanner.model;

    public class PlanDetailConverter {
        public static Planner getMealPlannerFromMealAndDate(MealsItem mealDto, String date, int dayOfWeek) {
            Planner planDetailsModel = new Planner();
            planDetailsModel.idMeal = mealDto.getIdMeal();
            planDetailsModel.date = date;
            planDetailsModel.strMeal = mealDto.getStrMeal();
            planDetailsModel.dayOfWeek = dayOfWeek;
            planDetailsModel.strCategory = mealDto.getStrCategory();
            planDetailsModel.strArea = mealDto.getStrArea();
            planDetailsModel.strInstructions = mealDto.getStrInstructions();
            planDetailsModel.strMealThumb = mealDto.getStrMealThumb();
            planDetailsModel.strTags = (String) mealDto.getStrTags();
            planDetailsModel.strYoutube = mealDto.getStrYoutube();
            planDetailsModel.strIngredient1 = mealDto.getStrIngredient1();
            planDetailsModel.strIngredient2 = mealDto.getStrIngredient2();
            planDetailsModel.strIngredient3 = mealDto.getStrIngredient3();
            planDetailsModel.strIngredient4 = mealDto.getStrIngredient4();
            planDetailsModel.strIngredient5 = mealDto.getStrIngredient5();
            planDetailsModel.strIngredient6 = mealDto.getStrIngredient6();
            planDetailsModel.strIngredient7 = mealDto.getStrIngredient7();
            planDetailsModel.strIngredient8 = mealDto.getStrIngredient8();
            planDetailsModel.strIngredient9 = mealDto.getStrIngredient9();
            planDetailsModel.strIngredient10 = mealDto.getStrIngredient10();
            planDetailsModel.strIngredient11 = mealDto.getStrIngredient11();
            planDetailsModel.strIngredient12 = mealDto.getStrIngredient12();
            planDetailsModel.strIngredient13 = mealDto.getStrIngredient13();
            planDetailsModel.strIngredient14 = mealDto.getStrIngredient14();
            planDetailsModel.strIngredient15 = mealDto.getStrIngredient15();
            planDetailsModel.strIngredient16 = mealDto.getStrIngredient16();
            planDetailsModel.strIngredient17 = mealDto.getStrIngredient17();
            planDetailsModel.strIngredient18 = mealDto.getStrIngredient18();
            planDetailsModel.strIngredient19 = mealDto.getStrIngredient19();
            planDetailsModel.strIngredient20 = mealDto.getStrIngredient20();
            planDetailsModel.strMeasure1 = mealDto.getStrMeasure1();
            planDetailsModel.strMeasure2 = mealDto.getStrMeasure2();
            planDetailsModel.strMeasure3 = mealDto.getStrMeasure3();
            planDetailsModel.strMeasure4 = mealDto.getStrMeasure4();
            planDetailsModel.strMeasure5 = mealDto.getStrMeasure5();
            planDetailsModel.strMeasure6 = mealDto.getStrMeasure6();
            planDetailsModel.strMeasure7 = mealDto.getStrMeasure7();
            planDetailsModel.strMeasure8 = mealDto.getStrMeasure8();
            planDetailsModel.strMeasure9 = mealDto.getStrMeasure9();
            planDetailsModel.strMeasure10 = mealDto.getStrMeasure10();
            planDetailsModel.strMeasure11 = mealDto.getStrMeasure11();
            planDetailsModel.strMeasure12 = mealDto.getStrMeasure12();
            planDetailsModel.strMeasure13 = mealDto.getStrMeasure13();
            planDetailsModel.strMeasure14 = mealDto.getStrMeasure14();
            planDetailsModel.strMeasure15 = mealDto.getStrMeasure15();
            planDetailsModel.strMeasure16 = mealDto.getStrMeasure16();
            planDetailsModel.strMeasure17 = mealDto.getStrMeasure17();
            planDetailsModel.strMeasure18 = mealDto.getStrMeasure18();
            planDetailsModel.strMeasure19 = mealDto.getStrMeasure19();
            planDetailsModel.strMeasure20 = mealDto.getStrMeasure20();
            planDetailsModel.id = date + "-" + mealDto.getIdMeal();
            return planDetailsModel;
        }

        public static MealsItem getMealFromMealPlanner(Planner planDetailsModel) {
            MealsItem mealDto = new MealsItem();
            mealDto.setIdMeal(planDetailsModel.idMeal);
            mealDto.setStrMeal(planDetailsModel.strMeal);
            mealDto.setStrCategory(planDetailsModel.strCategory);
            mealDto.setStrArea(planDetailsModel.strArea);
            mealDto.setStrInstructions(planDetailsModel.strInstructions);
            mealDto.setStrMealThumb(planDetailsModel.strMealThumb);
            mealDto.setStrTags(planDetailsModel.strTags);
            mealDto.setStrYoutube(planDetailsModel.strYoutube);
            mealDto.setStrIngredient1(planDetailsModel.strIngredient1);
            mealDto.setStrIngredient2(planDetailsModel.strIngredient2);
            mealDto.setStrIngredient3(planDetailsModel.strIngredient3);
            mealDto.setStrIngredient4(planDetailsModel.strIngredient4);
            mealDto.setStrIngredient5(planDetailsModel.strIngredient5);
            mealDto.setStrIngredient6(planDetailsModel.strIngredient6);
            mealDto.setStrIngredient7(planDetailsModel.strIngredient7);
            mealDto.setStrIngredient8(planDetailsModel.strIngredient8);
            mealDto.setStrIngredient9(planDetailsModel.strIngredient9);
            mealDto.setStrIngredient10(planDetailsModel.strIngredient10);
            mealDto.setStrIngredient11(planDetailsModel.strIngredient11);
            mealDto.setStrIngredient12(planDetailsModel.strIngredient12);
            mealDto.setStrIngredient13(planDetailsModel.strIngredient13);
            mealDto.setStrIngredient14(planDetailsModel.strIngredient14);
            mealDto.setStrIngredient15(planDetailsModel.strIngredient15);
            mealDto.setStrIngredient16(planDetailsModel.strIngredient16);
            mealDto.setStrIngredient17(planDetailsModel.strIngredient17);
            mealDto.setStrIngredient18(planDetailsModel.strIngredient18);
            mealDto.setStrIngredient19(planDetailsModel.strIngredient19);
            mealDto.setStrIngredient20(planDetailsModel.strIngredient20);
            mealDto.setStrMeasure1(planDetailsModel.strMeasure1);
            mealDto.setStrMeasure2(planDetailsModel.strMeasure2);
            mealDto.setStrMeasure3(planDetailsModel.strMeasure3);
            mealDto.setStrMeasure4(planDetailsModel.strMeasure4);
            mealDto.setStrMeasure5(planDetailsModel.strMeasure5);
            mealDto.setStrMeasure6(planDetailsModel.strMeasure6);
            mealDto.setStrMeasure7(planDetailsModel.strMeasure7);
            mealDto.setStrMeasure8(planDetailsModel.strMeasure8);
            mealDto.setStrMeasure9(planDetailsModel.strMeasure9);
            mealDto.setStrMeasure10(planDetailsModel.strMeasure10);
            mealDto.setStrMeasure11(planDetailsModel.strMeasure11);
            mealDto.setStrMeasure12(planDetailsModel.strMeasure12);
            mealDto.setStrMeasure13(planDetailsModel.strMeasure13);
            mealDto.setStrMeasure14(planDetailsModel.strMeasure14);
            mealDto.setStrMeasure15(planDetailsModel.strMeasure15);
            mealDto.setStrMeasure16(planDetailsModel.strMeasure16);
            mealDto.setStrMeasure17(planDetailsModel.strMeasure17);
            mealDto.setStrMeasure18(planDetailsModel.strMeasure18);
            mealDto.setStrMeasure19(planDetailsModel.strMeasure19);
            mealDto.setStrMeasure20(planDetailsModel.strMeasure20);
            return mealDto;
        }

    }


