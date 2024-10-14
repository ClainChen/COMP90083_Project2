/*
 * Created by JFormDesigner on Sun Oct 13 22:44:38 AEDT 2024
 */

package com.example.model.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.*;
import javax.swing.event.*;

import com.example.model.Main;
import com.example.model.customChart.LineChart;
import com.example.model.utils.Enums;
import com.example.model.utils.Parameters;
import com.example.model.utils.Tools;
import com.formdev.flatlaf.FlatIntelliJLaf;

/**
 * @author Clain
 */
public class MainWindow extends JFrame {
    public MainWindow() {
        initComponents();
    }

    private void bSetup(ActionEvent e) {
        // TODO add your code here
        Main.setup();
        for (LineChart lc: charts){
            lc.resetData();
            lc.resetChart();
        }
        initCharts();
        resetCharts();
    }

    private void bGoOnce(ActionEvent e) {
        // TODO add your code here
        Main.go_once();
    }

    private void bGo(ActionEvent e) {
        // TODO add your code here
        Main.go();
    }

    private void sliderSpeedStateChanged(ChangeEvent e) {
        Main.speed = sliderSpeed.getValue();
        if (Main.currentABM != null){
            Main.currentABM.updateSpeed(Main.speed);
        }
        switch (Main.speed){
            case 1 -> lSpeed.setText("Speed: Very Slow");
            case 2 -> lSpeed.setText("Speed: Slow");
            case 3 -> lSpeed.setText("Speed: Normal");
            case 4 -> lSpeed.setText("Speed: Fast");
            case 5 -> lSpeed.setText("Speed: Very Fast");
        }
    }

    private void lBuyerStrategy(ActionEvent e) {
        switch (Objects.requireNonNull(lBuyerStrategy.getSelectedItem()).toString()){
            case "NORMAL" -> Parameters.buyerStrategy = Enums.BuyerStrategy.NORMAL;
            case "AGGRESSIVE" -> Parameters.buyerStrategy = Enums.BuyerStrategy.AGGRESSIVE;
            case "RANDOM" -> Parameters.buyerStrategy = Enums.BuyerStrategy.RANDOM;
            case "MIXUP" -> Parameters.buyerStrategy = Enums.BuyerStrategy.MIX_UP;
        }

        if (Main.currentABM != null){
            Main.currentABM.buyerManager.updateStrategies();
        }
    }

    private void lSellerStrategy(ActionEvent e) {
        switch (Objects.requireNonNull(lSellerStrategy.getSelectedItem()).toString()){
            case "NORMAL" -> Parameters.sellerStrategy = Enums.SellerStrategy.NORMAL;
            case "AGGRESSIVE" -> Parameters.sellerStrategy = Enums.SellerStrategy.AGGRESSIVE;
            case "RANDOM" -> Parameters.sellerStrategy = Enums.SellerStrategy.RANDOM;
            case "MIXUP" -> Parameters.sellerStrategy = Enums.SellerStrategy.MIX_UP;
        }

        if (Main.currentABM != null){
            Main.currentABM.sellerManager.updateStrategies();
        }
    }

    private void sBudgetStateChanged(ChangeEvent e) {
        Parameters.startingBudget = (double) sBudget.getValue();
    }

    private void sNumBuyersStateChanged(ChangeEvent e) {
        Parameters.numBuyers = (int) sNumBuyers.getValue();
    }

    private void sNumSellersStateChanged(ChangeEvent e) {
        Parameters.numSellers = (int) sNumSellers.getValue();
    }



