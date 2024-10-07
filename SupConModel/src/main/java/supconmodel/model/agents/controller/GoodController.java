package supconmodel.model.agents.controller;

import supconmodel.SupConModelMain;
import supconmodel.model.agents.Good;
import supconmodel.model.agents.GoodSummarizer;

import java.util.ArrayList;

public class GoodController {
    public ArrayList<GoodSummarizer> totalGoods;

    public GoodController() {
        totalGoods = new ArrayList<>();
    }

    public void init(){
        totalGoods.clear();
        for (Good good: SupConModelMain.allGoods){
            GoodSummarizer goodController = new GoodSummarizer(good.id, good.name, good.type,
                    good.cost, good.expectProfitability, good.basic, good.convenience, good.luxury);
            totalGoods.add(goodController);
        }
    }

    public double getGoodCompetitive(int id, int soldNum, SupplierController supplierController){
        GoodSummarizer goodSummarizer = getGoodSummarizer(id);
        if (goodSummarizer != null){
            return 1 - ((double) soldNum / Math.max(1, goodSummarizer.getTotalSoldNum(supplierController.suppliers)));
        }
        return -1;
    }

    public GoodSummarizer getGoodSummarizer(int id){
        for (GoodSummarizer good: totalGoods){
            if (good.id == id){
                return good;
            }
        }
        return null;
    }

}
