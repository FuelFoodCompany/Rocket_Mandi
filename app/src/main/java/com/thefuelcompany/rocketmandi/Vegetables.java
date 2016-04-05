package com.thefuelcompany.rocketmandi;

/**
 * Created by pradumanpraduman on 15/03/16.
 * The class will store information of vegetables
 */
public class Vegetables {

    private int vegetableIcon;
    private String vegetableName;
    private String vegetablePrice;
    private Integer vegetableQuantity;

    public Vegetables (int vegetableIcon , String vegetableName, String vegetablePrice, Integer vegetableQuantity ){
        super();
        this.vegetableIcon = vegetableIcon;
        this.vegetableName = vegetableName;
        this.vegetablePrice = vegetablePrice;
        this.vegetableQuantity = vegetableQuantity;

    }
    public int getVegetableIcon(){
        return vegetableIcon;
    }
    public String getVegetableName(){
        return vegetableName;
    }
    public String getVegetablePrice(){
        return vegetablePrice;
    }
    public Integer getVegetableQuantity(){
        return vegetableQuantity;
    }

}
