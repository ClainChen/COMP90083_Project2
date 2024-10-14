package supconmodel.model.agents.controller;

import supconmodel.model.agents.*;
import supconmodel.utils.Enums;
import supconmodel.utils.Parameters;
import supconmodel.utils.Tools;

import java.sql.Array;
import java.util.*;

public class ConsumerController {
    public ArrayList<Consumer> consumers;
    public Random random;

    public ArrayList<DynamicGood> deal;

    public ConsumerController() {
        consumers = new ArrayList<>();
        random = new Random();
        deal = new ArrayList<>();
    }

    public void addConsumer(Consumer c) {
        consumers.add(c);
    }

    public void init(){
        // Initialize consumer
        // Randomly allocate the level to each consumer
        consumers.clear();
        ArrayList<Integer> consumerLevelList = new ArrayList<>();
        long numL3 = Math.max(1, Math.round(Parameters.num_consumers * Parameters.getLevelRatio(3)));
        long numL2 = Math.max(1, Math.round(Parameters.num_consumers * Parameters.getLevelRatio(2)));
        long numL1 = Parameters.num_consumers - numL3 - numL2;
        for (int x = 0; x < numL1; x++){
            consumerLevelList.add(1);
        }
        for (int x = 0; x < numL2; x++){
            consumerLevelList.add(2);
        }
        for (int x = 0; x < numL3; x++){
            consumerLevelList.add(3);
        }
        Collections.shuffle(consumerLevelList);

        // Create consumers
        for (int i = 0; i < Parameters.num_consumers; i++){
            Consumer c = new Consumer(i, consumerLevelList.get(i));
            addConsumer(c);
        }
    }

    public void periodUpdateConsumer() {
        for (Consumer c: consumers){
            // consumer income
            c.deposit += c.income;

            // consumer change level
            double upgradeProb = 0;
            double downgradeProb = 0;
            switch (c.level){
                case 1 -> upgradeProb = Parameters.prob_1to2;
                case 2 -> {
                    upgradeProb = Parameters.prob_2to3;
                    downgradeProb = Parameters.prob_2to1;
                }
                case 3 -> downgradeProb = Parameters.prob_3to2;
            }
            if (random.nextDouble() < upgradeProb){
                c.changeLevel(true, 20, 40, 40);

            } else if (random.nextDouble() < downgradeProb){
                c.changeLevel(false, -30, -20, -20);
            }
        }
    }

    public void updateConsumer(GoodController goodController, SupplierController supplierController) {
        // Consumer Regular Update
        Hashtable<Integer, Integer> interInventory = new Hashtable<>();
        for (GoodSummarizer good: goodController.totalGoods){
            interInventory.put(good.id, good.getTotalInventory(supplierController.suppliers));
        }

        for (Consumer c: consumers){
            c.spend = 0;

            updateDemand(c);

            Consumer newC = new Consumer(c);
            for (GoodSummarizer good: goodController.totalGoods){
                double avgPrice = good.getAvgPrice(supplierController.suppliers);
                int last7SoldNum = good.getLast7SoldNum(supplierController.suppliers);
                double buyProp = Tools.propBuy(good.type, good.basic, good.convenient, good.luxury,
                        avgPrice, last7SoldNum, interInventory.get(good.id), newC, good.type.equals(Enums.GoodType.BASIC));
                if (random.nextDouble() < buyProp){
                    newC.cart.add(good.id);
                    consumerInterChange(newC, good, supplierController.suppliers);
                    interInventory.put(good.id, interInventory.get(good.id) - 1);
                }
            }
            c.cart = newC.cart;
            buyGood(c, goodController, supplierController);

            // Clear consumer's cart
            c.cart.clear();
        }

        updateGoods(supplierController);

    }

