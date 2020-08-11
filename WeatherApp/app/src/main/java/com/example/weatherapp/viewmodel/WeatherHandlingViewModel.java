package com.example.weatherapp.viewmodel;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.java.Constants;
import com.example.weatherapp.java.WeatherInfo;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import lombok.Getter;
import lombok.SneakyThrows;

@Getter
public abstract class WeatherHandlingViewModel extends AndroidViewModel {

    private MutableLiveData<WeatherInfo> weatherInfo;
    private MutableLiveData<Bitmap> weatherIcon;

    WeatherHandlingViewModel(@NonNull Application application) {
        super(application);
        weatherInfo = new MutableLiveData<>();
        weatherIcon = new MutableLiveData<>();
    }

    public void searchCity(String city) {
        new GetTask(city, weatherInfo, weatherIcon).execute();
    }

    private static class GetTask extends AsyncTask<Void, Void, JSONObject> {
        private String cityName;
        private MutableLiveData<WeatherInfo> liveData;
        private MutableLiveData<Bitmap> weatherIcon;

        GetTask(String cityName, MutableLiveData<WeatherInfo> liveData, MutableLiveData<Bitmap> weatherIcon) {
            super();
            this.cityName = cityName;
            this.liveData = liveData;
            this.weatherIcon = weatherIcon;
        }

        @Override
        protected JSONObject doInBackground(Void... params) {

            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(
                        new InputStreamReader(new URL(Constants.getApiCall(cityName)).openConnection().getInputStream()));
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    builder.append(line);
                }
                return new JSONObject(builder.toString());
            } catch (Exception e) {
                Log.e("", "", e);
                return null;
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @SneakyThrows
        @Override
        protected void onPostExecute(JSONObject response) {
            if (response == null) {
                liveData.postValue(null);
                weatherIcon.postValue(null);
            } else {
                String cityName = response.getString("name");
                JSONObject main = response.getJSONObject("main");
                JSONObject weather = response.getJSONArray("weather").getJSONObject(0);
                double temperature = main.getDouble("temp");
                String weatherDesc = weather.getString("description");
                String weatherIconUrl = "http://openweathermap.org/img/wn/" + weather.getString("icon") + "@2x.png";
                WeatherInfo weatherInfo = new WeatherInfo(cityName, temperature, weatherDesc);
                new DownloadImageTask(weatherIcon).execute(weatherIconUrl);
                liveData.postValue(weatherInfo);
            }
        }
    }

    private static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        private MutableLiveData<Bitmap> weatherIcon;

        DownloadImageTask(MutableLiveData<Bitmap> weatherIcon) {
            this.weatherIcon = weatherIcon;
        }

        protected Bitmap doInBackground(String... strings) {
            Bitmap icon = null;
            try {
                icon = BitmapFactory.decodeStream(new URL(strings[0]).openStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return icon;
        }

        protected void onPostExecute(Bitmap result) {
            weatherIcon.postValue(result);
        }
    }

}
