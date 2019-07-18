package com.example.demo.service.impl;

import com.example.demo.dao.OrderDao;
import com.example.demo.entity.OrderVoEntity;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Create by Jimmy on 2019/7/4 2:14 PM
 */

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;


    @Override
    public List<OrderVoEntity> search_order_by_user_id(int user_id){
        return orderDao.search_order_by_user_id(user_id);
    }

    @Override
    public int add_order(int user_id, int address_id, double totalprice, int state){
        return orderDao.add_order(user_id, address_id, totalprice, state);
    }

    @Override
    public double get_money(int user_id){
        return orderDao.get_money(user_id);
    }

    @Override
    public int update_account(int user_id, double new_money){
        return orderDao.update_account(user_id, new_money);
    }



}