    private void updateDemand(Consumer c) {
        switch (c.level){
            case 1 -> {
                c.updateBasicDemand(Parameters.l1_add_basicDemand);
                c.updateConvenientDemand(Parameters.l1_add_convenientDemand);
                c.updateLuxuryDemand(Parameters.l1_add_luxuryDemand);
            }
            case 2 -> {
                c.updateBasicDemand(Parameters.l2_add_basicDemand);
                c.updateConvenientDemand(Parameters.l2_add_convenientDemand);
                c.updateLuxuryDemand(Parameters.l2_add_luxuryDemand);
            }
            case 3 -> {
                c.updateBasicDemand(Parameters.l3_add_basicDemand);
                c.updateConvenientDemand(Parameters.l3_add_convenientDemand);
                c.updateLuxuryDemand(Parameters.l3_add_luxuryDemand);
            }
        }
    }

    private void consumerInterChange(Consumer c, GoodSummarizer good, ArrayList<Supplier> suppliers) {
        c.deposit -= good.getAvgPrice(suppliers);
        c.updateBasicDemand(-good.basic);
        c.updateConvenientDemand(-good.convenient);
        c.updateLuxuryDemand(-good.luxury);
    }

    private void buyGood(Consumer c, GoodController goodController, SupplierController supplierController){
        // Decide to buy the good from which supplier
        for (int goodID: c.cart){
            GoodSummarizer good = goodController.getGoodSummarizer(goodID);
            double maxPropBuy = 0;
            ArrayList<Integer> potentialBuyFromSupplier = new ArrayList<>();
            for (int supplierID: good.ownedBy){
                DynamicGood supplierGood = supplierController.suppliers.get(supplierID).getGood(good.id);
                double propBuyFromSupplier =
                        Tools.propBuy(supplierGood.type, supplierGood.basic, supplierGood.convenience, supplierGood.luxury,
                                supplierGood.price, supplierGood.getLast7SoldNum(), 1, c,
                                supplierGood.type.equals(Enums.GoodType.BASIC));
                if (propBuyFromSupplier > 0){
                    if (propBuyFromSupplier > maxPropBuy){
                        maxPropBuy = propBuyFromSupplier;
                        potentialBuyFromSupplier.clear();
                        potentialBuyFromSupplier.add(supplierID);
                    }else if (propBuyFromSupplier == maxPropBuy){
                        potentialBuyFromSupplier.add(supplierID);
                    }
                }
            }

            if (potentialBuyFromSupplier.size() >= 1){
                Collections.shuffle(potentialBuyFromSupplier);
                int buyFromSupplierID = potentialBuyFromSupplier.getFirst();
                DynamicGood supplierGood = supplierController.suppliers.get(buyFromSupplierID).getGood(goodID);
                buy(c, supplierGood);
            }
        }
    }

    public double getTotalDemand(Enums.GoodType type){
        double demand = 0;
        for (Consumer c: consumers){
            switch (type){
                case GoodType.BASIC -> demand += c.basicDemand;
                case GoodType.CONVENIENCE -> demand += c.convenientDemand;
                case GoodType.LUXURY -> demand += c.luxuryDemand;
            }
        }
        return demand;
    }

    /**
     * consumer's buy action
     * @param c: consumer
     * @param supplierGood: supplier's good
     */
    private void buy(Consumer c, DynamicGood supplierGood) {
        // change on consumer side
        c.deposit -= supplierGood.price;
        c.spend += supplierGood.price;
        c.updateBasicDemand(-supplierGood.basic);
        c.updateConvenientDemand(-supplierGood.convenience);
        c.updateLuxuryDemand(-supplierGood.luxury);

        deal.add(supplierGood);
    }

    private void updateGoods(SupplierController supplierController){
        for (DynamicGood good: deal){
            // change on supplier side
            good.totalSoldNum += 1;
            good.lastSoldNum += 1;
            good.inventory -= 1;
            good.totalIncome += good.price;
            good.lastIncome += good.price;
        }

        for (Supplier supplier: supplierController.suppliers){
            for (DynamicGood good: supplier.goodList){
                good.soldNumHistory.add(good.lastSoldNum);
                good.incomeHistory.add(good.lastIncome);
                good.lastSoldNum = 0;
                good.lastIncome = 0;
            }
        }

        deal.clear();
    }

    public void updateRecorder(){
        for (Consumer c: consumers){
            c.updateRecorder();
        }
    }
}