    private void initComponents() {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        panel4 = new JPanel();
        bSetup = new JButton();
        bGoOnce = new JButton();
        bGo = new JButton();
        lDay = new JLabel();
        lSpeed = new JLabel();
        sliderSpeed = new JSlider();
        panel1 = new JPanel();
        panel2 = new JPanel();
        label2 = new JLabel();
        lBuyerStrategy = new JComboBox<>();
        label3 = new JLabel();
        lSellerStrategy = new JComboBox<>();
        label4 = new JLabel();
        sBudget = new JSpinner();
        label5 = new JLabel();
        sNumBuyers = new JSpinner();
        label6 = new JLabel();
        sNumSellers = new JSpinner();
        label7 = new JLabel();
        lASP = new JLabel();
        label8 = new JLabel();
        lpSMU = new JLabel();
        label10 = new JLabel();
        lNumSoldOut = new JLabel();
        label11 = new JLabel();
        lNumSatisfied = new JLabel();
        panel3 = new JPanel();
        chart1 = new LineChart();
        chart2 = new LineChart();
        chart3 = new LineChart();
        chart4 = new LineChart();
        chart5 = new LineChart();
        chart6 = new LineChart();
        chart7 = new LineChart();
        chart8 = new LineChart();
        chart9 = new LineChart();

        //======== this ========
        setMinimumSize(new Dimension(1280, 960));
        setMaximumSize(new Dimension(1600, 1200));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        ((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {0, 0};
        ((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {0, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
        ((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("ABM History");

                //---- menuItem1 ----
                menuItem1.setText("Select Simed ABM");
                menu1.add(menuItem1);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //======== panel4 ========
        {
            panel4.setLayout(new GridBagLayout());
            ((GridBagLayout)panel4.getLayout()).columnWidths = new int[] {0, 141, 0, 0};
            ((GridBagLayout)panel4.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
            ((GridBagLayout)panel4.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel4.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

            //---- bSetup ----
            bSetup.setText("setup");
            bSetup.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
            bSetup.addActionListener(e -> bSetup(e));
            panel4.add(bSetup, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(2, 2, 7, 7), 0, 0));

            //---- bGoOnce ----
            bGoOnce.setText("go-once");
            bGoOnce.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
            bGoOnce.addActionListener(e -> bGoOnce(e));
            panel4.add(bGoOnce, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(2, 2, 7, 7), 0, 0));

            //---- bGo ----
            bGo.setText("go");
            bGo.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
            bGo.addActionListener(e -> bGo(e));
            panel4.add(bGo, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(2, 2, 7, 2), 0, 0));

            //---- lDay ----
            lDay.setText("Day: 0");
            lDay.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
            panel4.add(lDay, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- lSpeed ----
            lSpeed.setText("Speed: Fast");
            lSpeed.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
            panel4.add(lSpeed, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 5, 5), 0, 0));

            //---- sliderSpeed ----
            sliderSpeed.setMaximum(5);
            sliderSpeed.setMinimum(1);
            sliderSpeed.setMinorTickSpacing(1);
            sliderSpeed.setValue(4);
            sliderSpeed.setSnapToTicks(true);
            sliderSpeed.setPaintTicks(true);
            sliderSpeed.addChangeListener(e -> sliderSpeedStateChanged(e));
            panel4.add(sliderSpeed, new GridBagConstraints(0, 2, 3, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        contentPane.add(panel4, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //======== panel1 ========
        {
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

            //======== panel2 ========
            {
                panel2.setLayout(new GridBagLayout());
                ((GridBagLayout)panel2.getLayout()).columnWidths = new int[] {0, 0, 0};
                ((GridBagLayout)panel2.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                ((GridBagLayout)panel2.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
                ((GridBagLayout)panel2.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                //---- label2 ----
                label2.setText("Buyer Strategy");
                label2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(label2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- lBuyerStrategy ----
                lBuyerStrategy.setModel(new DefaultComboBoxModel<>(new String[] {
                    "NORMAL",
                    "AGGRESSIVE",
                    "RANDOM",
                    "MIXUP"
                }));
                lBuyerStrategy.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                lBuyerStrategy.addActionListener(e -> lBuyerStrategy(e));
                panel2.add(lBuyerStrategy, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- label3 ----
                label3.setText("Seller Strategy");
                label3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(label3, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- lSellerStrategy ----
                lSellerStrategy.setModel(new DefaultComboBoxModel<>(new String[] {
                    "NORMAL",
                    "AGGRESSIVE",
                    "RANDOM",
                    "MIXUP"
                }));
                lSellerStrategy.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                lSellerStrategy.addActionListener(e -> lSellerStrategy(e));
                panel2.add(lSellerStrategy, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- label4 ----
                label4.setText("Starting Budget");
                label4.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(label4, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- sBudget ----
                sBudget.setModel(new SpinnerNumberModel(500, 0, null, 1));
                sBudget.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                sBudget.addChangeListener(e -> sBudgetStateChanged(e));
                panel2.add(sBudget, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- label5 ----
                label5.setText("Num Buyers");
                label5.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(label5, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- sNumBuyers ----
                sNumBuyers.setModel(new SpinnerNumberModel(50, 10, null, 1));
                sNumBuyers.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                sNumBuyers.addChangeListener(e -> sNumBuyersStateChanged(e));
                panel2.add(sNumBuyers, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- label6 ----
                label6.setText("Num Sellers");
                label6.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(label6, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- sNumSellers ----
                sNumSellers.setModel(new SpinnerNumberModel(50, 10, null, 1));
                sNumSellers.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                sNumSellers.addChangeListener(e -> sNumSellersStateChanged(e));
                panel2.add(sNumSellers, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- label7 ----
                label7.setText("Avg Sale Price: ");
                label7.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(label7, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- lASP ----
                lASP.setText("0");
                lASP.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(lASP, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- label8 ----
                label8.setText("% Start Money Used: ");
                label8.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(label8, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- lpSMU ----
                lpSMU.setText("0");
                lpSMU.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(lpSMU, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- label10 ----
                label10.setText("Num Sold out: ");
                label10.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(label10, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- lNumSoldOut ----
                lNumSoldOut.setText("0");
                lNumSoldOut.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(lNumSoldOut, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- label11 ----
                label11.setText("Num Satisfied: ");
                label11.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(label11, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- lNumSatisfied ----
                lNumSatisfied.setText("0");
                lNumSatisfied.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(lNumSatisfied, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 0), 0, 0));
            }
            panel1.add(panel2, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(4, 4, 4, 9), 0, 0));

            //======== panel3 ========
            {
                panel3.setLayout(new GridBagLayout());
                ((GridBagLayout)panel3.getLayout()).columnWidths = new int[] {0, 0, 0, 0};
                ((GridBagLayout)panel3.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0};
                ((GridBagLayout)panel3.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout)panel3.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};

                //---- chart1 ----
                chart1.setPreferredSize(new Dimension(420, 260));
                panel3.add(chart1, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- chart2 ----
                chart2.setPreferredSize(new Dimension(420, 260));
                panel3.add(chart2, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- chart3 ----
                chart3.setPreferredSize(new Dimension(420, 260));
                panel3.add(chart3, new GridBagConstraints(2, 0, 1, 1, 1.0, 1.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- chart4 ----
                chart4.setPreferredSize(new Dimension(420, 260));
                panel3.add(chart4, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- chart5 ----
                chart5.setPreferredSize(new Dimension(420, 260));
                panel3.add(chart5, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- chart6 ----
                chart6.setPreferredSize(new Dimension(420, 260));
                panel3.add(chart6, new GridBagConstraints(2, 1, 1, 1, 1.0, 1.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- chart7 ----
                chart7.setPreferredSize(new Dimension(420, 260));
                panel3.add(chart7, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- chart8 ----
                chart8.setPreferredSize(new Dimension(420, 260));
                panel3.add(chart8, new GridBagConstraints(1, 2, 1, 1, 1.0, 1.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- chart9 ----
                chart9.setPreferredSize(new Dimension(420, 260));
                panel3.add(chart9, new GridBagConstraints(2, 2, 1, 1, 1.0, 1.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));
            }
            panel1.add(panel3, new GridBagConstraints(1, 0, 1, 1, 3.0, 3.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(4, 4, 4, 4), 0, 0));
        }
        contentPane.add(panel1, new GridBagConstraints(0, 1, 1, 1, 1.0, 9.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(2, 2, 2, 2), 0, 0));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on

        charts = new ArrayList<>();
        charts.add(chart1);
        charts.add(chart2);
        charts.add(chart3);
        charts.add(chart4);
        charts.add(chart5);
        charts.add(chart6);
        charts.add(chart7);
        charts.add(chart8);
        charts.add(chart9);

        Timer timer = new Timer(20, e -> {
            if (isVisible()){
                Tools.refreshFrame(this);
            }
        });
        timer.start();
    }

    private void initCharts(){
        chart1.setTitle("Price");
        chart1.setxLabel("Day");
        chart1.setyLabel("Price");
        chart1.addSeries("Max Price");
        chart1.addSeries("Min Price");
        chart1.addSeries("Avg Price");

        chart2.setTitle("Expectation");
        chart2.setxLabel("Day");
        chart2.setyLabel("Expectation");
        chart2.addSeries("Max Expectation");
        chart2.addSeries("Min Expectation");
        chart2.addSeries("Avg Expectation");

        chart3.setTitle("Goods Sell Track");
        chart3.setxLabel("Day");
        chart3.setyLabel("Num of Goods");
        chart3.addSeries("Total Inventory");
        chart3.addSeries("Total Demand");
        chart3.addSeries("Total Sold");

        chart4.setTitle("Average Trade Price");
        chart4.setxLabel("Day");
        chart4.setyLabel("Trade Price");
        chart4.addSeries("Avg Trade Price");

        chart5.setTitle("Average Spent");
        chart5.setxLabel("Day");
        chart5.setyLabel("Spent");
        chart5.addSeries("Avg Spent");

        chart6.setTitle("Avg Income");
        chart6.setxLabel("Day");
        chart6.setyLabel("Income");
        chart6.addSeries("Avg Income");

        chart7.setTitle("Budget");
        chart7.setxLabel("Day");
        chart7.setyLabel("Budget");
        chart7.addSeries("Max Budget");
        chart7.addSeries("Min Budget");
        chart7.addSeries("Avg Budget");


    }

    public void updateCharts(int day){
        try{
            chart1.addData("Max Price", day, Main.currentABM.supervisor.maxPriceHistory.getLast());
            chart1.addData("Min Price", day, Main.currentABM.supervisor.minPriceHistory.getLast());
            chart1.addData("Avg Price", day, Main.currentABM.supervisor.avgPriceHistory.getLast());
            chart2.addData("Max Expectation", day, Main.currentABM.supervisor.maxExpectationHistory.getLast());
            chart2.addData("Min Expectation", day, Main.currentABM.supervisor.minExpectationHistory.getLast());
            chart2.addData("Avg Expectation", day, Main.currentABM.supervisor.avgExpectationHistory.getLast());
            chart3.addData("Total Inventory", day, Main.currentABM.supervisor.totalInventoryHistory.getLast());
            chart3.addData("Total Demand", day, Main.currentABM.supervisor.totalDemandHistory.getLast());
            chart3.addData("Total Sold", day, Main.currentABM.supervisor.totalBoughtHistory.getLast());
            chart4.addData("Avg Trade Price", day, Main.currentABM.supervisor.avgTradePriceHistory.getLast());
            chart5.addData("Avg Spent", day, Main.currentABM.supervisor.avgSpentHistory.getLast());
            chart6.addData("Avg Income", day, Main.currentABM.supervisor.avgIncomeHistory.getLast());
            chart7.addData("Max Budget", day, Main.currentABM.supervisor.maxBudgetHistory.getLast());
            chart7.addData("Min Budget", day, Main.currentABM.supervisor.minBudgetHistory.getLast());
            chart7.addData("Avg Budget", day, Main.currentABM.supervisor.avgBudgetHistory.getLast());

            resetCharts();
        }catch (Exception _){
            System.out.println("Update Charts Error");
        }

    }

    private void resetCharts(){
        for (LineChart c: charts){
            c.resetChart();
        }
    }

    public ArrayList<LineChart> charts;

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JPanel panel4;
    private JButton bSetup;
    private JButton bGoOnce;
    private JButton bGo;
    public JLabel lDay;
    private JLabel lSpeed;
    private JSlider sliderSpeed;
    private JPanel panel1;
    private JPanel panel2;
    private JLabel label2;
    private JComboBox<String> lBuyerStrategy;
    private JLabel label3;
    private JComboBox<String> lSellerStrategy;
    private JLabel label4;
    private JSpinner sBudget;
    private JLabel label5;
    private JSpinner sNumBuyers;
    private JLabel label6;
    private JSpinner sNumSellers;
    private JLabel label7;
    public JLabel lASP;
    private JLabel label8;
    public JLabel lpSMU;
    private JLabel label10;
    public JLabel lNumSoldOut;
    private JLabel label11;
    public JLabel lNumSatisfied;
    private JPanel panel3;
    private LineChart chart1;
    private LineChart chart2;
    private LineChart chart3;
    private LineChart chart4;
    private LineChart chart5;
    private LineChart chart6;
    private LineChart chart7;
    private LineChart chart8;
    private LineChart chart9;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
