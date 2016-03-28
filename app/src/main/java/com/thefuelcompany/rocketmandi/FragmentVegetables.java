package com.thefuelcompany.rocketmandi;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentContainer;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.app.ProgressDialog.show;
import static com.thefuelcompany.rocketmandi.R.color.colorAppTheme;


/**
 * Created by pradumanpraduman on 11/03/16.
 */
public class FragmentVegetables extends Fragment {
    private List<Vegetables> vegetablesList = new ArrayList<Vegetables>();
    private View v;
    // get the object of rocket mandi class from home activity , this is only for time pass
    private RocketMandiModel modelObject = new RocketMandiModel();

 public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle saveInstanceState) {

     v = inflater.inflate(R.layout.fragment_vegetables, container, false);
     populateVegetableList();
     populateVegetableListView();
     return v;
 }


    /**
     * The method will populate vegetable list with objects of vegetables class.
     * The amount of product added, add and subtract sign are not required to be populated as they are
     * already executed in xml file.
     */
    private void populateVegetableList(){
        List<Integer> vegetableIconList;
        vegetableIconList = modelObject.getVegetableIconList();

        List<String> vegetableNameList;
        vegetableNameList = modelObject.getVegetableNameList();

        List<String> vegetablePriceRateList;
        vegetablePriceRateList = modelObject.getVegetablePriceRateList();

        List<Integer> vegetableIdList;
        vegetableIdList = modelObject.getVegetableIdList();

        for(int i=0; i<vegetableIconList.size(); i++){
         vegetablesList.add(new Vegetables(vegetableIconList.get(i),vegetableNameList.get(i),vegetablePriceRateList.get(i),vegetableIdList.get(i)));
        }

    }


    private void populateVegetableListView() {
        ArrayAdapter<Vegetables> vegetableAdapter = new MyListAdapter();
        ListView vegetableListView = (ListView) v.findViewById(R.id.vegetables_list_view);
        vegetableListView.setAdapter(vegetableAdapter);
    }

    private class MyListAdapter extends ArrayAdapter<Vegetables>{
        public MyListAdapter(){
            super(FragmentVegetables.this.getContext() , R.layout.list_view_vegetables, vegetablesList);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent){
            View itemView = convertView;
            if(itemView == null){
                itemView = getActivity().getLayoutInflater().inflate(R.layout.list_view_vegetables, parent, false);
            }
            Vegetables currentVeggi = vegetablesList.get(position);

            //Add vegetable icon
            ImageView imageView = (ImageView) itemView.findViewById(R.id.image_view_vegetable);
            imageView.setImageResource(currentVeggi.getVegetableIcon());

            //Add Vegetable name
            TextView vegetableNameTextView = (TextView) itemView.findViewById(R.id.text_view_vegetable_name);
            vegetableNameTextView.setText(currentVeggi.getVegetableName());

            //Add rate
            TextView vegetablePriceRateTextView = (TextView) itemView.findViewById(R.id.text_view_vegetable_rate);
            vegetablePriceRateTextView.setText(currentVeggi.getVegetablePrice());

            //Adding sign
            TextView vegetableAddIconSymbolTextView = (TextView) itemView.findViewById(R.id.list_view_vegetable_add_symbol);
            vegetableAddIconSymbolTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(getActivity().getApplicationContext(), "Clicked " + position, Toast.LENGTH_SHORT).show();
                }
            });

            TextView vegetableSubtractIconSymbolTextView = (TextView) itemView.findViewById(R.id.list_view_vegetable_subtract_symbol);
            vegetableSubtractIconSymbolTextView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick (View view){
                    Toast.makeText(getActivity().getApplicationContext(), "Subtract clicked "+ position, Toast.LENGTH_SHORT).show();
                }
            });



            return itemView;
        }

    }

}
