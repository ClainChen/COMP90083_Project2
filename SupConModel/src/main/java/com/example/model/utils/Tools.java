package com.example.model.utils;

import com.example.model.Main;

import javax.swing.*;

public class Tools {
    public static void refreshFrame(JFrame frame){
        SwingUtilities.invokeLater(() -> {
            frame.revalidate();
            frame.repaint();
        });
    }

    public static void feedback(String message, int messageType){
        JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(false);
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JOptionPane optionPane = new JOptionPane(message, messageType);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> dialog.dispose());

        optionPane.setOptions(new Object[]{okButton});
        dialog.setContentPane(optionPane);
        dialog.pack();

        dialog.setLocationRelativeTo(Main.mainWindow);
        dialog.setVisible(true);
    }
}
