/*
 * Created by JFormDesigner on Sat Sep 28 19:13:24 AEST 2024
 */

package supconmodel.model.gui.window;

import java.awt.event.*;
import javax.swing.event.*;
import com.formdev.flatlaf.FlatLightLaf;

import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;
import javax.swing.*;
import javax.swing.border.*;

import supconmodel.SupConModelMain;
import supconmodel.model.agents.Good;
import supconmodel.utils.Enums.*;
import supconmodel.utils.Parameters;
import supconmodel.utils.Tools;

/**
 * @author Clain
 */
public class SettingsWindow extends JFrame {
    public SettingsWindow() {
        initComponents();
    }

    private void bChoose(ActionEvent e) {
        SupConModelMain.windowController.openSelectWindow(WindowEnum.GOOD_SELECT);
    }

    private void bAdd(ActionEvent e) {
        String name = tName.getText();
        if (name == null || name.trim().isEmpty()){
            Tools.feedback("Good name cannot be empty", 3);
            return;
        }
        SupConModelMain.maxGoodID += 1;
        int id = SupConModelMain.maxGoodID;
        GoodType type = GoodType.values()[cbType.getSelectedIndex()];
        double cost = (double) tCost.getValue();
        double ep = (double) tEProfit.getValue();
        double basic = (double) tBasicDemand.getValue();
        double convenience = (double) tConvenienceDemand.getValue();
        double luxury = (double) tLuxuryDemand.getValue();
        Good newGood = new Good(id, name, type, cost, ep, basic, convenience, luxury);

        SupConModelMain.allGoods.add(newGood);
        SupConModelMain.windowController.goodSelectWindow.ResetLayout();
    }

    private void bChange(ActionEvent e) {
        boolean succ = false;
        for (int i = 0; i < SupConModelMain.allGoods.size(); i++) {
            if (SupConModelMain.allGoods.get(i).id == Integer.parseInt(tID.getText())){
                SupConModelMain.allGoods.set(i, getCurrentGoodInfo());
                succ = true;
                break;
            }
        }
        SupConModelMain.windowController.goodSelectWindow.ResetLayout();
        if (!succ){
            Tools.feedback("Fail to change good: good not found", 3);
        }

    }

    private void bDelete(ActionEvent e) {
        boolean succ = false;
        for (Good good: SupConModelMain.allGoods){
            if (good.id == Integer.parseInt(tID.getText())){
                tID.setText("0");
                tName.setText("");
                cbType.setSelectedIndex(0);
                tCost.setValue(0);
                tEProfit.setValue(0);
                tBasicDemand.setValue(0);
                tConvenienceDemand.setValue(0);
                tLuxuryDemand.setValue(0);
                SupConModelMain.allGoods.remove(good);
                succ = true;
                break;
            }
        }
        if (!succ){
            Tools.feedback("Fail to delete: good not found", 3);
        }
    }

    private void bCopy(ActionEvent e) {
        goodClip = getCurrentGoodInfo();
    }

    private void bPaste(ActionEvent e) {
        tName.setText(goodClip.name);
        cbType.setSelectedIndex(goodClip.type.ordinal());
        tCost.setValue(goodClip.cost);
        tEProfit.setValue(goodClip.expectProfitability);
        tBasicDemand.setValue(goodClip.basic);
        tConvenienceDemand.setValue(goodClip.convenience);
        tLuxuryDemand.setValue(goodClip.luxury);
    }

    private Good getCurrentGoodInfo() {
        Good good = new Good();
        good.id = Integer.parseInt(tID.getText());
        good.name = tName.getText();
        good.type = GoodType.values()[cbType.getSelectedIndex()];
        good.cost = (double) tCost.getValue();
        good.expectProfitability = (double) tEProfit.getValue();
        good.basic = (double) tBasicDemand.getValue();
        good.convenience = (double) tConvenienceDemand.getValue();
        good.luxury = (double) tLuxuryDemand.getValue();
        return good;
    }

    public void initProperties(FileInputStream input){
        try{
            SupConModelMain.properties = new Properties();
            SupConModelMain.SetUpParameters(input);

            sL1.setValue(Parameters.init_l1_ratio);
            sL2.setValue(Parameters.init_l2_ratio);
            sL3.setValue(Parameters.init_l3_ratio);

            sProbDemand.setValue(Parameters.w_d);
            sProbPrice.setValue(Parameters.w_p);
            sProbPopular.setValue(Parameters.w_h);
            sProbCompetitive.setValue(Parameters.w_c);

            sProdPopular.setValue(Parameters.prod_a);
            sProdBasic.setValue(Parameters.prod_basic);
            sProdConvenient.setValue(Parameters.prod_convenient);
            sProdLuxury.setValue(Parameters.prod_luxury);

            sBasic1.setValue(Parameters.l1_init_basicDemand);
            sBasic2.setValue(Parameters.l2_init_basicDemand);
            sBasic3.setValue(Parameters.l3_init_basicDemand);

            sConvenient1.setValue(Parameters.l1_init_convenientDemand);
            sConvenient2.setValue(Parameters.l2_init_convenientDemand);
            sConvenient3.setValue(Parameters.l3_init_convenientDemand);

            sLuxury1.setValue(Parameters.l1_init_luxuryDemand);
            sLuxury2.setValue(Parameters.l2_init_luxuryDemand);
            sLuxury3.setValue(Parameters.l3_init_luxuryDemand);

            sProfit1.setValue(Parameters.l1_init_profit);
            sProfit2.setValue(Parameters.l2_init_profit);
            sProfit3.setValue(Parameters.l3_init_profit);
        }catch (Exception ex){
            throw new RuntimeException("Error reading properties file: " + ex.getMessage());
        }

    }

