package com.itheima.service.impl;

import com.itheima.dao.IProductDao;
import com.itheima.pojo.Product;
import com.itheima.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @Author: 吴将
 * \* Time: 2020/5/31 16:14
 * \* Description:业务层实现类
 * \
 */
@Service("productService")
@Transactional
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao productDao;

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Product> findAllProduct() {
        return productDao.findAllProduct();
    }

    /**
     * 保存所有
     * @param product
     */
    @Override
    public void save(Product product) {
        productDao.save(product);
    }
}
