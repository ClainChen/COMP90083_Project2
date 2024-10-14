package supconmodel.model.agents;

import supconmodel.model.agents.dataRecorder.GoodDataRecorder;
import supconmodel.utils.Parameters;
import supconmodel.utils.Tools;

import java.util.ArrayList;

public class DynamicGood extends Good{
    public int inventory;
    public int production;

    public double price;

    public int totalSoldNum;
    public ArrayList<Integer> soldNumHistory;
    public int lastSoldNum;

    public double totalIncome;
    public double totalCost;
    public double totalProduction;

    public double lastIncome;
    public ArrayList<Double> incomeHistory;
    public double lastCost;

    public double productiveAlpha = 1;

    public GoodDataRecorder dataRecorder;

    public DynamicGood(Good good){
        super(good);
        this.inventory = 0;
        this.price = cost + cost * expectProfitability * 2;
        this.totalSoldNum = 0;
        this.soldNumHistory = new ArrayList<>();
        this.lastSoldNum = 0;
        this.totalIncome = 0;
        this.totalCost = 0;
        this.totalProduction = 0;
        this.lastIncome = 0;
        this.lastCost = 0;
        this.incomeHistory = new ArrayList<>();
        this.production = 0;
        this.dataRecorder = new GoodDataRecorder();
    }

    public void setPrice(int N, double competitiveRate){
        double last7Income = getLast7Income();
        double last14Income = getLast14Income();
        double llast7Income = last14Income - last7Income;
        boolean strategy = llast7Income * 0.7 > last7Income;
        this.price = Tools.price(cost, expectProfitability, getLast7SoldNum(), N, getMaxInventory(), inventory, competitiveRate, type, strategy);
    }

    private int getMaxInventory(){
        return switch (type){
            case BASIC -> Parameters.init_basic_max_inventory;
            case CONVENIENCE -> Parameters.init_convenient_max_inventory;
            case LUXURY -> Parameters.init_luxury_max_inventory;
        };
    }

    public void updateRecorder(boolean isPeriod){
        if (isPeriod){
            dataRecorder.periodicUpdateRecorder(this);
        }
        dataRecorder.updateRecorder(this);
    }

    public int getLast7SoldNum(){
        int soldNum = 0;
        int historyLength = soldNumHistory.size();
        for (int i = 0; i < 7; i++){
            if (i < historyLength - 1){
                soldNum += soldNumHistory.get(historyLength - i - 1);
            }else{
                break;
            }
        }
        return soldNum;
    }

    public int getLast14SoldNum(){
        int soldNum = 0;
        int historyLength = soldNumHistory.size();
        for (int i = 0; i < 14; i++){
            if (i < historyLength - 1){
                soldNum += soldNumHistory.get(historyLength - i - 1);
            }else{
                break;
            }
        }
        return soldNum;
    }

    public double getLast7Income(){
        double totalIncome = 0;
        int historyLength = incomeHistory.size();
        for (int i = 0; i < 7; i++){
            if (i < historyLength - 1){
                totalIncome += incomeHistory.get(historyLength - i - 1);
            }else{
                break;
            }
        }
        return totalIncome;
    }

    public double getLast14Income(){
        double totalIncome = 0;
        int historyLength = incomeHistory.size();
        for (int i = 0; i < 14; i++){
            if (i < historyLength - 1){
                totalIncome += incomeHistory.get(historyLength - i - 1);
            }else{
                break;
            }
        }
        return totalIncome;
    }
}
