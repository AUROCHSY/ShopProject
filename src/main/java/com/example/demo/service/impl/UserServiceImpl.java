package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDAO;

    //将参数从service层传递给dao层
    @Override
    public int search_user_id_by_user_name(String username){
        return userDAO.search_user_id_by_user_name(username);
    }

    @Override
    public int search_user_state_by_user_name(String username){
        return userDAO.search_user_state_by_user_name(username);
    }

    @Override
    public String login(String username, String password) {
        return userDAO.login(username, password);
    }

    @Override
    public int register(String username, String password){
        String tmp = userDAO.username_isExist(username);
        if (tmp == null) {//如果username不存在，说明可以注册
//            Timestamp regtime = new Timestamp(System.currentTimeMillis());
//            userDAO.register(username, password, regtime);
            userDAO.register(username, password);
            return 1;
        }//如果username存在，说明不可以注册
        return 0;
    }

    @Override
    public String username_isExist(String username){
        return userDAO.username_isExist(username);
    }

    @Override
    public double search_money_by_user_id(int user_id){
        return userDAO.search_money_by_user_id(user_id);
    }

}
