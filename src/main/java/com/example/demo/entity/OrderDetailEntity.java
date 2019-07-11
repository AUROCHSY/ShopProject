package com.example.demo.entity;


import lombok.Data;

/*
 *订单详情实体类
 */
@Data
public class OrderDetailEntity {

    private int detail_id;    //主键
    private int commodity_id;    //外键
    private int order_id;    //外键
    private int flavor_id;    //外键

    private int quantity;
    private double price;

}
