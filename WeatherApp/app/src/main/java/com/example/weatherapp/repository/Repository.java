package com.example.weatherapp.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.weatherapp.dao.Dao;
import com.example.weatherapp.database.Database;
import com.example.weatherapp.java.UserConfig;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import lombok.AllArgsConstructor;

public class Repository {

    private Dao dao;

    public Repository(Application application) {
        this.dao = Database.getInstance(application).getDao();
    }

    public String getDefaultCity() {
        final AtomicReference<String> cityNameReference = new AtomicReference<>();
        final AtomicBoolean mutex = new AtomicBoolean(false);
        new HandleObjectAsyncTask<String>(() -> {
            String cityName = dao.getDefaultCity(UserConfig.CONFIG_ID);
            cityNameReference.set(cityName);
            mutex.set(true);
        }).execute();
        while (!mutex.get()) ;
        return cityNameReference.get();
    }

    public void setDefaultCity(String cityName) {
        final AtomicBoolean mutex = new AtomicBoolean(false);
        new HandleObjectAsyncTask<String>(() -> {
            dao.update(new UserConfig(UserConfig.CONFIG_ID, cityName));
            mutex.set(true);
        }).execute();
    }

    @AllArgsConstructor
    protected static class HandleObjectAsyncTask<T> extends AsyncTask<T, Void, Void> {
        private Runnable task;

        @SafeVarargs
        @Override
        protected final Void doInBackground(T... ts) {
            task.run();
            return null;
        }
    }

}
