package com.example.demo.entity;


import lombok.Data;

/*
 *购物车实体类
 */
@Data
public class CartEntity {

    private int cart_id;   //主键
    private int user_id;   //外键
    private int flavor_id;   //外键
    private int commodity_id;   //外键

    private int quantity;

}
