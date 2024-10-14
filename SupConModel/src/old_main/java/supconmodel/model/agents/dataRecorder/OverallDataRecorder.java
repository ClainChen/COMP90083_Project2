package supconmodel.model.agents.dataRecorder;

import supconmodel.model.agents.Consumer;
import supconmodel.model.agents.DynamicGood;
import supconmodel.model.agents.GoodSummarizer;
import supconmodel.model.agents.Supplier;
import supconmodel.model.agents.controller.ConsumerController;
import supconmodel.model.agents.controller.GoodController;
import supconmodel.model.agents.controller.SupplierController;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OverallDataRecorder {
    // Supplier
    public ArrayList<Double> last7IncomeData;
    public ArrayList<Double> basicIncomeData;
    public ArrayList<Double> convenientIncomeData;
    public ArrayList<Double> luxuryIncomeData;

    public ArrayList<Integer> last7SoldNumData;
    public ArrayList<Integer> basicSoldNumData;
    public ArrayList<Integer> convenientSoldNumData;
    public ArrayList<Integer> luxurySoldNumData;

    public ArrayList<Double> lastCostData;
    public ArrayList<Double> basicCostData;
    public ArrayList<Double> convenientCostData;
    public ArrayList<Double> luxuryCostData;

    // Consumer
    public ArrayList<Double> basicDemandData;
    public ArrayList<Double> convenientDemandData;
    public ArrayList<Double> luxuryDemandData;

    public OverallDataRecorder() {
        last7IncomeData = new ArrayList<>();
        basicIncomeData = new ArrayList<>();
        convenientIncomeData = new ArrayList<>();
        luxuryIncomeData = new ArrayList<>();
        last7SoldNumData = new ArrayList<>();
        basicSoldNumData = new ArrayList<>();
        convenientSoldNumData = new ArrayList<>();
        luxurySoldNumData = new ArrayList<>();
        lastCostData = new ArrayList<>();
        basicCostData = new ArrayList<>();
        convenientCostData = new ArrayList<>();
        luxuryCostData = new ArrayList<>();
        basicDemandData = new ArrayList<>();
        convenientDemandData = new ArrayList<>();
        luxuryDemandData = new ArrayList<>();
    }

    public void updateRecord(GoodController g, ConsumerController c, SupplierController s){
        // Supplier
        double totalIncome = 0;
        double basicIncome = 0;
        double convenientIncome = 0;
        double luxuryIncome = 0;
        int totalSoldNum = 0;
        int basicSoldNum = 0;
        int convenientSoldNum = 0;
        int luxurySoldNum = 0;
        double totalCost = 0;
        double basicCost = 0;
        double convenientCost = 0;
        double luxuryCost = 0;
        for (GoodSummarizer goodSummarizer: g.totalGoods){
            int soldNum = goodSummarizer.getLast7SoldNum(s.suppliers);
            double cost = goodSummarizer.getLastCost(s.suppliers);
            double income = soldNum * goodSummarizer.getAvgPrice(s.suppliers);
            totalSoldNum += soldNum;
            totalCost += cost;
            totalIncome += income;
            switch (goodSummarizer.type){
                case BASIC -> {
                    basicSoldNum += soldNum;
                    basicIncome += income;
                    basicCost += cost;
                }
                case CONVENIENCE -> {
                    convenientSoldNum += soldNum;
                    convenientIncome += income;
                    convenientCost += cost;
                }
                case LUXURY -> {
                    luxurySoldNum += soldNum;
                    luxuryIncome += income;
                    luxuryCost += cost;
                }
            }
        }
        for (Supplier supplier: s.suppliers){
            for (DynamicGood good: supplier.goodList){
                totalIncome += good.getLast7Income();
                switch (good.type){
                    case BASIC -> basicIncome += good.getLast7Income();
                    case CONVENIENCE -> convenientIncome+= good.getLast7Income();
                    case LUXURY -> luxuryIncome += good.getLast7Income();
                }
            }
        }
        last7IncomeData.add(totalIncome);
        basicIncomeData.add(basicIncome);
        convenientIncomeData.add(convenientIncome);
        luxuryIncomeData.add(luxuryIncome);
        last7SoldNumData.add(totalSoldNum);
        basicSoldNumData.add(basicSoldNum);
        convenientSoldNumData.add(convenientSoldNum);
        luxurySoldNumData.add(luxurySoldNum);
        lastCostData.add(totalCost);
        basicCostData.add(basicCost);
        convenientCostData.add(convenientCost);
        luxuryCostData.add(luxuryCost);

        // Consumer Demands
        double basicDemand = 0;
        double convenientDemand = 0;
        double luxuryDemand = 0;
        for (Consumer consumer: c.consumers){
            basicDemand += consumer.basicDemand;
            convenientDemand += consumer.convenientDemand;
            luxuryDemand += consumer.luxuryDemand;
        }
        basicDemandData.add(basicDemand);
        convenientDemandData.add(convenientDemand);
        luxuryDemandData.add(luxuryDemand);

    }

}
