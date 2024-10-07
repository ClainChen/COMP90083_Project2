package supconmodel.model.simulation;

import supconmodel.SupConModelMain;
import supconmodel.model.agents.DynamicGood;
import supconmodel.model.agents.Supplier;
import supconmodel.model.agents.controller.ConsumerController;
import supconmodel.model.agents.controller.GoodController;
import supconmodel.model.agents.controller.SupplierController;
import supconmodel.model.agents.dataRecorder.OverallDataRecorder;
import supconmodel.model.gui.window.ConsumerSupervisorWindow;
import supconmodel.model.gui.window.GoodSupervisorWindow;
import supconmodel.model.gui.window.SupplierSupervisorWindow;
import supconmodel.utils.Parameters;
import supconmodel.utils.Tools;

import javax.swing.*;

public class Simulator {
    public ConsumerController consumerController;
    public SupplierController supplierController;
    public GoodController goodController;
    public OverallDataRecorder overallDataRecorder;

    public final Timer timer;
    public int day;

    public Simulator(){
        consumerController = new ConsumerController();
        supplierController = new SupplierController();
        goodController = new GoodController();
        overallDataRecorder = new OverallDataRecorder();
        timer = new Timer(Parameters.simulate_speed, _ -> update());
        day = 1;
    }

    public void setup(){
        try{
            day = 0;
            SupConModelMain.windowController.mainWindow.lDay.setText("Day: " + day);
            overallDataRecorder = new OverallDataRecorder();

            goodController.init();
            consumerController.init();
            supplierController.init(goodController, consumerController);
            Tools.feedback("Setup Success", 1);

            // update view in supervisor
            for (SupplierSupervisorWindow window: SupConModelMain.windowController.supplierSupervisorWindow){
                if (window.isVisible()){
                    window.supervisingID = 0;
                    window.tCurrentSupplierID.setValue(0);
                    window.updateGoodSpinner();

                    window.updateSupplierSupervise();
                    window.updateGoodSupervise();
                }
            }
            for (ConsumerSupervisorWindow window: SupConModelMain.windowController.consumerSupervisorWindow){
                if (window.isVisible()){
                    window.supervisingID = 0;
                    window.tCurrentConsumerID.setValue(0);
                    window.updateConsumerSupervise();
                }
            }
            for (GoodSupervisorWindow window: SupConModelMain.windowController.goodSupervisorWindow){
                if (window.isVisible()){
                    window.updateGoodSpinner();
                    window.updateGoodSupervise();
                }
            }
        }catch (Exception e){
            Tools.feedback("Unexpected Error occured during setup" + e.getMessage(), 3);
        }
    }

    public void go(){
        if (timer.isRunning()){
            timer.stop();
        }else{
            timer.start();
        }
    }

    public void step(){
        update();
    }

    private void update(){
        boolean isPeriod = day % 7 == 0;
        consumerController.updateRecorder();
        supplierController.updateRecorder(isPeriod);
        overallDataRecorder.updateRecord(goodController, consumerController, supplierController);
        if (isPeriod){
            consumerController.periodUpdateConsumer();
            supplierController.periodUpdateSupplier(consumerController, goodController);
        }
        day += 1;
        consumerController.updateConsumer(goodController, supplierController);
        supplierController.updateSupplier();

        // update view in supervisor
        for (SupplierSupervisorWindow window: SupConModelMain.windowController.supplierSupervisorWindow){
            if (window.isVisible()){
                window.updateSupplierSupervise();
                window.updateGoodSupervise();
            }
        }
        for (ConsumerSupervisorWindow window: SupConModelMain.windowController.consumerSupervisorWindow){
            if (window.isVisible()){
                window.updateConsumerSupervise();
            }
        }
        for (GoodSupervisorWindow window: SupConModelMain.windowController.goodSupervisorWindow){
            if (window.isVisible()){
                window.updateGoodSupervise();
            }
        }
        SupConModelMain.windowController.mainWindow.updateOverallSupervise(this);
        SupConModelMain.windowController.mainWindow.lDay.setText("Day: " + day);
    }

    public void setTimerDelay(int delay){
        timer.setDelay(delay);
    }

    public double getTotalIncome(){
        double totalIncome = 0;
        for (Supplier supplier: supplierController.suppliers){
            for (DynamicGood good: supplier.goodList){
                totalIncome += good.totalIncome;
            }
        }
        return totalIncome;
    }

    public double getLast7TotalIncome(){
        double totalIncome = 0;
        for (Supplier supplier: supplierController.suppliers){
            for (DynamicGood good: supplier.goodList){
                totalIncome += good.getLast7Income();
            }
        }
        return totalIncome;
    }

    public int getTotalSoldNum(){
        int totalSoldNum = 0;
        for (Supplier supplier: supplierController.suppliers){
            for (DynamicGood good: supplier.goodList){
                totalSoldNum += good.totalSoldNum;
            }
        }
        return totalSoldNum;
    }

    public int getLast7TotalSoldNum(){
        int totalSoldNum = 0;
        for (Supplier supplier: supplierController.suppliers){
            for (DynamicGood good: supplier.goodList){
                totalSoldNum += good.getLast7SoldNum();
            }
        }
        return totalSoldNum;
    }

    public double getTotalCost(){
        return supplierController.getTotalCost();
    }

    public double getLastCost(){
        return supplierController.getLastCost();
    }
}
