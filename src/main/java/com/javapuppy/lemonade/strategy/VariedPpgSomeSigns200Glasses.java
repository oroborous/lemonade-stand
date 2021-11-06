package com.javapuppy.lemonade.strategy;

import com.javapuppy.lemonade.PlayerDecisions;
import com.javapuppy.lemonade.PlayerInformation;

import java.util.Random;

public class VariedPpgSomeSigns200Glasses implements LemonadeStandStrategy {

    private Random random = new Random();

    @Override
    public PlayerDecisions getDecisions(int assets, PlayerInformation info) {
        int ppg = (int) random.nextInt(15) + 6;
        int signs = (int) random.nextInt(4);
        int glassesMade = 100;
        return new PlayerDecisions(glassesMade, signs, ppg);
    }
}
