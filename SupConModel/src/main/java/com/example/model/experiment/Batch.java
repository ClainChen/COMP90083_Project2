package com.example.model.experiment;

import com.example.model.abm.Supervisor;
import com.example.model.utils.Enums;

import java.util.ArrayList;

public class Batch {
    public ArrayList<Supervisor> supervisors;
    public Enums.BuyerStrategy buyerStrategy;
    public Enums.SellerStrategy sellerStrategy;
    public boolean isJudgement;

    public Batch(Enums.BuyerStrategy buyerStrategy, Enums.SellerStrategy sellerStrategy, boolean isJudgement) {
        supervisors = new ArrayList<>();
        this.buyerStrategy = buyerStrategy;
        this.sellerStrategy = sellerStrategy;
        this.isJudgement = isJudgement;
    }

    public void addSupervisor(Supervisor supervisor) {
        supervisors.add(supervisor);
    }

    public ArrayList<String> getAllCSVFormat(){
        StringBuilder sb = new StringBuilder();
        sb.append("Buyer Strategy,").append(buyerStrategy.toString()).append("\n");
        sb.append("Seller Strategy,").append(sellerStrategy.toString()).append("\n");
        sb.append("Is Judgement,").append(isJudgement).append("\n");

        ArrayList<String> allCSVs = new ArrayList<>();
        allCSVs.add(sb.toString());
        for (Supervisor supervisor : supervisors) {
            allCSVs.add(supervisor.getCSVFormat());
        }

        return allCSVs;
    }

    @Override
    public String toString(){
        return buyerStrategy.name() + "-" + sellerStrategy.name() + "-" + isJudgement;
    }
}
