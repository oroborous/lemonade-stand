package com.javapuppy.lemonade;

import com.javapuppy.lemonade.conditions.*;
import com.javapuppy.lemonade.log.ConsoleLogger;
import com.javapuppy.lemonade.log.CsvFileLogger;
import com.javapuppy.lemonade.log.Logger;
import com.javapuppy.lemonade.strategy.LemonadeStandStrategy;

import java.util.Arrays;

public class LemonadeStand {
    // all amounts are in pennies
    public static final int SIGN_COST = 15;
    final int STARTING_ASSETS;
    private final int MAX_DAYS;

    private final double P9 = 10;
    private final double S2 = 30;
    private final double C9 = 0.5;
    private final double C2 = 1;

    private int currentDay = 0;
    private Player player;


    // Runs faster when console output disabled
    private boolean consoleEnabled = false;
    private Logger clog = new ConsoleLogger();
    private Logger flog = new CsvFileLogger();

    public LemonadeStand(int maxDays, int startingAssets) {
        this.MAX_DAYS = maxDays;
        this.STARTING_ASSETS = startingAssets;
    }

    public void close() {
        flog.close();
    }

    public void setConsoleEnabled(boolean enabled) {
        this.consoleEnabled = enabled;
    }


    public void openForBusiness(LemonadeStandStrategy strategy, Conditions conditions) {
        currentDay = 0;
        player = new Player(STARTING_ASSETS, strategy);

        do {
            DailyConditions dailyConditions = startNewDay(conditions);

            PlayerDecisions playerDecisions =
                    player.getDecisions(dailyConditions.getPlayerInformation());

            calculateResults(dailyConditions, player, playerDecisions);
        } while (player.assets > 0 && (currentDay < MAX_DAYS || MAX_DAYS == -1));
    }


    private DailyConditions startNewDay(Conditions conditions) {
        currentDay += 1;
        DailyConditions dailyConditions;
        if (conditions == Conditions.SUNNY_NO_CONSTRUCTION)
            dailyConditions = new SunnyNoConstruction(currentDay);
        else if (conditions == Conditions.HOT)
            dailyConditions = new Hot(currentDay);
        else if (conditions == Conditions.CLOUDY_30_PERC)
            dailyConditions = new Cloudy(currentDay, 30);
        else
            dailyConditions = new StandardGameConditions(currentDay);

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

//        if (dailyConditions.getDayNum() == 30 || player.assets <= 0) {
        flog.log(report);
//        }
        if (consoleEnabled)
            clog.log(report);
    }


}
