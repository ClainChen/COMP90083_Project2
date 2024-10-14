package com.example.model.agents;

import com.example.model.utils.Enums;
import com.example.model.utils.Parameters;

import java.util.Random;

public class Buyer {
    public int id;
    public double startBudget;
    public double budget;
    public int demand;
    public double expectation;
    public Enums.BuyerStrategy strategy;
    public double addonRate;
    public double reductionRate;

    public Buyer(int id) {
        this.id = id;
        this.startBudget = Parameters.startingBudget / 2 + (int) (Math.random() * (Parameters.startingBudget / 2));
        this.budget = this.startBudget;
        this.demand = 1 + (int) (Math.random() * Parameters.defaultDemand);
        this.expectation = 1 + (int) (Math.random() * Parameters.startExpectation);

        setStrategy(Parameters.buyerStrategy);
    }

    public void decreaseExpectation() {
        double newExpectation = expectation;
        double rate = 1;
        switch (strategy) {
            case NORMAL, AGGRESSIVE -> {
                rate -= reductionRate;
                newExpectation = expectation * rate;
            }
            case RANDOM -> {
                Random rand = new Random();
                rate += (5 - rand.nextDouble() * 10) / 100;
                newExpectation = expectation * rate;
            }
        }
        if (newExpectation < 0.01) {
            newExpectation = 0.01;
        }
        if (newExpectation > budget){
            newExpectation = budget;
        }
        if (newExpectation == expectation){
            newExpectation -= 0.01;
        }
        expectation = newExpectation;
    }

    public void increaseExpectation() {
        double newExpectation = expectation;
        double rate = 1;
        switch (strategy) {
            case NORMAL, AGGRESSIVE -> {
                rate += addonRate;
                newExpectation = expectation * rate;
            }
            case RANDOM -> {
                Random rand = new Random();
                rate += (5 - rand.nextDouble() * 10) / 100;
                newExpectation = expectation * rate;
            }
        }
        if (newExpectation < 0.01) {
            newExpectation = 0.01;
        }
        if (newExpectation > budget){
            newExpectation = budget;
        }
        if (newExpectation == expectation){
            newExpectation += 0.01;
        }
        expectation = newExpectation;
    }

    public void setStrategy(Enums.BuyerStrategy strategy){
        this.strategy = strategy;
        switch (strategy){
            case NORMAL, RANDOM -> {
                addonRate = Parameters.expectationAddonRate *  2.5;
                reductionRate = Parameters.expectationReductionRate * 2.0;
            }
            case AGGRESSIVE -> {
                addonRate = Parameters.expectationAddonRate * 7.5;
                reductionRate = Parameters.expectationReductionRate * 0.8;
            }
            case MIX_UP -> {
                int tag = (int) (Math.random() * 3);
                switch (tag){
                    case 0 -> {
                        this.strategy = Enums.BuyerStrategy.NORMAL;
                        addonRate = Parameters.expectationAddonRate * 2.5;
                        reductionRate = Parameters.expectationReductionRate * 2.0;
                    }
                    case 1 -> {
                        this.strategy = Enums.BuyerStrategy.AGGRESSIVE;
                        addonRate = Parameters.expectationAddonRate * 7.5;
                        reductionRate = Parameters.expectationReductionRate * 0.8;
                    }
                    case 2 -> {
                        this.strategy = Enums.BuyerStrategy.RANDOM;
                        addonRate = Parameters.expectationAddonRate * 2.5;
                        reductionRate = Parameters.expectationReductionRate * 2.0;
                    }
                }
            }
        }
    }

}
