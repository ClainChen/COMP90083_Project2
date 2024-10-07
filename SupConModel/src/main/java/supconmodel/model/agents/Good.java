package supconmodel.model.agents;

import supconmodel.utils.Enums.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Good {
    public int id;
    public String name;
    public GoodType type;

    public double cost;
    public double expectProfitability;

    // Satisfaction Rates
    public double basic;
    public double convenience;
    public double luxury;

    public ArrayList<Integer> ownedBy = new ArrayList<>();

    public Good(int id, String name, GoodType type, double cost, double expectProfitability, double basic, double convenience, double luxury) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.cost = cost;
        this.expectProfitability = expectProfitability;
        this.basic = basic;
        this.convenience = convenience;
        this.luxury = luxury;
    }

    public Good(Good good){
        this.id = good.id;
        this.name = good.name;
        this.type = good.type;
        this.cost = good.cost;
        this.expectProfitability = good.expectProfitability;
        this.basic = good.basic;
        this.convenience = good.convenience;
        this.luxury = good.luxury;
    }

    public Good(){}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(",").append(name).append(",").append(type.ordinal()).append(",");
        BigDecimal roundedValue = new BigDecimal(cost).setScale(5, RoundingMode.HALF_UP);
        sb.append(roundedValue).append(",");
        roundedValue = new BigDecimal(expectProfitability).setScale(5, RoundingMode.HALF_UP);
        sb.append(roundedValue).append(",");
        roundedValue = new BigDecimal(basic).setScale(3, RoundingMode.HALF_UP);
        sb.append(roundedValue).append(",");
        roundedValue = new BigDecimal(convenience).setScale(3, RoundingMode.HALF_UP);
        sb.append(roundedValue).append(",");
        roundedValue = new BigDecimal(luxury).setScale(3, RoundingMode.HALF_UP);
        sb.append(roundedValue);
        return sb.toString();
    }

}
