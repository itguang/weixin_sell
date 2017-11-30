package com.itguang.weixinsell.service.impl;

import com.itguang.weixinsell.entity.ProductInfoEntity;
import com.itguang.weixinsell.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl infoService;

    @Test
    public void findByProductStatusTest(){
        List<ProductInfoEntity> list = infoService.findByProductStatus(ProductStatusEnum.UP.getCode());
        Assert.assertNotEquals(0,list.size());

    }


    @Test
    public void findAllTest(){
        Pageable pageable = new PageRequest(1,5);
        Page<ProductInfoEntity> page = infoService.findAll(pageable);
        System.out.println(page.getSize());

    }


    @Test
    public void saveTest(){

        ProductInfoEntity entity = new ProductInfoEntity();
        entity.setProductId("8888");
        entity.setProductName("皮皮虾2");
        entity.setCategoryType(5);
        entity.setProductDescription("我们走!!!");
        entity.setProductIcon("http:xxx.jpg");
        entity.setProductPrice(new BigDecimal(25.0));
        entity.setProductStock(999);

        infoService.save(entity);


    }




    @Test
    public void enmuTest(){
        Integer upCode = ProductStatusEnum.UP.getCode();
        Integer downCode = ProductStatusEnum.DOWN.getCode();

        System.out.println(upCode);//0
        System.out.println(downCode);//1


    }


}