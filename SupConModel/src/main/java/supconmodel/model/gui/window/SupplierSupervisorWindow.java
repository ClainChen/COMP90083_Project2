/*
 * Created by JFormDesigner on Tue Oct 01 13:52:14 AEST 2024
 */

package supconmodel.model.gui.window;

import java.awt.*;
import javax.swing.*;
import supconmodel.model.customChart.*;

/**
 * @author Clain
 */
public class SupplierSupervisorWindow extends JFrame {
    public SupplierSupervisorWindow() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        hSpacer1 = new JPanel(null);
        bPreviousSupplier = new JButton();
        lCurrentSupplierID = new JTextField();
        bNextSupplier = new JButton();
        hSpacer2 = new JPanel(null);
        panel2 = new JPanel();
        panel1 = new JPanel();
        label1 = new JLabel();
        lTotalProfit = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        label2 = new JLabel();
        lTotalCost = new JLabel();
        label3 = new JLabel();
        lLastProfit = new JLabel();
        label7 = new JLabel();
        llastCost = new JLabel();
        panel3 = new JPanel();
        customLineChart1 = new CustomLineChart();
        customLineChart2 = new CustomLineChart();

        //======== this ========
        setResizable(false);
        setMinimumSize(new Dimension(800, 600));
        setPreferredSize(new Dimension(800, 600));
        setTitle("Supplier Supervisor");
        var contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        ((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {0, 86, 37, 0, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {0, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

        //---- hSpacer1 ----
        hSpacer1.setForeground(Color.white);
        contentPane.add(hSpacer1, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 10, 10), 0, 0));

        //---- bPreviousSupplier ----
        bPreviousSupplier.setText("Previous");
        bPreviousSupplier.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        contentPane.add(bPreviousSupplier, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
            new Insets(5, 5, 10, 10), 0, 0));

        //---- lCurrentSupplierID ----
        lCurrentSupplierID.setText("1");
        lCurrentSupplierID.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        lCurrentSupplierID.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lCurrentSupplierID, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
            new Insets(5, 5, 10, 10), 0, 0));

        //---- bNextSupplier ----
        bNextSupplier.setText("Next");
        bNextSupplier.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        contentPane.add(bNextSupplier, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
            new Insets(5, 5, 10, 10), 0, 0));

        //---- hSpacer2 ----
        hSpacer2.setForeground(Color.white);
        contentPane.add(hSpacer2, new GridBagConstraints(4, 0, 1, 1, 1.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
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
                ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 77, 0};
                ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
                ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
                ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

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
                    new Insets(2, 2, 7, 2), 0, 0));

                //---- label8 ----
                label8.setText("Total Income");
                label8.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(label8, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label9 ----
                label9.setText("0");
                label9.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(label9, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 0), 0, 0));

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
                    new Insets(2, 2, 7, 2), 0, 0));

                //---- label3 ----
                label3.setText("Last Profit");
                label3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(label3, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 7), 0, 0));

                //---- lLastProfit ----
                lLastProfit.setText("0");
                lLastProfit.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel1.add(lLastProfit, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(2, 2, 7, 2), 0, 0));

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
                    new Insets(2, 2, 7, 2), 0, 0));
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
        contentPane.add(panel2, new GridBagConstraints(0, 1, 5, 1, 1.0, 1.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel hSpacer1;
    private JButton bPreviousSupplier;
    private JTextField lCurrentSupplierID;
    private JButton bNextSupplier;
    private JPanel hSpacer2;
    private JPanel panel2;
    private JPanel panel1;
    private JLabel label1;
    private JLabel lTotalProfit;
    private JLabel label8;
    private JLabel label9;
    private JLabel label2;
    private JLabel lTotalCost;
    private JLabel label3;
    private JLabel lLastProfit;
    private JLabel label7;
    private JLabel llastCost;
    private JPanel panel3;
    private CustomLineChart customLineChart1;
    private CustomLineChart customLineChart2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
