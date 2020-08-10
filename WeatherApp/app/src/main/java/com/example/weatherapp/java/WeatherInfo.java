package com.example.weatherapp.java;

import lombok.Value;

@Value
public class WeatherInfo {
    String cityName;
    double temperature;
    String weatherDesc;
}
