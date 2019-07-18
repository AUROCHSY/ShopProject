package com.example.demo.service;


/*
* Service层的接口定义。
* function:传递从Controller发来的参数，到Dao层处理
 */

import com.example.demo.entity.UserEntity;

public interface UserService {

    int search_user_id_by_user_name(String username);

    int search_user_state_by_user_name(String username);

    String login(String username, String password);

    int register(String username, String password);

    String username_isExist(String username);

    double search_money_by_user_id(int user_id);
}
