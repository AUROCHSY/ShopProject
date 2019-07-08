package com.example.demo.entity;/*
* create by yrh on 2019/7/4 21:48
*/

import lombok.Data;

@Data
public class CategoryEntity {
    private int first_category_id;            //一级目录Id
    private int second_category_id;//二级目录id
    private String first_category_name;//一级目录名称
    private String second_category_name;//二级目录名称

}
