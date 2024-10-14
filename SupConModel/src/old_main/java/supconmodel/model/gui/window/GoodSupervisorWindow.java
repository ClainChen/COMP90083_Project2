/*
 * Created by JFormDesigner on Mon Oct 07 12:48:56 AEDT 2024
 */

package supconmodel.model.gui.window;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

import supconmodel.SupConModelMain;
import supconmodel.model.agents.GoodSummarizer;
import supconmodel.model.agents.Supplier;
import supconmodel.model.customChart.*;
import supconmodel.utils.Tools;

/**
 * @author Clain
 */
public class GoodSupervisorWindow extends JFrame {
    public GoodSupervisorWindow() {
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
        updateGoodSupervise();
    }

    private void tCurrentSupplierIDStateChanged(ChangeEvent e) {
        try{
            int id = (int) tCurrentGoodID.getValue();
            currentGoodID = id;
            updateGoodSupervise();
        }catch (Exception ex){
            Tools.feedback("Switch Good Failed, possibly because of didn't setup", 3);
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        tCurrentGoodID = new JSpinner();
        panel2 = new JPanel();
        panel1 = new JPanel();
        label1 = new JLabel();
        lName = new JLabel();
        label9 = new JLabel();
        lInventory = new JLabel();
        label10 = new JLabel();
        lProduction = new JLabel();
        label8 = new JLabel();
        lCost = new JLabel();
        label2 = new JLabel();
        lExpectProfit = new JLabel();
        label6 = new JLabel();
        lPrice = new JLabel();
        label3 = new JLabel();
        lBasicSatis = new JLabel();
        label7 = new JLabel();
        lConvSatis = new JLabel();
        label4 = new JLabel();
        lLuxurySatis = new JLabel();
        panel3 = new JPanel();
        customLineChart1 = new CustomLineChart();
        customLineChart2 = new CustomLineChart();

        //======== this ========
        setMinimumSize(new Dimension(800, 600));
        setPreferredSize(new Dimension(800, 600));
        setTitle("Good Supervisor");
        setAlwaysOnTop(true);
        var contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        ((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {0, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
        ((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

        //---- tCurrentGoodID ----
        tCurrentGoodID.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        tCurrentGoodID.setModel(new SpinnerListModel());
        tCurrentGoodID.addChangeListener(e -> tCurrentSupplierIDStateChanged(e));
        contentPane.add(tCurrentGoodID, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
            new Insets(5, 5, 10, 10), 0, 0));

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
                ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 77, 0};
                ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
                ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                //---- label1 ----
                label1.setText("Name");
                label1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- lName ----
                lName.setText("500000");
                lName.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(lName, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 2), 0, 0));

                //---- label9 ----
                label9.setText("Inventory");
                label9.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(label9, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- lInventory ----
                lInventory.setText("0");
                lInventory.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(lInventory, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- label10 ----
                label10.setText("Production");
                label10.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(label10, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- lProduction ----
                lProduction.setText("0");
                lProduction.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(lProduction, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- label8 ----
                label8.setText("Cost");
                label8.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(label8, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- lCost ----
                lCost.setText("0");
                lCost.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(lCost, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- label2 ----
                label2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                label2.setText("Expect Profitability");
                panel1.add(label2, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- lExpectProfit ----
                lExpectProfit.setText("0");
                lExpectProfit.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(lExpectProfit, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 2), 0, 0));

                //---- label6 ----
                label6.setText("Price");
                label6.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(label6, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- lPrice ----
                lPrice.setText("0");
                lPrice.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(lPrice, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 2), 0, 0));

                //---- label3 ----
                label3.setText("Basic Satisfaction");
                label3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(label3, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- lBasicSatis ----
                lBasicSatis.setText("0");
                lBasicSatis.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(lBasicSatis, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 2), 0, 0));

                //---- label7 ----
                label7.setText("Convenience Satisfaction");
                label7.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(label7, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- lConvSatis ----
                lConvSatis.setText("0");
                lConvSatis.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(lConvSatis, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 2), 0, 0));

                //---- label4 ----
                label4.setText("Luixury Satisfaction");
                label4.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(label4, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 2, 7), 0, 0));

                //---- lLuxurySatis ----
                lLuxurySatis.setText("0");
                lLuxurySatis.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(lLuxurySatis, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 2, 2), 0, 0));
            }
            panel2.add(panel1, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
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
        contentPane.add(panel2, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public void updateGoodSupervise(){
        if (!SupConModelMain.simulator.goodController.totalGoods.isEmpty()){
            GoodSummarizer good = SupConModelMain.simulator.goodController.getGoodSummarizer(currentGoodID);
            lName.setText(good.name);
            lInventory.setText(String.valueOf(good.getTotalInventory(SupConModelMain.simulator.supplierController.suppliers)));
            lProduction.setText(String.valueOf(good.getLastProduction(SupConModelMain.simulator.supplierController.suppliers)));
            lCost.setText(String.format("%.3f", good.cost));
            lExpectProfit.setText(String.format("%.3f", good.expectProfitability));
            lPrice.setText(String.format("%.3f", good.getAvgPrice(SupConModelMain.simulator.supplierController.suppliers)));
            lBasicSatis.setText(String.format("%.3f", good.basic));
            lConvSatis.setText(String.format("%.3f", good.convenient));
            lLuxurySatis.setText(String.format("%.3f", good.luxury));
        }
    }

    public void updateGoodSpinner(){
        ArrayList<Integer> goodSpinnerList = new ArrayList<>();
        for (GoodSummarizer good: SupConModelMain.simulator.goodController.totalGoods){
            goodSpinnerList.add(good.id);
        }
        if (goodSpinnerList.isEmpty()){
            return;
        }
        tCurrentGoodID.setModel(new SpinnerListModel(goodSpinnerList));
        currentGoodID = goodSpinnerList.getFirst();
        tCurrentGoodID.setValue(goodSpinnerList.getFirst());
    }

    public int currentGoodID = 0;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    public JSpinner tCurrentGoodID;
    private JPanel panel2;
    private JPanel panel1;
    private JLabel label1;
    private JLabel lName;
    private JLabel label9;
    private JLabel lInventory;
    private JLabel label10;
    private JLabel lProduction;
    private JLabel label8;
    private JLabel lCost;
    private JLabel label2;
    private JLabel lExpectProfit;
    private JLabel label6;
    private JLabel lPrice;
    private JLabel label3;
    private JLabel lBasicSatis;
    private JLabel label7;
    private JLabel lConvSatis;
    private JLabel label4;
    private JLabel lLuxurySatis;
    private JPanel panel3;
    private CustomLineChart customLineChart1;
    private CustomLineChart customLineChart2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
