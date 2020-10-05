package com.itheima.dao;

import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    //根据id 查询出所有对应的角色
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.dao.IPermissionDao.findPermissionByRoleId"))
    })
    List<Role> findRoleByUserId(String userId);

    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    @Select("select * from role where id=#{id}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.dao.IPermissionDao.findPermissionByRoleId"))

    })
    Role findById(String id);

    @Select("select * from role where id in (select roleId from role_permission where permissionId=#{permissionId})")
    List<Role> findRoleByPermissionId(String permissionId);

    @Delete("delete from role where id=#{id}")
    void deleteRoleById(String id);

    @Delete("delete from role_permission where roleId=#{id}")
    void deleteFromRole_PermissionByRoleId(String id);

    @Delete("delete from users_role where roleId=#{id}")
    void deleteFromUser_RoleByRoleId(String id);

    @Select("select * from permission where id not in(select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermission(String roleId);

    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{id})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("id") String id);
}
