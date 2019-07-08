package com.example.demo.controller;/*
* create by yrh on 2019/7/4 9:17
*/

import com.example.demo.entity.BrandEntity;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.GoodsEntity_yrh;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.EndPagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class EndPagesController {
    @Autowired
    private EndPagesService endpagesservice;

    /*主页,全部数据展示====================================================*/
    /*管理台主页*/
    @RequestMapping(value = {"/EndPages"})//默认展示用户管理
    public String index(HttpServletRequest request, HttpSession httpSession, Model model) {

        List<UserEntity> userlist = endpagesservice.showAllUser();//展示所有用户
        List<CategoryEntity> categorylist = endpagesservice.showAllCategory();//展示所有目录
        List<BrandEntity> brandlist = endpagesservice.getAllBrand();
        List<GoodsEntity_yrh> goodslist=endpagesservice.getAllGoods();

        model.addAttribute("userlist", userlist);//所有用户的集合list
        model.addAttribute("categorylist", categorylist);
        model.addAttribute("brandlist", brandlist);
        System.out.println("here!");
        model.addAttribute("goodslist",goodslist);
        System.out.println("I GOT GOODSLIST!");
        System.out.println(goodslist);
        return "EndPages/index";
    }
/*用户管理======================================*
      /*用户管理选项列表*/
//    @RequestMapping(value = {"/userlist"})//用户管理
//    public String showAllUser(HttpServletRequest request, HttpSession httpSession, Model model){
//
//        List<UserEntity> userlist  = endpagesservice.showAllUser();//展示所有用户
//        List<CategoryEntity> categorylist  = endpagesservice.showAllCategory();//展示所有目录
//        if(userlist != null){//集合非空
//            model.addAttribute("userlist",userlist);//所有用户的集合list
//            model.addAttribute("categorylist",categorylist);
//        }
//        else{//集合为空
//            System.out.println("数据库中没有用户");
//        }
//        return "EndPages/index";
//    }

    /*用户管理详情*/
    @RequestMapping(value = {"/userdetail"})//用户管理
    public String userManage(HttpServletRequest request, HttpSession httpSession, Model model) {//通过request传递参数

        List<UserEntity> userlist = endpagesservice.showAllUser();//展示所有用户

        if (userlist != null) {//集合非空
            model.addAttribute("userlist", userlist);//所有用户的集合list
        } else {//集合为空
            System.out.println("数据库中没有用户");
        }
        return "EndPages/userdetail";
    }

    /*用户信息更新*/
    @RequestMapping(value = {"/userdetailUpdate"})//用户管理
    public String userdetailUpdate(HttpServletRequest request, HttpSession httpSession, Model model) {
        //通过request获取前端userdetail.html的form表单的参数
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        String username = (String) request.getParameter("username");
        String sex = (String) request.getParameter("sex");
        String email = (String) request.getParameter("email");
        String phone = (String) request.getParameter("phone");

        int result = endpagesservice.updateUser(username, sex, email, phone, user_id);//更新用户信息

        List<UserEntity> userlist = endpagesservice.showAllUser();//展示所有用户
        model.addAttribute("userlist", userlist);
        return "EndPages/userdetail";

    }

    /*根据用户名搜索用户*/
    @RequestMapping(value = {"/userSearch"})
    public String userSearch(HttpServletRequest request, HttpSession httpSession, Model model) {
        String df = "用户名";
        String key = request.getParameter("key");
        System.out.println(key);
//        if(df.equals(key)) {
//            System.out.println("key为空");
//        }
        List<UserEntity> userlist = endpagesservice.searchUser(key);
        model.addAttribute("userlist", userlist);
        return "EndPages/userdetail";
    }
    /*用户删除-不用做*/
//    @RequestMapping(value = {"/userDelete"})//用户管理
//    public String userdelete(HttpServletRequest request, HttpSession httpSession, Model model){
//        int user_id = Integer.parseInt(request.getParameter("user_id"));
//
//        int cart_delete_result=endpagesservice.deleteCart(user_id);//删除用户购物车信息
//        int user_delete_result=endpagesservice.deleteUser(user_id);//删除用户信息
//
//        List<UserEntity> userlist  = endpagesservice.showAllUser();//展示所有用户
//        model.addAttribute("userlist",userlist);
//        return "EndPages/index";
//
//    }
    //=======================================================================================

/*种类(目录)管理======================================*/

    /*目录(种类)操作(编辑和删除)*/
    @RequestMapping(value = {"/categoryOperation"})//用户管理
    public String categoryOperarion(HttpServletRequest request, HttpSession httpSession, Model model) {//通过request传递参数

        String operationKind = (String) request.getParameter("submitInput");//获取操作类型
//        System.out.println(operationKind);

        int second_category_id = Integer.parseInt(request.getParameter("second_category_id"));
        System.out.println("heiheihei");
        System.out.println(request.getParameter("second_category_id"));

        String first_category_name = (String) request.getParameter("first_category_name");
        String second_category_name = (String) request.getParameter("second_category_name");


        if (operationKind.equals("编辑")) {//只是跳转到编辑页
            model.addAttribute("sc_id", second_category_id);
            model.addAttribute("fc_name", first_category_name);
            model.addAttribute("sc_name", second_category_name);

            return "EndPages/kindedit";
        } else if (operationKind.equals("删除")) {
            endpagesservice.deleteSecondCategory(second_category_id);

//            System.out.println("OOOOOOOOOOOOOOOSSSOOOOOOOOOOOOOOSSSSSSSSSSSSSSSSSSOOOOOOOOS");
//            System.out.println(second_category_id);

            //list们//todo:===============================================================================
            List<UserEntity> userlist = endpagesservice.showAllUser();//展示所有用户
            List<CategoryEntity> categorylist = endpagesservice.showAllCategory();//展示所有目录
            List<BrandEntity> brandlist = endpagesservice.getAllBrand();
            List<GoodsEntity_yrh>goodslist=endpagesservice.getAllGoods();

            model.addAttribute("userlist", userlist);//所有用户的集合list
            model.addAttribute("categorylist", categorylist);
            model.addAttribute("brandlist", brandlist);
            model.addAttribute("goodslist",goodslist);
            return "EndPages/index";
        } else {
            System.out.println("提交的操作类型错误！！");
            return "EndPages/index";
        }
    }


    /*目录(种类)编辑*/
    @RequestMapping(value = {"/categoryUpdate"})//在详情页中编辑并更新数据库
    public String categoryUpdate(HttpServletRequest request, HttpSession httpSession, Model model) {

        int second_category_id = Integer.parseInt(request.getParameter("sc_id"));
        String first_category_name = (String) request.getParameter("fc_name");
        String second_category_name = (String) request.getParameter("sc_name");

        endpagesservice.updateCategory(second_category_id, first_category_name, second_category_name);

        //list们//todo:===============================================================================
        List<UserEntity> userlist = endpagesservice.showAllUser();//展示所有用户
        List<CategoryEntity> categorylist = endpagesservice.showAllCategory();//展示所有目录
        List<BrandEntity> brandlist = endpagesservice.getAllBrand();
        List<GoodsEntity_yrh>goodslist=endpagesservice.getAllGoods();

        model.addAttribute("userlist", userlist);//所有用户的集合list
        model.addAttribute("categorylist", categorylist);
        model.addAttribute("brandlist", brandlist);
        model.addAttribute("goodslist",goodslist);
        return "EndPages/index";

    }

    /*目录(种类)添加*/
    @RequestMapping(value = {"/EndPages/categoryAdd"})//在详情页中编辑并更新数据库
    public String categoryAdd(HttpServletRequest request, HttpSession httpSession, Model model) {

        String fc_name = (String) request.getParameter("fc_name");
        String sc_name = (String) request.getParameter("sc_name");

        //查询tb_first_category是否存在当前输入的一级目录名
        String fc_id = endpagesservice.getFcIdByName(fc_name);
        if (fc_id == null) {
            endpagesservice.addFcCategory(fc_name);
            System.out.println("成功添加一级目录！");
            model.addAttribute("fc_response", "成功添加一级目录!");
            fc_id = endpagesservice.getFcIdByName(fc_name);//获取插入新一级目录后得到的其id

        } else {
            System.out.println("该一级类型已存在！");
            model.addAttribute("fc_response", "该一级类型已存在！");
        }

//        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        String sc_id = endpagesservice.getScIdByName(sc_name);
//        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        int nfc_id = Integer.parseInt(fc_id);//将该id装为int类型
//        System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
        if (sc_id == null) {
//            System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
            endpagesservice.addScCategory(nfc_id, sc_name);
            System.out.println("成功添加二级目录！");
            model.addAttribute("sc_response", "成功添加二级目录！");
        } else {
            System.out.println("该二级类型已存在!");
            model.addAttribute("sc_response", "该二级类型已存在！");
            return "EndPages/kindadd";
        }
        return "EndPages/kindadd";

    }

    //搜索目录(种类)
        /*根据用户名搜索用户*/
    @RequestMapping(value = {"/categorySearch"})
    public String categorySearch(HttpServletRequest request, HttpSession httpSession, Model model) {
        String df = "类型名";
        String c_key = request.getParameter("c_key");
        System.out.println(c_key);
        if (df.equals(c_key)) {
            System.out.println("c_key为空");
        }
        List<CategoryEntity> categorylist = endpagesservice.searchCategory(c_key);
        System.out.println(categorylist);
        model.addAttribute("categorylist", categorylist);
        return "EndPages/categorydetail_yrh";
    }

/*品牌管理======================================*/

    /*品牌操作(列表页的编辑和删除)*/
    @RequestMapping(value = {"/brandOperation"})//用户管理
    public String brandOperarion(HttpServletRequest request, HttpSession httpSession, Model model) {//通过request传递参数

        String operationKind = (String) request.getParameter("submitInput");//获取操作类型
//        System.out.println(operationKind);

        int brand_id = Integer.parseInt(request.getParameter("brand_id"));
//        System.out.println("heiheihei");
//        System.out.println(request.getParameter("brand_id"));
        String name = (String) request.getParameter("name");//品牌名
        String contact = (String) request.getParameter("contact");//负责人
        String phone = (String) request.getParameter("phone");//电话
        String address = (String) request.getParameter("address");//供货地址

        if (operationKind.equals("编辑")) {//只是跳转到编辑页
            model.addAttribute("brand_id", brand_id);
            model.addAttribute("name", name);
            model.addAttribute("contact", contact);
            model.addAttribute("phone", phone);
            model.addAttribute("address", address);

            return "EndPages/brandedit";
        } else if (operationKind.equals("删除")) {
            endpagesservice.deleteBrand(brand_id);

            //list们//todo:===============================================================================
            List<UserEntity> userlist = endpagesservice.showAllUser();//展示所有用户
            List<CategoryEntity> categorylist = endpagesservice.showAllCategory();//展示所有目录
            List<BrandEntity> brandlist = endpagesservice.getAllBrand();
            List<GoodsEntity_yrh>goodslist=endpagesservice.getAllGoods();

            model.addAttribute("userlist", userlist);//所有用户的集合list
            model.addAttribute("categorylist", categorylist);
            model.addAttribute("brandlist", brandlist);
            model.addAttribute("goodslist",goodslist);
            return "EndPages/index";
        } else {
            System.out.println("提交的操作类型错误！！");
            return "EndPages/index";
        }
    }

    /*品牌编辑(在品牌详情页中)*/
    @RequestMapping(value = {"/brandUpdate"})//在详情页中编辑并更新数据库
    public String brandUpdate(HttpServletRequest request, HttpSession httpSession, Model model) {

        int brand_id = Integer.parseInt(request.getParameter("brand_id"));
        String name = (String) request.getParameter("name");//品牌名
        String contact = (String) request.getParameter("contact");//负责人
        String phone = (String) request.getParameter("phone");//电话
        String address = (String) request.getParameter("address");//供货地址

        endpagesservice.updateBrand(name,contact,phone,address,brand_id);

        //list们//todo:===============================================================================
        List<UserEntity> userlist = endpagesservice.showAllUser();//展示所有用户
        List<CategoryEntity> categorylist = endpagesservice.showAllCategory();//展示所有目录
        List<BrandEntity> brandlist = endpagesservice.getAllBrand();
        List<GoodsEntity_yrh>goodslist=endpagesservice.getAllGoods();

        model.addAttribute("userlist", userlist);//所有用户的集合list
        model.addAttribute("categorylist", categorylist);
        model.addAttribute("brandlist", brandlist);
        model.addAttribute("goodslist",goodslist);

        return "EndPages/index";

    }

    /*品牌添加*/
    @RequestMapping(value = {"/EndPages/brandAdd"})//在详情页中编辑并更新数据库
    public String brandAdd(HttpServletRequest request, HttpSession httpSession, Model model) {

        String name = (String) request.getParameter("name");//品牌名
        String contact = (String) request.getParameter("contact");//负责人
        String phone = (String) request.getParameter("phone");//电话
        String email = (String) request.getParameter("email");//邮箱
        String address = (String) request.getParameter("address");//供货地址

        //查询tb_brand是否存在当前输入的品牌名
        String brand_id = endpagesservice.getBrandIdByName(name);
        System.out.println(brand_id);
        if (brand_id == null) {
            endpagesservice.addBrand(name,contact, phone,email,address);
            System.out.println("成功添加该品牌！");
            model.addAttribute("brand_response", "成功添加该品牌!");
//            brand_id = endpagesservice.getFcIdByName(name);//获取插入新品牌后的其id

        } else {
            System.out.println("该品牌已存在！");
            model.addAttribute("brand_response", "该品牌已存在！");
        }
        return "EndPages/brandadd";
    }

    //搜索品牌
    @RequestMapping(value = {"/brandSearch"})
    public String brandSearch(HttpServletRequest request, HttpSession httpSession, Model model) {
        String df = "品牌名";
        String key = request.getParameter("key");
        System.out.println(key);
        if (df.equals(key)) {
            System.out.println("key为空");
        }
        List<BrandEntity> brandlist = endpagesservice.searchBrand(key);
//        System.out.println(brandlist);
        model.addAttribute("brandlist", brandlist);
        return "EndPages/branddetail_yrh";
    }
/*商品管理=================================================*/

    /*商品操作(列表页的编辑和删除)*/
    @RequestMapping(value = {"/commodityOperation"})//用户管理
    public String commodityOperarion(HttpServletRequest request, HttpSession httpSession, Model model) {//通过request传递参数

        String operationKind = (String) request.getParameter("submitInput");//获取操作类型

        int commodity_id = Integer.parseInt(request.getParameter("commodity_id"));
        String cname = (String) request.getParameter("cname");//商品名
        String name = (String) request.getParameter("name");//品牌名
        String sc_name = (String) request.getParameter("sc_name");//二级目录名(类型)

        if (operationKind.equals("编辑")) {//只是跳转到编辑页
            model.addAttribute("commodity_id",commodity_id);
            model.addAttribute("cname", cname);
            model.addAttribute("name", name);
            model.addAttribute("sc_name", sc_name);

            List<CategoryEntity> categorylist = endpagesservice.showAllCategory();//将目录列表和品牌列表传递到编辑页
            List<BrandEntity> brandlist = endpagesservice.getAllBrand();
            model.addAttribute("categorylist",categorylist);
            model.addAttribute("brandlist",brandlist);

            System.out.println("I got categorylist!");
            System.out.println(categorylist);
            System.out.println("I got brandlist!");
            System.out.println(brandlist);
            System.out.println("before turn to edit!!!!!!!!!!!!!!!");

            return "EndPages/foodedit";
        } else if (operationKind.equals("删除")) {//商品是订单表的主表，删除商品首先要先删除其产生的全部订单，不符合实际应用场景，所以这个不做了，在前端去掉了删除按钮
            endpagesservice.deleteCommodity(commodity_id);

            //list们//todo:===============================================================================
            List<UserEntity> userlist = endpagesservice.showAllUser();//展示所有用户
            List<CategoryEntity> categorylist = endpagesservice.showAllCategory();//展示所有目录
            List<BrandEntity> brandlist = endpagesservice.getAllBrand();
            List<GoodsEntity_yrh>goodslist=endpagesservice.getAllGoods();

            model.addAttribute("userlist", userlist);//所有用户的集合list
            model.addAttribute("categorylist", categorylist);
            model.addAttribute("brandlist", brandlist);
            model.addAttribute("goodslist",goodslist);
            return "EndPages/index";
        } else {
            System.out.println("提交的操作类型错误！！");
            return "EndPages/index";
        }
    }

    /*商品编辑(在商品详情页中)*/
    @RequestMapping(value = {"/commodityUpdate"})//在详情页中编辑并更新数据库
    public String commodityUpdate(HttpServletRequest request, HttpSession httpSession, Model model) {

        int commodity_id = Integer.parseInt(request.getParameter("commodity_id"));

        String cname = (String) request.getParameter("cname");//商品名
        String name=(String) request.getParameter("name");//品牌名,从下拉列表中选取
        String sc_name=(String) request.getParameter("sc_name");//二级目录名，从下拉列表中选取

        String promotional_price = (String) request.getParameter("promotional_price");//品牌名
        String original_price = (String) request.getParameter("original_price");//二级目录名(类型)
        String description = (String) request.getParameter("description");//二级目录名(类型)

        String brand_id=endpagesservice.getBrandIdByName(name);//由商品名查询数据库得商品id
        String sc_id=endpagesservice.getScIdByName(sc_name);//由二级目录名查询数据库得二级目录id

        double npromotional_price=Double.valueOf(promotional_price);//促销价和原价转为double类型
        double noriginal_price=Double.valueOf(original_price);
        int nbrand_id=Integer.parseInt(brand_id);//将商品和二级目录id转为int类型
        int nsc_id=Integer.parseInt(sc_id);

        endpagesservice.updateCommodity(nbrand_id,nsc_id,cname,npromotional_price,noriginal_price,description,commodity_id);
        System.out.println("修改成功！");
        //list们//todo:===============================================================================
        List<UserEntity> userlist = endpagesservice.showAllUser();//展示所有用户
        List<CategoryEntity> categorylist = endpagesservice.showAllCategory();//展示所有目录
        List<BrandEntity> brandlist = endpagesservice.getAllBrand();
        List<GoodsEntity_yrh>goodslist=endpagesservice.getAllGoods();

        model.addAttribute("userlist", userlist);//所有用户的集合list
        model.addAttribute("categorylist", categorylist);
        model.addAttribute("brandlist", brandlist);
        model.addAttribute("goodslist",goodslist);

        return "EndPages/index";

    }

    /*跳转到商品添加页*/
    @RequestMapping(value = {"/EndPages/commodityAdd"})//在详情页中编辑并更新数据库
    public String commodityAdd(HttpServletRequest request, HttpSession httpSession, Model model) {

        List<CategoryEntity> categorylist = endpagesservice.showAllCategory();//将全部目录和品牌传递到页面
        List<BrandEntity> brandlist = endpagesservice.getAllBrand();
        model.addAttribute("categorylist", categorylist);
        model.addAttribute("brandlist", brandlist);
        return "EndPages/foodadd";
    }
    /*商品添加(详情页中)*/
    @RequestMapping(value = {"/EndPages/commodityAddDetail"})//在详情页中编辑并更新数据库
    public String commodityAddDetail(HttpServletRequest request, HttpSession httpSession, Model model) {

//        int commodity_id = Integer.parseInt(request.getParameter("commodity_id"));
        String cname = (String) request.getParameter("cname");//商品名
        String name=(String) request.getParameter("name");//品牌名,从下拉列表中选取
        String sc_name=(String) request.getParameter("sc_name");//二级目录名，从下拉列表中选取

        String promotional_price = (String) request.getParameter("promotional_price");//品牌名
        String original_price = (String) request.getParameter("original_price");//二级目录名(类型)
        String description = (String) request.getParameter("description");//二级目录名(类型)

        String brand_id=endpagesservice.getBrandIdByName(name);//由商品名查询数据库得商品id
        String sc_id=endpagesservice.getScIdByName(sc_name);//由二级目录名查询数据库得二级目录id

        double npromotional_price=Double.valueOf(promotional_price);//促销价和原价转为double类型
        double noriginal_price=Double.valueOf(original_price);
        int nbrand_id=Integer.parseInt(brand_id);//将商品和二级目录id转为int类型
        int nsc_id=Integer.parseInt(sc_id);

        endpagesservice.addCommodity(cname,nbrand_id,nsc_id,npromotional_price,noriginal_price,description);

        //数据库里商品没有跟店铺关联，所以权且当做同样商品时可以重复出现的
        System.out.println("成功添加商品！");
        model.addAttribute("commodity_response", "成功添加该商品!");

        return "EndPages/foodadd";
    }

    //搜索商品
    @RequestMapping(value = {"/commoditySearch"})
    public String commoditySearch(HttpServletRequest request, HttpSession httpSession, Model model) {
        String df = "商品名";
        String key = request.getParameter("key");
        System.out.println(key);
        if (df.equals(key)) {
            System.out.println("key为空");
        }
        List<GoodsEntity_yrh> goodslist = endpagesservice.searchGoods(key);
//        System.out.println(brandlist);
        model.addAttribute("goodslist", goodslist);
        return "EndPages/goodsdetail_yrh";
    }
}