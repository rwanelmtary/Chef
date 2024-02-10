package com.example.mealplanner.home.view;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mealplanner.R;
import com.example.mealplanner.filter.view.presenter.FilterPresenter;
import com.example.mealplanner.home.presenter.HomePresenter;
import com.example.mealplanner.home.presenter.HomePresenterImp;
import com.example.mealplanner.model.AreaResponse;
import com.example.mealplanner.model.CategoriesItem;
import com.example.mealplanner.model.CountryResponse;
import com.example.mealplanner.model.Details;
import com.example.mealplanner.model.MealIngredients;
import com.example.mealplanner.model.MealsItem;
import com.example.mealplanner.model.MealsRepo;
import com.example.mealplanner.model.MealsRepoImp;
import com.example.mealplanner.model.MealsResponse;
import com.example.mealplanner.model.AllCategoryResponse;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements HomeView,AllMealsListener ,OnCountryClickListener,OnCategoryClickListener{
    HomePresenter homePresenter;
    MealsRepo mealsRepo;
    ImageView imageView;
    TextView mealCategoryTextView;
    TextView mealAreaTextView;

    HomeAdapter homeAdapter;

    CountryAdapter countryAdapter;
    Button favButton;
    MealsItem MealsItem;

    View view;
    CardView randomCardView;
    AllMealsListener allMealsListener;
    FilterPresenter filterPresenter;
    String mealid;
    com.example.mealplanner.model.MealsItem details;




    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mealsRepo = MealsRepoImp.getInstance(getContext());
        filterPresenter = new FilterPresenter() {
            @Override
            public void getFilterdMeals(String category, char c) {


            }

            @Override
            public void onFilterdMealsReceived(MealsResponse mealsResponse) {

            }
        }; // Initialize filterPresenter


        homePresenter = new HomePresenterImp(mealsRepo, this);
        homePresenter.getRandomMeal();
        homePresenter.getCategories();
        homePresenter.getCountries();
      //  homePresenter.getIngredients();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_home, container, false);

        // Inflate the layout for this fragment
        imageView = view.findViewById(R.id.imageView);
        mealCategoryTextView = view.findViewById(R.id.mealname);
        mealAreaTextView = view.findViewById(R.id.mealcountry);
        randomCardView = view.findViewById(R.id.randomcardView);
        randomCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if mealId is not null
                if (mealid != null) {
                    // Navigate to the details fragment and pass the mealId as an argument
                    HomeFragmentDirections.ActionHomeFragmentToDetailsFragment2 action =
                            HomeFragmentDirections.actionHomeFragmentToDetailsFragment2(details,mealid);
                    Navigation.findNavController(requireView()).navigate(action);
                } else {
                    // Handle the case where mealId is null
                    String id = MealsItem.getIdMeal();
                    homePresenter.getDetaildMeal(id);
                    Log.e(TAG, "onClick: MealId is null");
                }
            }
        });


        //countrt recycler
        // Initialize and set the adapter
        RecyclerView recyclerViewCountry =  view.findViewById(R.id.countryrecycler);
        LinearLayoutManager layoutManagerCountry = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCountry.setLayoutManager(layoutManagerCountry);
        countryAdapter = new CountryAdapter(getContext(), new ArrayList<>(), this); // Pass 'this' as the listener
        recyclerViewCountry.setAdapter(countryAdapter);

         //category recycler
        RecyclerView recyclerView = view.findViewById(R.id.categoryrecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Initialize homeAdapter
        homeAdapter = new HomeAdapter(getContext(), new ArrayList<>(),this);
        recyclerView.setAdapter(homeAdapter); // Set the adapter to the RecyclerView

        favButton = view.findViewById(R.id.favourite);
        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MealsItem != null) {
                    allMealsListener.onMealClick(MealsItem);
                    Log.i(TAG, "onClick: added to favourite");
                } else {
                    Log.e(TAG, "onClick: MealsItem is null");
                }
           }
});


        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.categoryrecycler);
        allMealsListener = this;
      //  RecyclerView countryRecycler = view.findViewById(R.id.countryrecycler);
     //   countryRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
       // countryRecycler.setLayoutManager(layoutManager);

    }

    @Override
    public void showMeal(MealsResponse meals) {
        if (!meals.getMeals().isEmpty()) {
            MealsItem meal = meals.getMeals().get(0);
            String mealCategory = meal.getStrCategory();
            String mealArea = meal.getStrArea();

            // Set the text of TextViews with meal category and area
            mealCategoryTextView.setText(mealCategory);
            mealAreaTextView.setText(mealArea);

            // Load image using Glide
            Glide.with(this)
                    .load(meal.getStrMealThumb())
                    .into(imageView);

            // Set MealsItem here
            MealsItem = meal;
        } else {
            // Handle the case where there are no meals available
            Log.e(TAG, "showMeal: No meals available");
        }
    }



    // HomeFragment.java
    @Override
    public void showAllMeals(AllCategoryResponse allmeals) {
        if (allmeals != null && allmeals.getCategories() != null && !allmeals.getCategories().isEmpty()) {
            // Check if the list of meals is not empty before accessing its elements
            homeAdapter.setList(allmeals.getCategories());
            homeAdapter.notifyDataSetChanged();
            CategoriesItem categoriesItem = allmeals.getCategories().get(0); // Change the index to access the first item or handle multiple items appropriately
            if (categoriesItem != null) {
                Log.i(TAG, "showAllMeals: " + categoriesItem.getStrCategory());

            }
        } else {
            Log.e(TAG, "showAllMeals: Category response is null or empty");
        }
//
    }

    @Override
    public void showCountries(AreaResponse areaResponse) {
        if (areaResponse != null && areaResponse.getCountries() != null && !areaResponse.getCountries().isEmpty()){
            CountryResponse countryResponse = areaResponse.getCountries().get(0);

            countryAdapter.setList(areaResponse.getCountries());
            countryAdapter.notifyDataSetChanged();
            if (areaResponse != null){
                Log.i(TAG, "showCountries: "+areaResponse.getCountries());
            }
        } else {
            Log.e(TAG, "showCountries: is empty" );
        }
    }

    @Override
    public void showFilterMeal(MealsResponse mealsResponse) {
        if (mealsResponse != null && mealsResponse.getMeals() != null && !mealsResponse.getMeals().isEmpty()) {
            Log.i(TAG, "showFilterMeal: " + mealsResponse.getMeals().get(0).getStrArea());
            HomeFragmentDirections.ActionHomeFragmentToFilterFragment action =
                    HomeFragmentDirections.actionHomeFragmentToFilterFragment(mealsResponse);
            Navigation.findNavController(requireView()).navigate(action);
        }else {
            Log.e(TAG, "showFilterd: is empty" );
        }

    }

    @Override
    public void showDetails(Details details, String id) {
        if (details != null && details.getMeals() != null && !details.getMeals().isEmpty()) {
            Log.i(TAG, "showDetails: " + details.getMeals().get(0).getStrArea());
            // Set the details variable here
            this.details = details.getMeals().get(0);
            // Set mealid
            this.mealid = id;

            // Navigate to DetailsFragment
            HomeFragmentDirections.ActionHomeFragmentToDetailsFragment2 action =
                    HomeFragmentDirections.actionHomeFragmentToDetailsFragment2(this.details, this.mealid);
            Navigation.findNavController(requireView()).navigate(action);
        } else {
            Log.e(TAG, "showDetails: Details response is null or empty");
        }
    }






    @Override
    public void showIngredients(MealIngredients mealIngredients) {
        if (mealIngredients != null && mealIngredients.getIngredients() != null && !mealIngredients.getIngredients().isEmpty()){
           // ItemIngredients itemIngredients = mealIngredients.getIngredients().get(1);


            if (mealIngredients != null){
                Log.i(TAG, "showIngridents: "+mealIngredients.getIngredients().get(0));
            }
        } else {
            Log.e(TAG, "showInhgridents: is empty" );
        }
    }


    @Override
    public void showError (String errormsg){
        Log.i(TAG, "showError: " + errormsg);

    }

    @Override
    public void onMealClick(MealsItem mealsItem) {
        addMeal(mealsItem);

    }
    public void addMeal(MealsItem mealsItem){
        homePresenter.addToFav(mealsItem);

    }

    @Override
    public void onCategoryClick(String category) {
        homePresenter.getFilterMeal(category,'c');

    }

    @Override
    public void onClickCountry(String country) {
        homePresenter.getFilterMeal(country,'a');
    }
}
