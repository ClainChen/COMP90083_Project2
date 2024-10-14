package supconmodel.model.agents.dataRecorder;

import supconmodel.model.agents.Supplier;

import java.util.ArrayList;

public class SupplierDataRecorder {
    public ArrayList<Double> lastIncomeData;
    public ArrayList<Double> lastCostData;

    public SupplierDataRecorder() {
        lastIncomeData = new ArrayList<>();
        lastCostData = new ArrayList<>();
    }

    public void updateRecorder(Supplier supplier) {
        lastIncomeData.add(supplier.getLastIncome());
    }

    public void periodUpdateRecorder(Supplier supplier) {
        lastCostData.add(supplier.getLastCost());
    }

}
