package com.example.model.utils;

public class Parameters {
    public static int numBuyers = 100;
    public static int numSellers = 100;

    public static final double startPrice = 60;
    public static final double startExpectation = 60;

    public static final double priceAddonRate = 0.01;
    public static final double priceReductionRate = 0.01;

    public static final int defaultInventory = 60;
    public static final int defaultDemand = 60;

    public static final double expectationAddonRate = 0.01;
    public static final double expectationReductionRate = 0.01;

    public static double startingBudget = 1800;

    public static Enums.SellerStrategy sellerStrategy = Enums.SellerStrategy.NORMAL;
    public static Enums.BuyerStrategy buyerStrategy = Enums.BuyerStrategy.NORMAL;

    public static boolean isJudgement = false;
    public static boolean canChoose = false;

    public static int numChoices = 4;
    public static int bargainRound = 3;
    public static double bargainFactor = 1.5;
}
