package com.javapuppy.lemonade.strategy;

import com.javapuppy.lemonade.PlayerDecisions;
import com.javapuppy.lemonade.PlayerInformation;

public class FiftyGlassesNoSigns implements LemonadeStandStrategy {
    @Override
    public PlayerDecisions getDecisions(int assets, PlayerInformation info) {
        int possiblePrices = 30 - info.getCostPerGlass() + 1;
        int price = (int) Math.floor(Math.random() * possiblePrices) + info.getCostPerGlass();
        return new PlayerDecisions(50, 0, price);
    }
}
