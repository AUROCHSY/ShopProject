package com.example.demo.entity;

import lombok.Data;

import java.sql.Timestamp;

/*
 *评论实体类
 */
@Data
public class EvatuateEntity {

    private int evaluate_id;    //主键
    private int user_id;    //外键
    private int order_id;    //外键

    private String content;
    private Timestamp createtime;

}
