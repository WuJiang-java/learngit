package com.itheima.dao;

import com.itheima.pojo.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产品数据持久层
 */
@Repository
public interface IProductDao {
    /**
     * 根据id查询一个
     */
    @Select("select * from product where id=#{id}")
    Product findById(String id);

    /**
     * 查询所有
     * @return
     */
    @Select("select * from product")
    List<Product> findAllProduct();

    /**
     * 保存所有
     * @param product
     */
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);
}
