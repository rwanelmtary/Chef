package com.example.mealplanner.search.view;

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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mealplanner.R;
import com.example.mealplanner.home.presenter.HomePresenter;
import com.example.mealplanner.home.view.HomeAdapter;
import com.example.mealplanner.model.MealsItem;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {


        private static final String TAG = "SearchedMealsAdapter";
        private List<MealsItem> allMeals;
        private Context context;
        private SearchClickListener searchClickListener;
    com.example.mealplanner.model.MealsItem details;
    MealsItem mealsItem;
    HomePresenter homePresenter;


    public SearchAdapter(Context context, SearchClickListener searchClickListener,HomePresenter homePresenter) {
        super();
        this.context = context;
        this.searchClickListener = searchClickListener;
        this.homePresenter = homePresenter;
    }

        public void setAllMeals (List < MealsItem > allMeals) {
        this.allMeals = allMeals;
        notifyDataSetChanged();
    }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent,int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.searchcell, parent, false);
        Log.i("onCreateViewHolder: ", viewType + "");
        return new ViewHolder(view);
    }

        @Override
        public void onBindViewHolder (@NonNull ViewHolder holder,int position){
        MealsItem meal = allMeals.get(position);
        holder.mealSearchedNameCardTextView.setText(allMeals.get(position).getStrMeal());
        holder.categoryTextview.setText(allMeals.get(position).getStrCategory());
        Glide.with(context).load(allMeals.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(200, 160))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.mealSearchedCardImageView);
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



        }

        @Override
        public int getItemCount () {
        return allMeals.size();
    }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView categoryTextview, mealSearchedNameCardTextView;
            ImageView mealSearchedCardImageView;
            ConstraintLayout searchCardConstraintLayout;
            CardView cardView;

            public ViewHolder(View itemView) {
                super(itemView);
                categoryTextview = itemView.findViewById(R.id.categorySearchCard);
                mealSearchedCardImageView = itemView.findViewById(R.id.mealSearchedCardImageView);
                mealSearchedNameCardTextView = itemView.findViewById(R.id.mealSearchedNameCardTextView);
                searchCardConstraintLayout = itemView.findViewById(R.id.searchCardConstraintLayout);
                cardView = itemView.findViewById(R.id.searchcardview);


            }
        }
    }


