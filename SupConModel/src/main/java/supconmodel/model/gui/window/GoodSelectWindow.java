/*
 * Created by JFormDesigner on Thu Oct 03 16:00:53 AEST 2024
 */

package supconmodel.model.gui.window;

import supconmodel.SupConModelMain;
import supconmodel.model.agents.Good;
import supconmodel.model.agents.GoodType;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.*;

/**
 * @author Clain
 */
public class GoodSelectWindow extends JFrame {

    public GoodSelectWindow() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel2 = new JPanel();
        bRefresh = new JButton();
        pGoodSelect = new JPanel();

        //======== this ========
        setMinimumSize(new Dimension(400, 300));
        var contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        ((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {0, 0};
        ((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {30, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
        ((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 1.0, 1.0E-4};

        //======== panel2 ========
        {
            panel2.setLayout(new GridBagLayout());
            ((GridBagLayout)panel2.getLayout()).columnWidths = new int[] {0, 0};
            ((GridBagLayout)panel2.getLayout()).rowHeights = new int[] {0, 0};
            ((GridBagLayout)panel2.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
            ((GridBagLayout)panel2.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

            //---- bRefresh ----
            bRefresh.setText("Refresh");
            panel2.add(bRefresh, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        contentPane.add(panel2, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(0, 0, 5, 0), 0, 0));

        //======== pGoodSelect ========
        {
            pGoodSelect.setLayout(new GridBagLayout());
            ((GridBagLayout)pGoodSelect.getLayout()).columnWidths = new int[] {0, 0, 0};
            ((GridBagLayout)pGoodSelect.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
            ((GridBagLayout)pGoodSelect.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
            ((GridBagLayout)pGoodSelect.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
        }
        contentPane.add(pGoodSelect, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    private void resetLayout() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("goods.csv");
        if (inputStream == null) {
            System.out.println("No Goods File Found");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                GoodType type;
                switch (parts[2]) {
                    case "1" -> type = GoodType.BASIC;
                    case "2" -> type = GoodType.CONVENIENCE;
                    case "3" -> type = GoodType.LUXURY;
                    default -> {
                        System.out.println("Unknown GoodType " + parts[2]);
                        return;
                    }
                }
                float cost = Float.parseFloat(parts[3]);
                float expectedProfitability = Float.parseFloat(parts[4]);
                float basic = Float.parseFloat(parts[5]);
                float convenience = Float.parseFloat(parts[6]);
                float luxury = Float.parseFloat(parts[7]);

                Good newGood = new Good(id, name, type, cost, expectedProfitability, basic, convenience, luxury);


            }
        } catch (IOException e) {
            System.out.println("Unexpect Error during reading good file");
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel2;
    private JButton bRefresh;
    private JPanel pGoodSelect;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
