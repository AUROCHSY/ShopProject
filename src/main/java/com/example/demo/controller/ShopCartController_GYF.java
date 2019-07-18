package com.example.demo.controller;


import com.example.demo.entity.*;
import com.example.demo.service.AddressService;
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
public class ShopCartController_GYF { //2019.7.3 22:20
    @Autowired
    private ShopCartService_GYF shopCartService_gyf;

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/shopcart_index")
    public String shopcart_index(HttpServletRequest request, HttpSession httpSession, Model model) {
        if(httpSession.getAttribute("username")==null) return "FrontPages/login";   //安全检测

        int user_id = (int)httpSession.getAttribute("user_id");
        List<CartVoEntity> tmp = shopCartService_gyf.get_cartList(user_id); //需要替换成session中的user_id
        model.addAttribute("cartList", tmp);

        return "FrontPages/shopcart";
    }



    //goto_detail和goto_detail2一起调用
    //goto_detail和goto_detail2一起调用
    //goto_detail和goto_detail2一起调用
    @RequestMapping(value = "/goto_detail") //用户点击购物车中商品的图片或文字，跳转到相应的商品详情detail页面
    @ResponseBody
    public String goto_detail(String cname, HttpServletRequest httpServletRequest, HttpSession httpSession, Model model) {

        int commodity_id = commodityService.search_commodity_id_by_cname(cname);

        //获取dao层的数据
        CommodityEntity commodity = commodityService.search_commodity_by_commodity_id(commodity_id);
        List<FlavorEntity> flavor = commodityService.search_flavor_by_commodity_id(commodity_id);
        List<EvatuateEntity> evaluate = commodityService.search_evaluate_by_commodity_id(commodity_id);

        //将dao层返回的数据传递给session
        httpSession.setAttribute("detail_commodity",commodity);
        httpSession.setAttribute("detail_flavor",flavor);
        httpSession.setAttribute("detail_evaluate",evaluate);


        return "happy!"; //返回给ajax的json，没有用途 ,之后前端ajax在通过href跳转到下面的/goto_detail2，跳转新页面
    }

    @RequestMapping(value = "/goto_detail2")
    public String detail(Model model, HttpSession httpSession) {
        //先从session中获得参数
        CommodityEntity commodity = (CommodityEntity)httpSession.getAttribute("detail_commodity");
        List<FlavorEntity> flavor = (List<FlavorEntity>)httpSession.getAttribute("detail_flavor");
        List<EvatuateEntity> evaluate = (List<EvatuateEntity>)httpSession.getAttribute("detail_evaluate");

        //将获得的参数放入model中，传入detail.html网页中
        model.addAttribute("search_result",commodity);//搜索点击商品
        model.addAttribute("flavor_result",flavor);//搜索点击商品的口味
        model.addAttribute("evaluate_result",evaluate);//搜索点击商品的全部评价

        //将session中的参数移除
        httpSession.removeAttribute("detail_commodity");
        httpSession.removeAttribute("detail_flavor");
        httpSession.removeAttribute("detail_evaluate");

        return "FrontPages/detail";
    }






    @RequestMapping(value = "/deleteCommodity") //删除商品，分两步：第一步找到需要删除的cart_id，第二步进行删除
    @ResponseBody
    public String deleteCommodity(String cname, String flavor_name, HttpSession httpSession, Model model) {
        int user_id = (int)httpSession.getAttribute("user_id");
        shopCartService_gyf.delete_cart(user_id, cname, flavor_name);

        return "success";
    }

    @RequestMapping(value = "/deleteCheckedCommodities")
    @ResponseBody
    public String deleteCheckedCommodities(String cnames_string, String flavor_names_string, HttpSession httpSession) {
        String[] cnames = cnames_string.split(",");
        String[] flavor_names = flavor_names_string.split(",");

        int len = cnames.length;
        for(int i=0;i<len;i++) {
            System.out.println(cnames[i]);
            System.out.println(flavor_names[i]);
            int user_id = (int)httpSession.getAttribute("user_id");
            shopCartService_gyf.delete_cart(user_id, cnames[i], flavor_names[i]);
        }

        return "success";
    }

