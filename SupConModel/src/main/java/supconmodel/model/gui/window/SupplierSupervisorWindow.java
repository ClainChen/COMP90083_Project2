/*
 * Created by JFormDesigner on Tue Oct 01 13:52:14 AEST 2024
 */

package supconmodel.model.gui.window;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

import supconmodel.SupConModelMain;
import supconmodel.model.agents.DynamicGood;
import supconmodel.model.agents.Supplier;
import supconmodel.model.customChart.*;
import supconmodel.utils.Tools;

/**
 * @author Clain
 */
public class SupplierSupervisorWindow extends JFrame {
    public SupplierSupervisorWindow() {
        initComponents();
    }

    @Override
    public void setVisible(boolean visible){
        super.setVisible(visible);
        if (visible){
            thisWindowOpened();
        }
    }

    private void thisWindowOpened() {
        updateGoodSpinner();
        updateSupplierSupervise();
        updateGoodSupervise();
    }

    public void tCurrentSupplierIDStateChanged(ChangeEvent e) {
        try{
            supervisingID = Math.clamp((int) tCurrentSupplierID.getValue(), 0, SupConModelMain.simulator.supplierController.suppliers.size() - 1);
            tCurrentSupplierID.setValue(supervisingID);

            updateGoodSpinner();
            updateSupplierSupervise();
            updateGoodSupervise();
        }catch (Exception ex){
            Tools.feedback("Switch Supplier Failed, possibly because of didn't setup", 3);
        }
    }

