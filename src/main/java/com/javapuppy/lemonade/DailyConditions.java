package com.javapuppy.lemonade;

public class DailyConditions {
    int dayNum;
    Weather weather;
    double weatherFactor;
    boolean streetCrewWorking;
    boolean streetCrewThirsty;
    boolean stormBrewing;
    int costPerGlass;
    String specialEventText;

    public DailyConditions(int dayNum) {
        this.dayNum = dayNum;

        if (dayNum < 3) {
            costPerGlass = 2;
        } else if (dayNum < 7) {
            costPerGlass = 4;
        } else {
            costPerGlass = 5;
        }

        makeWeather();
        doRandomEvents();
    }

    public PlayerInformation getPlayerInformation() {
        return new PlayerInformation(weather, streetCrewWorking, costPerGlass);
    }

    private void makeWeather() {
        double r = Math.random();
        if (r < 0.6) {
            weather = Weather.SUNNY;
        } else if (r < 0.8) {
            weather = Weather.CLOUDY;
        } else {
            if (dayNum < 3) {
                weather = Weather.SUNNY;
            } else {
                weather = Weather.HOT;
            }
        }


        if (weather == Weather.CLOUDY) {
            double chanceOfRain = 30 + Math.floor(Math.random() * 5) * 10;
            weatherFactor = 1.0 - chanceOfRain / 100;
        } else if (weather == Weather.HOT) {
            weatherFactor = 2.0;
        } else {
            weatherFactor = 1.0;
        }
    }

    private void doRandomEvents() {
        streetCrewWorking = streetCrewThirsty = false;
        stormBrewing = false;

        if (weather == Weather.CLOUDY) {
            if (Math.random() < 0.25) {
                stormBrewing = true;
            }
        } else if (weather == Weather.HOT) {
            specialEventText = "Heat wave!";
        } else {
            if (Math.random() < 0.25) {
                specialEventText = "The street department is working today. There will be no traffic on your street.";
                streetCrewWorking = true;
                if (Math.random() < 0.5) {
                    streetCrewThirsty = true;
                } else {
                    weatherFactor = 1.0;
                }
            }
        }
    }
}
