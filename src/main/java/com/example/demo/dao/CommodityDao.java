package com.example.demo.dao;

import com.example.demo.entity.CommodityEntity;
import com.example.demo.entity.EvatuateEntity;
import com.example.demo.entity.FlavorEntity;
import com.example.demo.entity.SecondCategoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/*
 * Create by Jimmy on 2019/7/1 9:30 PM
 */

@Mapper
public interface CommodityDao {

    //根据商品名称cname找到commodity_id
    @Select(value = "select demo.tb_commodity.commodity_id from demo.tb_commodity where demo.tb_commodity.cname = #{arg0} ")
    int search_commodity_id_by_cname(String cname);

    //根据输入内容search_input筛选CommodityEntity的对象
    @Select(value = "select * from demo.tb_commodity where cname REGEXP #{arg0} ")
    List<CommodityEntity> search_commodity(String search_input);

    //根据commodity_id筛选一个CommodityEntity的对象
    @Select(value = "select * from demo.tb_commodity where commodity_id = #{arg0} ")
    CommodityEntity search_commodity_by_commodity_id(int commodity_id);


    //根据二级目录second_category_id找到该目录下的全部商品CommodityEntity对象
    @Select(value = "select * from demo.tb_commodity where second_category_id = #{arg0} ")
    List<CommodityEntity> search_commodity_entity_by_second_category_id(int second_category_id);

    //根据一级目录first_category_id找到该目录下的全部二级目录SecondCategoryEntity对象
    @Select(value = "select * from demo.tb_second_category where first_category_id = #{arg0} ")
    List<SecondCategoryEntity> search_second_category_entity_by_first_category_id(int first_category_id);

    //根据商品id找到全部口味
    @Select(value = "select f.* from demo.tb_flavor_commodity fc" +
            " inner join demo.tb_commodity c on c.commodity_id = fc.commodity_id" +
            " inner join demo.tb_flavor f on f.flavor_id = fc.flavor_id" +
            " where c.commodity_id = #{arg0} ")
    List<FlavorEntity> search_flavor_by_commodity_id(int commodity_id);

    //根据商品上架时间createtime来排序
    @Select(value = "select * from demo.tb_commodity order by createtime desc limit 12")
    List<CommodityEntity> sort_commodity_by_createtime();

    //根据商品commodity_id找到全部评价
    @Select(value = "SELECT distinct demo.tb_evaluate.* " +
            "FROM demo.tb_evaluate, demo.tb_commodity, demo.tb_cart, demo.tb_user " +
            "WHERE demo.tb_commodity.commodity_id = demo.tb_cart.commodity_id " +
            "AND demo.tb_cart.user_id = demo.tb_user.user_id " +
            "AND demo.tb_user.user_id = demo.tb_evaluate.user_id " +
            "AND demo.tb_commodity.commodity_id = #{arg0} " +
            "ORDER BY demo.tb_evaluate.createtime DESC LIMIT 10")
    List<EvatuateEntity> search_evaluate_by_commodity_id(int commodity_id);

}
