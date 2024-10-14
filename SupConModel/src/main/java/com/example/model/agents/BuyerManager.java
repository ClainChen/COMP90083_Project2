package com.example.model.agents;

import com.example.model.abm.ConstantParameters;
import com.example.model.utils.Parameters;

import java.util.ArrayList;

public class BuyerManager {
    public ArrayList<Buyer> buyers;
    public int numBuyers;

    public BuyerManager() {
        buyers = new ArrayList<>();
        numBuyers = Parameters.numBuyers;
        for (int i = 0; i < numBuyers; i++) {
            Buyer b = new Buyer(i);
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

    public double getAvgSpent(){
        double sum = 0;
        for (Buyer b : buyers) {
            sum += b.startBudget - b.budget;
        }
        return sum / numBuyers;
    }

    public double getPercentSpent(){
        double sum = 0;
        double totalSpent = 0;
        for (Buyer b : buyers) {
            sum += b.startBudget;
            totalSpent += b.startBudget - b.budget;
        }
        return totalSpent / sum;
    }

    public double getMaxBudget(){
        double maxBudget = 0;
        for (Buyer b : buyers) {
            maxBudget = Math.max(maxBudget, b.budget);
        }
        return maxBudget;
    }

    public double getMinBudget(){
        double minBudget = Double.MAX_VALUE;
        for (Buyer b : buyers) {
            minBudget = Math.min(minBudget, b.budget);
        }
        return minBudget;
    }

    public double getAvgBudget(){
        double sumBudget = 0;
        for (Buyer b : buyers) {
            sumBudget += b.budget;
        }
        return sumBudget / numBuyers;
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

    public void updateStrategies(){
        for (Buyer b : buyers) {
            b.setStrategy(Parameters.buyerStrategy);
        }
    }

    public int getNumSatisfied(){
        int satisfied = 0;
        for (Buyer b : buyers) {
            if (b.demand == 0) {
                satisfied++;
            }
        }
        return satisfied;
    }


}
