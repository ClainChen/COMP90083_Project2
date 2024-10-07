package supconmodel.model.agents;

import supconmodel.utils.Enums;

import java.util.ArrayList;

public class GoodSummarizer {
    public int id;
    public String name;
    public Enums.GoodType type;
    public double cost;
    public double expectProfitability;
    public double basic;
    public double convenient;
    public double luxury;

    public ArrayList<Integer> ownedBy = new ArrayList<>();

    public GoodSummarizer(int id, String name, Enums.GoodType type, double cost, double expectProfitability, double basic, double convenient, double luxury) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.cost = cost;
        this.expectProfitability = expectProfitability;
        this.basic = basic;
        this.convenient = convenient;
        this.luxury = luxury;
    }

    public double getAvgPrice(ArrayList<Supplier> suppliers){
        double totalPrice = 0;
        int numGoods = 0;
        for (int i: ownedBy){
            Supplier supplier = suppliers.get(i);
            DynamicGood good = supplier.getGood(id);
            if (good != null){
                totalPrice += good.price;
                numGoods += 1;
            }
        }
        return totalPrice / numGoods;
    }

    public int getTotalSoldNum(ArrayList<Supplier> suppliers){
        int totalSoldNum = 0;
        for (int i: ownedBy){
            Supplier supplier = suppliers.get(i);
            DynamicGood good = supplier.getGood(id);
            if (good != null){
                totalSoldNum += good.totalSoldNum;
            }
        }
        return totalSoldNum;
    }

    public int getLast7SoldNum(ArrayList<Supplier> suppliers){
        int totalSoldNum = 0;
        for (int i: ownedBy){
            Supplier supplier = suppliers.get(i);
            DynamicGood good = supplier.getGood(id);
            if (good != null){
                totalSoldNum += good.getLast7SoldNum();
            }
        }
        return totalSoldNum;
    }

    public double getLastCost(ArrayList<Supplier> suppliers){
        double totalCost = 0;
        for (int i: ownedBy){
            Supplier supplier = suppliers.get(i);
            DynamicGood good = supplier.getGood(id);
            if (good != null){
                totalCost += good.lastCost;
            }
        }
        return totalCost;
    }

    public int getTotalInventory(ArrayList<Supplier> suppliers){
        int totalInventory = 0;
        for (int i: ownedBy){
            Supplier supplier = suppliers.get(i);
            DynamicGood good = supplier.getGood(id);
            if (good != null){
                totalInventory += good.inventory;
            }
        }
        return totalInventory;
    }

    public int getLastProduction(ArrayList<Supplier> suppliers){
        int totalProduction = 0;
        for (int i: ownedBy){
            Supplier supplier = suppliers.get(i);
            DynamicGood good = supplier.getGood(id);
            if (good != null && !good.dataRecorder.lastProductionData.isEmpty()){
                totalProduction += good.dataRecorder.lastProductionData.getLast();
            }
        }
        return totalProduction;
    }
}
