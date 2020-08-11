package com.example.weatherapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

public class ShowWeatherViewModel extends WeatherHandlingViewModel {

    public ShowWeatherViewModel(@NonNull Application application) {
        super(application);
    }

    public void setAsDefault(String city) {
        repository.setDefaultCity(city);
    }

}
