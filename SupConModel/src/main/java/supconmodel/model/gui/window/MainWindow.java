/*
 * Created by JFormDesigner on Sat Sep 28 17:29:16 AEST 2024
 */

package supconmodel.model.gui.window;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.formdev.flatlaf.FlatIntelliJLaf;
import org.jdesktop.beansbinding.*;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

import supconmodel.SupConModelMain;
import supconmodel.utils.Enums.*;

/**
 * @author Clain
 */
public class MainWindow extends JFrame {

    // <editor-fold desc="variables">
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JMenuBar menuBar;
    private JMenu settingsMenu;
    private JMenuItem generalSettingsMI;
    private JMenuItem consumerSettingsMI;
    private JMenuItem goodSettingsMI;
    private JMenu supervisorMenu;
    private JMenuItem consumerSupervisorMI;
    private JMenuItem suuplierSupervisorMI;
    private JMenu outputMenu;
    private JMenuItem outputHistoryMI;
    private JLabel label1;
    private JSlider silderNumSup;
    private JLabel lNumSup;
    private JLabel label2;
    private JSlider silderNumCon;
    private JLabel lNumCon;
    private JPanel panel1;
    private BindingGroup bindingGroup;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    // </editor-fold>

    public MainWindow() {
        initComponents();
    }

    private void generalSettingsMI(ActionEvent e) {
        SupConModelMain.windowController.settingsWindow.tabbedPane1.setSelectedIndex(0);
        SupConModelMain.windowController.openSelectWindow(WindowEnum.SETTING);
    }

    private void consumerSettingsMI(ActionEvent e) {
        SupConModelMain.windowController.settingsWindow.tabbedPane1.setSelectedIndex(1);
        SupConModelMain.windowController.openSelectWindow(WindowEnum.SETTING);
    }

    private void goodSettingsMI(ActionEvent e) {
        SupConModelMain.windowController.settingsWindow.tabbedPane1.setSelectedIndex(2);
        SupConModelMain.windowController.openSelectWindow(WindowEnum.SETTING);
    }

    private void consumerSupervisorMI(ActionEvent e) {
        SupConModelMain.windowController.openSelectWindow(WindowEnum.CONSUMER_SUPERVISOR);
    }

    private void suuplierSupervisorMI(ActionEvent e) {
        SupConModelMain.windowController.openSelectWindow(WindowEnum.SUPPLIER_SUPERVISOR);
    }

    private void initComponents() {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        menuBar = new JMenuBar();
        settingsMenu = new JMenu();
        generalSettingsMI = new JMenuItem();
        consumerSettingsMI = new JMenuItem();
        goodSettingsMI = new JMenuItem();
        supervisorMenu = new JMenu();
        consumerSupervisorMI = new JMenuItem();
        suuplierSupervisorMI = new JMenuItem();
        outputMenu = new JMenu();
        outputHistoryMI = new JMenuItem();
        label1 = new JLabel();
        silderNumSup = new JSlider();
        lNumSup = new JLabel();
        label2 = new JLabel();
        silderNumCon = new JSlider();
        lNumCon = new JLabel();
        panel1 = new JPanel();

        //======== this ========
        setTitle("SupConModel");
        setMinimumSize(new Dimension(1600, 900));
        setPreferredSize(new Dimension(1600, 900));
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        ((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {0, 158, 34, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

        //======== menuBar ========
        {

            //======== settingsMenu ========
            {
                settingsMenu.setText("Settings");
                settingsMenu.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));

                //---- generalSettingsMI ----
                generalSettingsMI.setText("General Settings");
                generalSettingsMI.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                generalSettingsMI.addActionListener(e -> generalSettingsMI(e));
                settingsMenu.add(generalSettingsMI);

                //---- consumerSettingsMI ----
                consumerSettingsMI.setText("Consumer Settings");
                consumerSettingsMI.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                consumerSettingsMI.addActionListener(e -> consumerSettingsMI(e));
                settingsMenu.add(consumerSettingsMI);

                //---- goodSettingsMI ----
                goodSettingsMI.setText("Good Settings");
                goodSettingsMI.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                goodSettingsMI.addActionListener(e -> goodSettingsMI(e));
                settingsMenu.add(goodSettingsMI);
            }
            menuBar.add(settingsMenu);

            //======== supervisorMenu ========
            {
                supervisorMenu.setText("Supervisor");
                supervisorMenu.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));

                //---- consumerSupervisorMI ----
                consumerSupervisorMI.setText("Consumer Supervisor");
                consumerSupervisorMI.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                consumerSupervisorMI.addActionListener(e -> consumerSupervisorMI(e));
                supervisorMenu.add(consumerSupervisorMI);

                //---- suuplierSupervisorMI ----
                suuplierSupervisorMI.setText("Supplier Supervisor");
                suuplierSupervisorMI.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                suuplierSupervisorMI.addActionListener(e -> suuplierSupervisorMI(e));
                supervisorMenu.add(suuplierSupervisorMI);
            }
            menuBar.add(supervisorMenu);

            //======== outputMenu ========
            {
                outputMenu.setText("Output");
                outputMenu.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));

                //---- outputHistoryMI ----
                outputHistoryMI.setText("output History");
                outputHistoryMI.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                outputMenu.add(outputHistoryMI);
            }
            menuBar.add(outputMenu);
        }
        setJMenuBar(menuBar);

        //---- label1 ----
        label1.setText("Num of Suppliers");
        label1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        contentPane.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 10, 10), 0, 0));

        //---- silderNumSup ----
        silderNumSup.setMinimum(4);
        silderNumSup.setMaximum(20);
        silderNumSup.setValue(3);
        silderNumSup.setMinorTickSpacing(1);
        silderNumSup.setMajorTickSpacing(4);
        silderNumSup.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        silderNumSup.setSnapToTicks(true);
        silderNumSup.setMaximumSize(new Dimension(32765, 20));
        silderNumSup.setPreferredSize(null);
        contentPane.add(silderNumSup, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(2, 2, 7, 7), 0, 0));

        //---- lNumSup ----
        lNumSup.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        contentPane.add(lNumSup, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label2 ----
        label2.setText("Num of Consumer");
        label2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        contentPane.add(label2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 10, 10), 0, 0));

        //---- silderNumCon ----
        silderNumCon.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        silderNumCon.setMinimum(10);
        silderNumCon.setMaximum(1000);
        silderNumCon.setSnapToTicks(true);
        silderNumCon.setMinorTickSpacing(1);
        silderNumCon.setMajorTickSpacing(100);
        silderNumCon.setPreferredSize(null);
        contentPane.add(silderNumCon, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(2, 2, 7, 7), 0, 0));

        //---- lNumCon ----
        lNumCon.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        lNumCon.setText("100");
        contentPane.add(lNumCon, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
            new Insets(0, 0, 5, 5), 0, 0));

        //======== panel1 ========
        {
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
        }
        contentPane.add(panel1, new GridBagConstraints(0, 2, 4, 1, 1.0, 1.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));
        pack();
        setLocationRelativeTo(getOwner());

        //---- bindings ----
        bindingGroup = new BindingGroup();
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            silderNumSup, BeanProperty.create("value"),
            lNumSup, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            silderNumCon, BeanProperty.create("value"),
            lNumCon, BeanProperty.create("text")));
        bindingGroup.bind();
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on



    }


}
