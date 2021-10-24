package com.javapuppy.lemonade;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.util.Enumeration;

public class Main {
    public static void main(String[] args) {
        LemonadeStand ls = new LemonadeStand(1, 30);
        for (int i = 0; i < 1000; i++) {
            ls.openForBusiness();
        }
        ls.close();
    }
}
