package com.thefuelcompany.rocketmandi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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
    private RocketMandiModel modelObject;
    List<Integer> vegetableQuantityList;


 public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle saveInstanceState) {

     /**
      * I do not know how inflator and shit works, I learned it from you tube and it
      * just work.
      */
     v = inflater.inflate(R.layout.fragment_vegetables, container, false);
     setModelObject();
     populateVegetableList();
     populateVegetableListView();
     return v;
 }


    /**
     * The method will populate vegetable list with objects of vegetables class.
     * The amount of product added, add and subtract sign are not required to be populated as they are
     * already executed in xml file.
     * The method will get individual lists from Model, and then make a new list of Vegetable class
     * objects which will store data of each individual row of vegetable and fruit.
     */
    private void populateVegetableList(){
        List<Integer> vegetableIconList;
        vegetableIconList = modelObject.getVegetableIconList();

        List<String> vegetableNameList;
        vegetableNameList = modelObject.getVegetableNameList();

        List<String> vegetablePriceRateList;
        vegetablePriceRateList = modelObject.getVegetablePriceRateList();

        vegetableQuantityList = modelObject.getVegetableQuantityList();

        for(int i=0; i<vegetableIconList.size(); i++){
         vegetablesList.add(new Vegetables(vegetableIconList.get(i),vegetableNameList.get(i),vegetablePriceRateList.get(i),vegetableQuantityList.get(i)));
        }

    }


    /**
     *The method and the inner class will populate the inflator with the vegetables.
     * I do not know how this work. Do not touch it.
     */
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

            //Add quantity
            final TextView vegetableQuantityAddedTextView = (TextView) itemView.findViewById(R.id.text_view_vegetable_amount_ordered);
            vegetableQuantityAddedTextView.setText(currentVeggi.getVegetableQuantity()+"");

            //Adding sign
            TextView vegetableAddIconSymbolTextView = (TextView) itemView.findViewById(R.id.list_view_vegetable_add_symbol);
            vegetableAddIconSymbolTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    addVegetableQuantity(position, vegetableQuantityAddedTextView);

                }
            });

            //subtract sign
            TextView vegetableSubtractIconSymbolTextView = (TextView) itemView.findViewById(R.id.list_view_vegetable_subtract_symbol);
            vegetableSubtractIconSymbolTextView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick (View view){
                    subtractVegetableQuantity(position , vegetableQuantityAddedTextView);
                }
            });



            return itemView;
        }

    }

    /**
     * The method will update the view with the quantity added.
     * Also, it will update the Model class with the added quantity.
     * @param position
     * @param textView
     */
    private void addVegetableQuantity(int position, TextView textView){
        Integer quantityBefore = vegetableQuantityList.get(position);
        Integer quantityNew = quantityBefore+1;
        vegetableQuantityList.set(position, quantityNew);
        modelObject.changeVegetableQuantity(position, quantityNew);
        textView.setText(quantityNew+"");
    }

    /**
     * The method will update the view with the quantity subtracted.
     * Also, it will update the Model class with the subtracted quantity.
     * @param position
     * @param textView
     */
    private void subtractVegetableQuantity(int position, TextView textView){
        Integer quantityBefore = vegetableQuantityList.get(position);
        Integer quantityNew;
        if (quantityBefore > 0){
            quantityNew = quantityBefore-1;
            modelObject.changeVegetableQuantity(position, quantityNew);
            vegetableQuantityList.set(position, quantityNew);
            textView.setText(quantityNew+"");
        }else {
            quantityNew = quantityBefore;
        }

    }


    public void setVegetableListItemZero(int positionOfDeletedProduct){
    //    vegetableQuantityList.set(positionOfDeletedProduct , 0);
        // I will add the functionality myseld , just call this method without exception
        Toast.makeText(getActivity().getApplicationContext(),"This is working  :"+ positionOfDeletedProduct , Toast.LENGTH_SHORT).show();
    }

    /**
     * The method will retrieve the Model class object
     * from the Bundle when instantiated.
     */
    private void setModelObject(){
       modelObject = (RocketMandiModel) getArguments().getSerializable("key");
    }

}
