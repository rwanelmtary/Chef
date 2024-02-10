package com.example.mealplanner.planner.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealplanner.R;
import com.example.mealplanner.favourite.view.FavouriteAdapter;
import com.example.mealplanner.model.MealsItem;
import com.example.mealplanner.model.Planner;

import java.util.List;

public class PlannerAdapter extends RecyclerView.Adapter<PlannerAdapter.ViewHolder> {
        private Context context;
        private List<Planner> mealsItems;
        private FavouriteAdapter.OnItemClickListener listener;

    public PlannerAdapter(Context context, List<Planner> mealsItems) {
            this.context = context;
            this.mealsItems = mealsItems;
        }

        public void setOnItemClickListener(FavouriteAdapter.OnItemClickListener listener) {
            this.listener = listener;
        }

        @NonNull
        @Override
        public PlannerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.savedcell, parent, false);
            return new ViewHolder(view);
        }

        public void setList(List<Planner> mealsItems) {
            this.mealsItems = mealsItems;
            notifyDataSetChanged();
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Planner mealsItem = mealsItems.get(position);
            holder.getFavName().setText(mealsItem.idMeal);

            Glide.with(context).load(mealsItem.strMealThumb)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(holder.getImageView());

            holder.removeBt.setOnClickListener(view -> {
                if (listener != null) {
                    listener.onItemClick((MealsItem) mealsItems);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mealsItems.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView favName;
            Button removeBt;

            public ViewHolder(View itemView) {
                super(itemView);
                favName = itemView.findViewById(R.id.favname);
                imageView = itemView.findViewById(R.id.favImage);
                removeBt = itemView.findViewById(R.id.button);
            }

            public TextView getFavName() {
                return favName;
            }

            public ImageView getImageView() {
                return imageView;
            }
        }
            public interface OnItemClickListener {
                void onItemClick(MealsItem item);

        }

    }
