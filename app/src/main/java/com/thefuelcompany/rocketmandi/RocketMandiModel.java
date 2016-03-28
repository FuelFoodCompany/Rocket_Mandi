package com.thefuelcompany.rocketmandi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pradumanpraduman on 11/03/16.
 */
public class RocketMandiModel {

    private String username;
    private String fullName;

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

    public List<Integer> getFruitIdList(){
        List<Integer> vegetableIdList = new ArrayList<Integer>();
        vegetableIdList.add(101);
        vegetableIdList.add(102);
        return vegetableIdList;
    }



}
