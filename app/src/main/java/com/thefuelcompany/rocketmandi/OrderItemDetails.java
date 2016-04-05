package com.thefuelcompany.rocketmandi;

/**
 * Created by pradumanpraduman on 02/04/16.
 */
public class OrderItemDetails {

    private String productName;
    private String productPriceRate;
    private Integer productTotal;
    private Integer productQuantity;

    public OrderItemDetails (String productName, String productPriceRate,  Integer productTotal
    , Integer productQuantity){
        super();
        this.productName = productName;
        this.productPriceRate = productPriceRate;
        this.productTotal = productTotal;
        this.productQuantity = productQuantity;

    }

  public String getProductName(){
      return productName;
  }
    public String getProductPriceRate(){
        return productPriceRate;
    }

    public Integer getProductTotal(){
        return productTotal;
    }

    public Integer getProductQuantity(){
        return productQuantity;
    }
}
