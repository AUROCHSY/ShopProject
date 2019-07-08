package com.example.demo.entity;


import lombok.Data;

/*
 *网站全部消费记录实体类
 */
@Data
public class RecordEntity {

    private int record_id;     //主键
    private int user_id;        //外键

    private double consume;

}
