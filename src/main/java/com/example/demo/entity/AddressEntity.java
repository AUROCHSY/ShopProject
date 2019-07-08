package com.example.demo.entity;

import lombok.Data;

/*
 *地址实体类
 */
@Data
public class AddressEntity {

    private int address_id;     //主键
    private int user_id;        //外键

    private String full_address;//详细地址
    private String phone;       //电话
    private String zip_code;    //邮编
    private String name;        //联系人姓名
    private int state;          //1：默认 0：非默认


}
