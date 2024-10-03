/*
 * Created by JFormDesigner on Tue Oct 01 13:52:44 AEST 2024
 */

package supconmodel.model.gui.window;

import java.awt.*;
import javax.swing.*;
import org.jdesktop.beansbinding.*;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import supconmodel.model.customChart.*;

/**
 * @author Clain
 */
public class ConsumerSupervisorWindow extends JFrame {
    public ConsumerSupervisorWindow() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        hSpacer1 = new JPanel(null);
        bPreviousConsumer = new JButton();
        lCurrentConsumerID = new JTextField();
        bNextConsumer = new JButton();
        hSpacer2 = new JPanel(null);
        panel1 = new JPanel();
        panel2 = new JPanel();
        label1 = new JLabel();
        label8 = new JLabel();
        label2 = new JLabel();
        proBasicDemand = new JProgressBar();
        lBasicDemand = new JLabel();
        label3 = new JLabel();
        proConvenientDemand = new JProgressBar();
        lConvenientDemand = new JLabel();
        label4 = new JLabel();
        proLuxuryDemand = new JProgressBar();
        lLuxuryDemand = new JLabel();
        label5 = new JLabel();
        label9 = new JLabel();
        label6 = new JLabel();
        label10 = new JLabel();
        label7 = new JLabel();
        label11 = new JLabel();
        panel3 = new JPanel();
        customLineChart1 = new CustomLineChart();
        customLineChart2 = new CustomLineChart();

        //======== this ========
        setResizable(false);
        setMinimumSize(new Dimension(800, 600));
        setPreferredSize(new Dimension(800, 600));
        setTitle("Consumer Supervisor");
        setBackground(SystemColor.window);
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

        //---- bPreviousConsumer ----
        bPreviousConsumer.setText("Previous");
        bPreviousConsumer.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        contentPane.add(bPreviousConsumer, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
            new Insets(5, 5, 10, 10), 0, 0));

        //---- lCurrentConsumerID ----
        lCurrentConsumerID.setText("1000");
        lCurrentConsumerID.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        lCurrentConsumerID.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lCurrentConsumerID, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
            new Insets(5, 5, 10, 10), 0, 0));

        //---- bNextConsumer ----
        bNextConsumer.setText("Next");
        bNextConsumer.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        contentPane.add(bNextConsumer, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
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
                ((GridBagLayout)panel2.getLayout()).columnWidths = new int[] {0, 0, 31, 0};
                ((GridBagLayout)panel2.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                ((GridBagLayout)panel2.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout)panel2.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                //---- label1 ----
                label1.setText("Level");
                label1.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(5, 5, 10, 10), 0, 0));

                //---- label8 ----
                label8.setText("1");
                label8.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(label8, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(5, 5, 10, 10), 0, 0));

                //---- label2 ----
                label2.setText("Basic Demand");
                label2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(label2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(5, 5, 10, 10), 0, 0));

                //---- proBasicDemand ----
                proBasicDemand.setValue(11);
                proBasicDemand.setForeground(new Color(0x00ff66));
                proBasicDemand.setBackground(SystemColor.inactiveCaptionBorder);
                panel2.add(proBasicDemand, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(5, 5, 10, 10), 0, 0));

                //---- lBasicDemand ----
                lBasicDemand.setText("text");
                lBasicDemand.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(lBasicDemand, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(5, 5, 10, 5), 0, 0));

                //---- label3 ----
                label3.setText("Convenient Demand");
                label3.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(label3, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(5, 5, 10, 10), 0, 0));

                //---- proConvenientDemand ----
                proConvenientDemand.setBackground(SystemColor.inactiveCaptionBorder);
                panel2.add(proConvenientDemand, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(5, 5, 10, 10), 0, 0));

                //---- lConvenientDemand ----
                lConvenientDemand.setText("text");
                lConvenientDemand.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(lConvenientDemand, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(5, 5, 10, 5), 0, 0));

                //---- label4 ----
                label4.setText("Luxury Demand");
                label4.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(label4, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(5, 5, 10, 10), 0, 0));

                //---- proLuxuryDemand ----
                proLuxuryDemand.setBackground(SystemColor.inactiveCaptionBorder);
                panel2.add(proLuxuryDemand, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(5, 5, 10, 10), 0, 0));

                //---- lLuxuryDemand ----
                lLuxuryDemand.setText("text");
                lLuxuryDemand.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(lLuxuryDemand, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(5, 5, 10, 5), 0, 0));

                //---- label5 ----
                label5.setText("Basic Income");
                label5.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(label5, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(5, 5, 10, 10), 0, 0));

                //---- label9 ----
                label9.setText("5000");
                label9.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(label9, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(5, 5, 10, 10), 0, 0));

                //---- label6 ----
                label6.setText("Last Income");
                label6.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(label6, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(5, 5, 10, 10), 0, 0));

                //---- label10 ----
                label10.setText("8000");
                label10.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(label10, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(5, 5, 10, 10), 0, 0));

                //---- label7 ----
                label7.setText("Deposit");
                label7.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(label7, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(5, 5, 10, 10), 0, 0));

                //---- label11 ----
                label11.setText("48000");
                label11.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                panel2.add(label11, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(5, 5, 10, 10), 0, 0));
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

        //---- bindings ----
        bindingGroup = new BindingGroup();
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            proBasicDemand, BeanProperty.create("value"),
            lBasicDemand, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            proConvenientDemand, BeanProperty.create("value"),
            lConvenientDemand, BeanProperty.create("text")));
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            proLuxuryDemand, BeanProperty.create("value"),
            lLuxuryDemand, BeanProperty.create("text")));
        bindingGroup.bind();
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel hSpacer1;
    private JButton bPreviousConsumer;
    private JTextField lCurrentConsumerID;
    private JButton bNextConsumer;
    private JPanel hSpacer2;
    private JPanel panel1;
    private JPanel panel2;
    private JLabel label1;
    private JLabel label8;
    private JLabel label2;
    private JProgressBar proBasicDemand;
    private JLabel lBasicDemand;
    private JLabel label3;
    private JProgressBar proConvenientDemand;
    private JLabel lConvenientDemand;
    private JLabel label4;
    private JProgressBar proLuxuryDemand;
    private JLabel lLuxuryDemand;
    private JLabel label5;
    private JLabel label9;
    private JLabel label6;
    private JLabel label10;
    private JLabel label7;
    private JLabel label11;
    private JPanel panel3;
    private CustomLineChart customLineChart1;
    private CustomLineChart customLineChart2;
    private BindingGroup bindingGroup;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
