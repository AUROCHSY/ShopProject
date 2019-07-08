package com.example.demo.entity;


import lombok.Data;

import java.sql.Timestamp;

/*
 *订单实体类
 */
@Data
public class OrderEntity {

    private String order_id;    //主键
    private int user_id;        //外键
    private int address_id;    //外键

    private double totalprice;
    private String remark;
    private Timestamp ordertime;
    private int state;          //0：待付款     1：待发货   2：收获    3：待评价


}
