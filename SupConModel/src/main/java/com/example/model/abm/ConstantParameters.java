package com.example.model.abm;

import com.example.model.utils.Enums;
import com.example.model.utils.Parameters;

public class ConstantParameters {
    public int numBuyers;
    public int numSellers;

    public double startPrice;
    public double startExpectation;

    public double priceAddonRate;
    public double priceReductionRate;

    public int defaultInventory;

    public double expectationAddonRate;
    public double expectationReductionRate;

    public double startingBudget;
    public int defaultDemand;

    public Enums.SellerStrategy sellerStrategy;
    public Enums.BuyerStrategy buyerStrategy;

    public ConstantParameters() {
        numBuyers = Parameters.numBuyers;
        numSellers = Parameters.numSellers;

        startPrice = Parameters.startPrice;
        startExpectation = Parameters.startExpectation;

        defaultInventory = Parameters.defaultInventory;

        startingBudget = Parameters.startingBudget;
        defaultDemand = Parameters.defaultDemand;

        sellerStrategy = Parameters.sellerStrategy;
        buyerStrategy = Parameters.buyerStrategy;

        priceAddonRate = Parameters.priceAddonRate;
        priceReductionRate = Parameters.priceReductionRate;

        expectationAddonRate = Parameters.expectationAddonRate;
        expectationReductionRate = Parameters.expectationReductionRate;


    }
}
