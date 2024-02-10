package com.example.mealplanner.filter.view;

import static androidx.fragment.app.FragmentManager.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.helper.widget.MotionEffect;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealplanner.R;
import com.example.mealplanner.filter.view.presenter.FilterPresenter;
import com.example.mealplanner.filter.view.presenter.FilterPresenterImp;
import com.example.mealplanner.home.presenter.HomePresenter;
import com.example.mealplanner.home.presenter.HomePresenterImp;
import com.example.mealplanner.home.view.HomeAdapter;
import com.example.mealplanner.home.view.HomeFragmentDirections;
import com.example.mealplanner.home.view.HomeView;
import com.example.mealplanner.home.view.OnCategoryClickListener;
import com.example.mealplanner.home.view.OnCountryClickListener;
import com.example.mealplanner.model.AllCategoryResponse;
import com.example.mealplanner.model.AreaResponse;
import com.example.mealplanner.model.CategoriesItem;
import com.example.mealplanner.model.Details;
import com.example.mealplanner.model.MealIngredients;
import com.example.mealplanner.model.MealsItem;
import com.example.mealplanner.model.MealsRepo;
import com.example.mealplanner.model.MealsRepoImp;
import com.example.mealplanner.model.MealsResponse;

import java.util.ArrayList;
import java.util.List;


public class filterFragment extends Fragment implements FilterView ,HomeView{
    MealsRepo mealsRepo;
    FilterPresenter filterPresenter;
    FilterAdapter filterAdapter;
    private View rootView; // Add a member variable to store the root view
    String mealid;
    com.example.mealplanner.model.MealsItem details;
    MealsItem mealsItem;
    HomePresenter homePresenter;




    public filterFragment() {
        // Required empty public constructor
    }






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mealsRepo = MealsRepoImp.getInstance(getContext());
        filterPresenter = new FilterPresenterImp(mealsRepo,this);







    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_filter, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerfilter);

        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        filterFragmentArgs args = filterFragmentArgs.fromBundle(getArguments());
        MealsResponse mealsResponse = args.getMealResponse();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerfilter);
        recyclerView.setLayoutManager(layoutManager);



        // Pass the context to the adapter
// Create FilterAdapter instance with the filtered meals list, context, and homePresenter
         homePresenter = new HomePresenterImp(mealsRepo,this);
        filterAdapter = new FilterAdapter(mealsResponse.getMeals(), getContext(),homePresenter);
        recyclerView.setAdapter(filterAdapter);

        filterAdapter.notifyDataSetChanged();
    }


    @Override
    public void showFilterdMeals(MealsResponse mealsResponse) {

      filterAdapter.setList(mealsResponse.getMeals());
//
//        // Notify the adapter about the data change
      filterAdapter.notifyDataSetChanged();



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
    public void showDetails(Details details, String id) {
        if (details != null && details.getMeals() != null && !details.getMeals().isEmpty()) {
            Log.i(MotionEffect.TAG, "showDetails: " + details.getMeals().get(0).getStrArea());
            // Set the details variable here
            this.details = details.getMeals().get(0);
            // Set mealid
            this.mealid = id;

            // Navigate to DetailsFragment
            filterFragmentDirections.ActionFilterFragmentToDetailsFragment2 action =
                   filterFragmentDirections.actionFilterFragmentToDetailsFragment2(this.details, this.mealid);
            Navigation.findNavController(requireView()).navigate(action);
        } else {
            Log.e(MotionEffect.TAG, "showDetails: Details response is null or empty");
        }

    }

    @Override
    public void showIngredients(MealIngredients mealIngredients) {

    }

    @Override
    public void showError(String errormsg) {

    }
}