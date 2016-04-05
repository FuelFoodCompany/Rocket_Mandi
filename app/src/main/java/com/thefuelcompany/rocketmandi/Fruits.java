package com.thefuelcompany.rocketmandi;

/**
 * Created by pradumanpraduman on 15/03/16.
 * Store data of fruits
 */
public class Fruits {


    private int fruitIcon;
    private String fruitName;
    private String fruitPrice;
    private Integer fruitQuantity;


    public Fruits (int fruitIcon , String fruitName, String fruitPrice, Integer fruitQuantity
            ){
        super();
        this.fruitIcon = fruitIcon;
        this.fruitName = fruitName;
        this.fruitPrice = fruitPrice;
        this.fruitQuantity = fruitQuantity;


    }
    public int getFruitIcon(){
        return fruitIcon;
    }
    public String getFruitName(){
        return fruitName;
    }
    public String getFruitPrice(){
        return fruitPrice;
    }
    public Integer getFruitQuantity(){
        return fruitQuantity;
    }


}
