package com.example.model.experiment;

import com.example.model.abm.ABM;
import com.example.model.utils.Enums;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Experiment implements Runnable {
    public Batch batch;
    public Enums.BuyerStrategy buyerStrategy;
    public Enums.SellerStrategy sellerStrategy;
    public Boolean isJudgement;
    public int batchSize;
    public int expID;
    public int completedExp;

    public Experiment(int expID, Enums.BuyerStrategy buyerStrategy, Enums.SellerStrategy sellerStrategy, Boolean isJudgement, int batchSize) {
        this.buyerStrategy = buyerStrategy;
        this.sellerStrategy = sellerStrategy;
        this.isJudgement = isJudgement;
        this.batchSize = batchSize;
        this.expID = expID;
        completedExp = 0;
    }

    public void exportToCSV(){
        ArrayList<String> batchCSV = batch.getAllCSVFormat();
        String prefix = "output/" + batch;
        File directory = new File(prefix);

        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Folder Created: " + prefix);
            } else {
                System.out.println("Folder create failed: " + prefix);
                return;
            }
        }

        prefix += "/";

        String typeFile = prefix + "type.csv";
        outputCSV(typeFile, batchCSV.getFirst());
        for (int i = 1; i < batch.supervisors.size() + 1; i++) {
            String path = prefix + i + ".csv";
            outputCSV(path, batchCSV.get(i));
        }
        System.out.println("Experiment exported to CSV");
    }

    public void outputCSV(String outputPath, String csvString){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath))) {
            bw.write(csvString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
//        System.out.println("Experiment ID " + expID + "  started");
        batch = new Batch(buyerStrategy, sellerStrategy, isJudgement);
        for (int i = 0; i < batchSize; i++) {
            ABM abmQuest = new ABM();
            new Thread(() -> {
                abmQuest.experimentSetup();
                abmQuest.quickSpeed();
                abmQuest.experimentGo();
            }).start();
            try {
                abmQuest.awaitCompletion();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            batch.addSupervisor(abmQuest.supervisor);
//            System.out.printf("Experiment ID %d, Experiment %d completed%n", expID, i+1);
            completedExp++;
        }
    }
}
