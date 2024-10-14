package com.example.model.agents;

import com.example.model.abm.ConstantParameters;
import com.example.model.utils.Enums;
import com.example.model.utils.Parameters;
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

    public Seller(int id) {
        this.id = id;
        this.price = (Parameters.startPrice / 2) + (Math.random() * Parameters.startPrice / 2);
        this.startInventory = 1 + (int) (Math.random() * Parameters.defaultInventory);
        this.inventory = startInventory;
        this.strategy = Parameters.sellerStrategy;
        this.potential_buyers = 0;
        trade_history = new ArrayList<>();

        setStrategy(strategy);
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

    public void setStrategy(Enums.SellerStrategy strategy){
        this.strategy = strategy;
        switch (strategy){
            case NORMAL, RANDOM -> {
                addonRate = Parameters.priceAddonRate * 2.0;
                reductionRate = Parameters.priceReductionRate * 2.5;
            }
            case AGGRESSIVE -> {
                addonRate = Parameters.priceAddonRate * 1.5;
                reductionRate = Parameters.priceReductionRate * 7.5;
            }
            case MIX_UP -> {
                int tag = (int) (Math.random() * 3);
                switch (tag){
                    case 0 -> {
                        this.strategy = Enums.SellerStrategy.NORMAL;
                        addonRate = Parameters.priceAddonRate * 2.0;
                        reductionRate = Parameters.priceReductionRate * 2.5;
                    }
                    case 1 -> {
                        this.strategy = Enums.SellerStrategy.AGGRESSIVE;
                        addonRate = Parameters.priceAddonRate * 1.5;
                        reductionRate = Parameters.priceReductionRate * 7.5;
                    }
                    case 2 -> {
                        this.strategy = Enums.SellerStrategy.RANDOM;
                        addonRate = Parameters.priceAddonRate * 2.0;
                        reductionRate = Parameters.priceReductionRate * 2.5;
                    }
                }
            }
        }
    }


}
