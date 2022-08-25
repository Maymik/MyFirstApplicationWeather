package com.kozyrev.myfirstapplication.models;

import java.io.Serializable;

public class CoordForecast implements Serializable {
    private float lat;
    private float lon;

    public CoordForecast(float lat, float lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }
}
