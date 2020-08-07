package com.example.weatherapp.java;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "config")
public class UserConfig {
    public static final int CONFIG_ID = 1;

    public static final String API_KEY = "6c5b345608698b4bab1083ef8e2cbe8d";

    @PrimaryKey
    private int configId;

    private String defaultCity;

    public UserConfig(String defaultCity) {
        this.defaultCity = defaultCity;
    }
}
