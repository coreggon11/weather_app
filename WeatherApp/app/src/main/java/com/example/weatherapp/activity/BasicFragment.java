package com.example.weatherapp.activity;

import androidx.fragment.app.Fragment;

import com.example.weatherapp.viewmodel.BasicViewModel;

/**
 * abstract fragment which conations view model for fragment
 *
 * @param <BVM> type parameter of viewmodel
 */
public abstract class BasicFragment<BVM extends BasicViewModel> extends Fragment {

    protected BVM viewModel;

}
