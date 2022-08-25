package com.kozyrev.myfirstapplication.models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class WeatherForecastModel implements Serializable {
    private String cod;
    private int message;
    private int cnt;
    private List<DayForecast> list;
    private CityForecast city;

    public WeatherForecastModel(String cod, int message, int cnt, List<DayForecast> list, CityForecast city) {
        this.cod = cod;
        this.message = message;
        this.cnt = cnt;
        this.list = list;
        //int iint = 5;
        this.city = city;
    }
    @NonNull
    @Override
    public String toString() {
        return "current forecast:"+ this.getList().get(0).getMain().getTemp() + "\nwind forecast:"+ this.getList().get(0).getWindForecast().getSpeed();
    }


    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<DayForecast> getList() {
        return list;
    }

    public void setList(List<DayForecast> dayForecast) {
        this.list = dayForecast;
    }

    public CityForecast getCity() {
        return city;
    }

    public void setCity(CityForecast city) {
        this.city = city;
    }
}
