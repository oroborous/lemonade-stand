package com.javapuppy.lemonade;

public class Player {
    private static int nextId = 1;
    int playerId;
    public int assets;
    public int finalDay;
    LemonadeStandStrategy strategy = new LemonadeStandStrategy();

    public Player(int assets) {
        this.playerId = nextId++;
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
