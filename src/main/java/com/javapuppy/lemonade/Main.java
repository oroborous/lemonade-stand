package com.javapuppy.lemonade;

import com.javapuppy.lemonade.ui.MainWindow;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

public class Main {
    public static void main(String[] args) {
        InputStream is = MainWindow.class.getResourceAsStream("C64_Pro-STYLE.ttf");
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            setUIFont(new FontUIResource(font));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            setUIFont(new FontUIResource("Verdana", Font.BOLD, 18));
        }


        MainWindow mainWindow = new MainWindow();



//
//        LemonadeStand ls = new LemonadeStand();
//        ls.open(1);
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
