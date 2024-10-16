package com.example.model;

import com.example.model.abm.ABM;
import com.example.model.experiment.Experiment;
import com.example.model.experiment.ExperimentSpace;
import com.example.model.gui.MainWindow;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static ABM currentABM;
    public static MainWindow mainWindow;
    public static int speed;
    public static ExperimentSpace experimentSpace;

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
        speed = 4;
        experimentSpace = new ExperimentSpace();

        mainWindow = new MainWindow();
        mainWindow.setVisible(true);
    }

    public static void setup(){
        if (currentABM != null) {
            currentABM.stop();
        }
        currentABM = new ABM();
        currentABM.setup();

    }

    public static void go_once(){
        currentABM.go_once();
    }

    public static void go(){
        currentABM.go();
    }
}
