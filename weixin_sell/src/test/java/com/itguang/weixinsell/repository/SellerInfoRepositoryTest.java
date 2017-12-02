package com.itguang.weixinsell.repository;

import com.itguang.weixinsell.entity.SellerInfoEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Test
    public void findbyOpenidTest(){

        SellerInfoEntity sellerInfoEntity = sellerInfoRepository.findByOpenid("110");
        Assert.assertNotNull(sellerInfoEntity);


    }


}