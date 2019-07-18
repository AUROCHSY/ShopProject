package com.example.demo.service;

/*
 * Create by Jimmy on 2019/7/7 10:45 AM
 */


public interface CartService_ZKY {
    int search_flavor_id_by_flavor_name(String flavor_name);
    int insert_cart(int user_id, String flavor_name, String cname, int quantity);
    int get_quantity(int cart_id);
}
