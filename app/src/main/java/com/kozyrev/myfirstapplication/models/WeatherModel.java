package com.kozyrev.myfirstapplication.models;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

/**
 * [WeatherModel] is a model class which is used to hold data from (weather api)[]
 * @see <a href="https://docs.oracle.com/en/java/">Java Dcoumentation</a>
 */
public class WeatherModel implements Serializable { // finish the weather model
    private MainWeatherModel main;
    private String name;
    private Coord coord;
    private List<Weather> weather;
    private Wind wind;
    private Clouds clouds;
    private Sys sys;
    private String base;
    private int visibility;
    private int dt;
    private int timezone;
    private int id;
    private int code;

    public WeatherModel(MainWeatherModel main, String name, Coord coord, List<Weather> weather, Wind wind, Clouds clouds, Sys sys, String base, int visibility, int dt, int timezone, int id, int code) {
        this.main = main;
        this.name = name;
        this.coord = coord;
        this.weather = weather;
        this.wind = wind;
        this.clouds = clouds;
        this.sys = sys;
        this.base = base;
        this.visibility = visibility;
        this.dt = dt;
        this.timezone = timezone;
        this.id = id;
        this.code = code;
    }

    public MainWeatherModel getMain() {
        return main;
    }

    public void setMain(MainWeatherModel main) {
        this.main = main;
    }

    @NonNull
    @Override
    public String toString() {
        return "current temp:"+ this.getMain().getTemp() + "\nwind:"+ this.getWind().getSpeed();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
