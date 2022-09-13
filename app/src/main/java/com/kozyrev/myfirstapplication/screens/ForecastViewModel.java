package com.kozyrev.myfirstapplication.screens;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.kozyrev.myfirstapplication.models.Weather;
import com.kozyrev.myfirstapplication.models.WeatherForecastModel;
import com.kozyrev.myfirstapplication.networking.WeatherApi;
import com.kozyrev.myfirstapplication.networking.WeatherService;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Response;

public class ForecastViewModel extends ViewModel {
    private MutableLiveData<WeatherForecastModel> mutableForecast = null;
    Executor threadPool = Executors.newFixedThreadPool(5);
    WeatherService ws = new WeatherApi().createWeatherService();
    private Context context;

    public LiveData<WeatherForecastModel> getForecast() {
        if (mutableForecast == null) {
            mutableForecast = new MutableLiveData<>();
            loadWeather();
        }
        return mutableForecast;
    }

    private void loadWeather() {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                WeatherForecastModel forecast = loadWeatherRetrofit();
                mutableForecast.postValue(forecast);

            }
        });
    }

    private WeatherForecastModel loadWeatherRetrofit() {

        try {
            Response<WeatherForecastModel> response = ws.getForecast("50.450", "30.5234", "8aca3120b4fbe8b8b6dbeb584c7fc0e0").execute();
            if (response.isSuccessful()) {
                WeatherForecastModel weatherForecastModel = response.body();
                saveData(weatherForecastModel);
                return weatherForecastModel;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        WeatherForecastModel backupValue = readDataFromDisk();
        return backupValue;
    }

    void saveData(WeatherForecastModel weatherForecastModel){
        // shared preferences
        // file read write
        // sqlite database / room database
        SharedPreferences sp = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("weather-forecast-key", gson.toJson(weatherForecastModel));
        editor.apply();
    }

    WeatherForecastModel readDataFromDisk(){
        SharedPreferences sp = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        String weatherForecastString = sp.getString("weather-forecast-key", null);
        if (weatherForecastString != null) {
            WeatherForecastModel weatherForecastModel = gson.fromJson(weatherForecastString, WeatherForecastModel.class);
            return weatherForecastModel;
        }
        return null;
    }

    private Gson gson = new Gson();

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
