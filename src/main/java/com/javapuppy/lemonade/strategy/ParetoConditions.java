package com.javapuppy.lemonade.strategy;

import com.javapuppy.lemonade.Player;
import com.javapuppy.lemonade.PlayerDecisions;
import com.javapuppy.lemonade.PlayerInformation;

import java.util.Random;

public class ParetoConditions implements LemonadeStandStrategy {

    private Random random = new Random();

    @Override
    public PlayerDecisions getDecisions(int assets, PlayerInformation info) {
        // 0 - 3 signs
        int signs = (int)random.nextInt(4);
        int glassesMade = 20;
        int ppg = (int)random.nextInt(15) + 6;


        return new PlayerDecisions(glassesMade, signs, ppg);
    }
}
