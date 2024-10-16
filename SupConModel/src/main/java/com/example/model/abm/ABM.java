package com.example.model.abm;

import com.example.model.Main;
import com.example.model.agents.Buyer;
import com.example.model.agents.BuyerManager;
import com.example.model.agents.Seller;
import com.example.model.agents.SellerManager;
import com.example.model.utils.Parameters;
import com.example.model.utils.TradesInADay;

import javax.swing.*;

public class ABM {
    public Supervisor supervisor;

    public BuyerManager buyerManager;
    public SellerManager sellerManager;

    public Timer timer;

    public int judgeDeal = 0;
    public int day;

    public final Object lock = new Object();
    public boolean isRunning = false;

    public ABM(){
        supervisor = new Supervisor();
        day = 0;
        int delay = getSpeed();
        timer = new Timer(delay, _ -> go_once());
    }


    public void setup(){
        buyerManager = new BuyerManager();
        sellerManager = new SellerManager();
        timer.stop();

        Main.mainWindow.lDay.setText("Day 0");
        Main.mainWindow.lASP.setText(String.format("%.2f", 0.0));
        Main.mainWindow.lpSMU.setText(String.format("%.2f", 0.0));
        Main.mainWindow.lNumSoldOut.setText(String.format("%d", 0));
        Main.mainWindow.lNumSatisfied.setText(String.format("%d", 0));
        Main.mainWindow.lJudgeDeals.setText(String.format("%d", 0));
    }

    /**
     * Execute one iteration of the ABM
     */
    public void go_once(){
        experimentGoOnce();

        Main.mainWindow.lDay.setText("Day " + day);
        Main.mainWindow.lASP.setText(String.format("%.2f", sellerManager.getAvgTradePrice()));
        Main.mainWindow.lpSMU.setText(String.format("%.2f", buyerManager.getPercentSpent() * 100));
        Main.mainWindow.lNumSoldOut.setText(String.format("%d", sellerManager.getNumSoldOut()));
        Main.mainWindow.lNumSatisfied.setText(String.format("%d", buyerManager.getNumSatisfied()));
        Main.mainWindow.lJudgeDeals.setText(String.format("%d", judgeDeal));

        Main.mainWindow.updateCharts(day);
    }

    public void experimentGoOnce(){
        checkEnd();

        for (Seller s : sellerManager.sellers){
            s.trade_history.add(new TradesInADay(day));
            s.potential_buyers = 0;
        }

        for (Buyer b : buyerManager.buyers){
            Seller s = sellerManager.getRandomSeller();
            if (s == null){
                break;
            }
            trade(b,s);
        }

        for (Seller s: sellerManager.sellers){
            if (s.potential_buyers != 0){
                if ((double) s.trade_history.getLast().dealPrices.size() / s.potential_buyers > 0.5){
                    s.increasePrice();
                }else{
                    s.decreasePrice();
                }
            }
        }

        updateSupervisor();
        day += 1;
    }

    public void experimentGo(){
        isRunning = true;
        while (isRunning){
            experimentGoOnce();
        }
//        System.out.println("Experiment finished");
    }

    public void experimentSetup(){
        buyerManager = new BuyerManager();
        sellerManager = new SellerManager();
        timer.stop();
    }

    private void checkEnd() {
        if (buyerManager.getMaxBudget() == 0 || sellerManager.getMaxInventory() == 0 || buyerManager.getMaxDemand() == 0
        || sellerManager.getAvgPrice() == 0){
            timer.stop();
            synchronized (lock){
                lock.notify();
            }
            isRunning = false;
            supervisor.day = day;
        }
        if (day >= 500){
            timer.stop();
            synchronized (lock){
                lock.notify();
            }
            isRunning = false;
            supervisor.day = day;
        }

    }

    public void trade(Buyer b, Seller s){
        if (b.demand == 0 || s.inventory == 0){
            return;
        }
        if (! Parameters.isJudgement){
            if (b.expectation >= s.price){
                deal(b, s, s.price);
            }else{
                b.increaseExpectation();
            }
        }else{
            if (b.expectation >= s.price){
                deal(b, s, s.price);
            }else{
                double judgedExp = b.expectation;
                double judgedPrice = s.price;
                boolean dealSucc = false;
                for (int i = 0; i < 3; i++){
                    judgedExp += Math.abs(Math.random() * 3 - 1);
                    judgedPrice -= Math.abs(Math.random() * 3 - 1);
                    if (judgedExp >= judgedPrice){
                        dealSucc = true;
                        judgeDeal += 1;
                        break;
                    }
                }
                if (dealSucc){
                    deal(b, s, judgedPrice);
                }else{
                    b.increaseExpectation();
                }
            }
        }
        s.potential_buyers += 1;
    }

    public void deal(Buyer b, Seller s, double price){
        b.budget -= price;
        s.income += price;
        b.demand -= 1;
        s.inventory -= 1;
        s.trade_history.getLast().dealPrices.add(price);
        b.decreaseExpectation();
    }

    public void go(){
        if (timer.isRunning()){
            timer.stop();
        }else{
            timer.start();
        }
    }

    private int getSpeed(){
        assert Main.speed >= 1 && Main.speed <= 5;
        return switch (Main.speed) {
            case 1 -> 1000;
            case 2 -> 125;
            case 3 -> 31;
            case 4 -> 16;
            case 5 -> 8;
            default -> throw new IllegalStateException("Unexpected value: " + Main.speed);
        };
    }

    public void quickSpeed(){
        timer.setDelay(1);
    }

    public void stop(){
        timer.stop();
    }

    public void updateSpeed(){
        int delay = getSpeed();
        timer.setDelay(delay);
    }

    public void updateSupervisor(){
        supervisor.maxPriceHistory.add(sellerManager.getMaxPrice());
        supervisor.minPriceHistory.add(sellerManager.getMinPrice());
        supervisor.avgPriceHistory.add(sellerManager.getAvgPrice());
        supervisor.maxExpectationHistory.add(buyerManager.getMaxExpectation());
        supervisor.minExpectationHistory.add(buyerManager.getMinExpectation());
        supervisor.avgExpectationHistory.add(buyerManager.getAvgExpectation());
        supervisor.avgTradePriceHistory.add(sellerManager.getAvgTradePrice());
        supervisor.avgSpentHistory.add(buyerManager.getAvgSpent());
        supervisor.avgIncomeHistory.add(sellerManager.getAvgIncome());
        supervisor.totalInventoryHistory.add(sellerManager.getInventory());
        supervisor.totalBoughtHistory.add(sellerManager.getBought());
        supervisor.totalDemandHistory.add(buyerManager.getDemand());
        supervisor.maxBudgetHistory.add(buyerManager.getMaxBudget());
        supervisor.minBudgetHistory.add(buyerManager.getMinBudget());
        supervisor.avgBudgetHistory.add(buyerManager.getAvgBudget());
        supervisor.percentSpentHistory.add(buyerManager.getPercentSpent() * 100);
        supervisor.numSoldOutHistory.add(sellerManager.getNumSoldOut());
        supervisor.numSatisfiedHistory.add(buyerManager.getNumSatisfied());
        supervisor.judgeDealHistory.add(judgeDeal);
    }

    public void awaitCompletion() throws InterruptedException {
        synchronized (lock){
            while (isRunning){
                lock.wait();
            }
        }
    }

}
