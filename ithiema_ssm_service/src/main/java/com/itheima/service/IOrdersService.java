package com.itheima.service;

import com.itheima.pojo.Orders;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @Author: 吴将
 * \* Time: 2020/6/1 13:31
 * \* Description:订单业务层接口
 * \
 */
public interface IOrdersService {

    List<Orders> findAll(int page,int size);

    Orders findById(String id);
}
