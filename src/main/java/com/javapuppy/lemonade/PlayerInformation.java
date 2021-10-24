package com.javapuppy.lemonade;

public class PlayerInformation {
    private boolean streetCrewWorking;
    private Weather weather;
    private int costPerGlass;

    public PlayerInformation(Weather weather,
                             boolean streetCrewWorking,
                             int costPerGlass) {
        this.weather = weather;
        this.streetCrewWorking = streetCrewWorking;
        this.costPerGlass = costPerGlass;
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
