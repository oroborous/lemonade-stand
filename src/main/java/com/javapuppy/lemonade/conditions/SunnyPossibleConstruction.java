package com.javapuppy.lemonade.conditions;

import com.javapuppy.lemonade.Weather;

public class SunnyPossibleConstruction extends CostPerGlassRises {
    private double weatherFactor;
    private boolean streetCrewWorking, streetCrewThirsty;
    private String specialEventText;

    public SunnyPossibleConstruction(int dayNum) {
        super(dayNum);

        makeWeather();
        doRandomEvents();
    }

    @Override
    public String getSpecialEventText() {
        return specialEventText;
    }

    @Override
    public double getWeatherFactor() {
        return weatherFactor;
    }

    @Override
    public double getChanceOfRain() {
        return 0;
    }

    @Override
    public boolean isStormBrewing() {
        return false;
    }

    @Override
    public boolean isStreetCrewThirsty() {
        return streetCrewThirsty;
    }

    @Override
    public boolean isStreetCrewWorking() {
        return streetCrewWorking;
    }

    private void makeWeather() {
        setWeather(Weather.SUNNY);
        weatherFactor = 1.0;
    }

    private void doRandomEvents() {
        streetCrewWorking = streetCrewThirsty = false;
        specialEventText = "";

        if (Math.random() < 0.25) {
            specialEventText = "The street department is working today. There will be no traffic on your street.";
            streetCrewWorking = true;
            if (Math.random() < 0.5) {
                streetCrewThirsty = true;
            } else {
                weatherFactor = 0.1;
            }
        }
    }
}
