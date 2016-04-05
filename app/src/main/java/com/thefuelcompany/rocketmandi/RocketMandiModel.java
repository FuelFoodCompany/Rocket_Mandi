package com.thefuelcompany.rocketmandi;

import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pradumanpraduman on 11/03/16.
 */
public class RocketMandiModel implements Serializable{

    // user name and mobile number will be same
    private String username;
    private String fullName;
    private List<Integer> vegetableQuantityList;
    private List<Integer> fruitQuantityList;


    private int i = 0;

    /**
     *
     * @return List of Integers which will be used to set up the pictures of vegetables.
     * Rest of the methods  below will be used for similar operations.
     */
    public List<Integer> getVegetableIconList(){
        List<Integer> vegetableIconList = new ArrayList<Integer>();
        vegetableIconList.add(R.drawable.contacts_50);
        vegetableIconList.add(R.drawable.plus_48);
        return vegetableIconList;
    }

    public List<String> getVegetableNameList(){
        List<String> vegetableNameList = new ArrayList<String>();
        vegetableNameList.add("Onion");
        vegetableNameList.add("Brinjal");
        return vegetableNameList;
    }

    public List<String> getVegetablePriceRateList(){
        List<String> vegetablePriceRateList = new ArrayList<String>();
        vegetablePriceRateList.add("50");
        vegetablePriceRateList.add("100");
        return vegetablePriceRateList;
    }

    public List<Integer> getVegetableQuantityList(){
        vegetableQuantityList= new ArrayList<Integer>();
        vegetableQuantityList.add(0);
        vegetableQuantityList.add(0);
        return vegetableQuantityList;
    }

    public List<Integer> getVegetableIdList(){
        List<Integer> vegetableIdList = new ArrayList<Integer>();
        vegetableIdList.add(101);
        vegetableIdList.add(102);
        return vegetableIdList;
    }


    public List<Integer> getFruitIconList(){
        List<Integer> vegetableIconList = new ArrayList<Integer>();
        vegetableIconList.add(R.drawable.contacts_50);
        vegetableIconList.add(R.drawable.plus_48);
        return vegetableIconList;
    }

    public List<String> getFruitNameList(){
        List<String> vegetableNameList = new ArrayList<String>();
        vegetableNameList.add("Onion");
        vegetableNameList.add("Brinjal");
        return vegetableNameList;
    }

    public List<String> getFruitPriceRateList(){
        List<String> vegetablePriceRateList = new ArrayList<String>();
        vegetablePriceRateList.add("50");
        vegetablePriceRateList.add("100");
        return vegetablePriceRateList;
    }

    public List<Integer> getFruitQuantityList(){
        fruitQuantityList = new ArrayList<Integer>();
        fruitQuantityList.add(1);
        fruitQuantityList.add(2);
        return fruitQuantityList;
    }

    public List<Integer> getFruitIdList(){
        List<Integer> vegetableIdList = new ArrayList<Integer>();
        vegetableIdList.add(101);
        vegetableIdList.add(102);
        return vegetableIdList;
    }

       public void changeVegetableQuantity(int position, Integer quantityNew){

        vegetableQuantityList.set(position, quantityNew);
    }

    public void changeFruitQuantity(int position, Integer quantityNew){

        fruitQuantityList.set(position, quantityNew);
    }

    public List<String> getProductNameList(){
        List<String> list = new ArrayList<String>();
        list.add("Potato");
        return list;
    }

    public List<String> getProductRateList(){
        List<String> list = new ArrayList<String>();
        list.add("Potato");
        return list;
    }

    public List<Integer> getProductQuantityList(){
        List<Integer> list = new ArrayList<Integer>();
        list.add(2);
        return list;
    }

    public List<Integer> getTotalList(){
        List<Integer> list = new ArrayList<Integer>();
        list.add(100);
        return list;
    }

    public int checkModelObject(){
        i=i+1;
        return i;
    }

}
