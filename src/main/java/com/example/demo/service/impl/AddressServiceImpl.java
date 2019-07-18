package com.example.demo.service.impl;

import com.example.demo.dao.AddressDao;
import com.example.demo.entity.AddressEntity;
import com.example.demo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Create by Jimmy on 2019/7/2 3:32 PM
 */


@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressDao addressDao;

    //搜索
    @Override
    public List<AddressEntity> search_address_by_user_id(int user_id){
        return addressDao.search_address_by_user_id(user_id);
    }

    //增添
    @Override
    public int insert_address(int user_id, String full_address, String phone, String name){
        return addressDao.insert_address(user_id, full_address, phone, name);
    }

    //删除
    @Override
    public int delete_address(int address_id){
        return addressDao.delete_address(address_id);//1表示删除成功
    }

    //修改
    @Override
    public int update_address(int address_id, String full_address, String phone, String name){
        return addressDao.update_address(address_id, full_address, phone, name);
    }

    @Override
    public AddressEntity get_address_by_id(int address_id){
        return addressDao.get_address_by_id(address_id);
    }

}
