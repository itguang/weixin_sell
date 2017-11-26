package com.itguang.weixinsell.repository;

import com.itguang.weixinsell.entity.ProductInfoEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository infoRepository;

    @Test
    public void test1(){
        List<ProductInfoEntity> productInfoEntities = infoRepository.findAllByProductName("北京烤鸭");
        System.out.println(productInfoEntities.toString());

    }


    @Test
    public void test2(){
        List<ProductInfoEntity> productInfoEntities = infoRepository.findProductInfo();
        System.out.println(productInfoEntities.toString());

    }

    @Test
    public void test3(){
        List<ProductInfoEntity> productInfoEntities = infoRepository.findByProductNameStartingWithAndProductPriceLessThan("黄焖鸡",10.0);
        productInfoEntities.stream()
                .forEach(System.out::println);

    }

    @Test
    public void findMaxPriceTest(){
        List<ProductInfoEntity> productInfoEntities = infoRepository.findMaxPrice();
        productInfoEntities.stream()
                .forEach(System.out::println);

    }

    @Test
    public void findParamTest(){
        List<ProductInfoEntity> productInfoEntities = infoRepository.findParam("北京烤鸭",120);
        productInfoEntities.stream()
                .forEach(System.out::println);

    }

    @Test
    public void findParam2Test(){
        List<ProductInfoEntity> productInfoEntities = infoRepository.findParam2("北京烤鸭",120);
        productInfoEntities.stream()
                .forEach(System.out::println);

    }


    @Test
    public void getCountTest(){
        Integer count = infoRepository.getCount();
        System.out.println(count);

    }


}