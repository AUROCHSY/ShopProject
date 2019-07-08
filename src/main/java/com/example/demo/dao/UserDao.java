package com.example.demo.dao;


import com.example.demo.entity.CommodityEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;


@Mapper
public interface UserDao {

    //登录时的用户名密码判断，返回user_id。 username 和 password是前端页面传递来的数据
    @Select(value = "select user_id from demo.tb_user where tb_user.username = #{arg0} and tb_user.password = #{arg1} ")
    String login(String username, String password);

    //注册时判断tb_user表中username是否存在，返回username。
    @Select(value = "select username from demo.tb_user where tb_user.username = #{arg0} ")
    String username_isExist(String username);

    //注册时向tb_user表中插入username password
    @Insert(value = "insert into demo.tb_user(tb_user.username, tb_user.password) " +
            "value(#{arg0}, #{arg1}) ")
    int register(String username, String password);



}
