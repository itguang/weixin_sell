package com.itguang.weixinsell.repository;

import com.itguang.weixinsell.entity.ProductCategoryEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository categoryRepository;

    @Test
    public void findOneTest(){
        ProductCategoryEntity productCategoryEntity = categoryRepository.findOne(1);
        System.out.println(productCategoryEntity.toString());
    }

    @Test
    public void updateTest(){
        ProductCategoryEntity productCategoryEntity = categoryRepository.findOne(1);
        productCategoryEntity.setCategoryName("图书");

        categoryRepository.save(productCategoryEntity);

    }
    @Test
    public void test3(){


    }

}