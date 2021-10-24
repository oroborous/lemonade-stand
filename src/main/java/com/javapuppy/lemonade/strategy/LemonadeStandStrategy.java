package com.javapuppy.lemonade.strategy;

import com.javapuppy.lemonade.PlayerDecisions;
import com.javapuppy.lemonade.PlayerInformation;

public interface LemonadeStandStrategy {
    PlayerDecisions getDecisions(int assets, PlayerInformation info);
}
