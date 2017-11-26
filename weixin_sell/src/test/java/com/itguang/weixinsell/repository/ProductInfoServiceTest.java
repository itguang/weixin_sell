package com.itguang.weixinsell.repository;

import com.itguang.weixinsell.service.ProductInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author itguang
 * @create 2017-11-26 13:28
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceTest {

    @Autowired
    private ProductInfoService infoService;


    @Test
    public void  test1(){
        Integer i = infoService.updatePrice("1", 16);
        System.out.println(i);

    }
}
