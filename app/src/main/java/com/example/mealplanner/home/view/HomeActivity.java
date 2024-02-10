package com.example.mealplanner.home.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.mealplanner.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        Bundle bundle = new Bundle();
//        bundle.putString("category", "some_category_value");
//        filterFragment.setArguments(bundle);

      //  NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment_home);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_home_main);
        bottomNavigationView = findViewById(R.id.nav_view);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);


    }
}