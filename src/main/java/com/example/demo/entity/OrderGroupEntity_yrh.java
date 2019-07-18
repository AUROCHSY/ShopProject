package com.example.demo.entity;/*
* create by yrh on 2019/7/9 10:31
*/

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OrderGroupEntity_yrh {
    private int order_id; //订单id(tb_order和tb_order_detial都有)

    public String cname_group;    //tb_order_detial里的
    private String quantity_group; //商品数量，tb_order_detial里的
    private int state;          //0：待付款     1：待发货   2：收获    3：待评价


}
