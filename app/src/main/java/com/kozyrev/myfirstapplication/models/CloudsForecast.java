package com.kozyrev.myfirstapplication.models;

import java.io.Serializable;

public class CloudsForecast implements Serializable {
    private int all;

    public CloudsForecast(int all) {
        this.all = all;

    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }
}
