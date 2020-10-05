package com.itheima.controller;

import com.itheima.pojo.Product;
import com.itheima.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @Author: 吴将
 * \* Time: 2020/5/31 18:00
 * \* Description:
 * \
 */
@Controller
@RequestMapping( "/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @RequestMapping("/save")
    public String save(Product product){
        productService.save(product);
        return "redirect:findAll";
    }

    @RequestMapping( "/findAll")
    @RolesAllowed("ADMIN")
    public ModelAndView findAllProduct(){
        ModelAndView mv=new ModelAndView();
        List<Product> list = productService.findAllProduct();
        mv.addObject("list",list);
        mv.setViewName("product-list");

        return mv;
    }
}
