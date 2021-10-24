package com.javapuppy.lemonade;

import com.javapuppy.lemonade.conditions.DailyConditions;
import com.javapuppy.lemonade.conditions.StandardGameConditions;
import com.javapuppy.lemonade.log.ConsoleLogger;
import com.javapuppy.lemonade.log.CsvFileLogger;
import com.javapuppy.lemonade.log.Logger;

import java.util.Arrays;

public class LemonadeStand {
    // all amounts are in pennies
    public static final int SIGN_COST = 15;
    final int STARTING_ASSETS = 200;
    final double P9 = 10;
    final double S2 = 30;
    final double C9 = 0.5;
    final double C2 = 1;

    private int currentDay = 0;
    private int maxDays = -1;

    private Player[] players;

    // Runs faster when console output disabled
    private boolean consoleEnabled = false;
    private Logger clog = new ConsoleLogger();
    private Logger flog = new CsvFileLogger();

    public LemonadeStand(int numPlayers, int maxDays) {
        this.maxDays = maxDays;
        this.players = new Player[numPlayers];
    }

    public void close() {
        flog.close();
    }

    public void setConsoleEnabled(boolean enabled) {
        this.consoleEnabled = enabled;
    }

    private void initPlayers() {
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(STARTING_ASSETS);
        }
    }

    public void openForBusiness() {
        currentDay = 0;
        initPlayers();

        do {
            DailyConditions dailyConditions = startNewDay();

            for (int i = 0; i < players.length; i++) {
                Player player = players[i];
                PlayerDecisions playerDecisions =
                        player.getDecisions(dailyConditions.getPlayerInformation());

                calculateResults(dailyConditions, player, playerDecisions);
            }
        } while (anyPlayersSolvent() && (currentDay < maxDays || maxDays == -1));
    }

    private boolean anyPlayersSolvent() {
        return Arrays.stream(players).anyMatch(p -> p.assets > 0);
    }

    private DailyConditions startNewDay() {
        currentDay += 1;
        DailyConditions dailyConditions = new StandardGameConditions(currentDay);

        if (consoleEnabled) {
            clog.log("Weather Report for Day " + currentDay + ": " + dailyConditions.getWeather().getDisplay());
            clog.log("On day " + currentDay + ", the cost of lemonade is: " + dailyConditions.getCostPerGlass());

            if (dailyConditions.getSpecialEventText() != null) {
                clog.log(dailyConditions.getSpecialEventText());
            }
        }

        return dailyConditions;
    }


    private void calculateResults(DailyConditions dailyConditions,
                                  Player player,
                                  PlayerDecisions playerDecisions) {
        // calculate how many glasses are sold
        String specialResult = null;
        double n1;
        int ppg = playerDecisions.pricePerGlass;
        int signs = playerDecisions.signsMade;
        int glasses = playerDecisions.glassesMade;

        if (ppg >= P9) {
            n1 = Math.pow(P9, 2) * S2 / Math.pow(ppg, 2);
        } else {
            n1 = (P9 - ppg) / P9 * 0.8 * S2 + S2;
        }

        double w = -signs * C9;

        // increase in sales due to ads
        double adBenefit = 1 - Math.exp(w) * C2;
        double n2 = Math.floor(dailyConditions.getWeatherFactor() * n1 * (1 + adBenefit));

        DailySalesReport report = new DailySalesReport(dailyConditions,
                player, playerDecisions);

        if (dailyConditions.isStormBrewing()) {
            report.weather = Weather.STORM;
            dailyConditions.setWeather(Weather.STORM);
            n2 = 0;
            if (glasses > 0) {
                report.specialResultText = "All lemonade was ruined";
            }
        } else if (dailyConditions.isStreetCrewThirsty()) {
            n2 = glasses;
            report.specialResultText = "The street crews bought all your lemonade at lunchtime!";
        }

        int glassesSold = (int) Math.min(n2, glasses);
        report.calculateProfit(glassesSold);

        // adjust assets
        player.adjustAssets(report.profit, report.day);

        if (dailyConditions.getDayNum() == 30 || player.assets <= 0) {
        flog.log(report);
        }
        if (consoleEnabled)
            clog.log(report);
    }


}
