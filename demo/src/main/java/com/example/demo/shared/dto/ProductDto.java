package com.example.demo.shared.dto;

import java.io.Serializable;

public class ProductDto implements Serializable {

  //  private long id;
    private String productId;
    private String name;
    private String category;
    private float cost;

    /*public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
*/
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name!=null){
            this.name = name;    }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if(category!=null){
        this.category = category; }
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

}
