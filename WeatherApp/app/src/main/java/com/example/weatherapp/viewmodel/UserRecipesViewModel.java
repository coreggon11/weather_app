package com.example.weatherapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.java.LoginData;
import com.example.weatherapp.java.Recipe;
import com.example.weatherapp.repository.RecipeRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * view model for user recipes activity
 */
public class UserRecipesViewModel extends BasicViewModel<Recipe, RecipeRepository> {

    private MutableLiveData<String> recipesFilter;

    public UserRecipesViewModel(@NonNull Application application) {
        super(application, new RecipeRepository(application));
        recipesFilter = new MutableLiveData<>("%");
    }

    /**
     * @return list of recipes containing filter in title created by logged user
     */
    public List<Recipe> getFilteredRecipes(String newString) {
        recipesFilter.setValue("%" + newString + "%");
        List<Recipe> recipes = repository.getFilteredRecipes(LoginData.getLoggedUserId(), recipesFilter.getValue());
        return recipes == null ? new ArrayList<Recipe>() : recipes;
    }

}
