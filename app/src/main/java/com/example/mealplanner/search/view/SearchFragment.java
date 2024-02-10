package com.example.mealplanner.search.view;

import static com.example.mealplanner.network.MealsRemoteDataSourceImp.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.helper.widget.MotionEffect;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.mealplanner.R;
import com.example.mealplanner.filter.view.filterFragmentDirections;
import com.example.mealplanner.home.presenter.HomePresenter;
import com.example.mealplanner.home.view.HomeView;
import com.example.mealplanner.model.AllCategoryResponse;
import com.example.mealplanner.model.AreaResponse;
import com.example.mealplanner.model.Details;
import com.example.mealplanner.model.MealIngredients;
import com.example.mealplanner.model.MealsItem;
import com.example.mealplanner.model.MealsRepo;
import com.example.mealplanner.model.MealsRepoImp;
import com.example.mealplanner.model.MealsResponse;
import com.example.mealplanner.model.Planner;
import com.example.mealplanner.search.presenter.SearchPresenter;
import com.example.mealplanner.search.presenter.SearchPresenterImp;

import java.util.List;


public class SearchFragment extends Fragment implements Searchview,SearchClickListener, HomeView {

SearchAdapter searchAdapter;
SearchPresenter searchPresenter;
EditText searchEditText;
RecyclerView recyclerView;
    String mealid;
    com.example.mealplanner.model.MealsItem details;
    MealsItem mealsItem;
    HomePresenter homePresenter;


    public SearchFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        searchEditText = view.findViewById(R.id.searchEditText);
         recyclerView = view.findViewById(R.id.searchResultRecyclerView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homePresenter = new HomePresenter() {
            @Override
            public void getRandomMeal() {

            }

            @Override
            public void getCategories() {

            }

            @Override
            public void getCountries() {

            }

            @Override
            public void getFilterMeal(String country, char c) {

            }

            @Override
            public void getDetaildMeal(Details details, String id) {

            }

            @Override
            public void addToFav(MealsItem mealsItem) {

            }

            @Override
            public void getIngredients() {

            }

            @Override
            public void addToPlan(Planner planner, String date) {

            }

            @Override
            public void getDetaildMeal(String id) {

            }
        };
       // recyclerView.setAdapter(searchAdapter);
        searchPresenter = new SearchPresenterImp(MealsRepoImp.getInstance(getContext()),this);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchPresenter.getSearchedMeal(s+"");

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void resultMeal(List<MealsItem> mealsItems) {
        // Ensure that homePresenter is not null before initializing the adapter
        if (homePresenter != null) {

            searchAdapter = new SearchAdapter(getContext(), SearchFragment.this, homePresenter);
            searchAdapter.setAllMeals(mealsItems);
            recyclerView.setAdapter(searchAdapter);

        } else {
            Log.e(TAG, "resultMeal: homePresenter is null");
        }
    }

    @Override
    public void showMeal(MealsResponse meals) {

    }

    @Override
    public void showAllMeals(AllCategoryResponse allmeals) {

    }

    @Override
    public void showCountries(AreaResponse areaResponse) {

    }

    @Override
    public void showFilterMeal(MealsResponse mealsResponse) {

    }

    @Override
    public void showDetails(Details details, String mealId) {
        if (details != null && details.getMeals() != null && !details.getMeals().isEmpty()) {
            Log.i(TAG, "showDetails: " + details.getMeals().get(0).getStrArea());
            // Set the details variable here
            this.details = details.getMeals().get(0);
            // Set mealid
            this.mealid = mealId;

            // Navigate to DetailsFragment
            SearchFragmentDirections.ActionSearchFragmentToDetailsFragment action =
                    SearchFragmentDirections.actionSearchFragmentToDetailsFragment(this.details, this.mealid);
            Navigation.findNavController(requireView()).navigate(action);
        } else {
            Log.e(TAG, "showDetails: Details response is null or empty");
        }
    }


    @Override
    public void showIngredients(MealIngredients mealIngredients) {

    }

    @Override
    public void showError(String errormsg) {

    }
}