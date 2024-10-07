/*
 * Created by JFormDesigner on Thu Oct 03 16:00:53 AEST 2024
 */

package supconmodel.model.gui.window;

import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import supconmodel.SupConModelMain;
import supconmodel.model.agents.Good;
import supconmodel.model.gui.prefab.GoodTreeNode;
import supconmodel.utils.Enums.*;
import supconmodel.utils.Tools;

import java.awt.*;
import javax.swing.*;

/**
 * @author Clain
 */
public class GoodSelectWindow extends JFrame {

    public GoodSelectWindow() {
        initComponents();
    }

    private void bRefresh(ActionEvent e) {
        ResetLayout();
    }

    private void goodTreeMouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
            TreePath path = goodTree.getPathForLocation(e.getX(), e.getY());
            if (path != null) {
                GoodTreeNode selectedNode;
                try{
                    selectedNode = (GoodTreeNode) path.getLastPathComponent();
                    if (selectedNode != null) {
                        Good selectedGood = selectedNode.good;
                        SettingsWindow sw = SupConModelMain.windowController.settingsWindow;
                        sw.tID.setText(String.valueOf(selectedGood.id));
                        sw.tName.setText(selectedGood.name);
                        switch (selectedGood.type){
                            case BASIC -> sw.cbType.setSelectedIndex(0);
                            case CONVENIENCE -> sw.cbType.setSelectedIndex(1);
                            case LUXURY -> sw.cbType.setSelectedIndex(2);
                        }
                        sw.tCost.setValue(selectedGood.cost);
                        sw.tEProfit.setValue(selectedGood.expectProfitability);
                        sw.tBasicDemand.setValue(selectedGood.basic);
                        sw.tConvenienceDemand.setValue(selectedGood.convenience);
                        sw.tLuxuryDemand.setValue(selectedGood.luxury);

                        SupConModelMain.windowController.closeSelectWindow(WindowEnum.GOOD_SELECT);
                    }
                } catch (ClassCastException _){}
            }
        }
    }

    private void bChooseGood(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel2 = new JPanel();
        bRefresh = new JButton();
        pGoodSelect = new JScrollPane();

        //======== this ========
        setMinimumSize(new Dimension(400, 300));
        setResizable(false);
        setMaximumSize(new Dimension(400, 300));
        var contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        ((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {0, 0};
        ((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {30, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
        ((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 1.0, 1.0E-4};

        //======== panel2 ========
        {
            panel2.setLayout(new GridBagLayout());
            ((GridBagLayout)panel2.getLayout()).columnWidths = new int[] {94, 0};
            ((GridBagLayout)panel2.getLayout()).rowHeights = new int[] {0, 0};
            ((GridBagLayout)panel2.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
            ((GridBagLayout)panel2.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

            //---- bRefresh ----
            bRefresh.setText("Refresh");
            bRefresh.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
            bRefresh.addActionListener(e -> bRefresh(e));
            panel2.add(bRefresh, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
        }
        contentPane.add(panel2, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(0, 0, 5, 0), 0, 0));

        //======== pGoodSelect ========
        {
            pGoodSelect.setPreferredSize(new Dimension(400, 240));
            pGoodSelect.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        }
        contentPane.add(pGoodSelect, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    private void SetUpLayout(){
        try{
            DefaultMutableTreeNode root = new DefaultMutableTreeNode("Goods");
            DefaultMutableTreeNode basic = new DefaultMutableTreeNode("Basic");
            DefaultMutableTreeNode convenient = new DefaultMutableTreeNode("Convenient");
            DefaultMutableTreeNode luxury = new DefaultMutableTreeNode("Luxury");

            for (Good good: SupConModelMain.allGoods){
                switch (good.type){
                    case GoodType.BASIC -> {
                        GoodTreeNode newBasic = new GoodTreeNode(good);
                        basic.add(newBasic);
                    }
                    case GoodType.CONVENIENCE -> {
                        GoodTreeNode newConvenient = new GoodTreeNode(good);
                        convenient.add(newConvenient);
                    }
                    case GoodType.LUXURY -> {
                        GoodTreeNode newLuxury = new GoodTreeNode(good);
                        luxury.add(newLuxury);
                    }
                }
            }
            root.add(basic);
            root.add(convenient);
            root.add(luxury);

            goodTree = new JTree(root);
            goodTree.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
            goodTree.setShowsRootHandles(true);
            goodTree.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    goodTreeMouseClicked(e);
                }
            });
            pGoodSelect.setViewportView(goodTree);
        }catch (NullPointerException npe){
            Tools.feedback("Didn't initialize the goods yet!", 3);
        }


    }

    public void ResetLayout() {
        SetUpLayout();
        pack();
        setLocationRelativeTo(getOwner());
    }

    private JTree goodTree;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel2;
    private JButton bRefresh;
    private JScrollPane pGoodSelect;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
