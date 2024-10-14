package com.example.model.abm;

import java.util.ArrayList;

public class Supervisor {
    public ArrayList<Double> maxPriceHistory;
    public ArrayList<Double> minPriceHistory;
    public ArrayList<Double> avgPriceHistory;

    public ArrayList<Double> maxExpectationHistory;
    public ArrayList<Double> minExpectationHistory;
    public ArrayList<Double> avgExpectationHistory;

    public ArrayList<Double> avgTradePriceHistory;
    public ArrayList<Double> avgSpentHistory;
    public ArrayList<Double> avgIncomeHistory;

    public ArrayList<Integer> totalInventoryHistory;
    public ArrayList<Integer> totalBoughtHistory;
    public ArrayList<Integer> totalDemandHistory;

    public Supervisor() {
        maxPriceHistory = new ArrayList<>();
        minPriceHistory = new ArrayList<>();
        avgPriceHistory = new ArrayList<>();
        maxExpectationHistory = new ArrayList<>();
        minExpectationHistory = new ArrayList<>();
        avgExpectationHistory = new ArrayList<>();
        avgTradePriceHistory = new ArrayList<>();
        avgSpentHistory = new ArrayList<>();
        avgIncomeHistory = new ArrayList<>();
        totalInventoryHistory = new ArrayList<>();
        totalBoughtHistory = new ArrayList<>();
        totalDemandHistory = new ArrayList<>();
    }
}
