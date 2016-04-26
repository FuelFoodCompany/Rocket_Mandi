package com.thefuelcompany.rocketmandi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends  FragmentActivity {

    RocketMandiModel modelObject = new RocketMandiModel();
    ViewPager viewPager = null;
    ViewFlipper homeActivityViewFlipper;
    ImageView homeImageView;
    ImageView shoppingCartImageView;
    ImageView accountImageView;
    ListView  listView ;
    Fragment fragmentFruits;
    Fragment fragmentVegetables;
    private TextView infoTextViewInShoppingCart;
    private TextView checkOutTextViewInShoppingCart;
    public List<OrderItemDetails> ordersList = new ArrayList<OrderItemDetails>();
    ShoppingCartAdapter adp;
    private MyAccount myAccount;
    View v;

    private TextView editInMyAccount;
    private EditText nameTextViewInAccount;;
    private EditText phoneTextViewInAccount;
    private TextView deliveryLocationTextViewInAccount;
    private Spinner deliveryLocationSpinnerInAccount;
    private TextView myOrdersTextViewInAccount;
    private TextView myOrderArrowInAccount;
    private TextView logOutTextViewInAccount;
    //private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // check if someone is logged in or not

        setContentView(R.layout.activity_home);
        modelObject.initializeLists();
        setFlipper();
        setHomeImageView();
        setShoppingCartImageView();
        setAccountImageView();
        setTextViews();
        listView = (ListView) findViewById(R.id.products_orders_list_view_at_shopping_cart);

            viewPager = (ViewPager) findViewById(R.id.pager);
            FragmentManager fragmentManager = getSupportFragmentManager();
            viewPager.setAdapter(new MyAdapter(fragmentManager));

    }

    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return the Fragment associated with a specified position.
         *
         * @param position
         */
        @Override
        public Fragment getItem(int position) {

            if(position == 0){

                fragmentVegetables = FragmentVegetables.getInstance();
                Bundle args = new Bundle();
                args.putSerializable("key", modelObject);
                fragmentVegetables.setArguments(args);
                return fragmentVegetables;
            }
            if(position==1){

                fragmentFruits = FragmentFruits.getInstance();
                Bundle args = new Bundle();
                args.putSerializable("key", modelObject);
                fragmentFruits.setArguments(args);
                return fragmentFruits;
            }
            return null;

        }

        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return 2;
        }

        public CharSequence getPageTitle(int position){
            String title = new String();
            if(position==0){
                return "सब्जियां";
            }
            if(position==1){
                return "फल";
            }
            return null;
        }
    }

    private void setFlipper(){
        homeActivityViewFlipper = (ViewFlipper) findViewById(R.id.home_screen_view_flipper);
    }

    private void setHomeImageView(){
        homeImageView = (ImageView) findViewById(R.id.toolbar_home_image);
        homeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                homeActivityViewFlipper.setDisplayedChild(0);

            }
        });
    }

    private void setShoppingCartImageView(){
        shoppingCartImageView = (ImageView) findViewById(R.id.toolbar_shopping_cart_image);
        shoppingCartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                homeActivityViewFlipper.setDisplayedChild(1);
                if(modelObject.productsInShoppingCart()&&modelObject.getListViewPopulated()){
                    updateOrdersList();


                }else if(modelObject.productsInShoppingCart()){
                    populateProductListView();
                    modelObject.setListViewPopulated(true);
                    setCheckOutTextInShoppingCart();
                }

                else {
                    setInfoTextViewInShoppingCart();
                }

            }
        });
    }

    private void setAccountImageView(){
        accountImageView = (ImageView) findViewById(R.id.toolbar_profile_image);
        accountImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                homeActivityViewFlipper.setDisplayedChild(2);
                setUpAccountClass();
            }
        });
    }

    public void updateOrdersList(){
        
        ordersList.clear();
        populateListOfAdapter();
        adp.notifyDataSetChanged();
    }

    /**
     *The method and the inner class will populate the inflator with the vegetables.
     * I do not know how this work. Do not touch it.
     */
    public void populateProductListView() {
        adp = new ShoppingCartAdapter(this);
        listView.setAdapter(adp);
        populateListOfAdapter();
    }


    public void populateListOfAdapter(){
        List<String> productNameList;
        productNameList = modelObject.getProductNameList();

        List<String> productPriceRate;
        productPriceRate = modelObject.getProductRateList();

        List<Integer> productQuantityList;
        productQuantityList = modelObject.getProductQuantityList();

        List<Integer> totalList;
        totalList = modelObject.getTotalList();

        for(int i=0; i<productNameList.size(); i++){
            ordersList.add(new OrderItemDetails(productNameList.get(i), productPriceRate.get(i),
                    productQuantityList.get(i), totalList.get(i)));

        }
    }

    public class ShoppingCartAdapter extends ArrayAdapter<OrderItemDetails>{
        Context c;
        LayoutInflater inflater;
        public ShoppingCartAdapter (Context context){
            super(context, R.layout.list_view_shopping_cart, ordersList);
            this.c=context;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if(convertView == null){
                inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_view_shopping_cart, null);
            }

            OrderItemDetails currentProduct = ordersList.get(position);
            TextView productName = (TextView) convertView.findViewById(R.id.product_name_text_view_in_shopping_cart_list);
            productName.setText(currentProduct.getProductName());

            TextView productRate = (TextView) convertView.findViewById((R.id.rate_text_view_in_shopping_cart));
            productRate.setText(currentProduct.getProductPriceRate());

            TextView productQuantity = (TextView) convertView.findViewById(R.id.quantity_text_view_in_shopping_cart);
            productQuantity.setText(currentProduct.getProductQuantity()+"");

            TextView productTotal = (TextView) convertView.findViewById(R.id.total_amount_text_view_in_shopping_cart);
            productTotal.setText(currentProduct.getProductTotal()+"");

            TextView deleteTextView = (TextView) convertView.findViewById(R.id.delete_product_in_shopping_cart);
            deleteTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ordersList.remove(position);
                    int p = modelObject.getPositionOfDeletedProduct(position);
                    modelObject.deleteProductFromShoppingCart(position);
                    deleteProduct(p);
                    notifyDataSetChanged();
                }
            });

            return convertView;
        }
    }

    private void setCheckOutTextInShoppingCart(){
        checkOutTextViewInShoppingCart.setText("Check Out");
    }

    private void setInfoTextViewInShoppingCart(){
        infoTextViewInShoppingCart.setText("OOPS...!!! No items in your shopping cart");
    }

    private void setTextViews(){
        infoTextViewInShoppingCart = (TextView) findViewById(R.id.info_text_view_in_shopping_cart);
        checkOutTextViewInShoppingCart = (TextView) findViewById(R.id.check_out_text_view_in_shopping_cart);

        //Text View in My account flipper.
        editInMyAccount = (TextView) findViewById(R.id.edit_text_at_top_in_account);
        nameTextViewInAccount = (EditText)findViewById(R.id.name_in_my_account_text);
        phoneTextViewInAccount = (EditText) findViewById(R.id.phone_in_my_account_text);
        deliveryLocationTextViewInAccount = (TextView) findViewById(R.id.delivery_location_text_in_my_account);
        deliveryLocationSpinnerInAccount = (Spinner) findViewById(R.id.delivery_location_spinner_in_my_account);
        myOrdersTextViewInAccount = (TextView) findViewById(R.id.my_order_text_in_my_account);
        myOrderArrowInAccount = (TextView) findViewById(R.id.my_order_arrow_in_my_account);
        logOutTextViewInAccount = (TextView) findViewById(R.id.log_out_text_view);
    }

    private void deleteProduct(int position){
            String category = modelObject.getCategory();
        if(category.equalsIgnoreCase("vegetables")){
            FragmentVegetables f = FragmentVegetables.getInstance();
            f.setVegetableListItemZero(position);
        }else {
            FragmentFruits f = FragmentFruits.getInstance();
            f.setFruitListItemZero(position);
        }
    }

    /*************************************************************************
     *Here is the method which will set up the account class, which will have
     * all the action listeners for the third flipper ( child 2 ).
     * ***********************************************************************
     */

    private void setUpAccountClass(){
        setUpMyOrders();
        setUpLogOut();
        myAccount = new MyAccount(editInMyAccount,nameTextViewInAccount, phoneTextViewInAccount
               ,deliveryLocationTextViewInAccount,deliveryLocationSpinnerInAccount,
                 modelObject, HomeActivity.this);

    }
    private void setUpMyOrders(){

        myOrdersTextViewInAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<String> dateOfOrdersList = modelObject.getDateOfOrdersList();
                List<String> totalOfOrdersList = modelObject.getTotalOfOrdersList();
                List<String> deliveryOfOrdersList = modelObject.getDeliveryOfOrdersList();
                List<String> paymentStatusOfOrdersList = modelObject.getPaymentStatusOfOrdersList();

                if(dateOfOrdersList.isEmpty()){
                    showErrorDialogue("No Orders" , "You do not have to any orders yet.\n" +
                            "Place order now :-) ");
                }else {
                    Intent myOrderIntent = new Intent(HomeActivity.this, MyOrders.class);
                    myOrderIntent.putExtra("dateOfOrderList" , (Serializable) dateOfOrdersList);
                    myOrderIntent.putExtra("totalOfOrdersList" ,(Serializable) totalOfOrdersList);
                    myOrderIntent.putExtra("deliveryOfOrdersList" , (Serializable) deliveryOfOrdersList);
                    myOrderIntent.putExtra("paymentStatusOfOrdersList" ,(Serializable) paymentStatusOfOrdersList);
                    startActivity(myOrderIntent);
                }


            }
        });
    }

    private void setUpLogOut(){
        logOutTextViewInAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // log out from database
                startActivity(new Intent(HomeActivity.this, LogInActivity.class));
                HomeActivity.this.finish();
            }
        });
    }

    private void showErrorDialogue(String title , String message){
        new AlertDialog.Builder(HomeActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }

}
