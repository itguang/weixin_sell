package com.itguang.weixinsell.service;

import com.itguang.weixinsell.entity.ProductCategoryEntity;

import java.util.List;

public interface CategoryService {

    ProductCategoryEntity findOne(Integer id);

    List<ProductCategoryEntity> findAll();

    List<ProductCategoryEntity> findByCategoryTypeIn(List<Integer> types);

    ProductCategoryEntity save(ProductCategoryEntity categoryEntity);
}
