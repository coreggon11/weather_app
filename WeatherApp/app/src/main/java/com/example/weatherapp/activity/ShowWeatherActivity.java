package com.example.weatherapp.activity;

import android.os.Bundle;

import com.example.weatherapp.R;
import com.example.weatherapp.viewmodel.ShowWeatherViewModel;

public class ShowWeatherActivity extends WeatherHandlingActivity<ShowWeatherViewModel> {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(ShowWeatherViewModel.class, R.layout.activity_show_weather, R.id.tw_weather_info_show_weather, R.id.iv_weather_icon_show_weather);
    }

}
