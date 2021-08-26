package com.javapuppy.lemonade;

public enum Weather {
    SUNNY(2, "Sunny"),
    HOT(7, "Hot"),
    CLOUDY(10, "Cloudy"),
    STORM(5, "Storm");

    private int value;
    private String display;

    Weather(int value, String display) {
        this.value = value;
        this.display = display;
    }

    public int getValue() {
        return this.value;
    }

    public String getDisplay() {
        return this.display;
    }
}
