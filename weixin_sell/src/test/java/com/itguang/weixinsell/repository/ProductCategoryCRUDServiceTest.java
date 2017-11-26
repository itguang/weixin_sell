package com.itguang.weixinsell.repository;

import com.itguang.weixinsell.entity.ProductCategoryEntity;
import com.itguang.weixinsell.service.ProductCategoryCRUDService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryCRUDServiceTest {
    @Autowired
    private ProductCategoryCRUDService categoryCRUDService;


    //CRUD 操作

    //增 save(entity), save(entities)
    @Test
    public void save1(){
        ProductCategoryEntity categoryEntity = new ProductCategoryEntity();
        categoryEntity.setCategoryName("肯德基20");
        categoryEntity.setCategoryType(20);

        ProductCategoryEntity save = categoryCRUDService.saveOne(categoryEntity);
        System.out.println(save);
    }

    // save(entities)
    @Test
    public void saveManyTest(){

        ProductCategoryEntity categoryEntity = new ProductCategoryEntity();
        categoryEntity.setCategoryName("test21");
        categoryEntity.setCategoryType(101);

        ProductCategoryEntity categoryEntity2 = new ProductCategoryEntity();
        categoryEntity2.setCategoryName("test22");
        categoryEntity.setCategoryType(201);

        ArrayList<ProductCategoryEntity> categoryEntities = new ArrayList<>();
//        categoryEntities.add(categoryEntity);
        categoryEntities.add(categoryEntity2);

        categoryCRUDService.saveMany(categoryEntities);


    }


    //删 delete(id),delete(entity),delete(entities),deleteAll

    //查 findOne(id) ,findAll, exits(id)


    @Test
    public void test1(){


    }

}