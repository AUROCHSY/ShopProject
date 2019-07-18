package com.example.demo.controller;

/*
 * Create by Jimmy on 2019/7/7 12:56 PM
 */

import com.example.demo.entity.AddressEntity;
import com.example.demo.entity.CartVoEntity;
import com.example.demo.entity.CommodityEntity;
import com.example.demo.service.AddressService;
import com.example.demo.service.CartService_ZKY;
import com.example.demo.service.CommodityService;
import com.example.demo.service.ShopCartService_GYF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController_ZKY {

    @Autowired
    private AddressService addressService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private CartService_ZKY cartService_zky;
    @Autowired
    private ShopCartService_GYF shopCartService_gyf;

    //注意！！！返回一个cartList方便前端处理
    @RequestMapping(value = "/detail_pay1")        //detail.html ajax提交
    @ResponseBody
    public String detail_pay(String cname, double total_price, String img, double promotional_price, double original_price, String flavor_name, int quantity, HttpServletRequest request, HttpSession httpSession){
        if(httpSession.getAttribute("username")==null) return "FrontPages/login";   //安全检测

        //通过cname返回一个commodity_id,在返回一个commodityEntity
        int commodity_id = commodityService.search_commodity_id_by_cname(cname);
        CommodityEntity commodity = commodityService.search_commodity_by_commodity_id(commodity_id);

        //调用构造函数，创建一个CartVoEntity对象，放到一个list中
        CartVoEntity CVE = new CartVoEntity(cname, img, original_price, promotional_price, flavor_name, quantity);
        List<CartVoEntity> list = new ArrayList<>();
        list.add(CVE);


        //从httpsession中获得user_id
        int user_id = (int)httpSession.getAttribute("user_id");
        //获取dao层地址
        List<AddressEntity> address_list = addressService.search_address_by_user_id(user_id);//获得该user_id下的地址表

        //将数据传递到session中
        httpSession.setAttribute("address_list",address_list);
        httpSession.setAttribute("total_price",total_price);
        httpSession.setAttribute("single_commodity_list",list);

        return "success";

    }


    @RequestMapping(value = "/detail_pay2")        //detail.html ajax提交
    public String detail_pay2(HttpSession httpSession, Model model){
        if(httpSession.getAttribute("username")==null) return "FrontPages/login";   //安全检测

        //从session中获得数据
        List<AddressEntity> address_list = (List<AddressEntity>)httpSession.getAttribute("address_list");
        List<CartVoEntity> single_commodity_list = (List<CartVoEntity>)httpSession.getAttribute("single_commodity_list");
        double total_price = (double)httpSession.getAttribute("total_price");

        //将获得的参数放入model中，传入pay.html网页中
        model.addAttribute("address_list",address_list);
        model.addAttribute("cart_list",single_commodity_list);
        model.addAttribute("total_price",total_price);

        //将session中的参数移除
        httpSession.removeAttribute("address_list");
        httpSession.removeAttribute("single_commodity_list");
        httpSession.removeAttribute("total_price");


        int address_id = 1;
        AddressEntity addressEntity = addressService.get_address_by_id(address_id);
        model.addAttribute("address",addressEntity);

        return "FrontPages/pay";
    }


    @RequestMapping(value = "/detail_cart1")
    @ResponseBody
    public String detail_cart(String flavor_name, String cname, int quantity, HttpSession httpSession){
        if(httpSession.getAttribute("username")==null) return "FrontPages/login";   //安全检测

        //从httpsession中获得user_id
        int user_id = (int)httpSession.getAttribute("user_id");


        String get_name = shopCartService_gyf.get_cname(user_id,cname,flavor_name);
        if (get_name == null){//cart数据库没有该商品，则直接插入购物车
            //插入购物车成功返回1，失败返回0
            int tmp = cartService_zky.insert_cart(user_id, flavor_name, cname, quantity);
        }
        else{//cart有该商品，则需要更新购物车的quantity值
            //首先获取要更改的cart_id，再获取该cart_id下的原quantity，再相加之后更新quantity的值
            int cart_id = shopCartService_gyf.get_cart_id(user_id, cname, flavor_name);
            int original_quantity = cartService_zky.get_quantity(cart_id);
            int new_quantity = original_quantity + quantity;
            shopCartService_gyf.cart_commodity_quantity_change(user_id, cname, flavor_name, new_quantity);
        }

        return "success";
    }


}
