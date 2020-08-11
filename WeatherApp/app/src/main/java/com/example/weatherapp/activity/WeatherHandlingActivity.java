package com.example.weatherapp.activity;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.example.weatherapp.R;
import com.example.weatherapp.java.WeatherInfo;
import com.example.weatherapp.viewmodel.WeatherHandlingViewModel;

public abstract class WeatherHandlingActivity<VM extends WeatherHandlingViewModel> extends BasicActivity<VM> {

    protected void init(Class<VM> viewModelClass, int layoutRes, int cityTvRes, int descRes, int tempRes, int weatherIconRes) {
        setContentView(layoutRes);

        viewModel = new ViewModelProvider(this).get(viewModelClass);
        TextView tvCityName = findViewById(cityTvRes);
        TextView tvDesc = findViewById(descRes);
        TextView tvTemp = findViewById(tempRes);
        ImageView weatherIcon = findViewById(weatherIconRes);

        viewModel.getWeatherInfo().observe(this, (WeatherInfo weatherInfo) -> {
            if (weatherInfo == null) {
                tvCityName.setText(R.string.no_result_found);
            } else {
                tvCityName.setText(weatherInfo.getCityName());
                tvDesc.setText(weatherInfo.getWeatherDesc());
                tvTemp.setText(String.format("%s°C", weatherInfo.getTemperature()));
            }
        });
        viewModel.getWeatherIcon().observe(this, weatherIcon::setImageBitmap);
    }

}
