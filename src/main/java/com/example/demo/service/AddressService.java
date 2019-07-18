package com.example.demo.service;

/*
 * Create by Jimmy on 2019/7/2 3:31 PM
 */

import com.example.demo.entity.AddressEntity;

import java.util.List;

public interface AddressService {
    //查找user_id下的全部address
    List<AddressEntity> search_address_by_user_id(int user_id);

    //增加，需要根据外键user_id来增加
    int insert_address(int user_id, String full_address, String phone, String name);

    //删除，直接将主键address_id删除
    int delete_address(int address_id);

    //修改，不需要user_id，直接根据主键address_id更新
    int update_address(int address_id, String full_address, String phone, String name);

    //通过address_id得到
    AddressEntity get_address_by_id(int address_id);
}
