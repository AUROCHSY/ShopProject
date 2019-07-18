package com.example.demo.service;

/*
 * Create by Jimmy on 2019/7/1 9:32 PM
 */


import com.example.demo.entity.CommodityEntity;
import com.example.demo.entity.EvatuateEntity;
import com.example.demo.entity.FlavorEntity;
import com.example.demo.entity.SecondCategoryEntity;

import java.util.List;

public interface CommodityService {

    int search_commodity_id_by_cname(String cname);

    List<CommodityEntity> search_commodity(String search_input);

    CommodityEntity search_commodity_by_commodity_id(int commodity_id);

    List<FlavorEntity> search_flavor_by_commodity_id(int commodity_id);

    List<CommodityEntity> search_commodity_entity_by_second_category_id(int second_category_id);

    List<SecondCategoryEntity> search_second_category_entity_by_first_category_id(int fisrt_category_id);

    List<CommodityEntity> sort_commodity_by_createtime();

    List<EvatuateEntity> search_evaluate_by_commodity_id(int commodity_id);
}
