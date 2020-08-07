package com.example.weatherapp.java;

public class Constants {

    public static final String API_KEY = "6c5b345608698b4bab1083ef8e2cbe8d";
    public static final String API_CALL = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";

    public static String getApiCall(String city) {
        return String.format(API_CALL, city, API_KEY);
    }

}
