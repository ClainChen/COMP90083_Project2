package com.example.model.experiment;

import com.example.model.Main;
import com.example.model.utils.Enums;
import com.example.model.utils.Parameters;

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
    public ArrayList<Boolean> canChooses;
    private ArrayList<Experiment> experiments;
    public ArrayList<Integer> numChoices;
    public ArrayList<Integer> bargainRounds;
    public ArrayList<Double> bargainFactors;

    public ExperimentSpace() {
        threadPool = Executors.newCachedThreadPool();
        batchSize = 1000;
        sellerStrategies = new ArrayList<>(
                Arrays.asList(Enums.SellerStrategy.NORMAL, Enums.SellerStrategy.AGGRESSIVE, Enums.SellerStrategy.MIX_UP)
        );
        buyerStrategies = new ArrayList<>(
                Arrays.asList(Enums.BuyerStrategy.NORMAL, Enums.BuyerStrategy.AGGRESSIVE, Enums.BuyerStrategy.MIX_UP)
        );
        isJudgements = new ArrayList<>(Arrays.asList(false, true));
        canChooses = new ArrayList<>(Arrays.asList(false, true));
        experiments = new ArrayList<>();
        numChoices = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 6));
        bargainRounds = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 6));
        bargainFactors = new ArrayList<>(Arrays.asList(0.3, 0.5, 0.7, 0.9, 1.1));
    }

    public void executeExperiment() {
        int id = 0;
        for (int i = 0; i < 3; i++){
            Enums.BuyerStrategy buyerStrategy = buyerStrategies.get(i);
            Enums.SellerStrategy sellerStrategy = sellerStrategies.get(i);
            for (Boolean isJudgment : isJudgements) {
                for (Boolean canChoose : canChooses) {
                    Experiment experiment = new Experiment(id, buyerStrategy, sellerStrategy, isJudgment, canChoose,
                            batchSize, Parameters.numChoices, Parameters.bargainRound, Parameters.bargainFactor);
                    experiments.add(experiment);
                    threadPool.submit(experiment);
                    id++;
                    System.out.println("Experiment #" + id +
                            " Start: " +
                            sellerStrategy.name() + " " +
                            buyerStrategy.name() + " " +
                            isJudgment + " " +
                            canChoose + " " +
                            batchSize);
                }
            }
        }

        showProgress();
    }

    public void executeSensitivityExperiment() {
        int id = 0;
        for (int numChoice : numChoices) {
            Experiment experiment = new Experiment(id, Enums.BuyerStrategy.MIX_UP, Enums.SellerStrategy.MIX_UP, Parameters.isJudgement, Parameters.canChoose,
                    batchSize, numChoice, Parameters.bargainRound, Parameters.bargainFactor);
            experiments.add(experiment);
            threadPool.submit(experiment);
            id++;
            System.out.println("Sensitivity Experiment #" + id +
                    " Start: " +
                    Enums.BuyerStrategy.MIX_UP.name() + " " +
                    Enums.SellerStrategy.MIX_UP.name() + " " +
                    Parameters.isJudgement + " " +
                    Parameters.canChoose + " " +
                    batchSize + " " +
                    numChoice + " " +
                    Parameters.bargainRound + " " +
                    Parameters.bargainFactor);
        }

        showProgress();
    }

    public void outputCSV(){
        for (Experiment experiment : experiments) {
            experiment.exportToCSV();
        }
    }

    public void showProgress(){
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
                Main.mainWindow.bStartSenseExp.setEnabled(true);
                System.out.println("\n");
            }
        });

        timer.start();
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

        System.out.print("\r" + progressBar);
    }
}
