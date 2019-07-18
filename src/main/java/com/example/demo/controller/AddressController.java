package com.example.demo.controller;

import com.example.demo.entity.AddressEntity;
import com.example.demo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/*
 * Create by Jimmy on 2019/7/2 3:35 PM
 */
@Controller
public class AddressController {
    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/address")
    public String address(HttpSession httpSession, Model model){

        int user_id = (int)httpSession.getAttribute("user_id");
        List<AddressEntity> address_list = addressService.search_address_by_user_id(user_id);
        model.addAttribute("address_list",address_list);

        return "FrontPages/person/address";

        //addres.html 59行 update
    }

    @RequestMapping(value = "/delete_address1")         //删除地址
    @ResponseBody
    public String delete_address(int address_id, HttpSession httpSession){

        int tmp = addressService.delete_address(address_id);
        if (tmp==1){
            return "success";
        }
        else return "failed";
    }

    @RequestMapping(value = "/delete_address2")
    public String delete_address2(HttpSession httpSession, Model model){
        //同上面address()函数
        int user_id = (int)httpSession.getAttribute("user_id");
        List<AddressEntity> address_list = addressService.search_address_by_user_id(user_id);
        model.addAttribute("address_list",address_list);

        return "FrontPages/person/address";
    }

    @RequestMapping(value = "/add_new_address1")     //address.html form表单提交
    public String add_new_address(HttpServletRequest request, HttpSession httpSession, Model model){

        int user_id = (int)httpSession.getAttribute("user_id");

        //获取form表单提交的信息   1.收货人姓名 2.收货人电话  3.收货人地址
        String user_name = (String)request.getParameter("user-name");
        String user_phone = (String)request.getParameter("user-phone");
        String user_intro = (String)request.getParameter("user-intro");

        addressService.insert_address(user_id,user_intro,user_phone,user_name);

        //同上面address()函数
        List<AddressEntity> address_list = addressService.search_address_by_user_id(user_id);
        model.addAttribute("address_list",address_list);


        return "FrontPages/person/address";
    }

    @RequestMapping(value = "/add_new_address2")     //address.html form表单提交
    public String add_new_address2(HttpServletRequest request, HttpSession httpSession, Model model){

        int user_id = (int)httpSession.getAttribute("user_id");

        //获取form表单提交的信息   1.收货人姓名 2.收货人电话  3.收货人地址
        String user_name = (String)request.getParameter("user-name");
        String user_phone = (String)request.getParameter("user-phone");
        String user_intro = (String)request.getParameter("user-intro");

        addressService.insert_address(user_id,user_intro,user_phone,user_name);

        //同上面address()函数
        List<AddressEntity> address_list = addressService.search_address_by_user_id(user_id);
        model.addAttribute("address_list",address_list);

        return "FrontPages/pay";
    }











}
