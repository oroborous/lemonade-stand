package com.javapuppy.lemonade.conditions;

import com.javapuppy.lemonade.PlayerInformation;
import com.javapuppy.lemonade.Weather;

public class StandardGameConditions extends CostPerGlassRises {
    private double weatherFactor, chanceOfRain;
    private boolean streetCrewWorking, streetCrewThirsty, stormBrewing;
    private String specialEventText;

    public StandardGameConditions(int dayNum) {
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
        return chanceOfRain;
    }

    @Override
    public boolean isStormBrewing() {
        return stormBrewing;
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
        double r = Math.random();
        if (r < 0.6) {
            setWeather(Weather.SUNNY);
        } else if (r < 0.8) {
            setWeather(Weather.CLOUDY);
        } else {
            if (getDayNum() < 3) {
                setWeather(Weather.SUNNY);
            } else {
                setWeather(Weather.HOT);
            }
        }

        if (getWeather() == Weather.CLOUDY) {
            chanceOfRain = 30 + Math.floor(Math.random() * 5) * 10;
            weatherFactor = 1.0 - chanceOfRain / 100;
        } else if (getWeather() == Weather.HOT) {
            weatherFactor = 2.0;
        } else {
            weatherFactor = 1.0;
        }
    }

    private void doRandomEvents() {
        streetCrewWorking = streetCrewThirsty = stormBrewing = false;
        specialEventText = "";

        if (getWeather() == Weather.CLOUDY) {
            if (Math.random() < 0.25) {
                stormBrewing = true;
            }
        } else if (getWeather() == Weather.HOT) {
            specialEventText = "Heat wave!";
        } else {
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
}
