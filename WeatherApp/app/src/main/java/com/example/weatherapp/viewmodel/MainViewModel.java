package com.example.weatherapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.weatherapp.repository.Repository;

/**
 * view model for main activity
 */
public class MainViewModel extends BasicViewModel {

    public MainViewModel(@NonNull Application application) {
        super(application, new Repository(application));
    }

}
