package com.javapuppy.lemonade.conditions;

import com.javapuppy.lemonade.Weather;

public class Hot extends CostPerGlassRises{
    public Hot(int dayNum) {
        super(dayNum);
        setWeather(Weather.HOT);
    }

    @Override
    public String getSpecialEventText() {
        return "Always hot, no construction";
    }

    @Override
    public double getWeatherFactor() {
        return 2.0;
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
        return false;
    }

    @Override
    public boolean isStreetCrewWorking() {
        return false;
    }
}
