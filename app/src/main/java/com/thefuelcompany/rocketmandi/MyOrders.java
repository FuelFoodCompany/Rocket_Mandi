package com.thefuelcompany.rocketmandi;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyOrders extends Activity {

    private Button closeButton;
    private List<String> dateOfOrdersList;
    private List<String> totalOfOrdersList;
    private List<String> paymentStatusOfOrdersList;
    private List<String> deliveryOfOrdersList;
    private ListView myOrderListView;
    private List<MyOrderDetailsList> myOrderList = new ArrayList<MyOrderDetailsList>();
    private MyOrdersDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_my_orders);
        populateListOfAdapter();
        setAdapter();
        setCloseButtonActionListener();
    }

    private void populateListOfAdapter(){
        setOrderLists();
        for(int i =0; i<dateOfOrdersList.size(); i++){
            myOrderList.add(new MyOrderDetailsList(dateOfOrdersList.get(i), totalOfOrdersList.get(i),
                    paymentStatusOfOrdersList.get(i),deliveryOfOrdersList.get(i)));
        }
    }


    private void setOrderLists(){
        dateOfOrdersList = (ArrayList<String>) getIntent().getSerializableExtra("dateOfOrderList");
        totalOfOrdersList = (ArrayList<String>) getIntent().getSerializableExtra("totalOfOrdersList");
        paymentStatusOfOrdersList = (ArrayList<String>) getIntent().getSerializableExtra("deliveryOfOrdersList");
        deliveryOfOrdersList = (ArrayList<String>) getIntent().getSerializableExtra("paymentStatusOfOrdersList");
        myOrderListView = (ListView) findViewById(R.id.my_orders_list_view);
    }

    private void setAdapter(){
        adapter = new MyOrdersDetailAdapter(this);
        myOrderListView.setAdapter(adapter);
    }


    public class MyOrdersDetailAdapter extends ArrayAdapter<MyOrderDetailsList> {
        Context c;
        LayoutInflater inflater;
        public MyOrdersDetailAdapter (Context context){
            super(context, R.layout.my_order_list_view_layout, myOrderList);
            this.c=context;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if(convertView == null){
                inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.my_order_list_view_layout, null);
            }

            MyOrderDetailsList currentDetail = myOrderList.get(position);

            TextView date = (TextView) convertView.findViewById(R.id.date_of_order_text_view_in_my_order_list);
            date.setText(currentDetail.getDate());

            TextView total = (TextView) convertView.findViewById((R.id.total_of_order_text_view_in_my_order_list));
            total.setText(currentDetail.getTotal());

            TextView delivery = (TextView) convertView.findViewById(R.id.delivery_of_order_text_view_in_my_order_list);
            delivery.setText(currentDetail.getDelivery());

            TextView paymentStatus = (TextView) convertView.findViewById(R.id.payment_status_of_order_text_view_in_my_order_list);
            paymentStatus.setText(currentDetail.getPaymentStatus());

            return convertView;
        }
    }

    private void setCloseButtonActionListener(){
        closeButton = (Button) findViewById(R.id.finish_activity_my_orders_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyOrders.this.finish();
            }
        });

    }

}
