package com.example.demo.entity;


import lombok.Data;

/*
 *二级分类 实体类
 */
@Data
public class SecondCategoryEntity {

    private int second_category_id;            //主键
    private int first_category_id;            //外键

    private String name;

}
