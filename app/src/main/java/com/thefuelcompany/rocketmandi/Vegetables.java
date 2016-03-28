package com.thefuelcompany.rocketmandi;

/**
 * Created by pradumanpraduman on 15/03/16.
 * The class will store information of vegetables
 */
public class Vegetables {

    private int vegetableIcon;
    private String vegetableName;
    private String vegetablePrice;
    private int vegetableId;

    public Vegetables (int vegetableIcon , String vegetableName, String vegetablePrice
    , int vegetableId){
        super();
        this.vegetableIcon = vegetableIcon;
        this.vegetableName = vegetableName;
        this.vegetablePrice = vegetablePrice;
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
    public int getVegetableId(){
        return vegetableId;
    }

}
