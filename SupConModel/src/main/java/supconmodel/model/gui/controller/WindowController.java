package supconmodel.model.gui.controller;

import javax.swing.*;

import supconmodel.model.gui.window.ConsumerSupervisorWindow;
import supconmodel.model.gui.window.MainWindow;
import supconmodel.model.gui.window.SettingsWindow;
import supconmodel.model.gui.window.SupplierSupervisorWindow;
import supconmodel.utils.Enums.WindowEnum;
import supconmodel.utils.Tools;

public class WindowController {
    public MainWindow mainWindow;
    public SettingsWindow settingsWindow;
    public SupplierSupervisorWindow supplierSupervisorWindow;
    public ConsumerSupervisorWindow consumerSupervisorWindow;

    public WindowController(){
        mainWindow = new MainWindow();
        settingsWindow = new SettingsWindow();
        supplierSupervisorWindow = new SupplierSupervisorWindow();
        consumerSupervisorWindow = new ConsumerSupervisorWindow();

        Timer timer = new Timer(20, e -> {
            if (mainWindow.isVisible()){
                Tools.refreshFrame(mainWindow);
            }
            if (settingsWindow.isVisible()){
                Tools.refreshFrame(settingsWindow);
            }
            if (supplierSupervisorWindow.isVisible()){
                Tools.refreshFrame(supplierSupervisorWindow);
            }
            if (consumerSupervisorWindow.isVisible()){
                Tools.refreshFrame(consumerSupervisorWindow);
            }
        });
        timer.start();
    }

    public void openSelectWindow(WindowEnum window){
        switch(window){
            case MAIN -> openWindow(mainWindow);
            case SETTING -> openWindow(settingsWindow);
            case SUPPLIER_SUPERVISOR -> openWindow(supplierSupervisorWindow);
            case CONSUMER_SUPERVISOR -> openWindow(consumerSupervisorWindow);
        }
    }

    private void openWindow(JFrame window){
        SwingUtilities.invokeLater(() -> window.setVisible(true));
    }
}
