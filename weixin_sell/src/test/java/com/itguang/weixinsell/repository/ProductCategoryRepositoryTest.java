package com.itguang.weixinsell.repository;

import com.itguang.weixinsell.entity.ProductCategoryEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {


    @Autowired
    private ProductCategoryRepository categoryRepository;


    @Test
    public void test() {


    }


    @Test
    public void findByPrOrCategoryTypeTest() {
        List<Integer> types = Arrays.asList(1, 2, 3, 4, 5);
        List<ProductCategoryEntity> list = categoryRepository.findAllByCategoryTypeIn(types);
        list.stream()
                .forEach(System.out::println);


    }

}