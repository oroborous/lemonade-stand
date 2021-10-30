package com.javapuppy.lemonade.conditions;

import com.javapuppy.lemonade.Weather;

public class SunnyNoConstruction extends CostPerGlassRises {

    public SunnyNoConstruction(int dayNum) {
        super(dayNum);
        setWeather(Weather.SUNNY);
    }


    @Override
    public String getSpecialEventText() {
        return "Always sunny, no construction";
    }

    @Override
    public double getWeatherFactor() {
        return 1.0;
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
