package com.itheima.service.impl;

import com.itheima.dao.IPermissionDao;
import com.itheima.pojo.Permission;
import com.itheima.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @Author: 吴将
 * \* Time: 2020/7/7 15:48
 * \* Description:
 * \
 */
@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }

    @Override
    public Permission findById(String id) {

        return permissionDao.findById(id);
    }

    @Override
    public void deletePermissionById(String permissionId) {
        //role_permission表中删除
        permissionDao.deleteFromRole_PermissionByPId(permissionId);

        permissionDao.deletePermissionById(permissionId);
    }
}
