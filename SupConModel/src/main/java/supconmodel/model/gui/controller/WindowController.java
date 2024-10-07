package supconmodel.model.gui.controller;

import javax.swing.*;

import supconmodel.model.gui.window.*;
import supconmodel.utils.Enums.WindowEnum;
import supconmodel.utils.Tools;

import java.awt.*;
import java.util.ArrayList;

public class WindowController {
    public MainWindow mainWindow;
    public SettingsWindow settingsWindow;
    public ArrayList<SupplierSupervisorWindow> supplierSupervisorWindow;
    public ArrayList<ConsumerSupervisorWindow> consumerSupervisorWindow;
    public ArrayList<GoodSupervisorWindow> goodSupervisorWindow;
    public GoodSelectWindow goodSelectWindow;

    public WindowController(){
        setDefaultUI(new Font("JetBrains Mono", Font.PLAIN, 12));

        mainWindow = new MainWindow();
        settingsWindow = new SettingsWindow();
        supplierSupervisorWindow = new ArrayList<>();
        consumerSupervisorWindow = new ArrayList<>();
        goodSupervisorWindow = new ArrayList<>();
        goodSelectWindow = new GoodSelectWindow();

        Timer timer = new Timer(20, e -> {
            if (mainWindow.isVisible()){
                Tools.refreshFrame(mainWindow);
            }
            if (settingsWindow.isVisible()){
                Tools.refreshFrame(settingsWindow);
            }
            if (goodSelectWindow.isVisible()){
                Tools.refreshFrame(goodSelectWindow);
            }
            for (SupplierSupervisorWindow window : supplierSupervisorWindow){
                if (window.isVisible()){
                    Tools.refreshFrame(window);
                }
            }
            for (ConsumerSupervisorWindow window : consumerSupervisorWindow){
                if (window.isVisible()){
                    Tools.refreshFrame(window);
                }
            }
            for (GoodSupervisorWindow window : goodSupervisorWindow){
                if (window.isVisible()){
                    Tools.refreshFrame(window);
                }
            }
        });
        timer.start();


    }

    public static void setDefaultUI(Font font) {
        UIManager.put("OptionPane.okButtonText", "OK");
        UIManager.put("OptionPane.cancelButtonText", "Cancel");
    }

    public void openSelectWindow(WindowEnum window){
        switch(window){
            case MAIN -> openWindow(mainWindow);
            case SETTING -> openWindow(settingsWindow);
            case GOOD_SELECT -> openWindow(goodSelectWindow);
        }
    }

    public void openSupervisor(WindowEnum windowEnum){
        switch(windowEnum){
            case SUPPLIER_SUPERVISOR -> {
                for(SupplierSupervisorWindow window : supplierSupervisorWindow){
                    if (!window.isVisible()){
                        openWindow(window);
                        return;
                    }
                }
                createSupervisorWindow(windowEnum);
            }
            case CONSUMER_SUPERVISOR -> {
                for(ConsumerSupervisorWindow window : consumerSupervisorWindow){
                    if (!window.isVisible()){
                        openWindow(window);
                        return;
                    }
                }
                createSupervisorWindow(windowEnum);
            }
            case GOOD_SUPERVISOR -> {
                for(GoodSupervisorWindow window : goodSupervisorWindow){
                    if (!window.isVisible()){
                        openWindow(window);
                        return;
                    }
                }
                createSupervisorWindow(windowEnum);
            }
        }
    }

    public void closeSelectWindow(WindowEnum window){
        switch(window){
            case MAIN -> closeWindow(mainWindow);
            case SETTING -> closeWindow(settingsWindow);
            case GOOD_SELECT -> closeWindow(goodSelectWindow);
        }
    }

    private void createSupervisorWindow(WindowEnum window){
        switch(window){
            case SUPPLIER_SUPERVISOR -> {
                SupplierSupervisorWindow newSupSupervisor = new SupplierSupervisorWindow();
                supplierSupervisorWindow.add(newSupSupervisor);
                SwingUtilities.invokeLater(() -> newSupSupervisor.setVisible(true));
            }
            case CONSUMER_SUPERVISOR -> {
                ConsumerSupervisorWindow newConsumerSupervisor = new ConsumerSupervisorWindow();
                consumerSupervisorWindow.add(newConsumerSupervisor);
                SwingUtilities.invokeLater(() -> newConsumerSupervisor.setVisible(true));
            }
            case GOOD_SUPERVISOR -> {
                GoodSupervisorWindow newGoodSupervisor = new GoodSupervisorWindow();
                goodSupervisorWindow.add(newGoodSupervisor);
                SwingUtilities.invokeLater(() -> newGoodSupervisor.setVisible(true));
            }
        }
    }

    private void openWindow(JFrame window){
        SwingUtilities.invokeLater(() -> window.setVisible(true));
    }

    private void closeWindow(JFrame window) {SwingUtilities.invokeLater(() -> window.setVisible(false));}
}
