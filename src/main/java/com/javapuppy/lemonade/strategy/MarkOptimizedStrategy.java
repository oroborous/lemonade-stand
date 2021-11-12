package com.javapuppy.lemonade.strategy;

import com.javapuppy.lemonade.PlayerDecisions;
import com.javapuppy.lemonade.PlayerInformation;
import com.javapuppy.lemonade.Weather;

public class MarkOptimizedStrategy implements LemonadeStandStrategy{
    @Override
    public PlayerDecisions getDecisions(int assets, PlayerInformation info) {
        if (info.isStreetCrewWorking() || info.getWeather() == Weather.CLOUDY) {
            return new PlayerDecisions(0, 0, 0);
        }
        if (assets < 400) {
            if (info.getWeather() == Weather.HOT) {
                return new PlayerDecisions(56, 0, 10);
            } else {
                return new PlayerDecisions(27, 0, 10);
            }
        } else {
            if (info.getWeather() == Weather.HOT) {
                return new PlayerDecisions(90, 2, 10);
            } else {
                return new PlayerDecisions(45, 2, 10);
            }
        }
    }
}
