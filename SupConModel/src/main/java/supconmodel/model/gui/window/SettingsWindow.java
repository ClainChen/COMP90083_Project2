/*
 * Created by JFormDesigner on Sat Sep 28 19:13:24 AEST 2024
 */

package supconmodel.model.gui.window;

import com.formdev.flatlaf.FlatLightLaf;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import org.jdesktop.beansbinding.*;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

/**
 * @author Clain
 */
public class SettingsWindow extends JFrame {
    public SettingsWindow() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        tabbedPane1 = new JTabbedPane();
        pGeneralSettings = new JScrollPane();
        panel2 = new JPanel();
        pConsumerSettings = new JScrollPane();
        panel1 = new JPanel();
        lLevel1 = new JLabel();
        separator1 = new JSeparator();
        lBasic1 = new JLabel();
        sliderBasic1 = new JSlider();
        label1 = new JLabel();
        lConvenient1 = new JLabel();
        sliderConvenient1 = new JSlider();
        label2 = new JLabel();
        lLuxury1 = new JLabel();
        sliderLuxury1 = new JSlider();
        label3 = new JLabel();
        lProfit1 = new JLabel();
        textProfit1 = new JTextField();
        lDefaultDeposit1 = new JLabel();
        textDefaultDeposit1 = new JTextField();
        lLevel2 = new JLabel();
        separator2 = new JSeparator();
        lBasic2 = new JLabel();
        sliderBasic2 = new JSlider();
        label4 = new JLabel();
        lConvenient2 = new JLabel();
        sliderConvenient2 = new JSlider();
        label5 = new JLabel();
        lLuxury2 = new JLabel();
        sliderLuxury2 = new JSlider();
        label7 = new JLabel();
        lProfit2 = new JLabel();
        textProfit2 = new JTextField();
        lDefaultDeposit2 = new JLabel();
        textDefaultDeposit2 = new JTextField();
        lLevel3 = new JLabel();
        separator3 = new JSeparator();
        lBasic3 = new JLabel();
        sliderBasic3 = new JSlider();
        label8 = new JLabel();
        lConvenient3 = new JLabel();
        sliderConvenient3 = new JSlider();
        label9 = new JLabel();
        lLuxury3 = new JLabel();
        sliderLuxury3 = new JSlider();
        label10 = new JLabel();
        lProfit3 = new JLabel();
        textProfit3 = new JTextField();
        lDefaultDeposit3 = new JLabel();
        textDefaultDeposit3 = new JTextField();
        pGoodsSettings = new JScrollPane();
        panel7 = new JPanel();
        tChooseGoodName = new JTextField();
        bShowAllGoods = new JButton();
        bChoose = new JButton();
        tCurrentGoodName = new JLabel();
        panel3 = new JPanel();
        separator5 = new JSeparator();
        lName = new JLabel();
        tName = new JTextField();
        lType = new JLabel();
        cbType = new JComboBox<>();
        lCost = new JLabel();
        tCost = new JTextField();
        lEProfit = new JLabel();
        tEProfit = new JTextField();
        label6 = new JLabel();
        separator4 = new JSeparator();
        lBasicDemand = new JLabel();
        tBasicDemand = new JTextField();
        lConvenienceDemand = new JLabel();
        tConvenienceDemand = new JTextField();
        lLuxuryDemand = new JLabel();
        tLuxuryDemand = new JTextField();
        panel4 = new JPanel();
        bAdd = new JButton();
        bChange = new JButton();
        bCopy = new JButton();
        bPaste = new JButton();
        panel6 = new JPanel();
        hSpacer1 = new JPanel(null);
        bSave = new JButton();
        bCancel = new JButton();
        bApply = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(800, 600));
        setPreferredSize(new Dimension(800, 600));
        setMaximumSize(new Dimension(800, 600));
        setTitle("Settings");
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== tabbedPane1 ========
        {
            tabbedPane1.setTabPlacement(SwingConstants.LEFT);
            tabbedPane1.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
            tabbedPane1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));

            //======== pGeneralSettings ========
            {

                //======== panel2 ========
                {
                    panel2.setLayout(new GridBagLayout());
                    ((GridBagLayout)panel2.getLayout()).columnWidths = new int[] {0, 0, 0};
                    ((GridBagLayout)panel2.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
                    ((GridBagLayout)panel2.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
                    ((GridBagLayout)panel2.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
                }
                pGeneralSettings.setViewportView(panel2);
            }
            tabbedPane1.addTab("General", pGeneralSettings);

            //======== pConsumerSettings ========
            {

                //======== panel1 ========
                {
                    panel1.setLayout(new GridBagLayout());
                    ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 135, 0, 42, 0, 0};
                    ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                    ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                    ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                    //---- lLevel1 ----
                    lLevel1.setText("Level 1");
                    lLevel1.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
                    panel1.add(lLevel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(5, 5, 5, 5), 0, 0));

                    //---- separator1 ----
                    separator1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(separator1, new GridBagConstraints(1, 0, 4, 1, 1.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(5, 0, 5, 0), 0, 0));

                    //---- lBasic1 ----
                    lBasic1.setText("Basic Demand");
                    lBasic1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(lBasic1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- sliderBasic1 ----
                    sliderBasic1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(sliderBasic1, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- label1 ----
                    label1.setText("text");
                    panel1.add(label1, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                        new Insets(2, 2, 7, 7), 0, 0));

                    //---- lConvenient1 ----
                    lConvenient1.setText("Convenient Demand");
                    lConvenient1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(lConvenient1, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- sliderConvenient1 ----
                    sliderConvenient1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(sliderConvenient1, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- label2 ----
                    label2.setText("text");
                    panel1.add(label2, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                        new Insets(2, 2, 7, 7), 0, 0));

                    //---- lLuxury1 ----
                    lLuxury1.setText("Luxury Demand");
                    lLuxury1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(lLuxury1, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- sliderLuxury1 ----
                    sliderLuxury1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(sliderLuxury1, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- label3 ----
                    label3.setText("text");
                    panel1.add(label3, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                        new Insets(2, 2, 7, 7), 0, 0));

                    //---- lProfit1 ----
                    lProfit1.setText("Profit");
                    lProfit1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(lProfit1, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- textProfit1 ----
                    textProfit1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(textProfit1, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- lDefaultDeposit1 ----
                    lDefaultDeposit1.setText("Defaul Deposit");
                    lDefaultDeposit1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(lDefaultDeposit1, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- textDefaultDeposit1 ----
                    textDefaultDeposit1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(textDefaultDeposit1, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- lLevel2 ----
                    lLevel2.setText("Level 2");
                    lLevel2.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
                    panel1.add(lLevel2, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(5, 5, 5, 5), 0, 0));

                    //---- separator2 ----
                    separator2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(separator2, new GridBagConstraints(1, 6, 4, 1, 1.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(5, 0, 5, 0), 0, 0));

                    //---- lBasic2 ----
                    lBasic2.setText("Basic Demand");
                    lBasic2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(lBasic2, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- sliderBasic2 ----
                    sliderBasic2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(sliderBasic2, new GridBagConstraints(2, 7, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- label4 ----
                    label4.setText("text");
                    panel1.add(label4, new GridBagConstraints(3, 7, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                        new Insets(2, 2, 7, 7), 0, 0));

                    //---- lConvenient2 ----
                    lConvenient2.setText("Convenient Demand");
                    lConvenient2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(lConvenient2, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- sliderConvenient2 ----
                    sliderConvenient2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(sliderConvenient2, new GridBagConstraints(2, 8, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- label5 ----
                    label5.setText("text");
                    panel1.add(label5, new GridBagConstraints(3, 8, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                        new Insets(2, 2, 7, 7), 0, 0));

                    //---- lLuxury2 ----
                    lLuxury2.setText("Luxury Demand");
                    lLuxury2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(lLuxury2, new GridBagConstraints(1, 9, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- sliderLuxury2 ----
                    sliderLuxury2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(sliderLuxury2, new GridBagConstraints(2, 9, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- label7 ----
                    label7.setText("text");
                    panel1.add(label7, new GridBagConstraints(3, 9, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                        new Insets(2, 2, 7, 7), 0, 0));

                    //---- lProfit2 ----
                    lProfit2.setText("Profit");
                    lProfit2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(lProfit2, new GridBagConstraints(1, 10, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- textProfit2 ----
                    textProfit2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(textProfit2, new GridBagConstraints(2, 10, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- lDefaultDeposit2 ----
                    lDefaultDeposit2.setText("Defaul Deposit");
                    lDefaultDeposit2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(lDefaultDeposit2, new GridBagConstraints(1, 11, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- textDefaultDeposit2 ----
                    textDefaultDeposit2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(textDefaultDeposit2, new GridBagConstraints(2, 11, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- lLevel3 ----
                    lLevel3.setText("Level 3");
                    lLevel3.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
                    panel1.add(lLevel3, new GridBagConstraints(0, 12, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(5, 5, 5, 5), 0, 0));

                    //---- separator3 ----
                    separator3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(separator3, new GridBagConstraints(1, 12, 4, 1, 1.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(5, 0, 5, 0), 0, 0));

                    //---- lBasic3 ----
                    lBasic3.setText("Basic Demand");
                    lBasic3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(lBasic3, new GridBagConstraints(1, 13, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- sliderBasic3 ----
                    sliderBasic3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(sliderBasic3, new GridBagConstraints(2, 13, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- label8 ----
                    label8.setText("text");
                    panel1.add(label8, new GridBagConstraints(3, 13, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                        new Insets(2, 2, 7, 7), 0, 0));

                    //---- lConvenient3 ----
                    lConvenient3.setText("Convenient Demand");
                    lConvenient3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(lConvenient3, new GridBagConstraints(1, 14, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- sliderConvenient3 ----
                    sliderConvenient3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(sliderConvenient3, new GridBagConstraints(2, 14, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- label9 ----
                    label9.setText("text");
                    panel1.add(label9, new GridBagConstraints(3, 14, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                        new Insets(2, 2, 7, 7), 0, 0));

                    //---- lLuxury3 ----
                    lLuxury3.setText("Luxury Demand");
                    lLuxury3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(lLuxury3, new GridBagConstraints(1, 15, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- sliderLuxury3 ----
                    sliderLuxury3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(sliderLuxury3, new GridBagConstraints(2, 15, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- label10 ----
                    label10.setText("text");
                    panel1.add(label10, new GridBagConstraints(3, 15, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                        new Insets(2, 2, 7, 7), 0, 0));

                    //---- lProfit3 ----
                    lProfit3.setText("Profit");
                    lProfit3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(lProfit3, new GridBagConstraints(1, 16, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- textProfit3 ----
                    textProfit3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(textProfit3, new GridBagConstraints(2, 16, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- lDefaultDeposit3 ----
                    lDefaultDeposit3.setText("Defaul Deposit");
                    lDefaultDeposit3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(lDefaultDeposit3, new GridBagConstraints(1, 17, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));

                    //---- textDefaultDeposit3 ----
                    textDefaultDeposit3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(textDefaultDeposit3, new GridBagConstraints(2, 17, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));
                }
                pConsumerSettings.setViewportView(panel1);
            }
            tabbedPane1.addTab("Consumer", pConsumerSettings);

            //======== pGoodsSettings ========
            {

                //======== panel7 ========
                {
                    panel7.setLayout(new GridBagLayout());
                    ((GridBagLayout)panel7.getLayout()).columnWidths = new int[] {99, 0, 0, 0, 0};
                    ((GridBagLayout)panel7.getLayout()).rowHeights = new int[] {0, 0, 0};
                    ((GridBagLayout)panel7.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
                    ((GridBagLayout)panel7.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};
                    panel7.add(tChooseGoodName, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(5, 5, 10, 7), 0, 0));

                    //---- bShowAllGoods ----
                    bShowAllGoods.setIcon(new ImageIcon(getClass().getResource("/image/search-analytics.png")));
                    bShowAllGoods.setMinimumSize(null);
                    bShowAllGoods.setPreferredSize(null);
                    panel7.add(bShowAllGoods, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(5, 2, 10, 10), 0, 0));

                    //---- bChoose ----
                    bChoose.setText("Choose");
                    bChoose.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel7.add(bChoose, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(5, 5, 10, 10), 0, 0));
                    panel7.add(tCurrentGoodName, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 0, 5, 0), 0, 0));

                    //======== panel3 ========
                    {
                        panel3.setLayout(new GridBagLayout());
                        ((GridBagLayout)panel3.getLayout()).columnWidths = new int[] {0, 91, 0, 0};
                        ((GridBagLayout)panel3.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                        ((GridBagLayout)panel3.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
                        ((GridBagLayout)panel3.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                        panel3.add(separator5, new GridBagConstraints(0, 0, 3, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- lName ----
                        lName.setText("Name");
                        lName.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        panel3.add(lName, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                            new Insets(2, 2, 7, 7), 0, 0));
                        panel3.add(tName, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(2, 2, 7, 7), 0, 0));

                        //---- lType ----
                        lType.setText("Type");
                        lType.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        lType.setLabelFor(tChooseGoodName);
                        panel3.add(lType, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                            GridBagConstraints.EAST, GridBagConstraints.NONE,
                            new Insets(2, 2, 7, 7), 0, 0));

                        //---- cbType ----
                        cbType.setModel(new DefaultComboBoxModel<>(new String[] {
                            "basic",
                            "convenience",
                            "luxury"
                        }));
                        cbType.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        panel3.add(cbType, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.NONE,
                            new Insets(2, 2, 7, 7), 0, 0));

                        //---- lCost ----
                        lCost.setText("Cost");
                        lCost.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        lCost.setLabelFor(tCost);
                        panel3.add(lCost, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                            new Insets(2, 2, 7, 7), 0, 0));
                        panel3.add(tCost, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                            new Insets(2, 2, 7, 7), 0, 0));

                        //---- lEProfit ----
                        lEProfit.setText("Expected Profitability");
                        lEProfit.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        lEProfit.setLabelFor(tEProfit);
                        panel3.add(lEProfit, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                            new Insets(2, 2, 7, 7), 0, 0));
                        panel3.add(tEProfit, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                            new Insets(2, 2, 7, 7), 0, 0));

                        //---- label6 ----
                        label6.setText("Satisfaction Rates");
                        label6.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
                        panel3.add(label6, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                            new Insets(2, 2, 7, 7), 0, 0));
                        panel3.add(separator4, new GridBagConstraints(1, 5, 2, 1, 1.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- lBasicDemand ----
                        lBasicDemand.setText("Basic");
                        lBasicDemand.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        lBasicDemand.setLabelFor(tBasicDemand);
                        panel3.add(lBasicDemand, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
                            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                            new Insets(2, 2, 7, 7), 0, 0));
                        panel3.add(tBasicDemand, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                            new Insets(2, 2, 7, 7), 0, 0));

                        //---- lConvenienceDemand ----
                        lConvenienceDemand.setText("Convenience");
                        lConvenienceDemand.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        lConvenienceDemand.setLabelFor(tConvenienceDemand);
                        panel3.add(lConvenienceDemand, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0,
                            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                            new Insets(2, 2, 7, 7), 0, 0));
                        panel3.add(tConvenienceDemand, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                            new Insets(2, 2, 7, 7), 0, 0));

                        //---- lLuxuryDemand ----
                        lLuxuryDemand.setText("Luxury");
                        lLuxuryDemand.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        lLuxuryDemand.setLabelFor(tLuxuryDemand);
                        panel3.add(lLuxuryDemand, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0,
                            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                            new Insets(2, 2, 7, 7), 0, 0));
                        panel3.add(tLuxuryDemand, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                            new Insets(2, 2, 7, 7), 0, 0));

                        //======== panel4 ========
                        {
                            panel4.setLayout(new GridBagLayout());
                            ((GridBagLayout)panel4.getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0};
                            ((GridBagLayout)panel4.getLayout()).rowHeights = new int[] {0, 0};
                            ((GridBagLayout)panel4.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
                            ((GridBagLayout)panel4.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

                            //---- bAdd ----
                            bAdd.setText("Add");
                            bAdd.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                            panel4.add(bAdd, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
                                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                                new Insets(0, 0, 0, 5), 0, 0));

                            //---- bChange ----
                            bChange.setText("Change");
                            bChange.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                            panel4.add(bChange, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0,
                                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                                new Insets(0, 0, 0, 5), 0, 0));

                            //---- bCopy ----
                            bCopy.setText("Copy");
                            bCopy.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                            panel4.add(bCopy, new GridBagConstraints(2, 0, 1, 1, 1.0, 0.0,
                                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                                new Insets(0, 0, 0, 5), 0, 0));

                            //---- bPaste ----
                            bPaste.setText("Paste");
                            bPaste.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                            panel4.add(bPaste, new GridBagConstraints(3, 0, 1, 1, 1.0, 0.0,
                                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                                new Insets(0, 0, 0, 0), 0, 0));
                        }
                        panel3.add(panel4, new GridBagConstraints(0, 10, 3, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                            new Insets(0, 0, 5, 0), 0, 0));
                    }
                    panel7.add(panel3, new GridBagConstraints(0, 1, 4, 1, 1.0, 1.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
                }
                pGoodsSettings.setViewportView(panel7);
            }
            tabbedPane1.addTab("Goods", pGoodsSettings);
        }
        contentPane.add(tabbedPane1, BorderLayout.CENTER);

        //======== panel6 ========
        {
            panel6.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, true));
            panel6.setLayout(new GridBagLayout());
            ((GridBagLayout)panel6.getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0};
            ((GridBagLayout)panel6.getLayout()).rowHeights = new int[] {0, 0};
            ((GridBagLayout)panel6.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel6.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};
            panel6.add(hSpacer1, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));

            //---- bSave ----
            bSave.setText("Save");
            bSave.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
            panel6.add(bSave, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(4, 4, 4, 9), 0, 0));

            //---- bCancel ----
            bCancel.setText("Cancel");
            bCancel.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
            panel6.add(bCancel, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(4, 4, 4, 9), 0, 0));

            //---- bApply ----
            bApply.setText("Apply");
            bApply.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
            panel6.add(bApply, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(4, 4, 4, 4), 0, 0));
        }
        contentPane.add(panel6, BorderLayout.PAGE_END);
        pack();
        setLocationRelativeTo(getOwner());

        //---- bindings ----
        bindingGroup = new BindingGroup();
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            sliderBasic1, BeanProperty.create("value"),
            label1, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            sliderConvenient1, BeanProperty.create("value"),
            label2, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            sliderLuxury1, BeanProperty.create("value"),
            label3, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            sliderBasic2, BeanProperty.create("value"),
            label4, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            sliderConvenient2, BeanProperty.create("value"),
            label5, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            sliderLuxury2, BeanProperty.create("value"),
            label7, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            sliderBasic3, BeanProperty.create("value"),
            label8, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            sliderConvenient3, BeanProperty.create("value"),
            label9, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            sliderLuxury3, BeanProperty.create("value"),
            label10, BeanProperty.create("text")));
        bindingGroup.bind();
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on

        try {
            // 设置 Arc Orange 样式
            FlatLightLaf.setup();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    public JTabbedPane tabbedPane1;
    private JScrollPane pGeneralSettings;
    private JPanel panel2;
    private JScrollPane pConsumerSettings;
    private JPanel panel1;
    private JLabel lLevel1;
    private JSeparator separator1;
    private JLabel lBasic1;
    public JSlider sliderBasic1;
    private JLabel label1;
    private JLabel lConvenient1;
    public JSlider sliderConvenient1;
    private JLabel label2;
    private JLabel lLuxury1;
    public JSlider sliderLuxury1;
    private JLabel label3;
    private JLabel lProfit1;
    public JTextField textProfit1;
    private JLabel lDefaultDeposit1;
    public JTextField textDefaultDeposit1;
    private JLabel lLevel2;
    private JSeparator separator2;
    private JLabel lBasic2;
    public JSlider sliderBasic2;
    private JLabel label4;
    private JLabel lConvenient2;
    public JSlider sliderConvenient2;
    private JLabel label5;
    private JLabel lLuxury2;
    public JSlider sliderLuxury2;
    private JLabel label7;
    private JLabel lProfit2;
    public JTextField textProfit2;
    private JLabel lDefaultDeposit2;
    public JTextField textDefaultDeposit2;
    private JLabel lLevel3;
    private JSeparator separator3;
    private JLabel lBasic3;
    public JSlider sliderBasic3;
    private JLabel label8;
    private JLabel lConvenient3;
    public JSlider sliderConvenient3;
    private JLabel label9;
    private JLabel lLuxury3;
    public JSlider sliderLuxury3;
    private JLabel label10;
    private JLabel lProfit3;
    public JTextField textProfit3;
    private JLabel lDefaultDeposit3;
    public JTextField textDefaultDeposit3;
    private JScrollPane pGoodsSettings;
    private JPanel panel7;
    public JTextField tChooseGoodName;
    public JButton bShowAllGoods;
    private JButton bChoose;
    public JLabel tCurrentGoodName;
    private JPanel panel3;
    private JSeparator separator5;
    private JLabel lName;
    public JTextField tName;
    private JLabel lType;
    public JComboBox<String> cbType;
    private JLabel lCost;
    public JTextField tCost;
    private JLabel lEProfit;
    public JTextField tEProfit;
    private JLabel label6;
    private JSeparator separator4;
    private JLabel lBasicDemand;
    public JTextField tBasicDemand;
    private JLabel lConvenienceDemand;
    public JTextField tConvenienceDemand;
    private JLabel lLuxuryDemand;
    public JTextField tLuxuryDemand;
    private JPanel panel4;
    public JButton bAdd;
    public JButton bChange;
    public JButton bCopy;
    public JButton bPaste;
    private JPanel panel6;
    private JPanel hSpacer1;
    public JButton bSave;
    public JButton bCancel;
    public JButton bApply;
    private BindingGroup bindingGroup;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
