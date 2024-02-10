package com.example.mealplanner.database;

import android.content.Context;
import android.media.Image;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mealplanner.model.MealsItem;
import com.example.mealplanner.model.Planner;

@Database(entities = {MealsItem.class, Planner.class},version = 1)

public abstract class MealsDataBase  extends RoomDatabase {
    private static  MealsDataBase instance = null;
    public abstract MealsDao getMealsDao();
    public static synchronized  MealsDataBase getInstance(Context context){
        if (instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(),MealsDataBase.class,"mealsdb").build();

        return instance;

    }

}
