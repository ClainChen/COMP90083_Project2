/*
 * Created by JFormDesigner on Sat Sep 28 17:29:16 AEST 2024
 */

package supconmodel.model.gui.window;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Properties;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.formdev.flatlaf.FlatIntelliJLaf;
import org.jdesktop.beansbinding.*;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

import supconmodel.SupConModelMain;
import supconmodel.model.agents.Supplier;
import supconmodel.model.customChart.*;
import supconmodel.model.simulation.Simulator;
import supconmodel.utils.Constants;
import supconmodel.utils.Enums.*;
import supconmodel.utils.Parameters;
import supconmodel.utils.Tools;

/**
 * @author Clain
 */
public class MainWindow extends JFrame {

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
        SupConModelMain.windowController.openSupervisor(WindowEnum.CONSUMER_SUPERVISOR);
    }

    private void supplierSupervisorMI(ActionEvent e) {
        SupConModelMain.windowController.openSupervisor(WindowEnum.SUPPLIER_SUPERVISOR);
    }

    private void goodSupervisorMI(ActionEvent e) {
        SupConModelMain.windowController.openSupervisor(WindowEnum.GOOD_SUPERVISOR);
    }



    private void bInitProps(ActionEvent e) {
        try{
            JFileChooser fileChooser = new JFileChooser();

            FileNameExtensionFilter filter = new FileNameExtensionFilter("Properties File (*.properties)", "properties");
            fileChooser.setFileFilter(filter);

            int userSelection = fileChooser.showOpenDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                SupConModelMain.propertiesFile = fileChooser.getSelectedFile();
                try (FileInputStream inputStream = new FileInputStream(SupConModelMain.propertiesFile)){
                    SupConModelMain.windowController.settingsWindow.initProperties(inputStream);
                    sNumSup.setValue(Parameters.num_suppliers);
                    sNumCon.setValue(Parameters.num_consumers);
                    sNumGpS.setValue(Parameters.goods_per_supplier);
                    Tools.feedback("Properties Successfully initialized", 1);
                }
            }

        }catch (RuntimeException | IOException re){
            Tools.feedback("Properties Fail to initialize\n" + re.getMessage(), 3);
            re.printStackTrace();
        }
    }

    private void bInitGoods(ActionEvent e) {
        try{
            JFileChooser fileChooser = new JFileChooser();

            FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV File (*.csv)", "csv");
            fileChooser.setFileFilter(filter);

            int userSelection = fileChooser.showOpenDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                SupConModelMain.goodsFile = fileChooser.getSelectedFile();
                FileInputStream inputStream = new FileInputStream(SupConModelMain.goodsFile);
                SupConModelMain.SetUpGoodData(inputStream);
                SupConModelMain.windowController.goodSelectWindow.ResetLayout();
                Tools.feedback("Goods Successfully initialized", 1);
            }

        }catch (RuntimeException | IOException re){
            Tools.feedback("Goods Fail to initialize\n" + re.getMessage(), 3);
            re.printStackTrace();
        }
    }

    private void sNumSupStateChanged(ChangeEvent e) {
        Parameters.num_suppliers = (int) sNumSup.getValue();
    }

    private void bSetup(ActionEvent e) {
        SupConModelMain.simulator.setup();
    }

    private void bStep(ActionEvent e) {
        SupConModelMain.simulator.step();
    }

    private void bGo(ActionEvent e) {
        SupConModelMain.simulator.go();
        if (SupConModelMain.simulator.timer.isRunning()){
            Tools.startTimer(10, false, e1 -> bGo.setBackground(new Color(192,192,192)));
        }else{
            Tools.startTimer(10, false, e2 -> bGo.setBackground(new Color(253,253,253)));
        }
    }

    private void sliderSpeedStateChanged(ChangeEvent e) {
        int speed = sliderSpeed.getValue();
        lSpeed.setText(Constants.speedTextPair.get(speed));
        Parameters.simulate_speed= Constants.speedValuePair.get(speed);
        SupConModelMain.simulator.setTimerDelay(Parameters.simulate_speed);
    }

    private void sNumConStateChanged(ChangeEvent e) {
        Parameters.num_consumers = (int) sNumCon.getValue();
        int baseInventory = Parameters.num_consumers * 7 * 50;
        Parameters.init_basic_max_inventory = baseInventory;
        Parameters.init_convenient_max_inventory = baseInventory * 2 / 3;
        Parameters.init_luxury_max_inventory = baseInventory / 3;
        Parameters.init_basic_inventory = Parameters.init_basic_max_inventory * 3 / 5;
        Parameters.init_convenient_inventory = Parameters.init_convenient_max_inventory * 3 / 5;
        Parameters.init_luxury_inventory = Parameters.init_luxury_max_inventory * 3 / 5;
    }

    private void sNumGpSStateChanged(ChangeEvent e) {
        Parameters.goods_per_supplier = (int) sNumGpS.getValue();
    }

    public void updateOverallSupervise(Simulator simulator){
        double totalIncome = simulator.getTotalIncome();
        double last7DayIncome = simulator.overallDataRecorder.last7IncomeData.getLast();
        double totalSolNnum = simulator.getTotalSoldNum();
        double last7DaysSoldNum = simulator.overallDataRecorder.last7SoldNumData.getLast();
        double totalCost = simulator.getTotalCost();
        double last7DaysCost = simulator.overallDataRecorder.lastCostData.getLast();
        double totalBasicDemand = simulator.overallDataRecorder.basicDemandData.getLast();
        double totalConvenientDemand = simulator.overallDataRecorder.convenientDemandData.getLast();
        double totalLuxuryDemand = simulator.overallDataRecorder.luxuryDemandData.getLast();
        double last7BasicIncome = simulator.overallDataRecorder.basicIncomeData.getLast();
        double last7ConvenientIncome = simulator.overallDataRecorder.convenientIncomeData.getLast();
        double last7LuxuryIncome = simulator.overallDataRecorder.luxuryIncomeData.getLast();
        lTIncome.setText(String.format("%.3f", totalIncome));
        lLast7DaysIncome.setText(String.format("%.3f", last7DayIncome));
        lTSoldNum.setText(String.format("%.3f", totalSolNnum));
        lLast7DaysSoldNum.setText(String.format("%.3f", last7DaysSoldNum));
        lTCost.setText(String.format("%.3f", totalCost));
        lLast7DaysCost.setText(String.format("%.3f", last7DaysCost));
        lTBasicDemand.setText(String.format("%.3f", totalBasicDemand));
        lTConvenientDemand.setText(String.format("%.3f", totalConvenientDemand));
        lTLuxuryDemand.setText(String.format("%.3f", totalLuxuryDemand));
        lLast7BasicIncome.setText(String.format("%.3f", last7BasicIncome));
        lLast7ConvenientIncome.setText(String.format("%.3f", last7ConvenientIncome));
        lLast7LuxuryIncome.setText(String.format("%.3f", last7LuxuryIncome));

        updateCharts();
    }

    private void bStep7Days(ActionEvent e) {
        for (int i = 0; i < 7; i++){
            SupConModelMain.simulator.step();
        }
    }

    private void selectData(ActionEvent e) {
        Object object = e.getSource();
        if (object instanceof JCheckBox){
            JCheckBox checkBox = (JCheckBox) object;
            if (checkBox.isSelected()){
                selectedData.add(checkBox);
            }else{
                selectedData.remove(checkBox);
            }
        }
        if (selectedData.size() > 6){
            selectedData.removeFirst().setSelected(false);
        }
        updateCharts();
    }

    private void updateCharts(){
        for (int i = 0; i < 6; i++){
            ArrayList<Double> doubleDataset = new ArrayList<>();
            ArrayList<Integer> intDataset = new ArrayList<>();
            String title = "";
            CustomLineChart chart = charts.get(i);
            chart.resetData();
            int isDouble = 0;

            if (i < selectedData.size()){
                title = selectedData.get(i).getText();
                switch (selectedData.get(i).getText()){
                    case "Last 7 Days Income" -> {
                        doubleDataset = SupConModelMain.simulator.overallDataRecorder.last7IncomeData;
                        isDouble = 1;
                    }
                    case "Last 7 Days Sold Num" -> {
                        isDouble = 2;
                        intDataset = SupConModelMain.simulator.overallDataRecorder.last7SoldNumData;
                    }
                    case "Last 7 Days Cost" -> {
                        isDouble = 1;
                        doubleDataset = SupConModelMain.simulator.overallDataRecorder.lastCostData;
                    }
                    case "Last 7 Basic Income" -> {
                        isDouble = 1;
                        doubleDataset = SupConModelMain.simulator.overallDataRecorder.basicIncomeData;
                    }
                    case "Last 7 Convenient Income" -> {
                        isDouble = 1;
                        doubleDataset = SupConModelMain.simulator.overallDataRecorder.convenientIncomeData;
                    }
                    case "Last 7 Luxury Income" -> {
                        isDouble = 1;
                        doubleDataset = SupConModelMain.simulator.overallDataRecorder.luxuryIncomeData;
                    }
                    case "Total Basic Demand" -> {
                        isDouble = 1;
                        doubleDataset = SupConModelMain.simulator.overallDataRecorder.basicDemandData;
                    }
                    case "Total Convenient Demand" -> {
                        isDouble = 1;
                        doubleDataset = SupConModelMain.simulator.overallDataRecorder.convenientDemandData;
                    }
                    case "Total Luxury Demand" -> {
                        isDouble = 1;
                        doubleDataset = SupConModelMain.simulator.overallDataRecorder.luxuryDemandData;
                    }
                }
            }

            if (isDouble == 1){
                chart.setTitle(title);
                chart.setxLabel("days");
                chart.setyLabel(title);
                chart.addSeries(title);
                for (int j = 0; j < doubleDataset.size(); j++){
                    chart.addData(title, j, doubleDataset.get(j));
                }
            }
            if (isDouble == 2){
                chart.setTitle(title);
                chart.setxLabel("7 days");
                chart.setyLabel(title);
                chart.addSeries(title);
                for (int j = 0; j < intDataset.size(); j++){
                    chart.addData(title, j, intDataset.get(j));
                }
            }
            chart.resetChart();

        }
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
        supplierSupervisorMI = new JMenuItem();
        goodSupervisorMI = new JMenuItem();
        outputMenu = new JMenu();
        outputHistoryMI = new JMenuItem();
        label1 = new JLabel();
        sNumSup = new JSpinner();
        bInitProps = new JButton();
        bInitGoods = new JButton();
        label2 = new JLabel();
        sNumCon = new JSpinner();
        lDay = new JLabel();
        bSetup = new JButton();
        bStep = new JButton();
        bGo = new JButton();
        label3 = new JLabel();
        sNumGpS = new JSpinner();
        label4 = new JLabel();
        sliderSpeed = new JSlider();
        lSpeed = new JLabel();
        bStep7Days = new JButton();
        separator1 = new JSeparator();
        panel1 = new JPanel();
        panel2 = new JPanel();
        labelTotalIncome = new JLabel();
        lTIncome = new JLabel();
        selectLast7DaysIncome = new JCheckBox();
        lLast7DaysIncome = new JLabel();
        label6 = new JLabel();
        lTSoldNum = new JLabel();
        selectLast7DaysSoldNum = new JCheckBox();
        lLast7DaysSoldNum = new JLabel();
        label7 = new JLabel();
        lTCost = new JLabel();
        selectLast7DaysCost = new JCheckBox();
        lLast7DaysCost = new JLabel();
        selectLast7BasicIncome = new JCheckBox();
        lLast7BasicIncome = new JLabel();
        selectLast7ConvenientIncome = new JCheckBox();
        lLast7ConvenientIncome = new JLabel();
        selectLast7LuxuryIncome = new JCheckBox();
        lLast7LuxuryIncome = new JLabel();
        selectTotalBasicDemand = new JCheckBox();
        lTBasicDemand = new JLabel();
        selectTotalConvenientDemand = new JCheckBox();
        lTConvenientDemand = new JLabel();
        selectTotalLuxuryDemand = new JCheckBox();
        lTLuxuryDemand = new JLabel();
        chart1 = new CustomLineChart();
        chart2 = new CustomLineChart();
        chart3 = new CustomLineChart();
        chart4 = new CustomLineChart();
        chart5 = new CustomLineChart();
        chart6 = new CustomLineChart();
        tFeedBack = new JLabel();

        //======== this ========
        setTitle("SupConModel");
        setMinimumSize(new Dimension(800, 450));
        setPreferredSize(new Dimension(1440, 810));
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new Dimension(1440, 810));
        var contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        ((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {0, 158, 0, 184, 0, 0, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

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

                //---- supplierSupervisorMI ----
                supplierSupervisorMI.setText("Supplier Supervisor");
                supplierSupervisorMI.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                supplierSupervisorMI.addActionListener(e -> supplierSupervisorMI(e));
                supervisorMenu.add(supplierSupervisorMI);

                //---- goodSupervisorMI ----
                goodSupervisorMI.setText("Good Supervisor");
                goodSupervisorMI.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                goodSupervisorMI.addActionListener(e -> goodSupervisorMI(e));
                supervisorMenu.add(goodSupervisorMI);
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

        //---- sNumSup ----
        sNumSup.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        sNumSup.setModel(new SpinnerNumberModel(3, 1, null, 1));
        sNumSup.addChangeListener(e -> sNumSupStateChanged(e));
        contentPane.add(sNumSup, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(2, 2, 7, 7), 0, 0));

        //---- bInitProps ----
        bInitProps.setText("Init Properties");
        bInitProps.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        bInitProps.addActionListener(e -> bInitProps(e));
        contentPane.add(bInitProps, new GridBagConstraints(2, 0, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- bInitGoods ----
        bInitGoods.setText("Init Goods");
        bInitGoods.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        bInitGoods.addActionListener(e -> bInitGoods(e));
        contentPane.add(bInitGoods, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label2 ----
        label2.setText("Num of Consumer");
        label2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        contentPane.add(label2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 10, 10), 0, 0));

        //---- sNumCon ----
        sNumCon.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        sNumCon.setPreferredSize(null);
        sNumCon.setModel(new SpinnerNumberModel(3, 1, null, 1));
        sNumCon.addChangeListener(e -> sNumConStateChanged(e));
        contentPane.add(sNumCon, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(2, 2, 7, 7), 0, 0));

        //---- lDay ----
        lDay.setText("Day: 0");
        lDay.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        contentPane.add(lDay, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(2, 2, 7, 7), 0, 0));

        //---- bSetup ----
        bSetup.setText("Setup");
        bSetup.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        bSetup.addActionListener(e -> bSetup(e));
        contentPane.add(bSetup, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- bStep ----
        bStep.setText("Step");
        bStep.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        bStep.addActionListener(e -> bStep(e));
        contentPane.add(bStep, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- bGo ----
        bGo.setText("Go");
        bGo.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        bGo.addActionListener(e -> bGo(e));
        contentPane.add(bGo, new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label3 ----
        label3.setText("Num of Goods per Supplier");
        label3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        contentPane.add(label3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 10, 10), 0, 0));

        //---- sNumGpS ----
        sNumGpS.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        sNumGpS.setModel(new SpinnerNumberModel(3, 1, null, 1));
        sNumGpS.addChangeListener(e -> sNumGpSStateChanged(e));
        contentPane.add(sNumGpS, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(2, 2, 7, 7), 0, 0));

        //---- label4 ----
        label4.setText("Speed");
        label4.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        contentPane.add(label4, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 10, 10), 0, 0));

        //---- sliderSpeed ----
        sliderSpeed.setSnapToTicks(true);
        sliderSpeed.setMinimum(1);
        sliderSpeed.setMaximum(9);
        sliderSpeed.setMinorTickSpacing(1);
        sliderSpeed.setValue(7);
        sliderSpeed.setPaintTicks(true);
        sliderSpeed.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        sliderSpeed.addChangeListener(e -> sliderSpeedStateChanged(e));
        contentPane.add(sliderSpeed, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(2, 2, 7, 7), 0, 0));

        //---- lSpeed ----
        lSpeed.setText("60ticks/s");
        lSpeed.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        contentPane.add(lSpeed, new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- bStep7Days ----
        bStep7Days.setText("Step 7 Days");
        bStep7Days.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        bStep7Days.addActionListener(e -> bStep7Days(e));
        contentPane.add(bStep7Days, new GridBagConstraints(5, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- separator1 ----
        separator1.setBackground(Color.darkGray);
        separator1.setForeground(Color.darkGray);
        contentPane.add(separator1, new GridBagConstraints(0, 3, 7, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 5, 0), 0, 0));

        //======== panel1 ========
        {
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

            //======== panel2 ========
            {
                panel2.setLayout(new GridBagLayout());
                ((GridBagLayout)panel2.getLayout()).columnWidths = new int[] {0, 61, 0};
                ((GridBagLayout)panel2.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                ((GridBagLayout)panel2.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
                ((GridBagLayout)panel2.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                //---- labelTotalIncome ----
                labelTotalIncome.setText("Total Income");
                labelTotalIncome.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                labelTotalIncome.setBackground(Color.white);
                panel2.add(labelTotalIncome, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(3, 3, 8, 8), 0, 0));

                //---- lTIncome ----
                lTIncome.setText("0");
                lTIncome.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(lTIncome, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(3, 3, 8, 3), 0, 0));

                //---- selectLast7DaysIncome ----
                selectLast7DaysIncome.setText("Last 7 Days Income");
                selectLast7DaysIncome.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                selectLast7DaysIncome.setBackground(Color.white);
                selectLast7DaysIncome.setBorderPaintedFlat(true);
                selectLast7DaysIncome.addActionListener(e -> selectData(e));
                panel2.add(selectLast7DaysIncome, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(3, 3, 8, 8), 0, 0));

                //---- lLast7DaysIncome ----
                lLast7DaysIncome.setText("0");
                lLast7DaysIncome.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(lLast7DaysIncome, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(3, 3, 8, 3), 0, 0));

                //---- label6 ----
                label6.setText("Total Sold Num");
                label6.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                label6.setBackground(Color.white);
                panel2.add(label6, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(3, 3, 8, 8), 0, 0));

                //---- lTSoldNum ----
                lTSoldNum.setText("0");
                lTSoldNum.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(lTSoldNum, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(3, 3, 8, 3), 0, 0));

                //---- selectLast7DaysSoldNum ----
                selectLast7DaysSoldNum.setText("Last 7 Days Sold Num");
                selectLast7DaysSoldNum.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                selectLast7DaysSoldNum.setBackground(Color.white);
                selectLast7DaysSoldNum.setBorderPaintedFlat(true);
                selectLast7DaysSoldNum.addActionListener(e -> selectData(e));
                panel2.add(selectLast7DaysSoldNum, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(3, 3, 8, 8), 0, 0));

                //---- lLast7DaysSoldNum ----
                lLast7DaysSoldNum.setText("0");
                lLast7DaysSoldNum.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(lLast7DaysSoldNum, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(3, 3, 8, 3), 0, 0));

                //---- label7 ----
                label7.setText("Total Cost");
                label7.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                label7.setBackground(Color.white);
                panel2.add(label7, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(3, 3, 8, 8), 0, 0));

                //---- lTCost ----
                lTCost.setText("0");
                lTCost.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(lTCost, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(3, 3, 8, 3), 0, 0));

                //---- selectLast7DaysCost ----
                selectLast7DaysCost.setText("Last 7 Days Cost");
                selectLast7DaysCost.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                selectLast7DaysCost.setBackground(Color.white);
                selectLast7DaysCost.setBorderPaintedFlat(true);
                selectLast7DaysCost.addActionListener(e -> selectData(e));
                panel2.add(selectLast7DaysCost, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(3, 3, 8, 8), 0, 0));

                //---- lLast7DaysCost ----
                lLast7DaysCost.setText("0");
                lLast7DaysCost.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(lLast7DaysCost, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(3, 3, 8, 3), 0, 0));

                //---- selectLast7BasicIncome ----
                selectLast7BasicIncome.setText("Last 7 Basic Income");
                selectLast7BasicIncome.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                selectLast7BasicIncome.setBackground(Color.white);
                selectLast7BasicIncome.setBorderPaintedFlat(true);
                selectLast7BasicIncome.addActionListener(e -> selectData(e));
                panel2.add(selectLast7BasicIncome, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(3, 3, 8, 8), 0, 0));

                //---- lLast7BasicIncome ----
                lLast7BasicIncome.setText("0");
                lLast7BasicIncome.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(lLast7BasicIncome, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(3, 3, 8, 3), 0, 0));

                //---- selectLast7ConvenientIncome ----
                selectLast7ConvenientIncome.setText("Last 7 Convenient Income");
                selectLast7ConvenientIncome.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                selectLast7ConvenientIncome.setBackground(Color.white);
                selectLast7ConvenientIncome.setBorderPaintedFlat(true);
                selectLast7ConvenientIncome.addActionListener(e -> selectData(e));
                panel2.add(selectLast7ConvenientIncome, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(3, 3, 8, 8), 0, 0));

                //---- lLast7ConvenientIncome ----
                lLast7ConvenientIncome.setText("0");
                lLast7ConvenientIncome.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(lLast7ConvenientIncome, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(3, 3, 8, 3), 0, 0));

                //---- selectLast7LuxuryIncome ----
                selectLast7LuxuryIncome.setText("Last 7 Luxury Income");
                selectLast7LuxuryIncome.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                selectLast7LuxuryIncome.setBackground(Color.white);
                selectLast7LuxuryIncome.setBorderPaintedFlat(true);
                selectLast7LuxuryIncome.addActionListener(e -> selectData(e));
                panel2.add(selectLast7LuxuryIncome, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(3, 3, 8, 8), 0, 0));

                //---- lLast7LuxuryIncome ----
                lLast7LuxuryIncome.setText("0");
                lLast7LuxuryIncome.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(lLast7LuxuryIncome, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(3, 3, 8, 3), 0, 0));

                //---- selectTotalBasicDemand ----
                selectTotalBasicDemand.setText("Total Basic Demand");
                selectTotalBasicDemand.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                selectTotalBasicDemand.setBackground(Color.white);
                selectTotalBasicDemand.setBorderPaintedFlat(true);
                selectTotalBasicDemand.addActionListener(e -> selectData(e));
                panel2.add(selectTotalBasicDemand, new GridBagConstraints(0, 9, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(3, 3, 8, 8), 0, 0));

                //---- lTBasicDemand ----
                lTBasicDemand.setText("0");
                lTBasicDemand.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(lTBasicDemand, new GridBagConstraints(1, 9, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(3, 3, 8, 3), 0, 0));

                //---- selectTotalConvenientDemand ----
                selectTotalConvenientDemand.setText("Total Convenient Demand");
                selectTotalConvenientDemand.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                selectTotalConvenientDemand.setBackground(Color.white);
                selectTotalConvenientDemand.setBorderPaintedFlat(true);
                selectTotalConvenientDemand.addActionListener(e -> selectData(e));
                panel2.add(selectTotalConvenientDemand, new GridBagConstraints(0, 10, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(3, 3, 8, 8), 0, 0));

                //---- lTConvenientDemand ----
                lTConvenientDemand.setText("0");
                lTConvenientDemand.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(lTConvenientDemand, new GridBagConstraints(1, 10, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(3, 3, 8, 3), 0, 0));

                //---- selectTotalLuxuryDemand ----
                selectTotalLuxuryDemand.setText("Total Luxury Demand");
                selectTotalLuxuryDemand.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                selectTotalLuxuryDemand.setBackground(Color.white);
                selectTotalLuxuryDemand.setBorderPaintedFlat(true);
                selectTotalLuxuryDemand.addActionListener(e -> selectData(e));
                panel2.add(selectTotalLuxuryDemand, new GridBagConstraints(0, 11, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(3, 3, 8, 8), 0, 0));

                //---- lTLuxuryDemand ----
                lTLuxuryDemand.setText("0");
                lTLuxuryDemand.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(lTLuxuryDemand, new GridBagConstraints(1, 11, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    new Insets(3, 3, 8, 3), 0, 0));
            }
            panel1.add(panel2, new GridBagConstraints(0, 0, 1, 2, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 0, 5), 0, 0));
            panel1.add(chart1, new GridBagConstraints(1, 0, 1, 1, 2.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(chart2, new GridBagConstraints(2, 0, 1, 1, 2.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
            panel1.add(chart3, new GridBagConstraints(3, 0, 1, 1, 2.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));
            panel1.add(chart4, new GridBagConstraints(1, 1, 1, 1, 2.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));
            panel1.add(chart5, new GridBagConstraints(2, 1, 1, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));
            panel1.add(chart6, new GridBagConstraints(3, 1, 1, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        contentPane.add(panel1, new GridBagConstraints(0, 4, 7, 1, 1.0, 1.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));
        contentPane.add(tFeedBack, new GridBagConstraints(0, 5, 7, 1, 0.0, 0.0,
            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
            new Insets(2, 2, 2, 2), 0, 0));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on

        selectedData = new ArrayList<>();
        charts = new ArrayList<>();
        charts.add(chart1);
        charts.add(chart2);
        charts.add(chart3);
        charts.add(chart4);
        charts.add(chart5);
        charts.add(chart6);
    }

    public ArrayList<JCheckBox> selectedData;
    public ArrayList<CustomLineChart> charts;

    // <editor-fold desc="variables">
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JMenuBar menuBar;
    private JMenu settingsMenu;
    private JMenuItem generalSettingsMI;
    private JMenuItem consumerSettingsMI;
    private JMenuItem goodSettingsMI;
    private JMenu supervisorMenu;
    private JMenuItem consumerSupervisorMI;
    private JMenuItem supplierSupervisorMI;
    private JMenuItem goodSupervisorMI;
    private JMenu outputMenu;
    private JMenuItem outputHistoryMI;
    private JLabel label1;
    private JSpinner sNumSup;
    private JButton bInitProps;
    private JButton bInitGoods;
    private JLabel label2;
    public JSpinner sNumCon;
    public JLabel lDay;
    private JButton bSetup;
    private JButton bStep;
    private JButton bGo;
    private JLabel label3;
    public JSpinner sNumGpS;
    private JLabel label4;
    private JSlider sliderSpeed;
    private JLabel lSpeed;
    private JButton bStep7Days;
    private JSeparator separator1;
    private JPanel panel1;
    private JPanel panel2;
    private JLabel labelTotalIncome;
    private JLabel lTIncome;
    private JCheckBox selectLast7DaysIncome;
    private JLabel lLast7DaysIncome;
    private JLabel label6;
    private JLabel lTSoldNum;
    private JCheckBox selectLast7DaysSoldNum;
    private JLabel lLast7DaysSoldNum;
    private JLabel label7;
    private JLabel lTCost;
    private JCheckBox selectLast7DaysCost;
    private JLabel lLast7DaysCost;
    private JCheckBox selectLast7BasicIncome;
    private JLabel lLast7BasicIncome;
    private JCheckBox selectLast7ConvenientIncome;
    private JLabel lLast7ConvenientIncome;
    private JCheckBox selectLast7LuxuryIncome;
    private JLabel lLast7LuxuryIncome;
    private JCheckBox selectTotalBasicDemand;
    private JLabel lTBasicDemand;
    private JCheckBox selectTotalConvenientDemand;
    private JLabel lTConvenientDemand;
    private JCheckBox selectTotalLuxuryDemand;
    private JLabel lTLuxuryDemand;
    private CustomLineChart chart1;
    private CustomLineChart chart2;
    private CustomLineChart chart3;
    private CustomLineChart chart4;
    private CustomLineChart chart5;
    private CustomLineChart chart6;
    public JLabel tFeedBack;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    // </editor-fold>

}
