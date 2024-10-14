package com.example.model.utils;

public class Parameters {
    public static final int numBuyers = 50;
    public static final int numSellers = 50;

    public static final double startPrice = 100;
    public static final double startExpectation = 10;

    public static final double priceAddonRate = 0.01;
    public static final double priceReductionRate = 0.01;

    public static final int defaultInventory = 50;
    public static final int defaultDemand = 50;

    public static final double expectationAddonRate = 0.01;
    public static final double expectationReductionRate = 0.01;

    public static final double startingBudget = 500;

    public static final Enums.SellerStrategy sellerStrategy = Enums.SellerStrategy.NORMAL;
    public static final Enums.BuyerStrategy buyerStrategy = Enums.BuyerStrategy.NORMAL;
}
