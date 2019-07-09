package com.example.demo.service;/*
* create by yrh on 2019/7/4 9:06
*/

import com.example.demo.entity.*;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EndPagesService {
/*用户管理============================*/
    List<UserEntity> searchUser(String key);//根据用户名搜索用户
    List<UserEntity> showAllUser();//展示所有用户
    int updateUser(String username,String sex,String email,String phone,int user_id);//更新用户信息
//    int deleteUser(int user_id);//删除用户-不用做
//    int deleteCart(int user_id);//删除购物车

/*种类(目录)管理=====================*/
    List<CategoryEntity> showAllCategory();
    int updateCategory(int sc_id,String fc_name,String sc_name);//参数:二级目录Id，一级目录名称，二级目录名称
    int deleteSecondCategory(int sc_id);//删除二级目录
    String getFcIdByName(String fc_name);//获取一级目录id
    int addFcCategory(String fc_name);//一级目录表中新增目录种类
    String getScIdByName(String sc_name);//查询二级目录里是否存在指定二级目录名称的Id
    int addScCategory(int fc_id, String sc_name);//二级目录表中新增目录(种类)信息
    List<CategoryEntity> searchCategory(String c_key);    //依据一级/二级目录名搜索种类(一二级目录)信息

/*品牌管理*/
    //根据品牌名名搜索用户
    List<BrandEntity> searchBrand(String key);
    //返回所有品牌列表
    List<BrandEntity> getAllBrand();
    //更新品牌信息
    int updateBrand(String name,String contact,String phone,String address,int brand_id);
    //删除品牌
    int deleteBrand(int brand_id);
    //根据品牌名获取其id
    String getBrandIdByName(String name);//如果为空则返回Null,否则返回id(记得要转为int)
    //新增品牌
    int addBrand(String name,String contact,String phone,String email,String address);

/*商品管理=====================================*/
    //返回所有商品列表(商品名，品牌名，二级目录名(种类))
    List<GoodsEntity_yrh> getAllGoods();
    //搜索商品
    List<GoodsEntity_yrh> searchGoods(String cname);
    //编辑(更新)商品
    int updateCommodity(int brand_id,int second_category_id,String cname,double promotional_price,double original_price,String description,int commodity_id);
    //删除商品
    int deleteCommodity(int commodity_id);
    //添加商品(在这之前需要先获取对应品牌的id) //后期可添加添加图片功能
    int addCommodity(String cname,int brand_id,int second_category_id,double promotional_price,double original_price,String description);

    //根据品牌id、二级目录id、商品名获取商品id
    String getCommodityIdByName(int brand_id,int second_category_id,String cname);//如果为空则返回Null,否则返回id(记得要转为int)
    //根据口味名获取口味id
    String getFlavorIdByName(String flavor_name);//如果为空则返回Null,否则返回id(记得要转为int)
    //获取全部口味
    List<FlavorEntity> getAllFlavor();

    //根据flavor_id和commodity_id查询tb_flavor_commodity是否存在记录
    String  getFlavorCommodity_Stock(int commodity_id,int flavor_id);//如果为空则返回Null,否则返回id(记得要转为int)

    //根据flavor_commodity_id更新tb_flavor_commodity中的库存
    int updateStock(int stock,int flavor_commodity_id);

    //tb_flavor_commodity插入新数据
    int addFlavorCommodity(int commodity_id,int flavor_id,int stock);

    //tb_flavor_commodity更新口味id 和 库存stock
    int updateFlavorIdAndStock(int flavor_id,int stock,int flavor_commodity_id);

/*管理员登录=====================================================*/
//用户名密码验证，返回admin_id
List<AdminEntity_yrh> admin_login(String admin_name, String password);

/*订单管理=================================================*/
// 整合订单表tb_order,订单详情表tb_order_detail,商品表commodity_id，
// 返回订单id,订单状态，订单详情id,商品数量，商品名称(有多个，字符串/数组的形式？)
    List<OrderGroupEntity_yrh> getAllOrderGroup();

    //编辑订单状态
    int updateOrder(int state,String order_id);
}
