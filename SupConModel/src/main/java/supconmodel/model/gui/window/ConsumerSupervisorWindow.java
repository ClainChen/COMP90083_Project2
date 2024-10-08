/*
 * Created by JFormDesigner on Tue Oct 01 13:52:44 AEST 2024
 */

package supconmodel.model.gui.window;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import supconmodel.SupConModelMain;
import supconmodel.model.customChart.*;
import supconmodel.utils.Tools;

/**
 * @author Clain
 */
public class ConsumerSupervisorWindow extends JFrame {
    public ConsumerSupervisorWindow() {
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
        supervisingID = 0;
        tCurrentConsumerID.setValue(0);
        updateConsumerSupervise();
    }

    private void tCurrentConsumerIDStateChanged(ChangeEvent e) {
        try{
            supervisingID = Math.clamp((int) tCurrentConsumerID.getValue(), 0, SupConModelMain.simulator.consumerController.consumers.size() - 1);
            tCurrentConsumerID.setValue(supervisingID);
            updateConsumerSupervise();
        }catch (Exception ex){
            Tools.feedback("Switch Consumer failed, possibly because of didn't setup", 3);
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        hSpacer1 = new JPanel(null);
        tCurrentConsumerID = new JSpinner();
        hSpacer2 = new JPanel(null);
        panel1 = new JPanel();
        panel2 = new JPanel();
        label1 = new JLabel();
        lLevel = new JLabel();
        label2 = new JLabel();
        proBasicDemand = new JProgressBar();
        label3 = new JLabel();
        proConvenientDemand = new JProgressBar();
        label4 = new JLabel();
        proLuxuryDemand = new JProgressBar();
        label5 = new JLabel();
        lBasicIncome = new JLabel();
        label6 = new JLabel();
        lIncome = new JLabel();
        label7 = new JLabel();
        lDeposit = new JLabel();
        panel3 = new JPanel();
        customLineChart1 = new CustomLineChart();
        customLineChart2 = new CustomLineChart();

        //======== this ========
        setMinimumSize(new Dimension(800, 600));
        setPreferredSize(new Dimension(800, 600));
        setTitle("Consumer Supervisor");
        setBackground(SystemColor.window);
        setAlwaysOnTop(true);
        var contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        ((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {0, 0, 53, 0, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {0, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

        //---- hSpacer1 ----
        hSpacer1.setForeground(Color.white);
        contentPane.add(hSpacer1, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 10, 10), 0, 0));

        //---- tCurrentConsumerID ----
        tCurrentConsumerID.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        tCurrentConsumerID.setModel(new SpinnerNumberModel(0, 0, null, 1));
        tCurrentConsumerID.addChangeListener(e -> tCurrentConsumerIDStateChanged(e));
        contentPane.add(tCurrentConsumerID, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
            new Insets(5, 5, 10, 10), 0, 0));

        //---- hSpacer2 ----
        hSpacer2.setForeground(Color.white);
        contentPane.add(hSpacer2, new GridBagConstraints(4, 0, 1, 1, 1.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(5, 5, 10, 5), 0, 0));

        //======== panel1 ========
        {
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 1.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {1.0, 1.0E-4};

            //======== panel2 ========
            {
                panel2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.setBackground(Color.white);
                panel2.setMinimumSize(new Dimension(300, 280));
                panel2.setLayout(new GridBagLayout());
                ((GridBagLayout)panel2.getLayout()).columnWidths = new int[] {0, 0, 0};
                ((GridBagLayout)panel2.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                ((GridBagLayout)panel2.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
                ((GridBagLayout)panel2.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                //---- label1 ----
                label1.setText("Level");
                label1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(5, 5, 10, 10), 0, 0));

                //---- lLevel ----
                lLevel.setText("1");
                lLevel.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(lLevel, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(5, 5, 10, 5), 0, 0));

                //---- label2 ----
                label2.setText("Basic Demand");
                label2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(label2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(5, 5, 10, 10), 0, 0));

                //---- proBasicDemand ----
                proBasicDemand.setValue(90);
                proBasicDemand.setForeground(Color.blue);
                proBasicDemand.setBackground(Color.lightGray);
                proBasicDemand.setStringPainted(true);
                proBasicDemand.setFont(new Font("JetBrains Mono", Font.BOLD, 10));
                panel2.add(proBasicDemand, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(5, 5, 10, 5), 0, 0));

                //---- label3 ----
                label3.setText("Convenient Demand");
                label3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(label3, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(5, 5, 10, 10), 0, 0));

                //---- proConvenientDemand ----
                proConvenientDemand.setBackground(Color.lightGray);
                proConvenientDemand.setValue(90);
                proConvenientDemand.setStringPainted(true);
                proConvenientDemand.setFont(new Font("JetBrains Mono", Font.BOLD, 10));
                proConvenientDemand.setForeground(Color.blue);
                panel2.add(proConvenientDemand, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(5, 5, 10, 5), 0, 0));

                //---- label4 ----
                label4.setText("Luxury Demand");
                label4.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(label4, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(5, 5, 10, 10), 0, 0));

