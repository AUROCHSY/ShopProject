package com.example.demo.controller;

/*
 * Create by Jimmy on 2019/7/1 9:08 PM
 */

import com.example.demo.entity.AddressEntity;
import com.example.demo.entity.CommodityEntity;
import com.example.demo.entity.EvatuateEntity;
import com.example.demo.entity.FlavorEntity;
import com.example.demo.service.AddressService;
import com.example.demo.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class CommodityController {
    @Autowired
    private CommodityService commodityService;

    @RequestMapping(value = "/detail1")         //index||search最新商品ajax中get_detail()函数传递commodity_id到detail1
    @ResponseBody
    public String detail(int commodity_id, HttpSession httpSession){

        //获取dao层的数据
        CommodityEntity commodity = commodityService.search_commodity_by_commodity_id(commodity_id);
        List<FlavorEntity> flavor = commodityService.search_flavor_by_commodity_id(commodity_id);
        List<EvatuateEntity> evaluate = commodityService.search_evaluate_by_commodity_id(commodity_id);

//        System.out.println("==================================================================================");
//        System.out.println(commodity);
//        System.out.println(flavor);
//        System.out.println(evaluate);

        //将dao层返回的数据传递给session
        httpSession.setAttribute("commodity",commodity);
        httpSession.setAttribute("flavor",flavor);
        httpSession.setAttribute("evaluate",evaluate);

        return "success";
    }

    @RequestMapping(value = "/detail2")
    public String detail2(Model model, HttpSession httpSession){
        //先从session中获得参数
        CommodityEntity commodity = (CommodityEntity)httpSession.getAttribute("commodity");
        List<FlavorEntity> flavor = (List<FlavorEntity>)httpSession.getAttribute("flavor");
        List<EvatuateEntity> evaluate = (List<EvatuateEntity>)httpSession.getAttribute("evaluate");

        //将获得的参数放入model中，传入detail.html网页中
        model.addAttribute("search_result",commodity);//搜索点击商品
        model.addAttribute("flavor_result",flavor);//搜索点击商品的口味
        model.addAttribute("evaluate_result",evaluate);//搜索点击商品的全部评价

        //将session中的参数移除
        httpSession.removeAttribute("commodity");
        httpSession.removeAttribute("flavor");
        httpSession.removeAttribute("evaluate");

        return "FrontPages/detail";
    }

    @RequestMapping(value = "/detail")
    public String detail(){
        return "FrontPages/detail";
    }



    @RequestMapping(value = "/search")      //header.html form表单提交  index.html href提交
    public String search(HttpServletRequest request, HttpSession httpSession, Model model){
        String search_input = request.getParameter("index_none_header_sysc");

        List<CommodityEntity> list = commodityService.search_commodity(search_input);

        if(list != null){//搜索成功
            model.addAttribute("search_result",list);//搜索结果商品list
            model.addAttribute("result_num",list.size());//搜索结果商品数
            model.addAttribute("search_key",search_input);//搜索关键词
            return "FrontPages/search";
        }
        else{//搜索失败
            model.addAttribute("error_search_fail","没有相关商品");
            return "FrontPages/index";
        }
    }



    @RequestMapping(value = "/shop_index")      //header.html form表单提交
    public String shop_index(HttpServletRequest request, HttpSession httpSession, Model model){

//        List<SecondCategoryEntity> tmp1 = commodityService.search_second_category_entity_by_first_category_id();
//        List<CommodityEntity> tmp2 = commodityService.search_commodity_entity_by_second_category_id();
        List<CommodityEntity> tmp3 = commodityService.sort_commodity_by_createtime();

        if (tmp3 != null){
            model.addAttribute("sort_result",tmp3);//排序后的商品列表
        }
        return "FrontPages/index";
    }


    @RequestMapping(value = "/cart")        //form表单提交 购物车
    public String cart(HttpServletRequest request, HttpSession httpSession, Model model){

        String quantity = request.getParameter("quantity");

        return "FrontPages/detail";
    }


}
