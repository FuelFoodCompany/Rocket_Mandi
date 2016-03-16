package com.thefuelcompany.rocketmandi;

/**
 * Created by pradumanpraduman on 15/03/16.
 * The class will store information of vegetables
 */
public class Vegetables {

    private int vegetableIcon;
    private String vegetableName;
    private int vegetablePrice;
    private int vegetableQuantityAdded;
    private String vegetableAddIcon;
    private int vegetableId;

    public Vegetables (int vegetableIcon , String vegetableName, int vegetablePrice, int vegetableQuantityAdded
    , String vegetableAddIcon, int vegetableId){
        super();
        this.vegetableIcon = vegetableIcon;
        this.vegetableName = vegetableName;
        this.vegetablePrice = vegetablePrice;
        this.vegetableQuantityAdded = vegetableQuantityAdded;
        this.vegetableAddIcon = vegetableAddIcon;
        this.vegetableId = vegetableId;

    }
    public int getVegetableIcon(){
        return vegetableIcon;
    }
    public String getVegetableName(){
        return vegetableName;
    }
    public int getVegetablePrice(){
        return vegetablePrice;
    }
    public int getVegetableQuantityAdded(){
        return vegetableQuantityAdded;
    }
    public String getVegetableAddIcon(){
        return vegetableAddIcon;
    }
    public int getVegetableId(){
        return vegetableId;
    }

    public void setVegetableQuantityAdded(int quantityAdded){
        this.vegetableQuantityAdded = quantityAdded;
    }

}
