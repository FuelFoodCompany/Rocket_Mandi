package com.thefuelcompany.rocketmandi;

import android.content.Context;
import android.widget.TextView;
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

    private String emailEncoded;
    private String fullName;
    private String phone;
    private String newFullName;
    private String newPhone;
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
    private List<String> productNameList;
    private List<Integer> productQuantityList;
    private List<Integer> productPriceList;
    private List<String> productPriceRateList;
    private List<Integer> productTotalPriceList;
    private List <Integer> productIdList;
    private List <Integer> shoppingTotalList;
    private List<Integer> totalBillList;
    private String [] pickUpLocationArray;
    private String [] pickUpAreaArray;
    private HashMap<String , List<String>> pickUpLocationHashMap;
    private int previousPositionOfDeliveryLocationSelected;
    private int previousPositionOfPickUpAreaSelected;
    private String pickUpAreaSelected;
    private String pickUpLocationSelected;


    private boolean listViewPopulated = false;
    private int i = 0;

    public RocketMandiModel(String emailEncoded){
        this.emailEncoded = emailEncoded;
    }

    public void initializeLists(){

        productNameList = new ArrayList<String>();
        productPriceList = new ArrayList<Integer>();
        productPriceRateList = new ArrayList<String>();
        productQuantityList = new ArrayList<Integer>();
        productTotalPriceList = new ArrayList<Integer>();
        productIdList = new ArrayList<Integer>();
        shoppingTotalList = new ArrayList<Integer>();
        totalBillList = new ArrayList<Integer>();
        pickUpLocationHashMap = new HashMap<>();
        pickUpAreaSelected = "Rk Puram Sec 10";

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

        setPhone();
        setFullName();
        setPickUpAreaArray();
        setPickUpLocationHashMap();
        setDeliveryLocationArray();
        setPreviousPositionPositionOfPickUpAreaSelected();
        setPreviousPositionOfDeliveryLocationSelected();

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
        fruitIdList.add(201);
        fruitIdList.add(202);
        fruitIdList.add(203);
        fruitIdList.add(204);
    }

    private void setPhone(){
        this.phone = "8447788214";
    }

    private void setFullName(){
        this.fullName = "Lucky Solanki";
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
        return productNameList;
    }

    public List<String> getProductRateList(){
        return productPriceRateList;
    }

    public List<Integer> getProductQuantityList(){
        return productQuantityList;
    }
    public List<Integer> getTotalList(){
        return productTotalPriceList;
    }

    public boolean getListViewPopulated(){
        return listViewPopulated;
    }

    /**
     * This will be called from Hoem Activity when delete button on list view is clicked.
     * This method will take position of product in shopping cart as a variable.
     * Then compute and return the position for that product in vegetable or fruit list, so that
     * it can be deleted from lists of vegetable and fruit fragments.
     * @param position
     * @return
     */
    public int getPositionOfDeletedProduct(int position){

        deletedProductId =  productIdList.get(position);
        int positionOfDeletedProduct;
        if(deletedProductId>100 && deletedProductId<201){
            positionOfDeletedProduct = vegetableIdList.indexOf(deletedProductId);
        }else {
            positionOfDeletedProduct = fruitIdList.indexOf(deletedProductId);
        }
        return positionOfDeletedProduct;
    }

    public String getCategory(){
        if(deletedProductId>100 && deletedProductId <201){
            return "vegetables";
        }else {
            return "fruits";
        }
    }

    public int getDeletedProductId(){
        return deletedProductId;
    }


    /**
     * The method will multiply the individual price of the product with the
     * product quantity that is ordered.Then the total price of that product will be added
     * to the price map where it is stored with the key, which is the product id
     * @param productPrice
     * @param productQuantity
     */

    private void setTotalOfIndividualProduct(int productPrice, int productQuantity , int positionInProductList){
        int totalPrice= productPrice*productQuantity;
        productTotalPriceList.set(positionInProductList, totalPrice);
    }


    private void setTotalOfIndividualProduct( int productPrice, int productQuantity ){
        int totalPrice= productPrice*productQuantity;
        productTotalPriceList.add(totalPrice);
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
        if(productNameList.isEmpty()){
            return false;
        }else {
            return true;
        }

    }

    /**
     * When add button is clicked on the interface, then the method will get the information
     * of that particular vegetable or fruit and store it in the product list that are ordered, with the
     * key which is the product id.
     * This method is called from Fragment Vegetable, when add or subtract button is clicked.
     * @param position
     * @param quantityNew
     */
    public void changeVegetableQuantity(int position, Integer quantityNew){
        int id = vegetableIdList.get(position);
        int price = vegetablePriceList.get(position);
        int positionInProductList;

        if(productIdList.contains(id)) {
            positionInProductList = productIdList.indexOf(id);
            changeProductList(quantityNew , price , positionInProductList);

        }else {
            String vegetableName = vegetableNameList.get(position);
            String vegetablePriceRate = vegetablePriceRateList.get(position);
            changeProductList(id, vegetableName, quantityNew, vegetablePriceRate, price);
        }


    }

    public void changeFruitQuantity(int position, int quantityNew){
        int id = fruitIdList.get(position);
        int price = fruitPriceList.get(position);
        int positionInProductList;

        if(productIdList.contains(id)) {
            positionInProductList = productIdList.indexOf(id);
            changeProductList(quantityNew , price , positionInProductList);

        }else {
            String vegetableName = fruitNameList.get(position);
            String vegetablePriceRate = fruitPriceRateList.get(position);
            changeProductList(id, vegetableName, quantityNew, vegetablePriceRate, price);
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
        productQuantityList.add(quantityNew);
        productNameList.add(productName);
        productPriceRateList.add(productPriceRate);
        setTotalOfIndividualProduct(productPrice, quantityNew);
    }

    private void changeProductList( int quantityNew, int productPrice , int positionInProductList){
        productQuantityList.set(positionInProductList, quantityNew);
        setTotalOfIndividualProduct(productPrice, quantityNew, positionInProductList);
    }


    /**
     * This is called from HomeActivity class, when delete button in List Views at Shopping cart screen
     * is clicked.
     * The method will delete item at the particular position which is passed.
     * It will be used to delete product from database, will not affect the UI at Home page.
     * @param position
     */
    public void deleteProductFromShoppingCart(int position ){

        productNameList.remove(position);
        productIdList.remove(position);
        productQuantityList.remove(position);
        productPriceRateList.remove(position);
        productTotalPriceList.remove(position);
    }

    /**
     ****** From here all the methods used by My account will be written***********
     */

    public String getFullName(){
        return fullName;
    }

    public String getPhoneNumber(){
        return phone;
    }

    public void setNewFullName(String newFullName){
        // update in the database first
        fullName = newFullName;

    }
    public void setNewPhoneNumber(String newPhone){
        // first update in the database
        phone = newPhone;
    }

    /**
     * The method is called from MyAccount class to
     * check if the new number entered by user is
     * same as old one or not.
     * @param newPhone
     * @return
     */
    public boolean matchNewPhoneWithOld(String newPhone){
        if(phone.equalsIgnoreCase(newPhone)){
            return true;
        }else {
            return false;
        }
    }

    public String setNewEmail(String email , String newEmail , String password, String emailDecoded){
        return "";
    }

    public String setNewPassword(String email , String oldPassword ,String newPassword){
        return "";
    }


    public String getEmail(){
        return "gajal@gmail.com";
    }

    private void setPickUpAreaArray(){
        pickUpAreaArray = new String[]{"Select pick up area", "Rohini Sec 8" , "Rk Puram Sec 10" , "South Ex"};
    }

    private void setPickUpLocationHashMap(){
        List<String> emptyList = new ArrayList<String>();
        List<String> l1 = new ArrayList<String>();
        l1.add("Select pick up shop");l1.add("Shop no 2");l1.add("Shop no 3 Chiki ckik");l1.add("Shop no 6 aggarwal sweets");
        List<String> l2 = new ArrayList<String>();
        l2.add("Select pick up shop");l2.add("Shop no 12 Ding dong shop");l2.add("Shop no 13 ");l2.add("Shop no 16 banbaar sweets");
        List<String> l3 = new ArrayList<String>();
        l3.add("Select pick up shop");l3.add("Shop no 22 Ding dong shop");l3.add("Shop no 23 ");l3.add("Shop no 26 banbaar sweets");
        pickUpLocationHashMap.put("Select pick up area" , emptyList);
        pickUpLocationHashMap.put("Rohini Sec 8" , l1);
        pickUpLocationHashMap.put("Rk Puram Sec 10" , l2);
        pickUpLocationHashMap.put("South Ex" ,l3 );
    }

    public void setDeliveryLocationArray(){
        if(previousPositionOfPickUpAreaSelected ==0){
            pickUpLocationArray = new String[]{};
        }else {
            List<String> list = pickUpLocationHashMap.get(pickUpAreaSelected);
            pickUpLocationArray = list.toArray(new String[list.size()]);
        }
    }

    public void setPickUpAreaSelected(String areaSelected){
        pickUpAreaSelected = areaSelected;
    }

    private void setPreviousPositionOfDeliveryLocationSelected(){
        // get from database
        previousPositionOfDeliveryLocationSelected = 0;
    }

    private void setPreviousPositionPositionOfPickUpAreaSelected(){
        // get from database
        previousPositionOfPickUpAreaSelected = 0;
    }

    public void setNewPickUpLocation(String locationSelected){
        pickUpLocationSelected = locationSelected;
    }

    public String[] getDeliveryLocationArray(){
        return pickUpLocationArray;
    }

    public String[] getPickUpAreaArray(){
        return pickUpAreaArray;
    }

    public int getPreviousPositionOfDeliveryLocationSelected(){
        return previousPositionOfDeliveryLocationSelected;
    }

    public int getPreviousPositionOfPickUpAreaSelected(){
        return previousPositionOfPickUpAreaSelected;
    }

    public void setNewPositionOfPickUpAreaSelected(int position){
        previousPositionOfPickUpAreaSelected = position;
    }

    public void setNewPositionOfDeliveryLocation(int position){
        // update in database
        previousPositionOfDeliveryLocationSelected = position;
    }

    public List<String> getPaymentStatusOfOrdersList() {
        List<String> paymentStatusOrdersList = new ArrayList<String>();
        paymentStatusOrdersList.add("Done");
        paymentStatusOrdersList.add("Done");
        paymentStatusOrdersList.add("Pending");
        paymentStatusOrdersList.add("Done");
        return paymentStatusOrdersList;
    }

    public List<String> getDeliveryOfOrdersList() {
        List<String> deliveryStatusOfOrdersList = new ArrayList<String>();
        deliveryStatusOfOrdersList.add("Done");
        deliveryStatusOfOrdersList.add("Done");
        deliveryStatusOfOrdersList.add("Done");
        deliveryStatusOfOrdersList.add("Done");
        return deliveryStatusOfOrdersList;
    }

    public List<String> getTotalOfOrdersList() {
        List<String> totalOfOrdersList = new ArrayList<String>();
        totalOfOrdersList.add("100");
        totalOfOrdersList.add("200");
        totalOfOrdersList.add("150");
        totalOfOrdersList.add("120");
        return totalOfOrdersList;
    }

    public List<String> getDateOfOrdersList() {
        List<String> dateOfOrdersList = new ArrayList<String>();
        dateOfOrdersList.add("28/3/2015");
        dateOfOrdersList.add("29/3/2015");
        dateOfOrdersList.add("30/4/2016");
        dateOfOrdersList.add("14/4/2016");
        return dateOfOrdersList;
    }
}
