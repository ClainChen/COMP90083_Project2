package com.example.model.experiment;

import com.example.model.Main;
import com.example.model.utils.Enums;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExperimentSpace {
    public ExecutorService threadPool;
    // Experimental Parameters
    public int batchSize = 100;
    public ArrayList<Enums.SellerStrategy> sellerStrategies;
    public ArrayList<Enums.BuyerStrategy> buyerStrategies;
    public ArrayList<Boolean> isJudgements;
    private ArrayList<Experiment> experiments;

    public ExperimentSpace() {
        threadPool = Executors.newCachedThreadPool();
        batchSize = 100;
        sellerStrategies = new ArrayList<>(
                Arrays.asList(Enums.SellerStrategy.NORMAL, Enums.SellerStrategy.AGGRESSIVE,
                        Enums.SellerStrategy.RANDOM, Enums.SellerStrategy.MIX_UP)
        );
        buyerStrategies = new ArrayList<>(
                Arrays.asList(Enums.BuyerStrategy.NORMAL, Enums.BuyerStrategy.AGGRESSIVE,
                        Enums.BuyerStrategy.RANDOM, Enums.BuyerStrategy.MIX_UP)
        );
        isJudgements = new ArrayList<>(Arrays.asList(false, true));
        experiments = new ArrayList<>();
    }

    public void executeExperiment() {
        int id = 0;
        for (Enums.SellerStrategy sellerStrategy : sellerStrategies) {
            for (Enums.BuyerStrategy buyerStrategy : buyerStrategies) {
                for (Boolean isJudgment : isJudgements) {
                    Experiment experiment = new Experiment(id, buyerStrategy, sellerStrategy, isJudgment, batchSize);
                    experiments.add(experiment);
                    threadPool.submit(experiment);
                    id++;
                    System.out.println("Experiment #" + id + " Start: " + sellerStrategy.name() + " " + buyerStrategy.name() + " " + isJudgment + " " + batchSize);
                }
            }
        }

        int totalQuests = experiments.stream().mapToInt(experiment -> experiment.batchSize).sum();

        Timer timer = new Timer(500, e -> {
            int finishedQuests = 0;
            for (Experiment experiment : experiments) {
                finishedQuests += experiment.completedExp;
            }
            printProgress(finishedQuests, totalQuests);

            if (finishedQuests == totalQuests) {
                ((Timer) e.getSource()).stop();
                Main.mainWindow.bStartExp.setEnabled(true);
            }
        });

        timer.start();


    }

    public void outputCSV(){
        for (Experiment experiment : experiments) {
            experiment.exportToCSV();
        }
    }

    public static void printProgress(int current, int total) {
        int barLength = 50;
        int progress = (current * barLength) / total;

        StringBuilder progressBar = new StringBuilder();
        progressBar.append("[");
        for (int i = 0; i < barLength; i++) {
            if (i < progress) {
                progressBar.append("=");
            } else {
                progressBar.append(" ");
            }
        }
        progressBar.append("] ");

        int percent = (current * 100) / total;
        progressBar.append(percent).append("%");

        progressBar.append(" (").append(current).append("/").append(total).append(")");

        System.out.print("\r" + progressBar.toString());
    }
}
