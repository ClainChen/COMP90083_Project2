package com.example.model.experiment;

import com.example.model.abm.Supervisor;
import com.example.model.utils.Enums;

import java.util.ArrayList;

public class Batch {
    public ArrayList<Supervisor> supervisors;
    public Enums.BuyerStrategy buyerStrategy;
    public Enums.SellerStrategy sellerStrategy;
    public boolean isJudgement;
    public boolean canChoose;
    public int numChoices;
    public int bargainRounds;
    public double bargainFactor;

    public Batch(Enums.BuyerStrategy buyerStrategy, Enums.SellerStrategy sellerStrategy, boolean isJudgement, boolean canChoose,
                 int numChoices, int bargainRounds, double bargainFactor) {
        supervisors = new ArrayList<>();
        this.buyerStrategy = buyerStrategy;
        this.sellerStrategy = sellerStrategy;
        this.isJudgement = isJudgement;
        this.canChoose = canChoose;
        this.numChoices = numChoices;
        this.bargainRounds = bargainRounds;
        this.bargainFactor = bargainFactor;
    }

    public void addSupervisor(Supervisor supervisor) {
        supervisors.add(supervisor);
    }

    public ArrayList<String> getAllCSVFormat(){
        String sb = "Buyer Strategy," + buyerStrategy.toString() + "\n" +
                "Seller Strategy," + sellerStrategy.toString() + "\n" +
                "Is Judgement," + isJudgement + "\n" +
                "Can Choose," + canChoose + "\n" +
                "Num Choices," + numChoices + "\n" +
                "Bargain Rounds," + bargainRounds + "\n" +
                "Bargain Factor," + bargainFactor;

        ArrayList<String> allCSVs = new ArrayList<>();
        allCSVs.add(sb);
        for (Supervisor supervisor : supervisors) {
            allCSVs.add(supervisor.getCSVFormat());
        }

        return allCSVs;
    }

    @Override
    public String toString(){
        return "CHOICES-" + buyerStrategy.name() + "-" + sellerStrategy.name() + "-" + isJudgement + "-" + canChoose + "-" +
                numChoices + "-" + bargainRounds + "-" + bargainFactor;
    }
}
