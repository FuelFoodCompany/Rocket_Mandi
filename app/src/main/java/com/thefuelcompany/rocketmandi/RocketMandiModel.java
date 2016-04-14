package com.thefuelcompany.rocketmandi;

import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pradumanpraduman on 11/03/16.
 */
public class RocketMandiModel implements Serializable{

    // user name and mobile number will be same
    private String username;
    private String fullName;
    private List<Integer> vegetableQuantityList;
    private List<String> vegetableNameList;
    private List<Integer> vegetablePriceList;
    private List<String> vegetableRateList;
    private List<String> vegetablePriceRateList;
    private List<Integer> vegetableIdList;
    private List<Integer> vegetableIconList;
    private List<Integer> fruitQuantityList;
    private List<String> fruitNameList;
    private List<Integer> fruitPriceList;
    private List<String> fruitRateList;
    private List<String> fruitPriceRateList;
    private List<Integer> fruitIdList;
    private List<Integer> fruitIconList;
    private int deletedProductId;
    private HashMap<Integer , String> productNameMap;
    private HashMap<Integer , Integer> productQuantityMap;
    private HashMap<Integer , Integer> productPriceMap;
    private HashMap<Integer , String> productPriceRateMap;
    private HashMap<Integer , Integer> productTotalPriceMap;
    private List <Integer> productIdList;
    private List <Integer> shoppingTotalList;
    private List<Integer> totalBillList;


    private boolean listViewPopulated = false;
    private int i = 0;


    public void initializeLists(){








        productNameMap = new HashMap<>();
        productPriceMap = new HashMap<>();
        productPriceRateMap = new HashMap<>();
        productQuantityMap = new HashMap<>();
        productTotalPriceMap = new HashMap<>();
        productIdList = new ArrayList<Integer>();
        shoppingTotalList = new ArrayList<Integer>();
        totalBillList = new ArrayList<Integer>();

        setVegetableIconList();
        setVegetableNameList();
        setVegetableQuantityList();
        setVegetableIdList();
        setVegetablePriceList();
        setVegetableRateList();
        setVegetablePriceRateList();

        setFruitIdList();
        setFruitPriceList();
        setFruitRateList();
        setFruitIconList();
        setFruitNameList();
        setFruitPriceRateList();
        setFruitQuantityList();

    }

    /**
     *
     * @return List of Integers which will be used to set up the pictures of vegetables.
     * Rest of the methods  below will be used for similar operations.
     */


    private void setVegetableIconList(){
        vegetableIconList = new ArrayList<Integer>();
        vegetableIconList.add(R.drawable.contacts_50);
        vegetableIconList.add(R.drawable.plus_48);
        vegetableIconList.add(R.drawable.contacts_filled_50);
        vegetableIconList.add(R.drawable.plus_48);
    }

    private void setVegetableNameList(){
        vegetableNameList = new ArrayList<String>();
        vegetableNameList.add("Onion");
        vegetableNameList.add("Brinjal");
        vegetableNameList.add("Carrot");
        vegetableNameList.add("Capsicum");
    }

    public void setVegetablePriceList(){
        vegetablePriceList = new ArrayList<Integer>();
        vegetablePriceList.add(30);
        vegetablePriceList.add(40);
        vegetablePriceList.add(50);
        vegetablePriceList.add(60);
    }

    public void setVegetableRateList(){
        vegetableRateList = new ArrayList<String>();
        vegetableRateList.add("/kg");
        vegetableRateList.add("/500gm");
        vegetableRateList.add("/100gm");
        vegetableRateList.add("/kg");
    }

    private void setVegetablePriceRateList(){
        vegetablePriceRateList = new ArrayList<String>();
        for (int i=0; i<vegetableIdList.size(); i++){
            vegetablePriceRateList.add(vegetablePriceList.get(i)+vegetableRateList.get(i));
        }
    }



    private void setVegetableQuantityList(){
        vegetableQuantityList= new ArrayList<Integer>();
        vegetableQuantityList.add(0);
        vegetableQuantityList.add(0);
        vegetableQuantityList.add(0);
        vegetableQuantityList.add(0);
    }



    private void setVegetableIdList(){
        vegetableIdList = new ArrayList<Integer>();
        vegetableIdList.add(101);
        vegetableIdList.add(102);
        vegetableIdList.add(103);
        vegetableIdList.add(104);
    }

    private void setFruitIconList(){
        fruitIconList= new ArrayList<Integer>();
        fruitIconList.add(R.drawable.contacts_50);
        fruitIconList.add(R.drawable.plus_48);
        fruitIconList.add(R.drawable.contacts_filled_50);
        fruitIconList.add(R.drawable.plus_48);
    }



    private void setFruitNameList(){
        fruitNameList = new ArrayList<String>();
        fruitNameList.add("Apple");
        fruitNameList.add("Mango");
        fruitNameList.add("Banana");
        fruitNameList.add("Grapes");
    }

    public void setFruitPriceList(){
        fruitPriceList = new ArrayList<Integer>();
        fruitPriceList.add(30);
        fruitPriceList.add(40);
        fruitPriceList.add(50);
        fruitPriceList.add(60);
    }

    private void setFruitRateList(){
        fruitRateList = new ArrayList<String>();
        fruitRateList.add("/kg");
        fruitRateList.add("/500gm");
        fruitRateList.add("/100gm");
        fruitRateList.add("/kg");
    }



    private void setFruitPriceRateList(){
        fruitPriceRateList = new ArrayList<String>();
        for (int i=0; i<fruitIdList.size(); i++){
            fruitPriceRateList.add(fruitPriceList.get(i)+fruitRateList.get(i));
        }
    }


