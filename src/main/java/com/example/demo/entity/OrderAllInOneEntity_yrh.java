package com.example.demo.entity;/*
* create by yrh on 2019/7/9 17:40
*/

import lombok.Data;

import java.util.List;

@Data
public class OrderAllInOneEntity_yrh {
    public OrderGroupEntity_yrh orderOne;//订单聚合类实体中的一个订单
    public String [] cnameOneLineArr;//将所有订单所有商品cname转化成的二维数组中的一维
}
