package com.thefuelcompany.rocketmandi;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
/**
 * Created by pradumanpraduman on 11/03/16.
 */
public class FragmentVegetables extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle saveInstanceState){
    return inflater.inflate(R.layout.fragment_vegetables, container, false);
    }
}
