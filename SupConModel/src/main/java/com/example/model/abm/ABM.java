package com.example.model.abm;

import com.example.model.Main;
import com.example.model.agents.Buyer;
import com.example.model.agents.BuyerManager;
import com.example.model.agents.Seller;
import com.example.model.agents.SellerManager;
import com.example.model.utils.TradesInADay;

import javax.swing.*;

public class ABM {
    public ConstantParameters parameters;
    public Supervisor supervisor;

    public BuyerManager buyerManager;
    public SellerManager sellerManager;

    public Timer timer;

    public int day;
    public ABM(){
        parameters = new ConstantParameters();
        supervisor = new Supervisor();
        day = 0;
        timer = new Timer(16, _ -> {
            go_once();
            Main.mainWindow.updateCharts(day);
            day += 1;
        });
    }


    public void setup(){
        buyerManager = new BuyerManager(parameters);
        sellerManager = new SellerManager(parameters);
        timer.stop();

        Main.mainWindow.lDay.setText("Day 0");
    }

    /**
     * Execute one iteration of the ABM
     */
    public void go_once(){
        checkEnd();

        for (Seller s : sellerManager.sellers){
            s.trade_history.add(new TradesInADay(day, s.price));
            s.potential_buyers = 0;
        }

        for (Buyer b : buyerManager.buyers){
            Seller s = sellerManager.getRandomSeller();
            trade(b,s);
//            System.out.println(b.expectation);
        }

        for (Seller s: sellerManager.sellers){
            if (s.potential_buyers != 0){
                if ((double) s.trade_history.getLast().buyerIDs.size() / s.potential_buyers > 0.5){
                    s.increasePrice();
                }else{
                    s.decreasePrice();
                }
            }
        }

//        System.out.println("Day " + day);
        updateSupervisor();
        Main.mainWindow.lDay.setText("Day " + day);
    }

    private void checkEnd() {
        if (buyerManager.getMaxBudget() < 5 || sellerManager.getMaxInventory() == 0 || buyerManager.getMaxDemand() == 0
        || sellerManager.getAvgPrice() < 1){
            timer.stop();
        }
    }

    public void trade(Buyer b, Seller s){
        if (b.demand == 0 || s.inventory == 0){
            return;
        }
        if (b.expectation >= s.price){
            b.budget -= s.price;
            s.income += s.price;
            b.demand -= 1;
            s.inventory -= 1;
            s.trade_history.getLast().buyerIDs.add(b.id);
            b.decreaseExpectation();
        }else{
            b.increaseExpectation();
        }
        s.potential_buyers += 1;
    }

    public void go(){
        if (timer.isRunning()){
            timer.stop();
        }else{
            timer.start();
        }
    }

    public void changeSpeed(int delay){
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
        supervisor.avgSpentHistory.add(buyerManager.getAvgSpent(parameters));
        supervisor.avgIncomeHistory.add(sellerManager.getAvgIncome());
        supervisor.totalInventoryHistory.add(sellerManager.getInventory());
        supervisor.totalBoughtHistory.add(sellerManager.getBought());
        supervisor.totalDemandHistory.add(buyerManager.getDemand());
    }

}
