package com.example.family_bet.DB.NBA;




public class Meta {
    private int version;
    private String request;
    private String time;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String toString() {
        return "version:" + version + " request:" + request + " time:" + time;
    }
}