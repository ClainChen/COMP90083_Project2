package com.example.model.agents;

import com.example.model.abm.ConstantParameters;
import com.example.model.utils.Enums;
import com.example.model.utils.TradesInADay;

import java.util.ArrayList;
import java.util.Random;

public class Seller {
    public int id;
    public double price;
    public int startInventory;
    public int inventory;
    public double income;
    public Enums.SellerStrategy strategy;
    public int potential_buyers;
    public double addonRate;
    public double reductionRate;

    public ArrayList<TradesInADay> trade_history;

    public Seller(int id, ConstantParameters parameters) {
        this.id = id;
        this.price = (parameters.startPrice / 2) + (Math.random() * parameters.startPrice / 2);
        this.startInventory = 1 + (int) (Math.random() * parameters.defaultInventory);
        this.inventory = startInventory;
        this.strategy = parameters.sellerStrategy;
        this.potential_buyers = 0;
        trade_history = new ArrayList<>();

        switch (parameters.sellerStrategy){
            case NORMAL, RANDOM -> {
                addonRate = parameters.priceAddonRate * 2.0;
                reductionRate = parameters.priceReductionRate * 2.5;
            }
            case AGGRESSIVE -> {
                addonRate = parameters.priceAddonRate * 1.5;
                reductionRate = parameters.priceReductionRate * 7.5;
            }
            case MIX_UP -> {
                int tag = (int) (Math.random() * 3);
                switch (tag){
                    case 0 -> {
                        this.strategy = Enums.SellerStrategy.NORMAL;
                        addonRate = parameters.priceAddonRate * 2.5;
                        reductionRate = parameters.priceReductionRate * 2.5;
                    }
                    case 1 -> {
                        this.strategy = Enums.SellerStrategy.AGGRESSIVE;
                        addonRate = parameters.priceAddonRate * 2.5;
                        reductionRate = parameters.priceReductionRate * 7.5;
                    }
                    case 2 -> {
                        this.strategy = Enums.SellerStrategy.RANDOM;
                        addonRate = parameters.priceAddonRate * 2.5;
                        reductionRate = parameters.priceReductionRate * 2.5;
                    }
                }
            }
        }
    }

    public void decreasePrice() {
        double newPrice = price;
        double rate = 1;
        switch (strategy) {
            case NORMAL, AGGRESSIVE -> {
                rate -= reductionRate;
                newPrice = price * rate;

            }
            case RANDOM -> {
                Random rand = new Random();
                rate += (5 - rand.nextDouble() * 10) / 100;
                newPrice = price * rate;
            }
        }
        if (newPrice < 0.01) {
            newPrice = 0.01;
        }
        if (newPrice == price){
            price -= 0.01;
        }
//        System.out.println("New price: " + newPrice);
        price = newPrice;
    }

    public void increasePrice() {
        double newPrice = price;
        double rate = 1;
        switch (strategy) {
            case NORMAL, AGGRESSIVE -> {
                rate += addonRate;
                newPrice = price * rate;

            }
            case RANDOM -> {
                Random rand = new Random();
                rate += (5 - rand.nextDouble() * 10) / 100;
                newPrice = price * rate;
            }
        }
        if (newPrice < 0.01) {
            newPrice = 0.01;
        }
        if (newPrice == price){
            price += 0.01;
        }
//        System.out.println("New price: " + newPrice);
        price = newPrice;
    }


}
