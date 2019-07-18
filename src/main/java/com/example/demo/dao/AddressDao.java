package com.example.demo.dao;

/*
 * 地址数据层
 * Create by Jimmy on 2019/7/2 3:20 PM
 */

import com.example.demo.entity.AddressEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface AddressDao {

    //根据user_id找address
    @Select(value = "select * from demo.tb_address where user_id = #{arg0} ")
    public List<AddressEntity> search_address_by_user_id(int user_id);

    //为增添地址信息
    @Insert(value = "insert into demo.tb_address(user_id, full_address, phone, name) " +
            " value( #{arg0},  #{arg1}, #{arg2}, #{arg3})")
    public int insert_address(int user_id, String full_address, String phone, String name);

    //删除地址
    @Delete(value = "delete from demo.tb_address where address_id = #{arg0} ")
    public int delete_address(int address_id);

    //修改地址
    @Update(value = "update demo.tb_address set full_address = #{arg1}, phone = #{arg2}, name = #{arg3}" +
            " where address_id = #{arg0} ")
    public int update_address(int address_id, String full_address, String phone, String name);

    //根据address_id找到地址信息
    @Select(value = "select * from demo.tb_address where address_id = #{arg0} ")
    public AddressEntity get_address_by_id(int address_id);
}
