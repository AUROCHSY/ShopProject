package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
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


}
