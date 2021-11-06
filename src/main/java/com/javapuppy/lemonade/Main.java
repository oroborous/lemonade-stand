package com.javapuppy.lemonade;

import com.javapuppy.lemonade.conditions.Conditions;
import com.javapuppy.lemonade.strategy.VariedPpgSomeSigns200Glasses;


public class Main {
    public static void main(String[] args) {
        LemonadeStand ls = new LemonadeStand(30, 20000);
        for (int i = 0; i < 100; i++) {
            ls.openForBusiness(new VariedPpgSomeSigns200Glasses(), Conditions.SUNNY_NO_CONSTRUCTION);
        }
        ls.close();
    }
}
