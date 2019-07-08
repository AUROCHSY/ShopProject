package com.example.demo.service.impl;/*
* create by yrh on 2019/7/4 9:09
*/

import com.example.demo.dao.EndPagesDao;
import com.example.demo.entity.BrandEntity;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.GoodsEntity_yrh;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.EndPagesService;
import org.springframework.stereotype.Service;
import com.example.demo.dao.UserDao;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


import java.util.List;

@Service
public class EndPagesServiceImpl implements EndPagesService {

    @Autowired
    private EndPagesDao endpagesdao;

/*用户管理======================================*/
    //根据用户名搜索用户
    @Override
    public List<UserEntity> searchUser(String key) {
        return endpagesdao.searchUser(key);
    }

    //展示所有用户
    @Override
    public List<UserEntity> showAllUser() {
        return endpagesdao.showAllUser();
    }

    //更新用户信息
    @Override
    public int updateUser(String username, String sex, String email, String phone, int user_id) {
        return endpagesdao.updateUser(username, sex, email, phone, user_id);
    }

/*种类(目录)管理============================================*/
    //展示所有种类(目录)信息
    @Override
    public List<CategoryEntity> showAllCategory(){
        return endpagesdao.showAllCategory();
    }

    //更新(编辑)种类(目录)信息
    @Override
    public int updateCategory(int sc_id,String fc_name,String sc_name) {//参数:二级目录Id，一级目录名称，二级目录名称
        return endpagesdao.updateCategory(sc_id,fc_name,sc_name);
    }

    //删除二级目录
    @Override
    public int deleteSecondCategory(int sc_id){
        return endpagesdao.deleteSecondCategory(sc_id);
    }
    //获取一级目录id
    @Override
    public String getFcIdByName(String fc_name){
        return  endpagesdao.getFcIdByName(fc_name);
    }

    //一级目录表中新增目录种类
    @Override
    public int addFcCategory(String fc_name){
        return  endpagesdao.addFcCategory(fc_name);
    }

    //查询二级目录里是否存在指定二级目录名称的Id
    @Override
    public String getScIdByName(String sc_name){
        return  endpagesdao.getScIdByName(sc_name);
    }

    //二级目录表中新增目录(种类)信息
    @Override
    public int addScCategory(int fc_id, String sc_name){
        return  endpagesdao.addScCategory(fc_id,sc_name);
    }

    //依据一级/二级目录名搜索种类(一二级目录)信息
    @Override
    public List<CategoryEntity> searchCategory(String c_key){
        return endpagesdao.searchCategory(c_key);
    }

/*品牌管理=======================================*/
    //根据品牌名名搜索用户
    @Override
    public List<BrandEntity> searchBrand(String key){
        return endpagesdao.searchBrand(key);
    }
    //返回所有品牌列表
    @Override
    public List<BrandEntity> getAllBrand(){
        return endpagesdao.getAllBrand();
    }
    //更新品牌信息
    @Override
    public int updateBrand(String name,String contact,String phone,String address,int brand_id){
        return endpagesdao.updateBrand(name,contact,phone,address,brand_id);
    }
    //删除品牌
    @Override
    public int deleteBrand(int brand_id){
        return endpagesdao.deleteBrand(brand_id);
    }
    //根据品牌名获取其id
    @Override
    public String getBrandIdByName(String name){//如果为空则返回Null,否则返回id(记得要转为int)
        return endpagesdao.getBrandIdByName(name);
    }
    //新增品牌
    @Override
    public int addBrand(String name,String contact,String phone,String email,String address){
        return endpagesdao.addBrand(name,contact,phone,email,address);
    }
    /*商品管理=====================================*/
    //返回所有商品列表(商品名，品牌名，二级目录名(种类))
    @Override
    public List<GoodsEntity_yrh> getAllGoods(){
        return endpagesdao.getAllGoods();
    }
    //搜索商品
    @Override
    public List<GoodsEntity_yrh> searchGoods(String cname){
        return endpagesdao.searchGoods(cname);
    }
    //编辑(更新)商品
    @Override
    public int updateCommodity(int brand_id,int second_category_id,String cname,double promotional_price,double original_price,String description,int commodity_id){
        return endpagesdao.updateCommodity(brand_id,second_category_id,cname,promotional_price,original_price,description,commodity_id);
    }
    //删除商品
    @Override
    public int deleteCommodity(int commodity_id){
        return endpagesdao.deleteCommodity(commodity_id);
    }

    //添加商品(在这之前需要先获取对应品牌的id) //后期可添加添加图片功能
    @Override
    public int addCommodity(String cname,int brand_id,int second_category_id,double promotional_price,double original_price,String description){
        return endpagesdao.addCommodity(cname,brand_id,second_category_id,promotional_price,original_price,description);
    }
//    //删除用户-不用做
//    @Override
//    public int deleteUser(int user_id) {
//        return endpagesdao.deleteUser(user_id);
//    }
//
//    //删除购物车
//    @Override
//    public int deleteCart(int user_id) {
//        return endpagesdao.deleteCart(user_id);
//    }
}