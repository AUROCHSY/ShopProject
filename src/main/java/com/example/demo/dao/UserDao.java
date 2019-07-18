package com.example.demo.dao;


import com.example.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;


@Mapper
public interface UserDao {

    //根据username找到user_id
    @Select(value = "select user_id from demo.tb_user where tb_user.username = #{arg0} ")
    int search_user_id_by_user_name(String username);

    //根据username找到user_id
    @Select(value = "select user_state from demo.tb_user where tb_user.username = #{arg0} ")
    int search_user_state_by_user_name(String username);

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

    //通过user_id找到账户余额money(double)
    @Select(value = "SELECT money FROM demo.tb_account a " +
            "INNER JOIN demo.tb_user u ON u.user_id = a.user_id " +
            "WHERE u.user_id = #{arg0} ")
    double search_money_by_user_id(int user_id);
}
