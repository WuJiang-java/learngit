package com.itheima.controller;

import com.itheima.pojo.Permission;
import com.itheima.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @Author: 吴将
 * \* Time: 2020/7/7 15:43
 * \* Description:
 * \
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("deletePermissionById")
    public String deletePermissionById(@RequestParam("id") String permissionId){

        permissionService.deletePermissionById(permissionId);
        return "redirect:findAll";
    }

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mv=new ModelAndView();
        List<Permission> list = permissionService.findAll();
        mv.addObject("permissionList",list);
        mv.setViewName("permission-list");
        return mv;
    }


    @RequestMapping("/save")
    public String save(Permission permission){

        permissionService.save(permission);
        return "redirect:findAll";
    }


    @RequestMapping("/findById")
    public ModelAndView findById(String id){

        ModelAndView mv=new ModelAndView();
        Permission p=permissionService.findById(id);
        mv.addObject("permission",p);
        mv.setViewName("permission-show");

        return mv;
    }
}
