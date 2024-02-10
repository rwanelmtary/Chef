package com.example.mealplanner.favourite.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealplanner.R;
import com.example.mealplanner.favourite.presenter.FavouritePresenter;
import com.example.mealplanner.favourite.presenter.FavouritePresenterImp;
import com.example.mealplanner.home.view.HomeAdapter;
import com.example.mealplanner.model.MealsItem;
import com.example.mealplanner.model.MealsRepo;
import com.example.mealplanner.model.MealsRepoImp;
import com.example.mealplanner.network.MealsRemoteDataSourceImp;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class FavouriteFragment extends Fragment implements FavouriteView{
    private FavouritePresenter favouritePresenter;
    private MealsRepo mealsRepo;
    private FavouriteAdapter favouriteAdapter;




    public FavouriteFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mealsRepo = MealsRepoImp.getInstance(getContext());


        favouritePresenter = new FavouritePresenterImp(this,mealsRepo);
        favouritePresenter.getFavMeal();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite ,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.favouriterecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        favouriteAdapter = new FavouriteAdapter(getContext(), new ArrayList<>());
        recyclerView.setAdapter(favouriteAdapter);
        favouriteAdapter.setOnItemClickListener(item -> favouritePresenter.remove(item));


        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.favouriterecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void showFavMeals(Flowable<List<MealsItem>> mealsItemList) {
        mealsItemList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(productList -> {
                            favouriteAdapter.setList(productList);
                            favouriteAdapter.notifyDataSetChanged();
                        },
                        throwable -> {
                            Log.i("TAG", "showMeals: unable to show meals because: "+throwable.getMessage());
                        });

    }

    @Override
    public void deleteMeal(MealsItem mealsItem) {

    }
}