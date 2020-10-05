package com.itheima.service;

import com.itheima.pojo.Role;
import com.itheima.pojo.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<UserInfo> findAll();

    void save(UserInfo userInfo);

    UserInfo findById(String id);

    List<Role> findOtherRole(String id);

    void addRoleToUser(String userId, String[] ids);
}
