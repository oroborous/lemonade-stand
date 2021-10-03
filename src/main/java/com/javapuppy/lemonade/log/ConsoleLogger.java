package com.javapuppy.lemonade.log;

import com.javapuppy.lemonade.DailySalesReport;

import static com.javapuppy.lemonade.LemonadeStand.SIGN_COST;

public class ConsoleLogger implements Logger {

    

    @Override
    public void log(String text) {
        System.out.println(text);
    }

    @Override
    public void log(DailySalesReport report) {
        if (report.specialResultText != null) {
            log(report.specialResultText);
        }

        log("Day " + report.day);
        log("Stand " + report.playerNum);
        log(report.glassesSold + " Glasses Sold");
        log("Price per glass: " + report.pricePerGlass);
        log("Sales income: " + report.salesIncome);
        log(report.glassesMade + " Glasses Made");
        log("Cost per glass: " + report.costPerGlass);
        log("Lemonade expense: " + report.lemonadeExpense);
        log(report.signsMade + " Signs Made");
        log("Cost per sign: " + SIGN_COST);
        log("Ad expense: " + report.adExpense);
        log("Profit: " + report.profit);
        log("New assets: " + report.endingAssets);
    }

    @Override
    public void close() {
        // No need to close std out
    }
}
