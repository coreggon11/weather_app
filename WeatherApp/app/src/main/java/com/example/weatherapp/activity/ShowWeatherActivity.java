package com.example.weatherapp.activity;

import android.os.Bundle;

import com.example.weatherapp.R;
import com.example.weatherapp.viewmodel.ShowWeatherViewModel;

import static com.example.weatherapp.activity.MainActivity.EXTRA_CITY_NAME;

public class ShowWeatherActivity extends WeatherHandlingActivity<ShowWeatherViewModel> {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(ShowWeatherViewModel.class, R.layout.activity_show_weather, R.id.tv_city_info_show_weather,
                R.id.tv_weather_info_show_weather, R.id.tv_temp_info_show_weather, R.id.iv_weather_icon_show_weather);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String cityName = getIntent().getStringExtra(EXTRA_CITY_NAME);

        viewModel.searchCity(cityName);
        setTitle(getString(R.string.weather_in, cityName));
    }

}
