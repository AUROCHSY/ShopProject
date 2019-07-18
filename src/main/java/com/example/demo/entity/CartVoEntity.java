package com.example.demo.entity;

/*
 * Create by Jimmy on 2019/7/2 2:19 PM
 */

import lombok.Data;

@Data
public class CartVoEntity {
    private String cname;
    private String img;
    private double original_price;
    private double promotional_price;
    private String flavor_name;
    private int quantity;


    public CartVoEntity(String cname, String img, double original_price, double promotional_price, String flavor_name, int quantity) {
        this.cname = cname;
        this.img = img;
        this.original_price = original_price;
        this.promotional_price = promotional_price;
        this.flavor_name = flavor_name;
        this.quantity = quantity;
    }




}
