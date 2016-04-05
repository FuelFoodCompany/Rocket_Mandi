package com.thefuelcompany.rocketmandi;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pradumanpraduman on 02/04/16.
 */
public class ShoppingCart extends Activity{


    private RocketMandiModel modelObject;


    public ShoppingCart(RocketMandiModel modelObject){
       this.modelObject = modelObject;
   }

}
