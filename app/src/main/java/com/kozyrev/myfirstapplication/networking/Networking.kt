package com.kozyrev.myfirstapplication.networking

import com.kozyrev.myfirstapplication.models.WeatherForecastModel
import com.kozyrev.myfirstapplication.models.WeatherModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {
    @GET("data/2.5/weather")
    fun getWeather(
        @Query("lat")  latitude: String,
        @Query("lon")  longitude : String,
        @Query("appid")  appid : String
    ): Call<WeatherModel>

    @GET("data/2.5/forecast")
    fun getForecast(
                    @Query("lat")  latitude: String,
                    @Query("lon")  longitude : String,
                    @Query("appid")  appid : String): Call<WeatherForecastModel>
}

class WeatherApi {
    fun createWeatherService(): WeatherService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(WeatherService::class.java)
    }
}
