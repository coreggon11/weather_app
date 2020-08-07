package com.example.weatherapp.viewmodel;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.java.Constants;
import com.example.weatherapp.repository.Repository;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import lombok.Getter;

/**
 * view model for main activity
 */
@Getter
public class MainViewModel extends AndroidViewModel {

    protected Repository repository;
    private MutableLiveData<JSONObject> jsonResponse;

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
        jsonResponse = new MutableLiveData<>();
        searchCity();
    }

    public void searchCity() {
        new GetTask(repository.getDefaultCity(), jsonResponse).execute();
    }

    private static class GetTask extends AsyncTask<Void, Void, JSONObject> {
        private String cityName;
        private MutableLiveData<JSONObject> liveData;

        GetTask(String cityName, MutableLiveData<JSONObject> liveData) {
            super();
            this.cityName = cityName;
            this.liveData = liveData;
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

        @Override
        protected void onPostExecute(JSONObject response) {
            liveData.postValue(response);
        }
    }

}
