package com.itheima.dao;

import com.itheima.pojo.Permission;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IPermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id})")
    List<Permission> findPermissionByRoleId(String id);


    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission);

    @Select("select * from permission where id=#{id}")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "permissionName",property = "permissionName"),
            @Result(column = "url",property = "url"),
            @Result(column = "id",property = "roles",javaType = java.util.List.class,many=@Many(select = "com.itheima.dao.IRoleDao.findRoleByPermissionId"))
    })
    Permission findById(String id);

    @Delete("delete from role_permission where permissionId=#{permissionId}")
    void deleteFromRole_PermissionByPId(String permissionId);

    @Delete("delete from permission where id=#{permissionId}")
    void deletePermissionById(String permissionId);
}
