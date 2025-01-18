package com.example.shortlink;

class AppConfig {
    private int maxLinkLifetimeDays = 7;

    public AppConfig() {

    }

    public int getMaxLinkLifetimeDays() {
        return maxLinkLifetimeDays;
    }
}
