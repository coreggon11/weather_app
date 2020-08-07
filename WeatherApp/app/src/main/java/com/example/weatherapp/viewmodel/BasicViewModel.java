package com.example.weatherapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.weatherapp.repository.Repository;

import lombok.Getter;

public class BasicViewModel<T> extends AndroidViewModel {

    @Getter
    protected Repository repository;

    protected BasicViewModel(@NonNull Application application, Repository repository) {
        super(application);
        this.repository = repository;
    }


}
