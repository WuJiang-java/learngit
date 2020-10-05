package com.itheima.service.impl;

import com.itheima.dao.IRoleDao;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @Author: 吴将
 * \* Time: 2020/7/7 15:03
 * \* Description:
 * \
 */
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;


    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }

    @Override
    public void deleteRoleById(String id) {
        //从user_role表中删除
        roleDao.deleteFromUser_RoleByRoleId(id);
        //从role_permission表中删除
        roleDao.deleteFromRole_PermissionByRoleId(id);
        roleDao.deleteRoleById(id);
    }

    @Override
    public List<Permission> findOtherPermission(String roleId) {
        return roleDao.findOtherPermission(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] ids) {
        for (String id : ids) {
            roleDao.addPermissionToRole(roleId,id);
        }
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
