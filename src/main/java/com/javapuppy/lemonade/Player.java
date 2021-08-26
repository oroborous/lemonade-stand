package com.javapuppy.lemonade;

public class Player {
    private int assets;
    private int glassesMade;
    private int signsMade;
    private int pricePerGlass;

    public int getAssets() {
        return assets;
    }

    public int getGlassesMade() {
        return glassesMade;
    }

    public int getSignsMade() {
        return signsMade;
    }

    public int getPricePerGlass() {
        return pricePerGlass;
    }

    public Player(int assets) {
        this.assets = assets;
    }

    public void addProfit(int profit) {
        this.assets += profit;
    }

    public void openStand(boolean streetCrew, Weather weather, int costPerGlass) {
        if (weather == Weather.SUNNY || weather == Weather.HOT) {
            if (streetCrew) {
                openStand(assets * 0.75, costPerGlass, 1.0, 1.0);
            } else {
                openStand(assets * 0.95, costPerGlass, 0.75, 0.5);
            }
        } else if (weather == Weather.CLOUDY) {
            openStand(assets * 0.35, costPerGlass, 0.5,
                    minProfitableMarkup(assets * 0.35, costPerGlass, 0.5));
        } else {
            openStand(0, costPerGlass, 0, 0);
        }
    }

    private double minProfitableMarkup(double assetsRisked, int costPerGlass,
                                       double percentGlasses) {
        int glassesMade = (int) ((assetsRisked * percentGlasses) / costPerGlass);
        double costOfGlassesMade = glassesMade * costPerGlass;
        int signsMade = (int) ((assetsRisked * (1 - percentGlasses)) / LemonadeStand.SIGN_COST);
        double costOfSigns = signsMade * LemonadeStand.SIGN_COST;

        double totalCost = costOfGlassesMade + costOfSigns;
        double markup = 0.0;

        while ((1 + markup) * costPerGlass * glassesMade < totalCost) {
            markup += 0.05;
        }

        return markup;
    }

    private void openStand(double assetsRisked, double costPerGlass,
                           double percentGlasses, double markup) {
        glassesMade = (int) ((assetsRisked * percentGlasses) / costPerGlass);
        double balance = assetsRisked - (glassesMade * costPerGlass);
        signsMade = (int) ((assetsRisked * (1 - percentGlasses)) / LemonadeStand.SIGN_COST);
        pricePerGlass = (int) Math.round((1 + markup) * costPerGlass);
    }


}