    //修改购物车商品数量的三种方法：减少1，增加1，自定义数量
    @RequestMapping(value = "/quantity_minus") //将对应用户购物车中对应的商品数量减1（注意，要判断quantity是否大于等于1）
    @ResponseBody
    public String quantity_minus(String cname, String flavor_name, HttpSession httpSession, Model model) {
        int user_id = (int)httpSession.getAttribute("user_id");
        shopCartService_gyf.cart_commodity_quantity_minus(user_id, cname, flavor_name);
        return "success";
    }

    @RequestMapping(value = "/quantity_plus") //将对应用户购物车中对应的商品数量减1（注意，要判断quantity是否大于等于1）
    @ResponseBody
    public String quantity_plus(String cname, String flavor_name, HttpSession httpSession, Model model) {
        int user_id = (int)httpSession.getAttribute("user_id");
        shopCartService_gyf.cart_commodity_quantity_plus(user_id, cname, flavor_name);
        return "success";
    }

    @RequestMapping(value = "/quantity_change") //将用户购物车中对应的商品数量改为自定义数量
    @ResponseBody
    public String quantity_change(int new_quantity, String cname, String flavor_name, HttpSession httpSession) {
        int user_id = (int)httpSession.getAttribute("user_id");
        shopCartService_gyf.cart_commodity_quantity_change(user_id, cname, flavor_name, new_quantity);
        return "success";
    }



//    购物车最终结算的方法，分为两步：
//    第一步：shopcart.html的pay()方法向Controller中的/pay传递两个字符串（cname_string和flavor_name_string)
//    /pay方法对这两个字符串用逗号进行切割（和上面的删除方法类似），并逐一查找相应的cart_id，最后将所有cart_id合并成一个String，
//    并写入Session（这样就避免了传递数组）
//    第二步：前端ajax利用window.location.href = "/pay2"跳转到Controller层中的/pay2方法，
//    pay2方法再从session中读取刚才写入的cart_id字符串，用逗号进行切割，转成数组，然后逐一查找，最终将商品信息写入到List<CartVoEntity>中，
//    然后传递给Model，注意先不要删除session中的cart_id字符串（之后提交订单会用到），最后跳转到pay.html页面

    @RequestMapping(value = "/pay")
    @ResponseBody
    public String pay(String cnames_string, String flavor_names_string, String total_price, HttpSession httpSession) {
        String[] cnames = cnames_string.split(",");
        String[] flavor_names = flavor_names_string.split(",");
        int user_id = (int)httpSession.getAttribute("user_id");

        ArrayList<String> cart_id_list = new ArrayList<> ();

        int len = cnames.length;
        for(int i=0;i<len;i++) {
            cart_id_list.add(String.valueOf(shopCartService_gyf.get_cart_id(user_id, cnames[i], flavor_names[i])));
        }


        double total_price_double = Double.parseDouble(total_price);

        //字符串工作
        String cart_id_list_string = cart_id_list.toString().replace("[","").replace("]","");
        httpSession.setAttribute("cart_id_list_string", cart_id_list_string);
        httpSession.setAttribute("total_price",total_price_double); //这里偷了个懒，直接shopcart传回计算好的总价格，存入session，供后面pay.html页面调用，不然的话在pay.html还要单独写一个JavaScript进行计算。

        return "success";
    }

    @RequestMapping(value = "/pay2")
    public String pay2(HttpSession httpSession, Model model) {
        String cart_id_list_string = (String)httpSession.getAttribute("cart_id_list_string");

        List<CartVoEntity> list = new ArrayList<>();

        String[] cart_id_list = cart_id_list_string.split(",\\s+");
        for(int i=0;i<cart_id_list.length;i++) {
            list.add(shopCartService_gyf.get_cart_commodity_info(Integer.valueOf(cart_id_list[i])));
        }
        double total_price = (double)httpSession.getAttribute("total_price");

        model.addAttribute("cart_list",list);
        model.addAttribute("total_price",total_price);


        //-------------------------2019.7.7 21:34  zky  向前端传递user_id下的address_list-------------------------

        int user_id = (int)httpSession.getAttribute("user_id");
        List<AddressEntity> address_list = addressService.search_address_by_user_id(user_id);
        model.addAttribute("address_list",address_list);

        int address_id = 1;
        AddressEntity addressEntity = addressService.get_address_by_id(address_id);
        model.addAttribute("address",addressEntity);



        return "FrontPages/pay";
    }

}
