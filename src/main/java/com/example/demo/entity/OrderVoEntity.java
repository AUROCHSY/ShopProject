package com.example.demo.entity;

import lombok.Data;

import java.sql.Timestamp;

/*
 * 在person_index中"我的订单"显示
 * Create by Jimmy on 2019/7/4 4:22 PM
 */
@Data
public class OrderVoEntity {

    public int user_id;         //设为public是因为在usercontroller中要调用
    public int order_id;

    private String cname;
    private Timestamp ordertime;
    private double totalprice;
    private int state;          //0：待付款     1：待发货   2：收获    3：待评价
    private String img;

}
