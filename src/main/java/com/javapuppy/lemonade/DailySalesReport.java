package com.javapuppy.lemonade;

import com.javapuppy.lemonade.conditions.DailyConditions;
import com.javapuppy.lemonade.conditions.StandardGameConditions;

import static com.javapuppy.lemonade.LemonadeStand.SIGN_COST;

public class DailySalesReport {
    public int playerNum;
    public int day;
    public Weather weather;
    public double chanceOfRain;
    public boolean streetCrewWorking;
    public int startingAssets;
    public int costPerGlass;
    public int glassesSold;
    public int pricePerGlass;
    public int salesIncome;
    public int glassesMade;
    public int lemonadeExpense;
    public int signsMade;
    public int adExpense;
    public int profit;
    public int endingAssets;
    public String specialResultText;

    public DailySalesReport(DailyConditions conditions, Player player,
                            PlayerDecisions decisions) {
        this.playerNum = player.playerId;
        this.day = conditions.getDayNum();
        this.weather = conditions.getWeather();
        this.costPerGlass = conditions.getCostPerGlass();
        this.chanceOfRain = conditions.getChanceOfRain();
        this.streetCrewWorking = conditions.isStreetCrewWorking();

        this.startingAssets = player.assets;
        this.glassesMade = decisions.glassesMade;
        this.signsMade = decisions.signsMade;
        this.pricePerGlass = decisions.pricePerGlass;
    }

    public void calculateProfit(int glassesSold) {
        this.glassesSold = glassesSold;

        // calculate income and expenses
        lemonadeExpense = glassesMade * costPerGlass;
        adExpense = signsMade * SIGN_COST;
        salesIncome = glassesSold * pricePerGlass;
        profit = salesIncome - lemonadeExpense - adExpense;

        endingAssets = startingAssets + profit;
    }
}
