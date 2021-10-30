package com.javapuppy.lemonade.log;

import com.javapuppy.lemonade.DailySalesReport;
import com.javapuppy.lemonade.LemonadeStand;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvFileLogger implements Logger {
    @Override
    public void log(String text) {
        try {
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private FileWriter writer;

    public CsvFileLogger() {
        try {
            this.writer = new FileWriter(File.createTempFile("lemonade-", ".csv", new File(".")));
            StringBuilder sb = new StringBuilder();
            sb.append("Day").append(",");
            sb.append("Weather").append(",");
            sb.append("Chance of Rain").append(",");
            sb.append("Street Crew?").append(",");
            sb.append("Stand").append(",");
            sb.append("Starting Assets").append(",");
            sb.append("Glasses Sold").append(",");
            sb.append("Price Per Glass").append(",");
            sb.append("Sales Income").append(",");
            sb.append("Glasses Made").append(",");
            sb.append("Cost Per Glass").append(",");
            sb.append("Lemonade Expense").append(",");
            sb.append("Signs Made").append(",");
            sb.append("Cost Per Sign").append(",");
            sb.append("Ad Expense").append(",");
            sb.append("Profit").append(",");
            sb.append("Ending Assets").append("\n");
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(DailySalesReport report) {
        StringBuilder sb = new StringBuilder();
        sb.append(report.day).append(",");
        sb.append(report.weather.getDisplay()).append(",");
        sb.append(String.format("%f%", report.chanceOfRain)).append(",");
        sb.append(report.streetCrewWorking ? "Y" : "N").append(",");
        sb.append(report.playerNum).append(",");
        sb.append(report.startingAssets).append(",");
        sb.append(report.glassesSold).append(",");
        sb.append(report.pricePerGlass).append(",");
        sb.append(report.salesIncome).append(",");
        sb.append(report.glassesMade).append(",");
        sb.append(report.costPerGlass).append(",");
        sb.append(report.lemonadeExpense).append(",");
        sb.append(report.signsMade).append(",");
        sb.append(LemonadeStand.SIGN_COST).append(",");
        sb.append(report.adExpense).append(",");
        sb.append(report.profit).append(",");
        sb.append(report.endingAssets).append("\n");

        try {
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
