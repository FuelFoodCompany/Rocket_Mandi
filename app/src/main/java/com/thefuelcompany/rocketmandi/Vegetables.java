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
    private Integer vegetableID;

    public Vegetables (int vegetableIcon , String vegetableName, String vegetablePrice, Integer vegetableQuantity , Integer vegetableID ){
        super();
        this.vegetableIcon = vegetableIcon;
        this.vegetableName = vegetableName;
        this.vegetablePrice = vegetablePrice;
        this.vegetableQuantity = vegetableQuantity;
        this.vegetableID = vegetableID;

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
    public Integer getVegetableID(){
        return vegetableID;
    }

}
