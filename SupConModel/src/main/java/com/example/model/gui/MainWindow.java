/*
 * Created by JFormDesigner on Sun Oct 13 22:44:38 AEDT 2024
 */

package com.example.model.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import com.example.model.Main;
import com.example.model.customChart.LineChart;
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



    private void initComponents() {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel4 = new JPanel();
        bSetup = new JButton();
        bGoOnce = new JButton();
        bGo = new JButton();
        lDay = new JLabel();
        panel1 = new JPanel();
        panel2 = new JPanel();
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

        //======== panel4 ========
        {
            panel4.setLayout(new GridBagLayout());
            ((GridBagLayout)panel4.getLayout()).columnWidths = new int[] {0, 0, 0, 0};
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
            panel4.add(lDay, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));
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
                ((GridBagLayout)panel2.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
                ((GridBagLayout)panel2.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
                ((GridBagLayout)panel2.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
            }
            panel1.add(panel2, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));

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
                new Insets(0, 0, 0, 0), 0, 0));
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


    }

    public void updateCharts(int day){
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

        resetCharts();
    }

    private void resetCharts(){
        for (LineChart c: charts){
            c.resetChart();
        }
    }

    public ArrayList<LineChart> charts;

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel4;
    private JButton bSetup;
    private JButton bGoOnce;
    private JButton bGo;
    public JLabel lDay;
    private JPanel panel1;
    private JPanel panel2;
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
