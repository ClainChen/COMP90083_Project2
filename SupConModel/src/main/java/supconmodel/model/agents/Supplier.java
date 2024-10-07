package supconmodel.model.agents;

import supconmodel.model.agents.dataRecorder.SupplierDataRecorder;

import java.util.ArrayList;

public class Supplier {
    public int id;

    public ArrayList<DynamicGood> goodList;

    public SupplierDataRecorder dataRecorder;

    public Supplier(int id){
        this.id = id;
        this.goodList = new ArrayList<>();
        dataRecorder = new SupplierDataRecorder();
    }

    public double getTotalIncome(){
        double totalProfit = 0;
        for (DynamicGood good : goodList){
            totalProfit += good.totalIncome;
        }
        return totalProfit;
    }

    public double getTotalCost(){
        double totalCost = 0;
        for (DynamicGood good : goodList){
            totalCost += good.totalCost;
        }
        return totalCost;
    }

    public double getLastIncome(){
        double lastProfit = 0;
        for (DynamicGood good : goodList){
            if (!good.incomeHistory.isEmpty()){
                lastProfit += good.incomeHistory.getLast();
            }
        }
        return lastProfit;
    }

    public double getLastCost(){
        double lastCost = 0;
        for (DynamicGood good : goodList){
            lastCost += good.lastCost;
        }
        return lastCost;
    }

    public DynamicGood getGood(int id){
        for (DynamicGood good : goodList){
            if (good.id == id){
                return good;
            }
        }
        return null;
    }

    public void updateRecorder(boolean isPeriod){
        if (isPeriod){
            dataRecorder.periodUpdateRecorder(this);
        }
        dataRecorder.updateRecorder(this);
    }
}
