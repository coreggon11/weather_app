package com.example.weatherapp.activity;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.example.weatherapp.R;
import com.example.weatherapp.java.WeatherInfo;
import com.example.weatherapp.viewmodel.WeatherHandlingViewModel;

public abstract class WeatherHandlingActivity<VM extends WeatherHandlingViewModel> extends BasicActivity<VM> {

    protected void init(Class<VM> viewModelClass, int layoutRes, int weatherTextViewRes, int weatherIconRes) {
        setContentView(layoutRes);

        viewModel = new ViewModelProvider(this).get(viewModelClass);
        TextView weatherInfoTextView = findViewById(weatherTextViewRes);
        ImageView weatherIcon = findViewById(weatherIconRes);

        viewModel.getWeatherInfo().observe(this, (WeatherInfo weatherInfo) -> {
            if (weatherInfo == null) {
                weatherInfoTextView.setText(R.string.no_resulst_found);
            } else {
                weatherInfoTextView.setText(weatherInfo.getCityName() + "," + weatherInfo.getWeatherDesc() + ", " + weatherInfo.getTemperature() + "°C");
            }
        });
        viewModel.getWeatherIcon().observe(this, weatherIcon::setImageBitmap);
    }

}