    private void setFruitQuantityList(){
        fruitQuantityList = new ArrayList<Integer>();
        fruitQuantityList.add(0);
        fruitQuantityList.add(0);
        fruitQuantityList.add(0);
        fruitQuantityList.add(0);
    }


    private void setFruitIdList(){
        fruitIdList = new ArrayList<Integer>();
        fruitIdList.add(202);
        fruitIdList.add(202);
        fruitIdList.add(203);
        fruitIdList.add(204);
    }

    /**
     * The method will multiply the individual price of the product with the
     * product quantity that is ordered.Then the total price of that product will be added
     * to the price map where it is stored with the key, which is the product id
     * @param productId
     * @param productPrice
     * @param productQuantity
     */

    private void setTotalOfIndividualProduct(int productId, int productPrice, int productQuantity){
        int totalPrice= productPrice*productQuantity;
        productTotalPriceMap.put(productId, totalPrice);
    }

    /**
     * The method will set the boolean value, which will be used to find out whether
     * list views in shopping cart are populated or not.
     * It will help at UI part what is required to display,
     * If there is no item in the list view then
     * value will be false here and text will be displayed saying , shopping cart is empty.
     * If is alrea
     * @param value
     */

    public void setListViewPopulated(boolean value){
        this.listViewPopulated = value;
    }
    /**
     *
     * @return Whether there are any products added to shopping cart or not.
     */
    public boolean productsInShoppingCart(){
        if(productNameMap == null){
            return false;
        }else {
            return true;
        }

    }

    /**
     * When add button is clicked on the interface, then the method will get the information
     * of that particular vegetable or fruit and store it in the product list that are ordered, with the
     * key which is the product id.
     * @param position
     * @param quantityNew
     */
       public void changeVegetableQuantity(int position, Integer quantityNew){
        int id = vegetableIdList.get(position);
        int price = vegetablePriceList.get(position);

           if(productIdList.contains(id)) {
               changeProductList(id , quantityNew , price);
           }else {
               String vegetableName = vegetableNameList.get(position);
               String vegetablePriceRate = vegetablePriceRateList.get(position);
               changeProductList(id, vegetableName, quantityNew, vegetablePriceRate, price);
           }


    }

    public void changeFruitQuantity(int position, int quantityNew){
        int id = fruitIdList.get(position);

        int price = fruitPriceList.get(position);

        if(productIdList.contains(id)) {
            changeProductList(id , quantityNew , price);
        }else {
            String fruitName = fruitNameList.get(position);
            String fruitPriceRate = fruitPriceRateList.get(position);
            changeProductList(id, fruitName, quantityNew, fruitPriceRate, price);
        }

    }

    /**
     * The two methods will change the product hash maps, which will be displayed at the
     * shopping cart of the application. It should not have any extra product other than
     * added by the customer to purchase.
     * @param productId
     * @param productName
     * @param quantityNew
     * @param productPriceRate
     * @param productPrice
     */

    private void changeProductList(int productId, String productName ,int quantityNew , String productPriceRate, int productPrice){

            productIdList.add(productId);
            productQuantityMap.put(productId, quantityNew);
            productNameMap.put(productId , productName);
            productPriceRateMap.put(productId , productPriceRate);
            setTotalOfIndividualProduct(productId, productPrice, quantityNew);
    }

    private void changeProductList(int productId, int quantityNew, int productPrice){
        productQuantityMap.put(productId , quantityNew);
        setTotalOfIndividualProduct(productId , productPrice , quantityNew);
    }



    public void deleteProductFromShoppingCart(int position){
        int id = productIdList.get(position);
        productQuantityMap.remove(id);
        productNameMap.remove(id);
        productPriceRateMap.remove(id);
        productTotalPriceMap.remove(id);
        productIdList.remove(position);
    }


    public int checkModelObject(){
        i=i+1;
        return i;
    }

    public List<String> getVegetableNameList(){

        return vegetableNameList;
    }

    public List<String> getVegetablePriceRateList(){

        return vegetablePriceRateList;
    }

    public List<Integer> getVegetableQuantityList(){


        return vegetableQuantityList;
    }
    public List<Integer> getVegetableIdList(){
        return vegetableIdList;
    }

    public List<Integer> getVegetableIconList(){
        return vegetableIconList;
    }

    public List<Integer> getFruitIconList(){
        return fruitIconList;
    }
    public List<String> getFruitNameList(){

        return fruitNameList;
    }

    public List<String> getFruitPriceRateList(){

        return fruitPriceRateList;
    }

    public List<Integer> getFruitQuantityList(){
        return fruitQuantityList;
    }

    public List<Integer> getFruitIdList(){
        return fruitIdList;
    }

    public List<String> getProductNameList(){
        return  new ArrayList<String>(productNameMap.values());
    }

    public List<String> getProductRateList(){
        return new ArrayList<String>(productPriceRateMap.values());
    }

    public List<Integer> getProductQuantityList(){
        return  new ArrayList<Integer>(productQuantityMap.values());
    }
    public List<Integer> getTotalList(){

        return new ArrayList<Integer>(productTotalPriceMap.values());
    }

    public boolean getListViewPopulated(){
        return listViewPopulated;
    }

    public int getPositionOfProduct(int positionOfDeletedProduct){

    /**    deletedProductId =  productIdList.get(positionOfDeletedProduct);
        int position;
        if(deletedProductId>100 && deletedProductId<201){
            position = vegetableIdList.indexOf(deletedProductId);
        }else {
            position = fruitIdList.indexOf(deletedProductId);
        }
        return position;*/
        // I will do this, just take care of activity and fragment
        return 1;
    }

    public String getCategory(){
        if(deletedProductId>101 && deletedProductId <200){
            return "vegetables";
        }else {
            return "fruits";
        }
    }
}
