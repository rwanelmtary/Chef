package com.example.mealplanner.favourite.view;

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
import com.example.mealplanner.model.MealsItem;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {
    private Context context;
    private List<MealsItem> mealsItems;
    private OnItemClickListener listener;

    public FavouriteAdapter(Context context, List<MealsItem> mealsItems) {
        this.context = context;
        this.mealsItems = mealsItems;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favouritecell, parent, false);
        return new ViewHolder(view);
    }

    public void setList(List<MealsItem> mealsItems) {
        this.mealsItems = mealsItems;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealsItem mealsItem = mealsItems.get(position);
        holder.getFavName().setText(mealsItem.getStrMeal());

        Glide.with(context).load(mealsItem.getStrMealThumb())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.getImageView());

        holder.removeBt.setOnClickListener(view -> {
            if (listener != null) {
                listener.onItemClick(mealsItem);
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
