package com.kozyrev.myfirstapplication.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RainForecast implements Serializable {

    @SerializedName("3h")
    private float h;

    public RainForecast(float h) {
        this.h = h;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }
}
