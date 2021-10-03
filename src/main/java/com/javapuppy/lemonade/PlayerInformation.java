package com.javapuppy.lemonade;

public class PlayerInformation {
    boolean streetCrewWorking;
    Weather weather;
    int costPerGlass;

    public PlayerInformation(Weather weather,
                             boolean streetCrewWorking,
                             int costPerGlass) {
        this.weather = weather;
        this.streetCrewWorking = streetCrewWorking;
        this.costPerGlass = costPerGlass;
    }
}
