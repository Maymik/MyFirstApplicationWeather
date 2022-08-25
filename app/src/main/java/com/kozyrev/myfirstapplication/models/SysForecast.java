package com.kozyrev.myfirstapplication.models;

import java.io.Serializable;

public class SysForecast implements Serializable {
    private String pod;

    public SysForecast(String pod) {
        this.pod = pod;
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }
}
