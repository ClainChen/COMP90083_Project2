package supconmodel.model.agents.dataRecorder;

import supconmodel.model.agents.Consumer;

import java.util.ArrayList;

public class ConsumerDataRecorder {
    public ArrayList<Double> spendData;
    public ArrayList<Double> depositData;
    public ArrayList<Double> basicDemandData;
    public ArrayList<Double> convenientDemandData;
    public ArrayList<Double> luxuryDemandData;

    public ConsumerDataRecorder() {
        spendData =  new ArrayList<>();
        depositData =  new ArrayList<>();
        basicDemandData =  new ArrayList<>();
        convenientDemandData =  new ArrayList<>();
        luxuryDemandData =  new ArrayList<>();
    }

    public void updateRecorder(Consumer c){
        if (!spendData.isEmpty()){
            spendData.add(c.spend);
            depositData.add(c.deposit);
            basicDemandData.add(c.basicDemand);
            convenientDemandData.add(c.convenientDemand);
            luxuryDemandData.add(c.luxuryDemand);
        }
    }
}
