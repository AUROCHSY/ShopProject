package com.example.demo.service;

/*
 * Create by Jimmy on 2019/7/4 2:14 PM
 */


import com.example.demo.entity.OrderVoEntity;

import java.util.List;

public interface OrderService {

    List<OrderVoEntity> search_order_by_user_id(int user_id);
    int add_order(int user_id, int address_id, double totalprice, int state);
    double get_money(int user_id);
    int update_account(int user_id, double new_money);

}
