package com.example.mealplanner.home.view;

import android.content.Context;
import android.os.Bundle;
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
import com.example.mealplanner.filter.view.filterFragment;
import com.example.mealplanner.model.CategoriesItem;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private Context context;
    private List<CategoriesItem>categoriesItems;
    private OnCategoryClickListener listener;

    public HomeAdapter(Context context, List<CategoriesItem> categoriesItems, OnCategoryClickListener listener) {
        this.context = context;
        this.categoriesItems = categoriesItems;
        this.listener = listener;
    }



    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.allcategorycell,parent,false);
        return new ViewHolder(view);
    }
    public void setList(List<CategoriesItem>categoriesItems){
        this.categoriesItems = categoriesItems;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        String categoriesItem = categoriesItems.get(position).getStrCategory();
        holder.name.setText(categoriesItems.get(position).getStrCategory());


        Glide.with(context).load(categoriesItems.get(position).getStrCategoryThumb())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.getImageView());
        holder.cardView .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCategoryClick( categoriesItem);

            }
        });



        }



    @Override
    public int getItemCount() {
        return categoriesItems.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;

        private ImageView imageView;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            cardView = itemView.findViewById(R.id.categoryCardView);

            imageView = itemView.findViewById(R.id.imageView3);

        }
        public TextView getName(){
            return name;
        }
        public ImageView getImageView(){
            return imageView;
        }
    }
}
