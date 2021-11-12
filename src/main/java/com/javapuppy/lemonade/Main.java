package com.javapuppy.lemonade;

import com.javapuppy.lemonade.conditions.Conditions;
import com.javapuppy.lemonade.strategy.ConservativeStrategy;
import com.javapuppy.lemonade.strategy.ConsoleInputStrategy;
import com.javapuppy.lemonade.strategy.MarkOptimizedStrategy;
import com.javapuppy.lemonade.strategy.VariedPpgSomeSigns200Glasses;


public class Main {
    public static void main(String[] args) {
        LemonadeStand ls = new LemonadeStand(30, 200);
        for (int i = 0; i < 50; i++) {
            ls.openForBusiness(new MarkOptimizedStrategy(), Conditions.STANDARD_GAME);
        }
        ls.close();
    }
}
