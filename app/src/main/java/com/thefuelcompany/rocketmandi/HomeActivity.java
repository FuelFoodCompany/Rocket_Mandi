package com.thefuelcompany.rocketmandi;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends  FragmentActivity {

    RocketMandiModel modelObject = new RocketMandiModel();
    ViewPager viewPager = null;
    ViewFlipper homeActivityViewFlipper;
    ImageView homeImageView;
    ImageView shoppingCartImageView;
    ImageView accountImageView;
    ShoppingCart shoppingCart;
    ListView  listView ;
    private TextView infoTextViewInShoppingCart;
    public List<OrderItemDetails> ordersList = new ArrayList<OrderItemDetails>();
    ShoppingCartAdapter adp;

    View v;
    //private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // check if someone is logged in or not

        setContentView(R.layout.activity_home);
        setShoppingCartObject();
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
            Fragment fragment = null;
            if(position == 0){
                fragment = new FragmentVegetables();
                Bundle args = new Bundle();
                args.putSerializable("key", modelObject);
                fragment.setArguments(args);
            }
            if(position==1){
                fragment = new FragmentFruits();
                Bundle args = new Bundle();
                args.putSerializable("key", modelObject);
                fragment.setArguments(args);
            }
            return fragment;
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
                    shoppingCart.setCheckOutFromShoppingCart(infoTextViewInShoppingCart);
                }

                else {
                    shoppingCart.setEmptyProductShoppingCart(infoTextViewInShoppingCart);
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

            }
        });
    }

    private void setShoppingCartObject(){
        shoppingCart = new ShoppingCart(modelObject);
    }

    public void updateOrdersList(){

        Toast.makeText(getApplicationContext(), "dude it is updated", Toast.LENGTH_SHORT).show();
        ordersList.clear();
        populateListOfAdapter();
        adp.notifyDataSetChanged();
    }

    /**
     *The method and the inner class will populate the inflator with the vegetables.
     * I do not know how this work. Do not touch it.
     */
    public void populateProductListView() {

        Toast.makeText(this, "Update", Toast.LENGTH_SHORT).show();

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
        public View getView(int position, View convertView, ViewGroup parent) {

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


            return convertView;
        }
    }

    private void setTextViews(){
        infoTextViewInShoppingCart = (TextView) findViewById(R.id.info_text_view_in_shopping_cart);
    }

}
