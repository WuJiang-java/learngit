package com.itheima.dao;

import com.itheima.pojo.Role;
import com.itheima.pojo.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    //进行spring-security登录验证，根据用户名查询出完整的用户信息
    @Select("select * from users where username=#{username}")
    @Results({

            @Result(id=true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.dao.IRoleDao.findRoleByUserId")),
            })
    UserInfo findByUserName(String username);

    @Select("select * from users")
    List<UserInfo> findAll();

    @Select("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.dao.IRoleDao.findRoleByUserId")),

    })
    UserInfo findById(String id);

    @Select("select * from role where id not in(select roleId from users_role where userId=#{id})")
    List<Role> findOtherRole(String id);

    @Insert("insert into users_role(userId,roleId) values(#{userId},#{id})")
    void addRoleToUser(@Param("userId") String userId, @Param("id") String id);
}
