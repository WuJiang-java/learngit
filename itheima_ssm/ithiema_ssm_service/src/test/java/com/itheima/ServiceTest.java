package com.itheima;

import com.itheima.dao.IOrdersDao;
import com.itheima.pojo.Orders;
import com.itheima.pojo.Product;
import com.itheima.service.IProductService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @Author: 吴将
 * \* Time: 2020/5/31 16:36
 * \* Description:测试service层是否配置成功
 * \
 */
public class ServiceTest {

    @Test
    public void testService(){
        ApplicationContext ap=new ClassPathXmlApplicationContext("applicationContext.xml");
        IOrdersDao ordersDao = ap.getBean(IOrdersDao.class);
        List<Orders> list = ordersDao.findAll();
        for (Orders orders : list) {
            System.out.println(orders.getOrderDesc());
        }

    }
}
