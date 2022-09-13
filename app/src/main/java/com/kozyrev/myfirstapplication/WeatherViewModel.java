package com.kozyrev.myfirstapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kozyrev.myfirstapplication.models.WeatherModel;
import com.kozyrev.myfirstapplication.networking.WeatherApi;
import com.kozyrev.myfirstapplication.networking.WeatherService;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Response;

public class WeatherViewModel extends ViewModel {
    private MutableLiveData<WeatherModel> mutableWeather = null;
    Executor threadPool = Executors.newFixedThreadPool(5);
    WeatherService ws = new WeatherApi().createWeatherService();
    public LiveData<WeatherModel> getWeather() {
        if (mutableWeather == null) {
            mutableWeather = new MutableLiveData<>();
            loadWeather();
        }
        return mutableWeather;
    }

    private void loadWeather() {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                //String currentForecast = MainActivity.this.loadFromLegacyApi(urlWeather);
                //weather = gson.fromJson(currentForecast, WeatherModel.class);

                WeatherModel weather = loadWeatherRetrofit();
                mutableWeather.postValue(weather);

            }
        });
    }

    private WeatherModel loadWeatherRetrofit(){

        try {
            Response<WeatherModel> response =  ws.getWeather("50.450", "30.5234", "8aca3120b4fbe8b8b6dbeb584c7fc0e0").execute();
            if (response.isSuccessful()){
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
