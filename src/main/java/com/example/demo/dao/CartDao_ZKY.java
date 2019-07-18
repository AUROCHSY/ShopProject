package com.example.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/*
 * Create by Jimmy on 2019/7/7 10:36 AM
 */
@Mapper
public interface CartDao_ZKY {

    //根据商品名称flavor_name找到flavor_id
    @Select(value = "select demo.tb_flavor.flavor_id from demo.tb_flavor where demo.tb_flavor.flavor_name = #{arg0} ")
    int search_flavor_id_by_flavor_name(String flavor_name);

    //插入一条购物车（加入购物车功能）
    //user_id在session中，flavor_id通过上面得到，commodity_id通过commodityDao得到，quantity通过前端传参到后端
    @Insert(value = "insert  into demo.tb_cart(tb_cart.user_id, tb_cart.flavor_id, tb_cart.commodity_id, tb_cart.quantity) " +
            "value(#{arg0} ,#{arg1} ,#{arg2} ,#{arg3} ) ")
    int insert_cart(int user_id, int flavor_id, int commodity_id, int quantity);

    //获取购物车中该商品的原quantity值
    @Select(value = "SELECT quantity FROM demo.tb_cart " +
            "WHERE demo.tb_cart.cart_id = #{arg0} ")
    int get_quantity(int cart_id);


}
