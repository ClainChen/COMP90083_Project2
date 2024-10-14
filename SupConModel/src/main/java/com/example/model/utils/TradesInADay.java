package com.example.model.utils;

import java.util.ArrayList;

public class TradesInADay {
    public int day;
    public double price;
    public ArrayList<Integer> buyerIDs;

    public TradesInADay(int day, double price) {
        this.day = day;
        this.price = price;
        this.buyerIDs = new ArrayList<>();
    }
}
