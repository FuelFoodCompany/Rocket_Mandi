package com.thefuelcompany.rocketmandi;

/**
 * Created by pradumanpraduman on 26/04/16.
 */
public class MyOrderDetailsList {
    private String date;
    private String total;
    private String paymentStatus;
    private String delivery;

    public MyOrderDetailsList(String date, String total, String paymentStatus, String delivery){
        this.date = date;
        this.total = total;
        this.paymentStatus = paymentStatus;
        this.delivery = delivery;
    }

    public String getDate(){
        return date;
    }
    public String getTotal(){
        return total;
    }
    public String getPaymentStatus(){
        return paymentStatus;
    }
    public String getDelivery(){
        return delivery;
    }
}
