package com.thefuelcompany.rocketmandi;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class HomeActivity extends  FragmentActivity {

    ViewPager viewPager = null;
    //private String username;
   // private RocketMandiModel modelObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // check if someone is logged in or not
        if(false){

            setContentView(R.layout.activity_home);
            viewPager = (ViewPager) findViewById(R.id.pager);
            FragmentManager fragmentManager = getSupportFragmentManager();
            viewPager.setAdapter(new MyAdapter(fragmentManager));
            //   modelObject = new RocketMandiModel();

            //start log in
        }else {

            Intent LogInActivityIntent= new Intent(HomeActivity.this,LogInActivity.class);
            startActivity(LogInActivityIntent);

        }
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
            }
            if(position==1){
                fragment = new FragmentFruits();
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


}
