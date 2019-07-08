package com.example.demo.entity;


import lombok.Data;

/*
 *口味商品中间表 实体类
 */
@Data
public class FlavorCommodityEntity {

    private int flavor_commodity_id;  //主键
    private int flavor_id;            //外键
    private int commodity_id;         //外键

    private int stock;                 //库存


}
