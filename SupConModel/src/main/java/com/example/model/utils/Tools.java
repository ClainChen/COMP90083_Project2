package com.example.model.utils;

import javax.swing.*;

public class Tools {
    public static void refreshFrame(JFrame frame){
        SwingUtilities.invokeLater(() -> {
            frame.revalidate();
            frame.repaint();
        });
    }


}