    private void bSave(ActionEvent e) {
        try{
            // Save Properties
            updateParameters();
            SupConModelMain.SaveProperties();

            // Save Goods
            SupConModelMain.SaveGoods();
            SupConModelMain.windowController.closeSelectWindow(WindowEnum.SETTING);
            bakSettings();
            Tools.feedback("Successfully save the settings", 1);
        }catch (RuntimeException re){
            Tools.feedback("Fail to save settings \n" + re.getMessage(), 3);
            re.printStackTrace();
        }
    }

    private void bApply(ActionEvent e) {
        updateParameters();
        bakSettings();
    }

    private void bCancel(ActionEvent e) {
        SupConModelMain.windowController.closeSelectWindow(WindowEnum.SETTING);
    }

    private void updateParameters(){
        Parameters.init_l1_ratio = (double) sL1.getValue();
        Parameters.init_l2_ratio = (double) sL2.getValue();
        Parameters.init_l3_ratio = (double) sL3.getValue();

        Parameters.w_d = (double) sProbDemand.getValue();
        Parameters.w_p = (double) sProbPrice.getValue();
        Parameters.w_h = (double) sProbPopular.getValue();
        Parameters.w_c = (double) sProbCompetitive.getValue();

        Parameters.prod_a = (double) sProdPopular.getValue();
        Parameters.prod_basic = (double) sProdBasic.getValue();
        Parameters.prod_convenient = (double) sProdConvenient.getValue();
        Parameters.prod_luxury = (double) sProdLuxury.getValue();

        Parameters.l1_init_basicDemand = (double) sBasic1.getValue();
        Parameters.l2_init_basicDemand = (double) sBasic2.getValue();
        Parameters.l3_init_basicDemand = (double) sBasic3.getValue();

        Parameters.l1_init_convenientDemand = (double) sConvenient1.getValue();
        Parameters.l2_init_convenientDemand = (double) sConvenient2.getValue();
        Parameters.l3_init_convenientDemand = (double) sConvenient3.getValue();

        Parameters.l1_init_luxuryDemand = (double) sLuxury1.getValue();
        Parameters.l2_init_luxuryDemand = (double) sLuxury2.getValue();
        Parameters.l3_init_luxuryDemand = (double) sLuxury3.getValue();

        Parameters.l1_init_profit = (double) sProfit1.getValue();
        Parameters.l2_init_profit = (double) sProfit2.getValue();
        Parameters.l3_init_profit = (double) sProfit3.getValue();
    }

    private void thisWindowOpened() {
        System.out.println("Settings window opened");
        bakSettings();
    }

    private void thisWindowClose() {
        System.out.println("Settings window closing");

        sL1.setValue(bak.sL1);
        sL2.setValue(bak.sL2);
        sL3.setValue(bak.sL3);

        sProbDemand.setValue(bak.sProbDemand);
        sProbPrice.setValue(bak.sProbPrice);
        sProbPopular.setValue(bak.sProbPopular);
        sProbCompetitive.setValue(bak.sProbCompetitive);

        sProdPopular.setValue(bak.sProdPopular);
        sProdBasic.setValue(bak.sProdBasic);
        sProdConvenient.setValue(bak.sProdConvenient);
        sProdLuxury.setValue(bak.sProdLuxury);

        sBasic1.setValue(bak.sBasic1);
        sBasic2.setValue(bak.sBasic2);
        sBasic3.setValue(bak.sBasic3);

        sConvenient1.setValue(bak.sConvenient1);
        sConvenient2.setValue(bak.sConvenient2);
        sConvenient3.setValue(bak.sConvenient3);

        sLuxury1.setValue(bak.sLuxury1);
        sLuxury2.setValue(bak.sLuxury2);
        sLuxury3.setValue(bak.sLuxury3);

        sProfit1.setValue(bak.sProfit1);
        sProfit2.setValue(bak.sProfit2);
        sProfit3.setValue(bak.sProfit3);
    }

    @Override
    public void setVisible(boolean visible){
        super.setVisible(visible);
        if (visible){
            thisWindowOpened();
        }else{
            thisWindowClose();
        }
    }

