package com.itheima.service;

import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll();

    void save(Role role);

    Role findById(String id);

    void deleteRoleById(String id);

    List<Permission> findOtherPermission(String roleId);

    void addPermissionToRole(String roleId, String[] ids);
}
