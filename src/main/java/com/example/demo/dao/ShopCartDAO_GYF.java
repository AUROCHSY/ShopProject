package com.example.demo.dao;


import com.example.demo.entity.CartVoEntity;
import com.example.demo.entity.CommodityEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShopCartDAO_GYF {

    //通过user_id找到该用户的全部购物车信息
    @Select(value = "SELECT demo.tb_commodity.cname, demo.tb_commodity.img, demo.tb_commodity.original_price, demo.tb_commodity.promotional_price, demo.tb_flavor.flavor_name, demo.tb_cart.quantity " +
            "FROM demo.tb_cart, demo.tb_flavor, demo.tb_commodity " +
            "WHERE (demo.tb_cart.user_id = #{arg0} " +
            "AND demo.tb_cart.flavor_id = demo.tb_flavor.flavor_id " +
            "AND demo.tb_cart.commodity_id = demo.tb_commodity.commodity_id);")
    List<CartVoEntity> get_cart_list(int user_id);

    //查询一下需要删除或更改的物品的cart_id，为之后的删除或更改做准备，通过用户id、商品名称和口味确定唯一的cart_id
    @Select(value = "SELECT cart_id FROM demo.tb_cart, demo.tb_commodity, demo.tb_flavor " +
            "WHERE (demo.tb_cart.commodity_id = demo.tb_commodity.commodity_id " +
            "AND demo.tb_cart.flavor_id = demo.tb_flavor.flavor_id " +
            "AND demo.tb_cart.user_id = #{arg0} " +
            "AND demo.tb_commodity.cname = #{arg1} " +
            "AND demo.tb_flavor.flavor_name = #{arg2} )")
    int get_cart_id(int user_id, String cname, String flavor_name);

    //同上一个select语句。返回一个string.查询成功返回cname,查询失败是null------------------zky------------------2019.7.8 13:00
    @Select(value = "SELECT cname FROM demo.tb_cart, demo.tb_commodity, demo.tb_flavor " +
            "WHERE (demo.tb_cart.commodity_id = demo.tb_commodity.commodity_id " +
            "AND demo.tb_cart.flavor_id = demo.tb_flavor.flavor_id " +
            "AND demo.tb_cart.user_id = #{arg0} " +
            "AND demo.tb_commodity.cname = #{arg1} " +
            "AND demo.tb_flavor.flavor_name = #{arg2} )")
    String get_cname(int user_id, String cname, String flavor_name);

    //购物车删除商品
    @Delete(value = "DELETE FROM demo.tb_cart " +
            "where demo.tb_cart.cart_id = #{arg0} ")
    int delete_by_cart_id(int cart_id);


    //根据cart_id获取quantity，用于购物车商品数量减1之前的判断
    @Select(value = "SELECT quantity FROM demo.tb_cart " +
            "WHERE demo.tb_cart.cart_id = #{arg0} ")
    int get_commodity_quantity_by_cart_id(int cart_id);


    //购物车商品数量变更
    @Update(value = "UPDATE demo.tb_cart " +
            "SET demo.tb_cart.quantity = #{arg1} " +
            "WHERE demo.tb_cart.cart_id = #{arg0} ")
    int cart_commodity_quantity_change(int cart_id, int new_quantity);


    //通过cart_id获取一个商品的信息（存储在CartVoEntity中）
    @Select(value = "SELECT demo.tb_commodity.cname, demo.tb_commodity.img, demo.tb_commodity.original_price, demo.tb_commodity.promotional_price, demo.tb_flavor.flavor_name, demo.tb_cart.quantity " +
            "FROM demo.tb_cart, demo.tb_flavor, demo.tb_commodity " +
            "WHERE (demo.tb_cart.cart_id = #{arg0} " +
            "AND demo.tb_cart.flavor_id = demo.tb_flavor.flavor_id " +
            "AND demo.tb_cart.commodity_id = demo.tb_commodity.commodity_id);")
    CartVoEntity get_cart_commodity_info(int cart_id);



}
