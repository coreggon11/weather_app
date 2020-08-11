package com.example.weatherapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.weatherapp.repository.Repository;

/**
 * view model for main activity
 */
public class MainViewModel extends WeatherHandlingViewModel {

    private Repository repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
        searchCity();
    }

    private void searchCity() {
        searchCity(repository.getDefaultCity());
    }

}
