package com.itguang.weixinsell.service;

import com.itguang.weixinsell.dto.CartDTO;
import com.itguang.weixinsell.entity.ProductInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {


    ProductInfoEntity findOne(String id);


    /**
     * 查询所有上架商品
     *
     * @param status 0正常/1下架
     * @return
     */
    List<ProductInfoEntity> findByProductStatus(Integer status);


    /**
     * 分类查询所有商品
     *
     * @param pageable
     * @return
     */
    Page<ProductInfoEntity> findAll(Pageable pageable);


    /**
     * 保存商品
     *
     * @param infoEntity
     * @return
     */
    ProductInfoEntity save(ProductInfoEntity infoEntity);


    //加库存
    void increaseStock(List<CartDTO> cartDTOS);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOS);

    //上架

    //下架

}