package com.example.model.agents;

import com.example.model.abm.ConstantParameters;

import java.util.ArrayList;

public class BuyerManager {
    public ArrayList<Buyer> buyers;
    public int numBuyers;

    public BuyerManager(ConstantParameters parameters) {
        buyers = new ArrayList<>();
        numBuyers = parameters.numBuyers;
        for (int i = 0; i < numBuyers; i++) {
            Buyer b = new Buyer(i, parameters);
            buyers.add(b);
        }
    }

    public double getMaxExpectation(){
        double maxExp = 0;
        for (Buyer b : buyers) {
            if (b.budget > 0 && b.demand > 0) {
                maxExp = Math.max(maxExp, b.expectation);
            }
        }
        return maxExp;
    }

    public double getMinExpectation(){
        double minExp = Double.MAX_VALUE;
        for (Buyer b : buyers) {
            if (b.budget > 0 && b.demand > 0) {
                minExp = Math.min(minExp, b.expectation);
            }
        }
        return minExp;
    }

    public double getAvgExpectation(){
        double sumExp = 0;
        int numBuyers = 0;
        for (Buyer b : buyers) {
            if (b.budget > 0 && b.demand > 0) {
                sumExp += b.expectation;
                numBuyers++;
            }
        }
        return sumExp / numBuyers;
    }

    public double getAvgSpent(ConstantParameters parameters){
        double sum = 0;
        for (Buyer b : buyers) {
            sum += parameters.startingBudget - b.budget;
        }
        return sum / numBuyers;
    }

    public double getMaxBudget(){
        double maxBudget = 0;
        for (Buyer b : buyers) {
            double inv = b.budget;
            maxBudget = Math.max(maxBudget, inv);
        }
        return maxBudget;
    }

    public double getMaxDemand(){
        double maxDemand = 0;
        for (Buyer b : buyers) {
            maxDemand = Math.max(maxDemand, b.demand);
        }
        return maxDemand;
    }

    public int getDemand(){
        int demand = 0;
        for (Buyer b : buyers) {
            demand += b.demand;
        }
        return demand;
    }


}
