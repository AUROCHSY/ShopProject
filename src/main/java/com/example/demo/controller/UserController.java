package com.example.demo.controller;

import com.example.demo.entity.CommodityEntity;
import com.example.demo.entity.OrderVoEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.CommodityService;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = {"/","/init"})
    public String init(){
        return "FrontPages/login";
    }

    @RequestMapping(value = "/login")    //login.html form表单提交
    public String login(HttpServletRequest request, HttpSession httpSession, Model model){//通过request传递参数


        String username = (String)request.getParameter("username");//request获取前端login.html页面发送的参数
        String password = (String)request.getParameter("password");

        int user_state = userService.search_user_state_by_user_name(username);
        if (user_state==1){//用户被禁言
            model.addAttribute("error_login","您已被禁言！");
            return "FrontPages/login";
        }

        //用户在url中输入http://localhost:8080/login是直接跳转到这个login()函数，username==null是防止直接进入login页面显示"账号或密码错误"
        if(username==null) return "FrontPages/login";

        String login = userService.login(username,password);//参数传递到service层，返回user_id
        int user_id = userService.search_user_id_by_user_name(username);

        if (login != null){//登陆成功
            httpSession.setAttribute("username",username);
            httpSession.setAttribute("user_id",user_id);

            List<CommodityEntity> tmp = commodityService.sort_commodity_by_createtime();

            if (tmp != null){
                model.addAttribute("sort_result",tmp);//排序后的商品列表
            }
            return "FrontPages/index";
        }
        else {//登陆失败
            model.addAttribute("error_login_fail","账号或密码错误");
            return "FrontPages/login";
        }
    }

    @RequestMapping(value = "/register")    //register.html href提交
    public String register(HttpSession httpSession, Model model){
        return "FrontPages/register";
    }

    @RequestMapping(value = "/register1")    //register.html form表单提交
    public String register1(HttpServletRequest request, HttpSession httpSession, Model model){
//        if(httpSession.getAttribute("username")==null) return "FrontPages/login";   //安全检测

        String username = (String)request.getParameter("username");
        String password = (String)request.getParameter("password");
        String passwordRepeat = (String)request.getParameter("passwordRepeat");

        if(!password.equals(passwordRepeat)){ //注册失败
            model.addAttribute("error_same_password","请输入一样的密码");
            return "FrontPages/register";
        }
        else { //密码一致
            int tmp = userService.register(username,password);
            if (tmp == 0){//用户名重复
                model.addAttribute("error_duplicated_username","用户名已存在");
                return "FrontPages/register";
            }
            else {//用户名不重复
                model.addAttribute("correct_username","not_duplicated");
                return "FrontPages/register";
            }
        }
    }

    @RequestMapping(value = "/register_username")
    @ResponseBody
    public int register_check_username(String username){
        String tmp = userService.username_isExist(username);
        System.out.println(tmp);
        if (tmp==null)  {return 1;}//如果username不存在，说明可以注册，返回1
        else return 0;//如果username存在，说明不可以注册，返回0
    }

    @RequestMapping(value = "/header")
    public String header(HttpServletRequest request, HttpSession httpSession, Model model){
        Object username = httpSession.getAttribute("username");
        model.addAttribute("username",(String)username);
        return "FrontPages/header";
    }

    @RequestMapping(value = "/person_index")       //个人中心
    public String person_index(HttpServletRequest request, HttpSession httpSession, Model model){
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

        return "FrontPages/person/index";
    }
    @RequestMapping(value = "/information")       //个人中心
    public String information(HttpServletRequest request, HttpSession httpSession, Model model){
        if(httpSession.getAttribute("username")==null) return "FrontPages/login";   //安全检测

        Object username = httpSession.getAttribute("username");//httpSession中获取信息
        Object user_id = httpSession.getAttribute("user_id");



        model.addAttribute("username",(String)username);//通过model向前端传数据


        return "FrontPages/person/information";
    }




}
