package com.javapuppy.lemonade;

import com.javapuppy.lemonade.conditions.Conditions;
import com.javapuppy.lemonade.strategy.FiftyGlassesNoSigns;
import com.javapuppy.lemonade.strategy.ParetoConditions;


public class Main {
    public static void main(String[] args) {
        LemonadeStand ls = new LemonadeStand(1,30, 20000);
        for (int i = 0; i < 1; i++) {
            ls.openForBusiness(new ParetoConditions(), Conditions.STANDARD_GAME);
        }
        ls.close();
    }
}
