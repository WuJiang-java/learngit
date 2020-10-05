package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.IOrdersDao;
import com.itheima.pojo.Orders;
import com.itheima.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @Author: 吴将
 * \* Time: 2020/6/1 13:32
 * \* Description:
 * \
 */
@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {
    @Autowired
    private IOrdersDao ordersDao;

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Orders> findAll(int page,int size) {

        //使用分页插件PageHelper
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    /**
     * 根据id查询订单信息
     * @param id
     */
    @Override
    public Orders findById(String id) {
        return ordersDao.findById(id);
    }
}
