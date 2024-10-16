package com.example.model.utils;

import java.util.ArrayList;

public class TradesInADay {
    public int day;
    public ArrayList<Double> dealPrices;

    public TradesInADay(int day) {
        this.day = day;
        this.dealPrices = new ArrayList<>();
    }

    public double dealIncomeForADay(){
        double total = 0;
        for (Double price : dealPrices) {
            total += price;
        }
        return total;
    }
}
