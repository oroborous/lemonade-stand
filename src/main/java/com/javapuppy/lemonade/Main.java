package com.javapuppy.lemonade;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.util.Enumeration;

public class Main {
    public static void main(String[] args) {
//        InputStream is = MainWindow.class.getResourceAsStream("C64_Pro-STYLE.ttf");
//        try {
//            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
//            setUIFont(new FontUIResource(font));
//        } catch (FontFormatException | IOException e) {
//            e.printStackTrace();
//            setUIFont(new FontUIResource("Verdana", Font.BOLD, 18));
//        }
//
//
//        MainWindow mainWindow = new MainWindow();

        LemonadeStand ls = new LemonadeStand(1, 30);
        for (int i = 0; i < 1000; i++) {
            ls.openForBusiness();
        }
        ls.close();

    }

    public static void setUIFont (FontUIResource f){
        Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get (key);
            if (value instanceof FontUIResource)
                UIManager.put (key, f);
        }
    }
}
