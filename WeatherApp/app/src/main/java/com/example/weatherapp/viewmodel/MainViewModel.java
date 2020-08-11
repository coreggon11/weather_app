package com.example.weatherapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

/**
 * view model for main activity
 */
public class MainViewModel extends WeatherHandlingViewModel {

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

}
