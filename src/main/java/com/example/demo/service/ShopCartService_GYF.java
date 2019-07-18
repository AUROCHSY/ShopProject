package com.example.demo.service;

import com.example.demo.entity.CartVoEntity;

import java.util.List;

public interface ShopCartService_GYF {
    List<CartVoEntity> get_cartList(int user_id);
    CartVoEntity get_cart_commodity_info(int cart_id);
    int delete_cart(int user_id, String cname, String flavor_name);
    int cart_commodity_quantity_minus(int user_id, String cname, String flavor_name);
    int cart_commodity_quantity_plus(int user_id, String cname, String flavor_name);
    int cart_commodity_quantity_change(int user_id, String cname, String flavor_name, int new_quantity);
    int get_cart_id(int user_id, String cname, String flavor_name);
    String get_cname(int user_id, String cname, String flavor_name);
}