package com.example.model.agents;

import com.example.model.abm.ConstantParameters;
import com.example.model.utils.Enums;

import java.util.Random;

public class Buyer {
    public int id;
    public double budget;
    public int demand;
    public double expectation;
    public Enums.BuyerStrategy strategy;
    public double addonRate;
    public double reductionRate;

    public Buyer(int id, ConstantParameters parameters) {
        this.id = id;
        this.budget = parameters.startingBudget / 2 + (int) (Math.random() * (parameters.startingBudget / 2));
        this.demand = 1 + (int) (Math.random() * parameters.defaultDemand);
        this.expectation = 1 + (int) (Math.random() * parameters.startExpectation);
        this.strategy = parameters.buyerStrategy;

        switch (parameters.buyerStrategy){
            case NORMAL, RANDOM -> {
                addonRate = parameters.expectationAddonRate *  2.5;
                reductionRate = parameters.expectationReductionRate * 2.0;
            }
            case AGGRESSIVE -> {
                addonRate = parameters.expectationAddonRate * 7.5;
                reductionRate = parameters.expectationReductionRate * 1.5;
            }
            case MIX_UP -> {
                int tag = (int) (Math.random() * 3);
                switch (tag){
                    case 0 -> {
                        this.strategy = Enums.BuyerStrategy.NORMAL;
                        addonRate = parameters.expectationAddonRate * 2.5;
                        reductionRate = parameters.expectationReductionRate * 2.5;
                    }
                    case 1 -> {
                        this.strategy = Enums.BuyerStrategy.AGGRESSIVE;
                        addonRate = parameters.expectationAddonRate * 7.5;
                        reductionRate = parameters.expectationReductionRate * 1.5;
                    }
                    case 2 -> {
                        this.strategy = Enums.BuyerStrategy.RANDOM;
                        addonRate = parameters.expectationAddonRate;
                        reductionRate = parameters.expectationReductionRate;
                    }
                }
            }
        }
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

}
