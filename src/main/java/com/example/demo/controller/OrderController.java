package com.example.demo.controller;

import com.example.demo.entity.AddressEntity;
import com.example.demo.entity.OrderEntity;
import com.example.demo.entity.OrderVoEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.AddressService;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/*
 * Create by Jimmy on 2019/7/4 2:14 PM
 */
@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/order")
    public String goto_order(HttpServletRequest request, HttpSession httpSession, Model model){
        if(httpSession.getAttribute("username")==null) return "FrontPages/login";   //安全检测

        Object username = httpSession.getAttribute("username");//httpSession中获取信息
        Object user_id = httpSession.getAttribute("user_id");

        double money = userService.search_money_by_user_id((int)user_id);//调用service层获取数据库数据
        List<OrderVoEntity> list = orderService.search_order_by_user_id((int)user_id);
        List<OrderVoEntity> order = new ArrayList();

        //判断是否有相同的order_id，如果有则不加入order
        int order_id;
        for(int i=0;i<list.size();i++){
            if (i==0){
                order.add(list.get(0));
            }
            else if (list.get(i).order_id!=list.get(i-1).order_id){
                order.add(list.get(i));
            }
        }

        model.addAttribute("username",(String)username);//通过model向前端传数据
        model.addAttribute("money",money);
        model.addAttribute("order",order);



        return "FrontPages/person/order";
    }

    @RequestMapping(value = "/orderinfo")
    public String goto_orderinfo(HttpServletRequest request, HttpSession httpSession, Model model){
        return "FrontPages/orderinfo";
    }


    //支付成功后，将该订单加入用户订单，减去账户余额
    //并且要传递收货人，联系电话，收货地址。
    @RequestMapping(value = "/pay_success1")
    @ResponseBody
    public String pay_success1(double total_price, int address_id,HttpSession httpSession){
        if(httpSession.getAttribute("username")==null) return "FrontPages/login";   //安全检测

        int user_id = (int)httpSession.getAttribute("user_id");

        double money = orderService.get_money(user_id);
        double total_price_double = (double)total_price;
        if (money<total_price_double){//余额不足
            return "failed";
        }
        else {
            double new_money = money-total_price_double;
            httpSession.setAttribute("total_price",total_price);
            httpSession.setAttribute("new_money",new_money);
            httpSession.setAttribute("address_id",address_id);

            //增加订单
            orderService.add_order(user_id,address_id,total_price,1);
            return "success";
        }
    }

    //支付成功后，将该订单加入用户订单，更新账户余额
    @RequestMapping(value = "/pay_success2")
    public String pay_success2(HttpSession httpSession, Model model){
        if(httpSession.getAttribute("username")==null) return "FrontPages/login";   //安全检测

        //从session中获取数据
        int user_id = (int)httpSession.getAttribute("user_id");
        double total_price = (double)httpSession.getAttribute("total_price");
        double new_money = (double)httpSession.getAttribute("new_money");
        int address_id = (int)httpSession.getAttribute("address_id");


        //向dao层获取相关数据，并通过model传递给前端
        AddressEntity address = addressService.get_address_by_id(address_id);
        model.addAttribute("total_price",total_price);
        model.addAttribute("address",address);

        //将session中的参数移除
        httpSession.removeAttribute("address_id");
        httpSession.removeAttribute("new_money");
        httpSession.removeAttribute("total_price");

        orderService.update_account(user_id,new_money);

        return "FrontPages/success";
    }
}
