package com.javapuppy.lemonade.conditions;

import com.javapuppy.lemonade.PlayerInformation;
import com.javapuppy.lemonade.Weather;

public abstract class CostPerGlassRises implements DailyConditions {
    private final int dayNum;
    private final int costPerGlass;
    private Weather weather;

    public CostPerGlassRises(int dayNum) {
        this.dayNum = dayNum;

        if (dayNum < 3) {
            costPerGlass = 2;
        } else if (dayNum < 7) {
            costPerGlass = 4;
        } else {
            costPerGlass = 5;
        }
    }

    @Override
    public PlayerInformation getPlayerInformation() {
        return new PlayerInformation(getWeather(), isStreetCrewWorking(), getCostPerGlass());
    }

    @Override
    public int getCostPerGlass() {
        return costPerGlass;
    }

    @Override
    public int getDayNum() {
        return dayNum;
    }

    @Override
    public Weather getWeather() {
        return weather;
    }

    @Override
    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
