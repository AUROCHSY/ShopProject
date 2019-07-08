package com.example.demo.service.impl;

/*
 * Create by Jimmy on 2019/7/1 9:32 PM
 */

import com.example.demo.dao.CommodityDao;
import com.example.demo.entity.CommodityEntity;
import com.example.demo.entity.FlavorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.service.CommodityService;

import java.util.List;


@Service
public class CommodityServiceImpl implements CommodityService{

    @Autowired
    private CommodityDao commodityDao;

    @Override
    public List<CommodityEntity> search_commodity(String search_input){
        return commodityDao.search_commodity(search_input);
    }

    @Override
    public List<FlavorEntity> search_flavor_by_commodity_id(int commodity_id){
        return commodityDao.search_flavor_by_commodity_id(commodity_id);
    }
}
