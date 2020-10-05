package com.itheima.controller;

import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.service.IRoleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @Author: 吴将
 * \* Time: 2020/7/7 14:44
 * \* Description:
 * \
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(String roleId,String[] ids){

        roleService.addPermissionToRole(roleId,ids);
        return "redirect:findAll";

    }

    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam("id") String roleId){
        ModelAndView mv=new ModelAndView();

        Role role = roleService.findById(roleId);
        List<Permission> permissionList=roleService.findOtherPermission(roleId);

        mv.addObject("role",role);
        mv.addObject("permissionList",permissionList);

        mv.setViewName("role-permission-add");

        return mv;
    }


    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mv=new ModelAndView();

        List<Role> list = roleService.findAll();
        mv.addObject("roleList",list);
        mv.setViewName("role-list");
        return mv;
    }


    @RequestMapping("/save")
    public String save(Role role){

        roleService.save(role);
        return "redirect:findAll";
    }


    @RequestMapping("/findById")
    public ModelAndView findById(String id){

        ModelAndView mv=new ModelAndView();
        Role role=roleService.findById(id);
        mv.addObject("role",role);
        mv.setViewName("role-show");

        return mv;
    }

    @RequestMapping("/deleteRole")
    public String deleteRole(String id){
        roleService.deleteRoleById(id);
        return "redirect:findAll";
    }
}
