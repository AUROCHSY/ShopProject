package com.example.demo.entity;


import lombok.Data;
import java.sql.Timestamp;

/*
 *商品实体类
 */
@Data
public class CommodityEntity {

    private int commodity_id;   //主键
    private int second_category_id;   //外键
    private int brand_id;   //外键

    private String cname;//商品名
    private double promotional_price;//促销价
    private double original_price;
    private String description;
    private String img;
    private Timestamp createtime;
    private String type;
    private String product_place;
    private String product_area;
    private String product_specificat;
    private String expiration_date;
    private String usage;
    private String storage_method;
    private String storage_number;
    private String license_number;

}
