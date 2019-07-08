package com.example.demo.dao;/*
* create by yrh on 2019/7/4 8:29
*/
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.GoodsEntity_yrh;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.BrandEntity;
import com.example.demo.entity.GoodsEntity_yrh;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EndPagesDao {
/*用户管理===============================================================================*/
    //根据用户名搜索用户
    @Select(value = "select * from demo.tb_user where username REGEXP #{arg0} ")
    List<UserEntity> searchUser(String key);

    //展示所有用户
    @Select(value = "select * from demo.tb_user")
    List<UserEntity> showAllUser();

    //更新用户信息
    @Update(value="update demo.tb_user SET username=#{arg0},sex=#{arg1},email=#{arg2},phone=#{arg3} where user_id=#{arg4}")
    int updateUser(String username,String sex,String email,String phone,int user_id);

    //根据商品id找到全部口味
//    @Select(value = "select f.* from demo.tb_flavor_commodity fc" +
//            " inner join demo.tb_commodity c on c.commodity_id = fc.commodity_id" +
//            " inner join demo.tb_flavor f on f.flavor_id = fc.flavor_id" +
//            " where c.commodity_id = #{arg0} ")
//    List<FlavorEntity> search_flavor_by_commodity_id(int commodity_id);

    //删除购物车tb_cart 信息
    //    @Delete(value="delete demo.tb_cart，demo.tb_user from demo.tb_cart left join demo.tb_user
// on demo.tb_cart.user_id=demo.tb_user.user_id where demo.tb_cart.user_id=#{arg0}")
//    @Delete(value="delete from demo.tb_cart where user_id=#{arg0}")
//    int deleteCart(int user_id);

    //删除用户 tb_user 信息-不用做
//    @Delete(value="delete from demo.tb_user where user_id=#{arg0}")
//    int deleteUser(int user_id);
    //==========================================================================================

/*种类(目录)管理==========================================================================================================*/

    //展示全部种类(一二级目录)信息
    @Select(value="SELECT fc.first_category_id, sc.second_category_id, fc.first_category_name, sc.second_category_name"+
            " FROM demo.tb_first_category fc INNER JOIN demo.tb_second_category sc"+
            " ON fc.first_category_id = sc.first_category_id limit 9;")
    List<CategoryEntity> showAllCategory();

    //依据一级/二级目录名搜索种类(一二级目录)信息
    @Select(value="SELECT fc.first_category_id, sc.second_category_id, fc.first_category_name, sc.second_category_name"+
            " FROM demo.tb_first_category fc LEFT JOIN demo.tb_second_category sc"+
            " ON fc.first_category_id = sc.first_category_id" +
            " WHERE first_category_name REGEXP #{arg0} OR second_category_name REGEXP #{arg0};")
    List<CategoryEntity> searchCategory(String c_key);

    //编辑(更新)目录(种类)信息
    @Update(value="UPDATE demo.tb_first_category fc"+
            " INNER JOIN tb_second_category sc"+
            " ON fc.first_category_id = sc.first_category_id"+
            " SET fc.first_category_name=#{arg1},sc.second_category_name=#{arg2}"+
            " WHERE sc.second_category_id=#{arg0}")
    int updateCategory(int sc_id,String fc_name,String sc_name);//参数:二级目录Id，一级目录名称，二级目录名称

    //删除二级目录(种类)
    @Delete(value="delete from demo.tb_second_category where second_category_id=#{arg0}")
    int deleteSecondCategory(int sc_id);

    /*用于实现添加目录(种类)*/
    //查询一级目录里是否存在指定一级目录名称的Id
    @Select(value="select tb_first_category.first_category_id from demo.tb_first_category"+
            " where first_category_name=#{arg0}")
    String getFcIdByName(String fc_name);//如果为空则返回Null,否则返回id(记得要转为int)

    //一级目录表中新增目录种类
    @Insert(value="insert into demo.tb_first_category(" +
            "tb_first_category.first_category_name)" +
            " value(#{arg0})")
    int addFcCategory(String fc_name);

    //查询二级目录里是否存在指定二级目录名称的Id
    @Select(value="select tb_second_category.second_category_id from demo.tb_second_category"+
            " where second_category_name=#{arg0}")
    String getScIdByName(String sc_name);//如果为空则返回Null,否则返回id(记得要转为int)

    //二级目录表中新增目录(种类)信息
    @Insert(value = "insert into demo.tb_second_category"+
            "(tb_second_category.first_category_id,tb_second_category.second_category_name)" +
            " value(#{arg0}, #{arg1}) ")
    int addScCategory(int fc_id,String sc_name);

/*品牌管理===============================================================================================================*/

    //根据品牌名名搜索用户
    @Select(value = "select * from demo.tb_brand where name REGEXP #{arg0} ")
    List<BrandEntity> searchBrand(String key);

    //返回所有品牌列表
    @Select(value = "select * from demo.tb_brand")
    List<BrandEntity> getAllBrand();

    //更新品牌信息
    @Update(value="update demo.tb_brand SET name=#{arg0},contact=#{arg1},phone=#{arg2},address=#{arg3} where brand_id=#{arg4}")
    int updateBrand(String name,String contact,String phone,String address,int brand_id);

    //删除品牌
    @Delete(value="delete from demo.tb_brand where brand_id=#{arg0}")
    int deleteBrand(int brand_id);

    //根据品牌名获取其id
    @Select(value="select tb_brand.brand_id from demo.tb_brand"+
            " where name=#{arg0}")
    String getBrandIdByName(String name);//如果为空则返回Null,否则返回id(记得要转为int)

    //新增品牌
    @Insert(value = "insert into demo.tb_brand"+
            "(name,contact,phone,email,address)" +
            " value(#{arg0},#{arg1},#{arg2},#{arg3},#{arg4}) ")
    int addBrand(String name,String contact,String phone,String email,String address);

/*商品管理=====================================*/
    //返回所有商品列表(商品名，品牌名，二级目录名(种类))
    @Select(value = "SELECT tb_commodity.commodity_id,tb_commodity.cname,tb_brand.name,tb_second_category.second_category_name" +
            " FROM (tb_commodity LEFT JOIN tb_brand on tb_commodity.brand_id=tb_brand.brand_id)" +
            " LEFT JOIN tb_second_category on tb_commodity.second_category_id=tb_second_category.second_category_id")
    List<GoodsEntity_yrh> getAllGoods();
    //搜索商品
    @Select(value = "SELECT tb_commodity.commodity_id,tb_commodity.cname,tb_brand.name,tb_second_category.second_category_name" +
            " FROM (tb_commodity LEFT JOIN tb_brand on tb_commodity.brand_id=tb_brand.brand_id)" +
            " LEFT JOIN tb_second_category on tb_commodity.second_category_id=tb_second_category.second_category_id" +
            " where cname REGEXP #{arg0}")
    List<GoodsEntity_yrh> searchGoods(String cname);
    //编辑(更新)商品
    @Update(value="update demo.tb_commodity SET brand_id=#{arg0},second_category_id=#{arg1},cname=#{arg2},promotional_price=#{arg3},original_price=#{arg4},description=#{arg5} where commodity_id=#{arg6}")
    int updateCommodity(int brand_id,int second_category_id,String cname,double promotional_price,double original_price,String description,int commodity_id);
    //删除商品
    @Delete(value="delete from demo.tb_commodity where commodity_id=#{arg0}")
    int deleteCommodity(int commodity_id);

    //添加商品(在这之前需要先获取对应品牌的id) //后期可添加添加图片功能
    @Insert(value = "insert into demo.tb_commodity"+
            "(cname,brand_id,second_category_id,promotional_price,original_price,description,createtime)" +
            " value(#{arg0},#{arg1},#{arg2},#{arg3},#{arg4},#{arg5},now())")//now()为mysql中的函数，当前时间
    int addCommodity(String cname,int brand_id,int second_category_id,double promotional_price,double original_price,String description);
}
