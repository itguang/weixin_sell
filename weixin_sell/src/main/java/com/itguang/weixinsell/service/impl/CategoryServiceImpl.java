package com.itguang.weixinsell.service.impl;

import com.itguang.weixinsell.entity.ProductCategoryEntity;
import com.itguang.weixinsell.repository.ProductCategoryRepository;
import com.itguang.weixinsell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类目实现
 *
 * @author itguang
 * @create 2017-11-27 9:33
 **/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository categoryRepository;


    @Override
    public ProductCategoryEntity findOne(Integer id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public List<ProductCategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<ProductCategoryEntity> findByCategoryTypeIn(List<Integer> types) {
        return categoryRepository.findAllByCategoryTypeIn(types);
    }

    @Override
    public ProductCategoryEntity save(ProductCategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }
}
