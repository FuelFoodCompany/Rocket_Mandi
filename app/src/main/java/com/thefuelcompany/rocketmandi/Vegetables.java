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
    private int vegetableId;

    public Vegetables (int vegetableIcon , String vegetableName, String vegetablePrice, Integer vegetableQuantity
    , int vegetableId){
        super();
        this.vegetableIcon = vegetableIcon;
        this.vegetableName = vegetableName;
        this.vegetablePrice = vegetablePrice;
        this.vegetableQuantity = vegetableQuantity;
        this.vegetableId = vegetableId;

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
    public int getVegetableId(){
        return vegetableId;
    }

}
