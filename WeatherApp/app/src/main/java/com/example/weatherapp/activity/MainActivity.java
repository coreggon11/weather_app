package com.example.weatherapp.activity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.example.weatherapp.R;
import com.example.weatherapp.java.WeatherInfo;
import com.example.weatherapp.viewmodel.MainViewModel;

import java.util.Objects;

public class MainActivity extends BasicActivity<MainViewModel> {

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.search_action_bar);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        TextView weatherInfoTextView = findViewById(R.id.tw_weather_info);
        ImageView weatherIcon = findViewById(R.id.iv_weather_icon);
        viewModel.getWeatherInfo().observe(this, (WeatherInfo weatherInfo) -> {
            // TODO on response
            weatherInfoTextView.setText(weatherInfo.getCityName() + "," + weatherInfo.getWeatherDesc() + ", " + weatherInfo.getTemperature() + "°C");
        });
        viewModel.getWeatherIcon().observe(this, weatherIcon::setImageBitmap);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.button_search) {
            Toast.makeText(getApplicationContext(), "Search", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
