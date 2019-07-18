package com.example.demo.service.impl;

/*
 * Create by Jimmy on 2019/7/1 9:32 PM
 */

import com.example.demo.dao.CommodityDao;
import com.example.demo.entity.CommodityEntity;
import com.example.demo.entity.EvatuateEntity;
import com.example.demo.entity.FlavorEntity;
import com.example.demo.entity.SecondCategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.service.CommodityService;

import java.util.List;


@Service
public class CommodityServiceImpl implements CommodityService{

    @Autowired
    private CommodityDao commodityDao;

    @Override
    public int search_commodity_id_by_cname(String cname){
        return commodityDao.search_commodity_id_by_cname(cname);
    }

    @Override
    public List<CommodityEntity> search_commodity(String search_input){
        return commodityDao.search_commodity(search_input);
    }

    @Override
    public CommodityEntity search_commodity_by_commodity_id(int commodity_id){
        return commodityDao.search_commodity_by_commodity_id(commodity_id);
    }

    @Override
    public List<FlavorEntity> search_flavor_by_commodity_id(int commodity_id){
        return commodityDao.search_flavor_by_commodity_id(commodity_id);
    }

    @Override
    public List<SecondCategoryEntity> search_second_category_entity_by_first_category_id(int first_category_id){
        return commodityDao.search_second_category_entity_by_first_category_id(first_category_id);
    }

    @Override
    public List<CommodityEntity> search_commodity_entity_by_second_category_id(int second_category_id){
        return commodityDao.search_commodity_entity_by_second_category_id(second_category_id);
    }

    @Override
    public List<CommodityEntity> sort_commodity_by_createtime(){
        return commodityDao.sort_commodity_by_createtime();
    }

    @Override
    public List<EvatuateEntity> search_evaluate_by_commodity_id(int commodity_id){
        return commodityDao.search_evaluate_by_commodity_id(commodity_id);
    }


}
