package com.javapuppy.lemonade;

import java.util.Arrays;

public class LemonadeStand {
    // all amounts are in pennies
    static final int SIGN_COST = 15;
    final int STARTING_ASSETS = 200;
    final double P9 = 10;
    final double S2 = 30;
    final double C9 = 0.5;
    final double C2 = 1;

    private int day = 0;
    private Weather weather;
    private double weatherFactor = 1.0;
    private int costPerGlass;
    private boolean stormBrewing, streetCrewWorking, streetCrewThirsty;

    private Player[] players;

    private Logger logger = new ConsoleLogger();

    public void open(int numPlayers) {
        initGame(numPlayers);
        while (anyPlayersSolvent()) {
            startNewDay();
        }
    }

    private boolean anyPlayersSolvent() {
        return Arrays.stream(players).anyMatch(value -> value.getAssets() > 0);
    }

    private void startNewDay() {
        day += 1;
        makeWeather();
        logger.log("Weather Report for Day " + day + ": " + weather.getDisplay());

        if (day < 3) {
            costPerGlass = 2;
        } else if (day < 7) {
            costPerGlass = 4;
        } else {
            costPerGlass = 5;
        }

        logger.log("On day " + day + ", the cost of lemonade is: " + costPerGlass);

        String specialEvent = doRandomEvents();
        if (!specialEvent.isBlank()) {
            logger.log(specialEvent);
        }

        showDecisionPage();
    }

    private void showDecisionPage() {
        for (int i = 0; i < players.length; i++) {
            Player player = players[i];
            player.openStand(streetCrewWorking, weather, costPerGlass);
            SalesReport report = new SalesReport(i + 1, day,
                    weather, costPerGlass, player);
            calculateResults(player, report);
        }
    }

    private void initGame(int numPlayers) {
        this.day = 0;
        this.weatherFactor = 1.0;
        this.players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player(STARTING_ASSETS);
        }
    }

    private void calculateResults(Player player, SalesReport report) {
        // calculate how many glasses are sold
        String specialResult = null;
        double n1;

        if (player.getPricePerGlass() >= P9) {
            n1 = Math.pow(P9, 2) * S2 / Math.pow(player.getPricePerGlass(), 2);
        } else {
            n1 = (P9 - player.getPricePerGlass()) / P9 * 0.8 * S2 + S2;
        }

        double w = -player.getSignsMade() * C9;

        // increase in sales due to ads
        double adBenefit = 1 - Math.exp(w) * C2;
        double n2 = Math.floor(weatherFactor * n1 * (1 + adBenefit));

        if (stormBrewing) {
            report.weather = weather = Weather.STORM;
            n2 = 0;
            if (player.getGlassesMade() > 0) {
                specialResult = "All lemonade was ruined";
            }
        } else if (streetCrewThirsty) {
            n2 = player.getGlassesMade();
            specialResult = "The street crews bought all your lemonade at lunchtime!";
        }

        report.glassesSold = (int) Math.min(n2, player.getGlassesMade());

        // calculate income and expenses
        report.lemonadeExpense = player.getGlassesMade() * costPerGlass;
        report.adExpense = player.getSignsMade() * SIGN_COST;
        report.salesIncome = report.glassesSold * player.getPricePerGlass();
        report.profit = report.salesIncome - report.lemonadeExpense - report.adExpense;

        // adjust assets
        player.addProfit(report.profit);

        report.newAssets = player.getAssets();

        logger.log(report);

        if (specialResult != null) {
            logger.log(specialResult);
        }

        logger.log("Day " + day);
        logger.log("Stand " + report.standNum);
        logger.log(report.glassesSold + " Glasses Sold");
        logger.log("Price per glass: " + report.pricePerGlass);
        logger.log("Sales income: " + report.salesIncome);
        logger.log(report.glassesMade + " Glasses Made");
        logger.log("Cost per glass: " + costPerGlass);
        logger.log("Lemonade expense: " + report.lemonadeExpense);
        logger.log(report.signsMade + " Signs Made");
        logger.log("Cost per sign: " + SIGN_COST);
        logger.log("Ad expense: " + report.adExpense);
        logger.log("Profit: " + report.profit);
        logger.log("New assets: " + report.newAssets);
    }

    private void makeWeather() {
        double r = Math.random();
        if (r < 0.6) {
            weather = Weather.SUNNY;
        } else if (r < 0.8) {
            weather = Weather.CLOUDY;
        } else {
            if (day < 3) {
                weather = Weather.SUNNY;
            } else {
                weather = Weather.HOT;
            }
        }

        double chanceOfRain = 0;
        if (weather == Weather.CLOUDY) {
            chanceOfRain = 30 + Math.floor(Math.random() * 5) * 10;
            weatherFactor = 1.0 - chanceOfRain / 100;
        } else if (weather == Weather.HOT) {
            weatherFactor = 2.0;
        } else {
            weatherFactor = 1.0;
        }
    }



    private String doRandomEvents() {
        String specialDesc = "";

        streetCrewWorking = streetCrewThirsty = false;
        stormBrewing = false;

        if (weather == Weather.CLOUDY) {
            if (Math.random() < 0.25) {
                stormBrewing = true;
            }
        } else if (weather == Weather.HOT) {
            // heat wave already handled in makeWeather()
        } else {
            if (Math.random() >= 0.25) {
                return "";
            }
            specialDesc = "The street department is working today. There will be no traffic on your street.";
            streetCrewWorking = true;
            if (Math.random() < 0.5) {
                streetCrewThirsty = true;
            } else {
                weatherFactor = 1.0;
            }
        }

        return specialDesc;
    }
}
