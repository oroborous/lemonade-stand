package com.javapuppy.lemonade.log;

import com.javapuppy.lemonade.DailySalesReport;

public interface Logger {
    void log(String text);

    void log(DailySalesReport report);

    void close();
}
