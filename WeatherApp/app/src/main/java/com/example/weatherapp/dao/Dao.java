package com.example.weatherapp.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.weatherapp.java.UserConfig;

@androidx.room.Dao
public interface Dao {

    @Insert
    void insert(UserConfig userConfig);

    @Update
    void update(UserConfig userConfig);

    @Delete
    void delete(UserConfig userConfig);

    @Query("SELECT * FROM config WHERE configId = :configId")
    UserConfig getUserConfig(int configId);

    @Query("SELECT defaultCity FROM config WHERE configId = :configId")
    String getDefaultCity(int configId);

}
