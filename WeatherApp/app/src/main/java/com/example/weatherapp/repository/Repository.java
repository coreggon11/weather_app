package com.example.weatherapp.repository;

import android.app.Application;

import com.example.weatherapp.dao.Dao;
import com.example.weatherapp.database.Database;

public class Repository {

    private Dao dao;

    public Repository(Application application) {
        this.dao = Database.getInstance(application).getDao();
    }


}
