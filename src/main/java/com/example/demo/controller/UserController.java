package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/","/init"})
    public String init(){
        return "FrontPages/login";
    }

    @RequestMapping(value = "/login")    //login.html form表单提交
    public String login(HttpServletRequest request, HttpSession httpSession, Model model){//通过request传递参数

        String username = (String)request.getParameter("username");//request获取前端login.html页面发送的参数
        String password = (String)request.getParameter("password");

        //用户在url中输入http://localhost:8080/login是直接跳转到这个login()函数，username==null是防止直接进入login页面显示"账号或密码错误"
        if(username==null) return "FrontPages/login";

        String login = userService.login(username,password);//参数传递到service层，返回user_id
        if (login != null){//登陆成功
            httpSession.setAttribute("username",username);
            httpSession.setAttribute("password",password);
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
        String passwordRepeat = request.getParameter("passwordRepeat");

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


//    @RequestMapping(value = "/search")      //header.html form表单提交
//    public String search(HttpServletRequest request, HttpSession httpSession, Model model){
//        String search_input = request.getParameter("index_none_header_sysc");
//
//        String tmp = userService.search_commodity(search_input);
//        if(tmp != null){//搜索成功
//            return "FrontPages/success";
//        }
//        else{//搜索失败
//            model.addAttribute("error_login_fail","账号或密码错误");
//            return "FrontPages/login";
//        }
//

//    }

    @RequestMapping(value = "/index")      //header.html form表单提交
    public String index(HttpServletRequest request, HttpSession httpSession, Model model){
        return "FrontPages/index";
    }
}
