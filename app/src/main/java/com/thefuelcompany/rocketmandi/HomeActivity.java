package com.thefuelcompany.rocketmandi;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class HomeActivity extends  FragmentActivity {

    RocketMandiModel modelObject = new RocketMandiModel();
    ViewPager viewPager = null;
    ViewFlipper homeActivityViewFlipper;
    ImageView homeImageView;
    ImageView shoppingCartImageView;
    ImageView accountImageView;
    //private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // check if someone is logged in or not

            setContentView(R.layout.activity_home);
            setFlipper();
            setHomeImageView();
            setShoppingCartImageView();
            setAccountImageView();
            viewPager = (ViewPager) findViewById(R.id.pager);
            FragmentManager fragmentManager = getSupportFragmentManager();
            viewPager.setAdapter(new MyAdapter(fragmentManager));

            //start log in

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

}
