package supconmodel.model.agents;

import supconmodel.model.agents.dataRecorder.ConsumerDataRecorder;
import supconmodel.utils.Parameters;

import java.util.ArrayList;

public class Consumer {
    public int id;

    public int level;

    public double basicDemand;
    public double convenientDemand;
    public double luxuryDemand;

    public double basicIncome;
    public double income;
    public double deposit;
    public double spend;

    public ArrayList<Integer> cart;

    public ConsumerDataRecorder dataRecorder;

    public Consumer(int id, int level){
        this.id = id;
        this.level = level;
        switch (level){
            case 1 -> {
                basicDemand = Parameters.l1_init_basicDemand;
                convenientDemand = Parameters.l1_init_convenientDemand;
                luxuryDemand = Parameters.l1_init_luxuryDemand;
                basicIncome = Parameters.l1_init_profit;
            }
            case 2 -> {
                basicDemand = Parameters.l2_init_basicDemand;
                convenientDemand = Parameters.l2_init_convenientDemand;
                luxuryDemand = Parameters.l2_init_luxuryDemand;
                basicIncome = Parameters.l2_init_profit;
            }
            case 3 -> {
                basicDemand = Parameters.l3_init_basicDemand;
                convenientDemand = Parameters.l3_init_convenientDemand;
                luxuryDemand  = Parameters.l3_init_luxuryDemand;
                basicIncome = Parameters.l3_init_profit;
            }
        }
        income = basicIncome;
        deposit = income * Parameters.init_deposit_multiplier;
        cart = new ArrayList<>();
        dataRecorder = new ConsumerDataRecorder();
    }

    public Consumer(Consumer consumer){
        this.id = consumer.id;
        this.level = consumer.level;
        this.basicDemand = consumer.basicDemand;
        this.convenientDemand = consumer.convenientDemand;
        this.luxuryDemand = consumer.luxuryDemand;
        this.basicIncome = consumer.basicIncome;
        this.income = consumer.income;
        this.deposit = consumer.deposit;
        this.spend = consumer.spend;
        this.cart = new ArrayList<>();
        this.dataRecorder = new ConsumerDataRecorder();
    }

    public void changeLevel(boolean isUpgrade, double addonBasic, double addonConvenient, double addonLuxury){
        if(isUpgrade){
            this.level += 1;
        }else{
            this.level -= 1;
        }
        switch (this.level){
            case 1 -> basicIncome = Parameters.l1_init_profit;
            case 2 -> basicIncome = Parameters.l2_init_profit;
            case 3 -> basicIncome = Parameters.l3_init_profit;
        }
        income = basicIncome;
        basicDemand += addonBasic;
        convenientDemand += addonConvenient;
        luxuryDemand += addonLuxury;
    }

    public void updateRecorder(){
        dataRecorder.updateRecorder(this);
    }

    public void updateBasicDemand(double value){
        switch (level){
            case 1 -> {
                basicDemand += value;
                basicDemand = Math.clamp(basicDemand, 0, Parameters.l1_max_basicDemand);
            }
            case 2 -> {
                basicDemand += value;
                basicDemand = Math.clamp(basicDemand, 0, Parameters.l2_max_basicDemand);
            }
            case 3 -> {
                basicDemand += value;
                basicDemand = Math.clamp(basicDemand, 0, Parameters.l3_max_basicDemand);
            }
        }
    }

    public void updateConvenientDemand(double value){
        switch (level){
            case 1 -> {
                convenientDemand += value;
                convenientDemand = Math.clamp(convenientDemand, 0, Parameters.l1_max_convenientDemand);
            }
            case 2 -> {
                convenientDemand += value;
                convenientDemand = Math.clamp(convenientDemand, 0, Parameters.l2_max_convenientDemand);
            }
            case 3 -> {
                convenientDemand += value;
                convenientDemand = Math.clamp(convenientDemand, 0, Parameters.l3_max_convenientDemand);
            }
        }
    }

    public void updateLuxuryDemand(double value){
        switch (level){
            case 1 -> {
                luxuryDemand += value;
                luxuryDemand = Math.clamp(luxuryDemand, 0, Parameters.l1_max_luxuryDemand);
            }
            case 2 -> {
                luxuryDemand += value;
                luxuryDemand = Math.clamp(luxuryDemand, 0, Parameters.l2_max_luxuryDemand);
            }
            case 3 -> {
                luxuryDemand += value;
                luxuryDemand = Math.clamp(luxuryDemand, 0, Parameters.l3_max_luxuryDemand);
            }
        }
    }

}
