package com.example.demo.controller;

/*
 * Create by Jimmy on 2019/7/1 9:08 PM
 */

import com.example.demo.entity.CommodityEntity;
import com.example.demo.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class CommodityController {
    @Autowired
    private CommodityService commodityService;

    @RequestMapping(value = "/detail")
    public String detail(){
        return "FrontPages/detail";
    }

    @RequestMapping(value = "/search")      //header.html form表单提交  index.html href提交
    public String search(HttpServletRequest request, HttpSession httpSession, Model model){
        String search_input = request.getParameter("index_none_header_sysc");

        List<CommodityEntity> tmp = commodityService.search_commodity(search_input);
        System.out.println("==================================================================================");
        System.out.println(tmp);
        if(tmp != null){//搜索成功
            model.addAttribute("search_result",tmp);//搜索结果商品list
            model.addAttribute("result_num",tmp.size());//搜索结果商品数
            model.addAttribute("search_key",search_input);//搜索关键词
            return "FrontPages/search";
        }
        else{//搜索失败
            model.addAttribute("error_search_fail","没有相关商品");
            return "FrontPages/index";
        }
    }

    @RequestMapping(value = "/person_index")       //个人中心
    public String person_index(){

        return "FrontPages/person/index";
    }
}
