package supconmodel.utils;

import supconmodel.SupConModelMain;
import supconmodel.model.agents.Consumer;
import supconmodel.model.agents.controller.ConsumerController;
import supconmodel.model.agents.DynamicGood;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class Tools {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("[-+]?[0-9]+(\\.[0-9]+)?");

    public static void refreshFrame(JFrame frame){
        SwingUtilities.invokeLater(() -> {
            frame.revalidate();
            frame.repaint();
        });
    }

    public static void feedback(String message, int messageType){
        JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(false);
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JOptionPane optionPane = new JOptionPane(message, messageType);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> dialog.dispose());

        optionPane.setOptions(new Object[]{okButton});
        dialog.setContentPane(optionPane);
        dialog.pack();

        dialog.setLocationRelativeTo(SupConModelMain.windowController.mainWindow);
        dialog.setVisible(true);
    }

    public static boolean isFloat(String value) {
        return value != null && NUMBER_PATTERN.matcher(value).matches();
    }

    public static boolean isNonEmptyString(String value) {
        return value != null && !value.trim().isEmpty();
    }

    /**
     * This formula use for calculate the good's price
     * @param c: good's cost
     * @param m: good's expect profitability
     * @param h: good's popular (num of goods sold in past 7 days)
     * @param N: number of consumers
     * @param maxInventory: good's max inventory
     * @param inventory: good's current inventory
     * @param k: good's competitive factor
     * @return good's final price
     */
    public static double price(double c, double m, int h, int N, int maxInventory, int inventory, double k, Enums.GoodType type, boolean strategy){
        // TODO

        double basicPrice = c + m*c;
        double Nh = 0.2 * Nh(h, N);
        double discount = 0.8 * discountFactor(maxInventory, inventory, N);

//        double competitive = k + 0.2;

        double result = basicPrice * (1 + Nh - discount);
        if (1 + Nh - discount > 1){
            System.out.printf("%.3f, %.3f%n", Nh, discount);
        }

//        if (type != Enums.GoodType.LUXURY){
//            return strategy ? Math.max(0.7 * result, c) : result;
//        }else{
//            return strategy ? Math.min(0.9 * result, c) : result;
//        }

        return result;
    }

    public static double discountFactor(int maxInventory, int inventory, int N){
        return 1 / (1 + (Math.log(Math.sqrt(N) + maxInventory - inventory) / Math.log(Math.sqrt(N))));
    }

    /**
     * This formula use for calculate the popular factor
     * @param h: good's popular (num of goods sold in past 8 days)
     * @param N: number of consumers
     * @return
     */
    public static double Nh(int h, int N) {
        return Math.max(0, 1 - 1 / (((double) h / N) + 0.5));
    }

    public static double Nd(double Db, double Ds, double Dl, double Sb, double Ss, double Sl){
        return Math.max(0, 1 - Math.abs(((Db + Ds + Dl - Sb - Ss - Sl) / (Sb + Ss + Sl))));
    }

    public static double Np(double a, double p, double d){
        return a > d ? 0 : 1 - (a / (p + 1));
    }

    public static double propBuy(Enums.GoodType type, double goodBasic, double goodConvenience, double goodLuxury,
                                 double goodPrice, int goodSoldNum, int inventory,
                                 Consumer consumer, boolean isBasic){
        // TODO
        if (inventory <= 0){
            return 0;
        }
        double Nd = Nd(consumer.basicDemand, consumer.convenientDemand, consumer.luxuryDemand,
                       goodBasic, goodConvenience, goodLuxury);
        double Np = Np(goodPrice, isBasic ? consumer.income : consumer.deposit, consumer.deposit);
        double Nh = Nh(goodSoldNum, Parameters.num_consumers);
        // TODO: Fix the good competitive
        if (Np == 0 || Nd == 0){
            return 0;
        }

        double factor = 0;
        switch (type){
            case GoodType.BASIC -> factor = 1;
            case GoodType.CONVENIENCE -> factor = 0.8;
            case GoodType.LUXURY -> factor = 0.6;
        }

        double wd = 0, wp = 0, wh = 0;
        wd = Parameters.w_d / (Parameters.w_d + Parameters.w_p + Parameters.w_h);
        wp = Parameters.w_p / (Parameters.w_d + Parameters.w_p + Parameters.w_h);
        wh = Parameters.w_h / (Parameters.w_d + Parameters.w_p + Parameters.w_h);

        return factor * (wd * Nd + wp * Np + wh * Nh);
    }

    public static int productive(DynamicGood good, ConsumerController consumerController){
        // TODO
//        int numConsumer = consumerController.consumers.size();
//        double tBD = consumerController.getTotalDemand(Enums.GoodType.BASIC);
//        double tCD = consumerController.getTotalDemand(Enums.GoodType.CONVENIENCE);
//        double tLD = consumerController.getTotalDemand(Enums.GoodType.LUXURY);
//
//
//        double result = ((Parameters.prod_basic * tBD  / Math.max(1, good.basic)) +
//                        (Parameters.prod_convenient * tCD / Math.max(1, good.convenience)) +
//                        (Parameters.prod_luxury * tLD / Math.max(1, good.luxury)))
//                       * (Parameters.prod_a * Math.log((good.getLast7SoldNum() + 1)));
//        int output = (int) Math.round(result * productiveDiscount(good, 0.8));
//        return Math.max(0, output);
        double last14 = good.getLast14SoldNum();
        double last7 = good.getLast7SoldNum();
        double llast7 = last14 - last7;
        if (llast7 == 0){
            return (int) last7;
        }else{
            good.productiveAlpha *= Math.max(1 / llast7, last7 / llast7);
            good.productiveAlpha = Math.clamp(good.productiveAlpha, 0.625, 1.6);
            return (int) (last7 * good.productiveAlpha * productiveDiscount(good, 0.8));
        }
    }

    public static double productiveDiscount(DynamicGood good, double alpha){
        int maxInventory = 0;
        switch(good.type){
            case GoodType.BASIC -> maxInventory = Parameters.init_basic_max_inventory;
            case GoodType.CONVENIENCE -> maxInventory = Parameters.init_convenient_max_inventory;
            case GoodType.LUXURY -> maxInventory = Parameters.init_luxury_max_inventory;
        }

        if (good.inventory < maxInventory * alpha){
            return 1;
        }else if ((good.inventory >= maxInventory * alpha) && (good.inventory < maxInventory)){
            return Math.max(0, 1 - Math.pow(((good.inventory - alpha*maxInventory) / ((1-alpha)*maxInventory)), 2));
        }else{
            return 0;
        }
    }

    public static void startTimer(int t, boolean isLoop, ActionListener actions) {
        Timer timer = new Timer(t, e -> {
            actions.actionPerformed(e);
        });

        timer.setRepeats(isLoop);

        timer.start();
    }


}
