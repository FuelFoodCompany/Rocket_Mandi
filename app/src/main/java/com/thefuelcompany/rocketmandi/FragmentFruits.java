package com.thefuelcompany.rocketmandi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by praduman on 11/03/16.
 */
public class FragmentFruits extends Fragment {

    private List<Fruits> fruitList = new ArrayList<Fruits>();
    private View v;
    // get the object of rocket mandi class from home activity , this is only for time pass
    private RocketMandiModel modelObject = new RocketMandiModel();

    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle saveInstanceState){
        v = inflater.inflate(R.layout.fragment_fruits, container, false);
        populateFruitsList();
        populateFruitsView();
        return v;
    }

    /**
     * The method will populate vegetable list with objects of vegetables class.
     * The amount of product added, add and subtract sign are not required to be populated as they are
     * already executed in xml file.
     */
    private void populateFruitsList(){
        List<Integer> fruitIconList;
        fruitIconList = modelObject.getFruitIconList();

        List<String> fruitNameList;
        fruitNameList = modelObject.getFruitNameList();

        List<String> fruitPriceRateList;
        fruitPriceRateList = modelObject.getFruitPriceRateList();

        List<Integer> fruitIdList;
        fruitIdList = modelObject.getFruitIdList();

        for(int i=0; i<fruitIconList.size(); i++){
            fruitList.add(new Fruits(fruitIconList.get(i),fruitNameList.get(i),fruitPriceRateList.get(i),fruitIdList.get(i)));
        }

    }

    private void populateFruitsView() {
        ArrayAdapter<Fruits> fruitAdapter = new MyListAdapter();
        ListView fruitListView = (ListView) v.findViewById(R.id.fruits_list_view);
        fruitListView.setAdapter(fruitAdapter);
    }

    private class MyListAdapter extends ArrayAdapter<Fruits>{
        public MyListAdapter(){
            super(FragmentFruits.this.getContext() , R.layout.list_view_fruits, fruitList);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent){
            View itemView = convertView;
            if(itemView == null){
                itemView = getActivity().getLayoutInflater().inflate(R.layout.list_view_fruits, parent, false);
            }
            Fruits currentFruit = fruitList.get(position);

            //Add fruit icon
            ImageView imageView = (ImageView) itemView.findViewById(R.id.image_view_fruits);
            imageView.setImageResource(currentFruit.getFruitIcon());

            //Add fruit name
            TextView fruitNameTextView = (TextView) itemView.findViewById(R.id.text_view_fruit_name);
            fruitNameTextView.setText(currentFruit.getFruitName());

            //Add rate
            TextView fruitPriceRateTextView = (TextView) itemView.findViewById(R.id.text_view_fruit_rate);
            fruitPriceRateTextView.setText(currentFruit.getFruitPrice());

            //Adding sign
            TextView fruitAddIconSymbolTextView = (TextView) itemView.findViewById(R.id.list_view_fruit_add_symbol);
            fruitAddIconSymbolTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(getActivity().getApplicationContext(), "Clicked add" + position, Toast.LENGTH_SHORT).show();
                }
            });

            TextView fruitSubtractIconSymbolTextView = (TextView) itemView.findViewById(R.id.list_view_fruit_subtract_symbol);
            fruitSubtractIconSymbolTextView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick (View view){
                    Toast.makeText(getActivity().getApplicationContext(), "Subtract clicked fruit "+ position, Toast.LENGTH_SHORT).show();
                }
            });



            return itemView;
        }

    }
}
