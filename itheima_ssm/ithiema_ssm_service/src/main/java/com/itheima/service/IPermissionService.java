package com.itheima.service;

import com.itheima.pojo.Permission;

import java.util.List;

public interface IPermissionService {

    List<Permission> findAll();

    void save(Permission permission);

    Permission findById(String id);

    void deletePermissionById(String permissionId);
}
