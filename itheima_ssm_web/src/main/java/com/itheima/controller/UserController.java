package com.itheima.controller;

import com.itheima.pojo.Role;
import com.itheima.pojo.UserInfo;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @Author: 吴将
 * \* Time: 2020/7/7 10:29
 * \* Description:
 * \
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    //用于在用户和角色之间建立关系
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(String userId,String[] ids){

        userService.addRoleToUser(userId,ids);
        return "redirect:findAll";
    }

    //查找要操作的用户和可以添加的角色
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(String id){
        ModelAndView mv=new ModelAndView();

        UserInfo user = userService.findById(id);
        List<Role> roleList=userService.findOtherRole(id);

        mv.addObject("user",user);
        mv.addObject("roleList",roleList);

        mv.setViewName("user-role-add");

        return mv;
    }

    //查询指定id的用户
    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        ModelAndView mv=new ModelAndView();
        UserInfo userInfo=userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show1");

        return mv;
    }

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mv=new ModelAndView();

        List<UserInfo> list=userService.findAll();
        mv.addObject("userList",list);
        mv.setViewName("user-list");
        return mv;
    }


    @RequestMapping("/save")
    public String save(UserInfo userInfo){

        userService.save(userInfo);
        return "redirect:findAll";
    }
}
