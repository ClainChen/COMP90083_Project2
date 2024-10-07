package supconmodel.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class Parameters {
    public static double init_l1_ratio = 6;
    public static double init_l2_ratio = 3;
    public static double init_l3_ratio = 1;

    public static int goods_per_supplier = 3;

    public static int num_suppliers = 3;
    public static int num_consumers = 50;

    public static double init_deposit_multiplier = 10;

    public static double l1_max_basicDemand = 60;
    public static double l1_max_convenientDemand = 60;
    public static double l1_max_luxuryDemand = 30;

    public static double l1_init_basicDemand = 20;
    public static double l1_init_convenientDemand = 20;
    public static double l1_init_luxuryDemand = 20;
    public static double l1_init_profit = 800;

    public static double l1_add_basicDemand = 30;
    public static double l1_add_convenientDemand = 10;
    public static double l1_add_luxuryDemand = 1;

    public static double l2_max_basicDemand = 100;
    public static double l2_max_convenientDemand = 100;
    public static double l2_max_luxuryDemand = 60;

    public static double l2_init_basicDemand = 20;
    public static double l2_init_convenientDemand = 20;
    public static double l2_init_luxuryDemand = 20;
    public static double l2_init_profit = 1500;

    public static double l2_add_basicDemand = 50;
    public static double l2_add_convenientDemand = 20;
    public static double l2_add_luxuryDemand = 5;

    public static double l3_max_basicDemand = 100;
    public static double l3_max_convenientDemand = 100;
    public static double l3_max_luxuryDemand = 100;

    public static double l3_init_basicDemand = 20;
    public static double l3_init_convenientDemand = 20;
    public static double l3_init_luxuryDemand = 20;
    public static double l3_init_profit = 2800;

    public static double l3_add_basicDemand = 60;
    public static double l3_add_convenientDemand = 40;
    public static double l3_add_luxuryDemand = 20;

    public static double w_d = 0.6;
    public static double w_p = 0.8;
    public static double w_h = 0.3;
    public static double w_c = 0.5;

    public static double prod_a = 0.6;
    public static double prod_basic = 0.6;
    public static double prod_convenient = 0.3;
    public static double prod_luxury = 0.1;

    public static int init_basic_inventory = 2000;
    public static int init_convenient_inventory = 1000;
    public static int init_luxury_inventory = 500;

    public static int init_basic_max_inventory = 3000;
    public static int init_convenient_max_inventory = 1800;
    public static int init_luxury_max_inventory = 800;

    public static int simulate_speed = 16;

    public static double prob_1to2 = 0.05;
    public static double prob_2to3 = 0.05;
    public static double prob_3to2 = 0.05;
    public static double prob_2to1 = 0.05;

    public static double getLevelRatio(int level){
        double total = init_l1_ratio + init_l2_ratio + init_l3_ratio;
        switch (level){
            case 1 -> {
                return init_l1_ratio / total;
            }
            case 2 -> {
                return init_l2_ratio / total;
            }
            case 3 -> {
                return init_l3_ratio / total;
            }
            default -> {
                Tools.feedback("Unexpected level", 3);
                return 0;
            }
        }
    }

}
