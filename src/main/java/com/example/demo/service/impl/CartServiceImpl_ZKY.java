package com.example.demo.service.impl;

import com.example.demo.dao.CartDao_ZKY;
import com.example.demo.dao.CommodityDao;
import com.example.demo.service.CartService_ZKY;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Create by Jimmy on 2019/7/7 10:44 AM
 */
@Service
public class CartServiceImpl_ZKY implements CartService_ZKY {

    @Autowired
    private CartDao_ZKY cartDao_zky;
    @Autowired
    private CommodityDao commodityDao;

    @Override
    public int search_flavor_id_by_flavor_name(String flavor_name){
        return cartDao_zky.search_flavor_id_by_flavor_name(flavor_name);
    }

    @Override
    public int insert_cart(int user_id, String flavor_name, String cname, int quantity){
        int flavor_id = cartDao_zky.search_flavor_id_by_flavor_name(flavor_name);
        int commodity_id = commodityDao.search_commodity_id_by_cname(cname);

        if (flavor_id!=0 && commodity_id!=0){//如果口味和商品都存在,插入成功
            cartDao_zky.insert_cart(user_id,flavor_id,commodity_id,quantity);
            return 1;
        }
        //如果口味或商品不存在，则插入失败
        else return 0;
    }
    @Override
    public int get_quantity(int cart_id){
        return cartDao_zky.get_quantity(cart_id);
    }


}
