package com.example.model;

import com.example.model.abm.ABM;
import com.example.model.abm.ABMCollection;
import com.example.model.gui.MainWindow;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static ABMCollection abmCollection;
    public static ABM currentABM;
    public static MainWindow mainWindow;

    // This method is called when the program is launched
    // This is the main method of the program
    public static void main(String[] args) throws IOException {
        try (InputStream fontStream = Main.class.getResourceAsStream("/font/JetBrainsMono-Regular.ttf")) {
            if (fontStream == null) {
                System.out.println("Font not found!");
                return;
            }
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(16f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }

        abmCollection = new ABMCollection();

        mainWindow = new MainWindow();
        mainWindow.setVisible(true);
    }

    public static void setup(){
        ABM abm = new ABM();
        abm.setup();
        currentABM = abm;
        abmCollection.abms.add(abm);
    }

    public static void go_once(){
        currentABM.go_once();
    }

    public static void go(){
        currentABM.go();
    }
}
