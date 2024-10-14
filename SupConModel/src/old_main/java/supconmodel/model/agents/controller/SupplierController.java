package supconmodel.model.agents.controller;

import supconmodel.SupConModelMain;
import supconmodel.model.agents.DynamicGood;
import supconmodel.model.agents.Good;
import supconmodel.model.agents.Supplier;
import supconmodel.utils.Parameters;
import supconmodel.utils.Tools;

import java.util.ArrayList;
import java.util.Collections;

public class SupplierController {
    public ArrayList<Supplier> suppliers;

    public SupplierController() {
        suppliers = new ArrayList<>();
    }

    public void addSupplier(Supplier supplier) {
        suppliers.add(supplier);
    }

    public void init(GoodController goodController, ConsumerController consumerController){
        // Initialize supplier
        // Allocate Goods to each supplier
        suppliers.clear();
        for (int i = 0; i < Parameters.num_suppliers; i++) {
            Supplier s = new Supplier(i);
            ArrayList<Good> bakGood = new ArrayList<>(SupConModelMain.allGoods);
            Collections.shuffle(bakGood);
            // Initialize each dynamic goods
            for (int j = 0; j < Parameters.goods_per_supplier; j++) {
                DynamicGood dGood = new DynamicGood(bakGood.get(j));
                dGood.production = Tools.productive(dGood, consumerController);
                switch (dGood.type) {
                    case GoodType.BASIC -> dGood.inventory = Parameters.init_basic_inventory;
                    case GoodType.CONVENIENCE -> dGood.inventory = Parameters.init_convenient_inventory;
                    case GoodType.LUXURY -> dGood.inventory = Parameters.init_luxury_inventory;
                }
                goodController.getGoodSummarizer(dGood.id).ownedBy.add(i);
                s.goodList.add(dGood);
            }
            addSupplier(s);
        }
    }

    public void periodUpdateSupplier(ConsumerController consumerController, GoodController goodController) {
        // Update the price of each good
        for (Supplier s : suppliers) {
            for (DynamicGood good: s.goodList){
                good.setPrice(consumerController.consumers.size(), goodController.getGoodCompetitive(good.id, good.getLast7SoldNum(), this));
                good.inventory += good.production;
                good.totalProduction += good.production;
                good.lastCost = good.production * good.cost;
                good.totalCost += good.lastCost;
                good.production = Tools.productive(good, consumerController);

            }
        }
    }

    public void updateSupplier() {
    }

    public void updateRecorder(boolean isPeriod){
        for (Supplier s : suppliers) {
            s.updateRecorder(isPeriod);
            for (DynamicGood good: s.goodList){
                good.updateRecorder(isPeriod);
            }
        }
    }

    public double getTotalCost(){
        double totalCost = 0;
        for (Supplier s : suppliers) {
            totalCost += s.getTotalCost();
        }
        return totalCost;
    }

    public double getLastCost(){
        double lastCost = 0;
        for (Supplier s : suppliers) {
            lastCost += s.getLastCost();
        }
        return lastCost;
    }
}
