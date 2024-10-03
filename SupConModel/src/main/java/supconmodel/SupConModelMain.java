package supconmodel;

import supconmodel.model.agents.Good;
import supconmodel.model.gui.controller.WindowController;
import supconmodel.utils.Enums.WindowEnum;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SupConModelMain {
    public static WindowController windowController;
    public static ArrayList<Good> allGoods;

    public static void main(String[] args) throws IOException, FontFormatException {
        try (InputStream fontStream = SupConModelMain.class.getResourceAsStream("/font/JetBrainsMono-Regular.ttf")) {
            if (fontStream == null) {
                System.out.println("Font not found!");
                return;
            }
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(16f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        }

        InitializeData();

        windowController = new WindowController();
        windowController.openSelectWindow(WindowEnum.MAIN);
    }

    private static void InitializeData() throws IOException, FontFormatException {
        allGoods = new ArrayList<>();

    }
}