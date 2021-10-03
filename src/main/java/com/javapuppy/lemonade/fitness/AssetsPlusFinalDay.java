package com.javapuppy.lemonade.fitness;

import com.javapuppy.lemonade.Player;

import java.util.function.Function;

public class AssetsPlusFinalDay implements Function<Player, Integer> {
    @Override
    public Integer apply(Player player) {
        return player.assets - (31 - player.finalDay) + 31;
    }
}
