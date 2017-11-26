package com.itguang.weixinsell.repository;

import com.itguang.weixinsell.entity.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity,Integer> {

    //CRUD 操作

    //增 save(entity), save(entities)


    //删 delete(id),delete(entity),delete(entities),deleteAll

    //查 findOne(id) ,findAll, exits(id)
}