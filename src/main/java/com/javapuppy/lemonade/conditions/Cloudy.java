package com.javapuppy.lemonade.conditions;

import com.javapuppy.lemonade.Weather;

public class Cloudy extends CostPerGlassRises {
    private double chanceOfRain;

    public Cloudy(int dayNum, double chanceOfRain) {
        super(dayNum);
        this.chanceOfRain = chanceOfRain;
        setWeather(Weather.CLOUDY);
    }

    @Override
    public String getSpecialEventText() {
        return "Always cloudy with 30% chance of rain, no construction";
    }

    @Override
    public double getWeatherFactor() {
        return  1.0 - chanceOfRain / 100;
    }

    @Override
    public double getChanceOfRain() {
        return chanceOfRain;
    }

    @Override
    public boolean isStormBrewing() {
        return false;
    }

    @Override
    public boolean isStreetCrewThirsty() {
        return false;
    }

    @Override
    public boolean isStreetCrewWorking() {
        return false;
    }
}