    private void sGoodStateChanged(ChangeEvent e) {
        try{
            supervisingGoodID = (int) sGood.getValue();
            updateGoodSupervise();
        }catch (Exception ex){
            Tools.feedback("Switch Supplier's Good Failed, possibly because of didn't setup", 3);
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        tCurrentSupplierID = new JSpinner();
        panel2 = new JPanel();
        panel1 = new JPanel();
        label1 = new JLabel();
        lTotalProfit = new JLabel();
        label8 = new JLabel();
        lTotalIncome = new JLabel();
        label2 = new JLabel();
        lTotalCost = new JLabel();
        label3 = new JLabel();
        lLastIncome = new JLabel();
        label7 = new JLabel();
        llastCost = new JLabel();
        separator1 = new JSeparator();
        sGood = new JSpinner();
        label5 = new JLabel();
        lgName = new JLabel();
        label28 = new JLabel();
        lgInventory = new JLabel();
        label29 = new JLabel();
        lgProduction = new JLabel();
        label6 = new JLabel();
        lgCost = new JLabel();
        label9 = new JLabel();
        lgEP = new JLabel();
        label10 = new JLabel();
        lgPrice = new JLabel();
        label11 = new JLabel();
        lgTotalIncome = new JLabel();
        label12 = new JLabel();
        lgLastIncome = new JLabel();
        label16 = new JLabel();
        lgTotalSoldNum = new JLabel();
        label17 = new JLabel();
        lgLastSoldNum = new JLabel();
        label13 = new JLabel();
        lgBasic = new JLabel();
        label14 = new JLabel();
        lgConvenient = new JLabel();
        label15 = new JLabel();
        lgLuxury = new JLabel();
        panel3 = new JPanel();
        customLineChart1 = new CustomLineChart();
        customLineChart2 = new CustomLineChart();

        //======== this ========
        setMinimumSize(new Dimension(800, 600));
        setPreferredSize(new Dimension(800, 600));
        setTitle("Supplier Supervisor");
        var contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        ((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {0, 32, 0};
        ((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {0, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
        ((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

        //---- tCurrentSupplierID ----
        tCurrentSupplierID.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        tCurrentSupplierID.setModel(new SpinnerNumberModel(0, 0, null, 1));
        tCurrentSupplierID.addChangeListener(e -> tCurrentSupplierIDStateChanged(e));
        contentPane.add(tCurrentSupplierID, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
            new Insets(5, 5, 10, 5), 0, 0));

        //======== panel2 ========
        {
            panel2.setLayout(new GridBagLayout());
            ((GridBagLayout)panel2.getLayout()).columnWidths = new int[] {0, 0, 0};
            ((GridBagLayout)panel2.getLayout()).rowHeights = new int[] {0, 0};
            ((GridBagLayout)panel2.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel2.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

            //======== panel1 ========
            {
                panel1.setForeground(Color.lightGray);
                panel1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.setBackground(Color.white);
                panel1.setMinimumSize(new Dimension(300, 300));
                panel1.setLayout(new GridBagLayout());
                ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 82, 0, 0};
                ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                //---- label1 ----
                label1.setText("Total Profit");
                label1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- lTotalProfit ----
                lTotalProfit.setText("500000");
                lTotalProfit.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(lTotalProfit, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- label8 ----
                label8.setText("Total Income");
                label8.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(label8, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- lTotalIncome ----
                lTotalIncome.setText("0");
                lTotalIncome.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(lTotalIncome, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label2 ----
                label2.setText("Total Cost");
                label2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(label2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- lTotalCost ----
                lTotalCost.setText("0");
                lTotalCost.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(lTotalCost, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- label3 ----
                label3.setText("Last Income");
                label3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(label3, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- lLastIncome ----
                lLastIncome.setText("0");
                lLastIncome.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(lLastIncome, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- label7 ----
                label7.setText("Last Cost");
                label7.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(label7, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- llastCost ----
                llastCost.setText("0");
                llastCost.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(llastCost, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));
                panel1.add(separator1, new GridBagConstraints(0, 5, 3, 1, 1.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- sGood ----
                sGood.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                sGood.setModel(new SpinnerListModel());
                sGood.addChangeListener(e -> sGoodStateChanged(e));
                panel1.add(sGood, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- label5 ----
                label5.setText("Name");
                label5.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(label5, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- lgName ----
                lgName.setText("Good Name");
                lgName.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(lgName, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- label28 ----
                label28.setText("Inventory");
                label28.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(label28, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- lgInventory ----
                lgInventory.setText("0");
                lgInventory.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(lgInventory, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- label29 ----
                label29.setText("Production");
                label29.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(label29, new GridBagConstraints(0, 9, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- lgProduction ----
                lgProduction.setText("0");
                lgProduction.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(lgProduction, new GridBagConstraints(1, 9, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label6 ----
                label6.setText("Cost");
                label6.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(label6, new GridBagConstraints(0, 10, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- lgCost ----
                lgCost.setText("0");
                lgCost.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(lgCost, new GridBagConstraints(1, 10, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- label9 ----
                label9.setText("Expect profitability");
                label9.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(label9, new GridBagConstraints(0, 11, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- lgEP ----
                lgEP.setText("0");
                lgEP.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(lgEP, new GridBagConstraints(1, 11, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- label10 ----
                label10.setText("Price");
                label10.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(label10, new GridBagConstraints(0, 12, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- lgPrice ----
                lgPrice.setText("0");
                lgPrice.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(lgPrice, new GridBagConstraints(1, 12, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- label11 ----
                label11.setText("Total income");
                label11.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(label11, new GridBagConstraints(0, 13, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- lgTotalIncome ----
                lgTotalIncome.setText("0");
                lgTotalIncome.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(lgTotalIncome, new GridBagConstraints(1, 13, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- label12 ----
                label12.setText("Last income");
                label12.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(label12, new GridBagConstraints(0, 14, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- lgLastIncome ----
                lgLastIncome.setText("0");
                lgLastIncome.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(lgLastIncome, new GridBagConstraints(1, 14, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- label16 ----
                label16.setText("Total sold num");
                label16.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(label16, new GridBagConstraints(0, 15, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- lgTotalSoldNum ----
                lgTotalSoldNum.setText("0");
                lgTotalSoldNum.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(lgTotalSoldNum, new GridBagConstraints(1, 15, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- label17 ----
                label17.setText("Last sold num");
                label17.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(label17, new GridBagConstraints(0, 16, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- lgLastSoldNum ----
                lgLastSoldNum.setText("0");
                lgLastSoldNum.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(lgLastSoldNum, new GridBagConstraints(1, 16, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- label13 ----
                label13.setText("Basic");
                label13.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(label13, new GridBagConstraints(0, 17, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- lgBasic ----
                lgBasic.setText("0");
                lgBasic.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(lgBasic, new GridBagConstraints(1, 17, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- label14 ----
                label14.setText("Convenient");
                label14.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(label14, new GridBagConstraints(0, 18, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- lgConvenient ----
                lgConvenient.setText("0");
                lgConvenient.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(lgConvenient, new GridBagConstraints(1, 18, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- label15 ----
                label15.setText("Luxury");
                label15.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(label15, new GridBagConstraints(0, 19, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 2, 7), 0, 0));

                //---- lgLuxury ----
                lgLuxury.setText("0");
                lgLuxury.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(lgLuxury, new GridBagConstraints(1, 19, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 2, 7), 0, 0));
            }
            panel2.add(panel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 10), 0, 0));

            //======== panel3 ========
            {
                panel3.setBackground(Color.white);
                panel3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel3.setForeground(Color.lightGray);
                panel3.setMinimumSize(new Dimension(400, 400));
                panel3.setLayout(new GridBagLayout());
                ((GridBagLayout)panel3.getLayout()).columnWidths = new int[] {0, 0};
                ((GridBagLayout)panel3.getLayout()).rowHeights = new int[] {0, 0, 0};
                ((GridBagLayout)panel3.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
                ((GridBagLayout)panel3.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};
                panel3.add(customLineChart1, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(5, 5, 10, 5), 0, 0));
                panel3.add(customLineChart2, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(5, 5, 5, 5), 0, 0));
            }
            panel2.add(panel3, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        }
        contentPane.add(panel2, new GridBagConstraints(0, 1, 2, 1, 1.0, 1.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public void updateSupplierSupervise(){
        if (!SupConModelMain.simulator.supplierController.suppliers.isEmpty()){
            double totalIncome = SupConModelMain.simulator.supplierController.suppliers.get(supervisingID).getTotalIncome();
            double totalCost = SupConModelMain.simulator.supplierController.suppliers.get(supervisingID).getTotalCost();
            lTotalProfit.setText(String.format("%.3f", totalIncome - totalCost));
            lTotalIncome.setText(String.format("%.3f", totalIncome));
            lTotalCost.setText(String.format("%.3f", totalCost));
            lLastIncome.setText(String.format("%.3f", SupConModelMain.simulator.supplierController.suppliers.get(supervisingID).getLastIncome()));
            llastCost.setText(String.format("%.3f", SupConModelMain.simulator.supplierController.suppliers.get(supervisingID).getLastCost()));
        }
    }

    public void updateGoodSupervise(){
        if (SupConModelMain.simulator.supplierController.suppliers.isEmpty()){
            return;
        }
        DynamicGood good = SupConModelMain.simulator.supplierController.suppliers.get(supervisingID).getGood(supervisingGoodID);
        lgName.setText(good.name);
        lgInventory.setText(String.valueOf(good.inventory));
        lgProduction.setText(String.valueOf(good.production));
        lgCost.setText(String.format("%.3f", good.cost));
        lgEP.setText(String.format("%.3f", good.expectProfitability));
        lgPrice.setText(String.format("%.3f", good.price));
        lgTotalIncome.setText(String.format("%.3f", good.totalIncome));
        double lastIncome = 0;
        if (!good.incomeHistory.isEmpty()){
            lastIncome = good.incomeHistory.getLast();
        }
        lgLastIncome.setText(String.format("%.3f", lastIncome));
        lgTotalSoldNum.setText(String.valueOf(good.totalSoldNum));
        int lastSoldNum = 0;
        if (!good.soldNumHistory.isEmpty()){
            lastSoldNum = good.soldNumHistory.getLast();
        }
        lgLastSoldNum.setText(String.valueOf(lastSoldNum));
        lgBasic.setText(String.format("%.3f", good.basic));
        lgConvenient.setText(String.format("%.3f", good.convenience));
        lgLuxury.setText(String.format("%.3f", good.luxury));
    }

    public void updateGoodSpinner(){
        ArrayList<Integer> goodSpinnerList = new ArrayList<>();
        if (SupConModelMain.simulator.supplierController.suppliers.isEmpty()){
            return;
        }
        for (DynamicGood good: SupConModelMain.simulator.supplierController.suppliers.get(supervisingID).goodList){
            goodSpinnerList.add(good.id);
        }
        sGood.setModel(new SpinnerListModel(goodSpinnerList));
        supervisingGoodID = goodSpinnerList.getFirst();
        sGood.setValue(goodSpinnerList.getFirst());
    }

    public int supervisingID = 0;
    public int supervisingGoodID = 0;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    public JSpinner tCurrentSupplierID;
    private JPanel panel2;
    private JPanel panel1;
    private JLabel label1;
    private JLabel lTotalProfit;
    private JLabel label8;
    private JLabel lTotalIncome;
    private JLabel label2;
    private JLabel lTotalCost;
    private JLabel label3;
    private JLabel lLastIncome;
    private JLabel label7;
    private JLabel llastCost;
    private JSeparator separator1;
    public JSpinner sGood;
    private JLabel label5;
    private JLabel lgName;
    private JLabel label28;
    private JLabel lgInventory;
    private JLabel label29;
    private JLabel lgProduction;
    private JLabel label6;
    private JLabel lgCost;
    private JLabel label9;
    private JLabel lgEP;
    private JLabel label10;
    private JLabel lgPrice;
    private JLabel label11;
    private JLabel lgTotalIncome;
    private JLabel label12;
    private JLabel lgLastIncome;
    private JLabel label16;
    private JLabel lgTotalSoldNum;
    private JLabel label17;
    private JLabel lgLastSoldNum;
    private JLabel label13;
    private JLabel lgBasic;
    private JLabel label14;
    private JLabel lgConvenient;
    private JLabel label15;
    private JLabel lgLuxury;
    private JPanel panel3;
    private CustomLineChart customLineChart1;
    private CustomLineChart customLineChart2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
