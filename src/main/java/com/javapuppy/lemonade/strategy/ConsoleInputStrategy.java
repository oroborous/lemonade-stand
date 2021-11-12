package com.javapuppy.lemonade.strategy;

import com.javapuppy.lemonade.PlayerDecisions;
import com.javapuppy.lemonade.PlayerInformation;

import java.util.Scanner;

public class ConsoleInputStrategy implements LemonadeStandStrategy {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public PlayerDecisions getDecisions(int assets, PlayerInformation info) {
        System.out.printf("%n * * * * * DAY %d * * * * * %n", info.getDayNum());
        System.out.printf("Weather: %s%n", info.getWeather().getDisplay());
        if (info.isStreetCrewWorking())
        System.out.println("Street crewing is working today");
        System.out.printf("Cost per glass: %d cents%n", info.getCostPerGlass());
        System.out.printf("Assets: %d%n%n", assets);

        while (true) {
            try {
                System.out.print("How many glasses will you make? ");
                int glasses = scanner.nextInt();
                System.out.print("How many signs will you make? ");
                int signs = scanner.nextInt();
                System.out.print("How much will you charge per glass? ");
                int price = scanner.nextInt();
                return new PlayerDecisions(glasses, signs, price);
            } catch (Exception e) {}
        }
    }
}
