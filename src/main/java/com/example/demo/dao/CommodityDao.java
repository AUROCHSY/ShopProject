package com.example.demo.dao;

import com.example.demo.entity.CommodityEntity;
import com.example.demo.entity.FlavorEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/*
 * Create by Jimmy on 2019/7/1 9:30 PM
 */

@Mapper
public interface CommodityDao {

    //根据输入内容筛选CommodityEntity的对象
    @Select(value = "select * from demo.tb_commodity where cname REGEXP #{arg0} ")
    List<CommodityEntity> search_commodity(String search_input);

    //根据商品id找到全部口味
    @Select(value = "select f.* from demo.tb_flavor_commodity fc" +
            " inner join demo.tb_commodity c on c.commodity_id = fc.commodity_id" +
            " inner join demo.tb_flavor f on f.flavor_id = fc.flavor_id" +
            " where c.commodity_id = #{arg0} ")
    List<FlavorEntity> search_flavor_by_commodity_id(int commodity_id);

}



















//package com.example.demo.dao;/*
//* create by yrh on 2019/7/1 22:30
//*/
//
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Select;
//
//import java.sql.Timestamp;
//
//public interface CommodityDao {
//
//    //根据二级目录id 展示商品
//    @Select(value = "select * from demo.tb_commodity where second_category_id = #{arg0}")
//    String show_commodity(int second_category_id);
//
//    //根据一级目录id 展示二级目录
//    @Select(value = "select second_category_id from demo.tb_second_category where first_category_id =#{arg0}")
//    String show_second_category(int first_category_id);
//
//}
