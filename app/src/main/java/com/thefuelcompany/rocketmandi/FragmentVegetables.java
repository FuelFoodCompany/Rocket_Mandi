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

 public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle saveInstanceState) {

     v = inflater.inflate(R.layout.fragment_vegetables, container, false);
     populateVegetableList();
     populateVegetableListView();
     return v;
 }



    private void populateVegetableList(){
        vegetablesList.add(new Vegetables(R.drawable.contacts_filled_50, "onion" , 50, 0, "+", 101));
        vegetablesList.add(new Vegetables(R.drawable.contacts_filled_50, "brinjal" , 70, 0,"+", 102));
        vegetablesList.add(new Vegetables(R.drawable.contacts_filled_50, "capsicum" , 50, 0, "+", 103));
        vegetablesList.add(new Vegetables(R.drawable.contacts_filled_50, "ladyfinger (Grade A) murga", 50, 0, "+", 104));
        vegetablesList.add(new Vegetables(R.drawable.home_50, "potato", 80, 0, "+", 105));
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
        public View getView(int position, View convertView, ViewGroup parent){
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

            //Adding sign
            TextView vegetableAddIconSymbolTextView = (TextView) itemView.findViewById(R.id.image_view_vegetable_add_symbol);
            vegetableAddIconSymbolTextView.setText(currentVeggi.getVegetableAddIcon());

            vegetableAddIconSymbolTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    Toast.makeText(getActivity().getApplicationContext(), "Clicked" , Toast.LENGTH_SHORT).show();
                }
            });

            return itemView;
        }

    }

}
