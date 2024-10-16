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

    public ArrayList<Double> maxBudgetHistory;
    public ArrayList<Double> minBudgetHistory;
    public ArrayList<Double> avgBudgetHistory;

    public ArrayList<Double> percentSpentHistory;
    public ArrayList<Integer> numSoldOutHistory;
    public ArrayList<Integer> numSatisfiedHistory;
    public ArrayList<Integer> judgeDealHistory;

    public int day;

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
        maxBudgetHistory = new ArrayList<>();
        minBudgetHistory = new ArrayList<>();
        avgBudgetHistory = new ArrayList<>();
        percentSpentHistory = new ArrayList<>();
        numSoldOutHistory = new ArrayList<>();
        numSatisfiedHistory = new ArrayList<>();
        judgeDealHistory = new ArrayList<>();

        day = 0;
    }

    public String getCSVFormat() {
        StringBuilder csv = new StringBuilder();
        csv.append("day,").append(day).append("\n");

        csv.append("id,")
                .append("maxPriceHistory,minPriceHistory,avgPriceHistory,")
                .append("maxExpectationHistory,minExpectationHistory,avgExpectationHistory,")
                .append("avgTradePriceHistory,avgSpentHistory,avgIncomeHistory,")
                .append("totalInventoryHistory,totalBoughtHistory,totalDemandHistory,")
                .append("maxBudgetHistory,minBudgetHistory,avgBudgetHistory,")
                .append("percentSpentHistory,numSoldOutHistory,numSatisfiedHistory,judgeDealHistory\n");


        int maxRows = getMaxHistoryLength();

        for (int i = 0; i < maxRows; i++) {
            csv.append(i + 1).append(",");
            csv.append(getValue(maxPriceHistory, i)).append(",")
                    .append(getValue(minPriceHistory, i)).append(",")
                    .append(getValue(avgPriceHistory, i)).append(",")

                    .append(getValue(maxExpectationHistory, i)).append(",")
                    .append(getValue(minExpectationHistory, i)).append(",")
                    .append(getValue(avgExpectationHistory, i)).append(",")

                    .append(getValue(avgTradePriceHistory, i)).append(",")
                    .append(getValue(avgSpentHistory, i)).append(",")
                    .append(getValue(avgIncomeHistory, i)).append(",")

                    .append(getValue(totalInventoryHistory, i)).append(",")
                    .append(getValue(totalBoughtHistory, i)).append(",")
                    .append(getValue(totalDemandHistory, i)).append(",")

                    .append(getValue(maxBudgetHistory, i)).append(",")
                    .append(getValue(minBudgetHistory, i)).append(",")
                    .append(getValue(avgBudgetHistory, i)).append(",")

                    .append(getValue(percentSpentHistory, i)).append(",")
                    .append(getValue(numSoldOutHistory, i)).append(",")
                    .append(getValue(numSatisfiedHistory, i)).append(",")
                    .append(getValue(judgeDealHistory, i)).append("\n");
        }

        return csv.toString();
    }


    private int getMaxHistoryLength() {
        return Math.max(
                Math.max(
                        Math.max(
                                Math.max(getMaxListLength(maxPriceHistory, minPriceHistory, avgPriceHistory),
                                        getMaxListLength(maxExpectationHistory, minExpectationHistory, avgExpectationHistory)),
                                getMaxListLength(avgTradePriceHistory, avgSpentHistory, avgIncomeHistory)),
                        getMaxListLength(totalInventoryHistory, totalBoughtHistory, totalDemandHistory)),
                Math.max(
                        getMaxListLength(maxBudgetHistory, minBudgetHistory, avgBudgetHistory, percentSpentHistory),
                        getMaxListLength(numSoldOutHistory, numSatisfiedHistory, judgeDealHistory)));
    }


    @SafeVarargs
    private final <T> int getMaxListLength(ArrayList<T>... lists) {
        int maxLength = 0;
        for (ArrayList<T> list : lists) {
            if (list != null) {
                maxLength = Math.max(maxLength, list.size());
            }
        }
        return maxLength;
    }

    private String getValue(ArrayList<?> list, int index) {
        if (index < list.size()) {
            return list.get(index).toString();
        } else {
            return "";  // 空值
        }
    }
}
