package com.javapuppy.lemonade;

public class SalesReport {
    int standNum;
    int day;
    Weather weather;
    int startingAssets;
    int costPerGlass;
    int glassesSold;
    int pricePerGlass;
    int salesIncome;
    int glassesMade;
    int lemonadeExpense;
    int signsMade;
    int adExpense;
    int profit;
    int newAssets;

    public SalesReport(int standNum, int day, Weather weather,
                       int costPerGlass, Player player) {
        this.standNum = standNum;
        this.day = day;
        this.weather = weather;
        this.costPerGlass = costPerGlass;

        this.startingAssets = player.getAssets();
        this.glassesMade = player.getGlassesMade();
        this.signsMade = player.getSignsMade();
        this.pricePerGlass = player.getPricePerGlass();
    }
}
