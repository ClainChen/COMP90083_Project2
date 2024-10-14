package com.example.model.agents;

import com.example.model.utils.Parameters;
import com.example.model.utils.TradesInADay;

import java.util.ArrayList;

public class SellerManager {
    public ArrayList<Seller> sellers;
    public int numSeller;

    public SellerManager() {
        sellers = new ArrayList<>();
        numSeller = Parameters.numSellers;
        for (int i = 0; i < numSeller; i++) {
            sellers.add(new Seller(i));
        }
    }

    public Seller getRandomSeller(){
        ArrayList<Seller> availableSellers = new ArrayList<>();
        for (Seller seller: sellers) {
            if (seller.inventory > 0){
                availableSellers.add(seller);
            }
        }
        if (availableSellers.isEmpty()) {
            return null;
        }
        int size = availableSellers.size();
        return availableSellers.get((int)(Math.random() * size));
    }

    public double getMaxPrice(){
        double maxPrice = 0;
        for (Seller seller: sellers) {
            if (seller.inventory > 0){
                maxPrice = Math.max(maxPrice, seller.price);
            }
        }
        return maxPrice;
    }

    public double getMinPrice(){
        double minPrice = Double.MAX_VALUE;
        for (Seller seller: sellers) {
            if (seller.inventory > 0){
                minPrice = Math.min(minPrice, seller.price);
            }
        }
        return minPrice == Double.MAX_VALUE ? 0 : minPrice;
    }

    public double getAvgPrice(){
        double sum = 0;
        int numSeller = 0;
        for (Seller seller: sellers) {
            if (seller.inventory > 0){
                sum += seller.price;
                numSeller++;
            }
        }
        return sum / Math.max(1, numSeller);
    }

    public double getAvgTradePrice(){
        double sum = 0;
        double numTrades = 0;
        for (Seller seller: sellers) {
            for (TradesInADay trade: seller.trade_history) {
                int thisNumTrades = trade.buyerIDs.size();
                numTrades += thisNumTrades;
                sum += trade.price * thisNumTrades;
            }
        }
        return sum / Math.max(1, numTrades);
    }

    public double getAvgIncome(){
        double sum = 0;
        int numSeller = 0;
        for (Seller seller: sellers) {
            if (seller.inventory > 0){
                sum += seller.income;
                numSeller++;
            }
        }
        return sum / numSeller;
    }

    public double getMaxInventory(){
        double maxInventory = 0;
        for (Seller seller: sellers) {
            maxInventory = Math.max(maxInventory, seller.inventory);
        }
        return maxInventory;
    }

    public int getInventory(){
        int inventory = 0;
        for (Seller seller: sellers) {
            inventory += seller.inventory;
        }
        return inventory;
    }

    public int getBought(){
        int bought = 0;
        for (Seller seller: sellers) {
            bought += seller.startInventory - seller.inventory;
        }
        return bought;
    }

    public void updateStrategies(){
        for (Seller seller: sellers) {
            seller.setStrategy(Parameters.sellerStrategy);
        }
    }

    public int getNumSoldOut(){
        int numSoldOut = 0;
        for (Seller seller: sellers) {
            if (seller.inventory == 0){
                numSoldOut++;
            }
        }
        return numSoldOut;
    }

}
