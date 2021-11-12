package com.javapuppy.lemonade;

public class PlayerInformation {
    private int dayNum;
    private boolean streetCrewWorking;
    private Weather weather;
    private int costPerGlass;

    public PlayerInformation(int dayNum,
                             Weather weather,
                             boolean streetCrewWorking,
                             int costPerGlass) {
        this.dayNum = dayNum;
        this.weather = weather;
        this.streetCrewWorking = streetCrewWorking;
        this.costPerGlass = costPerGlass;
    }

    public int getDayNum() {
        return dayNum;
    }

    public int getCostPerGlass() {
        return costPerGlass;
    }

    public boolean isStreetCrewWorking() {
        return streetCrewWorking;
    }

    public Weather getWeather() {
        return weather;
    }
}
