package com.example.weatherapp.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.weatherapp.dao.RecipeDao;
import com.example.weatherapp.dao.UserDao;
import com.example.weatherapp.java.Recipe;
import com.example.weatherapp.java.RecipeComment;
import com.example.weatherapp.java.User;

@androidx.room.Database(entities = {User.class, Recipe.class, RecipeComment.class}, version = 1, exportSchema = false)
/**
 * database class which contains an instance of database and with which we can access the dao
 */
public abstract class Database extends RoomDatabase {

    private static Database instance;

    /**
     * method to get the instance of database. if instance is null we create new
     *
     * @param context apllication context
     * @return the database instance
     */
    public static synchronized Database getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), Database.class, "weather_app")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract UserDao userDao();

    public abstract RecipeDao recipeDao();

}