                //---- proLuxuryDemand ----
                proLuxuryDemand.setBackground(Color.lightGray);
                proLuxuryDemand.setValue(90);
                proLuxuryDemand.setStringPainted(true);
                proLuxuryDemand.setFont(new Font("JetBrains Mono", Font.BOLD, 10));
                proLuxuryDemand.setForeground(Color.blue);
                panel2.add(proLuxuryDemand, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(5, 5, 10, 5), 0, 0));

                //---- label5 ----
                label5.setText("Basic Income");
                label5.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(label5, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(5, 5, 10, 10), 0, 0));

                //---- lBasicIncome ----
                lBasicIncome.setText("5000");
                lBasicIncome.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(lBasicIncome, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(5, 5, 10, 5), 0, 0));

                //---- label6 ----
                label6.setText("Current Income");
                label6.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(label6, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(5, 5, 10, 10), 0, 0));

                //---- lIncome ----
                lIncome.setText("8000");
                lIncome.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(lIncome, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(5, 5, 10, 5), 0, 0));

                //---- label7 ----
                label7.setText("Deposit");
                label7.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(label7, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(5, 5, 10, 10), 0, 0));

                //---- lDeposit ----
                lDeposit.setText("48000");
                lDeposit.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(lDeposit, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(5, 5, 10, 5), 0, 0));
            }
            panel1.add(panel2, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));

            //======== panel3 ========
            {
                panel3.setBackground(Color.white);
                panel3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel3.setMinimumSize(new Dimension(400, 400));
                panel3.setPreferredSize(new Dimension(400, 400));
                panel3.setMaximumSize(new Dimension(2147483647, 2147483647));
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
            panel1.add(panel3, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        }
        contentPane.add(panel1, new GridBagConstraints(0, 1, 5, 1, 1.0, 1.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public void updateConsumerSupervise(){
        if (!SupConModelMain.simulator.consumerController.consumers.isEmpty()){
            lLevel.setText(String.valueOf(SupConModelMain.simulator.consumerController.consumers.get(supervisingID).level));
            proBasicDemand.setValue((int) Math.round(SupConModelMain.simulator.consumerController.consumers.get(supervisingID).basicDemand));
            proConvenientDemand.setValue((int) Math.round(SupConModelMain.simulator.consumerController.consumers.get(supervisingID).convenientDemand));
            proLuxuryDemand.setValue((int) Math.round(SupConModelMain.simulator.consumerController.consumers.get(supervisingID).luxuryDemand));
            lBasicIncome.setText(String.valueOf(SupConModelMain.simulator.consumerController.consumers.get(supervisingID).basicIncome));
            lIncome.setText(String.valueOf(SupConModelMain.simulator.consumerController.consumers.get(supervisingID).income));
            lDeposit.setText(String.valueOf(SupConModelMain.simulator.consumerController.consumers.get(supervisingID).deposit));
        }
    }

    public int supervisingID = 0;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel hSpacer1;
    public JSpinner tCurrentConsumerID;
    private JPanel hSpacer2;
    private JPanel panel1;
    private JPanel panel2;
    private JLabel label1;
    private JLabel lLevel;
    private JLabel label2;
    private JProgressBar proBasicDemand;
    private JLabel label3;
    private JProgressBar proConvenientDemand;
    private JLabel label4;
    private JProgressBar proLuxuryDemand;
    private JLabel label5;
    private JLabel lBasicIncome;
    private JLabel label6;
    private JLabel lIncome;
    private JLabel label7;
    private JLabel lDeposit;
    private JPanel panel3;
    private CustomLineChart customLineChart1;
    private CustomLineChart customLineChart2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
