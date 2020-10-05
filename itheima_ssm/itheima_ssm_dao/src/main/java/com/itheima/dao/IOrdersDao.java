package com.itheima.dao;

import com.itheima.pojo.Member;
import com.itheima.pojo.Orders;
import com.itheima.pojo.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IOrdersDao {

    /**
     * 查询所有
     * @return
     */
    @Select("select * from orders")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",javaType = Product.class,one = @One(select = "com.itheima.dao.IProductDao.findById"))


    })
    List<Orders> findAll();

    /**
     * 根据id进行查询
     * @param id
     * @return
     */
    @Select("select * from orders where id=#{id}")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",javaType = Product.class,one = @One(select = "com.itheima.dao.IProductDao.findById")),
            @Result(column = "memberId",property = "member",javaType = Member.class,one=@One(select = "com.itheima.dao.IMemberDao.findById")),
            @Result(column = "id",property = "travellers",javaType = java.util.List.class,many = @Many(select = "com.itheima.dao.ITravellerDao.findByOrdersId"))

    })
    Orders findById(String id);
}
