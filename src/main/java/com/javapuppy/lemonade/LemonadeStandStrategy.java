package com.javapuppy.lemonade;

public class LemonadeStandStrategy {

    // find percents in array by:
    // all streetCrewingWorking = true in first half of array, false in second half
    // Within that half, blocks of 3 in order of cost of lemonade: 2, 4, and 5
    // Within that portion, weather in order of SUNNY, HOT, CLOUDY

    public double[][] strategy = {
            {0.75, 1.0}, // crew working, cost 2, SUNNY
            {0.75, 1.0}, // crew working, cost 2, HOT
            {0.35, 0.5}, // crew working, cost 2, CLOUDY
            {0.75, 1.0}, // crew working, cost 4, SUNNY
            {0.75, 1.0}, // crew working, cost 4, HOT
            {0.35, 0.5}, // crew working, cost 4, CLOUDY
            {0.75, 1.0}, // crew working, cost 5, SUNNY
            {0.75, 1.0}, // crew working, cost 5, HOT
            {0.35, 0.5}, // crew working, cost 5, CLOUDY
            {0.95, 0.75}, // crew NOT working, cost 2, SUNNY
            {0.95, 0.75}, // crew NOT working, cost 2, HOT
            {0.35, 0.5}, // crew NOT working, cost 2, CLOUDY
            {0.95, 0.75}, // crew NOT working, cost 4, SUNNY
            {0.95, 0.75}, // crew NOT working, cost 4, HOT
            {0.35, 0.5}, // crew NOT working, cost 4, CLOUDY
            {0.95, 0.75}, // crew NOT working, cost 5, SUNNY
            {0.95, 0.75}, // crew NOT working, cost 5, HOT
            {0.35, 0.5}  // crew NOT working, cost 5, CLOUDY
    };

    private double[] getPercents(PlayerInformation info) {
        int index = 0;
        if (info.streetCrewWorking) {
            index += strategy.length / 2;
        }
        if (info.costPerGlass == 4) {
            index += 3;
        } else if (info.costPerGlass == 5) {
            index += 6;
        }
        if (info.weather == Weather.HOT) {
            index += 1;
        } else if (info.weather == Weather.CLOUDY) {
            index += 2;
        }

        return strategy[index];
    }



    public PlayerDecisions getDecisions(int assets, PlayerInformation info) {
        double[] percents = getPercents(info);
        double pReinvest = percents[0];
        double pSpentOnGlasses = percents[1];
        double amtReinvest = assets * pReinvest;
        double markup = minProfitableMarkup(amtReinvest, info.costPerGlass, pSpentOnGlasses);

        // Given the dollar amount we want to reinvest in making glasses of lemonade,
        // how many units can we make given the current cost of each glass?
        int glassesMade = (int) ((amtReinvest * pSpentOnGlasses) / info.costPerGlass);
        // Given the dollar amount we want to reinvest in making advertising signs,
        // how many signs can we make given the cost of each sign?
        int signsMade = (int) ((amtReinvest * (1 - pSpentOnGlasses)) / LemonadeStand.SIGN_COST);
        // Given the desired markup and the current cost per glass, what are we going
        // to charge per glass of lemonade?
        int pricePerGlass = (int) Math.round((1 + markup) * info.costPerGlass);

        return new PlayerDecisions(glassesMade, signsMade, pricePerGlass);

//        if (info.weather == Weather.SUNNY || info.weather == Weather.HOT) {
//            if (info.streetCrewWorking) {
//                return getDecisions(assets * 0.75, info.costPerGlass, 1.0, 1.0);
//            } else {
//                return getDecisions(assets * 0.95, info.costPerGlass, 0.75, 0.5);
//            }
//        } else if (info.weather == Weather.CLOUDY) {
//            return getDecisions(assets * 0.35, info.costPerGlass, 0.5,
//                    minProfitableMarkup(assets * 0.35, info.costPerGlass, 0.5));
//        } else {
//            return getDecisions(0, info.costPerGlass, 0, 0);
//        }
    }

    private double minProfitableMarkup(double amtAssetsSpent, int costPerGlass,
                                       double pSpentOnGlasses) {
        // Calculate cost to make lemonade
        int glassesMade = (int) ((amtAssetsSpent * pSpentOnGlasses) / costPerGlass);
        double costOfGlassesMade = glassesMade * costPerGlass;
        // Calculate cost to make signs
        int signsMade = (int) ((amtAssetsSpent * (1 - pSpentOnGlasses)) / LemonadeStand.SIGN_COST);
        double costOfSigns = signsMade * LemonadeStand.SIGN_COST;

        // Total cost for this day
        double totalCost = costOfGlassesMade + costOfSigns;

        double markup = 0.0;
        // Continue increasing markup by 5% until selling all
        // glasses will at least break even
        while ((1 + markup) * costPerGlass * glassesMade < totalCost) {
            markup += 0.05;
        }

        return markup;
    }

}
