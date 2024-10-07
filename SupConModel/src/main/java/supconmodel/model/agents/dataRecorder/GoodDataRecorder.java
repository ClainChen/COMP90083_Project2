package supconmodel.model.agents.dataRecorder;

import supconmodel.model.agents.DynamicGood;

import java.util.ArrayList;

public class GoodDataRecorder {
    public ArrayList<Integer> inventoryData;
    public ArrayList<Double> priceData;
    public ArrayList<Integer> soldNumData;
    public ArrayList<Double> incomeData;
    public ArrayList<Integer> lastProductionData;

    public GoodDataRecorder() {
        inventoryData = new ArrayList<>();
        priceData = new ArrayList<>();
        soldNumData = new ArrayList<>();
        incomeData = new ArrayList<>();
        lastProductionData = new ArrayList<>();
    }

    public void periodicUpdateRecorder(DynamicGood good){
        priceData.add(good.price);
        lastProductionData.add(good.production);
    }

    public void updateRecorder(DynamicGood good){
        inventoryData.add(good.inventory);
        soldNumData.add(good.totalSoldNum);
        incomeData.add(good.lastIncome);
    }

}
