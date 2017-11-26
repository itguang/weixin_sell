package com.itguang.weixinsell.service;

import com.itguang.weixinsell.entity.ProductCategoryEntity;
import com.itguang.weixinsell.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author itguang
 * @create 2017-11-26 14:09
 **/
@Service
public class ProductCategoryCRUDService {

    @Autowired
    private ProductCategoryRepository categoryRepository;

    /**
     * save(entity)
     * @param entity
     * @return
     */
    @Transactional
    public ProductCategoryEntity saveOne(ProductCategoryEntity entity){
        ProductCategoryEntity categoryEntity = categoryRepository.save(entity);
        return categoryEntity;

    }



    /**
     * save(entities)
     * @param entities
     * @return
     */
    @Transactional
    public void saveMany(List<ProductCategoryEntity> entities){
        categoryRepository.save(entities);

    }

}
