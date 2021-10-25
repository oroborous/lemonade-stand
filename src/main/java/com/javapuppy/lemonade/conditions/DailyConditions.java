package com.javapuppy.lemonade.conditions;

import com.javapuppy.lemonade.PlayerInformation;
import com.javapuppy.lemonade.Weather;

public interface DailyConditions {
    int getCostPerGlass();

    int getDayNum();

    PlayerInformation getPlayerInformation();

    String getSpecialEventText();

    double getWeatherFactor();

    Weather getWeather();

    boolean isStormBrewing();

    boolean isStreetCrewThirsty();

    boolean isStreetCrewWorking();

    void setWeather(Weather weather);
}
