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

    @PrimaryKey
    private int configId;

    private String defaultCity;

    public UserConfig(int configId, String defaultCity) {
        this.configId = configId;
        this.defaultCity = defaultCity;
    }
}
