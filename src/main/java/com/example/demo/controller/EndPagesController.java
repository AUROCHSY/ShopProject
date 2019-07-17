package com.example.demo.controller;/*
* create by yrh on 2019/7/4 9:17
*/

import com.example.demo.entity.*;
import com.example.demo.service.EndPagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EndPagesController {
    @Autowired
    private EndPagesService endpagesservice;

    /*主页,全部数据展示====================================================*/
    /*各功能的列表数据初始化都在这里=========================================*/
    /*后台登录页*/
    @RequestMapping(value = {"/EndPagesLogin"})//跳转到登录页
    public String EndPagesLogin() {
        return "EndPages/endpagelogin_yrh";
    }

    /*管理台主页==各功能数据初始化=============================================*/
    @RequestMapping(value = {"/EndPages"})//默认展示用户管理
    public String index(HttpServletRequest request, Model model) {
            /*处理管理员登录请求*/
            String admin_name = (String) request.getParameter("admin_name");
            String password = (String) request.getParameter("password");
            List<AdminEntity_yrh> adminlist = endpagesservice.admin_login(admin_name, password);
            System.out.println(adminlist);
            if (adminlist.size() == 0) {
                System.out.println("用户名或密码错误！");
                model.addAttribute("login_response", "用户名或密码错误！");
                return "EndPages/endpagelogin_yrh";
            } else {
                System.out.println("登录成功");
            }

            /*返回实体类到主页*/
            List<UserEntity> userlist = endpagesservice.showAllUser();//展示所有用户
            List<CategoryEntity> categorylist = endpagesservice.showAllCategory();//展示所有目录
            List<BrandEntity> brandlist = endpagesservice.getAllBrand();
            List<GoodsEntity_yrh> goodslist=endpagesservice.getAllGoods();
            List<OrderGroupEntity_yrh> orderlist=endpagesservice.getAllOrderGroup();

            model.addAttribute("userlist", userlist);//所有用户的集合list
            model.addAttribute("categorylist", categorylist);
            model.addAttribute("brandlist", brandlist);
            model.addAttribute("goodslist",goodslist);
            model.addAttribute("orderlist",orderlist);

            //当前登录用户实体
            model.addAttribute("adminlist",adminlist);


            /*返回一个每个对象为"一个order聚合类 和 一个order的所有商品"的list*/
            List<OrderAllInOneEntity_yrh> order_all_in_one_list=new ArrayList<>();
            OrderAllInOneEntity_yrh order_all_in_one;
            for(int i=0;i<orderlist.size();i++){
                order_all_in_one=new OrderAllInOneEntity_yrh();//该对象变量每次重新赋值前都要重新初始化
                order_all_in_one.orderOne=orderlist.get(i);//一个订单聚合类对象
                String one_order_cname=orderlist.get(i).cname_group;//一个订单的全部商品：用逗号连接的字符串
                String[] sgroup=one_order_cname.split(",");//每个订单的商品切割后存到一个临时数组中
                order_all_in_one.cnameOneLineArr=sgroup;//一个订单的全部商品：一维数组

                order_all_in_one_list.add(order_all_in_one);
            }

            model.addAttribute("order_all_in_one_list",order_all_in_one_list);

            return "EndPages/index";
}
    /*管理台主页：用于返回跳转==============================================*/
    @RequestMapping(value = {"/EndPagesIndex"})
    public String indexForReturn(HttpServletRequest request, Model model) {
            /*返回实体类到主页*/
        List<UserEntity> userlist = endpagesservice.showAllUser();//展示所有用户
        List<CategoryEntity> categorylist = endpagesservice.showAllCategory();//展示所有目录
        List<BrandEntity> brandlist = endpagesservice.getAllBrand();
        List<GoodsEntity_yrh> goodslist=endpagesservice.getAllGoods();
        List<OrderGroupEntity_yrh> orderlist=endpagesservice.getAllOrderGroup();

        model.addAttribute("userlist", userlist);//所有用户的集合list
        model.addAttribute("categorylist", categorylist);
        model.addAttribute("brandlist", brandlist);
        model.addAttribute("goodslist",goodslist);
        model.addAttribute("orderlist",orderlist);

            /*返回一个每个对象为"一个order聚合类 和 一个order的所有商品"的list*/
        List<OrderAllInOneEntity_yrh> order_all_in_one_list=new ArrayList<>();
        OrderAllInOneEntity_yrh order_all_in_one;
        for(int i=0;i<orderlist.size();i++){
            order_all_in_one=new OrderAllInOneEntity_yrh();//该对象变量每次重新赋值前都要重新初始化
            order_all_in_one.orderOne=orderlist.get(i);//一个订单聚合类对象
            String one_order_cname=orderlist.get(i).cname_group;//一个订单的全部商品：用逗号连接的字符串
            String[] sgroup=one_order_cname.split(",");//每个订单的商品切割后存到一个临时数组中
            order_all_in_one.cnameOneLineArr=sgroup;//一个订单的全部商品：一维数组

            order_all_in_one_list.add(order_all_in_one);
        }
        model.addAttribute("order_all_in_one_list",order_all_in_one_list);
        return "EndPages/index";
    }

    //主页上退出按钮的路由
    @RequestMapping(value = {"/endpagesexit"})
    public String endpagesexit(){
        System.out.println("退出成功！");
        return "EndPages/endpagelogin_yrh";
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
        int user_state = Integer.parseInt(request.getParameter("user_state"));

        int result = endpagesservice.updateUser(username, sex, email, phone,user_state, user_id);//更新用户信息

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

            //<label>list们
            List<UserEntity> userlist = endpagesservice.showAllUser();//展示所有用户
            List<CategoryEntity> categorylist = endpagesservice.showAllCategory();//展示所有目录
            List<BrandEntity> brandlist = endpagesservice.getAllBrand();
            List<GoodsEntity_yrh> goodslist=endpagesservice.getAllGoods();
            List<OrderGroupEntity_yrh> orderlist=endpagesservice.getAllOrderGroup();

            model.addAttribute("userlist", userlist);//所有用户的集合list
            model.addAttribute("categorylist", categorylist);
            model.addAttribute("brandlist", brandlist);
            model.addAttribute("goodslist",goodslist);
            model.addAttribute("orderlist",orderlist);

            /*返回一个每个对象为"一个order聚合类 和 一个order的所有商品"的list*/
            List<OrderAllInOneEntity_yrh> order_all_in_one_list=new ArrayList<>();
            OrderAllInOneEntity_yrh order_all_in_one;
            for(int i=0;i<orderlist.size();i++){
                order_all_in_one=new OrderAllInOneEntity_yrh();//该对象变量每次重新赋值前都要重新初始化
                order_all_in_one.orderOne=orderlist.get(i);//一个订单聚合类对象
                String one_order_cname=orderlist.get(i).cname_group;//一个订单的全部商品：用逗号连接的字符串
                String[] sgroup=one_order_cname.split(",");//每个订单的商品切割后存到一个临时数组中
                order_all_in_one.cnameOneLineArr=sgroup;//一个订单的全部商品：一维数组

                order_all_in_one_list.add(order_all_in_one);
            }

            model.addAttribute("order_all_in_one_list",order_all_in_one_list);

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

        //<label>list们
        List<UserEntity> userlist = endpagesservice.showAllUser();//展示所有用户
        List<CategoryEntity> categorylist = endpagesservice.showAllCategory();//展示所有目录
        List<BrandEntity> brandlist = endpagesservice.getAllBrand();
        List<GoodsEntity_yrh> goodslist=endpagesservice.getAllGoods();
        List<OrderGroupEntity_yrh> orderlist=endpagesservice.getAllOrderGroup();

        model.addAttribute("userlist", userlist);//所有用户的集合list
        model.addAttribute("categorylist", categorylist);
        model.addAttribute("brandlist", brandlist);
        model.addAttribute("goodslist",goodslist);
        model.addAttribute("orderlist",orderlist);
                    /*返回一个每个对象为"一个order聚合类 和 一个order的所有商品"的list*/
        List<OrderAllInOneEntity_yrh> order_all_in_one_list=new ArrayList<>();
        OrderAllInOneEntity_yrh order_all_in_one;
        for(int i=0;i<orderlist.size();i++){
            order_all_in_one=new OrderAllInOneEntity_yrh();//该对象变量每次重新赋值前都要重新初始化
            order_all_in_one.orderOne=orderlist.get(i);//一个订单聚合类对象
            String one_order_cname=orderlist.get(i).cname_group;//一个订单的全部商品：用逗号连接的字符串
            String[] sgroup=one_order_cname.split(",");//每个订单的商品切割后存到一个临时数组中
            order_all_in_one.cnameOneLineArr=sgroup;//一个订单的全部商品：一维数组

            order_all_in_one_list.add(order_all_in_one);
        }

        model.addAttribute("order_all_in_one_list",order_all_in_one_list);
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

            //<label>list们
            List<UserEntity> userlist = endpagesservice.showAllUser();//展示所有用户
            List<CategoryEntity> categorylist = endpagesservice.showAllCategory();//展示所有目录
            List<BrandEntity> brandlist = endpagesservice.getAllBrand();
            List<GoodsEntity_yrh> goodslist=endpagesservice.getAllGoods();
            List<OrderGroupEntity_yrh> orderlist=endpagesservice.getAllOrderGroup();

            model.addAttribute("userlist", userlist);//所有用户的集合list
            model.addAttribute("categorylist", categorylist);
            model.addAttribute("brandlist", brandlist);
            model.addAttribute("goodslist",goodslist);
            model.addAttribute("orderlist",orderlist);
                        /*返回一个每个对象为"一个order聚合类 和 一个order的所有商品"的list*/
            List<OrderAllInOneEntity_yrh> order_all_in_one_list=new ArrayList<>();
            OrderAllInOneEntity_yrh order_all_in_one;
            for(int i=0;i<orderlist.size();i++){
                order_all_in_one=new OrderAllInOneEntity_yrh();//该对象变量每次重新赋值前都要重新初始化
                order_all_in_one.orderOne=orderlist.get(i);//一个订单聚合类对象
                String one_order_cname=orderlist.get(i).cname_group;//一个订单的全部商品：用逗号连接的字符串
                String[] sgroup=one_order_cname.split(",");//每个订单的商品切割后存到一个临时数组中
                order_all_in_one.cnameOneLineArr=sgroup;//一个订单的全部商品：一维数组

                order_all_in_one_list.add(order_all_in_one);
            }

            model.addAttribute("order_all_in_one_list",order_all_in_one_list);
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

        //<label>list们
        List<UserEntity> userlist = endpagesservice.showAllUser();//展示所有用户
        List<CategoryEntity> categorylist = endpagesservice.showAllCategory();//展示所有目录
        List<BrandEntity> brandlist = endpagesservice.getAllBrand();
        List<GoodsEntity_yrh> goodslist=endpagesservice.getAllGoods();
        List<OrderGroupEntity_yrh> orderlist=endpagesservice.getAllOrderGroup();

        model.addAttribute("userlist", userlist);//所有用户的集合list
        model.addAttribute("categorylist", categorylist);
        model.addAttribute("brandlist", brandlist);
        model.addAttribute("goodslist",goodslist);
        model.addAttribute("orderlist",orderlist);

                    /*返回一个每个对象为"一个order聚合类 和 一个order的所有商品"的list*/
        List<OrderAllInOneEntity_yrh> order_all_in_one_list=new ArrayList<>();
        OrderAllInOneEntity_yrh order_all_in_one;
        for(int i=0;i<orderlist.size();i++){
            order_all_in_one=new OrderAllInOneEntity_yrh();//该对象变量每次重新赋值前都要重新初始化
            order_all_in_one.orderOne=orderlist.get(i);//一个订单聚合类对象
            String one_order_cname=orderlist.get(i).cname_group;//一个订单的全部商品：用逗号连接的字符串
            String[] sgroup=one_order_cname.split(",");//每个订单的商品切割后存到一个临时数组中
            order_all_in_one.cnameOneLineArr=sgroup;//一个订单的全部商品：一维数组

            order_all_in_one_list.add(order_all_in_one);
        }

        model.addAttribute("order_all_in_one_list",order_all_in_one_list);

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
        String flavor_name=(String) request.getParameter("flavor_name");//口味名称
        String stock=(String) request.getParameter("stock");//商品库存

        if (operationKind.equals("编辑")) {//只是跳转到编辑页
            model.addAttribute("commodity_id",commodity_id);
            model.addAttribute("cname", cname);
            model.addAttribute("name", name);
            model.addAttribute("sc_name", sc_name);


            List<CategoryEntity> categorylist = endpagesservice.showAllCategory();//将目录列表和品牌列表传递到编辑页
            List<BrandEntity> brandlist = endpagesservice.getAllBrand();
            List<FlavorEntity> flavorlist = endpagesservice.getAllFlavor();
            model.addAttribute("categorylist",categorylist);
            model.addAttribute("brandlist",brandlist);
            model.addAttribute("flavorlist",flavorlist);


            return "EndPages/foodedit";
        } else if (operationKind.equals("删除")) {//商品是订单表的主表，删除商品首先要先删除其产生的全部订单，不符合实际应用场景，所以这个不做了，在前端去掉了删除按钮
            endpagesservice.deleteCommodity(commodity_id);

            //<label>list们
            List<UserEntity> userlist = endpagesservice.showAllUser();//展示所有用户
            List<CategoryEntity> categorylist = endpagesservice.showAllCategory();//展示所有目录
            List<BrandEntity> brandlist = endpagesservice.getAllBrand();
            List<GoodsEntity_yrh> goodslist=endpagesservice.getAllGoods();
            List<OrderGroupEntity_yrh> orderlist=endpagesservice.getAllOrderGroup();

            model.addAttribute("userlist", userlist);//所有用户的集合list
            model.addAttribute("categorylist", categorylist);
            model.addAttribute("brandlist", brandlist);
            model.addAttribute("goodslist",goodslist);
            model.addAttribute("orderlist",orderlist);

                        /*返回一个每个对象为"一个order聚合类 和 一个order的所有商品"的list*/
            List<OrderAllInOneEntity_yrh> order_all_in_one_list=new ArrayList<>();
            OrderAllInOneEntity_yrh order_all_in_one;
            for(int i=0;i<orderlist.size();i++){
                order_all_in_one=new OrderAllInOneEntity_yrh();//该对象变量每次重新赋值前都要重新初始化
                order_all_in_one.orderOne=orderlist.get(i);//一个订单聚合类对象
                String one_order_cname=orderlist.get(i).cname_group;//一个订单的全部商品：用逗号连接的字符串
                String[] sgroup=one_order_cname.split(",");//每个订单的商品切割后存到一个临时数组中
                order_all_in_one.cnameOneLineArr=sgroup;//一个订单的全部商品：一维数组

                order_all_in_one_list.add(order_all_in_one);
            }

            model.addAttribute("order_all_in_one_list",order_all_in_one_list);

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

//        String flavor_name=(String) request.getParameter("flavor_name");//口味id
//        String stock=(String) request.getParameter("stock");//商品库存

        String brand_id=endpagesservice.getBrandIdByName(name);//由商品名查询数据库得商品id
        String sc_id=endpagesservice.getScIdByName(sc_name);//由二级目录名查询数据库得二级目录id

        double npromotional_price=Double.valueOf(promotional_price);//促销价和原价转为double类型
        double noriginal_price=Double.valueOf(original_price);
        int nbrand_id=Integer.parseInt(brand_id);//将商品和二级目录id转为int类型
        int nsc_id=Integer.parseInt(sc_id);

        //更新tb_commodity中的商品信息
        endpagesservice.updateCommodity(nbrand_id,nsc_id,cname,npromotional_price,noriginal_price,description,commodity_id);
        System.out.println("修改成功！");

        /*更新tb_flavor中的口味id flavor_id,库存数量stock*/
        //获取flavor_id
//        String flavor_id=endpagesservice.getFlavorIdByName(flavor_name);
//        int nflavor_id=Integer.parseInt(flavor_id);//将口味id转为Int类型
//        //根据flavor_id和commodity_id查询得到flavor_commodity_id
//        String flavor_commodity_id=endpagesservice.getFlavorCommodity_Stock(commodity_id,nflavor_id);
//        int  nflavor_commodity_id=Integer.parseInt(flavor_commodity_id);
//        //重置库存和口味id
//        int reset_stock=Integer.parseInt(stock);
//        endpagesservice.updateFlavorIdAndStock(nflavor_id,reset_stock,nflavor_commodity_id);

        //<label>list们
        List<UserEntity> userlist = endpagesservice.showAllUser();//展示所有用户
        List<CategoryEntity> categorylist = endpagesservice.showAllCategory();//展示所有目录
        List<BrandEntity> brandlist = endpagesservice.getAllBrand();
        List<GoodsEntity_yrh> goodslist=endpagesservice.getAllGoods();
        List<OrderGroupEntity_yrh> orderlist=endpagesservice.getAllOrderGroup();

        model.addAttribute("userlist", userlist);//所有用户的集合list
        model.addAttribute("categorylist", categorylist);
        model.addAttribute("brandlist", brandlist);
        model.addAttribute("goodslist",goodslist);
        model.addAttribute("orderlist",orderlist);

                    /*返回一个每个对象为"一个order聚合类 和 一个order的所有商品"的list*/
        List<OrderAllInOneEntity_yrh> order_all_in_one_list=new ArrayList<>();
        OrderAllInOneEntity_yrh order_all_in_one;
        for(int i=0;i<orderlist.size();i++){
            order_all_in_one=new OrderAllInOneEntity_yrh();//该对象变量每次重新赋值前都要重新初始化
            order_all_in_one.orderOne=orderlist.get(i);//一个订单聚合类对象
            String one_order_cname=orderlist.get(i).cname_group;//一个订单的全部商品：用逗号连接的字符串
            String[] sgroup=one_order_cname.split(",");//每个订单的商品切割后存到一个临时数组中
            order_all_in_one.cnameOneLineArr=sgroup;//一个订单的全部商品：一维数组

            order_all_in_one_list.add(order_all_in_one);
        }

        model.addAttribute("order_all_in_one_list",order_all_in_one_list);

        return "EndPages/index";

    }

    /*跳转到商品添加页*/
    @RequestMapping(value = {"/EndPages/commodityAdd"})//在详情页中编辑并更新数据库
    public String commodityAdd(HttpServletRequest request, HttpSession httpSession, Model model) {

        List<CategoryEntity> categorylist = endpagesservice.showAllCategory();//将全部目录和品牌传递到页面
        List<BrandEntity> brandlist = endpagesservice.getAllBrand();
        System.out.println("youtube");
        List<FlavorEntity> flavorlist=endpagesservice.getAllFlavor();
        System.out.println(flavorlist);
        model.addAttribute("categorylist", categorylist);
        model.addAttribute("brandlist", brandlist);
        model.addAttribute("flavorlist",flavorlist);
        System.out.println("you stupid");
        return "EndPages/foodadd";
    }

    /*商品添加(详情页中)*/
    @RequestMapping(value = {"/EndPages/commodityAddDetail"})//在详情页中编辑并更新数据库
    public String commodityAddDetail(HttpServletRequest request, Model model) {
//    public String commodityAddDetail(@RequestParam("file") MultipartFile file,HttpServletRequest request, Model model) {//图片上传功能

//        int commodity_id = Integer.parseInt(request.getParameter("commodity_id"));
        String cname = (String) request.getParameter("cname");//商品名
        String name=(String) request.getParameter("name");//品牌名,从下拉列表中选取
        String sc_name=(String) request.getParameter("sc_name");//二级目录名，从下拉列表中选取
        String flavor_name=(String) request.getParameter("flavor_name");//口味名称
        String stock=(String) request.getParameter("stock");//商品库存
        String promotional_price = (String) request.getParameter("promotional_price");//品牌名
        String original_price = (String) request.getParameter("original_price");//二级目录名(类型)
        String description = (String) request.getParameter("description");//二级目录名(类型)

        String brand_id=endpagesservice.getBrandIdByName(name);//由商品名查询数据库得商品id
        String sc_id=endpagesservice.getScIdByName(sc_name);//由二级目录名查询数据库得二级目录id

        double npromotional_price=Double.valueOf(promotional_price);//促销价和原价转为double类型
        double noriginal_price=Double.valueOf(original_price);
        int nbrand_id=Integer.parseInt(brand_id);//将商品和二级目录id转为int类型
        int nsc_id=Integer.parseInt(sc_id);

        /*往tb_commodity中添加商品*/
        String commodity_id=endpagesservice.getCommodityIdByName(nbrand_id,nsc_id,cname);//依据品牌id,二级目录id，商品名查询商品id
        if (commodity_id==null){//如果该商品原先没有则添加该商品
            endpagesservice.addCommodity(cname,nbrand_id,nsc_id,npromotional_price,noriginal_price,description);//添加商品
            commodity_id=endpagesservice.getCommodityIdByName(nbrand_id,nsc_id,cname);//获取插入商品后得到的其id
            System.out.println("成功添加商品！");
            model.addAttribute("commodity_response", "成功添加该商品!");
        }
        else {//否则只添加库存
            System.out.println("成功更新商品库存！");//但还是要更新库存
            model.addAttribute("commodity_response", "成功更新商品库存!");
        }
        /*更新口味商品表tb_flavor_commodity的库存*/

        //获取口味id
        String flavor_id=endpagesservice.getFlavorIdByName(flavor_name);
        int nflavor_id=Integer.parseInt(flavor_id);//将商品id,口味id,库存转为Int类型
        int ncommodity_id=Integer.parseInt(commodity_id);
        int nstock=Integer.parseInt(stock);

        //判断该商品_口味搭配是否已存在tb_flavor_commodity表中
        String flavor_commodity_id=endpagesservice.getFlavorCommodity_Stock(ncommodity_id,nflavor_id);
        if(flavor_commodity_id==null){//插入新记录
            endpagesservice.addFlavorCommodity(ncommodity_id,nflavor_id,nstock);
        }else{//只是更新已有记录的库存stock字段
            int nflavor_commodity_id=Integer.parseInt(flavor_commodity_id);
            endpagesservice.updateStock(nstock,nflavor_commodity_id);
        }

        //重新获取相关列表并返回该界面
        List<CategoryEntity> categorylist = endpagesservice.showAllCategory();//将全部目录、品牌、口味列表传递到页面
        List<BrandEntity> brandlist = endpagesservice.getAllBrand();
        List<FlavorEntity> flavorlist=endpagesservice.getAllFlavor();
        model.addAttribute("categorylist", categorylist);
        model.addAttribute("brandlist", brandlist);
        model.addAttribute("flavorlist",flavorlist);
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
/*订单管理==================================================*/
    //从主页跳转到订单编辑页
    @RequestMapping(value = {"/orderedit"})
    public String orderedit(HttpServletRequest request,  Model model) {
         int order_id = Integer.parseInt(request.getParameter("order_id"));
//        String state = (String) request.getParameter("state");//商品名

        model.addAttribute("order_id",order_id);
//        model.addAttribute("state", state);

        return "EndPages/orderedit";
    }

    //订单编辑详情页提交
    @RequestMapping(value = {"/orderdetialUpdate"})
    public String orderdetailEdit(HttpServletRequest request,  Model model) {
        int order_id =Integer.parseInt(request.getParameter("order_id"));
        String state = (String) request.getParameter("state");//商品名

        int nstate=Integer.parseInt(state);

        endpagesservice.updateOrder(nstate,order_id);

        //<label>list们
        List<UserEntity> userlist = endpagesservice.showAllUser();//展示所有用户
        List<CategoryEntity> categorylist = endpagesservice.showAllCategory();//展示所有目录
        List<BrandEntity> brandlist = endpagesservice.getAllBrand();
        List<GoodsEntity_yrh> goodslist=endpagesservice.getAllGoods();
        List<OrderGroupEntity_yrh> orderlist=endpagesservice.getAllOrderGroup();

        model.addAttribute("userlist", userlist);//所有用户的集合list
        model.addAttribute("categorylist", categorylist);
        model.addAttribute("brandlist", brandlist);
        model.addAttribute("goodslist",goodslist);
        model.addAttribute("orderlist",orderlist);

                    /*返回一个每个对象为"一个order聚合类 和 一个order的所有商品"的list*/
        List<OrderAllInOneEntity_yrh> order_all_in_one_list=new ArrayList<>();
        OrderAllInOneEntity_yrh order_all_in_one;
        for(int i=0;i<orderlist.size();i++){
            order_all_in_one=new OrderAllInOneEntity_yrh();//该对象变量每次重新赋值前都要重新初始化
            order_all_in_one.orderOne=orderlist.get(i);//一个订单聚合类对象
            String one_order_cname=orderlist.get(i).cname_group;//一个订单的全部商品：用逗号连接的字符串
            String[] sgroup=one_order_cname.split(",");//每个订单的商品切割后存到一个临时数组中
            order_all_in_one.cnameOneLineArr=sgroup;//一个订单的全部商品：一维数组

            order_all_in_one_list.add(order_all_in_one);
        }

        model.addAttribute("order_all_in_one_list",order_all_in_one_list);

        return "EndPages/index";
    }
}