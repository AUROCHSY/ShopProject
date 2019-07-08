package com.example.demo.service;

/*
 * Create by Jimmy on 2019/7/1 9:32 PM
 */


import com.example.demo.entity.CommodityEntity;
import com.example.demo.entity.FlavorEntity;

import java.util.List;

public interface CommodityService {

    List<CommodityEntity> search_commodity(String search_input);

    List<FlavorEntity> search_flavor_by_commodity_id(int commodity_id);
}
