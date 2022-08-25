package com.kozyrev.myfirstapplication.screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.kozyrev.myfirstapplication.R;
import com.kozyrev.myfirstapplication.models.ApiType;
import com.kozyrev.myfirstapplication.models.WeatherForecastModel;
import com.kozyrev.myfirstapplication.models.WeatherModel;
import com.kozyrev.myfirstapplication.networking.WeatherApi;
import com.kozyrev.myfirstapplication.networking.WeatherService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    WeatherModel weather = null;
    WeatherForecastModel forecast = null;
    Gson gson = new Gson();

    Executor threadPool = Executors.newFixedThreadPool(5);


    private View.OnClickListener button2Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // implicit intent
            Intent intent2 = new Intent(Intent.ACTION_VIEW);
            // explicit intent
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("mykey1", "mike");
            //bundle.putSerializable("weather", weather);
            bundle.putSerializable("WeatherForecastModel", forecast);
            intent.putExtras(bundle);
            MainActivity.this.startActivity(intent2);
        }
    };


    private View.OnClickListener button1Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            threadPool.execute(() -> {
//                    String currentForecast = MainActivity.this.loadFromLegacyApi(urlForecast);
//                    forecast = gson.fromJson(currentForecast, WeatherForecastModel.class);
                forecast = loadForecastRetrofit();
                Log.i("mytag", "current forecast " + forecast.getList());


                // String currentWeather = MainActivity.this.loadFromLegacyApi();

                // weather = gson.fromJson(currentWeather, WeatherModel.class);
                // Log.i("mytag", "current weather, temp =" + weather.getMain().getTemp());
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {// to celcius
                        //textView.setText(""+ weather);
                        textView.setText(""+ forecast);

                    }
                });
            });

            Thread t = new Thread(() -> {

                // do work
            });
            t.start();
        }
    };



    private View.OnClickListener button3Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    //String currentForecast = MainActivity.this.loadFromLegacyApi(urlWeather);
                    //weather = gson.fromJson(currentForecast, WeatherModel.class);

                    weather = loadWeatherRetrofit();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(""+ weather);

                        }
                    });
                }
            });

            Thread t = new Thread(() -> {

                // do work
            });
            t.start();
        }
    };
    private TextView textView;
    WeatherService ws = new WeatherApi().createWeatherService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button_id);
        Button button2 = findViewById(R.id.button_id2);
        Button button3 = findViewById(R.id.button_id3);
        textView = findViewById(R.id.text_view_id);


        button3.setOnClickListener(button3Listener);
        //this.
        button2.setOnClickListener(button2Listener);

        //invokeMyMethod(this);
        button.setOnClickListener(button1Listener);

        View view = new TextView(this);
        View view2 = new TextView(this.getApplicationContext());
        Log.i("mytag", "my first line of code in on create");
    }

    private void invokeMyMethod(View.OnClickListener listener) {
        // do some work
       if (listener instanceof MainActivity) {
           MainActivity ma = (MainActivity) listener;
        }

    }

    private static final String urlWeather = "https://api.openweathermap.org/data/2.5/weather?lat=50.4501&lon=30.5234&appid=8aca3120b4fbe8b8b6dbeb584c7fc0e0";
    private static final String urlForecast = "https://api.openweathermap.org/data/2.5/forecast?lat=50.4501&lon=30.5234&appid=8aca3120b4fbe8b8b6dbeb584c7fc0e0";

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

    private WeatherForecastModel loadForecastRetrofit(){
        try {
            Response<WeatherForecastModel> response =  ws.getForecast("50.450", "30.5234", "8aca3120b4fbe8b8b6dbeb584c7fc0e0").execute();
            if (response.isSuccessful()){
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadFromLegacyApi(ApiType.weather);///????
        return null;
    }


    public String loadFromLegacyApi(ApiType apiType) {
        URL url;
        HttpURLConnection urlConnection;

        String server_response = null;
        try {
            if (apiType.equals(ApiType.weather)) {
                url = new URL(urlWeather);
            } else if (apiType.equals(ApiType.forecast)){
                url = new URL(urlForecast);
            } else {
                throw new RuntimeException("we don't know such api type");
            }

           // url = new URL(urlWeather);
            urlConnection = (HttpURLConnection) url.openConnection();

            int responseCode = urlConnection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                 server_response = readStream(urlConnection.getInputStream());
                Log.v("CatalogClient", server_response);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return server_response;
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void onClick(View v) {

    }
}