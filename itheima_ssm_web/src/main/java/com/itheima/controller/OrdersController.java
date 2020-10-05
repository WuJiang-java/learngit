package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.pojo.Orders;
import com.itheima.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @Author: 吴将
 * \* Time: 2020/6/1 13:44
 * \* Description:订单表现层
 * \
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private IOrdersService ordersService;

    //未分页
    /*@RequestMapping(path = "/findAll")
    public ModelAndView findAll(){
        ModelAndView mv=new ModelAndView();
        List<Orders> list = ordersService.findAll();
        mv.addObject("ordersList",list);
        mv.setViewName("orders-list");

        return mv;
    }*/

    //分页代码
    @RequestMapping( "/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "size",required = true,defaultValue = "4") Integer size){
        ModelAndView mv=new ModelAndView();
        List<Orders> list = ordersService.findAll(page, size);
        PageInfo pageInfo=new PageInfo(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");

        return mv;
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String id){
        ModelAndView mv=new ModelAndView();
        Orders orders=ordersService.findById(id);
        System.out.println(orders);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }
}
