package com.example.demo.service.impl;

import com.example.demo.dao.ShopCartDAO_GYF;
import com.example.demo.entity.CartVoEntity;
import com.example.demo.service.ShopCartService_GYF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopCartServiceImpl_GYF implements ShopCartService_GYF {
    @Autowired
    private ShopCartDAO_GYF shopCartDAO_gyf;

    @Override
    public List<CartVoEntity> get_cartList(int user_id) {
        return shopCartDAO_gyf.get_cart_list(user_id);
    }

    @Override
    public CartVoEntity get_cart_commodity_info(int cart_id) {
        return shopCartDAO_gyf.get_cart_commodity_info(cart_id);
    }

    @Override
    public int delete_cart(int user_id, String cname, String flavor_name) {
        int cart_id = shopCartDAO_gyf.get_cart_id(user_id, cname, flavor_name);
        shopCartDAO_gyf.delete_by_cart_id(cart_id);
        return cart_id;
    }

    @Override
    public int cart_commodity_quantity_minus(int user_id, String cname, String flavor_name) {
        //购物车商品数量减1，返回变更后的数量(大于0)
        int cart_id = shopCartDAO_gyf.get_cart_id(user_id, cname, flavor_name);
        int old_quantity = shopCartDAO_gyf.get_commodity_quantity_by_cart_id(cart_id);
        int new_quantity = old_quantity - 1;

        if(new_quantity <= 0) return 0;

        shopCartDAO_gyf.cart_commodity_quantity_change(cart_id, new_quantity);
        return new_quantity;
    }

    @Override
    public int cart_commodity_quantity_plus(int user_id, String cname, String flavor_name) {
        //购物车商品数量加1，返回变更后的数量
        int cart_id = shopCartDAO_gyf.get_cart_id(user_id, cname, flavor_name);
        int old_quantity = shopCartDAO_gyf.get_commodity_quantity_by_cart_id(cart_id);
        int new_quantity = old_quantity + 1;

        shopCartDAO_gyf.cart_commodity_quantity_change(cart_id, new_quantity);
        return new_quantity;
    }

    @Override
    public int cart_commodity_quantity_change(int user_id, String cname, String flavor_name, int new_quantity) {
        int cart_id = shopCartDAO_gyf.get_cart_id(user_id, cname, flavor_name);
        shopCartDAO_gyf.cart_commodity_quantity_change(cart_id, new_quantity);
        return new_quantity;
    }

    @Override
    public int get_cart_id(int user_id, String cname, String flavor_name) {
        return shopCartDAO_gyf.get_cart_id(user_id, cname, flavor_name);
    }

    @Override
    public String get_cname(int user_id, String cname, String flavor_name){
        return shopCartDAO_gyf.get_cname(user_id, cname, flavor_name);
    }



}
