package com.itguang.weixinsell.service.impl;

import com.itguang.weixinsell.entity.ProductCategoryEntity;
import com.itguang.weixinsell.service.impl.CategoryServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;


    @Test
    public void findOne() throws Exception {
        ProductCategoryEntity categoryEntity = categoryService.findOne(new Integer(1));
        Assert.assertEquals(1,categoryEntity.getCategoryId());
    }

    @Test
    public void findAll() throws Exception {
        List<ProductCategoryEntity> list = categoryService.findAll();
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<ProductCategoryEntity> list = categoryService.findByCategoryTypeIn(Arrays.asList(1, 2, 3, 4));
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void save() throws Exception {
        ProductCategoryEntity entity = categoryService.save(new ProductCategoryEntity("甜点", 18));
        Assert.assertEquals(18,entity.getCategoryType());
    }

}