package supconmodel.model.agents;

import java.util.Dictionary;
import java.util.Hashtable;

public class Good {
    private int id;
    private String name;
    private GoodType type;

    private float productProfit;
    private int inventory;
    private int production;

    private float cost;
    private float price;
    private float expectProfitability;

    private int goodVotes;
    private int badVotes;

    private float popularRate;
    private float demandRate;
    private float competitiveRate;

    // Satisfaction Rates
    private float basic;
    private float convenience;
    private float luxury;

    public Good(int id, String name, GoodType type, float cost, float expectProfitability, float basic, float convenience, float luxury) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.productProfit = basic;
        this.inventory = 0;
        this.production = 0;
        this.cost = cost;
        this.price = 0;
        this.expectProfitability = expectProfitability;
        this.goodVotes = 0;
        this.badVotes = 0;
        this.popularRate = 0;
        this.demandRate = 0;
        this.competitiveRate = 0;
        this.basic = basic;
        this.convenience = convenience;
        this.luxury = luxury;
    }
}
