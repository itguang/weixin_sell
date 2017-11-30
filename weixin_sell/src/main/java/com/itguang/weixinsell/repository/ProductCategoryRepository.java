package com.itguang.weixinsell.repository;

import com.itguang.weixinsell.entity.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity,Integer> {

    List<ProductCategoryEntity> findAllByCategoryTypeIn(List<Integer> types);
}