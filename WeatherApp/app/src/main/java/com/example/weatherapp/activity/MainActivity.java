package com.example.weatherapp.activity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.weatherapp.R;
import com.example.weatherapp.viewmodel.MainViewModel;

import java.util.Objects;

public class MainActivity extends WeatherHandlingActivity<MainViewModel> {

    public static final String EXTRA_CITY_NAME = "EXTRA_CITY_NAME";

    private EditText searchText;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(MainViewModel.class, R.layout.activity_main, R.id.tw_weather_info, R.id.iv_weather_icon);

        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.search_action_bar);

        searchText = findViewById(R.id.et_search_city);
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
            Intent intent = new Intent(getApplicationContext(), ShowWeatherActivity.class)
                    .putExtra(EXTRA_CITY_NAME, searchText.getText().toString());
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.searchCity();
    }
}
