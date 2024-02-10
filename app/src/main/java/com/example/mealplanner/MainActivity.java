package com.example.mealplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.mealplanner.Auth.AuthActivity;
import com.example.mealplanner.home.view.HomeFragment;

import org.checkerframework.checker.units.qual.Current;

public class MainActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragmentContainerView, new HomeFragment());
        transaction.commit();
        intent = new Intent(MainActivity.this, AuthActivity.class);
        startActivity(intent);

    }
}