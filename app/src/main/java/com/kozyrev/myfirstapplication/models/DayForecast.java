package com.kozyrev.myfirstapplication.models;


import java.io.Serializable;
import java.util.List;

public class DayForecast implements Serializable {
    private int dt;
    private MainForecast main;
    private List<WeatherForecast> weather;
    private CloudsForecast clouds;
    private WindForecast wind;
    private int visibility;
    private RainForecast rain;
    private float pop;
    private SysForecast sys;
    private String dt_txt;

    public DayForecast(int dt, MainForecast main, List<WeatherForecast> weather, CloudsForecast clouds, WindForecast wind, int visibility, RainForecast rain, float pop, SysForecast sys, String dt_txt) {
        this.dt = dt;
        this.main = main;
        this.weather = weather;
        this.clouds = clouds;
        this.wind = wind;
        this.visibility = visibility;
        this.rain = rain;
        this.pop = pop;
        this.sys = sys;
        this.dt_txt = dt_txt;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public MainForecast getMain() {
        return main;
    }

    public void setMain(MainForecast mainForecast) {
        this.main = mainForecast;
    }

    public List<WeatherForecast> getWeatherForecast() {
        return weather;
    }

    public void setWeatherForecast(List<WeatherForecast> weatherForecast) {
        this.weather = weatherForecast;
    }

    public CloudsForecast getCloudsForecast() {
        return clouds;
    }

    public void setCloudsForecast(CloudsForecast cloudsForecast) {
        this.clouds = cloudsForecast;
    }

    public WindForecast getWindForecast() {
        return wind;
    }

    public void setWindForecast(WindForecast windForecast) {
        this.wind = windForecast;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public RainForecast getRainForecast() {
        return rain;
    }

    public void setRainForecast(RainForecast rainForecast) {
        this.rain = rainForecast;
    }

    public float getPop() {
        return pop;
    }

    public void setPop(float pop) {
        this.pop = pop;
    }

    public SysForecast getSys() {
        return sys;
    }

    public void setSys(SysForecast sys) {
        this.sys = sys;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }
}
