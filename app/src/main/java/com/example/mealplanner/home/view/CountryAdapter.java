package com.example.mealplanner.home.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;


import com.example.mealplanner.model.AreaResponse;
import com.example.mealplanner.model.CountriesResponse;
import com.example.mealplanner.model.CountryResponse;
import com.example.mealplanner.model.MealsItem;

import java.util.Arrays;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder>{

    private Context context;
    private List<CountryResponse> countryResponseList;
    private OnCountryClickListener onClickCountrylistener; // Declare the listener

    public CountryAdapter(Context context, List<CountryResponse> countryResponses, OnCountryClickListener onClickCountrylistener) {
        this.context = context;
        this.countryResponseList = countryResponses;
        this.onClickCountrylistener = onClickCountrylistener; // Initialize the listener
    }
    public void setList(List<CountryResponse>countryResponseList){
        this.countryResponseList = countryResponseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.countrycell,parent,false);
        return new CountryAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String countryResponse = countryResponseList.get(position).getStrArea();
        holder.name.setText(countryResponseList.get(position).getStrArea());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCountrylistener.onClickCountry(countryResponse);
            }
        });


    }

    @Override
    public int getItemCount() {
        return countryResponseList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;

        private ImageView imageView;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.countryname);
            cardView = itemView.findViewById(R.id.countrycardview);

         //   imageView = itemView.findViewById(R.id.imageView3);

        }
        public TextView getName(){
            return name;
        }
//        public ImageView getImageView(){
//            return imageView;
//        }
    }
}
