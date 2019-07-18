package com.example.demo.entity;


import lombok.Data;

/*
 *品牌实体类-----如乐视旗舰店
 */
@Data
public class BrandEntity {

    private int brand_id;       //主键

    private String name;        //品牌名称
    private String phone;
    private String email;
    private String address;
    private int state;          //0：合作 1：取消合作
    private String contact;     //负责人

}
