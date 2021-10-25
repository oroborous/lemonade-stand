package com.javapuppy.lemonade;

import com.javapuppy.lemonade.conditions.Conditions;
import com.javapuppy.lemonade.strategy.FiftyGlassesNoSigns;


public class Main {
    public static void main(String[] args) {
        LemonadeStand ls = new LemonadeStand(1, 100, 20000);
        for (int i = 0; i < 1; i++) {
            ls.openForBusiness(new FiftyGlassesNoSigns(), Conditions.CLOUDY_30_PERC);
        }
        ls.close();
    }
}
