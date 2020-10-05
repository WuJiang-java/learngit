package com.itheima.service;


import com.itheima.pojo.Product;

import java.util.List;

/**
 * 业务层
 */
public interface IProductService {

    /**
     * 查询所有
     * @return
     */
    List<Product> findAllProduct();

    /**
     * 保存产品
     * @param product
     */
    void save(Product product);
}
