package com.example.weatherapp.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp.viewmodel.BasicViewModel;

/**
 * abstract activity which conations view model for activity
 *
 * @param <BVM> type parameter of viewmodel
 */
public abstract class BasicActivity<BVM extends BasicViewModel> extends AppCompatActivity {

    protected BVM viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
