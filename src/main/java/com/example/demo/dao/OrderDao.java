package com.example.demo.dao;

/*
 * Create by Jimmy on 2019/7/4 2:15 PM
 */

import com.example.demo.entity.OrderVoEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderDao {

    //根据user_id取得全部订单信息OrderVoEntity
    @Select(value = "SELECT demo.tb_user.user_id, demo.tb_order.order_id, demo.tb_commodity.cname, demo.tb_order.ordertime, demo.tb_order.totalprice, demo.tb_order.state,demo.tb_commodity.img " +
            "FROM demo.tb_user, demo.tb_order, demo.tb_orders_detail, demo.tb_commodity " +
            "WHERE demo.tb_user.user_id = demo.tb_order.user_id " +
            "AND demo.tb_order.order_id = demo.tb_orders_detail.order_id " +
            "AND demo.tb_orders_detail.commodity_id = demo.tb_commodity.commodity_id " +
            "AND demo.tb_user.user_id = #{arg0} " +
            "ORDER BY demo.tb_order.ordertime DESC LIMIT 10")
    public List<OrderVoEntity> search_order_by_user_id(int user_id);

    //增加订单
    @Insert(value = "INSERT INTO demo.tb_order (user_id, address_id, totalprice, state) " +
            "VALUES (#{arg0}, #{arg1}, #{arg2},  #{arg3} ) " )
    public int add_order(int user_id, int address_id, double totalprice, int state);

    //获得账户余额
    @Select(value = "SELECT money FROM demo.tb_account where user_id = #{arg0} ")
    public double get_money(int user_id);

    //更新用户账户余额
    @Update(value = "UPDATE demo.tb_account SET money = #{arg1} WHERE account_id = #{arg0} ")
    public int update_account(int user_id, double new_money);

}
