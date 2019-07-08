package com.example.demo.entity;/*
* create by yrh on 2019/7/7 16:34
*/

import lombok.Data;

@Data
public class GoodsEntity_yrh {
        private int commodity_id;   //主键
//        private int second_category_id;   //外键(二级目录Id)
//        private int brand_id;   //外键(一级目录Id)
        private String cname;//商品名
        private String name;//品牌名
        private String second_category_name;//二级目录名
}
