package com.example.mealplanner.filter.view;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealplanner.R;
import com.example.mealplanner.home.presenter.HomePresenter;
import com.example.mealplanner.home.view.HomeFragmentDirections;
import com.example.mealplanner.model.MealsItem;

import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder> {
    private List<MealsItem> filteredMeals;
    private Context context;
    String mealid;
    com.example.mealplanner.model.MealsItem details;
    MealsItem mealsItem;
    HomePresenter homePresenter;

    public FilterAdapter(List<MealsItem> filteredMeals, Context context, HomePresenter homePresenter) {
        this.filteredMeals = filteredMeals;
        this.context = context;
        this.homePresenter = homePresenter; // Initialize homePresenter
    }


    public void setList(List<MealsItem> mealsItems) {
        this.filteredMeals = mealsItems; // Assign mealsItems to filteredMeals
    }





    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.filtercell, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealsItem meal = filteredMeals.get(position);
        holder.filtername.setText(meal.getStrMeal());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealsItem = meal; // Assign the clicked meal to mealsItem
                if (mealsItem != null) {
                    // If mealsItem is not null, use its ID
                    String id = mealsItem.getIdMeal();
                    homePresenter.getDetaildMeal(id);
                } else {
                    // Handle the case where mealsItem is null
                    Log.e(TAG, "onClick: mealsItem is null");
                }
            }
        });

        if (context != null) {
            Glide.with(context)
                    .load(meal.getStrMealThumb())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(holder.getImageView());
        }
    }

    @Override
    public int getItemCount() {
        return filteredMeals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView filtername;
        public ImageView imageView;
        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            filtername = itemView.findViewById(R.id.filtername);
            imageView = itemView.findViewById(R.id.imageView2);
            cardView = itemView.findViewById(R.id.filtercardview);


        }
        public ImageView getImageView(){
            return imageView;
        }
    }
}
