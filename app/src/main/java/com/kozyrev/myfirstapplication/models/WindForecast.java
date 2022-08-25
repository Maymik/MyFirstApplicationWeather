package com.kozyrev.myfirstapplication.models;

public class WindForecast {
    private  float speed;
    private int deg;
    private float gust;

    public WindForecast(float speed, int deg, float gust) {
        this.speed = speed;
        this.deg = deg;
        this.gust = gust;

    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public float getGust() {
        return gust;
    }

    public void setGust(float gust) {
        this.gust = gust;
    }
}
