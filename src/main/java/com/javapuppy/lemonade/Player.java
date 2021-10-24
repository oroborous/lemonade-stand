package com.javapuppy.lemonade;

import com.javapuppy.lemonade.strategy.ConservativeStrategy;
import com.javapuppy.lemonade.strategy.LemonadeStandStrategy;

public class Player {
    private static int nextId = 1;
    int playerId;
    public int assets;
    public int finalDay;
    private LemonadeStandStrategy strategy;

    public Player(int assets, LemonadeStandStrategy strategy) {
        this.playerId = nextId++;
        this.strategy = strategy;
        this.assets = assets;
    }

    public void adjustAssets(int profit, int currentDay) {
        this.assets += profit;
        this.finalDay = currentDay;
    }

    public PlayerDecisions getDecisions(PlayerInformation info) {
        return strategy.getDecisions(assets, info);
    }


}