    private void bakSettings(){
        bak.sL1 = (double) sL1.getValue();
        bak.sL2 = (double) sL2.getValue();
        bak.sL3 = (double) sL3.getValue();

        bak.sProbDemand = (double) sProbDemand.getValue();
        bak.sProbPrice = (double) sProbPrice.getValue();
        bak.sProbPopular = (double) sProbPopular.getValue();
        bak.sProbCompetitive = (double) sProbCompetitive.getValue();

        bak.sProdPopular = (double) sProdPopular.getValue();
        bak.sProdBasic = (double) sProdBasic.getValue();
        bak.sProdConvenient = (double) sProdConvenient.getValue();
        bak.sProdLuxury = (double) sProdLuxury.getValue();

        bak.sBasic1 = (double) sBasic1.getValue();
        bak.sBasic2 = (double) sBasic2.getValue();
        bak.sBasic3 = (double) sBasic3.getValue();

        bak.sConvenient1 = (double) sConvenient1.getValue();
        bak.sConvenient2 = (double) sConvenient2.getValue();
        bak.sConvenient3 = (double) sConvenient3.getValue();

        bak.sLuxury1 = (double) sLuxury1.getValue();
        bak.sLuxury2 = (double) sLuxury2.getValue();
        bak.sLuxury3 = (double) sLuxury3.getValue();

        bak.sProfit1 = (double) sProfit1.getValue();
        bak.sProfit2 = (double) sProfit2.getValue();
        bak.sProfit3 = (double) sProfit3.getValue();
    }

    private void levelRatioRespond(ChangeEvent e){
        double total = (double) sL1.getValue() + (double) sL2.getValue() + (double) sL3.getValue();
        double ratio1 = (double) sL1.getValue() / total;
        double ratio2 = (double) sL2.getValue() / total;
        double ratio3 = (double) sL3.getValue() / total;
        lL1.setText(new BigDecimal(ratio1).setScale(2, RoundingMode.HALF_UP).toString());
        lL2.setText(new BigDecimal(ratio2).setScale(2, RoundingMode.HALF_UP).toString());
        lL3.setText(new BigDecimal(ratio3).setScale(2, RoundingMode.HALF_UP).toString());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        tabbedPane1 = new JTabbedPane();
        pGeneralSettings = new JScrollPane();
        panel2 = new JPanel();
        label25 = new JLabel();
        separator8 = new JSeparator();
        label26 = new JLabel();
        sL1 = new JSpinner();
        lL1 = new JLabel();
        label27 = new JLabel();
        sL2 = new JSpinner();
        lL2 = new JLabel();
        label28 = new JLabel();
        sL3 = new JSpinner();
        lL3 = new JLabel();
        label16 = new JLabel();
        separator6 = new JSeparator();
        label12 = new JLabel();
        sProbDemand = new JSpinner();
        label13 = new JLabel();
        sProbPrice = new JSpinner();
        label14 = new JLabel();
        sProbPopular = new JSpinner();
        label15 = new JLabel();
        sProbCompetitive = new JSpinner();
        label17 = new JLabel();
        separator7 = new JSeparator();
        label18 = new JLabel();
        sProdPopular = new JSpinner();
        label19 = new JLabel();
        sProdBasic = new JSpinner();
        label20 = new JLabel();
        sProdConvenient = new JSpinner();
        label21 = new JLabel();
        sProdLuxury = new JSpinner();
        pConsumerSettings = new JScrollPane();
        panel1 = new JPanel();
        lLevel1 = new JLabel();
        separator1 = new JSeparator();
        lBasic1 = new JLabel();
        sBasic1 = new JSpinner();
        lConvenient1 = new JLabel();
        sConvenient1 = new JSpinner();
        lLuxury1 = new JLabel();
        sLuxury1 = new JSpinner();
        lProfit1 = new JLabel();
        sProfit1 = new JSpinner();
        lLevel2 = new JLabel();
        separator2 = new JSeparator();
        lBasic2 = new JLabel();
        sBasic2 = new JSpinner();
        lConvenient2 = new JLabel();
        sConvenient2 = new JSpinner();
        lLuxury2 = new JLabel();
        sLuxury2 = new JSpinner();
        lProfit2 = new JLabel();
        sProfit2 = new JSpinner();
        lLevel3 = new JLabel();
        separator3 = new JSeparator();
        lBasic3 = new JLabel();
        sBasic3 = new JSpinner();
        lConvenient3 = new JLabel();
        sConvenient3 = new JSpinner();
        lLuxury3 = new JLabel();
        sLuxury3 = new JSpinner();
        lProfit3 = new JLabel();
        sProfit3 = new JSpinner();
        pGoodsSettings = new JScrollPane();
        panel7 = new JPanel();
        bChoose = new JButton();
        panel3 = new JPanel();
        separator5 = new JSeparator();
        label11 = new JLabel();
        tID = new JLabel();
        lName = new JLabel();
        tName = new JTextField();
        lType = new JLabel();
        cbType = new JComboBox<>();
        lCost = new JLabel();
        tCost = new JSpinner();
        lEProfit = new JLabel();
        tEProfit = new JSpinner();
        label6 = new JLabel();
        separator4 = new JSeparator();
        lBasicDemand = new JLabel();
        tBasicDemand = new JSpinner();
        lConvenienceDemand = new JLabel();
        tConvenienceDemand = new JSpinner();
        lLuxuryDemand = new JLabel();
        tLuxuryDemand = new JSpinner();
        panel4 = new JPanel();
        bAdd = new JButton();
        bChange = new JButton();
        bCopy = new JButton();
        bPaste = new JButton();
        bDelete = new JButton();
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
        setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== tabbedPane1 ========
        {
            tabbedPane1.setTabPlacement(SwingConstants.LEFT);
            tabbedPane1.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
            tabbedPane1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));

