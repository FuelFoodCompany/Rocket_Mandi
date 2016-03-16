package com.thefuelcompany.rocketmandi;

/**
 * Created by pradumanpraduman on 15/03/16.
 * Store data of fruits
 */
public class Fruits {

    private int fruitIcon;
    private String fruitName;
    private int fruitPrice;
    private int fruitQuantityAdded;
    private int fruitAddIcon;
    private int fruitId;

    public Fruits (int fruitIcon , String fruitName, int fruitPrice, int fruitQuantityAdded
            , int fruitAddIcon, int fruitId){
        super();
        this.fruitIcon = fruitIcon;
        this.fruitName = fruitName;
        this.fruitPrice = fruitPrice;
        this.fruitQuantityAdded = fruitQuantityAdded;
        this.fruitAddIcon = fruitAddIcon;
        this.fruitId = fruitId;

    }
    public int getfruitIcon(){
        return fruitIcon;
    }
    public String getfruitName(){
        return fruitName;
    }
    public int getfruitPrice(){
        return fruitPrice;
    }
    public int getfruitQuantityAdded(){
        return fruitQuantityAdded;
    }
    public int getfruitAddIcon(){
        return fruitAddIcon;
    }
    public int getVegetableId(){
        return fruitId;
    }

    public void setfruitQuantityAdded(int quantityAdded){
        this.fruitQuantityAdded = quantityAdded;
    }

}
