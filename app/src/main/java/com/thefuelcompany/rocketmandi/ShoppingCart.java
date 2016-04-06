package com.thefuelcompany.rocketmandi;

import android.app.Activity;
import android.widget.TextView;

/**
 * Created by pradumanpraduman on 02/04/16.
 */
public class ShoppingCart extends Activity{


    private RocketMandiModel modelObject;
    private TextView infoTextViewInShopppingCart;


    public ShoppingCart(RocketMandiModel modelObject){
       this.modelObject = modelObject;
   }

    public void setEmptyProductShoppingCart(TextView textView){
        textView.setText("Opps , no product in shopping cart");
    }

    public void setCheckOutFromShoppingCart(TextView textView){
        textView.setText("Check Out");
    }

}