            //======== pGeneralSettings ========
            {
                pGeneralSettings.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));

                //======== panel2 ========
                {
                    panel2.setLayout(new GridBagLayout());
                    ((GridBagLayout)panel2.getLayout()).columnWidths = new int[] {0, 0, 51, 0, 0};
                    ((GridBagLayout)panel2.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                    ((GridBagLayout)panel2.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
                    ((GridBagLayout)panel2.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                    //---- label25 ----
                    label25.setText("Consumer initial level ratio");
                    label25.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
                    panel2.add(label25, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                        new Insets(3, 3, 8, 8), 0, 0));
                    panel2.add(separator8, new GridBagConstraints(1, 0, 3, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 5, 0), 0, 0));

                    //---- label26 ----
                    label26.setText("Level 1");
                    label26.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel2.add(label26, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                        new Insets(5, 5, 10, 10), 0, 0));

                    //---- sL1 ----
                    sL1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    sL1.setModel(new SpinnerNumberModel(0.7, 0.0, null, 0.05));
                    sL1.addChangeListener(e -> levelRatioRespond(e));
                    panel2.add(sL1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(2, 2, 7, 7), 0, 0));

                    //---- lL1 ----
                    lL1.setText("0");
                    lL1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel2.add(lL1, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- label27 ----
                    label27.setText("Level 2");
                    label27.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel2.add(label27, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                        new Insets(5, 5, 10, 10), 0, 0));

                    //---- sL2 ----
                    sL2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    sL2.setModel(new SpinnerNumberModel(0.2, 0.0, null, 0.05));
                    sL2.addChangeListener(e -> levelRatioRespond(e));
                    panel2.add(sL2, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(2, 2, 7, 7), 0, 0));

                    //---- lL2 ----
                    lL2.setText("0");
                    lL2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel2.add(lL2, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- label28 ----
                    label28.setText("Level 3");
                    label28.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel2.add(label28, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                        GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                        new Insets(5, 5, 10, 10), 0, 0));

                    //---- sL3 ----
                    sL3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    sL3.setModel(new SpinnerNumberModel(0.1, 0.0, null, 0.05));
                    sL3.addChangeListener(e -> levelRatioRespond(e));
                    panel2.add(sL3, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(2, 2, 7, 7), 0, 0));

                    //---- lL3 ----
                    lL3.setText("0");
                    lL3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel2.add(lL3, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- label16 ----
                    label16.setText("Weight factors for Buy Probability");
                    label16.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
                    panel2.add(label16, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                        GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                        new Insets(5, 5, 10, 10), 0, 0));
                    panel2.add(separator6, new GridBagConstraints(1, 4, 3, 1, 1.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(5, 5, 10, 5), 0, 0));

                    //---- label12 ----
                    label12.setText("Demand");
                    label12.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel2.add(label12, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
                        GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                        new Insets(2, 2, 7, 7), 0, 0));

                    //---- sProbDemand ----
                    sProbDemand.setModel(new SpinnerNumberModel(0.5, 0.0, 1.0, 0.05));
                    sProbDemand.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel2.add(sProbDemand, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(2, 2, 7, 7), 0, 0));

                    //---- label13 ----
                    label13.setText("Price");
                    label13.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel2.add(label13, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
                        GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                        new Insets(2, 2, 7, 7), 0, 0));

                    //---- sProbPrice ----
                    sProbPrice.setModel(new SpinnerNumberModel(0.5, 0.0, 1.0, 0.05));
                    sProbPrice.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel2.add(sProbPrice, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(2, 2, 7, 7), 0, 0));

                    //---- label14 ----
                    label14.setText("Popular");
                    label14.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel2.add(label14, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0,
                        GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                        new Insets(2, 2, 7, 7), 0, 0));

                    //---- sProbPopular ----
                    sProbPopular.setModel(new SpinnerNumberModel(0.5, 0.0, 1.0, 0.05));
                    sProbPopular.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel2.add(sProbPopular, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(2, 2, 7, 7), 0, 0));

                    //---- label15 ----
                    label15.setText("Competitive");
                    label15.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel2.add(label15, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0,
                        GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                        new Insets(2, 2, 7, 7), 0, 0));

                    //---- sProbCompetitive ----
                    sProbCompetitive.setModel(new SpinnerNumberModel(0.5, 0.0, 1.0, 0.05));
                    sProbCompetitive.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel2.add(sProbCompetitive, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(2, 2, 7, 7), 0, 0));

                    //---- label17 ----
                    label17.setText("Weight factors for Num of Production");
                    label17.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
                    panel2.add(label17, new GridBagConstraints(0, 9, 1, 1, 0.0, 0.0,
                        GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                        new Insets(5, 5, 10, 10), 0, 0));

                    //---- separator7 ----
                    separator7.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel2.add(separator7, new GridBagConstraints(1, 9, 3, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(5, 5, 10, 5), 0, 0));

                    //---- label18 ----
                    label18.setText("Popular");
                    label18.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel2.add(label18, new GridBagConstraints(0, 10, 1, 1, 0.0, 0.0,
                        GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                        new Insets(2, 2, 7, 7), 0, 0));

                    //---- sProdPopular ----
                    sProdPopular.setModel(new SpinnerNumberModel(0.5, 0.0, 1.0, 0.05));
                    sProdPopular.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel2.add(sProdPopular, new GridBagConstraints(1, 10, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(2, 2, 7, 7), 0, 0));

                    //---- label19 ----
                    label19.setText("Basic");
                    label19.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel2.add(label19, new GridBagConstraints(0, 11, 1, 1, 0.0, 0.0,
                        GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                        new Insets(2, 2, 7, 7), 0, 0));

                    //---- sProdBasic ----
                    sProdBasic.setModel(new SpinnerNumberModel(0.5, 0.0, 1.0, 0.05));
                    sProdBasic.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel2.add(sProdBasic, new GridBagConstraints(1, 11, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(2, 2, 7, 7), 0, 0));

                    //---- label20 ----
                    label20.setText("Convenient");
                    label20.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel2.add(label20, new GridBagConstraints(0, 12, 1, 1, 0.0, 0.0,
                        GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                        new Insets(2, 2, 7, 7), 0, 0));

                    //---- sProdConvenient ----
                    sProdConvenient.setModel(new SpinnerNumberModel(0.5, 0.0, 1.0, 0.05));
                    sProdConvenient.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel2.add(sProdConvenient, new GridBagConstraints(1, 12, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(2, 2, 7, 7), 0, 0));

                    //---- label21 ----
                    label21.setText("Luxury");
                    label21.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel2.add(label21, new GridBagConstraints(0, 13, 1, 1, 0.0, 0.0,
                        GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                        new Insets(2, 2, 2, 7), 0, 0));

                    //---- sProdLuxury ----
                    sProdLuxury.setModel(new SpinnerNumberModel(0.5, 0.0, 1.0, 0.05));
                    sProdLuxury.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel2.add(sProdLuxury, new GridBagConstraints(1, 13, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(2, 2, 2, 7), 0, 0));
                }
                pGeneralSettings.setViewportView(panel2);
            }
            tabbedPane1.addTab("General", pGeneralSettings);

            //======== pConsumerSettings ========
            {
                pConsumerSettings.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));

                //======== panel1 ========
                {
                    panel1.setLayout(new GridBagLayout());
                    ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 135, 99, 0, 0};
                    ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                    ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
                    ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                    //---- lLevel1 ----
                    lLevel1.setText("Level 1");
                    lLevel1.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
                    panel1.add(lLevel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(5, 5, 5, 5), 0, 0));

                    //---- separator1 ----
                    separator1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(separator1, new GridBagConstraints(1, 0, 3, 1, 1.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(5, 0, 5, 0), 0, 0));

                    //---- lBasic1 ----
                    lBasic1.setText("Basic Demand");
                    lBasic1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(lBasic1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- sBasic1 ----
                    sBasic1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    sBasic1.setModel(new SpinnerNumberModel(50.0, 0.0, 100.0, 1.0));
                    panel1.add(sBasic1, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- lConvenient1 ----
                    lConvenient1.setText("Convenient Demand");
                    lConvenient1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(lConvenient1, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- sConvenient1 ----
                    sConvenient1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    sConvenient1.setModel(new SpinnerNumberModel(50.0, 0.0, 100.0, 1.0));
                    panel1.add(sConvenient1, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- lLuxury1 ----
                    lLuxury1.setText("Luxury Demand");
                    lLuxury1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(lLuxury1, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- sLuxury1 ----
                    sLuxury1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    sLuxury1.setModel(new SpinnerNumberModel(50.0, 0.0, 100.0, 1.0));
                    panel1.add(sLuxury1, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- lProfit1 ----
                    lProfit1.setText("Profit");
                    lProfit1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(lProfit1, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- sProfit1 ----
                    sProfit1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    sProfit1.setModel(new SpinnerNumberModel(0.0, 0.0, null, 1.0));
                    panel1.add(sProfit1, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- lLevel2 ----
                    lLevel2.setText("Level 2");
                    lLevel2.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
                    panel1.add(lLevel2, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(5, 5, 5, 5), 0, 0));

                    //---- separator2 ----
                    separator2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(separator2, new GridBagConstraints(1, 5, 3, 1, 1.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(5, 0, 5, 0), 0, 0));

                    //---- lBasic2 ----
                    lBasic2.setText("Basic Demand");
                    lBasic2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(lBasic2, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- sBasic2 ----
                    sBasic2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    sBasic2.setModel(new SpinnerNumberModel(50.0, 0.0, 100.0, 1.0));
                    panel1.add(sBasic2, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- lConvenient2 ----
                    lConvenient2.setText("Convenient Demand");
                    lConvenient2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(lConvenient2, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- sConvenient2 ----
                    sConvenient2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    sConvenient2.setModel(new SpinnerNumberModel(50.0, 0.0, 100.0, 1.0));
                    panel1.add(sConvenient2, new GridBagConstraints(2, 7, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- lLuxury2 ----
                    lLuxury2.setText("Luxury Demand");
                    lLuxury2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(lLuxury2, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- sLuxury2 ----
                    sLuxury2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    sLuxury2.setModel(new SpinnerNumberModel(50.0, 0.0, 100.0, 1.0));
                    panel1.add(sLuxury2, new GridBagConstraints(2, 8, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- lProfit2 ----
                    lProfit2.setText("Profit");
                    lProfit2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(lProfit2, new GridBagConstraints(1, 9, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- sProfit2 ----
                    sProfit2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    sProfit2.setModel(new SpinnerNumberModel(0.0, 0.0, null, 1.0));
                    panel1.add(sProfit2, new GridBagConstraints(2, 9, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- lLevel3 ----
                    lLevel3.setText("Level 3");
                    lLevel3.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
                    panel1.add(lLevel3, new GridBagConstraints(0, 10, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(5, 5, 5, 5), 0, 0));

                    //---- separator3 ----
                    separator3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(separator3, new GridBagConstraints(1, 10, 3, 1, 1.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(5, 0, 5, 0), 0, 0));

                    //---- lBasic3 ----
                    lBasic3.setText("Basic Demand");
                    lBasic3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(lBasic3, new GridBagConstraints(1, 11, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- sBasic3 ----
                    sBasic3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    sBasic3.setModel(new SpinnerNumberModel(50.0, 0.0, 100.0, 1.0));
                    panel1.add(sBasic3, new GridBagConstraints(2, 11, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- lConvenient3 ----
                    lConvenient3.setText("Convenient Demand");
                    lConvenient3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(lConvenient3, new GridBagConstraints(1, 12, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- sConvenient3 ----
                    sConvenient3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    sConvenient3.setModel(new SpinnerNumberModel(50.0, 0.0, 100.0, 1.0));
                    panel1.add(sConvenient3, new GridBagConstraints(2, 12, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- lLuxury3 ----
                    lLuxury3.setText("Luxury Demand");
                    lLuxury3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(lLuxury3, new GridBagConstraints(1, 13, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- sLuxury3 ----
                    sLuxury3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    sLuxury3.setModel(new SpinnerNumberModel(50.0, 0.0, 100.0, 1.0));
                    panel1.add(sLuxury3, new GridBagConstraints(2, 13, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- lProfit3 ----
                    lProfit3.setText("Profit");
                    lProfit3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel1.add(lProfit3, new GridBagConstraints(1, 14, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));

                    //---- sProfit3 ----
                    sProfit3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    sProfit3.setModel(new SpinnerNumberModel(0.0, 0.0, null, 1.0));
                    panel1.add(sProfit3, new GridBagConstraints(2, 14, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));
                }
                pConsumerSettings.setViewportView(panel1);
            }
            tabbedPane1.addTab("Consumer", pConsumerSettings);

            //======== pGoodsSettings ========
            {
                pGoodsSettings.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));

                //======== panel7 ========
                {
                    panel7.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    panel7.setLayout(new GridBagLayout());
                    ((GridBagLayout)panel7.getLayout()).columnWidths = new int[] {99, 0, 0};
                    ((GridBagLayout)panel7.getLayout()).rowHeights = new int[] {0, 0, 0};
                    ((GridBagLayout)panel7.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
                    ((GridBagLayout)panel7.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

                    //---- bChoose ----
                    bChoose.setText("Choose");
                    bChoose.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    bChoose.addActionListener(e -> bChoose(e));
                    panel7.add(bChoose, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(5, 5, 10, 10), 0, 0));

                    //======== panel3 ========
                    {
                        panel3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        panel3.setLayout(new GridBagLayout());
                        ((GridBagLayout)panel3.getLayout()).columnWidths = new int[] {0, 91, 0, 0};
                        ((GridBagLayout)panel3.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                        ((GridBagLayout)panel3.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
                        ((GridBagLayout)panel3.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                        //---- separator5 ----
                        separator5.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        panel3.add(separator5, new GridBagConstraints(0, 0, 3, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- label11 ----
                        label11.setText("ID");
                        label11.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        panel3.add(label11, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                            new Insets(2, 2, 7, 7), 0, 0));

                        //---- tID ----
                        tID.setText("0");
                        tID.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        panel3.add(tID, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                            new Insets(0, 0, 5, 5), 0, 0));

                        //---- lName ----
                        lName.setText("Name");
                        lName.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        panel3.add(lName, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                            new Insets(2, 2, 7, 7), 0, 0));

                        //---- tName ----
                        tName.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        panel3.add(tName, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(2, 2, 7, 7), 0, 0));

                        //---- lType ----
                        lType.setText("Type");
                        lType.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        panel3.add(lType, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                            GridBagConstraints.EAST, GridBagConstraints.NONE,
                            new Insets(2, 2, 7, 7), 0, 0));

                        //---- cbType ----
                        cbType.setModel(new DefaultComboBoxModel<>(new String[] {
                            "basic",
                            "convenience",
                            "luxury"
                        }));
                        cbType.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        cbType.setSelectedIndex(0);
                        panel3.add(cbType, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.NONE,
                            new Insets(2, 2, 7, 7), 0, 0));

                        //---- lCost ----
                        lCost.setText("Cost");
                        lCost.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        lCost.setLabelFor(tCost);
                        panel3.add(lCost, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                            new Insets(2, 2, 7, 7), 0, 0));

                        //---- tCost ----
                        tCost.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        tCost.setModel(new SpinnerNumberModel(0.0, 0.0, null, 10.0));
                        panel3.add(tCost, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                            new Insets(2, 2, 7, 7), 0, 0));

                        //---- lEProfit ----
                        lEProfit.setText("Expected Profitability");
                        lEProfit.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        lEProfit.setLabelFor(tEProfit);
                        panel3.add(lEProfit, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
                            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                            new Insets(2, 2, 7, 7), 0, 0));

                        //---- tEProfit ----
                        tEProfit.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        tEProfit.setModel(new SpinnerNumberModel(0.0, 0.0, null, 10.0));
                        panel3.add(tEProfit, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                            new Insets(2, 2, 7, 7), 0, 0));

                        //---- label6 ----
                        label6.setText("Satisfaction Rates");
                        label6.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
                        panel3.add(label6, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                            new Insets(2, 2, 7, 7), 0, 0));

                        //---- separator4 ----
                        separator4.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        panel3.add(separator4, new GridBagConstraints(1, 6, 2, 1, 1.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                            new Insets(0, 0, 5, 0), 0, 0));

                        //---- lBasicDemand ----
                        lBasicDemand.setText("Basic");
                        lBasicDemand.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        lBasicDemand.setLabelFor(tBasicDemand);
                        panel3.add(lBasicDemand, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0,
                            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                            new Insets(2, 2, 7, 7), 0, 0));

                        //---- tBasicDemand ----
                        tBasicDemand.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        tBasicDemand.setModel(new SpinnerNumberModel(0.0, 0.0, null, 10.0));
                        panel3.add(tBasicDemand, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                            new Insets(2, 2, 7, 7), 0, 0));

                        //---- lConvenienceDemand ----
                        lConvenienceDemand.setText("Convenience");
                        lConvenienceDemand.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        lConvenienceDemand.setLabelFor(tConvenienceDemand);
                        panel3.add(lConvenienceDemand, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0,
                            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                            new Insets(2, 2, 7, 7), 0, 0));

                        //---- tConvenienceDemand ----
                        tConvenienceDemand.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        tConvenienceDemand.setModel(new SpinnerNumberModel(0.0, 0.0, null, 10.0));
                        panel3.add(tConvenienceDemand, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                            new Insets(2, 2, 7, 7), 0, 0));

                        //---- lLuxuryDemand ----
                        lLuxuryDemand.setText("Luxury");
                        lLuxuryDemand.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        lLuxuryDemand.setLabelFor(tLuxuryDemand);
                        panel3.add(lLuxuryDemand, new GridBagConstraints(0, 9, 1, 1, 0.0, 0.0,
                            GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                            new Insets(2, 2, 7, 7), 0, 0));

                        //---- tLuxuryDemand ----
                        tLuxuryDemand.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                        tLuxuryDemand.setModel(new SpinnerNumberModel(0.0, 0.0, null, 10.0));
                        panel3.add(tLuxuryDemand, new GridBagConstraints(1, 9, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                            new Insets(2, 2, 7, 7), 0, 0));

                        //======== panel4 ========
                        {
                            panel4.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                            panel4.setLayout(new GridBagLayout());
                            ((GridBagLayout)panel4.getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0, 0};
                            ((GridBagLayout)panel4.getLayout()).rowHeights = new int[] {0, 0};
                            ((GridBagLayout)panel4.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                            ((GridBagLayout)panel4.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

                            //---- bAdd ----
                            bAdd.setText("Add");
                            bAdd.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                            bAdd.addActionListener(e -> bAdd(e));
                            panel4.add(bAdd, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
                                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                                new Insets(0, 0, 0, 5), 0, 0));

                            //---- bChange ----
                            bChange.setText("Change");
                            bChange.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                            bChange.addActionListener(e -> bChange(e));
                            panel4.add(bChange, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0,
                                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                                new Insets(0, 0, 0, 5), 0, 0));

                            //---- bCopy ----
                            bCopy.setText("Copy");
                            bCopy.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                            bCopy.addActionListener(e -> bCopy(e));
                            panel4.add(bCopy, new GridBagConstraints(2, 0, 1, 1, 1.0, 0.0,
                                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                                new Insets(0, 0, 0, 5), 0, 0));

                            //---- bPaste ----
                            bPaste.setText("Paste");
                            bPaste.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                            bPaste.addActionListener(e -> bPaste(e));
                            panel4.add(bPaste, new GridBagConstraints(3, 0, 1, 1, 1.0, 0.0,
                                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                                new Insets(0, 0, 0, 5), 0, 0));

                            //---- bDelete ----
                            bDelete.setText("Delete");
                            bDelete.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                            bDelete.addActionListener(e -> bDelete(e));
                            panel4.add(bDelete, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0,
                                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0, 0, 0, 0), 0, 0));
                        }
                        panel3.add(panel4, new GridBagConstraints(0, 11, 3, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                            new Insets(0, 0, 5, 0), 0, 0));
                    }
                    panel7.add(panel3, new GridBagConstraints(0, 1, 2, 1, 1.0, 1.0,
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
            panel6.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
            panel6.setLayout(new GridBagLayout());
            ((GridBagLayout)panel6.getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0};
            ((GridBagLayout)panel6.getLayout()).rowHeights = new int[] {0, 0};
            ((GridBagLayout)panel6.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel6.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

            //---- hSpacer1 ----
            hSpacer1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
            panel6.add(hSpacer1, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));

            //---- bSave ----
            bSave.setText("Save");
            bSave.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
            bSave.setToolTipText("This will rewrite the parameter.properties and goods .csv file");
            bSave.addActionListener(e -> bSave(e));
            panel6.add(bSave, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(4, 4, 4, 9), 0, 0));

            //---- bCancel ----
            bCancel.setText("Cancel");
            bCancel.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
            bCancel.addActionListener(e -> bCancel(e));
            panel6.add(bCancel, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(4, 4, 4, 9), 0, 0));

            //---- bApply ----
            bApply.setText("Apply");
            bApply.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
            bApply.setToolTipText("This will only update the parameters in the system");
            bApply.addActionListener(e -> bApply(e));
            panel6.add(bApply, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(4, 4, 4, 4), 0, 0));
        }
        contentPane.add(panel6, BorderLayout.PAGE_END);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on

        try {
            // 设置 Arc Orange 样式
            FlatLightLaf.setup();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Good goodClip;
    private final SettingsBak bak = new SettingsBak();

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    public JTabbedPane tabbedPane1;
    private JScrollPane pGeneralSettings;
    private JPanel panel2;
    private JLabel label25;
    private JSeparator separator8;
    private JLabel label26;
    private JSpinner sL1;
    private JLabel lL1;
    private JLabel label27;
    private JSpinner sL2;
    private JLabel lL2;
    private JLabel label28;
    private JSpinner sL3;
    private JLabel lL3;
    private JLabel label16;
    private JSeparator separator6;
    private JLabel label12;
    public JSpinner sProbDemand;
    private JLabel label13;
    public JSpinner sProbPrice;
    private JLabel label14;
    public JSpinner sProbPopular;
    private JLabel label15;
    public JSpinner sProbCompetitive;
    private JLabel label17;
    private JSeparator separator7;
    private JLabel label18;
    public JSpinner sProdPopular;
    private JLabel label19;
    public JSpinner sProdBasic;
    private JLabel label20;
    public JSpinner sProdConvenient;
    private JLabel label21;
    public JSpinner sProdLuxury;
    private JScrollPane pConsumerSettings;
    private JPanel panel1;
    private JLabel lLevel1;
    private JSeparator separator1;
    private JLabel lBasic1;
    public JSpinner sBasic1;
    private JLabel lConvenient1;
    public JSpinner sConvenient1;
    private JLabel lLuxury1;
    public JSpinner sLuxury1;
    private JLabel lProfit1;
    public JSpinner sProfit1;
    private JLabel lLevel2;
    private JSeparator separator2;
    private JLabel lBasic2;
    public JSpinner sBasic2;
    private JLabel lConvenient2;
    public JSpinner sConvenient2;
    private JLabel lLuxury2;
    public JSpinner sLuxury2;
    private JLabel lProfit2;
    public JSpinner sProfit2;
    private JLabel lLevel3;
    private JSeparator separator3;
    private JLabel lBasic3;
    public JSpinner sBasic3;
    private JLabel lConvenient3;
    public JSpinner sConvenient3;
    private JLabel lLuxury3;
    public JSpinner sLuxury3;
    private JLabel lProfit3;
    public JSpinner sProfit3;
    private JScrollPane pGoodsSettings;
    private JPanel panel7;
    private JButton bChoose;
    private JPanel panel3;
    private JSeparator separator5;
    private JLabel label11;
    public JLabel tID;
    private JLabel lName;
    public JTextField tName;
    private JLabel lType;
    public JComboBox<String> cbType;
    private JLabel lCost;
    public JSpinner tCost;
    private JLabel lEProfit;
    public JSpinner tEProfit;
    private JLabel label6;
    private JSeparator separator4;
    private JLabel lBasicDemand;
    public JSpinner tBasicDemand;
    private JLabel lConvenienceDemand;
    public JSpinner tConvenienceDemand;
    private JLabel lLuxuryDemand;
    public JSpinner tLuxuryDemand;
    private JPanel panel4;
    public JButton bAdd;
    public JButton bChange;
    public JButton bCopy;
    public JButton bPaste;
    private JButton bDelete;
    private JPanel panel6;
    private JPanel hSpacer1;
    public JButton bSave;
    public JButton bCancel;
    public JButton bApply;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

class SettingsBak{
    public double sL1;
    public double sL2;
    public double sL3;
    public double sProbDemand;
    public double sProbPrice;
    public double sProbPopular;
    public double sProbCompetitive;
    public double sProdPopular;
    public double sProdBasic;
    public double sProdConvenient;
    public double sProdLuxury;
    public double sBasic1;
    public double sConvenient1;
    public double sLuxury1;
    public double sProfit1;
    public double sBasic2;
    public double sConvenient2;
    public double sLuxury2;
    public double sProfit2;
    public double sBasic3;
    public double sConvenient3;
    public double sLuxury3;
    public double sProfit3;

    public SettingsBak(){}
}
