package com.itguang.weixinsell.dao;

import com.itguang.weixinsell.dao.mapper.ProductCategoryMapper;
import com.itguang.weixinsell.entity.ProductCategoryEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperTest {


    @Autowired
    private ProductCategoryMapper productCategoryMapper;


    @Test
    public void test1(){
        Map<String, Object> map = new HashMap<>();
        map.put("categoryName","光哥最爱啊");
        map.put("categoryType",888);
        int i = productCategoryMapper.inseret2(map);
        Assert.assertEquals(i,1);
    }



    @Test
    public void test2(){
        ProductCategoryEntity categoryEntity = new ProductCategoryEntity();
        categoryEntity.setCategoryName("实体类");
        categoryEntity.setCategoryType(999);
        int i = productCategoryMapper.inseret(categoryEntity);
        Assert.assertEquals(i,1);
    }




    @Test
    public void selectByCategoryType(){

        ProductCategoryEntity categoryEntity = productCategoryMapper.selectByCategoryType(999);
        Assert.assertNotNull(categoryEntity);
    }






}