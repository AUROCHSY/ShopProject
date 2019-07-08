package com.example.demo.service;


/*
* Service层的接口定义。
* function:传递从Controller发来的参数，到Dao层处理
 */

public interface UserService {

    String login(String username, String password);

    int register(String username, String password);

//    String search_commodity(String input);
}
